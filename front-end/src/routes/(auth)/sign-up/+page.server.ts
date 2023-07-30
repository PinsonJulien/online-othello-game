import { redirect, type Actions } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';

export const load = (async () => {
    return {};
}) satisfies PageServerLoad;

export const actions = {
    "sign-up": async ({ cookies, request, fetch }) => {
        const data = await request.formData();
        const username = data.get('username');
        const password = data.get('password');

        const res = await fetch('http://127.0.0.1:8080/api/v1/auth/signup',
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            },
            body: JSON.stringify({
                "username": username,
                "password": password
            })
        });

        if (res.status === 200) {
            const resData = await res.json();
            const token = resData.accessToken;
    
            // set cookie with 24h expiration
            cookies.set('token', token, {
                maxAge: 60 * 60 * 24,
                path: '/', 
                sameSite: 'lax',
                httpOnly: true
            });
    
            throw redirect(
                303, 
                '/menu'
            );
        }
    }
} satisfies Actions;