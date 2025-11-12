/**
 * Lớp QuanLySinhVien: Cài đặt Danh sách liên kết kép
 * và các thuật toán theo yêu cầu đề tài.
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
     * Thêm sinh viên vào cuối danh sách
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

    /**
     * 18.2. Sắp xếp danh sách theo chiều tăng dần của điểm toán.
     * (Sử dụng Bubble Sort, hoán đổi DỮ LIỆU, không hoán đổi Node)
     */
    public void sapXepTheoDiemToan() {
        if (head == null || head.getNext() == null) return; // DS rỗng hoặc có 1 SV

        boolean daSapXep;
        do {
            daSapXep = false;
            Node hienTai = head;
            while (hienTai.getNext() != null) {
                // Lấy điểm Toán (mã "TOAN") của 2 node liền kề
                double diem1 = hienTai.getData().getDiemMon("TOAN");
                double diem2 = hienTai.getNext().getData().getDiemMon("TOAN");

                // Nếu điểm 1 > điểm 2 (sắp xếp tăng dần) thì hoán đổi
                if (diem1 > diem2) {
                    // Hoán đổi data (đối tượng SinhVien) giữa 2 node
                    SinhVien temp = hienTai.getData();
                    hienTai.setData(hienTai.getNext().getData());
                    hienTai.getNext().setData(temp);
                    daSapXep = true;
                }
                hienTai = hienTai.getNext();
            }
        } while (daSapXep); // Lặp lại nếu vẫn còn hoán đổi
        
        System.out.println("Da sap xep tang dan theo diem Toan.");
    }

    /**
     * 18.3. Sắp xếp danh sách theo chiều tăng dần của điểm trung bình.
     * (Sử dụng Bubble Sort, hoán đổi DỮ LIỆU)
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

                // Sắp xếp tăng dần
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
            
            // Tìm Max
            if (dtbHienTai > dtbMax) {
                dtbMax = dtbHienTai;
                nodeMax = hienTai;
            }
            
            // Tìm Min
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
}