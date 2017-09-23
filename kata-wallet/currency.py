from collections import namedtuple
from functools import total_ordering


class Currency(namedtuple('Currency', 'code precision description')):

    def amount(self, amount):
        return Amount(round(amount, self.precision), self)

    def render(self, amount):
        return self.code + " " + str(amount)

    def __call__(self, amount):
        return self.amount(amount)


@total_ordering
class Amount(object):

    @property
    def value(self):
        return self.__value

    @property
    def currency(self):
        return self.__currency

    def __init__(self, value, currency):
        self.__value = value
        self.__currency = currency

    def is_currency(self, currency):
        return self.__currency == currency

    def change(self, new_currency, exchange_rates):
        if self.is_currency(new_currency):
            return self
        return new_currency(self.__value * exchange_rates(self.__currency, new_currency))

    def __eq__(self, other):
        return self.__value == other.value and self.__currency == other.currency

    def __lt__(self, other):
        return self.__value < other.value and self.__currency == other.currency

    def __add__(self, other):
        if self.is_currency(other.currency):
            return self.__currency(self.__value + other.value)
        raise TypeError("Mismatch currency, expected " + self.__currency.code + " got " + other.currency.code)

    def __repr__(self):
        return self.__currency.render(self.__value)


AED = Currency("AED", 2, "United Arab Emirates dirham")
AFN = Currency("AFN", 2, "Afghan afghani")
ALL = Currency("ALL", 2, "Albanian lek")
AMD = Currency("AMD", 2, "Armenian dram")
ANG = Currency("ANG", 2, "Netherlands Antillean guilder")
AOA = Currency("AOA", 2, "Angolan kwanza")
ARS = Currency("ARS", 2, "Argentine peso")
AUD = Currency("AUD", 2, "Australian dollar")
AWG = Currency("AWG", 2, "Aruban florin")
AZN = Currency("AZN", 2, "Azerbaijani manat")
BAM = Currency("BAM", 2, "Bosnia and Herzegovina convertible mark")
BBD = Currency("BBD", 2, "Barbados dollar")
BDT = Currency("BDT", 2, "Bangladeshi taka")
BGN = Currency("BGN", 2, "Bulgarian lev")
BHD = Currency("BHD", 3, "Bahraini dinar")
BIF = Currency("BIF", 0, "Burundian franc")
BMD = Currency("BMD", 2, "Bermudian dollar")
BND = Currency("BND", 2, "Brunei dollar")
BOB = Currency("BOB", 2, "Boliviano")
BOV = Currency("BOV", 2, "Bolivian Mvdol")
BRL = Currency("BRL", 2, "Brazilian real")
BSD = Currency("BSD", 2, "Bahamian dollar")
BTN = Currency("BTN", 2, "Bhutanese ngultrum")
BWP = Currency("BWP", 2, "Botswana pula")
BYN = Currency("BYN", 2, "Belarusian ruble")
BZD = Currency("BZD", 2, "Belize dollar")
CAD = Currency("CAD", 2, "Canadian dollar")
CDF = Currency("CDF", 2, "Congolese franc")
CHE = Currency("CHE", 2, "WIR Euro (complementary currency)")
CHF = Currency("CHF", 2, "Swiss franc")
CHW = Currency("CHW", 2, "WIR Franc (complementary currency)")
CLF = Currency("CLF", 4, "Unidad de Fomento")
CLP = Currency("CLP", 0, "Chilean peso")
CNY = Currency("CNY", 2, "Chinese yuan")
COP = Currency("COP", 2, "Colombian peso")
COU = Currency("COU", 2, "Unidad de Valor Real (UVR)")
CRC = Currency("CRC", 2, "Costa Rican colon")
CUC = Currency("CUC", 2, "Cuban convertible peso")
CUP = Currency("CUP", 2, "Cuban peso")
CVE = Currency("CVE", 0, "Cape Verde escudo")
CZK = Currency("CZK", 2, "Czech koruna")
DASH = Currency("DASH", 8, "Dash")
DJF = Currency("DJF", 0, "Djiboutian franc")
DKK = Currency("DKK", 2, "Danish krone")
DOP = Currency("DOP", 2, "Dominican peso")
DZD = Currency("DZD", 2, "Algerian dinar")
EGP = Currency("EGP", 2, "Egyptian pound")
ERN = Currency("ERN", 2, "Eritrean nakfa")
ETB = Currency("ETB", 2, "Ethiopian birr")
ETH = Currency("ETH", 2, "Ether")
EUR = Currency("EUR", 2, "Euro")
FJD = Currency("FJD", 2, "Fiji dollar")
FKP = Currency("FKP", 2, "Falkland Islands pound")
GBP = Currency("GBP", 2, "Pound sterling")
GEL = Currency("GEL", 2, "Georgian lari")
GHS = Currency("GHS", 2, "Ghanaian cedi")
GIP = Currency("GIP", 2, "Gibraltar pound")
GMD = Currency("GMD", 2, "Gambian dalasi")
GNF = Currency("GNF", 0, "Guinean franc")
GTQ = Currency("GTQ", 2, "Guatemalan quetzal")
GYD = Currency("GYD", 2, "Guyanese dollar")
HKD = Currency("HKD", 2, "Hong Kong dollar")
HNL = Currency("HNL", 2, "Honduran lempira")
HRK = Currency("HRK", 2, "Croatian kuna")
HTG = Currency("HTG", 2, "Haitian gourde")
HUF = Currency("HUF", 2, "Hungarian forint")
IDR = Currency("IDR", 2, "Indonesian rupiah")
ILS = Currency("ILS", 2, "Israeli new shekel")
INR = Currency("INR", 2, "Indian rupee")
IOT = Currency("IOT", 0, "IOTA")
IQD = Currency("IQD", 3, "Iraqi dinar")
IRR = Currency("IRR", 2, "Iranian rial")
ISK = Currency("ISK", 0, "Icelandic króna")
JMD = Currency("JMD", 2, "Jamaican dollar")
JOD = Currency("JOD", 3, "Jordanian dinar")
JPY = Currency("JPY", 0, "Japanese yen")
KES = Currency("KES", 2, "Kenyan shilling")
KGS = Currency("KGS", 2, "Kyrgyzstani som")
KHR = Currency("KHR", 2, "Cambodian riel")
KMF = Currency("KMF", 0, "Comoro franc")
KPW = Currency("KPW", 2, "North Korean won")
KRW = Currency("KRW", 2, "South Korean won")
KWD = Currency("KWD", 3, "Kuwaiti dinar")
KYD = Currency("KYD", 2, "Cayman Islands dollar")
KZT = Currency("KZT", 2, "Kazakhstani tenge")
LAK = Currency("LAK", 2, "Lao kip")
LBP = Currency("LBP", 2, "Lebanese pound")
LKR = Currency("LKR", 2, "Sri Lankan rupee")
LRD = Currency("LRD", 2, "Liberian dollar")
LSL = Currency("LSL", 2, "Lesotho loti")
LYD = Currency("LYD", 3, "Libyan dinar")
MAD = Currency("MAD", 2, "Moroccan dirham")
MDL = Currency("MDL", 2, "Moldovan leu")
MGA = Currency("MGA", 1, "Malagasy ariary")
MKD = Currency("MKD", 2, "Macedonian denar")
MMK = Currency("MMK", 2, "Myanmar kyat")
MNT = Currency("MNT", 2, "Mongolian tögrög")
MOP = Currency("MOP", 2, "Macanese pataca")
MRO = Currency("MRO", 1, "Mauritanian ouguiya")
MUR = Currency("MUR", 2, "Mauritian rupee")
MVR = Currency("MVR", 2, "Maldivian rufiyaa")
MWK = Currency("MWK", 2, "Malawian kwacha")
MXN = Currency("MXN", 2, "Mexican peso")
MXV = Currency("MXV", 2, "Mexican Unidad de Inversion (UDI)")
MYR = Currency("MYR", 2, "Malaysian ringgit")
MZN = Currency("MZN", 2, "Mozambican metical")
NAD = Currency("NAD", 2, "Namibian dollar")
NGN = Currency("NGN", 2, "Nigerian naira")
NIO = Currency("NIO", 2, "Nicaraguan córdoba")
NOK = Currency("NOK", 2, "Norwegian krone")
NPR = Currency("NPR", 2, "Nepalese rupee")
NZD = Currency("NZD", 2, "New Zealand dollar")
OMR = Currency("OMR", 3, "Omani rial")
PAB = Currency("PAB", 2, "Panamanian balboa")
PEN = Currency("PEN", 2, "Peruvian Sol")
PGK = Currency("PGK", 2, "Papua New Guinean kina")
PHP = Currency("PHP", 2, "Philippine peso")
PKR = Currency("PKR", 2, "Pakistani rupee")
PLN = Currency("PLN", 2, "Polish złoty")
PYG = Currency("PYG", 0, "Paraguayan guaraní")
QAR = Currency("QAR", 2, "Qatari riyal")
RON = Currency("RON", 2, "Romanian leu")
RSD = Currency("RSD", 2, "Serbian dinar")
RUB = Currency("RUB", 2, "Russian ruble")
RWF = Currency("RWF", 0, "Rwandan franc")
SAR = Currency("SAR", 2, "Saudi riyal")
SBD = Currency("SBD", 2, "Solomon Islands dollar")
SCR = Currency("SCR", 2, "Seychelles rupee")
SDG = Currency("SDG", 2, "Sudanese pound")
SEK = Currency("SEK", 2, "Swedish krona/kronor")
SGD = Currency("SGD", 2, "Singapore dollar")
SHP = Currency("SHP", 2, "Saint Helena pound")
SLL = Currency("SLL", 2, "Sierra Leonean leone")
SOS = Currency("SOS", 2, "Somali shilling")
SRD = Currency("SRD", 2, "Surinamese dollar")
SSP = Currency("SSP", 2, "South Sudanese pound")
STD = Currency("STD", 2, "São Tomé and Príncipe dobra")
SVC = Currency("SVC", 2, "Salvadoran colón")
SYP = Currency("SYP", 2, "Syrian pound")
SZL = Currency("SZL", 2, "Swazi lilangeni")
THB = Currency("THB", 2, "Thai baht")
TJS = Currency("TJS", 2, "Tajikistani somoni")
TMT = Currency("TMT", 2, "Turkmenistan manat")
TND = Currency("TND", 3, "Tunisian dinar")
TOP = Currency("TOP", 2, "Tongan paʻanga")
TRY = Currency("TRY", 2, "Turkish lira")
TTD = Currency("TTD", 2, "Trinidad and Tobago dollar")
TWD = Currency("TWD", 2, "New Taiwan dollar")
TZS = Currency("TZS", 2, "Tanzanian shilling")
UAH = Currency("UAH", 2, "Ukrainian hryvnia")
UGX = Currency("UGX", 0, "Ugandan shilling")
USD = Currency("USD", 3, "United States dollar")
USN = Currency("USN", 2, "United States dollar (next day)")
UYI = Currency("UYI", 0, "Uruguay Peso en Unidades Indexadas (URUIURUI)")
UYU = Currency("UYU", 2, "Uruguayan peso")
UZS = Currency("UZS", 2, "Uzbekistan som")
VEF = Currency("VEF", 2, "Venezuelan bolívar")
VND = Currency("VND", 0, "Vietnamese đồng")
VUV = Currency("VUV", 0, "Vanuatu vatu")
WST = Currency("WST", 2, "Samoan tala")
XAF = Currency("XAF", 0, "CFA franc BEAC")
XAG = Currency("XAG", 8, "Silver (one troy ounce)")
XAU = Currency("XAU", 8, "Gold (one troy ounce)")
XBC = Currency("XBC", 8, "Bitcoin Cash")
XBT = Currency("XBT", 8, "Bitcoin Cash")
XCD = Currency("XCD", 2, "East Caribbean dollar")
XLM = Currency("XLM", 8, "Stellar Lumen")
XMR = Currency("XMR", 8, "Monero")
XOF = Currency("XOF", 0, "CFA franc BCEAO")
XPD = Currency("XPD", 8, "Palladium (one troy ounce)")
XPF = Currency("XPF", 0, "CFP franc (franc Pacifique)")
XPT = Currency("XPT", 8, "Platinum (one troy ounce)")
XRP = Currency("XRP", 8, "Ripple")
XSU = Currency("XSU", 8, "SUCRE")
XZC = Currency("XZC", 8, "Zcoin")
YER = Currency("YER", 2, "Yemeni rial")
ZAR = Currency("ZAR", 2, "South African rand")
ZEC = Currency("ZEC", 8, "Zcash")
ZMW = Currency("ZMW", 2, "Zambian kwacha")
ZWL = Currency("ZWL", 2, "Zimbabwean dollar A/10")
