package Utils;

import java.io.PrintWriter;

public class NASDAQMarketCap {
    final static String NYSE_URL = "http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=NYSE&render=download";
    final static String NASDAQ_URL = "http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=NASDAQ&render=download";
    final static String AMEX_URL = "http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=AMEX&render=download";

    public static void downloadMarketCapData(){
        System.out.println("Fetching NYSE");
        downloadHelper(NYSE_URL, "NYSE_MCAP.csv");
        System.out.println("Fetching NASDAQ");
        downloadHelper(NASDAQ_URL, "NASDAQ_MCAP.csv");
        System.out.println("Fetching AMEX");
        downloadHelper(AMEX_URL, "AMEX_MCAP.csv");
    }

    private static void downloadHelper(String URL, String Filename){
        String csv = Request.GET(URL);
        String[] lines = csv.split("\n");
        PrintWriter pw = FileUtils.MakeNewFile(FileUtils.MARKET_CAP_DATA_LOCATION + Filename);
        for(int i = 1; i < lines.length; i++){
            String[] rows = lines[i].split("\",\"");
            String SYM = rows[0];
//            String name = rows[1];
//            float lastSale = Float.parseFloat(rows[2]);
            float marketCap = Float.parseFloat(rows[3]);
//            String IPO = rows[5];
//            String sector = rows[6];
//            String industry = rows[7];
            if(marketCap != 0)
                pw.write(SYM.replace("\"", "") + "," + marketCap + "\n");
        }
        pw.close();
    }

}
