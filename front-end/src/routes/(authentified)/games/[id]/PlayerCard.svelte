<script lang="ts">
  import { player } from "$lib/stores/player";
  import { GamePlayerColor, type GamePlayer } from "$lib/types/game-players.d";
  import DiskComponent from "./DiskComponent.svelte";

  export let username: String;
  export let color: GamePlayerColor;
  export let isCurrentTurnPlayer: boolean;

  $: border = isCurrentTurnPlayer 
    ? 'ring-4 ring-rose-400' 
    : '';

  const cardBackgroundStyle = ((color: GamePlayerColor): string => {
    switch(color) {
      case GamePlayerColor.BLACK:
        return 'bg-neutral-900 text-neutral-100';
      case GamePlayerColor.WHITE:
        return 'bg-neutral-100 text-neutral-900';
    }
  })(color);

</script>

<div class="{cardBackgroundStyle} my-auto flex rounded-xl p-3 min-w-[10em] {border}">
  <div class="m-auto">
    <span
      class="font-bold"
    >
      {username}
    </span>
  </div>
</div>
