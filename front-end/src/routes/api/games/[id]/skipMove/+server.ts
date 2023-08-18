import GameOthelloGameApiService from '$lib/services/othello-game-api/game-othello-game-api.service';
import type { Game } from '$lib/types/game';
import { error, json } from '@sveltejs/kit';
import type { RequestHandler } from './$types';

export const POST: RequestHandler = async ({ request, params, cookies, fetch }) => {
    const gameService = new GameOthelloGameApiService(fetch, cookies);
    const id = params.id;

    const response = await gameService.skipMove(id);

    if (response.ok) {
        const game: Game = await response.json();
        return json(game);
    }

    throw error(response.status, await response.text());
};