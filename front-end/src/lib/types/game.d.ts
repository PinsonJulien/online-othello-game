import type { GamePlayer } from "./game-players"

export interface Game extends GameLight {
  players: GamePlayer[],
  winners: GamePlayer[],
  losers: GamePlayer[],
};

export interface GameLight {
  id: number,
  gridSize: GridSize,
  status: GameStatus,
  createdAt: Date,
  updatedAt: Date
};

export enum GameStatus {
  IN_PROGRESS,
  FINISHED
};

export interface GridSize {
  width: number,
  height: number,
};
