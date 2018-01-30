package DataAnalysis;

import ETF.ETF;
import ETF.ETFLoader;
import Stock.Stock;
import Stock.StockLoader;
import Utils.FileUtils;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ETFAnalysis {
    public static void generateETFFile(){
        ArrayList<Stock> stocks = StockLoader.fetchStocksWithMCAP();
        ArrayList<ETF> etfs = ETFLoader.loadAllEtfs();
        HashMap<String, HashMap<String, Float>> output = new HashMap<>();
        PrintWriter pw = FileUtils.MakeNewFile(FileUtils.ETF_ANALYSIS_LOCATION + "ETF.csv");
        //TODO inefficient here instead store more as hashmaps instead of lists
        stocks.forEach((stock) -> {
            output.put(stock.SYM, new HashMap<>());
            for (ETF etf : etfs) {
                if(etf.SymbolMap.containsKey(stock.SYM)){
                    output.get(stock.SYM).put(etf.SYM, (etf.SymbolMap.get(stock.SYM)/stock.MCAP) * 100);
                }
            }
        });
        output.keySet().forEach((SYM) -> {
            final Float[] sum = {0.0f};
            final String[] csvString = {""};
            output.get(SYM).keySet().forEach((ETFSYM) -> {
                sum[0] += output.get(SYM).get(ETFSYM);
                csvString[0]+= ETFSYM + "," + output.get(SYM).get(ETFSYM) + ",";
            });
            pw.write(SYM+","+sum[0]+","+csvString[0]+"\n");
        });
        pw.close();
    }
}
