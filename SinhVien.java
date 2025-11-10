/**
 * Lớp SinhVien: Dùng để lưu trữ thông tin của một sinh viên.
 * Đây là dữ liệu mà mỗi Node trong danh sách liên kết sẽ nắm giữ.
 */
public class SinhVien {
    // Thuộc tính
    private String maSV;
    private String hoTen;
    private double diem;

    // Constructor
    public SinhVien(String maSV, String hoTen, double diem) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.diem = diem;
    }

    // Các phương thức getter và setter (để truy cập và thay đổi)
    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    // Phương thức để hiển thị thông tin sinh viên
    @Override
    public String toString() {
        return "Ma SV: " + maSV + ", Ho ten: " + hoTen + ", Diem: " + diem;
    }
}