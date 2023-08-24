import { error } from '@sveltejs/kit';
import type { RequestHandler } from './$types';
import LobbyOthelloGameApiService from '$lib/services/othello-game-api/lobby-othello-game-api.service';
import ResponseFactory from '$lib/responses/response.factory';

export const GET: RequestHandler = async ({ params, request, cookies, fetch }) => {
    const lobbyOthelloGameApiService = new LobbyOthelloGameApiService(fetch, cookies);
    const responseFactory = new ResponseFactory();
    const id = params.id;

    const response = await lobbyOthelloGameApiService.getLobbySSE(id);

    if (response.ok)
        return responseFactory.createServerSentEvent(response);

    throw error(response.status, await response.text());
};