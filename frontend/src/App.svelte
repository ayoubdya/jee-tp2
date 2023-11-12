<script lang="ts">
  import type { Store } from "./interfaces";
  import ArticleContainer from "./lib/ArticleContainer.svelte";
  import Header from "./lib/Header.svelte";

  async function getStores(): Promise<Store[]> {
    const response = await fetch("http://localhost:8080/api/v1/magasin");
    const stores = await response.json();
    return stores;
  }

  let selectedStore: Store;
  let stores: Store[];
  getStores().then((data) => {
    selectedStore = data[0];
    stores = data;
  });
</script>

<link
  rel="stylesheet"
  href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap"
/>

<main>
  {#if stores}
    <Header {stores} bind:selectedStore />
    <ArticleContainer {selectedStore} />
  {/if}
</main>
