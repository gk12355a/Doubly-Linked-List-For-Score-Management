#ifndef STRUCTS_H
#define STRUCTS_H

// Định nghĩa các hằng số về độ dài chuỗi
#define MA_MON_LEN 10
#define TEN_MON_LEN 50
#define MA_SV_LEN 15
#define HO_TEN_LEN 50
#define SO_MON_HOC 4

// Tương đương lớp MonHoc
typedef struct {
    char maMonHoc[MA_MON_LEN];
    char tenMon[TEN_MON_LEN];
    int soTinChi;
} MonHoc;

// Tương đương lớp Diem
typedef struct {
    MonHoc monHoc; // Nhúng trực tiếp struct MonHoc
    double diemSo;
} Diem;

// Tương đương lớp SinhVien
typedef struct {
    char maSV[MA_SV_LEN];
    char hoTen[HO_TEN_LEN];
    Diem danhSachDiem[SO_MON_HOC]; // Mảng 4 điểm
} SinhVien;

// Tương đương lớp Node
// Cần khai báo 'typedef struct Node Node' để có thể tự tham chiếu
typedef struct Node Node;
struct Node {
    SinhVien data; // Nhúng trực tiếp struct SinhVien
    Node* prev;
    Node* next;
};

#endif // STRUCTS_H