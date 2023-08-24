<script lang="ts">
    import type { LayoutData } from './$types';
    
    export let data: LayoutData;

    const links = [
        {
            'href': '/sign-up',
            'name': 'Sign-up'
        },
        {
            "href": '/sign-in',
            'name': 'Sign-in'
        }
    ];

    $: activeTab = data.path;

    $: tabs = links.map((link) => {
        return {
            ...link,
            'isActive': activeTab === link.href
        };
    });

</script>

<div class="w-full h-full flex flex-col">
    <div class="m-auto flex flex-col gap-4 bg-neutral p-3 rounded-md">
        <div class="tabs tabs-boxed mx-auto justify-center p-3">
            {#each tabs as tab}
                <a 
                    class="tab {tab.isActive ? 'tab-active' : ''}"
                    href={tab.href}
                >
                    {tab.name}
                </a>
            {/each}
        </div>
    
        <div class="">
            <slot />
        </div>
    </div>

</div>
