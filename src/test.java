import ETF.ETF;
import ETF.ETFLoader;
import Utils.NASDAQMarketCap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class test {
    public static void main(String [] args){
//        ArrayList<ETF> etfs = ETFLoader.loadAllEtfs();
//        System.out.println(ETFLoader.AllSymbols(etfs).size());
        NASDAQMarketCap.downloadMarketCapData();
    }
}
