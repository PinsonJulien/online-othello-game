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

<div>

    <h1>Sign-up</h1>
    
    <form action="?/sign-up" method="POST">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" bind:value={username} required>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" bind:value={password} required>
        <button type="submit">Login</button>
    </form>

</div>