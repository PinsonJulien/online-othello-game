import { error } from '@sveltejs/kit';
import type { RequestHandler } from './$types';

export const GET: RequestHandler = async ({ params, cookies, fetch }) => {
    
    const token = cookies.get('token');
    const response = await fetch(`http://127.0.0.1:8080/api/v1/games/${params.id}/sse`, {
        method: 'GET',
        headers: {
            'Content-Type': 'text/event-stream',
            'Authorization': `Bearer ${token}`,
        },
    });

    if (response.ok) {
        return new Response(response.body, {
            headers: {
                'Content-Type': 'text/event-stream',
                'Cache-Control': 'no-cache',
                'Connection': 'keep-alive',
            },
        });
    }

    throw error(response.status, await response.text());
};