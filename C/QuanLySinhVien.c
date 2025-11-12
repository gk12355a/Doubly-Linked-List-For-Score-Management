#include "QuanLySinhVien.h"
#include <stdio.h>
#include <stdlib.h> // Cho malloc, free
#include <string.h> // Cho strcmp, strcpy

// --- CÁC HÀM NỘI BỘ (HELPER) (Tương đương hàm private) ---

// (Hàm này không cần lộ ra ở file .h, nên ta khai báo static)
static double tinhDiemTrungBinh(SinhVien sv) {
    double tongDiemNhanTinChi = 0;
    int tongTinChi = 0;
    for (int i = 0; i < SO_MON_HOC; i++) {
        tongDiemNhanTinChi += sv.danhSachDiem[i].diemSo * sv.danhSachDiem[i].monHoc.soTinChi;
        tongTinChi += sv.danhSachDiem[i].monHoc.soTinChi;
    }
    if (tongTinChi == 0) return 0;
    return tongDiemNhanTinChi / tongTinChi;
}

// Hàm helper lấy điểm môn theo mã
static double getDiemMon(SinhVien sv, const char* maMonHoc) {
    for (int i = 0; i < SO_MON_HOC; i++) {
        if (strcmp(sv.danhSachDiem[i].monHoc.maMonHoc, maMonHoc) == 0) {
            return sv.danhSachDiem[i].diemSo;
        }
    }
    return -1;
}

// Hàm helper xếp loại
static const char* getXepLoai(double dtb) {
    if (dtb >= 8.5) return "A";
    if (dtb >= 7.0) return "B";
    if (dtb >= 5.5) return "C";
    if (dtb >= 4.0) return "D";
    return "F";
}

// Hàm thêm "im lặng" để tải file
static void themSinhVienKhiTaiFile(QuanLySinhVien* qlsv, SinhVien sv) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    if (newNode == NULL) {
        printf("Loi cap phat bo nho!\n");
        return;
    }
    newNode->data = sv; // Sao chép struct SinhVien vào data
    newNode->prev = NULL;
    newNode->next = NULL;

    if (qlsv->head == NULL) {
        qlsv->head = newNode;
        qlsv->tail = newNode;
    } else {
        qlsv->tail->next = newNode;
        newNode->prev = qlsv->tail;
        qlsv->tail = newNode;
    }
}

// --- TRIỂN KHAI CÁC HÀM CÔNG KHAI ---

QuanLySinhVien* createQuanLySinhVien() {
    QuanLySinhVien* qlsv = (QuanLySinhVien*)malloc(sizeof(QuanLySinhVien));
    if (qlsv != NULL) {
        qlsv->head = NULL;
        qlsv->tail = NULL;
    }
    return qlsv;
}

void themSinhVien(QuanLySinhVien* qlsv, SinhVien sv) {
    themSinhVienKhiTaiFile(qlsv, sv); // Tái sử dụng hàm "im lặng"
    printf("=> Da them sinh vien: %s\n", sv.hoTen);
}

void hienThiDanhSach(QuanLySinhVien* qlsv) {
    if (qlsv->head == NULL) {
        printf("Danh sach rong.\n");
        return;
    }
    printf("--- DANH SACH SINH VIEN ---\n");
    Node* hienTai = qlsv->head;
    int stt = 1;
    while (hienTai != NULL) {
        SinhVien sv = hienTai->data;
        double dtb = tinhDiemTrungBinh(sv);
        const char* xl = getXepLoai(dtb);

        // Định dạng output giống Java
        printf("%d. Ma SV: %-8s | Ten: %-20s | DTB: %.2f (%s)",
               stt, sv.maSV, sv.hoTen, dtb, xl);

        // In điểm
        printf(" | Diem: [");
        for (int i = 0; i < SO_MON_HOC; i++) {
            printf("%s: %.1f", sv.danhSachDiem[i].monHoc.maMonHoc, sv.danhSachDiem[i].diemSo);
            if (i < SO_MON_HOC - 1) printf(", ");
        }
        printf("]\n");

        hienTai = hienTai->next;
        stt++;
    }
    printf("----------------------------\n");
}

void sapXepTheoDiemToan(QuanLySinhVien* qlsv) {
    if (qlsv->head == NULL || qlsv->head->next == NULL) return;

    int daSapXep;
    do {
        daSapXep = 0;
        Node* hienTai = qlsv->head;
        while (hienTai->next != NULL) {
            double diem1 = getDiemMon(hienTai->data, "TOAN");
            double diem2 = getDiemMon(hienTai->next->data, "TOAN");

            if (diem1 > diem2) {
                // Hoán đổi data (struct SinhVien)
                SinhVien temp = hienTai->data;
                hienTai->data = hienTai->next->data;
                hienTai->next->data = temp;
                daSapXep = 1;
            }
            hienTai = hienTai->next;
        }
    } while (daSapXep);
    printf("Da sap xep tang dan theo diem Toan.\n");
}

void sapXepTheoDTB(QuanLySinhVien* qlsv) {
     if (qlsv->head == NULL || qlsv->head->next == NULL) return;

    int daSapXep;
    do {
        daSapXep = 0;
        Node* hienTai = qlsv->head;
        while (hienTai->next != NULL) {
            double dtb1 = tinhDiemTrungBinh(hienTai->data);
            double dtb2 = tinhDiemTrungBinh(hienTai->next->data);

            if (dtb1 > dtb2) {
                SinhVien temp = hienTai->data;
                hienTai->data = hienTai->next->data;
                hienTai->next->data = temp;
                daSapXep = 1;
            }
            hienTai = hienTai->next;
        }
    } while (daSapXep);
    printf("Da sap xep tang dan theo Diem Trung Binh.\n");
}

void timMaxMinDTB(QuanLySinhVien* qlsv) {
    if (qlsv->head == NULL) {
        printf("Danh sach rong.\n");
        return;
    }
    Node* nodeMax = qlsv->head;
    Node* nodeMin = qlsv->head;
    double dtbMax = tinhDiemTrungBinh(qlsv->head->data);
    double dtbMin = tinhDiemTrungBinh(qlsv->head->data);

    Node* hienTai = qlsv->head->next;
    while (hienTai != NULL) {
        double dtbHienTai = tinhDiemTrungBinh(hienTai->data);
        if (dtbHienTai > dtbMax) {
            dtbMax = dtbHienTai;
            nodeMax = hienTai;
        }
        if (dtbHienTai < dtbMin) {
            dtbMin = dtbHienTai;
            nodeMin = hienTai;
        }
        hienTai = hienTai->next;
    }

    printf("--- SINH VIEN DTB MAX/MIN ---\n");
    printf("SV co DTB Cao nhat (Max): MaSV: %s, Ten: %s, DTB: %.2f\n",
           nodeMax->data.maSV, nodeMax->data.hoTen, dtbMax);
    printf("SV co DTB Thap nhat (Min): MaSV: %s, Ten: %s, DTB: %.2f\n",
           nodeMin->data.maSV, nodeMin->data.hoTen, dtbMin);
    printf("-----------------------------\n");
}

void xuatDanhSachTheoXepLoai(QuanLySinhVien* qlsv, const char* xepLoai) {
    if (qlsv->head == NULL) {
        printf("Danh sach rong.\n");
        return;
    }
    printf("--- DANH SACH SINH VIEN XEP LOAI: %s ---\n", xepLoai);
    Node* hienTai = qlsv->head;
    int stt = 1;
    int timThay = 0;
    while (hienTai != NULL) {
        double dtb = tinhDiemTrungBinh(hienTai->data);
        const char* xl = getXepLoai(dtb);
        if (strcmp(xl, xepLoai) == 0) {
            // (Bạn có thể gọi lại hàm in đầy đủ, nhưng in vắn tắt cũng được)
            printf("%d. Ma SV: %s, Ten: %s, DTB: %.2f\n",
                   stt, hienTai->data.maSV, hienTai->data.hoTen, dtb);
            stt++;
            timThay = 1;
        }
        hienTai = hienTai->next;
    }
    if (!timThay) {
        printf("Khong tim thay sinh vien nao dat xep loai %s\n", xepLoai);
    }
    printf("------------------------------------\n");
}

void taiDanhSachTuFile(QuanLySinhVien* qlsv, const char* tenFile, MonHoc cacMonHoc[]) {
    FILE* f = fopen(tenFile, "r");
    if (f == NULL) {
        printf("Loi khi doc file (file co the chua ton tai).\n");
        return;
    }

    char line[256];
    printf("Dang tai du lieu tu file %s...\n", tenFile);
    while (fgets(line, sizeof(line), f)) {
        SinhVien sv;
        double dToan, dLy, dAnh, dCTDL;

        // Dùng sscanf để phân tích dòng, giống định dạng file
        int items = sscanf(line, "%[^,],%[^,],%lf,%lf,%lf,%lf",
                           sv.maSV, sv.hoTen, &dToan, &dLy, &dAnh, &dCTDL);

        if (items == 6) {
            // Gán điểm và môn học vào struct SinhVien
            sv.danhSachDiem[0] = (Diem){cacMonHoc[0], dToan};
            sv.danhSachDiem[1] = (Diem){cacMonHoc[1], dLy};
            sv.danhSachDiem[2] = (Diem){cacMonHoc[2], dAnh};
            sv.danhSachDiem[3] = (Diem){cacMonHoc[3], dCTDL};

            themSinhVienKhiTaiFile(qlsv, sv);
        }
    }
    fclose(f);
    printf("Tai du lieu thanh cong!\n");
}

void luuDanhSachVaoFile(QuanLySinhVien* qlsv, const char* tenFile) {
    FILE* f = fopen(tenFile, "w");
    if (f == NULL) {
        printf("Loi khi ghi file!\n");
        return;
    }

    printf("Dang luu du lieu vao file %s...\n", tenFile);
    Node* hienTai = qlsv->head;
    while (hienTai != NULL) {
        SinhVien sv = hienTai->data;
        // Ghi theo đúng định dạng
        fprintf(f, "%s,%s,%.1f,%.1f,%.1f,%.1f\n",
                sv.maSV,
                sv.hoTen,
                getDiemMon(sv, "TOAN"),
                getDiemMon(sv, "LY"),
                getDiemMon(sv, "ANH"),
                getDiemMon(sv, "CTDL"));
        hienTai = hienTai->next;
    }
    fclose(f);
    printf("Luu du lieu thanh cong!\n");
}

void giaiPhongDanhSach(QuanLySinhVien* qlsv) {
    Node* hienTai = qlsv->head;
    while (hienTai != NULL) {
        Node* temp = hienTai;
        hienTai = hienTai->next;
        free(temp); // Giải phóng từng Node
    }
    free(qlsv); // Giải phóng chính cấu trúc QuanLySinhVien
    printf("Da giai phong bo nho.\n");
}