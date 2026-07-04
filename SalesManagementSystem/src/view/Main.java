package view;

import java.util.Scanner;

import service.CustomerManagement;
import service.ProductManagement;

/*  
 *  ==========================================
 *           SALES MANAGEMENT SYSTEMT         
 *  ==========================================
 *  Authors:  
 *       - Nguyễn Hoàng Duy 
 *       - Hoàng Công Sơn  
 *       - Nguyễn Hữu Phúc
 * 
*/

public class Main {
    public static void main(String[] args) throws Exception {
        int menuChoice; // biến điều kiển luồng menu
        Scanner sc = new Scanner(System.in);
        CustomerManagement customerManagement = new CustomerManagement();
        ProductManagement productManagement = new ProductManagement();

        // Menu Interface
        do {
            System.out.println("======================================");
            System.out.format("%31s", "SALES MANAGEMENT SYSTEM\n");
            System.out.println("======================================");
            System.out.println("  1. Manage Products");
            System.out.println("  2. Manage Customers");
            System.out.println("  3. Manage Sales Transactions");
            System.out.println("  4. Reports");
            System.out.println("  0. Close Program");
            System.out.print("Enter your choice> ");
            menuChoice = sc.nextInt();
            sc.nextLine();

            // Choice Options
            switch (menuChoice) {
                case 1: { // Product Management
                    int productChoice = -1; // biến điều khiển luồng product management

                    do {
                        System.out.println("======================================");
                        System.out.format("%27s", "Inventory Management\n");
                        System.out.println("======================================");
                        System.out.println("   1. Add New Product");
                        System.out.println("   2. Update Customer Information");
                        System.out.println("   3. Remove Customer");
                        System.out.println("   4. View All Customer");
                        System.out.println("   0. Back");
                        System.out.printf("Enter your choice> ");
                        productChoice = sc.nextInt();
                        sc.nextLine();

                        switch (productChoice) {
                            case 1: {
                                productManagement.addNewProduct();
                                break;
                            }
                            case 2: {
                                break;
                            }
                            case 3: {
                                break;
                            }
                            case 4: {
                                productManagement.viewAllProduct();
                                break;
                            }
                            case 0: {
                                System.out.println("Backing...");
                            }
                            default:
                                break;
                        }
                    } while (productChoice != 0);
                    break;
                }
                case 2: { // Customer Mangement
                    int customerChoice = -1; // biến điều khiển luồng customer management

                    do {
                        System.out.println("======================================");
                        System.out.format("%27s", "Customer Management\n");
                        System.out.println("======================================");
                        System.out.println("   1. Add New Customer");
                        System.out.println("   2. Update Customer Information");
                        System.out.println("   3. Remove Customer");
                        System.out.println("   4. View All Customer");
                        System.out.println("   0. Back");
                        System.out.printf("Enter your choice> ");
                        customerChoice = sc.nextInt();
                        sc.nextLine();

                        switch (customerChoice) {
                            case 1: {
                                customerManagement.addNewCustomer();
                                break;
                            }
                            case 2: {
                                customerManagement.updateCustomerInfor();
                                break;
                            }
                            case 3: {
                                break;
                            }
                            case 4: {
                                customerManagement.viewAllCustomer();
                                break;
                            }
                            case 0: {
                                System.out.println("Backing...");
                            }
                            default:
                                break;
                        }
                    } while (customerChoice != 0);
                    break;
                }
                case 3: { // Sales Transaction Management

                    int transactionChoice = -1;

                    do {
                        System.out.println("======================================");
                        System.out.format("%27s", "Sales Transaction\n");
                        System.out.println("======================================");
                        System.out.println("   1. Create New Transaction");
                        System.out.println("   2. Add/ Update Transaction");
                        System.out.println("   3. Calculate Total Bill");
                        System.out.println("   4. Delate Transaction");
                        System.out.println("   5. View Transaction History");
                        System.out.println("   6. Search Bill in Detailed");
                        System.out.println("   0. Back");
                        System.out.printf("Enter your choice> ");
                        transactionChoice = sc.nextInt();
                        sc.nextLine();

                        switch (transactionChoice) {
                            case 1: {
                                
                                break;
                            }
                            case 2: {
                                break;
                            }
                            case 3: {
                                break;
                            }
                            case 4: {
                                break;
                            }
                            case 0: {
                                break;
                            }
                            default:
                                break;
                        }
                    } while (transactionChoice != 0);
                    break;
                }
                case 4: { // Report Service
                    break;
                }
                case 0: { // Close Program
                    System.out.println("Exiting program... Goodbye!");
                    break;
                }
                default:
                    System.out.println("Invalid input! Please choose again.");
            }
        } while (menuChoice != 0);
        sc.close();
    }
}
