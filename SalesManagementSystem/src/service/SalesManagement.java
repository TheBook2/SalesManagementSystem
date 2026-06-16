package service;

import java.util.ArrayList;
import java.util.Scanner;

import model.Customer;
import model.Transaction;

public class SalesManagement {
    private ArrayList<Transaction> saleManagement = new ArrayList<Transaction>();
    private int count = 1;

    // =====================================================================================================

    public void CreateNewTransaction(CustomerManagement cManagement, ProductManagement pManagementManagement) {
        boolean cont = false, save = false;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("----------- NEW TRANSACTION -----------");
            Transaction tmp = new Transaction(count);

            System.out.printf("Enter customer's phone number: ");
            String phone = sc.nextLine();

            int index = cManagement.FindCustomerIndexbyPhone(phone);
            if (index != -1) {
                System.out.println("** This phone number is exit. You can create a new transaction.");
            } else {
                System.out.println("** This phone number is NOT exit!");
                System.out.println("** The customer should have a account before");
                return;
            }

            System.out.printf("Save this transaction?[YES: true/ NO: false]");
            save = sc.nextBoolean();
            if (save) {
                saleManagement.add(tmp);
                count++;
                System.out.println("** Save transaction successfully!");
            } else {
                System.out.println("** Cancel this transaction!");
            }

            System.out.println("Do you want to create more?[YES: true/ NO: false]");
            cont = sc.nextBoolean();
        } while (cont);
        sc.close();
    }

    // =====================================================================================================

    public void CalculateTotalBillAmount() {
        System.out.println("----------- CALCULATE TOTAL BILL -----------");
    }

    // =====================================================================================================

    public void DeleteTransaction() {
        System.out.println("----------- UPDATE TRANSACTION -----------");

    }

    // =====================================================================================================

    public void ViewTransactionHistory() {
        System.out.println("----------- VIEW TRANSACTION HISTORY -----------");
    }

    // =====================================================================================================
    // Phần getter & setter

}
