import type { Cookies } from "@sveltejs/kit";
import type { FetchMethod, Index } from "../rest.service";
import OthelloGameApiService from "./othello-game-api.service";

export default class LobbyOthelloGameApiService extends OthelloGameApiService {

  constructor(
    fetch: FetchMethod,
    cookies: Cookies,
  ) {
    super(
      fetch, 
      cookies,
      '/v1/lobbies',
    );
  }

  public async getLobby(id: Index): Promise<Response> {
    return this.get(`/${id}`);
  }

  public async joinClassicMatchmaking(): Promise<Response> {
    return this.post(`/join/classic`);
  }

  public async leaveLobby(id: Index): Promise<Response> {
    return this.post(`/${id}/leave`);
  }

  public async getLobbySSE(id: Index): Promise<Response> {
    return this.getServerSentEvents(`/${id}/sse`);
  }

}
