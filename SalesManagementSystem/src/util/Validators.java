package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validators {
    // Mẫu: bắt đầu bằng 0, sau là 9 chữ số 
    private static String PHONE_REGEX = "^0\\d{9}$";                       
    private static Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);   
    
    // Mẫu: bắt đầu bằng C, sau là n chữ số (n >= 1)
    private static String ID_CUSTOMER_REGEX = "^C\\d+{10}$";                                 
    private static Pattern ID_CUSTOMER_PATTERN = Pattern.compile(ID_CUSTOMER_REGEX);     

    // Mẫu: bắt đầu bằng P, sau là n chữ số (n >= 1);
    private static String ID_PRODUCT_REGEX = "^P\\d+{10}$";
    private static Pattern ID_PRODUCT_PATTERN = Pattern.compile(ID_PRODUCT_REGEX);

    private static String ID_TRANSACTION_REGEX = "^T\\d+{10}$";
    private static Pattern ID_TRANSACTION_PATTERN = Pattern.compile(ID_TRANSACTION_REGEX);



    // Validate phone number
    public static boolean isValidPhone(String phone) {
        if (phone == null) {
            return false;
        }
        Matcher matcher = PHONE_PATTERN.matcher(phone);

        return matcher.matches();  // trả về true nếu dữ liệu đúng với quy ước
    }

    // Validate ID Customer
    public static boolean isValidIdCustomer(String id) {
        if (id == null) return false;

        Matcher matcher = ID_CUSTOMER_PATTERN.matcher(id);
        return matcher.matches();
    }

    // Validate ID Product
    public static boolean isValidIdProduct(String id) {
        if (id == null) return false;

        Matcher matcher = ID_PRODUCT_PATTERN.matcher(id);
        return matcher.matches();
    }

    // Validate ID Transaction
    public static boolean isValidIdTransaction(String id) {
        if (id == null) return false;

        Matcher matcher = ID_TRANSACTION_PATTERN.matcher(id);
        return matcher.matches();
    }
}
