import { redirect } from '@sveltejs/kit';
import type { LayoutServerLoad } from './$types';
import type { Player } from '$lib/types/player';

export const load = (async ({ cookies, url, fetch }) => {
    const token = cookies.get('token');

    if (!token)
        throw redirect(303, `/sign-in?redirectTo=${url.pathname}`);

    // Fetch player data
    const response = await fetch(`http://127.0.0.1:8080/api/v1/auth/me`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
    });

    if (response.status !== 200) {
        throw redirect(303, `/sign-in?redirectTo=${url.pathname}`);
    }

    const player: Player = await response.json();
    
    return {
        player,
    };
        
}) satisfies LayoutServerLoad;