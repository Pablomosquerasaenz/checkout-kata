package solution;

import java.util.*;

// Map to hold price of individual item
public class itemPrices {
    public static HashMap<String, Double> itemPriceMap = new HashMap<String, Double>();

    static{
        itemPriceMap.put("VOUCHER", 5.0);
        itemPriceMap.put("TSHIRT", 20.0);
        itemPriceMap.put("PANTS", 7.50);
    }
}
