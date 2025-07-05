package Tool;

import java.util.List;
import Model.*;

public class OutputFormatter {
    
    private static final String LONG_SEPARATOR = "=============================================================================================";
    private static final String SHORT_SEPARATOR = "+----------------------+-----------------+-----------------+";
    private static final String MEDIUM_SEPARATOR = "+----------------------+-----------------+-----------------+----------+";
    
    public static void printNhanVienHeader() {
        System.out.println(LONG_SEPARATOR);
        System.out.printf("%-10s | %-20s | %-10s | %-10s | %-10s | %-10s\n", 
            "Ma So", "Ho Ten", "SDT", "So Ngay", "Luong CB", "Luong Thang");
        System.out.println(LONG_SEPARATOR);
    }
    
    public static void printNhanVienFooter() {
        System.out.println(LONG_SEPARATOR);
    }
    
    public static void printNhanVienChuaCoTPHeader() {
        System.out.println("\nDANH SACH NHAN VIEN CHUA CO TRUONG PHONG:");
        System.out.println(SHORT_SEPARATOR);
        System.out.println("| Ma Nhan Vien         | Ho Ten          | So Dien Thoai   |");
        System.out.println(SHORT_SEPARATOR);
    }
    
    public static void printNhanVienChuaCoTPRow(String maNV, String hoTen, String sdt) {
        System.out.printf("| %-20s | %-15s | %-15s |\n", maNV, hoTen, sdt);
    }
    
    public static void printNhanVienChuaCoTPFooter() {
        System.out.println(SHORT_SEPARATOR);
    }
    
    public static void printTruongPhongHeader() {
        System.out.println("\nDANH SACH TRUONG PHONG:");
        System.out.println(MEDIUM_SEPARATOR);
        System.out.println("| Ma Nhan Vien         | Ho Ten          | So Dien Thoai   | So NV    |");
        System.out.println(MEDIUM_SEPARATOR);
    }
    
    public static void printTruongPhongRow(String maNV, String hoTen, String sdt, int soNV) {
        System.out.printf("| %-20s | %-15s | %-15s | %-8d |\n", maNV, hoTen, sdt, soNV);
    }
    
    public static void printTruongPhongFooter() {
        System.out.println(MEDIUM_SEPARATOR);
    }
    
    public static void printThuNhapGiamDocHeader() {
        System.out.println("\nTONG THU NHAP TUNG GIAM DOC:");
        System.out.println(LONG_SEPARATOR);
        System.out.printf("%-10s | %-20s | %-12s | %-12s | %-12s | %-12s\n", 
            "Ma So", "Ho Ten", "SDT", "Luong Thang", "Co Phan(%)", "Thu Nhap");
        System.out.println(LONG_SEPARATOR);
    }
    
    public static void printThuNhapGiamDocRow(String maNV, String hoTen, String sdt, 
                                              double luongThang, double coPhan, double thuNhap) {
        System.out.printf("%-10s | %-20s | %-12s | %-12.2f | %-12.2f | %-12.2f\n", 
            maNV, hoTen, sdt, luongThang, coPhan, thuNhap);
    }
    public static void printThuNhapGiamDocFooter() {
        System.out.println(LONG_SEPARATOR);
    }
    
    public static void printCongTyHeader() {
        System.out.println("\nTHONG TIN CONG TY VUA NHAP LA:");
        System.out.println(SHORT_SEPARATOR);
        System.out.println("| Ten Cong Ty          | Ma So Thue      | Doanh Thu Thang |");
        System.out.println(SHORT_SEPARATOR);
    }
    
    public static void printCongTyRow(String tenCT, String maSoThue, double doanhThu) {
        System.out.printf("| %-20s | %-15s | %-15.2f |\n", tenCT, maSoThue, doanhThu);
    }
    
    public static void printCongTyFooter() {
        System.out.println(SHORT_SEPARATOR);
    }
    
    public static void printDanhSachNhanVien(List<NhanVien> dsNhanVien, String title) {
        if (dsNhanVien == null || dsNhanVien.isEmpty()) {
            System.out.println("Danh sach nhan vien trong!");
            return;
        }
        
        System.out.println("\n" + title.toUpperCase());
        printNhanVienHeader();
        
        for (NhanVien nv : dsNhanVien) {
            nv.XuatThongTinNhanVien();
        }
        
        printNhanVienFooter();
    }
}
