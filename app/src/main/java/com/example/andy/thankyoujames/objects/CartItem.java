public class CartItem {
    private int finalFoodID;
    private int quantity;
    private double price;

    public CartItem(int finalFoodID, int quantity, double price){
        this.quantity = quantity;
        this.finalFoodID = finalFoodID;
        this.price = price;
    }

    public int getQuantity(){
        return quantity;
    }

    


}
