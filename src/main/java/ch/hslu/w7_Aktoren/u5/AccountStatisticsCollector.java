package ch.hslu.w7_Aktoren.u5;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class AccountStatisticsCollector {
    private final Map<BalanceCategory, Integer> stats = new EnumMap<>(BalanceCategory.class);

//    Supplier
    public AccountStatisticsCollector(){
        for (BalanceCategory c : BalanceCategory.values()){
            stats.put(c, 0);
        }
//
    }

    public void classify(){
    }
}
