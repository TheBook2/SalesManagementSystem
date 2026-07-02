package model;

import java.util.ArrayList;
import service.IDelete;

public class Transaction implements IDelete {
    private ArrayList<TransactionItem> lineItems = new ArrayList<TransactionItem>();
    private String idCustomer; 
    private String createdDate;
    private String exportDate;
    private String idTransaction;
    private boolean isDelete;

    public void AddItem(TransactionItem newItem) {
        for (TransactionItem existing : lineItems) {
            if (existing.getProductId() == newItem.getProductId()) {
                existing.setQuantity(existing.getQuantity() + newItem.getQuantity());
                System.out.println("** Product already in bill — updated quantity.");
                return;
            }
        }
        lineItems.add(newItem);
        System.out.println("** Added item successfully.");
    }

    public double GetTotalAmount() {
        double total = 0;
        for (TransactionItem item : lineItems) {
            total += item.getLineTotal();
        }
        return total;
    }

     public void PrintLineItems() {
        if (lineItems.isEmpty()) {
            System.out.println("** No items in this transaction.");
            return;
        }
        System.out.printf("%-10s %-25s %-20s %-10s\n", "ID", "Name Product", "Price", "Quantity");
        for (TransactionItem item : lineItems) {
            System.out.println(item.toString());
        }
        System.out.printf("Total: %56s", GetTotalAmount());
    }

    @Override
    public void softDelete() {
        this.isDelete = true;
        System.out.println("** Transaction " + idTransaction + " has been deleted.");
    }

    @Override
    public void restore() {
        this.isDelete = false;
        System.out.println("** Transaction " + idTransaction + " has been restored.");
    }

    @Override
    public boolean isDeleted() {
        return isDelete;
    }

    // =====================================================================================================
    // Constructor
    public Transaction() {
        this.idCustomer = "";
        this.idTransaction = "";
        this.createdDate = "";
        this.exportDate = "";
        this.isDelete = false;
    }
    public Transaction(String idCustomer, String createdDate, String exportDate, String idTransaction,
            boolean isDelete) {
        this.idCustomer = idCustomer;
        this.createdDate = createdDate;
        this.exportDate = exportDate;
        this.idTransaction = idTransaction;
        this.isDelete = isDelete;
    }

    public Transaction(String idCustomer, String createdDate, String idTransaction, boolean isDelete) {
        this.idCustomer = idCustomer;
        this.createdDate = createdDate;
        this.idTransaction = idTransaction;
        this.isDelete = isDelete;
    }
    // =====================================================================================================
    // Getter & setter
    public String getIdCustomer() {
        return idCustomer;
    }
    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }
    public String getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    public String getExportDate() {
        return exportDate;
    }
    public void setExportDate(String exportDate) {
        this.exportDate = exportDate;
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
    
    // =====================================================================================================
}
