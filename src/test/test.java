package test;

import solution.*;

import java.util.HashMap;

public class test {
    public static void main(String[] args){

        HashMap<String, offerRule> itemOfferMap = new HashMap<String, offerRule>();

        itemOfferMap.put("VOUCHER", new offerRule("VOUCHER", 2, 2.5, false));
        itemOfferMap.put("TSHIRT", new offerRule("TSHIRT", 3, 19.0, true));

        pricingRules rules = new pricingRules(itemOfferMap);

        checkoutKata check = new checkoutKata(rules);
        check.scanItem("VOUCHER");
        check.scanItem("TSHIRT");
        check.scanItem("PANTS");

        try {
        check.displayCart();
        } catch (Exception e) {
            e.printStackTrace();
        }
       // System.out.println("Total price:" + check.checkout(check.itemsListMap));
        System.out.println("Total price:" + check.checkout(check.itemsListMap));

        if(check.checkout(check.itemsListMap) == 32.50) System.out.println("Passed");
        else System.out.println("Failed");

        try {
        check.removeItem("PANTS", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        check.scanItem("VOUCHER");
        try {
            check.displayCart();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Total price:" + check.checkout(check.itemsListMap));

        if(check.checkout(check.itemsListMap) == 25.0) System.out.println("Passed");
        else System.out.println("Failed");

        // Test to remove an item where there is no such item on the cart
        try {
            check.removeItem("PANTS", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Clear the cart
        check.emptyCart();

        check.scanItem("TSHIRT");
        check.scanItem("TSHIRT");
        check.scanItem("TSHIRT");
        check.scanItem("VOUCHER");
        check.scanItem("TSHIRT");
        try {
            check.displayCart();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Total price:" + check.checkout(check.itemsListMap));

        if(check.checkout(check.itemsListMap) == 81.0) System.out.println("Passed");
        else System.out.println("Failed");

        // Clear the cart
        check.emptyCart();

        check.scanItem("VOUCHER");
        check.scanItem("TSHIRT");
        check.scanItem("VOUCHER");
        check.scanItem("VOUCHER");
        check.scanItem("PANTS");
        check.scanItem("TSHIRT");
        check.scanItem("TSHIRT");
        try {
            check.displayCart();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Total price:" + check.checkout(check.itemsListMap));

        if(check.checkout(check.itemsListMap) == 74.50) System.out.println("Passed");
        else System.out.println("Failed");
    }
}