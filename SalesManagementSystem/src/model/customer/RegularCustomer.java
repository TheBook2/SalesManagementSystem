package model.customer;

public class RegularCustomer extends Customer {

    public RegularCustomer() {
        setCustomerType("Regular");
    }

    public RegularCustomer(int id, String name, String phone, String address) {
        super(id, name, phone, address);
        setCustomerType("Regular");
    }

    @Override
    public double getDiscountRate() {
        return 0.0;
    }
}
