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
    <svg fill="currentColor" width="15" viewBox="0 0 24 24"
      ><path d="M11 11V5H13V11H19V13H13V19H11V13H5V11H11Z" /></svg
    >
  </button>
</nav>
