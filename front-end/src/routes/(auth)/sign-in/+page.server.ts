import { redirect, type Actions } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';

export const load = (async () => {
    return {};
}) satisfies PageServerLoad;

export const actions = {
    "sign-in": async ({ cookies, request, url, fetch }) => {
        const data = await request.formData();
        const username = data.get('username');
        const password = data.get('password');

        const res = await fetch('http://127.0.0.1:8080/api/v1/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "username": username,
                "password": password
            })
        });

        if (res.status === 200) {
            const { token } = await res.json();

            // set cookie with 24h expiration
            cookies.set('token', token, {
                maxAge: 60 * 60 * 24,
                path: '/',
                sameSite: 'lax',
                httpOnly: true
            });

            const redirectTo = url.searchParams.get('redirectTo');

            throw redirect(
                303, 
                redirectTo ?? '/menu'
            );
        }
    }
} satisfies Actions;