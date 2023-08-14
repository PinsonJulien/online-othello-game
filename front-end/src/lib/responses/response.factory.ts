export default class ResponseFactory {

  public createServerSentEvent(
    response: Response,
  ): Response {
    return new Response(response.body, {
      headers: {
        'Content-Type': 'text/event-stream',
        'Cache-Control': 'no-cache',
        'Connection': 'keep-alive',
      },
    });
  }


}