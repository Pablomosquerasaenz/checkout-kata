package solution;

import java.util.Iterator;
import java.util.Set;
import java.util.HashMap;

public class checkoutKata{
    public static HashMap<String, Integer> itemsListMap = new HashMap<String, Integer>();
    public static pricingRules rules;

    public checkoutKata(pricingRules Rules){
        this.rules = Rules;
    }
    // Method to add item
    public static void scanItem(String item){
        // If the item does not exist in the cart then add it
        if(!itemsListMap.containsKey(item))
            itemsListMap.put(item, 1);
        else
        {
            itemsListMap.put(item, 1 + itemsListMap.get(item));
        }
    }

    // Method to remove items along with their quantities
    public static void removeItem(String item, Integer quantity) throws Exception{
        // If the item is not in the cart then throw exception
        if(!itemsListMap.containsKey(item))
            throw new NoSuchItemInCartException();

        // If the item's quantity to be removed is equal to the existing quantity
        // of the item then remove the item from the cart or else update the quantity
        // of the item in the cart. If the quantity to be removed is more than what's
        // existing in the cart then throw exception as well.
        if(itemsListMap.get(item).equals(quantity))
            itemsListMap.remove(item);
        else if(itemsListMap.get(item) > quantity)
            itemsListMap.put(item, itemsListMap.get(item) - quantity);
        else
            throw new NotEnoughItemsToRemoveException();
    }

    public static void displayCart() throws Exception
    {
        if(itemsListMap.isEmpty())
            throw new EmptyCartException();

        Set<String> allItemsInCart = itemsListMap.keySet();
        for(Iterator<String> i = allItemsInCart.iterator(); i.hasNext();){
            String item = i.next();
            Integer quantity = itemsListMap.get(item);
            System.out.println("Item: "+ item + " of Quantity: " + quantity);
        }
    }

    public static void emptyCart(){
        itemsListMap.clear();
    }


    public double checkout(HashMap<String, Integer> itemsListMap){
        Double total = 0.0;

        // Iterate through the cart and calculate the total value
        Set<String> keys = itemsListMap.keySet();
        for (Iterator<String> i = keys.iterator(); i.hasNext();) {
            String item = i.next();
            Integer quantity = itemsListMap.get(item);

            // If there is an offer on this item then process the price accordingly
            if(rules.itemOfferMap.containsKey(item)){
                // I assume one pricing offer per item. If several offers could be possible, then I should remodel
                offerRule thisOffer = (offerRule) rules.itemOfferMap.get(item);
                // If the quantity of the item in the cart is more than offer quantity
                // then process the items and their price accordingly
                if(thisOffer.quantity < quantity)
                {
                    //If the offer has the acumulate flag, then apply the price offer to all items
                    if(thisOffer.acumulate) {
                        total += quantity * thisOffer.price;
                    }
                    //If not, the offer only apply to groups of such quantity (for example 2x1 offer)
                    else {
                        int withoutOffer = (quantity % thisOffer.quantity);
                        int withOffer = (quantity - withoutOffer);

                        total += withoutOffer*itemPrices.itemPriceMap.get(item) + (withOffer * thisOffer.price);
                    }
                }
                else if(thisOffer.quantity.equals(quantity))
                {
                    total += quantity * thisOffer.price;
                }
                else
                {
                    total += (quantity)* itemPrices.itemPriceMap.get(item);
                }
            }
            else // If there is no offer we use normal price
            {
                total += (quantity)* itemPrices.itemPriceMap.get(item);
            }
        }

        return total;
    }
}

class NotEnoughItemsToRemoveException extends RuntimeException {}
class NoSuchItemInCartException extends RuntimeException {}
class EmptyCartException extends RuntimeException {}