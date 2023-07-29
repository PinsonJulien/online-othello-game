<script lang="ts">
    /*import type { PageData } from './$types';
    
    export let data: PageData;*/
</script>

<svelte:head>
    <title>Login</title>
</svelte:head>

<div>

    <h1>Login</h1>
    
    <form action="?/sign-in" method="POST">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" required>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>
        <button type="submit">Login</button>
    </form>

</div>