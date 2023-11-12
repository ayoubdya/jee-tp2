<script lang="ts">
  import type { Article, Fragile } from "../interfaces";

  export let article: Fragile;
  let editing = false;

  async function onDelete() {
    const response = await fetch(
      `http://localhost:8080/api/v1/article/${article.code}`,
      {
        method: "DELETE",
      }
    );
    if (response.ok) {
      window.location.reload();
    }
  }

  async function onEdit() {
    if (!editing) {
      editing = true;
      return;
    }
    const response = await fetch(
      `http://localhost:8080/api/v1/article/${article.code}?name=${article.name}&prixHT=${article.prixHT}&emballage=${article.emballage}`,
      {
        method: "PUT",
      }
    );
    editing = false;
    if (response.ok) {
      window.location.reload();
    }
  }
</script>

<!-- <div class="flex justify-between py-2 px-3 bg-slate-600 rounded-lg text-white"> -->
<tr
  class="bg-white border-b"
  on:keydown={(e) => {
    if (e.key === "Escape") {
      editing = false;
    } else if (e.key === "Enter") {
      onEdit();
    }
  }}
>
  {#if !editing}
    <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">
      <h1>{article.name}</h1>
    </td>
    <td class="px-6 py-4">
      <p>{article.prixHT} MAD</p>
    </td>
    <td class="px-6 py-4">
      {#if article.hasOwnProperty("emballage")}
        <p>{article.emballage}</p>
      {/if}
    </td>
  {:else}
    <td class="px-6 py-4">
      <input
        class="text-black rounded-lg p-1 ring-1 ring-gray-500"
        type="text"
        bind:value={article.name}
      />
    </td>
    <td class="px-6 py-4">
      <input
        class="text-black rounded-lg p-1 ring-1 ring-gray-500"
        type="number"
        bind:value={article.prixHT}
      />
    </td>
    <td class="px-6 py-4">
      {#if article.hasOwnProperty("emballage")}
        <input
          class="text-black rounded-lg p-1 ring-1 ring-gray-500"
          type="text"
          bind:value={article.emballage}
        />
      {/if}
    </td>
  {/if}
  <td>
    <div class="flex justify-center gap-1">
      <button on:click={onEdit}>
        {#if !editing}
          <svg fill="currentColor" width="24" viewBox="0 0 24 24"
            ><path
              d="M6.41421 15.89L16.5563 5.74786L15.1421 4.33365L5 14.4758V15.89H6.41421ZM7.24264 17.89H3V13.6474L14.435 2.21233C14.8256 1.8218 15.4587 1.8218 15.8492 2.21233L18.6777 5.04075C19.0682 5.43128 19.0682 6.06444 18.6777 6.45497L7.24264 17.89ZM3 19.89H21V21.89H3V19.89Z"
            /></svg
          >
        {:else}
          <svg fill="currentColor" width="24" viewBox="0 0 24 24"
            ><path
              d="M1.94631 9.31555C1.42377 9.14137 1.41965 8.86034 1.95706 8.6812L21.0433 2.31913C21.5717 2.14297 21.8748 2.43878 21.7268 2.95706L16.2736 22.0433C16.1226 22.5718 15.8179 22.5901 15.5946 22.0877L12.0002 14.0002L18.0002 6.00017L10.0002 12.0002L1.94631 9.31555Z"
            /></svg
          >
        {/if}
      </button>
      <button on:click={onDelete}>
        <svg fill="currentColor" width="24" viewBox="0 0 24 24"
          ><path
            d="M17 6H22V8H20V21C20 21.5523 19.5523 22 19 22H5C4.44772 22 4 21.5523 4 21V8H2V6H7V3C7 2.44772 7.44772 2 8 2H16C16.5523 2 17 2.44772 17 3V6ZM18 8H6V20H18V8ZM9 11H11V17H9V11ZM13 11H15V17H13V11ZM9 4V6H15V4H9Z"
          /></svg
        >
      </button>
    </div>
  </td>
</tr>

<!-- </div> -->
