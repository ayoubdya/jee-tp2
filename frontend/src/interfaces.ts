export interface Store {
  code: number;
  name?: string;
}

export interface Article {
  code?: number;
  name: string;
  prixHT: Number;
  magasin: Store;
}

export interface Fragile extends Article {
  emballage: string;
}
