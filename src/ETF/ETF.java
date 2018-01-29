package ETF;

import java.util.ArrayList;
import java.util.HashMap;

public class ETF {
    public String SYM;
    public ArrayList<HashMap<String, Float>> SymbolMap = new ArrayList<>();

    public ETF(String SYM){
        this.SYM = SYM;
    }
}
