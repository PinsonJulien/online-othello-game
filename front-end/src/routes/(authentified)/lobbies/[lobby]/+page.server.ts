import { redirect } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';

export const load = (async ({ cookies, params, fetch }) => {
    const id = params.lobby;
    const cookieId = cookies.get('lobby');

    if (id !== cookieId) {
        cookies.set('lobby', '', {
            maxAge: 0
        })
        
        throw redirect(303, '/menu')
    }

    // fetch data
    const res = fetch(`http://127.0.0.1:8080/api/v1/lobbies/${id}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })


}) satisfies PageServerLoad;