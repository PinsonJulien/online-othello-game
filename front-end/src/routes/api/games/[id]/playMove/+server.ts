import type { Game } from '$lib/types/game';
import { error, json } from '@sveltejs/kit';
import type { RequestHandler } from './$types';

export const POST: RequestHandler = async ({ request, params, cookies}) => {
    const id = params.id;
    const token = cookies.get('token');

    const { position } = await request.json();

    const response = await fetch(`http://127.0.0.1:8080/api/v1/games/${id}/playMove`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify({
            "row": position.row,
            "column": position.column,
        }),
    });

    if (response.ok) {
        const game: Game = await response.json();
        return json(game);
    }

    throw error(response.status, await response.text());
};