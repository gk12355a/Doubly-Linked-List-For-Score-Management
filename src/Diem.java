/**
 * Lớp Diem: Cấu trúc liên kết một Môn học và Điểm số.
 * (Theo yêu cầu: "Điểm cũng là một cấu trúc...")
 */
public class Diem {
    private MonHoc monHoc;
    private double diemSo; // Điểm hệ 10

    public Diem(MonHoc monHoc, double diemSo) {
        this.monHoc = monHoc;
        this.diemSo = diemSo;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public double getDiemSo() {
        return diemSo;
    }

    public void setDiemSo(double diemSo) {
        this.diemSo = diemSo;
    }
}