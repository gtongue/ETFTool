package ETF;

import java.util.ArrayList;
import java.util.HashMap;

public class ETF {
    public String SYM;
    public float MCAP;
    public HashMap<String, Float> SymbolMap = new HashMap<>();

    public ETF(String SYM, float MCAP){
        this.SYM = SYM;
        this.MCAP = MCAP;
    }

    @Override
    public String toString() {
        String toString = "SYM: " + this.SYM + " MCAP: " + this.MCAP + "\n" + SymbolMap.toString();
        return toString;
    }
}
