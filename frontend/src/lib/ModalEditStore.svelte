<script lang="ts">
  import type { Store } from "../interfaces";

  let dialog: HTMLDialogElement;
  export let open: boolean;

  $: if (open && dialog) {
    dialog.showModal();
  }

  export let store: Store;

  // : { name: string } = {
  //   name: "",
  // };

  async function onEdit() {
    const res = await fetch(
      `http://localhost:8080/api/v1/magasin/${store.code}?name=${store.name}`,
      {
        method: "PUT",
      }
    );
    if (res.ok) {
      window.location.reload();
    }
    dialog.close();
  }
</script>

<!-- svelte-ignore a11y-no-noninteractive-element-interactions -->
<dialog
  bind:this={dialog}
  on:close={() => {
    open = false;
    window.location.reload();
  }}
  on:keydown={(e) => {
    if (e.key === "escape") dialog.close();
    else if (e.key === "Enter") onEdit();
  }}
  class="p-4 rounded-lg drop-shadow-md bg-slate-200"
>
  <div class="flex flex-col gap-2">
    <label for="name">Nom de magasin</label>
    <input
      class="rounded-lg px-2 py-1"
      type="text"
      id="name"
      bind:value={store.name}
    />
    <button
      on:click={onEdit}
      class="rounded-lg bg-green-600 py-1 px-2 text-white hover:brightness-110 hover:drop-shadow-lg active:scale-90 transition-all"
      >Add</button
    >
    <button on:click={() => dialog.close()} class="absolute top-1 right-1">
      <svg width={24} viewBox="0 0 24 24"
        ><path
          d="M12 22C6.47715 22 2 17.5228 2 12C2 6.47715 6.47715 2 12 2C17.5228 2 22 6.47715 22 12C22 17.5228 17.5228 22 12 22ZM12 10.5858L9.17157 7.75736L7.75736 9.17157L10.5858 12L7.75736 14.8284L9.17157 16.2426L12 13.4142L14.8284 16.2426L16.2426 14.8284L13.4142 12L16.2426 9.17157L14.8284 7.75736L12 10.5858Z"
        /></svg
      ></button
    >
  </div>
</dialog>
