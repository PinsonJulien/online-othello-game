import { redirect } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';
import { GameStatus, type Game } from '$lib/types/game.d';
import GameOthelloGameApiService from '$lib/services/othello-game-api/game-othello-game-api.service';

export const load = (async ({ fetch, params, cookies }) => {
    const gameService = new GameOthelloGameApiService(fetch, cookies);
    const id = params.id;

    const response = await gameService.getGame(id);

    if (response.status !== 200) {
        throw redirect(307, '/menu');
    }

    const game: Game = await response.json();

    // If game is done, redirect to the game result page
    if (game.status === GameStatus.FINISHED)
        throw redirect(307, `/games/${id}/results`);

    return {
        game
    };

}) satisfies PageServerLoad;