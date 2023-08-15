import type { PlayerLight } from "./player";
import type { GameLight } from "./game";

export interface GamePlayer extends GamePlayerLight {
  game: GameLight,
  player: PlayerLight,
};

export interface GamePlayerLight {
  id: number,
  score: number,
  color: GamePlayerColor,
};

export enum GamePlayerColor {
  BLACK = 'BLACK', 
  WHITE = 'WHITE',
};