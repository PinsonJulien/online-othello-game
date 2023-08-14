import RestService from "../rest.service";

export default abstract class ApiService extends RestService {

  constructor(
    baseRoute: string,
  ) {
    const url = '/api';

    super(
      fetch,
      `${url}${baseRoute}`,
    );
  }

  /************************************************************/
  // PROTECTED METHODS
  /************************************************************/

  protected getServerSentEvents(
    path: string,
  ): EventSource {
    const url = this.getBaseURL();

    return new EventSource(`${url}${path}`);
  }
}
