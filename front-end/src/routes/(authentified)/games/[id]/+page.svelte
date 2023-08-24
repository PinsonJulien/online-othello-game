<script lang="ts">
    import { GameStatus, type Game, type Position } from '$lib/types/game.d';
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

<svelte:head>
    <title>Game n°{game.id}</title>
</svelte:head>

<div class="drawer lg:drawer-open">
    <input type="checkbox" id="drawer-toggle" class="drawer-toggle" />
    <div class="drawer-content">
        <div class="navbar bg-inherit p-5">
            <div class="navbar-start">
                <label for="drawer-toggle" class="btn btn-square btn-ghost lg:hidden">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-6 h-6 stroke-current"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16"></path></svg>
                </label>
            </div>

            <div class="navbar-center">
                <button
                    class="btn btn-primary m-auto {isTurnSkippable && isPlayerTurn ? '' : 'invisible'}"
                    on:click={skipMove}
                >
                    Pass turn
                </button>
            </div>

            <div class="navbar-end">
            </div>
        </div>

        <div class="p-5 lg:w-3/5 max-w-[600px] mx-auto">
            <div class="col-span-5 w-full h-full mx-auto">
                <div class="grid rainbow-background p-2 gap-1 grid-cols-[repeat(8,1fr)] rounded-lg">
                    {#each tiles as tile}
                        <div class="aspect-square bg-slate-200">
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
        </div>
    </div>

    <div class="drawer-side">
        <label for="drawer-toggle" class="drawer-overlay"></label>
        <div class="flex flex-col w-full gap-5 p-4">
            <h2 class="text-center text-2xl text-neutral-100 font-bold font-sans">
                Players
            </h2>
 
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
    </div>
</div>

<style>

.rainbow-background {
    background: repeating-linear-gradient(45deg,red 0%, yellow 7.14%, rgb(0,255,0) 14.28%, rgb(0,255,255) 21.42%, cyan 28.56%, blue 35.7%, magenta 42.84%, red 50%);
    background-size:600vw 600vw;
    animation: slide 10s infinite linear forwards;
}

@keyframes slide{
    0% {
        background-position-x: 0%;
    }

    100% {
        background-position-x: 600vw;
    }
}

</style>