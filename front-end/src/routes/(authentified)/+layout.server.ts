import { redirect } from '@sveltejs/kit';
import type { LayoutServerLoad } from './$types';
import type { Player } from '$lib/types/player';
import AuthOthelloGameApiService from '$lib/services/othello-game-api/auth-othello-game-api.service';

export const load = (async ({ cookies, url, fetch }) => {
    const authOthelloGameApiService = new AuthOthelloGameApiService(fetch, cookies);

    if (!authOthelloGameApiService.isLogged())
        throw redirect(303, `/sign-in?redirectTo=${url.pathname}`);

    // Fetch player data
    const response = await authOthelloGameApiService.getMe();

    if (response.status !== 200)
        throw redirect(303, `/sign-in?redirectTo=${url.pathname}`);

    const player: Player = await response.json();
    
    return {
        player
    };
        
}) satisfies LayoutServerLoad;