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

    const startLobbySSE = (id: number): EventSource => {
        let sse = lobbyApiService.getLobbySSE(id);

        sse.onmessage = (event) => {
            const data: Lobby = JSON.parse(event.data);
            
            if (data.game) {
                sse.close();
                return goto(`/games/${data.game.id}`);
            }

            // update lobby
            lobby = data;
        }

        sse.onerror = (error) => {
            // If the sse timeout, refresh it.
            sse = startLobbySSE(id);
        }

        return sse;
    }

    const joinMatchmaking = async () => {
        const response = await lobbyApiService.joinClassicMatchmaking();

        if (response.ok) {
            const data: Lobby = await response.json();

            if (data.game) {
                return goto(`/games/${data.game.id}`);
            }

            // subscribe to sse
            const sse = startLobbySSE(data.id);
            
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

<svelte:head>
    <title>Menu</title>
</svelte:head>

<div class="h-full w-full">
    <div class="h-full w-full flex justify-center ">
        <div class="w-1/2 sm:w-1/4 lg:w-1/6 flex flex-col rounded-box justify-center gap-6">
            <h1 class="font-sans text-4xl text-center">
                Othello
            </h1>
            <button 
                type="submit" 
                on:click={joinMatchmaking}
                class="btn btn-primary"
            > 
                Join Matchmaking
            </button>

            <form action="?/sign-out" method="POST" class="w-full">
                <button 
                    type="submit"
                    class="btn btn-secondary w-full"
                >
                    Sign out
                </button>
            </form>
        </div>
    </div>


    <MatchmakingDialog 
        lobby={lobby}
        show={showMatchmakingDialog}
        leaveButtonClick={leaveMatchmaking}
    />

</div>