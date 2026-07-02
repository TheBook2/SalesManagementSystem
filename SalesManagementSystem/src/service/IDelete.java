package service;

public interface IDelete {
    void softDelete();    // Đánh dấu đã xóa
    void restore();       // Khôi phục lại
    boolean isDeleted();  // Kiểm tra trạng thá
}
