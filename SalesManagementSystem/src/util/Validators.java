package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Customer;
import service.CustomerManagement;

public class Validators {
    private static String PHONE_REGEX = "^0\\d{9}$";                       // các biến dùng để tạo quy tắc cho dữ liệu đầu vào
    private static Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);   // quy ước thông tin sdt là: bắt đầu bằng số 0 và liên tiếp là 9 chữ số.
    private CustomerManagement cManagement = new CustomerManagement();
    private Customer[] customers = cManagement.getCustomers();
    // Validate phone number
    public static boolean PhoneValidation(String phone) {
        if (phone == null) {
            return false;
        }
        Matcher matcher = PHONE_PATTERN.matcher(phone);

        return matcher.matches();  // trả về true nếu dữ liệu đúng với quy ước
    }

    // ===============================

    public boolean IsPhoneUnique(String phone) {
        int count = cManagement.getCountCustomer();
        for (int i = 0; i < count; i++) {
            if (customers[i].getPhoneCustomer().equals(phone)) {
                return false;           
            }
        }
        return true;
    }
}
