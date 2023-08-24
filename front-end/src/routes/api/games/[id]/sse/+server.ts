import { error } from '@sveltejs/kit';
import type { RequestHandler } from './$types';
import ResponseFactory from '$lib/responses/response.factory';
import GameOthelloGameApiService from '$lib/services/othello-game-api/game-othello-game-api.service';

export const GET: RequestHandler = async ({ params, cookies, fetch }) => {
    const gameService = new GameOthelloGameApiService(fetch, cookies);
    const responseFactory = new ResponseFactory();

    const id = params.id;

    const response = await gameService.getGameSSE(id);

    if (response.ok)
        return responseFactory.createServerSentEvent(response);

    throw error(response.status, await response.text());
};