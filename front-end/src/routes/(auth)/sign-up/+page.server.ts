import { redirect, type Actions } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';
import AuthOthelloGameApiService from '$lib/services/othello-game-api/auth-othello-game-api.service';

export const load = (async () => {
    return {};
}) satisfies PageServerLoad;

export const actions = {
    "sign-up": async ({ cookies, request, fetch }) => {
        const authOthelloGameApiService = new AuthOthelloGameApiService(fetch, cookies);
        const data = await request.formData();
        const username = data.get('username') as string;
        const password = data.get('password') as string;

        const res = await authOthelloGameApiService.signUp(username, password);

        if (res.status === 200) {
            const resData = await res.json();
            const token = resData.accessToken;

            authOthelloGameApiService.setToken(token);
    
            throw redirect(
                303, 
                '/menu'
            );
        }
    }
} satisfies Actions;