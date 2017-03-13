import { De.Cweyermann.Btc.Client.SpaPage } from './app.po';

describe('de.cweyermann.btc.client.spa App', () => {
  let page: De.Cweyermann.Btc.Client.SpaPage;

  beforeEach(() => {
    page = new De.Cweyermann.Btc.Client.SpaPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
