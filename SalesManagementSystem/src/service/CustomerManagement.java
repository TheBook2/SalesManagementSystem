package service;

import java.util.Scanner;

import model.Customer;

public class CustomerManagement {
    private Customer[] customers = new Customer[100];
    Scanner sc = new Scanner(System.in);
    private int countCustomer = 0;

    // =====================================================================================================

    public void AddNewCustomer(Customer customer) {
        this.customers[countCustomer] = customer;
        this.countCustomer++;  
    }

    // =====================================================================================================

    public void UpdateCustomerInfor() {
        int updateChoice = -1;
        String phoneTemp;

        System.out.println("----------- Update Customer Information -----------");
        System.out.print("Enter phone number> ");
        phoneTemp = sc.nextLine();

        // xác định phần tử chứa số điện thoại được nhập
        int index = FindCustomerIndexbyPhone(phoneTemp);
        if (index == -1) {
            System.out.println("This phone number is NOT available");
            return;
        } else {
            System.out.format("Customer Information: %s\n", customers[index].getNameCustomer());
        }

        // hiển thị thông tin khách hành xác định
        System.out.printf("%-5s %-20s %-15s %-25s %-10s\n", "ID", "Name", "Phone", "Address", "Type");
        System.out.println("-----------------------------------------------------------------------------");

        // format 5 - 20 - 15 - 25 - 10
        System.out.format("%-5d %-20s %-15s %-25s %-10s\n",
                customers[index].getIdCustomer(),
                customers[index].getNameCustomer(),
                customers[index].getPhoneCustomer(),
                customers[index].getAddressCustomer(),
                customers[index].getCustomerType());

        // thực thi quá trình cập nhật thông tin
        do {
            System.out.println("Change customer information: ");
            System.out.println("  1. Name");
            System.out.println("  2. Phone number");
            System.out.println("  3. Address");
            System.out.println("  0. Back");
            System.out.println("Enter your choice> ");
            updateChoice = sc.nextInt();
            sc.nextLine();

            switch (updateChoice) {
                case 1: { // Name
                    String newName;

                    System.out.print("New Name: ");
                    newName = sc.nextLine();

                    customers[index].setNameCustomer(newName);
                    break;
                }
                case 2: { // Phone
                    String newPhone;

                    System.out.print("New phone number: ");
                    newPhone = sc.nextLine();

                    customers[index].setPhoneCustomer(newPhone);
                    break;
                }
                case 3: { // Address
                    String newAddress;

                    System.out.print("New address: ");
                    newAddress = sc.nextLine();

                    customers[index].setAddressCustomer(newAddress);
                    break;
                }
                case 0: {
                    System.out.println("Backing...");
                    break;
                }
                default:
                    break;
            }
        } while (updateChoice != 0);
    }

    // =====================================================================================================

    public void RemoveCustomer() {
        int verify;

        System.out.println("----------- REMOVE CUSTOMER -----------");
        System.out.print("Enter customer phone number> ");
        String phone = sc.nextLine();

        // xác định phần tử chứa số điện thoại được nhập
        int index = FindCustomerIndexbyPhone(phone);
        if (index == -1) {
            System.out.println("The phone number is NOT available");
            return;
        }

        // xóa phần tử bằng cách dịch chuyển các phần tử phía sau lên 1 vị trí
        System.out.print("Do you sure to remove? [YES: 1/ NO: 0]: ");
        verify = sc.nextInt();
        sc.nextLine();

        // xác nhận quá trình, xóa = 1/ hủy = 0;
        if (verify == 1) {
            // xóa phần tử bằng việc dịch lùi phần tử
            for (int i = index; i < countCustomer - 1; i++) {
                customers[i] = customers[i + 1];

                // cập nhật biến đếm
                customers[countCustomer - 1] = null;
                System.out.println("Customer removed successfully!");
            }
            countCustomer--;
        } else {
            System.out.println("The process is canceled. Returning ...");
        }
    }

    // =====================================================================================================

    public void ViewAllCustomer() {
        System.out.println("----------- CUSTOMER LIST -----------");

        // kiểm tra số lượng hợp lệ
        if (countCustomer == 0) {
            System.out.println("No customers available in the system.");
            return;
        }

        // format 5 - 20 - 15 - 25 - 10
        System.out.printf("%-5s %-20s %-15s %-25s %-10s\n", "ID", "Name", "Phone", "Address", "Type");
        System.out.println("-----------------------------------------------------------------------------");

        // duyệt các phần tử theo mảng để hiện từng phần tử
        for (int i = 0; i < countCustomer; i++) {
            Customer p = customers[i];
            System.out.printf("%-5d %-20s %-15s %-25s %-10s\n",
                    p.getIdCustomer(),
                    p.getNameCustomer(),
                    p.getPhoneCustomer(),
                    p.getAddressCustomer(),
                    p.getCustomerType());
        }
        System.out.println("-----------------------------------------------------------------------------");
    }

    // =====================================================================================================

    /*
     * Danh sách các hàm thêm vào, ngoài các hàm chính trên UML.
     * Mục đích để tăng sự quản lí của lớp CustomerService
     * 
     */

    // kiếm tra sự trùng lặp phần tử số điện thoại trong mảng customers
    public boolean IsPhoneUnique(String phone) {
        for (int i = 0; i < countCustomer; i++) {
            if (customers[i].getPhoneCustomer().equals(phone)) {
                return false;
            }
        }
        return true;
    }

    // tìm số điện thoại trong các phần tử được lưu trong mảng
    public int FindCustomerIndexbyPhone(String phone) {
        if (phone == null) {
            System.out.println("Invalid input");
            return -1;
        }

        // tìm vị trí phần tử chứa sdt trùng với input
        for (int i = 0; i < countCustomer; i++) {
            if (customers[i].getPhoneCustomer().equals(phone)) {
                return i;
            }
        }

        // trả về -1 nếu không tìm thấy sau khi duyệt hết mảng
        return -1;
    }

    public int getCountCustomer() {
        return countCustomer;
    }

    public Customer[] getCustomers() {
        return customers;
    }
    
    
}
