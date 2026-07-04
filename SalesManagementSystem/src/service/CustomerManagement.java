package service;

import java.util.Scanner;
import java.util.ArrayList;

import model.Customer;
import util.Validators;

public class CustomerManagement {
    private ArrayList<Customer> customerArr = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    private int customerCount = 1;

    // =====================================================================================================
    public void AddNewCustomer() {
        boolean addMore = false;
        do {
            String id;
            String name;
            String phone;
            String address;

            System.out.println("----------- NEW CUSTOMER -----------");
            System.out.print("Name: ");
            name = sc.nextLine();

            // Vòng lặp validate phone: lặp lại cho đến khi phone hợp lệ VÀ chưa tồn tại
            do {
                System.out.print("Phone number: ");
                phone = sc.nextLine();

                if (Validators.PhoneValidation(phone)) {
                    System.out.println("** The phone number is valid");
                    if (IsPhoneUnique(phone)) {
                        System.out.println("** The phone number is able to use");
                    } else {
                        System.out.println("** The phone number is already in use");
                    }
                } else {
                    System.out.println("** The phone number is NOT valid");
                }
            } while (!Validators.PhoneValidation(phone) || !IsPhoneUnique(phone));

            System.out.print("Address: ");
            address = sc.nextLine();

            // Hỏi người dùng có muốn lưu không
            System.out.print("Do you want to save? [YES: true/ NO: false]: ");
            boolean toSave = sc.nextBoolean();
            sc.nextLine();

            if (toSave) {
                id = GenerateIDtoCustomer();
                Customer sCustomer = new Customer(id, name, phone, address, "Regular");
                customerArr.add(sCustomer);
                customerCount++;
                System.out.println("** Add new customer successfully!");
            } else {
                System.out.println("** The customer's information is canceled.");
            }

            // Hỏi có muốn thêm tiếp không
            System.out.print("Would you like to add more? [YES: true/ NO: false]: ");
            addMore = sc.nextBoolean();
            sc.nextLine();;
        } while (addMore);
    }

    // =====================================================================================================

    public void UpdateCustomerInfor() {
        int updateChoice = -1;
        String phoneTemp;

        System.out.println("----------- Update Customer Information -----------");
        System.out.print("Enter phone number> ");
        phoneTemp = sc.nextLine();

        // xác định phần tử chứa số điện thoại được nhập
        int index = SearchCustomerIndexbyPhone(phoneTemp);
        if (index == -1) {
            System.out.println("** The phone number is NOT available");
            return;
        } else {
            System.out.format("Customer Information: %s\n", customerArr.get(index).getNameCustomer());
            // hiển thị thông tin khách hành xác định
            System.out.printf("%-5s %-20s %-15s %-25s %-10s\n", "ID", "Name", "Phone", "Address", "Type");
            System.out.println("-----------------------------------------------------------------------------");

            // format 5 - 20 - 15 - 25 - 10
            System.out.format("%-5s %-20s %-15s %-25s %-10s\n",
                    customerArr.get(index).getIdCustomer(),
                    customerArr.get(index).getNameCustomer(),
                    customerArr.get(index).getPhoneCustomer(),
                    customerArr.get(index).getAddressCustomer(),
                    customerArr.get(index).getCustomerType());
        }

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

                    customerArr.get(index).setNameCustomer(newName);
                    break;
                }
                case 2: { // Phone
                    String newPhone;

                    System.out.print("New phone number: ");
                    newPhone = sc.nextLine();

                    if (Validators.PhoneValidation(phoneTemp)) {
                        System.out.println("** The phone number is valid");
                        if (IsPhoneUnique(newPhone)) {
                            System.out.println("** The phone number is able to use");
                            customerArr.get(index).setPhoneCustomer(newPhone);
                        }
                    } else {
                        System.out.println("** The phone number is NOT valid");
                    }
                    break;
                }
                case 3: { // Address
                    String newAddress;

                    System.out.print("New address: ");
                    newAddress = sc.nextLine();

                    customerArr.get(index).setAddressCustomer(newAddress);
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
        int index = SearchCustomerIndexbyPhone(phone);
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
            customerArr.remove(index);
        } else {
            System.out.println("The process is canceled. Returning ...");
        }
    }
    // =====================================================================================================

    public void ViewAllCustomer() {
        System.out.println("----------- CUSTOMER LIST -----------");
        if (customerArr.isEmpty()) {
            System.out.println("** Customer list is empty");
        } else {
            // format 5 - 20 - 15 - 25 - 10
            System.out.printf("%-7s %-20s %-15s %-25s %-10s\n", "ID", "Name", "Phone", "Address", "Type");
            System.out.println("-----------------------------------------------------------------------------");

            // duyệt các phần tử theo mảng để hiện từng phần tử
            for (int i = 0; i < customerArr.size(); i++) {
                Customer p = customerArr.get(i);
                System.out.printf("%-7s %-20s %-15s %-25s %-10s\n",
                        p.getIdCustomer(),
                        p.getNameCustomer(),
                        p.getPhoneCustomer(),
                        p.getAddressCustomer(),
                        p.getCustomerType());
            }
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("** Press Enter to countinue...");
            sc.nextLine();
        }
    }

    // =====================================================================================================

    /*
     * Danh sách các hàm thêm vào, ngoài các hàm chính trên UML.
     * Mục đích để tăng sự quản lí của lớp CustomerService
     * 
     */

    // kiếm tra sự trùng lặp phần tử số điện thoại trong mảng customers
    public boolean IsPhoneUnique(String phone) {
        for (int i = 0; i < customerArr.size(); i++) {
            if (customerArr.get(i).getPhoneCustomer().equals(phone)) {
                return false;
            }
        }
        return true;
    }

    // tìm vị trí số điện thoại trong các phần tử được lưu trong mảng
    public int SearchCustomerIndexbyPhone(String phone) {
        if (phone == null) {
            System.out.println("Invalid input");
            return -1;
        }

        // tìm vị trí phần tử chứa sdt trùng với input
        for (int i = 0; i < customerArr.size(); i++) {
            if (customerArr.get(i).getPhoneCustomer().equals(phone)) {
                return i;
            }
        }

        // trả về -1 nếu không tìm thấy sau khi duyệt hết mảng
        return -1;
    }

    // tìm kiếm số điện thoại trong mảng
    public boolean IsPhoneNumberExist(String phone) {
        for (int i = 0; i < customerArr.size(); i++) {
            if (customerArr.get(i).getPhoneCustomer().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public boolean IsIdExit(String id) {
        for (int i = 0; i < customerArr.size(); i++) {
            if (customerArr.get(i).getIdCustomer().equals(id)) 
                return true;
        }
        return false;
    }

    private String GenerateIDtoCustomer() {
        return "C" + String.format("%03d", customerCount);
    }

    public String GetIdCustomer(int index) {
        return "" + customerArr.get(index).getIdCustomer();
    }
}
