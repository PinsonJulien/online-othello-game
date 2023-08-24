<script lang="ts">
    import type { Snapshot } from './$types';

    let username = '';
    let password = '';

    export const snapshot: Snapshot = {
        capture: () => ({ username, password }),
        restore: (value) => {
            username = value.username;
            password = value.password;
        }
    };
    
</script>

<svelte:head>
    <title>Sign-up</title>
</svelte:head>

<div class="flex flex-col gap-3">
    <h1 class="text-center text-2xl">
        Sign-up
    </h1>

    <form action="?/sign-up" method="POST" class="flex flex-col gap-4">
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
            Sign-up
        </button>
    </form>
</div>