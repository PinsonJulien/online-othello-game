import { redirect } from '@sveltejs/kit';
import type { LayoutServerLoad } from './$types';

export const load = (async ({ cookies, url }) => {
    const token = cookies.get('token');

    if (!token)
        throw redirect(303, `/sign-in?redirectTo=${url.pathname}`);
        
}) satisfies LayoutServerLoad;