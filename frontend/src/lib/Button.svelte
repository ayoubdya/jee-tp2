<script lang="ts">
  export let selected: boolean;
</script>

<button
  on:click
  class={`p-2 text-white rounded-md  hover:brightness-110 hover:ring-1 hover:ring-slate-800 active:ring-0 active:bg-slate-500 active:brightness-90 active:scale-95 transition-all ${
    selected ? "bg-green-600" : "bg-slate-600"
  }`}
>
  <slot />
</button>
