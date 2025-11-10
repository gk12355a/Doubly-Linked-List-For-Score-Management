package src;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Lớp QuanLyDiem: Cài đặt cấu trúc dữ liệu Danh sách liên kết kép.
 * Lớp này sẽ chứa các thuật toán để thao tác với danh sách.
 */
public class QuanLyDiem {
    private Node head; // Nút đầu danh sách
    private Node tail; // Nút cuối danh sách
    private int size;  // Số lượng sinh viên trong danh sách

    // Constructor
    public QuanLyDiem() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // --- CÁC THUẬT TOÁN CƠ BẢN ---

    /**
     * 1. Thêm sinh viên vào cuối danh sách
     */
    public void themSinhVien(SinhVien sv) {
        Node newNode = new Node(sv);
        if (head == null) {
            // Danh sách đang rỗng
            head = newNode;
            tail = newNode;
        } else {
            // Danh sách đã có phần tử
            tail.setNext(newNode); // Liên kết nút cuối cũ với nút mới
            newNode.setPrev(tail); // Liên kết nút mới với nút cuối cũ
            tail = newNode;        // Cập nhật lại nút cuối là nút mới
        }
        size++;
        System.out.println("Da them sinh vien: " + sv.getHoTen());
    }

    /**
     * 2. Hiển thị toàn bộ danh sách (Duyệt từ đầu đến cuối)
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
            hienTai = hienTai.getNext(); // Di chuyển sang nút tiếp theo
            stt++;
        }
        System.out.println("----------------------------");
    }
    
    /**
     * 3. Hiển thị toàn bộ danh sách (Duyệt ngược từ cuối về đầu)
     */
    public void hienThiDanhSachNguoc() {
        if (tail == null) {
            System.out.println("Danh sach rong.");
            return;
        }
        System.out.println("--- DANH SACH SINH VIEN (DUYET NGUOC) ---");
        Node hienTai = tail;
        int stt = size;
        while (hienTai != null) {
            System.out.println(stt + ". " + hienTai.getData().toString());
            hienTai = hienTai.getPrev(); // Di chuyển về nút phía trước
            stt--;
        }
        System.out.println("----------------------------------------");
    }

    /**
     * 4. Tìm kiếm sinh viên theo Mã SV
     * @return Node chứa sinh viên nếu tìm thấy, ngược lại trả về null
     */
    public Node timKiem(String maSV) {
        Node hienTai = head;
        while (hienTai != null) {
            if (hienTai.getData().getMaSV().equals(maSV)) {
                return hienTai; // Tìm thấy
            }
            hienTai = hienTai.getNext();
        }
        return null; // Không tìm thấy
    }

    /**
     * 5. Xóa một sinh viên khỏi danh sách theo Mã SV
     */
    public void xoaSinhVien(String maSV) {
        Node nodeCanXoa = timKiem(maSV);

        if (nodeCanXoa == null) {
            System.out.println("Khong tim thay sinh vien voi ma: " + maSV);
            return;
        }

        // --- Thuật toán xóa nút trong Danh sách liên kết kép ---

        // Trường hợp 1: Nút cần xóa là head
        if (nodeCanXoa == head) {
            head = nodeCanXoa.getNext();
            if (head != null) {
                head.setPrev(null); // Nút head mới không có prev
            } else {
                tail = null; // Nếu danh sách chỉ có 1 phần tử
            }
        }
        // Trường hợp 2: Nút cần xóa là tail
        else if (nodeCanXoa == tail) {
            tail = nodeCanXoa.getPrev();
            tail.setNext(null); // Nút tail mới không có next
        }
        // Trường hợp 3: Nút cần xóa nằm ở giữa
        else {
            Node prevNode = nodeCanXoa.getPrev();
            Node nextNode = nodeCanXoa.getNext();
            
            prevNode.setNext(nextNode); // Cập nhật liên kết next của nút trước
            nextNode.setPrev(prevNode); // Cập nhật liên kết prev của nút sau
        }

        size--;
        System.out.println("Da xoa sinh vien: " + nodeCanXoa.getData().getHoTen());
    }
    
    /**
     * 6. Sửa điểm cho sinh viên theo Mã SV
     */
    public void suaDiem(String maSV, double diemMoi) {
        Node nodeCanSua = timKiem(maSV);
        if (nodeCanSua != null) {
            nodeCanSua.getData().setDiem(diemMoi);
            System.out.println("Da cap nhat diem cho: " + nodeCanSua.getData().getHoTen());
            System.out.println("Thong tin moi: " + nodeCanSua.getData().toString());
        } else {
            System.out.println("Khong tim thay sinh vien voi ma: " + maSV);
        }
    }
    
    // --- CÁC PHƯƠNG THỨC ĐỌC/GHI FILE ---

    /**
     * 7. Tải danh sách sinh viên từ file
     */
    public void taiDanhSachTuFile(String tenFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            System.out.println("Dang tai du lieu tu file " + tenFile + "...");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                
                if (parts.length == 3) {
                    try {
                        String maSV = parts[0].trim();
                        String hoTen = parts[1].trim();
                        double diem = Double.parseDouble(parts[2].trim());
                        
                        SinhVien sv = new SinhVien(maSV, hoTen, diem);
                        // Chúng ta cần một hàm thêm "im lặng" để không bị spam console khi tải
                        this.themSinhVienKhiTaiFile(sv); 
                        
                    } catch (NumberFormatException e) {
                        System.out.println("Loi dinh dang diem: " + line);
                    }
                }
            }
            System.out.println("Tai du lieu thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi doc file (file co the chua ton tai): " + e.getMessage());
        }
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
        size++;
    }

    /**
     * 8. Lưu danh sách sinh viên hiện tại vào file
     */
    public void luuDanhSachVaoFile(String tenFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
            System.out.println("Dang luu du lieu vao file " + tenFile + "...");
            Node hienTai = head;
            while (hienTai != null) {
                SinhVien sv = hienTai.getData();
                bw.write(sv.getMaSV() + "," + sv.getHoTen() + "," + sv.getDiem());
                bw.newLine();
                hienTai = hienTai.getNext();
            }
            System.out.println("Luu du lieu thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }
}