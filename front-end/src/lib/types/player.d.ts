import type { GamePlayerLight } from "./game-players";
import type { Lobby } from "./lobby";

export interface Player extends PlayerLight {
  lobbies: Lobby[],
  gamePlayers: GamePlayerLight[],
}

export interface PlayerLight {
  id: number;
  username: string;
  createdAt: Date;
  updatedAt: Date;
}

