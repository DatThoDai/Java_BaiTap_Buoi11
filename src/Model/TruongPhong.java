package Model;

import java.util.List;

public class TruongPhong extends NhanVien {
    private int soNhanVien; 
    private List<NhanVienThuong> danhSachNhanVien;
    
    public TruongPhong() {
        super();
        this.luongMotNgay = 200;
        this.soNhanVien = 0;
    }
    
    public TruongPhong(String maNhanVien, String hoTen, String soDienThoai, int soNgayLamViec) {
        super(maNhanVien, hoTen, soDienThoai, soNgayLamViec);
        this.luongMotNgay = 200; 
        this.soNhanVien = 0;
    }

    @Override
    public double tinhLuongThang() {
        return luongMotNgay * soNgayLamViec + 100 * soNhanVien;
    }   

    public int getSoNhanVien() {
        return soNhanVien;
    }

    public void setSoNhanVien(int soNhanVien) {
        this.soNhanVien = soNhanVien;
    }
    
	public List<NhanVienThuong> getDanhSachNhanVien() {
		return danhSachNhanVien;
	}
	
	public void setDanhSachNhanVien(List<NhanVienThuong> danhSachNhanVien) {
		this.danhSachNhanVien = danhSachNhanVien;
	}

    @Override
    public String toString() {
		return String.format("| %-15s | %-20s | %-15s | %-15d | %-15.2f | %-15d |", maNhanVien, hoTen, soDienThoai,
				soNgayLamViec, luongMotNgay, soNhanVien);
    }
    
    @Override
    public void NhapThongTinNhanVien() {
        super.NhapThongTinNhanVien();
        nhapSoNgayLamViec();
    }
    
    public void LoaiBoNhanVien(NhanVienThuong nv) {
        if (danhSachNhanVien != null) {
            danhSachNhanVien.remove(nv);
        }
        this.soNhanVien = Math.max(0, this.soNhanVien - 1);
    }
}
