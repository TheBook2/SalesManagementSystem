package model.customer;

package model.customer;

public class VIPCustomer extends Customer {

    public VIPCustomer() {
        setCustomerType("VIP");
    }

    public VIPCustomer(int id, String name, String phone, String address) {
        super(id, name, phone, address);
        setCustomerType("VIP");
    }

    @Override
    public double getDiscountRate() {
        return 0.15; // 15%
    }
}
