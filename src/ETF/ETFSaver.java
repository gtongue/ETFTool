package ETF;

import Utils.FileUtils;
import Utils.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ETFSaver {
    final static String ETF_LIST_URL = "https://etfdailynews.com/best-etfs/";
    final static String ETF_INFO_URL = "https://etfdailynews.com/etf/";
    final static String[] ETFS = {"VTI", "VOO", "SPY", "IVV", "VO", "VB", "VNQ", "VUG", "VXF", "VTV", "GLD", "IWD", "IWF", "IJH", "VIG",
            "IWM", "VYM", "VBR", "IJR", "MDY", "DVY", "VBK", "IWB", "XLE", "SDY", "IVW", "VV", "USMV", "IWR", "XLF", "XLK", "DIA", "VOE", "GDX", "XLV", "VGT", "IVE", "RSP"
            , "XLY", "AMLP", "XLP", "IWS", "VOT", "XLU", "XLI", "SCHB", "IWN", "SPLV", "SCHX", "HDV", "IWP", "IWV", "IWO", "VHT", "IJK", "ITOT", "VDE", "IJJ", "GDXJ", "SCHD", "OEF",
            "VDC", "PRF", "RWX", "IYR", "IJS", "ICF", "SCHA", "VFH", "FDN", "AMJ", "RWR", "IJT", "QUAL", "VPU", "SCHG", "IYW", "GUNR", "XLB", "SCHH", "XLRE", "SPHD", "FVD",
            "SCHV", "XBI", "NOBL", "KBE", "SCHM", "RWO", "MGK", "VIS", "MLPI", "KRE", "DON", "FXG", "VCR", "DLN", "MTUM", "XOP", "RPG", "FDL",
            "IYH", "FXD", "VAW", "FNDX", "DES", "IYF", "FXU", "MGV", "SDOG", "VOX", "QDF", "IXJ", "PDP", "IVOO",
            "FXN", "EMLP", "PKW", "IHI", "REM", "DHS", "IYE", "MGC", "GSLC", "VLUE", "ITB", "IGE", "VOOG", "IXC", "FNDA", "SPHQ", "PWV", "PEY", "XHB", "IGM", "IUSG", "ITA",
            "FXH", "IYY", "DTN", "IXN", "FBGX", "PJP", "RYT", "IUSV", "IYG", "OIH", "NANR", "MOO", "TILT", "IYC", "IGV", "IYJ", "SLYG", "FXO", "XT", "HACK", "MOAT", "FBT",
            "GNR", "JKE", "IDU", "FLGE", "IWC", "DGRO", "VIOO", "RPV", "IYT", "XME", "PHO", "KIE", "SPYG", "XMLV", "EZM", "NFRA", "IVOG", "DSI", "XSLV", "JKD", "IWY", "RHS",
            "KXI", "IVOV", "SLYV", "JKG", "IYK", "XLG", "FPX", "FHLC", "FXL", "IHF", "SPHB", "RFG", "IHE", "SLY", "DTD", "XRT", "IYZ", "VOOV"};

    final static String OUTPUT_DIR = "output/ETF_DATA/";

    public static void DownloadETFSList() {
        String HTML = Request.GET(ETF_LIST_URL);
        Document doc = Jsoup.parse(HTML);
        Elements table = doc.select(".table-condensed");
        Elements rows = table.select("tbody tr");
        for (Element row : rows) {
            System.out.println(row.text());
        }
    }

    public static void DownloadAllETFInfo() {
        float counter = 0;

        for(String SYM : ETFS) {
            System.out.println(counter / ETFS.length + "%");
            PrintWriter pw = FileUtils.MakeNewFile(OUTPUT_DIR + SYM + ".csv");
            String HTML = Request.GET(ETF_INFO_URL + SYM);
            Document doc = Jsoup.parse(HTML);

            //Get ETF MCAP
            Elements etfInfoTable = doc.select(".table.fourteen.table-striped td");
            String assetsString = etfInfoTable.get(5).text();
            float assets = Float.parseFloat(assetsString.substring(0, assetsString.length() - 1));
            char multiplier = assetsString.charAt(assetsString.length() - 1);
            if (multiplier == 'M') {
                assets *= 1000000;
            } else {
                assets *= 1000000000;
            }
            pw.write(SYM + "," + assets + "\n");

            //Get Holdings Information
            Elements rows = doc.select("#etfs-that-own tbody tr");
            boolean delete = true;
            for (Element row : rows) {
                Elements data = row.select("td");
                String stockSYM = data.get(0).text();
                String stockName = data.get(1).text();
                Float stockPercent = Float.parseFloat(data.get(2).text().substring(0, data.get(2).text().length() - 1));
                if (!stockSYM.equals("")) {
                    pw.write(stockSYM + "," + stockName + "," + stockPercent);
                    pw.write("\n");
                    delete = false;
                }
            }
            pw.close();
            if(delete)
                FileUtils.Delete(OUTPUT_DIR + SYM + ".csv");
            counter++;
        }
    }
}
