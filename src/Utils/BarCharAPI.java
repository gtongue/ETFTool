package Utils;

public class BarCharAPI {
    final static String API_KEY = "22d3dd926821a9b42ff168a760c2da24";
    final static String URL = "https://marketdata.websol.barchart.com/getQuote.json?apikey=" + API_KEY +  "&symbols=AAPL&fields=";
    //TODO maybe end up using this but it doesn't contain market cap data which is what I currently need. Also possibly use alphaadvantage
}
