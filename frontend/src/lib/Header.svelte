<script lang="ts">
  import type { Store } from "../interfaces";
  import Button from "./Button.svelte";
  import ModalAddStore from "./ModalAddStore.svelte";

  export let stores: Store[];
  export let selectedStore: Store;

  let showStoreModal = false;
</script>

<ModalAddStore bind:open={showStoreModal} />
<nav class="flex flex-wrap gap-2 py-2 bg-slate-200 justify-center items-center">
  {#each stores as store}
    <Button
      selected={store.code === selectedStore.code}
      on:click={() => {
        selectedStore = store;
      }}>{store.name}</Button
    >
  {/each}
  <button
    on:click={() => (showStoreModal = true)}
    class=" text-white bg-slate-600 rounded-full p-2 hover:scale-110 hover:brightness-110 active:scale-95 active:brightness-100 transition-all"
  >
    <svg fill="currentColor" width="24" viewBox="0 0 24 24"
      ><path
        d="M4 3H20C20.5523 3 21 3.44772 21 4V20C21 20.5523 20.5523 21 20 21H4C3.44772 21 3 20.5523 3 20V4C3 3.44772 3.44772 3 4 3ZM5 5V19H19V5H5ZM11 11V7H13V11H17V13H13V17H11V13H7V11H11Z"
      /></svg
    >
  </button>
</nav>
