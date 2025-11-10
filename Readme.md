```markdown
Dự án: Quản lý Điểm Sinh viên bằng Danh sách liên kết kép

Đây là một dự án Java console đơn giản, ứng dụng cấu trúc dữ liệu Danh sách liên kết kép (Doubly Linked List) để xây dựng chương trình quản lý điểm sinh viên.

Chương trình cho phép người dùng thực hiện các thao tác CRUD cơ bản (Thêm, Đọc, Sửa, Xóa) trên danh sách sinh viên. Toàn bộ dữ liệu được tự động tải lên từ file sinhvien.txt khi khởi động và được lưu lại khi thoát chương trình.

Tính năng chính

Thêm sinh viên mới vào cuối danh sách.

Hiển thị toàn bộ danh sách sinh viên (hỗ trợ duyệt xuôi và duyệt ngược).

Tìm kiếm sinh viên theo Mã Sinh viên.

Sửa điểm của sinh viên dựa trên Mã Sinh viên.

Xóa một sinh viên khỏi danh sách dựa trên Mã Sinh viên.

Tải dữ liệu (Load): Tự động đọc file sinhvien.txt và nạp vào danh sách liên kết khi chương trình bắt đầu.

Lưu dữ liệu (Save): Tự động ghi lại toàn bộ danh sách liên kết vào file sinhvien.txt khi người dùng chọn "Thoát".

Cấu trúc Dự án

Dự án này bao gồm 5 tệp cốt lõi:

SinhVien.java: Lớp mô hình (model) chứa thông tin của một sinh viên (Mã SV, Họ tên, Điểm).

Node.java: Lớp đại diện cho một nút (mắt xích) trong danh sách liên kết kép. Mỗi nút chứa một đối tượng SinhVien và hai con trỏ prev (trước) và next (sau).

QuanLyDiem.java: Lớp chính chứa logic của Danh sách liên kết kép. Nó quản lý head, tail và triển khai tất cả các thuật toán (thêm, xóa, sửa, tìm kiếm, đọc/ghi file).

Main.java: Lớp thực thi chứa hàm main(). Lớp này hiển thị menu tương tác cho người dùng và gọi các phương thức từ QuanLyDiem.

sinhvien.txt: File dữ liệu văn bản dùng để lưu trữ và tải danh sách sinh viên.

Yêu cầu

Java Development Kit (JDK) phiên bản 8 trở lên.

Hướng dẫn chạy chương trình

Có hai cách phổ biến để chạy dự án này:

1. Chạy bằng Terminal (Khuyên dùng)

Đây là cách chạy trực tiếp và đơn giản nhất, không cần IDE.

Tổ chức file: Đảm bảo cả 4 file .java (SinhVien.java, Node.java, QuanLyDiem.java, Main.java) và file sinhvien.txt đều nằm chung trong một thư mục.

Mở Terminal: Mở Terminal (trên macOS/Linux) hoặc Command Prompt (trên Windows) và điều hướng đến thư mục chứa các file trên.

```bash
# Ví dụ:
cd /duong/dan/den/thu/muc/du/an
```

Biên dịch: Biên dịch tất cả các file Java. Lệnh *.java sẽ biên dịch tất cả các file có đuôi .java trong thư mục.

```bash
javac *.java
```

Thực thi: Sau khi biên dịch thành công (sẽ xuất hiện các file .class), bạn chạy file Main (file chứa hàm main()).

```bash
java Main
```

Chương trình sẽ khởi động, tải dữ liệu từ sinhvien.txt và hiển thị menu cho bạn.

2. Chạy bằng IDE (Ví dụ: VS Code, IntelliJ, Eclipse)

Tạo một dự án Java mới trong IDE của bạn.

Sao chép 4 file .java và dán vào thư mục src (hoặc thư mục mã nguồn chính) của dự án.

Quan trọng: Sao chép file sinhvien.txt và dán vào thư mục gốc (root) của dự án (thư mục cha của src).

Cấu trúc thư mục trong IDE nên trông giống như sau:

```
TenDuAn/
├── src/
│   ├── Main.java
│   ├── Node.java
│   ├── QuanLyDiem.java
│   └── SinhVien.java
└── sinhvien.txt  <-- Đặt file .txt ở đây
```

Mở file Main.java, nhấn chuột phải và chọn "Run" (hoặc "Chạy").

Định dạng File Dữ liệu (sinhvien.txt)

File dữ liệu sử dụng định dạng CSV (Các giá trị ngăn cách bởi dấu phẩy) đơn giản.

Cấu trúc: MaSV,HoTen,Diem

Ví dụ:

```
K15.001,Nguyen Van A,8.5
K15.002,Tran Thi B,9.0
K15.003,Le Van C,7.2
```
```