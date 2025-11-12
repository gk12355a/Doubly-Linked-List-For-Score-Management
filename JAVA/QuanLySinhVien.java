package JAVA;

// Thêm các thư viện java.io
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Lớp QuanLySinhVien: Cài đặt Danh sách liên kết kép
 * và các thuật toán theo yêu cầu đề tài.
 * (PHIÊN BẢN NÂNG CẤP CÓ ĐỌC/GHI FILE)
 */
public class QuanLySinhVien {
    private Node head;
    private Node tail;

    public QuanLySinhVien() {
        this.head = null;
        this.tail = null;
    }

    // --- CÁC HÀM CƠ BẢN ---
    
    /**
     * Thêm sinh viên vào cuối danh sách (Khi người dùng nhập)
     */
    public void themSinhVien(SinhVien sv) {
        Node newNode = new Node(sv);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        System.out.println("=> Da them sinh vien: " + sv.getHoTen());
    }

    /**
     * Hàm nội bộ: Thêm SV khi tải file mà không in ra thông báo
     */
    private void themSinhVienKhiTaiFile(SinhVien sv) {
        Node newNode = new Node(sv);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
    }

    /**
     * Hiển thị toàn bộ danh sách
     */
    public void hienThiDanhSach() {
        if (head == null) {
            System.out.println("Danh sach rong.");
            return;
        }
        System.out.println("--- DANH SACH SINH VIEN ---");
        Node hienTai = head;
        int stt = 1;
        while (hienTai != null) {
            System.out.println(stt + ". " + hienTai.getData().toString());
            hienTai = hienTai.getNext();
            stt++;
        }
        System.out.println("----------------------------");
    }

    // --- CÁC HÀM NGHIỆP VỤ (ĐỀ TÀI 18) ---
    // (Giữ nguyên toàn bộ các hàm: sapXepTheoDiemToan, sapXepTheoDTB, 
    // timMaxMinDTB, xuatDanhSachTheoXepLoai)

    /**
     * 18.2. Sắp xếp danh sách theo chiều tăng dần của điểm toán.
     */
    public void sapXepTheoDiemToan() {
        if (head == null || head.getNext() == null) return; 

        boolean daSapXep;
        do {
            daSapXep = false;
            Node hienTai = head;
            while (hienTai.getNext() != null) {
                double diem1 = hienTai.getData().getDiemMon("TOAN");
                double diem2 = hienTai.getNext().getData().getDiemMon("TOAN");

                if (diem1 > diem2) {
                    SinhVien temp = hienTai.getData();
                    hienTai.setData(hienTai.getNext().getData());
                    hienTai.getNext().setData(temp);
                    daSapXep = true;
                }
                hienTai = hienTai.getNext();
            }
        } while (daSapXep);
        
        System.out.println("Da sap xep tang dan theo diem Toan.");
    }

    /**
     * 18.3. Sắp xếp danh sách theo chiều tăng dần của điểm trung bình.
     */
    public void sapXepTheoDTB() {
        if (head == null || head.getNext() == null) return;

        boolean daSapXep;
        do {
            daSapXep = false;
            Node hienTai = head;
            while (hienTai.getNext() != null) {
                double dtb1 = hienTai.getData().tinhDiemTrungBinh();
                double dtb2 = hienTai.getNext().getData().tinhDiemTrungBinh();

                if (dtb1 > dtb2) {
                    SinhVien temp = hienTai.getData();
                    hienTai.setData(hienTai.getNext().getData());
                    hienTai.getNext().setData(temp);
                    daSapXep = true;
                }
                hienTai = hienTai.getNext();
            }
        } while (daSapXep);

        System.out.println("Da sap xep tang dan theo Diem Trung Binh.");
    }

    /**
     * 18.4 Tìm sinh viên có điểm trung bình Max/Min
     */
    public void timMaxMinDTB() {
        if (head == null) {
            System.out.println("Danh sach rong.");
            return;
        }

        Node nodeMax = head;
        Node nodeMin = head;
        double dtbMax = head.getData().tinhDiemTrungBinh();
        double dtbMin = head.getData().tinhDiemTrungBinh();

        Node hienTai = head.getNext();
        while (hienTai != null) {
            double dtbHienTai = hienTai.getData().tinhDiemTrungBinh();
            
            if (dtbHienTai > dtbMax) {
                dtbMax = dtbHienTai;
                nodeMax = hienTai;
            }
            
            if (dtbHienTai < dtbMin) {
                dtbMin = dtbHienTai;
                nodeMin = hienTai;
            }
            
            hienTai = hienTai.getNext();
        }

        System.out.println("--- SINH VIEN DTB MAX/MIN ---");
        System.out.println("SV co DTB Cao nhat (Max):");
        System.out.println(nodeMax.getData().toString());
        System.out.println("SV co DTB Thap nhat (Min):");
        System.out.println(nodeMin.getData().toString());
        System.out.println("-----------------------------");
    }

    /**
     * 18.5. Xuất danh sách sinh viên đạt điểm A, B, C, D, F
     */
    public void xuatDanhSachTheoXepLoai(String xepLoai) {
        if (head == null) {
            System.out.println("Danh sach rong.");
            return;
        }

        System.out.println("--- DANH SACH SINH VIEN XEP LOAI: " + xepLoai + " ---");
        Node hienTai = head;
        int stt = 1;
        boolean timThay = false;

        while (hienTai != null) {
            if (hienTai.getData().getXepLoai().equals(xepLoai)) {
                System.out.println(stt + ". " + hienTai.getData().toString());
                stt++;
                timThay = true;
            }
            hienTai = hienTai.getNext();
        }

        if (!timThay) {
            System.out.println("Khong tim thay sinh vien nao dat xep loai " + xepLoai);
        }
        System.out.println("------------------------------------");
    }

    // --- CÁC HÀM ĐỌC/GHI FILE (NÂNG CẤP) ---

    /**
     * Tải danh sách sinh viên từ file
     * @param tenFile Tên file (ví dụ: "data.txt")
     * @param cacMonHoc Mảng các môn học (lấy từ Main) để tạo đối tượng Diem
     */
    public void taiDanhSachTuFile(String tenFile, MonHoc[] cacMonHoc) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            System.out.println("Dang tai du lieu tu file " + tenFile + "...");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                
                // Định dạng: MaSV,HoTen,DiemToan,DiemLy,DiemAnh,DiemCTDL (6 phần)
                if (parts.length == 6) { 
                    try {
                        String maSV = parts[0].trim();
                        String hoTen = parts[1].trim();
                        
                        // Tạo mảng 4 điểm
                        Diem[] danhSachDiem = new Diem[cacMonHoc.length];
                        
                        // Đọc 4 điểm (parts[2] đến parts[5])
                        for (int i = 0; i < cacMonHoc.length; i++) {
                            // i=0 -> parts[2] (Toán)
                            // i=1 -> parts[3] (Lý)
                            // i=2 -> parts[4] (Anh)
                            // i=3 -> parts[5] (CTDL)
                            double diemSo = Double.parseDouble(parts[i + 2].trim());
                            danhSachDiem[i] = new Diem(cacMonHoc[i], diemSo);
                        }
                        
                        SinhVien sv = new SinhVien(maSV, hoTen, danhSachDiem);
                        this.themSinhVienKhiTaiFile(sv); // Thêm "im lặng"
                        
                    } catch (NumberFormatException e) {
                        System.out.println("Loi dinh dang so trong file: " + line);
                    }
                }
            }
            System.out.println("Tai du lieu thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi doc file (file co the chua ton tai): " + e.getMessage());
        }
    }

    /**
     * Lưu danh sách sinh viên hiện tại vào file
     */
    public void luuDanhSachVaoFile(String tenFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
            System.out.println("Dang luu du lieu vao file " + tenFile + "...");
            Node hienTai = head;
            while (hienTai != null) {
                SinhVien sv = hienTai.getData();
                
                // Chuẩn bị chuỗi dữ liệu
                // MaSV,HoTen,
                String line = sv.getMaSV() + "," + sv.getHoTen();
                
                // Nối 4 điểm vào chuỗi
                // LƯU Ý: Phải đảm bảo thứ tự điểm khi lưu
                line += "," + sv.getDiemMon("TOAN");
                line += "," + sv.getDiemMon("LY");
                line += "," + sv.getDiemMon("ANH");
                line += "," + sv.getDiemMon("CTDL");
                
                bw.write(line);
                bw.newLine();
                
                hienTai = hienTai.getNext();
            }
            System.out.println("Luu du lieu thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }
}