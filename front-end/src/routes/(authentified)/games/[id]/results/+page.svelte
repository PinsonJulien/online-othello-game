<script lang="ts">
    import type { Game } from '$lib/types/game.d';
    import type { PageData } from './$types';
    
    export let data: PageData;

    let game: Game = data.game;

    let winner = game.winners[0];

</script>

<div class="w-full h-full flex">
    <div class="m-auto bg-neutral-700 p-3 rounded-md flex flex-col gap-6">
        <h1 class="text-2xl uppercase text-bold">
            Game Over !
        </h1>

        <div>
            <p>Winner</p>
            <p class="text-xl text-center">
                {winner.player.username}
            </p>
        </div>
    
        <div>
            <p>Points</p>
            <p class="text-xl text-center">
                {winner.score}
            </p>
        </div>

        <a
            href="/menu"
            class="btn btn-circle mx-auto"
        >
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6" /></svg>
        </a>
    </div>
</div>
