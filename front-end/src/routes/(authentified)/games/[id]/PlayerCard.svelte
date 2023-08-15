<script lang="ts">
    import type { GamePlayer, GamePlayerColor } from "$lib/types/game-players.d";
    import DiskComponent from "./DiskComponent.svelte";

  export let username: String;
  export let color: GamePlayerColor;
  export let isCurrentTurnPlayer: boolean;

  $: border = isCurrentTurnPlayer 
    ? 'border-4 border-green-400' 
    : '';

</script>

<!-- player name and Disk -->

<div class="flex flex-col items-center {border} w-fit p-10">
  <div class="">
    <div class="ml-2 text-xl font-bold">
      {username}
    </div>
  </div>

  <div class="w-10 h-10 ml-2">
    <DiskComponent diskColor={color} />
  </div>

</div>