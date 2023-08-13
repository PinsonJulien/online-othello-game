import { redirect } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';
import type { Game } from '$lib/types/game';

export const load = (async ({ fetch, params, cookies }) => {
    const id = params.id;
    const token = cookies.get('token');

    // fetch game data
    const response = await fetch(`http://127.0.0.1:8080/api/v1/games/${id}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
    });

    if (response.status !== 200) {
        throw redirect(307, '/menu');
    }

    const game: Game = await response.json();

    return {
        game,
    };

}) satisfies PageServerLoad;