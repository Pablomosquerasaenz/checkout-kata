package solution;

import java.util.*;

// Map to hold items which have offer on them and the offer details
public class staticPricingRules {
    public static HashMap<String, offerRule> itemOfferMap = new HashMap<String, offerRule>();

    static{
        itemOfferMap.put("VOUCHER", new offerRule("VOUCHER", 2, 2.5, false));
        itemOfferMap.put("TSHIRT", new offerRule("TSHIRT", 3, 19.0, true));

    }


}