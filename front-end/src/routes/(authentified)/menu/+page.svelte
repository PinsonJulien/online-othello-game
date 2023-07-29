<script lang="ts">
    import type { PageData } from './$types';
    
    export let data: PageData;
</script>

<div>
    <form action="?/sign-out" method="POST">
        <button type="submit">Sign out</button>
    </form>
</div>