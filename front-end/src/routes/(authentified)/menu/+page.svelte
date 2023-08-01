<script lang="ts">
    import type { PageData } from './$types';
    
    export let data: PageData;

    const joinMatchmaking = async () => {
        const response = await fetch('/api/lobbies/join/classic', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        const json = await response.json();

        console.log(json);
    }

</script>

<div>

    <button type="submit" on:click={joinMatchmaking}>Matchmaking</button>

    <form action="?/sign-out" method="POST">
        <button type="submit">Sign out</button>
    </form>
</div>