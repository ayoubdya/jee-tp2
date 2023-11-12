<script lang="ts">
  import type { Article, Fragile, Store } from "../interfaces";
  import ArticleCard from "./ArticleCard.svelte";
  import ModalAddArticle from "./ModalAddArticle.svelte";

  export let selectedStore: Store;
  let showArticleModal = false;

  async function getArticles(storeId: number): Promise<Fragile[]> {
    const response = await fetch(
      `http://localhost:8080/api/v1/magasin/${storeId}/articles`
    );
    const articles = await response.json();
    console.log(articles);
    return articles;
  }
</script>

<div class="flex justify-center items-center mt-5 p-2 sm:p-0">
  <ModalAddArticle bind:open={showArticleModal} {selectedStore} />
  <div class="flex flex-col w-full sm:w-1/2">
    <table class="w-full text-sm text-center text-gray-500">
      <thead class="text-xs text-gray-700 uppercase bg-gray-50">
        <tr>
          <th class="px-6 py-3">Nom</th>
          <th class="px-6 py-3">PrixHT</th>
          <th class="px-6 py-3">Emballage</th>
          <th class="px-6 py-3">Actions</th>
        </tr>
      </thead>
      <tbody>
        {#if selectedStore}
          {#await getArticles(selectedStore.code)}
            <p>Loading...</p>
          {:then articles}
            {#each articles as article}
              <ArticleCard {article} />
            {/each}
          {:catch error}
            <p>Erreur: {error.message}</p>
          {/await}
        {/if}
      </tbody>
    </table>
    <button
      on:click={() => (showArticleModal = true)}
      class="ms-auto text-white bg-slate-600 rounded-full p-2 hover:scale-110 hover:brightness-110 active:scale-95 active:brightness-100 transition-all"
    >
      <svg fill="currentColor" width="30" viewBox="0 0 24 24"
        ><path d="M11 11V5H13V11H19V13H13V19H11V13H5V11H11Z" /></svg
      >
    </button>
  </div>
</div>
