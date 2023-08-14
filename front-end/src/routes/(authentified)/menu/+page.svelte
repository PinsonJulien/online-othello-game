<script lang="ts">
    import { beforeNavigate, goto } from '$app/navigation';
    import LobbyApiService from '$lib/services/api/lobby-api.service';
    import type { Lobby } from '$lib/types/lobby';
    import type { PageData } from './$types';
    import MatchmakingDialog from './MatchmakingDialog.svelte';
    
    export let data: PageData;

    const lobbyApiService = new LobbyApiService();

    let lobby: Lobby;
    let showMatchmakingDialog = false;

    beforeNavigate(async ({to, cancel}) => {
        if (lobby && to?.route.id !== '/(authentified)/games/[id]') {
            // if user is in matchmaking, leave matchmaking
            await lobbyApiService.leaveLobby(lobby.id);
        }
    });

    const joinMatchmaking = async () => {
        const response = await lobbyApiService.joinClassicMatchmaking();

        if (response.ok) {
            const data: Lobby = await response.json();

            if (data.game) {
                return goto(`/games/${data.game.id}`);
            }

            // subscribe to sse
            const sse = lobbyApiService.getLobbySSE(data.id);

            sse.onmessage = (event) => {
                const data: Lobby = JSON.parse(event.data);
                
                if (data.game) {
                    sse.close();
                    return goto(`/games/${data.game.id}`);
                }

                // update lobby
                lobby = data;
            }
            
            showModal();
            lobby = data;
        }
    }

    const leaveMatchmaking = async () => {
        const response = await lobbyApiService.leaveLobby(lobby.id);

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