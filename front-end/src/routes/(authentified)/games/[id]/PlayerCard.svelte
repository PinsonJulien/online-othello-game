<script lang="ts">
  import { GamePlayerColor } from "$lib/types/game-players.d";

  export let username: String;
  export let color: GamePlayerColor;
  export let isCurrentTurnPlayer: boolean;

  $: border = isCurrentTurnPlayer 
    ? 'ring-4 ring-accent' 
    : '';

  const cardBackgroundStyle = ((color: GamePlayerColor): string => {
    switch(color) {
      case GamePlayerColor.BLACK:
        return 'bg-neutral-900 text-neutral-100';
      case GamePlayerColor.WHITE:
        return 'bg-neutral-50 text-neutral-900';
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
