import { redirect } from '@sveltejs/kit';
import type { LayoutServerLoad } from './$types';

export const load = (async ({ cookies }) => {

    const token = cookies.get('token');

    if (token)
        throw redirect(303, `/menu`);

}) satisfies LayoutServerLoad;