import { json, error } from '@sveltejs/kit';
import type { RequestHandler } from './$types';
import LobbyOthelloGameApiService from '$lib/services/othello-game-api/lobby-othello-game-api.service';

export const POST: RequestHandler = async ({ params, request, cookies, fetch }) => {
    const lobbyOthelloGameApiService = new LobbyOthelloGameApiService(fetch, cookies);
    const id = params.id;

    const response = await lobbyOthelloGameApiService.leaveLobby(id);

    if (response.ok) {
        return json(null);
    }

    throw error(response.status, await response.text());    
}