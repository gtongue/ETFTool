package Stock;

import Utils.FileUtils;

import java.util.ArrayList;

public class StockLoader {
    public static ArrayList<Stock> fetchStocksWithMCAP(){
        ArrayList<Stock> stocks = new ArrayList<>();
        String[] MCAPFiles = {
                FileUtils.MARKET_CAP_DATA_LOCATION+"NYSE_MCAP.csv",
                FileUtils.MARKET_CAP_DATA_LOCATION+"AMEX_MCAP.csv",
                FileUtils.MARKET_CAP_DATA_LOCATION+"NASDAQ_MCAP.csv"
        };
        for(String filename : MCAPFiles){
            ArrayList<String> fileData = FileUtils.readFileToStringList(filename);
            fileData.forEach((el) -> {
                String[] data = el.split(",");
                Stock stock = new Stock(data[0], Float.parseFloat(data[1]));
                stocks.add(stock);
            });
        }
        System.out.println(stocks);
        System.out.println(stocks.size());
        return stocks;
    }
}
