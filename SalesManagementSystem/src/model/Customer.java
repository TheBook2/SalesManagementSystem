package model;

/*
Các quy tắc đặt tên: 
Fields: 
- idCustomer: int         (format nên là: C001)
- nameCustomer: String
- phoneCustomer: String
- addressCustomer: String
- customerType: String
Method:  
+ getDiscountRate(): double
*/

public class Customer {
    private String idCustomer; 
    private String nameCustomer;
    private String phoneCustomer;
    private String addressCustomer;
    private String customerType;
    
    // Constructor
    public Customer() {
        this.idCustomer = "";
        this.nameCustomer = "";
        this.phoneCustomer = "";
        this.addressCustomer = "";
        this.customerType = "Regular";
    }

    // Constructor with parameters
    public Customer(String idCustomer, String nameCustomer, String phoneCustomer, String addressCustomer) {
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.phoneCustomer = phoneCustomer;
        this.addressCustomer = addressCustomer;
    }

    

    // =====================================================================================================
    // Vung setter & getter cua cac fields

    // ID
    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    // Name
    public String getNameCustomer() {
        return this.nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    // Phone
    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    // Address
    public String getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    // Customer Type
    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
}
