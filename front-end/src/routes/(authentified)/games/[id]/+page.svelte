<script lang="ts">
    import type { Game, Position, Tile } from '$lib/types/game';
    import { onMount } from 'svelte';
    import type { PageData } from './$types';
    import { player } from '$lib/stores/player';
    import type { Player } from '$lib/types/player';
    import GameApiService from '$lib/services/api/game-api.service';
    import DiskComponent from './DiskComponent.svelte';
    import ValidMove from './ValidMove.svelte';
    import PlayerCard from './PlayerCard.svelte';

    export let data: PageData;

    const gameApiService = new GameApiService();

    let loggedPlayer: Player | null = $player;
    
    let game: Game = data.game

    onMount(() => {
        // subscribe to sse
        const sse = gameApiService.getGameSSE(game.id);
        sse.onmessage = (event) => {
            const data: Game = JSON.parse(event.data);
            
            // update game
            game = data;
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

    // create grid based on board width and height
    $: board = (() => {
        const board: Array<Array<Tile>> = [];

        for (let i = 0; i < boardHeight; i++) {
            const row: Array<Tile> = [];

            for (let j = 0; j < boardWidth; j++) {
                row.push(tiles[i * boardWidth + j]);
            }

            board.push(row);
        }

        return board;
    })();
    
    const playMove = async (position: Position) => {
        const response = await gameApiService.playMove(game.id, position);

        if (response.ok) {
            const data: Game = await response.json();
            game = data;
        }
    }

</script>

<div>
    <div class="flex">
        <PlayerCard 
            username={loggedPlayerProps.username}
            color={loggedPlayerProps.color}
            isCurrentTurnPlayer={isPlayerTurn}
        />

        <PlayerCard 
            username={opponentPlayerProps.username}
            color={opponentPlayerProps.color}
            isCurrentTurnPlayer={!isPlayerTurn}
        />
    </div>


    <p>Board:</p>

    <div class="grid grid-cols-{boardWidth} gap-1 bg-green-400">
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