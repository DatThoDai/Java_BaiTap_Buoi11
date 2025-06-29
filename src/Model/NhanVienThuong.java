package Model;

public class NhanVienThuong extends NhanVien{
	private String maTruongPhong = null;
	
	public NhanVienThuong() {
		super();
		this.luongMotNgay = 100;
		this.maTruongPhong = null;
	}
	
	public NhanVienThuong(String maNhanVien, String hoTen, String soDienThoai, int soNgayLamViec,
			String maTruongPhong) {
		super(maNhanVien, hoTen, soDienThoai, soNgayLamViec);
		this.luongMotNgay = 100;
        this.maTruongPhong = null;
	}

	@Override
    public double tinhLuongThang() {
        return luongMotNgay * soNgayLamViec;
    }

	public String getMaTruongPhong() {
		return maTruongPhong;
	}

	public void setMaTruongPhong(String maTruongPhong) {
		this.maTruongPhong = maTruongPhong;
	}
	
	
	@Override
	public String toString() {
		return String.format("| %-15s | %-20s | %-15s | %-15d | %-15.2f | %-15s |", maNhanVien, hoTen, soDienThoai,
				soNgayLamViec, luongMotNgay, maTruongPhong);
	}
	
	@Override
	public void NhapThongTinNhanVien() {
		super.NhapThongTinNhanVien();
		nhapSoNgayLamViec();
	}

}
