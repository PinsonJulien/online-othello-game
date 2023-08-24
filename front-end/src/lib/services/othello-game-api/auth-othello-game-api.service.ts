import type { Cookies } from "@sveltejs/kit";
import type { FetchMethod } from "../rest.service";
import OthelloGameApiService from "./othello-game-api.service";

export default class AuthOthelloGameApiService extends OthelloGameApiService {

  private readonly tokenCookieName = 'token';

  constructor(
    fetch: FetchMethod,
    cookies: Cookies,
  ) {
    super(fetch, cookies, '/v1/auth');
  }

  public async getMe(): Promise<Response> {
    return this.get(`/me`);
  }

  public async signIn(username: string, password: string): Promise<Response> {
    return this.post(`/login`, { 
      'username': username, 
      'password': password 
    });
  }

  public async signUp(username: string, password: string): Promise<Response> {
    return this.post(`/signup`, { 
      'username': username, 
      'password': password 
    });
  }

  public signOut(): void {
    const cookies = this.getCookies();

    // remove cookie
    cookies.set(this.tokenCookieName, '', {
        maxAge: 0,
        path: '/', 
        sameSite: 'lax',
        httpOnly: true
    });
  }

  public setToken(token: string): void {
    const cookies = this.getCookies();

    // set cookie with 24h expiration
    cookies.set(this.tokenCookieName, token, {
      maxAge: 60 * 60 * 24,
      path: '/',
      sameSite: 'lax',
      httpOnly: true
    });
  }

  public isLogged(): boolean {
    const cookies = this.getCookies();

    return cookies.get(this.tokenCookieName) !== undefined;
  }

}