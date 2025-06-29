package Model;

import java.util.Scanner;

public class Main {
    private static CongTy congTy;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        congTy = new CongTy();
         int luaChon;
//        taodulieu();
        do {
            showMenu();
            luaChon = Integer.parseInt(scanner.nextLine());
            switch (luaChon) {
                case 1:
                    System.out.println("NHAP THONG TIN CONG TY");
                    congTy.NhapThongTinCongTy();
                    System.out.println("THONG TIN CONG TY VUA NHAP LA");
                    congTy.HienThiThongTinCongTy();
                    break;
                case 2:
                    System.out.println("PHAN BO NHAN VIEN VAO TRUONG PHONG");
                    congTy.PhanBoNhanVienVaoTruongPhong();
                    break;
                case 3:
                    System.out.println("THEM NHAN SU");
                    congTy.ThemMotNhanSu();
                    break;
                case 4:
                    System.out.println("XOA NHAN SU");
                    congTy.XoaNhanSu();
                    break;
                case 5:
                    congTy.XuatThongTinToanNhanVien();
                    break;
                case 6:
                    System.out.println("TONG LUONG TOAN CONG TY: " + congTy.TongLuongToanCongTy());
                    break;
                case 7:
                    congTy.NhanVienThuongCoLuongCaoNhat();
                    break;
                case 8:
                    congTy.TruongPhongCoNhieuNhanVienNhat();
                    break;
                case 9:
                    congTy.SapXepNhanVienTheoTen();
                    break;
                case 10:
                    congTy.SapXepNhanVienGiamDanTheoLuong();
                    break;
                case 11:
                    congTy.GiamDocCoCoPhanNhieuNhat();
                    break;
                case 12:
                    congTy.TongThuNhapTungGiamDoc();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    break;
                default:
                    System.out.println("Can nhap dung chuc nang, nhap lai:");
                    break;
            }
        } while (luaChon != 0);
    }

    public static void showMenu() {
        System.out.println("\n==========MENU==========");
        System.out.println("1. Nhap thong tin cong ty.");
        System.out.println("2. Phan bo nhan vien vao truong phong.");
        System.out.println("3. Them nhan su.");
        System.out.println("4. Xoa nhan su.");
        System.out.println("5. Xuat thong tin toan bo nguoi trong cong ty.");
        System.out.println("6. Tinh va xuat tong luong cho toan cong ty.");
        System.out.println("7. Tim nhan vien thuong co luong cao nhat.");
        System.out.println("8. Tim truong phong co so luong nhan vien duoi quyen nhieu nhat.");
        System.out.println("9. Sap xep nhan vien toan cong ty theo ten.");
        System.out.println("10. Sap xep nhan vien toan cong ty theo luong giam dan.");
        System.out.println("11. Tim giam doc co co phan nhieu nhat.");
        System.out.println("12. Tinh tong thu nhap tung giam doc.");
        System.out.println("0. Thoat chuong trinh.");
        System.out.print("Nhap lua chon: ");
    }
    
    public static void taodulieu() {
        congTy.setTenCongTy("Cong Ty TNHH ABC");
        congTy.setMaSoThue("1234567890");
        congTy.setDoanhThuThang(50000000);
        
        GiamDoc giamDoc1 = new GiamDoc("GD001", "Nguyen Van An", "0901234567", 22, 15.5);
        GiamDoc giamDoc2 = new GiamDoc("GD002", "Tran Thi Binh", "0902345678", 20, 10.0);
        
        TruongPhong truongPhong1 = new TruongPhong("TP001", "Le Van Cuong", "0903456789", 21);
        TruongPhong truongPhong2 = new TruongPhong("TP002", "Pham Thi Dung", "0904567890", 19);
        TruongPhong truongPhong3 = new TruongPhong("TP003", "Hoang Van Em", "0905678901", 20);
        
        NhanVienThuong nhanVien1 = new NhanVienThuong("NV001", "Vo Van Phuc", "0906789012", 22, null);
        NhanVienThuong nhanVien2 = new NhanVienThuong("NV002", "Bui Thi Giang", "0907890123", 22, null);
        NhanVienThuong nhanVien3 = new NhanVienThuong("NV003", "Do Van Hung", "0908901234", 20, null);
        NhanVienThuong nhanVien4 = new NhanVienThuong("NV004", "Ly Thi Ich", "0909012345", 19, null);
        NhanVienThuong nhanVien5 = new NhanVienThuong("NV005", "Vu Van Khanh", "0910123456", 21, null);
        
        NhanVienThuong nhanVien6 = new NhanVienThuong("NV006", "Mai Thi Lan", "0911234567", 20, null);
        NhanVienThuong nhanVien7 = new NhanVienThuong("NV007", "Dao Van Minh", "0912345678", 18, null);
        nhanVien6.setMaTruongPhong("TP001");
        nhanVien7.setMaTruongPhong("TP001");
        
        truongPhong1.setSoNhanVien(2);
        
        congTy.themNhanVien(giamDoc1);
        congTy.themNhanVien(giamDoc2);
        congTy.themNhanVien(truongPhong1);
        congTy.themNhanVien(truongPhong2);
        congTy.themNhanVien(truongPhong3);
        congTy.themNhanVien(nhanVien1);
        congTy.themNhanVien(nhanVien2);
        congTy.themNhanVien(nhanVien3);
        congTy.themNhanVien(nhanVien4);
        congTy.themNhanVien(nhanVien5);
        congTy.themNhanVien(nhanVien6);
        congTy.themNhanVien(nhanVien7);
        
        System.out.println("Da tao thanh cong " + congTy.getDsNhanVien().size() + " nhan vien.");
    }
    
}