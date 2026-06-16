package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Transaction {
    private ArrayList<TransactionItem> lineItems = new ArrayList<TransactionItem>();
    private Customer customerTransaction; 
    private LocalDateTime currentDate = LocalDateTime.now();
    private String idTransaction;
    private boolean isDelete;
    private int count = 0;

    // =====================================================================================================
    // Constructor
    public Transaction(ArrayList<TransactionItem> lineItems, Customer customerTransaction, LocalDateTime currentDate,
            String idTransaction, boolean isDelete) {
        this.lineItems = lineItems;
        this.customerTransaction = customerTransaction;
        this.currentDate = currentDate;
        this.idTransaction = idTransaction;
        this.isDelete = isDelete;
    }

    public Transaction(int count) {
        this.currentDate = LocalDateTime.now();
        this.idTransaction = "T" + String.format("%03d", count++); // T001
        this.isDelete = false;
        this.count = count;
    }

    // =====================================================================================================
    





    // =====================================================================================================
    // Getter & setter
    public ArrayList<TransactionItem> getLineItems() {
        return lineItems;
    }
    public void setLineItems(ArrayList<TransactionItem> lineItems) {
        this.lineItems = lineItems;
    }
    public Customer getCustomerTransaction() {
        return customerTransaction;
    }
    public void setCustomerTransaction(Customer customerTransaction) {
        this.customerTransaction = customerTransaction;
    }
    public LocalDateTime getCurrentDate() {
        return currentDate;
    }
    public void setCurretnDate(LocalDateTime currentDate) {
        this.currentDate = currentDate;
    }
    public String getIdTransaction() {
        return idTransaction;
    }
    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }
    public boolean isDelete() {
        return isDelete;
    }
    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

}
