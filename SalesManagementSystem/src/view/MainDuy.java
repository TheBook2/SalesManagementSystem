package view;

import java.util.Scanner;

import service.InventoryManagement;
import service.ProductManagement;

public class MainDuy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Khởi tạo đối tượng quản lý sản phẩm
        ProductManagement pm = new ProductManagement();

        // 2. Khởi tạo đối tượng quản lý kho (truyền pm vào để dùng chung danh sách
        // ArrayList)
        InventoryManagement im = new InventoryManagement(pm);

        int choice;
        do {
            System.out.println("\n================ MAIN MENU ================");
            System.out.println("1. Add New Product(s)");
            System.out.println("2. View All Products");
            System.out.println("3. Update Product Info");
            System.out.println("4. Remove Product by ID");
            System.out.println("5. Search Product by Keyword");
            System.out.println("6. Check Stock Availability");
            System.out.println("7. Sell Product (Update Stock After Sale)");
            System.out.println("0. Exit Program");
            System.out.print("Please enter your choice (0-7): ");

            choice = sc.nextInt();
            sc.nextLine(); // Xóa bộ nhớ đệm tránh trôi lệnh

            switch (choice) {
                case 1:
                    pm.AddNewProduct();
                    break;

                case 2:
                    pm.ViewAllProduct();
                    break;

                case 3:
                    // Truyền tạm tham số id là 0 vì bên trong hàm của bạn sẽ hỏi lại id sau
                    pm.UpdateProduct(0);
                    break;

                case 4:
                    System.out.print("Enter Product ID to remove: ");
                    int idRemove = sc.nextInt();
                    sc.nextLine();
                    pm.RemoveProduct(idRemove);
                    break;

                case 5:
                    System.out.print("Enter keyword (Name or Category) to search: ");
                    String keyword = sc.nextLine();
                    pm.SearchProduct(keyword);
                    break;

                case 6:
                    System.out.print("Enter Product ID to check: ");
                    int idCheck = sc.nextInt();
                    System.out.print("Enter quantity to sell: ");
                    int qtyCheck = sc.nextInt();
                    sc.nextLine();
                    im.CheckAvailableStock(idCheck, qtyCheck);
                    break;

                case 7:
                    System.out.print("Enter Product ID to sell: ");
                    int idSale = sc.nextInt();
                    System.out.print("Enter quantity sold: ");
                    int qtySale = sc.nextInt();
                    sc.nextLine();

                    // Thường sẽ kiểm tra trước khi trừ kho
                    if (im.CheckAvailableStock(idSale, qtySale)) {
                        im.UpdateProductAfterSale(idSale, qtySale);
                    }
                    break;

                case 0:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please choose between 0 and 7.");
            }
        } while (choice != 0);

        sc.close();
    }

}
