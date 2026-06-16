package service;

import java.util.ArrayList;

import model.Product;

public class InventoryManagement {
    private ArrayList<Product> inventory; // lấy tính chất của Product
    private ProductManagement pm; // pm - product management, lấy tính chất mảng

    public InventoryManagement(ProductManagement pm) {
        this.pm = pm;
        this.inventory = pm.getArr(); // lấy dữ liệu từ lớp ProductManagement
    }

    /**
     * Hàm hỗ trợ tìm sản phẩm theo ID
     */
    private Product findProductById(int idProduct) {
        for (Product p : inventory) {
            if (p.getIdProduct() == idProduct) {
                return p; // Trả về địa chỉ của sản phẩm tìm thấy
            }
        }
        return null; // Không tìm thấy
    }

    public void UpdateProductAfterSale(int idProduct, int quantitySold) {
        Product p = findProductById(idProduct);

        if (p == null) {
            System.out.println("Product not found!");
            return;
        }

        p.setStockQuantity(p.getStockQuantity() - quantitySold);

        System.out.println("--- UPDATE SUCCESSFULLY ---");
        System.out.println("Current Stock: " + p.getStockQuantity());

    }

    public boolean CheckAvailableStock(int idProduct, int quantitySold) {
        if (quantitySold <= 0) {
            System.out.println("Error: Quantity sold must be greater than 0!");
            return false;
        }

        Product p = findProductById(idProduct);
        
        // BR3: Kiểm tra xem sản phẩm có tồn tại trong kho không
        if (p == null) {
            System.out.println("Error: Product ID not found!");
            return false;
        }

        // BR4: Kiểm tra số lượng tồn kho xem có đủ cung ứng không
        if (p.getStockQuantity() < quantitySold) {
            System.out.println("Error: Insufficient stock! Current stock: " + p.getStockQuantity());
            return false;
        }

        // Đạt mọi điều kiện hợp lệ
        System.out.println("Valid: Sufficient stock available for the sale.");
        return true; 
    }
    
}
