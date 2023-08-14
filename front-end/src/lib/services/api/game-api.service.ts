import type { Position } from "$lib/types/game";
import type { Index } from "../rest.service";
import ApiService from "./api.service";


export default class GameApiService extends ApiService {
  constructor() {
    super('/games');
  }

  public async getGame(id: Index): Promise<Response> {
    return this.get(`/${id}`);
  }

  public async playMove(id: Index, position: Position): Promise<Response> {
    return this.post(`/${id}/playMove`, { position });
  }

  public getGameSSE(id: Index): EventSource {
    return this.getServerSentEvents(`/${id}/sse`);
  }

}