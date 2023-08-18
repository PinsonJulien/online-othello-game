import { redirect } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';

export const load = (async () => {
    // redirect to /sign-in
    throw redirect(303, '/sign-in');

}) satisfies PageServerLoad;