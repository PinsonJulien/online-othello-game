import { json, error } from '@sveltejs/kit';
import type { RequestHandler } from './$types';
import type { Lobby } from '$lib/types/lobby';

export const GET: RequestHandler = async ({ params, request, cookies, fetch }) => {
    const id = params.id;
    const token = cookies.get('token');

    const response = await fetch(`http://127.0.0.1:8080/api/v1/lobbies/${id}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
    });

    if (response.status === 200) {
        const lobby: Lobby = await response.json();
        return json(lobby);
    }

    throw error(response.status, await response.text());
}