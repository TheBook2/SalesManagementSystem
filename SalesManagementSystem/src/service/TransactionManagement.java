package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import model.Transaction;
import model.TransactionItem;
import model.Product;
import model.customer.Customer;
public class TransactionManagement {
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    private CustomerManagement customerManagement;
    private ProductManagement productManagement;

    public TransactionManagement(
            CustomerManagement customerManagement,
            ProductManagement productManagement) {
        this.customerManagement = customerManagement;
        this.productManagement = productManagement;
    }

    // =====================================================================================================

public void CreateNewTransaction() {
    System.out.print("Enter customer phone: ");
    String phone = sc.nextLine();

    int customerIndex =
            customerManagement.FindCustomerIndexbyPhone(phone);

    if (customerIndex == -1) {
        System.out.println("Customer not found!");
        return;
    }

    Customer customer =
            customerManagement.getCustomers()[customerIndex];

    System.out.print("Enter product ID: ");
    int productId = sc.nextInt();
    sc.nextLine();

    Product selectedProduct = null;

    for (Product p : productManagement.getArr()) {
        if (p.getIdProduct() == productId) {
            selectedProduct = p;
            break;
        }
    }

    if (selectedProduct == null) {
        System.out.println("Product not found!");
        return;
    }

    System.out.print("Enter quantity: ");
    int quantity = sc.nextInt();
    sc.nextLine();

    if (selectedProduct.getStockQuantity() < quantity) {
        System.out.println("Not enough stock!");
        return;
    }

    TransactionItem item =
            new TransactionItem(selectedProduct, quantity);

    TransactionItem[] items = new TransactionItem[1];
    items[0] = item;

    Transaction transaction =
            new Transaction(
                    items,
                    LocalDate.now().toString(),
                    customer.getIdCustomer()
            );

    transactions.add(transaction);

    selectedProduct.updateStockProduct(-quantity);

    System.out.println("Transaction created successfully!");
}

    // =====================================================================================================

public void CalculateTotalBill() {
    if (transactions.isEmpty()) {
        System.out.println("No transactions found.");
        return;
    }

    Transaction transaction =
            transactions.get(transactions.size() - 1);

    double total = transaction.calculateTotal();

    System.out.println("Total bill: " + total);
}

    // =====================================================================================================

public void UpdateCancelTransaction() {
    if (transactions.isEmpty()) {
        System.out.println("No transaction available.");
        return;
    }

    System.out.println("1. Cancel last transaction");
    System.out.println("2. Back");
    System.out.print("Choice: ");

    int choice = sc.nextInt();
    sc.nextLine();

    if (choice == 1) {
        transactions.remove(transactions.size() - 1);
        System.out.println("Transaction canceled.");
    }
}

    // =====================================================================================================

    public void ViewTransactionHistory() {
        System.out.println("----------- VIEW TRANSACTION HISTORY -----------");
    }

    // =====================================================================================================
    // Phần getter & setter



}
