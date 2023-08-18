<script lang="ts">
    import { GameStatus, type Game, type Position, type Tile } from '$lib/types/game.d';
    import { onMount } from 'svelte';
    import type { PageData } from './$types';
    import { player } from '$lib/stores/player';
    import type { Player } from '$lib/types/player';
    import GameApiService from '$lib/services/api/game-api.service';
    import DiskComponent from './DiskComponent.svelte';
    import ValidMove from './ValidMove.svelte';
    import PlayerCard from './PlayerCard.svelte';
    import { goto } from '$app/navigation';

    export let data: PageData;

    const gameApiService = new GameApiService();

    let loggedPlayer: Player | null = $player;
    
    let game: Game = data.game

    onMount(() => {
        // subscribe to sse
        const sse = gameApiService.getGameSSE(game.id);
        sse.onmessage = (event) => {
            const data: Game = JSON.parse(event.data);

            if (data.status === GameStatus.FINISHED)
                sse.close();
            
            return updateGame(data);
        }

        return () => {
            // unsubscribe from sse
            sse.close();
        }
    });

    const loggedPlayerProps = {
        username: loggedPlayer!.username,
        color: game.players.find((gamePlayer) => gamePlayer.player.id === loggedPlayer!.id)!.color,
    }

    const opponentPlayerProps = {
        username: game.players.find((gamePlayer) => gamePlayer.player.id !== loggedPlayer!.id)!.player.username,
        color: game.players.find((gamePlayer) => gamePlayer.player.id !== loggedPlayer!.id)!.color,
    }

    $: isPlayerTurn = game.currentPlayer.player.id === loggedPlayer!.id;

    // Build board
    $: tiles = game.grid.tiles;
    const boardSize = game.grid.size;
    const boardWidth = boardSize.width;
    const boardHeight = boardSize.height;

    // Determine if the turn can be skipped
    // No tile should be playable.
    $: isTurnSkippable = !tiles.some((tile) => tile.playable);

    const playMove = async (position: Position) => {
        const response = await gameApiService.playMove(game.id, position);

        if (response.ok) {
            const data: Game = await response.json();
            return updateGame(data);
        }
    }

    const skipMove = async () => {
        const response = await gameApiService.skipMove(game.id);

        if (response.ok) {
            const data: Game = await response.json();
            return updateGame(data);
        }
    }

    const updateGame = (newGame: Game) => {        
        if (newGame.status === GameStatus.FINISHED)
            return goto(`/games/${game.id}/results`)

        game = newGame;
    }

</script>

<div class="h-full w-full grid grid-cols-7">
    <div class="flex flex-col col-span-1">
        <PlayerCard 
            username={loggedPlayerProps.username}
            color={loggedPlayerProps.color}
            isCurrentTurnPlayer={isPlayerTurn}
        />

        {#if isTurnSkippable && isPlayerTurn}
            <button
                class="bg-green-400 hover:bg-green-500 text-white font-bold py-2 px-4 rounded"
                on:click={skipMove}
            >
                Skip turn
            </button>
        {/if}
    </div>

    <div class="col-span-5 w-full h-full mx-auto">
        <div class="grid bg-green-400 p-3 gap-1 grid-cols-[repeat(8,1fr)]">
            {#each tiles as tile}
                <div class="aspect-square bg-green-200">
                    {#if tile.disk}
                        <DiskComponent diskColor={tile.disk.gamePlayer.color}/>
                    {:else if tile.playable && isPlayerTurn }
                        <ValidMove on:click={() => playMove(tile.position) }/>
                    {:else}
                        <div></div>
                    {/if}
                </div>
            {/each}
        </div>
    </div>

    <div class="flex col-span-1">
        <PlayerCard 
            username={opponentPlayerProps.username}
            color={opponentPlayerProps.color}
            isCurrentTurnPlayer={!isPlayerTurn}
        />
    </div>

</div>
