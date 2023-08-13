<script lang="ts">
    import type { LayoutData } from './$types';
    import { player } from '$lib/stores/player';
    
    export let data: LayoutData;
    
    // Set the logged player in the store
    const loggedPlayer = data.player;
    player.set(loggedPlayer);

</script>

<main>
    <slot />
</main>