export default abstract class RestService {

  /************************************************************/
  // PRIVATE PROPERTIES                                         
  /************************************************************/

  private readonly fetch: FetchMethod;
  private readonly baseURL: string;

  constructor(
    fetch: FetchMethod,
    baseURL: string,
  ) {
    this.fetch = fetch;
    this.baseURL = baseURL;
  }

  /************************************************************/
  // PROTECTED METHODS
  /************************************************************/

  protected getFetch(): FetchMethod {
    return this.fetch;
  }

  protected getBaseURL(): string {
    return this.baseURL;
  }
  
  protected async request(
    method: RequestMethod,
    path: string,
    body: Object = {},
    headers: RequestHeaders = {},
  ): Promise<Response> {
    const init: RequestInit = {
      method,
      headers: {
        'Content-Type': 'application/json',
        ...headers,
      },
    };

    if (method !== RequestMethod.GET)
      init.body = JSON.stringify(body);

    return this.fetch(`${this.baseURL}${path}`, init);
  }

  protected async get(
    path: string,
    headers: RequestHeaders = {},
  ): Promise<Response> {
    return this.request(RequestMethod.GET, path, {}, headers);
  }
  
  protected async post(
    path: string,
    body: Object = {},
    headers: RequestHeaders = {},
  ): Promise<Response> {
    return this.request(RequestMethod.POST, path, body, headers);
  }

}

export type FetchMethod = (input: RequestInfo, init?: RequestInit) => Promise<Response>;

export type RequestHeaders = { [key: string]: string };

export enum RequestMethod {
  GET = 'GET',
  POST = 'POST',
  PUT = 'PUT',
  DELETE = 'DELETE',
  PATCH = 'PATCH',
}

export type Index = number | string;