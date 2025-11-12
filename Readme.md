# Dự án: Quản lý Điểm Sinh viên (Đề tài 18)

## Giới thiệu

Dự án này là một ứng dụng **Java console**, được xây dựng để thực hiện **Đề tài 18: Ứng dụng danh sách liên kết kép vào quản lý điểm của sinh viên**.  

Chương trình sử dụng cấu trúc dữ liệu **Danh sách liên kết kép (Doubly Linked List)** để quản lý danh sách sinh viên.  
Mỗi sinh viên bao gồm thông tin cá nhân và điểm của 4 môn học cố định: **Toán, Lý, Anh, Cấu trúc dữ liệu & Giải thuật (CTDL)**.  

Chương trình có khả năng:
- Tải dữ liệu sinh viên từ file `diem_sinhvien_dt18.txt` khi khởi động.  
- Lưu lại toàn bộ danh sách vào file khi thoát chương trình.  

---

## Tính năng chính

Chương trình đáp ứng các yêu cầu của **Đề tài 18**:

1. **Nhập liệu (18.1):**  
   Nhập thêm sinh viên mới vào danh sách. Quá trình nhập dừng khi người dùng nhập dấu `$` ở tên sinh viên.

2. **Sắp xếp theo điểm Toán (18.2):**  
   Sắp xếp danh sách sinh viên tăng dần theo điểm môn Toán.

3. **Sắp xếp theo Điểm Trung Bình (18.3):**  
   Sắp xếp danh sách sinh viên theo chiều tăng dần của điểm trung bình.

4. **Tìm kiếm Max/Min (18.4):**  
   Tìm và hiển thị sinh viên có ĐTB cao nhất và thấp nhất.

5. **Lọc học lực (18.5):**  
   Xuất danh sách sinh viên đạt học lực A, B, C, D, hoặc F (theo ĐTB).

6. **Lưu trữ dữ liệu:**  
   Tự động đọc file `diem_sinhvien_dt18.txt` khi mở và lưu danh sách khi thoát.

---

## Cấu trúc dự án

```
TenDuAn/
├── src/
│   ├── Main.java
│   ├── Node.java
│   ├── SinhVien.java
│   ├── Diem.java
│   ├── MonHoc.java
│   └── QuanLySinhVien.java
└── diem_sinhvien_dt18.txt
```

### Mô tả các tệp

- **MonHoc.java:**  
  Lớp định nghĩa thông tin môn học (Mã môn, Tên môn, Số tín chỉ).

- **Diem.java:**  
  Lớp liên kết một đối tượng `MonHoc` với điểm số của sinh viên.

- **SinhVien.java:**  
  Lớp chứa thông tin cá nhân (Mã SV, Họ tên) và mảng `Diem[]` (4 môn).  
  Cung cấp phương thức `tinhDiemTrungBinh()` và `getXepLoai()`.

- **Node.java:**  
  Lớp nút của danh sách liên kết kép, chứa đối tượng `SinhVien` cùng hai con trỏ `prev`, `next`.

- **QuanLySinhVien.java:**  
  Lớp xử lý nghiệp vụ chính, quản lý danh sách sinh viên, các thao tác thêm, sắp xếp, tìm kiếm, lọc và đọc/ghi file.

- **Main.java:**  
  Lớp chứa hàm `main()` để chạy chương trình, hiển thị menu, nhận lệnh từ người dùng và định nghĩa 4 môn học cố định.

- **diem_sinhvien_dt18.txt:**  
  File dữ liệu văn bản để lưu danh sách sinh viên.

---

## Định dạng file dữ liệu

**Tên file:** `diem_sinhvien_dt18.txt`  
**Định dạng:** CSV (phân tách bằng dấu phẩy)

**Cấu trúc dòng:**  
`MaSV,HoTen,DiemToan,DiemLy,DiemAnh,DiemCTDL`

**Ví dụ:**
```
K15.101,Nguyen Van An,8.5,7.0,6.5,9.0
K15.102,Tran Thi Binh,5.0,6.5,7.0,4.0
```

**Lưu ý:**  
Thứ tự các điểm phải khớp với mảng `CAC_MON_HOC` trong `Main.java`.

---

## Yêu cầu hệ thống

- Java Development Kit (**JDK**) phiên bản **8** trở lên.

---

## Hướng dẫn chạy chương trình

### Cách 1: Chạy bằng Terminal (khuyên dùng)

1. **Tổ chức thư mục:**  
   Đảm bảo tất cả 6 file `.java` và file `diem_sinhvien_dt18.txt` nằm đúng như cấu trúc trên.

2. **Mở Terminal hoặc Command Prompt** và điều hướng đến thư mục dự án:  
   ```
   cd /duong/dan/den/thu/muc/TenDuAn/src
   ```

3. **Biên dịch mã nguồn:**  
   ```
   javac *.java
   ```

4. **Chạy chương trình:**  
   ```
   java Main
   ```

Chương trình sẽ tự động tải dữ liệu từ file `diem_sinhvien_dt18.txt` và hiển thị menu chính.

---

### Cách 2: Chạy bằng IDE (VS Code, IntelliJ IDEA, Eclipse)

1. Tạo một dự án Java mới trong IDE.  
2. Sao chép 6 file `.java` vào thư mục `src` của dự án.  
3. Sao chép file `diem_sinhvien_dt18.txt` vào thư mục gốc (cùng cấp với `src`).  
4. Cấu trúc thư mục trong IDE:

```
TenDuAn/
├── src/
│   ├── Main.java
│   ├── Node.java
│   ├── SinhVien.java
│   ├── Diem.java
│   ├── MonHoc.java
│   └── QuanLySinhVien.java
└── diem_sinhvien_dt18.txt
```

5. Mở `Main.java`, chọn **Run** hoặc **Chạy chương trình**.

---

## Ghi chú

- Chương trình được thiết kế để dễ mở rộng, có thể thêm nhiều môn học hoặc thay đổi tiêu chí xếp loại.  
- Nên đảm bảo file dữ liệu không bị sai định dạng (sai dấu phẩy, thiếu cột...).  
- Mọi thay đổi trong danh sách sinh viên sẽ được lưu lại tự động khi thoát chương trình.
