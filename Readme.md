Dự án: Quản lý Điểm Sinh viên (Đề tài 18)

Dự án này là một ứng dụng Java console, được xây dựng để thực hiện Đề tài 18: Ứng dụng danh sách liên kết kép vào quản lý điểm của sinh viên.

Chương trình sử dụng cấu trúc dữ liệu Danh sách liên kết kép (Doubly Linked List) để quản lý một danh sách sinh viên. Mỗi sinh viên được quản lý thông tin cá nhân và điểm của 4 môn học cố định (Toán, Lý, Anh, CTDL), mỗi môn có số tín chỉ riêng.

Chương trình hỗ trợ đầy đủ các thao tác theo yêu cầu đề tài, đồng thời có khả năng tải (load) dữ liệu từ file diem_sinhvien_dt18.txt khi khởi động và lưu (save) lại dữ liệu khi kết thúc.

Tính năng chính

Chương trình đáp ứng đầy đủ các yêu cầu của Đề tài 18:

* 18.1 (Nhập liệu): Nhập thêm sinh viên mới vào danh sách. Quá trình nhập dừng lại khi người dùng nhập dấu $ vào tên sinh viên.

* 18.2 (Sắp xếp): Sắp xếp danh sách sinh viên theo chiều tăng dần của điểm Toán.

* 18.3 (Sắp xếp): Sắp xếp danh sách sinh viên theo chiều tăng dần của Điểm Trung Bình (ĐTB).

* 18.4 (Tìm kiếm): Tìm và hiển thị sinh viên có ĐTB cao nhất (Max) và thấp nhất (Min).

* 18.5 (Lọc): Xuất danh sách các sinh viên đạt học lực A, B, C, D, hoặc F (dựa trên ĐTB).

* Lưu trữ: Tự động đọc file diem_sinhvien_dt18.txt khi mở và lưu lại toàn bộ danh sách hiện tại vào file khi thoát.

Cấu trúc Dự án

Dự án bao gồm 7 tệp (6 file .java và 1 file .txt):

1. MonHoc.java: Lớp cấu trúc, định nghĩa một môn học (Mã môn, Tên môn, Số tín chỉ).

2. Diem.java: Lớp cấu trúc, liên kết một đối tượng MonHoc với diemSo (điểm số) của sinh viên.

3. SinhVien.java: Lớp dữ liệu chính. Chứa thông tin cá nhân (Mã SV, Tên) và một mảng Diem[] (gồm 4 môn). Lớp này cũng chứa logic tính tinhDiemTrungBinh() và getXepLoai().

4. Node.java: Lớp triển khai "nút" (mắt xích) của danh sách liên kết kép. Mỗi Node chứa một đối tượng SinhVien và hai con trỏ prev, next.

5. QuanLySinhVien.java: Lớp logic cốt lõi. Triển khai cấu trúc danh sách liên kết kép (quản lý head, tail) và tất cả các thuật toán nghiệp vụ (Thêm, Sắp xếp 18.2, 18.3, Tìm Max/Min 18.4, Lọc 18.5) và logic Đọc/Ghi file.

6. Main.java: Lớp thực thi (entry-point) chứa hàm main(). Lớp này chịu trách nhiệm hiển thị menu, nhận input từ người dùng, và định nghĩa 4 đối tượng MonHoc cố định.

7. diem_sinhvien_dt18.txt: File dữ liệu văn bản dùng để lưu trữ và tải danh sách sinh viên.

Định dạng File Dữ liệu

* Tên file: diem_sinhvien_dt18.txt

* Định dạng: CSV (Các giá trị ngăn cách bởi dấu phẩy).

* Cấu trúc: MaSV,HoTen,DiemToan,DiemLy,DiemAnh,DiemCTDL

* Lưu ý quan trọng: Thứ tự của 4 cột điểm trong file .txt phải khớp chính xác với thứ tự của mảng CAC_MON_HOC được định nghĩa trong file Main.java.

Ví dụ:
K15.101,Nguyen Van An,8.5,7.0,6.5,9.0
K15.102,Tran Thi Binh,5.0,6.5,7.0,4.0
textYêu cầu

* Java Development Kit (JDK) phiên bản 8 trở lên.

Hướng dẫn chạy chương trình

Bạn có thể chạy dự án theo một trong hai cách sau:

1. Chạy bằng Terminal (Khuyên dùng)

Đây là cách chạy trực tiếp, không cần IDE.

1. Tổ chức file: Đảm bảo cả 6 file .java (Main.java, Node.java, SinhVien.java...) và file diem_sinhvien_dt18.txt đều nằm chung trong một thư mục.

2. Mở Terminal: Mở Terminal (trên macOS/Linux) hoặc Command Prompt (trên Windows) và điều hướng đến thư mục chứa các file trên.
Ví dụ:
cd /duong/dan/den/thu/muc/du/an
text3. Biên dịch (Compile): Biên dịch tất cả các file Java.
javac *.java
text4. Thực thi (Run): Sau khi biên dịch thành công (sẽ xuất hiện các file .class), bạn chạy file Main.
java Main
text5. Chương trình sẽ khởi động, tải dữ liệu từ diem_sinhvien_dt18.txt và hiển thị menu.

2. Chạy bằng IDE (Ví dụ: VS Code, IntelliJ, Eclipse)

1. Tạo một dự án Java mới trong IDE của bạn.

2. Sao chép 6 file .java và dán vào thư mục src (hoặc thư mục mã nguồn chính) của dự án.

3. Quan trọng: Sao chép file diem_sinhvien_dt18.txt và dán vào thư mục gốc (root) của dự án (thư mục cha của src).
Cấu trúc thư mục trong IDE của bạn nên trông giống như sau:
TenDuAn/
├── src/
│   ├── Main.java
│   ├── Node.java
│   ├── SinhVien.java
│   ├── Diem.java
│   ├── MonHoc.java
│   └── QuanLySinhVien.java
└── diem_sinhvien_dt18.txt  <-- Đặt file .txt ở đây
text4. Mở file Main.java, nhấn chuột phải và chọn "Run" (hoặc "Chạy").

Chúc mừng bạn đã hoàn thành dự án! Bạn có muốn tôi giúp tối ưu hóa bất kỳ thuật toán nào, v