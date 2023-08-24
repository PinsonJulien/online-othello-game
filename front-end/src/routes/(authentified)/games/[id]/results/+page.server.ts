import type { PageServerLoad } from './$types';
import { redirect } from '@sveltejs/kit';
import { type Game, GameStatus } from '$lib/types/game.d';
import GameOthelloGameApiService from '$lib/services/othello-game-api/game-othello-game-api.service';

export const load: PageServerLoad = (async ({params, fetch, cookies, parent }) => {
    const gameService = new GameOthelloGameApiService(fetch, cookies);

    const id = params.id;

    const response = await gameService.getGame(id);

    if (response.status !== 200)
        throw redirect(307, `/games/${id}`);

    const game: Game = await response.json();

    if (game.status !== GameStatus.FINISHED)
        throw redirect(307, `/games/${id}`);

    return {
        game
    };

}) satisfies PageServerLoad;