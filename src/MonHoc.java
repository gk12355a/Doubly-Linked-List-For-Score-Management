/**
 * Lớp MonHoc: Lưu thông tin về một môn học.
 * (Theo yêu cầu: "môn học cũng là một cấu trúc gồm tên môn, số tín chỉ")
 */
public class MonHoc {
    private String maMonHoc;
    private String tenMon;
    private int soTinChi;

    public MonHoc(String maMonHoc, String tenMon, int soTinChi) {
        this.maMonHoc = maMonHoc;
        this.tenMon = tenMon;
        this.soTinChi = soTinChi;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public String getTenMon() {
        return tenMon;
    }

    public int getSoTinChi() {
        return soTinChi;
    }
}