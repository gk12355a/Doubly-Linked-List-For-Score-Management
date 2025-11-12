#ifndef QUANLYSINHVIEN_H
#define QUANLYSINHVIEN_H

#include "structs.h" // Nạp các định nghĩa struct

// Cấu trúc chính quản lý danh sách
// Tương đương các biến 'head' và 'tail' trong lớp QuanLySinhVien
typedef struct {
    Node* head;
    Node* tail;
} QuanLySinhVien;

// --- KHAI BÁO CÁC HÀM (PROTOTYPES) ---

// Hàm khởi tạo (Constructor)
QuanLySinhVien* createQuanLySinhVien();

// Hàm thêm (có in thông báo)
void themSinhVien(QuanLySinhVien* qlsv, SinhVien sv);

// Hàm hiển thị
void hienThiDanhSach(QuanLySinhVien* qlsv);

// Các hàm nghiệp vụ
void sapXepTheoDiemToan(QuanLySinhVien* qlsv);
void sapXepTheoDTB(QuanLySinhVien* qlsv);
void timMaxMinDTB(QuanLySinhVien* qlsv);
void xuatDanhSachTheoXepLoai(QuanLySinhVien* qlsv, const char* xepLoai);

// Đọc/Ghi File
void taiDanhSachTuFile(QuanLySinhVien* qlsv, const char* tenFile, MonHoc cacMonHoc[]);
void luuDanhSachVaoFile(QuanLySinhVien* qlsv, const char* tenFile);

// Hàm dọn dẹp bộ nhớ (Destructor)
void giaiPhongDanhSach(QuanLySinhVien* qlsv);

#endif // QUANLYSINHVIEN_H