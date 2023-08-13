import type { Player } from "$lib/types/player";
import { writable, type Writable } from "svelte/store";

export const player: Writable<Player | null> = writable(null);