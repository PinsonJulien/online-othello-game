<script lang="ts">
    import { goto } from '$app/navigation';
    import type { Lobby } from '$lib/types/lobby';
    import type { PageData } from './$types';
    import MatchmakingDialog from './MatchmakingDialog.svelte';
    
    export let data: PageData;

    let lobby: Lobby;
    let showMatchmakingDialog = false;

    const joinMatchmaking = async () => {
        const response = await fetch('/api/lobbies/join/classic', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (response.ok) {
            const data: Lobby = await response.json();

            if (data.game) {
                goto(`/games/${data.game.id}`);
                return;
            }

            // subscribe to sse
            const sse = new EventSource(`/api/lobbies/${data.id}/sse`);

            sse.onmessage = (event) => {
                const data: Lobby = JSON.parse(event.data);
                
                if (data.game) {
                    sse.close();
                    goto(`/games/${data.game.id}`);
                    return;
                }

                // update lobby
                lobby = data;
            }
            
            showModal();
            lobby = data;
        }
    }

    const leaveMatchmaking = async () => {
        const response = await fetch(`/api/lobbies/${lobby.id}/leave`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (response.ok) {
            hideModal();
        }
    }

    const showModal = () => {
        showMatchmakingDialog = true;
    }

    const hideModal = () => {
        showMatchmakingDialog = false;
    }
</script>

<div>

    <button type="submit" on:click={joinMatchmaking}>Matchmaking</button>

    <form action="?/sign-out" method="POST">
        <button type="submit">Sign out</button>
    </form>

    <MatchmakingDialog 
        lobby={lobby}
        show={showMatchmakingDialog}
        leaveButtonClick={leaveMatchmaking}
    />

</div>