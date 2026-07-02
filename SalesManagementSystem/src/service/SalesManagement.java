package service;

import java.util.ArrayList;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import model.Product;

import util.Validators;
import model.Transaction;
import model.TransactionItem;

public class SalesManagement {
    private ArrayList<Transaction> saleManagement = new ArrayList<>();     // Mảng lưu hóa đơn chính
    private CustomerManagement refCustomerManagement;      // Biến tham chiếu Customer Management
    private ProductManagement refProductManagement;
    private int transCount = 1;       // Biến đếm giao dịch - không phụ thuộc vào số lượng phần tử trong mảng                                            
    Scanner sc = new Scanner(System.in);
    private DateTimeFormatter formatterCreatedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");   // Định dạng ngày

    // =====================================================================================================

    public void CreateNewTransaction() {
        boolean cont = false, save = false;

        do {
            System.out.println("----------- NEW TRANSACTION -----------");
            // Quy trinh kiem tra id/ phone number 
            // Dành cho xác thực thông tin trước khi thực hiện 1 chức năng (thông tin đã tồn tại)
            boolean validate = false;
            System.out.printf("Enter ID/ phone number> ");
            String infor = sc.nextLine();

            if (Validators.PhoneValidation(infor)) {
                System.out.println("** The phone number is valid");
                if (refCustomerManagement.IsPhoneNumberExist(infor)) {
                    System.out.println("** The phone number is exist");
                    int index = refCustomerManagement.SearchCustomerIndexbyPhone(infor);
                    infor = refCustomerManagement.GetIdCustomer(index);    // chuyen sang id de dung
                    validate = true;
                } else {
                    System.out.println("** The phone number is NOT exist");
                }
            } else if (Validators.IdValidation(infor)) { // them ham kiem tra id // them ham tim kiem id co ton tai ko
                if (refCustomerManagement.IsIdExit(infor)) {
                    System.out.println("** The Id is exist");
                    validate = true;
                } else {
                    System.out.println("** The id is NOT exist");
                }
            } else {
                System.out.println("** Your input is NOT valid. Please enter again.");
            }

            // thực thi sau khi đã có kết quả xác thực thông tin
            if (validate) {
                String idTransactionTmp = generateIDtoTransaction();
                System.out.println("------------------------------------");
                System.out.printf("ID Customer:   %20s\n", infor);
                System.out.printf("ID Transaction:%20s\n", idTransactionTmp);
                System.out.println("------------------------------------");

                System.out.printf("Save this transaction?[YES: true/ NO: false]> ");
                save = sc.nextBoolean();
                sc.nextLine();

                if (save) {
                    String createdDate = LocalDateTime.now().format(formatterCreatedDate);
                    Transaction tmp = new Transaction(infor, createdDate, idTransactionTmp, false);
                    saleManagement.add(tmp);
                    transCount++;
                    System.out.println("** Saved transaction successfully!");
                } else {
                    System.out.println("** Canceled this transaction!");
                }
            }

            System.out.println("Do you want to create more?[YES: true/ NO: false]> ");
            cont = sc.nextBoolean();
            sc.nextLine();
        } while (cont);
    }

    // =====================================================================================================
    public void AddItemToTransaction() {
        boolean cont;

        System.out.println("----------- Add Item into Transaction -----------");
        System.out.println("Enter ID transaction> ");
        String id = sc.nextLine();

        Transaction transTmp = SearchTransactionByID(id);
        if (transTmp == null) {
            System.out.println("** Transaction is NOT found");
        } else if (transTmp.isDelete()) {
            System.out.println("** Transaction has been deleted");
        } else {
            do {
                System.out.printf("Enter Product ID> ");
                int productId = sc.nextInt();
                sc.nextLine();

                // kiem tra san pham co ton tai ko?
                Product foundProduct = refProductManagement.GetProductById(productId);
                if (foundProduct == null) {
                    System.out.println("** Product is NOT found");
                } else if (foundProduct.getStockQuantity() == 0) {
                    System.out.println("** Product is out of stock");
                } else {
                    System.out.printf("%-10s %-25s %-20s %-10s\n", "ID", "Name Product", "Price", "Quantity");
                    System.out.printf("%-10s %-25s %-20s %-10s\n",
                            foundProduct.getIdProduct(),
                            foundProduct.getNameProduct(),
                            foundProduct.getPrice(),
                            foundProduct.getStockQuantity());

                    do {
                        System.out.printf("Enter quantity> ");
                        int quantity = sc.nextInt();
                        sc.nextLine();

                        if (quantity <= 0) {
                            System.out.println("** Quantity must greater than 0");
                        } else if (quantity > foundProduct.getStockQuantity()) {
                            System.out.println("** Not enough stock!");
                        } else {
                            TransactionItem item = new TransactionItem(
                                    foundProduct.getIdProduct(),
                                    foundProduct.getNameProduct(),
                                    quantity,
                                    foundProduct.getPrice());
                            transTmp.AddItem(item);

                            foundProduct.updateStockProduct(-quantity);
                            System.out.println("** Stock updated");
                            break;
                        }
                    } while (true);
                }

                System.out.print("Add more items? [true/false]> ");
                cont = sc.nextBoolean();
                sc.nextLine();
            } while (cont);
        }
}
    // =====================================================================================================

    public void CalculateTotalBillAmount() {
        System.out.println("----------- CALCULATE TOTAL BILL -----------");
        // Bước 1: Nhập và xác thực ID hóa đơn
        System.out.print("Enter Transaction ID> ");
        String id = sc.nextLine();

        Transaction transTmp = SearchTransactionByID(id);
        if (transTmp == null) {
            System.out.println("** Transaction NOT found.");
            return;
        }

        if (transTmp.isDeleted()) {
            System.out.println("** Transaction has been deleted.");
            return;
        }

        // Bước 2: Hiển thị thông tin hóa đơn
        System.out.println("----------- Bill Details -----------");
        System.out.printf("Transaction ID : %s\n", transTmp.getIdTransaction());
        System.out.printf("Customer ID    : %s\n", transTmp.getIdCustomer());
        System.out.printf("Created Date   : %s\n", transTmp.getCreatedDate());

        // Bước 3: Liệt kê các sản phẩm và tổng tiền
        transTmp.PrintLineItems();
    }

    // =====================================================================================================

    public void DeleteTransaction() {
        boolean cont;
        do {
            System.out.println("----------- Delete Transaction -----------");
            System.out.print("Enter Transaction ID> ");
            String id = sc.nextLine();

            Transaction transTmp = SearchTransactionByID(id);
            if (transTmp == null) {
                System.out.println("** Transaction NOT found.");
            } else if (transTmp.isDeleted()) {
                System.out.println("** Transaction already deleted. Restore? [true/false]> ");
                boolean restore = sc.nextBoolean();
                sc.nextLine();
                if (restore)
                    transTmp.restore();
            } else {
                System.out.print("** Confirm delete? [true/false]> ");
                boolean confirm = sc.nextBoolean();
                sc.nextLine();
                if (confirm)
                    transTmp.softDelete();
                else
                    System.out.println("** Cancelled.");
            }

            System.out.print("Continue? [true/false]> ");
            cont = sc.nextBoolean();
            sc.nextLine();
        } while (cont);
    }

    // =====================================================================================================

    public void ViewTransactionHistory() {
        System.out.println("----------- Transaction History -----------");

        if (saleManagement.isEmpty()) {
            System.out.println("** No transactions found.");
            return;
        }

        // Hỏi số lượng muốn xem
        System.out.printf("Total transactions: %d\n", saleManagement.size());
        System.out.print("How many recent transactions to view> ");
        int limit = sc.nextInt();
        sc.nextLine();

        if (limit <= 0) {
            System.out.println("** Number must be greater than 0.");
            return;
        }

        // Nếu nhập quá số lượng hiện có thì lấy tất cả
        if (limit > saleManagement.size()) {
            limit = saleManagement.size();
            System.out.printf("** Only %d transaction(s) available. Showing all.\n", limit);
        }

        // Tính vị trí bắt đầu từ cuối mảng
        int startIndex = saleManagement.size() - limit;

        System.out.printf("%-10s %-10s %-15s %-15s %-10s", "ID Transaction", "ID Customer", "Created Date", "Status");

        for (int i = startIndex; i < saleManagement.size(); i++) {
            Transaction t = saleManagement.get(i);
            System.out.printf("| %-10s | %-10s | %-19s | %-8s |\n",
                    t.getIdTransaction(),
                    t.getIdCustomer(),
                    t.getCreatedDate(),
                    t.isDeleted() ? "Delete" : "Exist");
        }   
    }

    // =====================================================================================================
    
    private String generateIDtoTransaction() {
        return "T" + String.format("%03d", transCount);
    }

    public SalesManagement(CustomerManagement customerManagement, ProductManagement productManagement) {
        this.refCustomerManagement = customerManagement;
        this.refProductManagement = productManagement;
    }

    public Transaction SearchTransactionByID(String id) {
        for (Transaction position : saleManagement) {
            if (position.getIdTransaction().equals(id)) return position;
        }
        return null;
    }
}
