<script lang="ts">
    import type { LayoutData } from './$types';
    
    export let data: LayoutData;
</script>

<div class="w-full h-full flex flex-col">
    <slot />
</div>