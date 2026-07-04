
package service;

import model.Product;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductManagement {
    private ArrayList<Product> arr = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // Hàm hỗ trợ kiểm tra xem tên sản phẩm đã tồn tại hay chưa (không phân biệt hoa
    // thường)
    public boolean isNameExists(String name) {
        for (Product p : arr) {
            if (p.getNameProduct().equalsIgnoreCase(name)) {
                return true; // Đã tồn tại tên này
            }
        }
        return false; // Chưa tồn tại
    }

    // Trả về size của ArrayList thay vì dùng biến countProduct riêng
    public int getCountProduct() {
        return arr.size();
    }

    public ArrayList<Product> getArr() {
        return this.arr;
    }

    public void AddNewProducts() {
        boolean cont = false;
        do {
            Product p = new Product();

            // 1. Vòng lặp bắt nhập ID duy nhất
            int id;
            while (true) {
                System.out.print("ID: ");
                id = sc.nextInt();
                sc.nextLine(); // Đọc bỏ dòng thừa

                if (GetProductById(id) != null) {
                    System.out.println("Error: This ID already exists! Please enter a unique ID.");
                } else {
                    break; // ID hợp lệ
                }
            }
            p.setIdProduct(id); // Gán ID vào đối tượng

            // 2. Vòng lặp bắt nhập TÊN duy nhất
            String name;
            while (true) {
                System.out.print("Name: ");
                name = sc.nextLine();

                if (isNameExists(name)) {
                    System.out.println("Error: This product name already exists! Please enter a unique name.");
                } else {
                    break; // Tên hợp lệ
                }
            }
            p.setNameProduct(name); // Gán Name vào đối tượng
            p.addNewProduct();
            arr.add(p);

            System.out.print("Add more (true/false)? ");
            cont = sc.nextBoolean();
            sc.nextLine();
        } while (cont);

    }

    public void UpdateProduct() {
        boolean cont = false;
        do {
            System.out.print("Enter product ID to update: ");
            int id = sc.nextInt();
            sc.nextLine(); // Đọc bỏ ký tự xuống dòng

            boolean found = false;
            // Áp dụng cách duyệt index arr.size()
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).getIdProduct() == id) {
                    System.out.print("Enter new name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter new category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter new price: ");
                    double price = sc.nextDouble();

                    System.out.print("Enter stock quantity: ");
                    int stockQuantity = sc.nextInt();
                    sc.nextLine(); // Đọc bỏ ký tự xuống dòng

                    // Cập nhật thông tin đối tượng tại vị trí i
                    arr.get(i).UpdateProduct(id, name, category, price, stockQuantity);
                    System.out.println("Update product successfully!");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Product not found!");
            }

            System.out.print("Update more products (true/false)? ");
            cont = sc.nextBoolean();
            sc.nextLine(); // Đọc bỏ ký tự xuống dòng

        } while (cont);
    }

    // Hàm xóa phần tử: Dùng hàm .remove(index) thần thánh của ArrayList
    public void RemoveProduct(int id) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getIdProduct() == id) {

                arr.remove(i); // Vừa tìm thấy index 'i' trùng ID là xóa luôn, ArrayList tự dồn hàng!

                System.out.println("Remove product successfully!");
                return; // Xóa xong thoát hàm luôn
            }
        }
        System.out.println("Product not found to remove!");
    }

    public void ViewAllProducts() {
        if (arr.size() == 0) {
            System.out.println("Product list is empty!");
            return;
        }

        System.out.println("----- Product List -----");
        for (Product p : arr) {
            p.viewAllProduct(); // Gọi hàm hiển thị của lớp Product
        }
    }

    public void SearchProduct(String keyword) {
        boolean found = false;
        // chuyển keyword về chữ thường trước để tối ưu tìm kiếm
        String lowerKeyword = keyword.toLowerCase();
        for (int i = 0; i < arr.size(); i++) {
            Product p = arr.get(i);

            // Lấy từng sản phẩm bằng arr.get(i) để kiểm tra
            if (p.getNameProduct().toLowerCase().contains(lowerKeyword)
                    || p.getCategoryProduct().toLowerCase().contains(lowerKeyword)) {
                p.viewAllProduct();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No product found with keyword: " + keyword);
        }
    }

    public Product GetProductById(int id) {
        for (Product d : arr) {
            if (d.getIdProduct() == id) {
                return d;
            }
        }
        return null;
    }
}