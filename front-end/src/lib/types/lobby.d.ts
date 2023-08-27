import type { PlayerLight } from "./player";
import type { GameLight } from './game';

export interface Lobby extends LobbyLight {
  players: PlayerLight[],
  game?: GameLight
}

export interface LobbyLight {
  id: number,
  maxPlayers: number,
  createdAt: Date, 
}