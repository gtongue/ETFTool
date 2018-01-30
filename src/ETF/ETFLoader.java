package ETF;

import Utils.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashSet;

public class ETFLoader {

    static final String ETF_LOCATION = "output/ETF_DATA/";

    public static ArrayList<ETF> loadAllEtfs(){
        ArrayList<ETF> ETFS = new ArrayList<>();
        File folder = new File(ETF_LOCATION);
        File [] files = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".csv");
            }
        });

        for(File f : files){
            ArrayList<String> fileOutput = FileUtils.readFileToStringList(f.getPath());
            String[] ETFInfo = fileOutput.get(0).split(",");
            ETF etf = new ETF(ETFInfo[0], Float.parseFloat(ETFInfo[1]));
            fileOutput.remove(0);
            fileOutput.forEach((el) -> {
                String[] symbolInfo = el.split(",");
                etf.SymbolMap.put(symbolInfo[0], etf.MCAP * (Float.parseFloat(symbolInfo[2])/100));
            });
            ETFS.add(etf);
            System.out.println(etf);
        }

        return ETFS;
    }

    public static HashSet<String> AllSymbols(ArrayList<ETF> ETFS){
        HashSet<String> symbols = new HashSet<>();
        ETFS.forEach((etf) -> {
            etf.SymbolMap.keySet().forEach((symbol) -> {
                symbols.add(symbol);
            });
        });
        return symbols;
    }

}
