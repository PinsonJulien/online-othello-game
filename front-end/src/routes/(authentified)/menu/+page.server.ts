import { redirect, type Actions } from '@sveltejs/kit';
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
    "matchmaking": async ({ cookies, fetch }) => {
        const res = await fetch("http://127.0.0.1:8080/api/v1/lobbies/join/classic", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (res.status === 200) {
            const resData = await res.json();
            const id = resData.id;

            cookies.set('lobby', id)

            throw redirect(
                303,
                `/lobbies/${id}`
            )
        }
    }
} satisfies Actions;
