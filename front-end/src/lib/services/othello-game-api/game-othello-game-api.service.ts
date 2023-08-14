import type { Cookies } from "@sveltejs/kit";
import type { FetchMethod, Index } from "../rest.service";
import OthelloGameApiService from "./othello-game-api.service";
import type { Position } from "$lib/types/game";

export default class GameOthelloGameApiService extends OthelloGameApiService {

  constructor(
    fetch: FetchMethod,
    cookies: Cookies,
  ) {
    super(
      fetch, 
      cookies,
      '/v1/games',
    );
  }

  public async getGame(id: Index): Promise<Response> {
    return this.get(`/${id}`);
  }

  public async playMove(id: Index, position: Position): Promise<Response> {
    return this.post(`/${id}/playMove`, {
      "row": position.row,
      "column": position.column,
    });
  }

  public async getGameSSE(id: Index): Promise<Response> {
    return this.getServerSentEvents(`/${id}/sse`);
  }

}