import { json, error } from '@sveltejs/kit';
import type { RequestHandler } from './$types';
import type { Lobby } from '$lib/types/lobby';
import LobbyOthelloGameApiService from '$lib/services/othello-game-api/lobby-othello-game-api.service';

export const POST: RequestHandler = async ({ cookies, fetch }) => {
    const lobbyOthelloGameApiService = new LobbyOthelloGameApiService(fetch, cookies);

    const response = await lobbyOthelloGameApiService.joinClassicMatchmaking();

    if (response.status === 200) {
        const data: Lobby = await response.json();
        return json(data);
    }

    throw error(response.status, await response.text());    
}