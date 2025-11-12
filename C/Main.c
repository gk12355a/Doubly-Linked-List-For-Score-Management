#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "QuanLySinhVien.h" // Nạp thư viện của chúng ta

// --- ĐỊNH NGHĨA CÁC MÔN HỌC (Biến toàn cục) ---
// (Khởi tạo các biến này là HỢP LỆ, vì chúng dùng hằng số)
MonHoc MON_TOAN = {"TOAN", "Toan", 3};
MonHoc MON_LY = {"LY", "Vat Ly", 3};
MonHoc MON_ANH = {"ANH", "Tieng Anh", 2};
MonHoc MON_CTDL = {"CTDL", "CTDL & GT", 3};

// KHÔNG khởi tạo mảng CAC_MON_HOC ở đây nữa
// const char* TEN_FILE = "diem_sinhvien_dt18.txt"; // Tốt hơn là di chuyển vào main
const char* TEN_FILE = "diem_sinhvien_dt18.txt";


// <<< THÊM THAM SỐ MonHoc cacMonHoc[]
void nhapDanhSach(QuanLySinhVien* qlsv, MonHoc cacMonHoc[]) {
    char inputBuffer[100]; // Bộ đệm để đọc
    printf("--- BAT DAU NHAP THEM SINH VIEN ---\n");
    while (1) {
        printf("Nhap Ho ten sinh vien (nhap '$' de dung): ");
        fgets(inputBuffer, sizeof(inputBuffer), stdin);
        inputBuffer[strcspn(inputBuffer, "\n")] = 0; 
        
        if (strcmp(inputBuffer, "$") == 0) {
            break;
        }

        SinhVien sv;
        strcpy(sv.hoTen, inputBuffer); 

        printf("Nhap Ma SV: ");
        fgets(inputBuffer, sizeof(inputBuffer), stdin);
        inputBuffer[strcspn(inputBuffer, "\n")] = 0;
        strcpy(sv.maSV, inputBuffer);

        // Lặp 4 môn để nhập điểm
        for (int i = 0; i < SO_MON_HOC; i++) {
            double diemSo = -1;
            while (diemSo < 0 || diemSo > 10) {
                // <<< SỬA LẠI ĐỂ DÙNG THAM SỐ
                printf("Nhap diem mon %s (%dTC): ", cacMonHoc[i].tenMon, cacMonHoc[i].soTinChi);
                fgets(inputBuffer, sizeof(inputBuffer), stdin);
                
                if (sscanf(inputBuffer, "%lf", &diemSo) != 1 || diemSo < 0 || diemSo > 10) {
                    printf("Diem khong hop le (0-10). Nhap lai.\n");
                    diemSo = -1; 
                }
            }
            // <<< SỬA LẠI ĐỂ DÙNG THAM SỐ
            sv.danhSachDiem[i].monHoc = cacMonHoc[i];
            sv.danhSachDiem[i].diemSo = diemSo;
        }

        themSinhVien(qlsv, sv); // Gọi hàm thêm
    }
    printf("--- KET THUC NHAP SINH VIEN ---\n");
}

// Hàm main
int main() {
    QuanLySinhVien* qlsv = createQuanLySinhVien();

    // --- DI CHUYỂN VÀO ĐÂY ---
    // Bây giờ đây là một biến *local* trong main, việc khởi tạo này là HỢP LỆ
    MonHoc CAC_MON_HOC[] = {MON_TOAN, MON_LY, MON_ANH, MON_CTDL};
    // -------------------------
    
    // Tải dữ liệu khi khởi động
    taiDanhSachTuFile(qlsv, TEN_FILE, CAC_MON_HOC);

    int luaChon = -1;
    char inputBuffer[10];

    do {
        printf("\n--- MENU QUAN LY DIEM (DE TAI 18 - C VERSION) ---\n");
        printf("1. Nhap them sinh vien moi (nhap $ de dung)\n");
        printf("2. Hien thi danh sach sinh vien\n");
        printf("3. Sap xep danh sach TANG DAN theo diem TOAN\n");
        printf("4. Sap xep danh sach TANG DAN theo DIEM TRUNG BINH\n");
        printf("5. Tim sinh vien co DTB Max / Min\n");
        printf("6. Xuat danh sach sinh vien theo xep loai (A, B, C, D, F)\n");
        printf("0. Thoat chuong trinh (Va Luu du lieu)\n");
        printf("Moi ban chon chuc nang: ");

        if (fgets(inputBuffer, sizeof(inputBuffer), stdin) == NULL) {
            luaChon = 0; 
        }
        if (sscanf(inputBuffer, "%d", &luaChon) != 1) {
            luaChon = -1; 
        }

        switch (luaChon) {
            case 1:
                // <<< TRUYỀN THAM SỐ VÀO HÀM
                nhapDanhSach(qlsv, CAC_MON_HOC);
                break;
            case 2:
                hienThiDanhSach(qlsv);
                break;
            case 3:
                sapXepTheoDiemToan(qlsv);
                hienThiDanhSach(qlsv); 
                break;
            case 4:
                sapXepTheoDTB(qlsv);
                hienThiDanhSach(qlsv); 
                break;
            case 5:
                timMaxMinDTB(qlsv);
                break;
            case 6: {
                char xepLoai[10];
                printf("Nhap xep loai can xem (A, B, C, D, F): ");
                fgets(xepLoai, sizeof(xepLoai), stdin);
                xepLoai[strcspn(xepLoai, "\n")] = 0; 
                xuatDanhSachTheoXepLoai(qlsv, xepLoai);
                break;
            }
            case 0:
                luuDanhSachVaoFile(qlsv, TEN_FILE);
                giaiPhongDanhSach(qlsv); 
                printf("Da thoat chuong trinh.\n");
                break;
            default:
                printf("Lua chon khong hop le. Vui long chon lai.\n");
        }

    } while (luaChon != 0);

    return 0;
}