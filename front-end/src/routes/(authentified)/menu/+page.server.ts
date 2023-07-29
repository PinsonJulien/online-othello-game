import type { Actions } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';

export const load = (async () => {
    return {};
}) satisfies PageServerLoad;

export const actions = {
    "sign-out": async ({ cookies, }) => {
        cookies.set('token', '', {
            maxAge: 0,
            path: '/', 
            sameSite: 'lax',
            httpOnly: true
        });

        return {
            status: 303,
            redirect: '/sign-in'
        };
    },
} satisfies Actions;
