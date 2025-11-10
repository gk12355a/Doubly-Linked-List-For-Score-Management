import java.util.Scanner;

/**
 * Lớp Main: Chứa menu và logic chính để chạy ứng dụng quản lý điểm.
 */
public class Main {
    
    // Định nghĩa tên file ở đây để dễ dàng thay đổi
    private static final String TEN_FILE = "sinhvien.txt";

    public static void main(String[] args) {
        // Tạo một đối tượng Scanner để đọc dữ liệu từ bàn phím
        Scanner scanner = new Scanner(System.in);
        
        // Tạo đối tượng quản lý (chính là danh sách liên kết kép)
        QuanLyDiem qld = new QuanLyDiem();
        
        // --- BƯỚC 1: TẢI DỮ LIỆU KHI KHỞI ĐỘNG ---
        qld.taiDanhSachTuFile(TEN_FILE);
        //-----------------------------------------

        int luaChon; // Biến lưu lựa chọn của người dùng

        do {
            // Hiển thị menu
            System.out.println("\n--- MENU QUAN LY DIEM SINH VIEN (DS LIEN KET KEP) ---");
            System.out.println("1. Them sinh vien vao cuoi danh sach");
            System.out.println("2. Hien thi danh sach (duyet xuoi)");
            System.out.println("3. Hien thi danh sach (duyet nguoc)");
            System.out.println("4. Tim kiem va hien thi thong tin sinh vien (theo Ma SV)");
            System.out.println("5. Sua diem sinh vien (theo Ma SV)");
            System.out.println("6. Xoa sinh vien (theo Ma SV)");
            System.out.println("0. Thoat chuong trinh (Va luu du lieu)");
            System.out.print("Moi ban chon chuc nang: ");

            // Đọc lựa chọn (sử dụng try-catch để tránh lỗi nhập sai kiểu)
            try {
                luaChon = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                luaChon = -1; // Gán một giá trị không hợp lệ để chạy default case
            }

            // Xử lý lựa chọn
            switch (luaChon) {
                case 1:
                    // 1. Thêm sinh viên
                    System.out.print("Nhap Ma SV: ");
                    String maSV = scanner.nextLine();
                    System.out.print("Nhap Ho ten: ");
                    String hoTen = scanner.nextLine();
                    double diem = -1;
                    // Vòng lặp yêu cầu nhập lại nếu điểm không hợp lệ
                    while (diem < 0 || diem > 10) {
                        try {
                            System.out.print("Nhap Diem (0-10): ");
                            diem = Double.parseDouble(scanner.nextLine());
                            if(diem < 0 || diem > 10) System.out.println("Diem phai trong khoang 0-10. Vui long nhap lai.");
                        } catch (NumberFormatException e) {
                            System.out.println("Vui long nhap mot so. Vui long nhap lai.");
                            diem = -1; // Đặt lại giá trị để lặp tiếp
                        }
                    }
                    // Tạo đối tượng SinhVien và thêm vào danh sách
                    SinhVien svMoi = new SinhVien(maSV, hoTen, diem);
                    qld.themSinhVien(svMoi);
                    break;
                case 2:
                    // 2. Hiển thị danh sách (xuôi)
                    qld.hienThiDanhSach();
                    break;
                case 3:
                    // 3. Hiển thị danh sách (ngược)
                    qld.hienThiDanhSachNguoc();
                    break;
                case 4:
                    // 4. Tìm kiếm
                    System.out.print("Nhap Ma SV can tim: ");
                    maSV = scanner.nextLine();
                    Node nodeTimThay = qld.timKiem(maSV);
                    if (nodeTimThay != null) {
                        System.out.println("Tim thay sinh vien:");
                        System.out.println(nodeTimThay.getData().toString());
                    } else {
                        System.out.println("Khong tim thay sinh vien voi ma: " + maSV);
                    }
                    break;
                case 5:
                    // 5. Sửa điểm
                    System.out.print("Nhap Ma SV can sua diem: ");
                    maSV = scanner.nextLine();
                    double diemMoi = -1;
                    // Vòng lặp yêu cầu nhập lại nếu điểm không hợp lệ
                    while (diemMoi < 0 || diemMoi > 10) {
                         try {
                            System.out.print("Nhap Diem moi (0-10): ");
                            diemMoi = Double.parseDouble(scanner.nextLine());
                            if(diemMoi < 0 || diemMoi > 10) System.out.println("Diem phai trong khoang 0-10. Vui long nhap lai.");
                        } catch (NumberFormatException e) {
                            System.out.println("Vui long nhap mot so. Vui long nhap lai.");
                            diemMoi = -1; // Đặt lại giá trị để lặp tiếp
                        }
                    }
                    qld.suaDiem(maSV, diemMoi);
                    break;
                case 6:
                    // 6. Xóa sinh viên
                    System.out.print("Nhap Ma SV can xoa: ");
                    maSV = scanner.nextLine();
                    qld.xoaSinhVien(maSV);
                    break;
                case 0:
                    // --- BƯỚC 2: LƯU DỮ LIỆU KHI THOÁT ---
                    qld.luuDanhSachVaoFile(TEN_FILE);
                    //---------------------------------------
                    
                    System.out.println("Cam on ban da su dung chuong trinh!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon tu 0 den 6.");
            }

        } while (luaChon != 0); // Lặp lại menu nếu người dùng chưa chọn 0

        scanner.close(); // Đóng scanner khi kết thúc
    }
}