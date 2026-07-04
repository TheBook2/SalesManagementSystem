package model;

public class TransactionItem {
    private int productId; 
    private String productName;
    private int snapshot_quantity;
    private double snapshot_price;

    public double getLineTotal() {
        return snapshot_quantity * snapshot_price;
    }

    @Override
    public String toString() {
        return String.format("%10s|%25s|%15s|%15s|", productId, productName, snapshot_quantity, snapshot_price, getLineTotal());
    }
    
    public TransactionItem() {
    }
    
    public TransactionItem(int productId, String productName, int snapshot_quantity, double snapshot_price) {
        this.productId = productId; 
        this.productName = productName;
        this.snapshot_quantity = snapshot_quantity;
        this.snapshot_price = snapshot_price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return snapshot_quantity;
    }

    public void setQuantity(int snapshot_quantity) {
        this.snapshot_quantity = snapshot_quantity;
    }

    public double getPriceCopy() {
        return snapshot_price;
    }

    public void setPriceCopy(double snapshot_price) {
        this.snapshot_price = snapshot_price;
    }

    
}