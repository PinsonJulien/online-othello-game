<script lang="ts">
    import type { LayoutData } from './$types';
    
    export let data: LayoutData;
</script>

<div class="flex flex-col ">
    <div class="tabs tabs-boxed">
        <a class="tab" href="/sign-up">
            Sign-up
        </a>
        <a class="tab" href="/sign-in">
            Sign-in
        </a>
    </div>

    <div>
        <slot />
    </div>
</div>
