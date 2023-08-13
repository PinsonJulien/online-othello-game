<script lang="ts">
    import type { Game, Position, Tile } from '$lib/types/game';
    import { onMount } from 'svelte';
    import type { PageData } from './$types';
    import { player } from '$lib/stores/player';
    import type { Player } from '$lib/types/player';
    
    export let data: PageData;

    let loggedPlayer: Player | null = $player;
    
    let game: Game = data.game

    onMount(() => {
        // subscribe to sse
        const sse = new EventSource(`/api/games/${game.id}/sse`);
        sse.onmessage = (event) => {
            const data: Game = JSON.parse(event.data);
            
            // update game
            game = data;
        }
    })

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
        const response = await fetch(`/api/games/${game.id}/playMove`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ position }),
        });

        if (response.ok) {
            const data: Game = await response.json();
            game = data;
        }
    }

</script>

<div>

    <h1>Game</h1>

    <p>Game id: {game.id}</p>
    <p>Game status: {game.status}</p>

    <p>Logged player: { loggedPlayer?.username } </p>
    <p>Current player: { game.currentPlayer.player.username}</p>

    <p>Players:</p>
    <ul>
        {#each game.players as gamePlayer}
            <li>{gamePlayer.player.username}</li>
        {/each}
    </ul>

    <p>Board:</p>
    <table>
        {#each board as row}
            <tr>
                {#each row as tile}
                    <td>
                        {#if tile.disk}
                            <div>
                                {tile.disk.gamePlayer.color}
                            </div>
                        {/if}
                        {#if tile.playable && isPlayerTurn }
                            <div>
                                <button
                                    on:click={() => playMove(tile.position) }
                                >
                                    X
                                </button>
                            </div>
                        {/if}
                    </td>
                {/each}
            </tr>
        {/each}
    </table>



</div>