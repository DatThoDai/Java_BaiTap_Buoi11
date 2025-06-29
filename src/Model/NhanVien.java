package Model;

import java.util.Scanner;
import Tool.KiemTraDieuKien;

public abstract class NhanVien {
	protected static final Scanner scanner = new Scanner(System.in);
	protected static final KiemTraDieuKien kt = new KiemTraDieuKien();
	
	protected String maNhanVien;
	protected String hoTen;
	protected String soDienThoai;
	protected int soNgayLamViec;
	protected double luongMotNgay;
	protected int thangLamViec = 6;
	protected int namLamViec = 2025;

	public abstract double tinhLuongThang();

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public int getSoNgayLamViec() {
		return soNgayLamViec;
	}

	public void setSoNgayLamViec(int soNgayLamViec) {
		this.soNgayLamViec = soNgayLamViec;
	}

	public double getLuongMotNgay() {
		return luongMotNgay;
	}

	public void setLuongMotNgay(double luongMotNgay) {
		this.luongMotNgay = luongMotNgay;
	}

	public int getThangLamViec() {
		return thangLamViec;
	}

	public void setThangLamViec(int thangLamViec) {
		this.thangLamViec = thangLamViec;
	}

	public int getNamLamViec() {
		return namLamViec;
	}

	public void setNamLamViec(int namLamViec) {
		this.namLamViec = namLamViec;
	}

	public void NhapThongTinNhanVien() {
		nhapMaNhanVien();
		nhapHoTen();
		nhapSoDienThoai();
	}

	private void nhapMaNhanVien() {
		System.out.print("Nhap ma nhan vien: ");
		String maNhanVien = scanner.nextLine();
		while(true) {
			if(kt.KiemTraChieuDaiChuoi(maNhanVien, 8)) break;
			else maNhanVien = scanner.nextLine();
		}
		setMaNhanVien(maNhanVien);
	}

	private void nhapHoTen() {
		System.out.print("Nhap ho ten: ");
		String hoTen = scanner.nextLine();
		while(true) {
			if(kt.KiemTraChuoiRong(hoTen)) break;
			else hoTen = scanner.nextLine();
		}
		setHoTen(hoTen);
	}

	private void nhapSoDienThoai() {
		System.out.print("Nhap so dien thoai: ");
		String soDienThoai = scanner.nextLine();
		while(true) {
			if(kt.KiemTraChieuDaiChuoi(soDienThoai, 10) && kt.KiemTraChuoiSo(soDienThoai)) break;
			else soDienThoai = scanner.nextLine();
		}
		setSoDienThoai(soDienThoai);
	}

	protected void nhapSoNgayLamViec() {
		System.out.print("Nhap so ngay lam viec cua thang " + getThangLamViec() + " nam " + getNamLamViec() + ": ");
		int soNgayLamViec = Integer.parseInt(scanner.nextLine());
		while(true) {
			if(kt.KiemTraSoNgayLamViec(soNgayLamViec, getThangLamViec(), getNamLamViec())) break;
			else soNgayLamViec = Integer.parseInt(scanner.nextLine());
		}
		setSoNgayLamViec(soNgayLamViec);
	}

	public NhanVien() {
	}

	public NhanVien(String maNhanVien, String hoTen, String soDienThoai, int soNgayLamViec) {
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.soNgayLamViec = soNgayLamViec;
	}
	
	@Override
	public String toString() {
		return String.format("| %-15s | %-20s | %-15s | %-15d | %-15.2f |", maNhanVien, hoTen, soDienThoai,
				soNgayLamViec, luongMotNgay);
	}
	
	public void XuatThongTinNhanVien() {
		System.out.printf("%-10s | %-20s | %-10s | %-10d | %-10.2f | %-10.2f\n", 
			maNhanVien, hoTen, soDienThoai, soNgayLamViec, luongMotNgay, tinhLuongThang());
	}
	
	public String LayTenNhanVien() {
		String[] hoTen = getHoTen().split(" ");
		return hoTen[hoTen.length-1];
	}

}
