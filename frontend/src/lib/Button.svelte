<script lang="ts">
  import type { Store } from "../interfaces";
  import ModalEditStore from "./ModalEditStore.svelte";

  export let selected: boolean;
  export let store: Store;
  let open = false;

  async function onDelete() {
    const response = await fetch(
      `http://localhost:8080/api/v1/magasin/${store.code}`,
      {
        method: "DELETE",
      }
    );
    if (response.ok) {
      window.location.reload();
    }
  }
</script>

<ModalEditStore {store} bind:open />
<button
  on:click
  class={`p-2 flex gap-3 text-white rounded-md group hover:brightness-110 hover:ring-2 hover:drop-shadow-lg active:ring-0 active:bg-slate-500 active:brightness-90 active:scale-95 transition-all ${
    selected
      ? "bg-green-600 hover:ring-green-800"
      : "bg-slate-600 hover:ring-slate-800"
  }`}
>
  <slot />
  <div class="hidden group-hover:flex transition-all">
    <button
      class="hover:scale-125 hover:brightness-110 hover:drop-shadow-lg transition-all"
      on:click={() => (open = true)}
    >
      <svg fill="currentColor" width="24" viewBox="0 0 24 24"
        ><path
          d="M6.41421 15.89L16.5563 5.74786L15.1421 4.33365L5 14.4758V15.89H6.41421ZM7.24264 17.89H3V13.6474L14.435 2.21233C14.8256 1.8218 15.4587 1.8218 15.8492 2.21233L18.6777 5.04075C19.0682 5.43128 19.0682 6.06444 18.6777 6.45497L7.24264 17.89ZM3 19.89H21V21.89H3V19.89Z"
        /></svg
      >
    </button>
    <button
      class="hover:scale-125 hover:brightness-110 hover:drop-shadow-lg transition-all"
      on:click={onDelete}
    >
      <svg fill="currentColor" width="24" viewBox="0 0 24 24"
        ><path
          d="M17 6H22V8H20V21C20 21.5523 19.5523 22 19 22H5C4.44772 22 4 21.5523 4 21V8H2V6H7V3C7 2.44772 7.44772 2 8 2H16C16.5523 2 17 2.44772 17 3V6ZM18 8H6V20H18V8ZM9 11H11V17H9V11ZM13 11H15V17H13V11ZM9 4V6H15V4H9Z"
        /></svg
      >
    </button>
  </div>
</button>
