from monad import html_soup, find_in_soup, compose, Error, Value, gf_parse, span, google_parse

from hypothesis import given, assume
from hypothesis.strategies import integers, text


def test_html_soup_should_parse_valid_html():
    assert html_soup("<html></html>")


def test_find_in_soup():
    item = compose(html_soup, find_in_soup(id='currency_converter_result'))('<div></h')
    assert isinstance(item, Error)


def test_find_in_soup_1():
    item = compose(html_soup, find_in_soup(id='currency_converter_result'))('<div></h')
    assert isinstance(item, Error)


def test_find_in_soup_2():
    item = compose(html_soup, find_in_soup(id='currency_converter_result'))(VALID_GOOGLE_REPLY)
    assert isinstance(item, Value)


def test_03():
    item = compose(html_soup, find_in_soup(id='currency_converter_result'), span)(VALID_GOOGLE_REPLY)
    assert isinstance(item, Value)


def test_04():
    item = google_parse(VALID_GOOGLE_REPLY)
    assert isinstance(item, Value)


VALID_GOOGLE_REPLY = """
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
 <head>
  <title>
   Currency Converter - Google Finance
  </title>
  <meta
    content="
        Get real-time stock quotes &amp; charts, financial news, currency conversions,
        or track your portfolio with Google Finance."
    name="Description">
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
