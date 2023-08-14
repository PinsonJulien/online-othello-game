import { json, error } from '@sveltejs/kit';
import type { RequestHandler } from './$types';
import type { Lobby } from '$lib/types/lobby';
import LobbyOthelloGameApiService from '$lib/services/othello-game-api/lobby-othello-game-api.service';

export const GET: RequestHandler = async ({ params, request, cookies, fetch }) => {
    const lobbyOthelloGameApiService = new LobbyOthelloGameApiService(fetch, cookies);
    const id = params.id;

    const response = await lobbyOthelloGameApiService.getLobby(id);

    if (response.status === 200) {
        const lobby: Lobby = await response.json();
        return json(lobby);
    }

    throw error(response.status, await response.text());
}