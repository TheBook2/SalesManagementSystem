package service;

import model.Product;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductManagement {
    private ArrayList<Product> arr = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // Trả về size của ArrayList thay vì dùng biến countProduct riêng
    public int getCountProduct() {
        return arr.size();
    }

    public ArrayList<Product> getArr() {
        return this.arr;
    }

    public void AddNewProduct() {
        boolean cont = false;
        do {
            Product p = new Product();
            p.addNewProduct();

            arr.add(p); // thêm đối tượng vào arraylist

            System.out.print("Add more (true/false)? ");
            cont = sc.nextBoolean();
            sc.nextLine(); // Sửa lỗi trôi lệnh: Đọc bỏ ký tự xuống dòng sau khi nhập boolean
        } while (cont);
    }

    public void UpdateProduct(int id) {
        boolean cont = false;
        do {
            System.out.print("Enter product ID to update: ");
            id = sc.nextInt();
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

    public void ViewAllProduct() {
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
        for (int i = 0; i < arr.size(); i++) {
            // Lấy từng sản phẩm bằng arr.get(i) để kiểm tra
            if (arr.get(i).getNameProduct().equalsIgnoreCase(keyword)
                    || arr.get(i).getCategoryProduct().equalsIgnoreCase(keyword)) {
                arr.get(i).viewAllProduct();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No product found with keyword: " + keyword);
        }
    }
}
