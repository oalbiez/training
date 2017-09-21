from pytest import mark
from exchange_rates import google_exchange_rates, yahoo_exchange_rates
from wallet import Wallet
from currency import USD, EUR, Currency


@mark.slowtest
def test_yahoo_exchange_rates_should_be_not_null():
    assert yahoo_exchange_rates()(EUR, USD) > 0


@mark.slowtest
def test_google_exchange_rates_should_be_not_null():
    assert google_exchange_rates()(EUR, USD) > 0
    assert google_exchange_rates()(Currency("xxx", 2), USD) > 0


@mark.slowtest
def test_real_conversion():
    assert Wallet(EUR(10), USD(0.01)).amount(EUR, google_exchange_rates()) > EUR(10)


def test_google_exchange_with_valid_response():
    reply = """<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
    <html>
     <head>
      <title>
       Currency Converter - Google Finance
      </title>
      <meta content="Get real-time stock quotes &amp; charts, financial news, currency conversions, or track your portfolio with Google Finance." name="Description">
       <link href="/finance/s/OVd9g2P4lGg/styles/finance_us.css" rel="stylesheet" type="text/css">
        <link href="/finance/favicon.ico" rel="icon" type="image/vnd.microsoft.icon"/>
       </link>
      </meta>
     </head>
     <body style="margin-left: 6px; min-width: 0px;">
      <div class="g-doc">
       <form action="/finance/converter" method="get" name="f">
        <div class="sfe-break-top">
         <input autocomplete="off" maxlength="12" name="a" size="5" value="1">
         </input>
        </div>
        <div class="sfe-break-top">
         <select name="from" value="EUR">
          <option value="CNY">
           Chinese Yuan (CN¥)
          </option>
          <option selected="" value="EUR">
           Euro (€)
          </option>
          <option value="USD">
           US Dollar ($)
          </option>
         </select>
        </div>
        <div style="padding: 6px 8px">
         to
        </div>
        <div>
         <select name="to" value="USD">
          <option value="CNY">
           Chinese Yuan (CN¥)
          </option>
          <option value="EUR">
           Euro (€)
          </option>
          <option selected="" value="USD">
           US Dollar ($)
          </option>
         </select>
        </div>
        <div id="currency_converter_result">
         1 EUR =
         <span class="bld">
          1.2000 USD
         </span>
         <input type="submit" value="Convert">
         </input>
        </div>
        <input name="meta" type="hidden" value="ei=vqTCWejUEMXQU-HejYgH">
        </input>
       </form>
      </div>
     </body>
    </html>
    """

    assert google_exchange_rates(getter=lambda x,y: reply)(EUR, USD) == 1.2


@mark.skip
def test_google_exchange_with_invalid_response():
    reply = """<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
    <html>
     <head>
      <title>
       Currency Converter - Google Finance
      </title>
      <meta content="Get real-time stock quotes &amp; charts, financial news, currency conversions, or track your portfolio with Google Finance." name="Description">
       <link href="/finance/s/OVd9g2P4lGg/styles/finance_us.css" rel="stylesheet" type="text/css">
        <link href="/finance/favicon.ico" rel="icon" type="image/vnd.microsoft.icon"/>
       </link>
      </meta>
     </head>
     <body style="margin-left: 6px; min-width: 0px;">
      <div class="g-doc">
       <form action="/finance/converter" method="get" name="f">
        <div class="sfe-break-top">
         <input autocomplete="off" maxlength="12" name="a" size="5" value="1">
         </input>
        </div>
        <div class="sfe-break-top">
         <select name="from" value="XXX">
          <option value="CNY">
           Chinese Yuan (CN¥)
          </option>
          <option value="EUR">
           Euro (€)
          </option>
          <option value="USD">
           US Dollar ($)
          </option>
         </select>
        </div>
        <div style="padding: 6px 8px">
         to
        </div>
        <div>
         <select name="to" value="USD">
          <option value="CNY">
           Chinese Yuan (CN¥)
          </option>
          <option value="EUR">
           Euro (€)
          </option>
          <option selected="" value="USD">
           US Dollar ($)
          </option>
         </select>
        </div>
        <div id="currency_converter_result">
         <input type="submit" value="Convert">
         </input>
        </div>
        <input name="meta" type="hidden" value="ei=HAXEWdnoGY2UUPKRndAK">
        </input>
       </form>
      </div>
     </body>
    </html>
    """

    assert google_exchange_rates(getter=lambda x,y: reply)(EUR, USD) == 1.2
