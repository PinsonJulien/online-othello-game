import type { Index } from "../rest.service";
import ApiService from "./api.service";

export default class LobbyApiService extends ApiService {

  constructor() {
    super('/lobbies');
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

  public getLobbySSE(id: Index): EventSource {
    return this.getServerSentEvents(`/${id}/sse`);
  }

}