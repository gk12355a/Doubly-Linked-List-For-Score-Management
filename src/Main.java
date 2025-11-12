import java.util.Scanner;

/**
 * Lớp Main: Chứa menu và logic chính để chạy ứng dụng (Đề tài 18).
 */
public class Main {
    
    // --- ĐỊNH NGHĨA CÁC MÔN HỌC (Cố định) ---
    // (Mã môn, Tên môn, Số tín chỉ)
    public static final MonHoc MON_TOAN = new MonHoc("TOAN", "Toan", 3);
    public static final MonHoc MON_LY = new MonHoc("LY", "Vat Ly", 3);
    public static final MonHoc MON_ANH = new MonHoc("ANH", "Tieng Anh", 2);
    public static final MonHoc MON_CTDL = new MonHoc("CTDL", "CTDL & GT", 3);
    
    // Mảng chứa các môn học để dễ dàng lặp khi nhập điểm
    public static final MonHoc[] CAC_MON_HOC = {MON_TOAN, MON_LY, MON_ANH, MON_CTDL};
    //------------------------------------------


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuanLySinhVien qlsv = new QuanLySinhVien();
        int luaChon;

        do {
            System.out.println("\n--- MENU QUAN LY DIEM (DE TAI 18) ---");
            System.out.println("1. Nhap danh sach sinh vien (cho den khi nhap $)");
            System.out.println("2. Hien thi danh sach sinh vien");
            System.out.println("3. Sap xep danh sach TANG DAN theo diem TOAN");
            System.out.println("4. Sap xep danh sach TANG DAN theo DIEM TRUNG BINH");
            System.out.println("5. Tim sinh vien co DTB Max / Min");
            System.out.println("6. Xuat danh sach sinh vien theo xep loai (A, B, C, D, F)");
            System.out.println("0. Thoat chuong trinh");
            System.out.print("Moi ban chon chuc nang: ");

            try {
                luaChon = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                luaChon = -1;
            }

            switch (luaChon) {
                case 1:
                    // 18.1. Nhập 1 danh sách cho đến khi nhập dấu “$” vào tên
                    nhapDanhSach(scanner, qlsv);
                    break;
                case 2:
                    qlsv.hienThiDanhSach();
                    break;
                case 3:
                    // 18.2. Sắp xếp danh sách theo chiều tăng dần của điểm toán
                    qlsv.sapXepTheoDiemToan();
                    qlsv.hienThiDanhSach(); // Hiển thị kết quả sau sắp xếp
                    break;
                case 4:
                    // 18.3. Sắp xếp danh sách theo chiều tăng dần của điểm trung bình
                    qlsv.sapXepTheoDTB();
                    qlsv.hienThiDanhSach(); // Hiển thị kết quả sau sắp xếp
                    break;
                case 5:
                    // 18.4 Tìm sinh viên có điểm trung bình Max/Min
                    qlsv.timMaxMinDTB();
                    break;
                case 6:
                    // 18.5. Xuất danh sách sinh viên đạt điểm A, B, C, D, F
                    System.out.print("Nhap xep loai can xem (A, B, C, D, F): ");
                    String xepLoai = scanner.nextLine().toUpperCase();
                    qlsv.xuatDanhSachTheoXepLoai(xepLoai);
                    break;
                case 0:
                    System.out.println("Da thoat chuong trinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }

        } while (luaChon != 0);

        scanner.close();
    }
    
    /**
     * Hàm chức năng cho 18.1. Nhập danh sách
     */
    public static void nhapDanhSach(Scanner scanner, QuanLySinhVien qlsv) {
        System.out.println("--- BAT DAU NHAP DANH SACH ---");
        while (true) {
            System.out.print("Nhap Ho ten sinh vien (nhap '$' de dung): ");
            String hoTen = scanner.nextLine();
            if (hoTen.equals("$")) {
                break; // Dừng nhập khi gõ $
            }

            System.out.print("Nhap Ma SV: ");
            String maSV = scanner.nextLine();

            // Khởi tạo mảng 4 điểm cho SV
            Diem[] danhSachDiem = new Diem[CAC_MON_HOC.length];

            // Lặp qua 4 môn học CỐ ĐỊNH để nhập điểm
            for (int i = 0; i < CAC_MON_HOC.length; i++) {
                MonHoc mon = CAC_MON_HOC[i];
                double diemSo = -1;
                
                // Vòng lặp để đảm bảo điểm nhập hợp lệ (0-10)
                while (diemSo < 0 || diemSo > 10) {
                    try {
                        System.out.print("Nhap diem mon " + mon.getTenMon() + " (" + mon.getSoTinChi() + "TC): ");
                        diemSo = Double.parseDouble(scanner.nextLine());
                        if (diemSo < 0 || diemSo > 10) {
                            System.out.println("Diem phai tu 0 den 10. Nhap lai.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Vui long nhap so. Nhap lai.");
                        diemSo = -1;
                    }
                }
                // Gán điểm vừa nhập vào mảng
                danhSachDiem[i] = new Diem(mon, diemSo);
            }
            
            // Tạo SV mới và thêm vào danh sách
            SinhVien sv = new SinhVien(maSV, hoTen, danhSachDiem);
            qlsv.themSinhVien(sv);
            System.out.println("=> Da them sinh vien: " + hoTen);
        }
        System.out.println("--- KET THUC NHAP DANH SACH ---");
    }
}