import { redirect } from '@sveltejs/kit';
import type { LayoutServerLoad } from './$types';
import AuthOthelloGameApiService from '$lib/services/othello-game-api/auth-othello-game-api.service';

export const load = (async ({ fetch, cookies, url }) => {
    const authOthelloGameApiService = new AuthOthelloGameApiService(fetch, cookies);

    if (authOthelloGameApiService.isLogged())
        throw redirect(303, `/menu`);

    // return the path of the page
    return {
        path: url.pathname
    };
    
}) satisfies LayoutServerLoad;