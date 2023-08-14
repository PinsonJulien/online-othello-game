import type { PageServerLoad } from './$types';
import AuthOthelloGameApiService from '$lib/services/othello-game-api/auth-othello-game-api.service';
import type { Actions } from '@sveltejs/kit';

export const load = (async () => {
    return {};
}) satisfies PageServerLoad;

export const actions = {
    "sign-out": async ({ fetch, cookies, }) => {
        const authOthelloGameApiService = new AuthOthelloGameApiService(fetch, cookies);

        authOthelloGameApiService.signOut();

        return {
            status: 303,
            redirect: '/sign-in'
        };
    }
} satisfies Actions;
