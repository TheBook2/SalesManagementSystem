package view;

import java.util.Scanner;

import model.customer.Customer;
import service.CustomerManagement;
import service.ProductManagement;
import util.Validators;

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
                    ProductManagement productManagement = new ProductManagement();
                    int productChoice = -1; // biến điều khiển luồng product management

                    do {
                        System.out.println("======================================");
                        System.out.format("%27s", "Inventory Management\n");
                        System.out.println("======================================");
                        System.out.println("   1. Add New Customer");
                        System.out.println("   2. Update Customer Information");
                        System.out.println("   3. Remove Customer");
                        System.out.println("   4. View All Customer");
                        System.out.println("   0. Back");
                        System.out.printf("Enter your choice> ");
                        productChoice = sc.nextInt();
                        sc.nextLine();

                        switch (productChoice) {
                            case 1: {
                                productManagement.AddNewProduct();
                                break;
                            }
                            case 2: {
                                productManagement.UpdateProduct();
                                break;
                            }
                            case 3: {
                                productManagement.RemoveProduct();
                                break;
                            }
                            case 4: {
                                productManagement.ViewAllProduct();
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
                    CustomerManagement customerManagement = new CustomerManagement();
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
                                // Dung vong lap de nhap lien tuc
                                while (true) {
                                    String id;
                                    String name;
                                    String phone;
                                    String address;

                                    System.out.println("----------- NEW CUSTOMER -----------");

                                    // Input name
                                    System.out.print("Name: ");
                                    name = sc.nextLine();

                                    // Input phone number
                                    while (true) {
                                        System.out.print("Phone number: ");
                                        phone = sc.nextLine();

                                        // Validate string
                                        if (!Validators.PhoneValidation(phone)) {
                                            System.out.println("This phone number is NOT valid");
                                            continue;
                                        }
                                        if (!customerManagement.IsPhoneUnique(phone)) {
                                            System.out.println("This phone number is available");
                                            continue;
                                        }
                                        break;
                                    }

                                    // Input address
                                    System.out.print("Address: ");
                                    address = sc.nextLine();

                                    // Save information
                                    System.out.println("Do you want to save this information?[YES: 1/ NO: 0]: ");
                                    int toSave = sc.nextInt();
                                    sc.nextLine();

                                    if (toSave == 1) {
                                        id = String.format("C%02d", customerManagement.getCountCustomer() + 1);
                                        Customer sCustomer = new Customer(id, name, phone, address);
                                        customerManagement.AddNewCustomer(sCustomer);
                                        System.out.println("Add new customer successfully!");
                                    } else {
                                        System.out.println("The customer's information is canceled.");
                                        continue;
                                    }

                                    // Hoi user nhap tiep ko
                                    System.out.println("Would you like to add more? [YES: 1/ NO: 0]");
                                    int temp = sc.nextInt();
                                    sc.nextLine();
                                    if (temp == 1) continue;
                                    else break;
                                };
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
                        System.out.println("   2. Calculate Total Bill");
                        System.out.println("   3. Update or Cancel Transaction");
                        System.out.println("   4. View Transaction History");
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
