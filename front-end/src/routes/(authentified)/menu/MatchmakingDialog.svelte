<script lang="ts">
  import type { Lobby } from "$lib/types/lobby";

  export let show: boolean = false;
  export let lobby: Lobby;
  export let leaveButtonClick: () => void;

  let dialog: HTMLDialogElement;  

  const showModal = () => {
    if (dialog)
      dialog.showModal();
  };

  const hideModal = () => {
    if (dialog)
      dialog.close();
  };

  $: if (show) {
    showModal();
  } else {
    hideModal();
  }

</script>

<dialog class="modal" bind:this={dialog} on:cancel={leaveButtonClick}>
  <div class="modal-content">
    <h1>Matchmaking</h1>
    <p>Waiting for other players...</p>
    <button on:click={leaveButtonClick}>Leave</button>
  </div>
</dialog>