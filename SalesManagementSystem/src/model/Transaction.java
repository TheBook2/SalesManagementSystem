package model;

import java.util.ArrayList;
import service.IDelete;

public class Transaction implements IDelete {
    private ArrayList<TransactionItem> lineItems = new ArrayList<TransactionItem>();
    private String idCustomer; 
    private String createdDate;
    private String exportedDate;
    private String idTransaction;
    private TransactionStatus status;
    private boolean isDeleted;

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

        System.out.println("=====================================================================");
        System.out.printf("%10s|%25s|%15s|%15s|\n", "ID", "Name Product", "Quantity", "Price");
        for (TransactionItem item : lineItems) {
            System.out.println(item.toString());
        }
        System.out.println("=====================================================================");
        System.out.printf("TOTAL:    %-58s|\n", GetTotalAmount());
    }

    public enum TransactionStatus {
        ACTIVE, PENDING, CLOSED, DELETED;
    }


    @Override
    public void softDelete() {
        this.isDeleted = true;
        System.out.println("** Transaction " + idTransaction + " has been deleted.");
    }

    @Override
    public void restore() {
        this.isDeleted = false;
        System.out.println("** Transaction " + idTransaction + " has been restored.");
    }

    @Override
    public boolean isDeleted() {
        return isDeleted;
    }

    // =====================================================================================================
    // Constructor
    public Transaction() {
        this.idCustomer = "";
        this.idTransaction = "";
        this.createdDate = "";
        this.exportedDate = "";
        this.isDeleted = false;
    }
    public Transaction(String idCustomer, String createdDate, String exportedDate, String idTransaction,
            TransactionStatus status, boolean isDeleted) {
        this.idCustomer = idCustomer;
        this.createdDate = createdDate;
        this.exportedDate = exportedDate;
        this.idTransaction = idTransaction;
        this.status = status;
        this.isDeleted = isDeleted;
    }

    public Transaction(String idCustomer, String createdDate, String idTransaction, boolean isDeleted) {
        this.idCustomer = idCustomer;
        this.createdDate = createdDate;
        this.idTransaction = idTransaction;
        this.status = TransactionStatus.ACTIVE;
        this.isDeleted = isDeleted;
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
    public String getExportedDate() {
        return exportedDate;
    }
    public void setExportDate(String exportedDate) {
        this.exportedDate = exportedDate;
    }
    public String getIdTransaction() {
        return idTransaction;
    }
    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
    
    
    // =====================================================================================================
}
