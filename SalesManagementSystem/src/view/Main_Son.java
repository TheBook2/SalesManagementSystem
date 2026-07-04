package view;

import service.CustomerManagement;
import service.SalesManagement;
import service.ProductManagement;
import java.util.Scanner;

public class Main_Son {
    public static void main(String[] args) {
        CustomerManagement cManagement = new CustomerManagement();
        ProductManagement pManagement = new ProductManagement();

        pManagement.AddNewProduct();  
        cManagement.AddNewCustomer();
        SalesManagement test = new SalesManagement(cManagement, pManagement);
        Scanner sc = new Scanner(System.in); 
        int choice;

        do {
            System.out.println("");
            System.out.println("Test Sales Management");
            System.out.println("1. Create New Transaction");
            System.out.println("2. Add/ Update Transaction");
            System.out.println("3. Calculate Total Bill");
            System.out.println("4. Delete Transaction");
            System.out.println("5. View Transaction History");
            System.out.println("6. Search Transaction in Detailed");
            System.out.println("0. Exit");
            System.out.println("Enter your choice>");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: {
                    test.CreateNewTransaction(sc);
                    break;
                }
                case 2: {
                    test.AddItemToTransaction(sc);
                    break;
                }
                case 3: {
                    test.CalculateTotalBillAmount(sc);
                    break;
                }
                case 4: {
                    test.DeleteTransaction(sc);
                    break;
                }
                case 5: {
                    test.ViewTransactionHistory(sc);
                    break;
                }
                case 6: {
                    test.SearchTransactionInDetailed(sc);
                }
                case 0: {
                    System.out.println("Exiting....");                    
                }
                default: System.out.println("In sai roi kia");
            }
        } while (choice != 0);
        pManagement.ViewAllProduct();
        sc.close();
    }
}