package model;

import java.util.Scanner;

/*
Các quy tắc đặt tên:
Fields: 
- idProduct: int
- nameProduct: String
- categoryProduct: String
- price: double
- stockQuantity: int
Method:  
+ updateStockQuantity(): void 
*/

public class Product {
    private int idProduct;
    private String nameProduct;
    private String categoryProduct;
    private double priceProduct;
    private int stockQuantity;

    // Constructor
    public Product() {

    }

    public Product(int idProduct, String nameProduct, String categoryProduct, double price, int stockQuantity) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.categoryProduct = categoryProduct;
        setPriceProduct(price);
        setStockQuantity(stockQuantity);
    }

    public void updateStockProduct(int quantity) {
        if (this.stockQuantity + quantity < 0) {
            System.out.println("Error: Stock quantity cannot be negative!");
            return;
        }
        this.stockQuantity += quantity;
    }

    public void UpdateProduct(int idProduct, String nameProduct, String categoryProduct, double price,
            int stockQuantity) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.categoryProduct = categoryProduct;
        setPriceProduct(price);
        setStockQuantity(stockQuantity);
    }

    public void viewAllProduct() {
        System.out.println("ID: " + idProduct);
        System.out.println("Name: " + nameProduct);
        System.out.println("Category: " + categoryProduct);
        System.out.println("Price: " + priceProduct);
        System.out.println("Stock: " + stockQuantity);
    }
    // =====================================================================================================
    // Vung setter & getter cua cac fields

    // ID
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    // Name
    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    // Category product
    public String getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(String categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    // Price
    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double price) {
        if (price < 0) {
            System.out.println("Price cannot be negative!");
            return;
        }

        this.priceProduct = price; // thêm điều kiện price ko đc âm
    }

    // Stock Quantity
    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            System.out.println("Stock quantity cannot be negative!");
            return;
        }
        this.stockQuantity = stockQuantity; // thêm điều kiện stock quantity ko đc âm
    }

    public void addNewProduct() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Category: ");
        categoryProduct = sc.nextLine();

        while (true) {
            System.out.println("Price: ");
            double inputPrice = sc.nextDouble();
            sc.nextLine();
            this.setPriceProduct(inputPrice);
            if (this.priceProduct == inputPrice) {
                break;
            }
        }
        while (true) {
            System.out.print("Stock Quantity: ");
            int inputStock = sc.nextInt();
            sc.nextLine();
            this.setStockQuantity(inputStock);
            if (this.stockQuantity == inputStock) {
                break;
            }
        }

    }

}
