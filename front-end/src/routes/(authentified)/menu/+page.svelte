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

    const testSSE = async () => {
        
        const eventSource = new EventSource('/api/lobbies/216/sse');

        eventSource.onmessage = (event) => {
            console.log(event);
        }

        /*eventSource.onopen = () => {
            console.log('Connection opened');
        };

        eventSource.onerror = (error) => {
            console.log(error);
        };*/
    }

</script>

<div>

    <button type="submit" on:click={joinMatchmaking}>Matchmaking</button>

    <button on:click={testSSE}>Test sse</button>

    <form action="?/sign-out" method="POST">
        <button type="submit">Sign out</button>
    </form>
</div>