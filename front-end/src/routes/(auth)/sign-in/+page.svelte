<script lang="ts">
    import type { PageData } from "./$types";

    export let data: PageData;

    let { redirectTo } = data;

    redirectTo = (redirectTo) ? `&redirectTo=${redirectTo}` : '';
</script>

<svelte:head>
    <title>Sign-in</title>
</svelte:head>

<div class="flex flex-col gap-3">
    <h1 class="text-center text-2xl">
        Sign-in
    </h1>

    <form action="?/sign-in{redirectTo}" method="POST" class="flex flex-col gap-4">
        <div class="form-control w-full">
            <label class="labe" for="username">
              <span class="label-text">Username</span>
            </label>
            <input 
                id="username"
                name="username" 
                required
                type="text" 
                class="input input-bordered"
                autocomplete="username"
            />
        </div>

        <div class="form-control w-full">
            <label class="label" for="password">
              <span class="label-text">Password</span>
            </label>
            <input 
                id="password"
                name="password"
                required 
                type="password"
                class="input input-bordered"
                autocomplete="current-password"
            />
        </div>

        <button 
            type="submit"
            class="btn btn-primary"
        >
            Sign-in
        </button>
    </form>
</div>