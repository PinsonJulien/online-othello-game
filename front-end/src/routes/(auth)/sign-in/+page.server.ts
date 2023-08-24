import { redirect, type Actions } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';
import AuthOthelloGameApiService from '$lib/services/othello-game-api/auth-othello-game-api.service';

export const load = (async ({ url }) => {

    // get the redirect url
    const redirectTo = url.searchParams.get('redirectTo');

    return {
        redirectTo
    };
}) satisfies PageServerLoad;

export const actions = {
    "sign-in": async ({ cookies, request, url, fetch }) => {
        const authOthelloGameApiService = new AuthOthelloGameApiService(fetch, cookies);

        const data = await request.formData();
        const username = data.get('username') as string;
        const password = data.get('password') as string;

        const res = await authOthelloGameApiService.signIn(username, password);

        if (res.status === 200) {
            const resData = await res.json();
            const token = resData.accessToken;

            authOthelloGameApiService.setToken(token);
            
            const redirectTo = url.searchParams.get('redirectTo');

            throw redirect(
                303, 
                redirectTo ?? '/menu'
            );
        }
    }
} satisfies Actions;