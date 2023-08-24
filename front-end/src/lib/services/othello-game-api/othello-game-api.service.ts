import type { Cookies } from "@sveltejs/kit";
import RestService, { RequestMethod, type FetchMethod, type RequestHeaders } from "../rest.service";
import { OTHELLO_GAME_API_URL } from "$env/static/private";

export default abstract class OthelloGameApiService extends RestService {

  /************************************************************/
  // PRIVATE PROPERTIES
  /************************************************************/
  private readonly cookies: Cookies;
  private readonly token: string | null = null;

  constructor(
    fetch: FetchMethod,
    cookies: Cookies,
    baseRoute: string,
  ) {
    const url = `${OTHELLO_GAME_API_URL}/api`;

    super(
      fetch,
      `${url}${baseRoute}`,
    );

    this.token = cookies.get('token') ?? null;
    this.cookies = cookies;
  }

  /************************************************************/
  // PROTECTED METHODS
  /************************************************************/

  protected getCookies(): Cookies {
    return this.cookies;
  }

  protected getToken(): string | null {
    return this.token;
  }
  
  protected async request(
    method: RequestMethod,
    path: string,
    body: Object = {},
    headers: RequestHeaders = {},
  ): Promise<Response> {

    // Automatically add the Authorization header to all requests
    headers = {
      'Content-Type': 'application/json',
      ...headers,
    };

    if (this.token) {
      headers.Authorization = `Bearer ${this.token}`;
    }

    return super.request(method, path, body, headers);
  }

  protected async getServerSentEvents(
    path: string,
    headers: RequestHeaders = {},
  ): Promise<Response> {

    headers = {
      ...headers,
      ...{
        'Content-Type': 'text/event-stream',
        'Cache-Control': 'no-cache',
        'Connection': 'keep-alive',
      }
    };

    return super.get(path, headers);
  }
}