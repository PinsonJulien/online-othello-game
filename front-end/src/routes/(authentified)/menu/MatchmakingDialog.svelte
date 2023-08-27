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

<dialog class="modal p-3" bind:this={dialog} on:cancel={leaveButtonClick}>
  <div class="modal-content bg-neutral rounded-2xl p-4">
    <div class="flex flex-col gap-5">
      <p class="text-2xl text-center">
        Waiting for an opponent
      </p>
      <span class="loading loading-spinner loading-lg mx-auto"></span>

      <button 
        on:click={leaveButtonClick}
        class="btn btn-primary"
      >
        Leave matchmaking
      </button>
    </div>
  </div>
</dialog>