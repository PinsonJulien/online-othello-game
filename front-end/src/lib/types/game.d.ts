import type { GamePlayer, GamePlayerLight } from "./game-players"

export interface Game extends GameLight {
  players: GamePlayer[],
  winners: GamePlayer[],
  losers: GamePlayer[],
  grid: Grid,
  currentPlayer: GamePlayer
};

export interface GameLight {
  id: number,
  status: GameStatus,
  createdAt: Date,
  updatedAt: Date,
};

export enum GameStatus {
  IN_PROGRESS,
  FINISHED
};

export interface Grid {
  size: {
    width: number,
    height: number,
  },
  tiles: Tile[],
};

export interface Tile {
  position: Position,
  disk?: Disk,
  playable: boolean,
};

export interface Position {
  row: number,
  column: number,
};

export interface Disk {
  gamePlayer: GamePlayerLight,
};