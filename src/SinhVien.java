/**
 * Lớp SinhVien: Quản lý thông tin cá nhân và danh sách điểm của sinh viên.
 * (Theo yêu cầu: "Mỗi node quản lý thông tin cá nhân, các môn học...")
 */
public class SinhVien {
    private String maSV;
    private String hoTen;
    
    // Mỗi sinh viên có một danh sách điểm (gồm 4 môn)
    private Diem[] danhSachDiem;

    public SinhVien(String maSV, String hoTen, Diem[] danhSachDiem) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.danhSachDiem = danhSachDiem;
    }

    // --- Các hàm Getters ---
    public String getMaSV() {
        return maSV;
    }

    public String getHoTen() {
        return hoTen;
    }
    
    public Diem[] getDanhSachDiem() {
        return danhSachDiem;
    }

    /**
     * Hàm nội bộ: Tìm điểm của một môn học cụ thể theo mã
     * (Hỗ trợ cho yêu cầu 18.2. Sắp xếp theo điểm toán)
     */
    public double getDiemMon(String maMonHoc) {
        for (Diem d : danhSachDiem) {
            if (d.getMonHoc().getMaMonHoc().equals(maMonHoc)) {
                return d.getDiemSo();
            }
        }
        return -1; // Không tìm thấy
    }

    /**
     * Hàm tính Điểm Trung Bình (Hệ 10)
     * (Hỗ trợ cho yêu cầu 18.3, 18.4, 18.5)
     */
    public double tinhDiemTrungBinh() {
        double tongDiemNhanTinChi = 0;
        int tongTinChi = 0;

        for (Diem d : danhSachDiem) {
            tongDiemNhanTinChi += d.getDiemSo() * d.getMonHoc().getSoTinChi();
            tongTinChi += d.getMonHoc().getSoTinChi();
        }

        if (tongTinChi == 0) {
            return 0;
        }
        
        // ĐTB = Tổng (Điểm * Tín chỉ) / Tổng (Tín chỉ)
        return tongDiemNhanTinChi / tongTinChi;
    }

    /**
     * Hàm xếp loại học lực theo điểm trung bình (Hệ 10)
     * (Hỗ trợ cho yêu cầu 18.5)
     */
    public String getXepLoai() {
        double dtb = tinhDiemTrungBinh();
        
        if (dtb >= 8.5) return "A";
        if (dtb >= 7.0) return "B";
        if (dtb >= 5.5) return "C";
        if (dtb >= 4.0) return "D";
        return "F";
    }

    /**
     * Hàm hiển thị thông tin sinh viên
     */
    @Override
    public String toString() {
        // Sử dụng String.format để căn chỉnh cột cho đẹp
        String info = String.format("Ma SV: %-8s | Ten: %-20s | DTB: %.2f (%s)", 
                                    maSV, hoTen, tinhDiemTrungBinh(), getXepLoai());
        
        // Nối chuỗi điểm của các môn
        StringBuilder diemStr = new StringBuilder(" | Diem: [");
        for (int i = 0; i < danhSachDiem.length; i++) {
            Diem d = danhSachDiem[i];
            diemStr.append(d.getMonHoc().getMaMonHoc())
                   .append(": ")
                   .append(String.format("%.1f", d.getDiemSo()));
            if (i < danhSachDiem.length - 1) {
                diemStr.append(", ");
            }
        }
        diemStr.append("]");
        
        return info + diemStr.toString();
    }
}