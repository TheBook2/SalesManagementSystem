package model;

public class Transaction {
    private TransactionItem[] lineItems;
    private String date;
    private int customerId;

    public Transaction() {
    }

    public Transaction(TransactionItem[] lineItems,
                       String date,
                       int customerId) {
        this.lineItems = lineItems;
        this.date = date;
        this.customerId = customerId;
    }

    public TransactionItem[] getLineItems() {
        return lineItems;
    }

    public void setLineItems(TransactionItem[] lineItems) {
        this.lineItems = lineItems;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double calculateTotal() {
        double total = 0;

        for (TransactionItem item : lineItems) {
            if (item != null) {
                total += item.getSubTotal();
            }
        }

        return total;
    }
}
