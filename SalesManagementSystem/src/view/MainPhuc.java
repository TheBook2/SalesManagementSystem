package view;

import java.util.Scanner;
import service.CustomerManagement;

public class MainPhuc {
    public static void main(String[] args) {
        int customerChoice;
        Scanner sc = new Scanner(System.in);
        CustomerManagement customerManagement = new CustomerManagement();

        do {
            System.out.println("======================================");
            System.out.format("%27s", "Customer Management\n");
            System.out.println("======================================");
            System.out.println("   1. Add New Customer");
            System.out.println("   2. Update Customer Information");
            System.out.println("   3. Remove Customer");
            System.out.println("   4. View All Customer");
            System.out.println("   0. Back");
            System.out.print("Enter your choice> ");

            customerChoice = sc.nextInt();
            sc.nextLine();

            switch (customerChoice) {
                case 1: {
                    customerManagement.AddNewCustomer();
                    break;
                }

                case 2: {
                    customerManagement.UpdateCustomerInfor();
                    break;
                }

                case 3: {
                    customerManagement.RemoveCustomer();
                    break;
                }

                case 4: {
                    customerManagement.ViewAllCustomer();
                    break;
                }

                case 0: {
                    System.out.println("Exiting program...");
                    break;
                }

                default: {
                    System.out.println("Invalid choice!");
                    break;
                }
            }

        } while (customerChoice != 0);

        sc.close();
    }
}