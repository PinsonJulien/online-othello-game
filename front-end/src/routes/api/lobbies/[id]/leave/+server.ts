import { json, error } from '@sveltejs/kit';
import type { RequestHandler } from './$types';

export const POST: RequestHandler = async ({ params, request, cookies, fetch }) => {
    const id = params.id;
    const token = cookies.get('token');

    const response = await fetch(`http://127.0.0.1:8080/api/v1/lobbies/${id}/leave`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
    });

    if (response.status === 200) {
        return json(await response.json());
    }

    throw error(response.status, await response.text());    
}