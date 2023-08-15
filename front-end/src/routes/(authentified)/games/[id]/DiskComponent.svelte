<script lang="ts">
    import { GamePlayerColor } from "$lib/types/game-players.d";

  export let diskColor: GamePlayerColor;

  const determineColorStyle = (diskColor: GamePlayerColor): string  => {
    switch(diskColor) {
      case GamePlayerColor.BLACK:
        return 'bg-green-600';
      case GamePlayerColor.WHITE:
        return 'bg-green-400';
    }
  }

  $: color = determineColorStyle(diskColor);

</script>

<div class="w-full h-full flex items-center justify-center">
  <div class="w-3/4 h-3/4 rounded-full {color}">
  </div>
</div>