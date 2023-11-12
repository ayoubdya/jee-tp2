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

  $: console.log("ArticleContainer selectedStore", selectedStore);
</script>

<div class="flex justify-center items-center mt-5 p-2 sm:p-0">
  <ModalAddArticle bind:open={showArticleModal} {selectedStore} />
  <div class="flex flex-col gap-2 w-full sm:w-1/2">
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
    <button
      on:click={() => (showArticleModal = true)}
      class="ms-auto text-white bg-slate-600 rounded-full p-2 hover:scale-110 hover:brightness-110 active:scale-95 active:brightness-100 transition-all"
    >
      <svg fill="currentColor" width="30" viewBox="0 0 24 24"
        ><path
          d="M4 3H20C20.5523 3 21 3.44772 21 4V20C21 20.5523 20.5523 21 20 21H4C3.44772 21 3 20.5523 3 20V4C3 3.44772 3.44772 3 4 3ZM5 5V19H19V5H5ZM11 11V7H13V11H17V13H13V17H11V13H7V11H11Z"
        /></svg
      >
    </button>
  </div>
</div>
