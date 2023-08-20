<script lang="ts">
    import type { Game } from '$lib/types/game.d';
    import type { PageData } from './$types';
    
    export let data: PageData;

    let game: Game = data.game;

    let winner = game.winners[0];

</script>

<div>
    <div>
        <h1>Game Over !</h1>
    </div>

    <div>
        <h2>Winner</h2>
        <p>{winner.player.username}</p>
    </div>

    <div>
        <h2>Points</h2>
        <p>{winner.score}</p>
    </div>

    <div>
        <a
            href="/menu"
        >
            Back to menu
        </a>
    </div>

</div>


<!-- Show winner name, their points  -->

<!-- Back to menu (quit) -->