package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Tool.KiemTraDieuKien;
import Tool.OutputFormatter;

public class CongTy {
	private static final Scanner scanner = new Scanner(System.in);
	private static final KiemTraDieuKien kt = new KiemTraDieuKien();
	private String tenCongTy;
	private String maSoThue;
	private double doanhThuThang;
	private List<NhanVien> dsNhanVien;

	// chuc nang 1 : Nhap thong tin cong ty
	private void NhapTenCongTy() {
		System.out.print("Nhap ten cong ty: ");
		do {
			this.tenCongTy = scanner.nextLine();
			if (this.tenCongTy.isEmpty()) {
				System.out.print("Ten cong ty khong duoc de trong. Vui long nhap lai: ");
			}
		} while (this.tenCongTy.isEmpty());
	}

	private void NhapMaSoThue() {
		System.out.print("Nhap ma so thue: ");
		do {
			this.maSoThue = scanner.nextLine();
			if (!kt.KiemTraChieuDaiChuoi(maSoThue, 10)) {
				System.out.println("Ma so thue phai co chieu dai 10 ky tu. Vui long nhap lai.");
			}
		} while (!kt.KiemTraChieuDaiChuoi(maSoThue, 10));
	}

	private void NhapDoanhThuThang() {
		System.out.print("Nhap doanh thu thang: ");
		do {
			this.doanhThuThang = Double.parseDouble(scanner.nextLine());
			if (!kt.KiemTraLaSoDuong(doanhThuThang)) {
				System.out.println("Doanh thu thang phai >= 0. Vui long nhap lai.");
			}
		} while (!kt.KiemTraLaSoDuong(doanhThuThang));
	}

	public void NhapThongTinCongTy() {
		NhapTenCongTy();
		NhapMaSoThue();
		NhapDoanhThuThang();
		System.out.println("Nhap thong tin cong ty thanh cong.");
	}

	// chuc nang 2 : Phan bo nhan vien vao truong phong
	public void PhanBoNhanVienVaoTruongPhong() {
		if (!CoNhanVienChuaCoTruongPhong()) {
			System.out.println("\nTat ca nhan vien da duoc phan bo truong phong!");
			return;
		}

		DSNhanVienChuaCoTruongPhong();
		System.out.print("\nNhap ma nhan vien thuong can phan bo: ");
		String maNVThuong = scanner.nextLine();

		NhanVienThuong nvtTimDuoc = (NhanVienThuong) TimNVThuongTheoMa(maNVThuong);
		if (nvtTimDuoc == null) {
			System.out.println("Khong tim thay nhan vien thuong voi ma " + maNVThuong);
			return;
		}

		if (nvtTimDuoc.getMaTruongPhong() != null) {
			System.out.println("Nhan vien nay da co truong phong quan ly!");
			return;
		}

		DanhSachTruongPhong();
		System.out.print("\nNhap ma truong phong can phan bo: ");
		String maTruongPhong = scanner.nextLine();

		TruongPhong truongPhongTimDuoc = (TruongPhong) TimTruongPhongTheoMa(maTruongPhong);
		if (truongPhongTimDuoc == null) {
			System.out.println("Khong tim thay truong phong voi ma " + maTruongPhong);
			return;
		}

		nvtTimDuoc.setMaTruongPhong(maTruongPhong);
		truongPhongTimDuoc.setSoNhanVien(truongPhongTimDuoc.getSoNhanVien() + 1);

		System.out.println("\nPhan bo thanh cong!");
		System.out.println("Nhan vien " + nvtTimDuoc.getHoTen() + " da duoc phan bo cho truong phong "
				+ truongPhongTimDuoc.getHoTen());
	}

	private NhanVienThuong TimNVThuongTheoMa(String maNV) {
		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof NhanVienThuong && nv.getMaNhanVien().equalsIgnoreCase(maNV)) {
				NhanVienThuong nvThuong = (NhanVienThuong) nv;
				if (nvThuong.getMaTruongPhong() == null) {
					return nvThuong;
				}
			}
		}
		return null;
	}

	private TruongPhong TimTruongPhongTheoMa(String maNV) {
		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof TruongPhong && nv.getMaNhanVien().equalsIgnoreCase(maNV)) {
				return (TruongPhong) nv;
			}
		}
		return null;
	}

	private void DSNhanVienChuaCoTruongPhong() {
		OutputFormatter.printNhanVienChuaCoTPHeader();

		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof NhanVienThuong && ((NhanVienThuong) nv).getMaTruongPhong() == null) {
				OutputFormatter.printNhanVienChuaCoTPRow(nv.getMaNhanVien(), nv.getHoTen(), nv.getSoDienThoai());
			}
		}

		OutputFormatter.printNhanVienChuaCoTPFooter();
	}

	private void DanhSachTruongPhong() {
		OutputFormatter.printTruongPhongHeader();

		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof TruongPhong) {
				TruongPhong tp = (TruongPhong) nv;
				OutputFormatter.printTruongPhongRow(tp.getMaNhanVien(), tp.getHoTen(), tp.getSoDienThoai(),
						tp.getSoNhanVien());
			}
		}

		OutputFormatter.printTruongPhongFooter();
	}

	private boolean CoNhanVienChuaCoTruongPhong() {
		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof NhanVienThuong && ((NhanVienThuong) nv).getMaTruongPhong() == null) {
				return true;
			}
		}
		return false;
	}

	public void themNhanVien(NhanVien nhanVien) {
		dsNhanVien.add(nhanVien);
	}

	// Chuc nang 3: them nhan su
	public void ThemMotNhanSu() {
		NhanVien nvThem;
		int luaChon;
		MenuLuaChonLoaiNhanVien();
		luaChon = Integer.parseInt(scanner.nextLine());
		switch (luaChon) {
		case 1:
			nvThem = new NhanVienThuong();
			((NhanVienThuong) nvThem).NhapThongTinNhanVien();
			if (KiemTraNhanVienTonTai(nvThem))
				System.out.println("Nhan vien co ma " + nvThem.getMaNhanVien() + " da ton tai.");
			else {
				dsNhanVien.add(nvThem);
				System.out.println("Da them nhan vien thuong thanh cong!");
			}
			break;
		case 2:
			nvThem = new TruongPhong();
			((TruongPhong) nvThem).NhapThongTinNhanVien();
			if (KiemTraNhanVienTonTai(nvThem))
				System.out.println("Nhan vien co ma " + nvThem.getMaNhanVien() + " da ton tai.");
			else {
				dsNhanVien.add(nvThem);
				System.out.println("Da them truong phong thanh cong!");
			}
			break;
		case 3:
			nvThem = new GiamDoc();
			((GiamDoc) nvThem).NhapThongTinNhanVien();
			if (KiemTraNhanVienTonTai(nvThem))
				System.out.println("Nhan vien co ma " + nvThem.getMaNhanVien() + " da ton tai.");
			else {
				dsNhanVien.add(nvThem);
				System.out.println("Da them giam doc thanh cong!");
			}
			break;
		default:
			System.out.println("Khong co loai nhan vien ban lua chon.");
			break;
		}
	}

	private void MenuLuaChonLoaiNhanVien() {
		System.out.println("Lua chon loai nhan vien muon them");
		System.out.println("1. Nhan vien thuong.");
		System.out.println("2. Truong phong.");
		System.out.println("3. Giam doc");
		System.out.print("Nhap lua chon: ");
	}

	private boolean KiemTraNhanVienTonTai(NhanVien nvKT) {
		for (NhanVien nv : dsNhanVien)
			if (nv.getMaNhanVien().equalsIgnoreCase(nvKT.getMaNhanVien()))
				return true;
		return false;
	}

	// Chuc nang 4: Xoa nhan su
	public void XoaNhanSu() {
		if (dsNhanVien == null || dsNhanVien.isEmpty()) {
			System.out.println("Danh sach nhan vien trong!");
			return;
		}

		System.out.print("Nhap ma so nhan su can xoa: ");
		String maSoXoa = scanner.nextLine().trim();
		NhanVien nvXoa = TimNhanVienTheoMa(maSoXoa);
		if (nvXoa == null) {
			System.out.println("Khong tim thay nhan su co ma " + maSoXoa);
		} else {
			if (nvXoa instanceof TruongPhong) {
				XoaTruongPhong((TruongPhong) nvXoa);
			} else if (nvXoa instanceof NhanVienThuong) {
				XoaNhanVienThuong((NhanVienThuong) nvXoa);
			} else {
				XoaGiamDoc((GiamDoc) nvXoa);
			}
		}
	}

	private NhanVien TimNhanVienTheoMa(String maNV) {
		for (NhanVien nv : dsNhanVien)
			if (nv.getMaNhanVien().equalsIgnoreCase(maNV))
				return nv;
		return null;
	}

	private void XoaTruongPhong(TruongPhong truongPhong) {
		for (NhanVien nv : dsNhanVien)
			if (nv instanceof NhanVienThuong && ((NhanVienThuong) nv).getMaTruongPhong() != null
					&& ((NhanVienThuong) nv).getMaTruongPhong().equalsIgnoreCase(truongPhong.getMaNhanVien()))
				((NhanVienThuong) nv).setMaTruongPhong(null);

		dsNhanVien.remove(truongPhong);
		System.out.println("Da xoa truong phong va ngat lien ket voi cac nhan vien.");
	}

	private void XoaNhanVienThuong(NhanVienThuong nhanVienThuong) {
		if (nhanVienThuong.getMaTruongPhong() != null) {
			TruongPhong truongPhongQL = (TruongPhong) TimTruongPhongTheoMa(nhanVienThuong.getMaTruongPhong());
			if (truongPhongQL != null)
				truongPhongQL.LoaiBoNhanVien(nhanVienThuong);
		}
		dsNhanVien.remove(nhanVienThuong);
		System.out.println("Da xoa nhan vien thuong.");
	}

	private void XoaGiamDoc(GiamDoc giamDoc) {
		dsNhanVien.remove(giamDoc);
		System.out.println("Da xoa giam doc.");
	}

	public void XuatThongTinToanNhanVien() {
		OutputFormatter.printDanhSachNhanVien(dsNhanVien, "THONG TIN TOAN BO NHAN SU TRONG CONG TY");
	}

	// Chuc nang 6: Tinh va xuat tong luong cho toan cong ty
	public void TinhVaXuatTongLuongCongTy() {
		double tongLuong = TongLuongToanCongTy();
		System.out.printf("\nTONG LUONG TOAN CONG TY: %.2f\n", tongLuong);
	}

	public double TongLuongToanCongTy() {
		double tongLuongCT = 0;
		for (NhanVien nv : dsNhanVien) {
			tongLuongCT += nv.tinhLuongThang();
		}
		return tongLuongCT;
	}

	// Chuc nang 7: Tim nhan vien thuong co luong cao nhat
	public void NhanVienThuongCoLuongCaoNhat() {
		if (dsNhanVien == null || dsNhanVien.isEmpty()) {
			System.out.println("Danh sach nhan vien trong!");
			return;
		}

		boolean coNhanVienThuong = false;
		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof NhanVienThuong) {
				coNhanVienThuong = true;
				break;
			}
		}

		if (!coNhanVienThuong) {
			System.out.println("Khong co nhan vien thuong nao trong cong ty!");
			return;
		}

		double luongMax = luongNhanVienThuongCaoNhat();

		System.out.println("\nNHAN VIEN THUONG CO LUONG CAO NHAT:");
		OutputFormatter.printNhanVienHeader();

		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof NhanVienThuong && nv.tinhLuongThang() == luongMax) {
				nv.XuatThongTinNhanVien();
			}
		}

		OutputFormatter.printNhanVienFooter();
		System.out.printf("Luong nhan vien thuong cao nhat la: %.2f\n", luongMax);
	}

	private double luongNhanVienThuongCaoNhat() {
		double luongNVTCaoNhat = 0;
		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof NhanVienThuong && nv.tinhLuongThang() > luongNVTCaoNhat) {
				luongNVTCaoNhat = nv.tinhLuongThang();
			}
		}
		return luongNVTCaoNhat;
	}

	// Chuc nang 8: Tim truong phong co so luong nhan vien duoi quyen nhieu nhat
	public void TruongPhongCoNhieuNhanVienNhat() {
		if (dsNhanVien == null || dsNhanVien.isEmpty()) {
			System.out.println("Danh sach nhan vien trong!");
			return;
		}

		boolean coTruongPhong = false;
		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof TruongPhong) {
				coTruongPhong = true;
				break;
			}
		}

		if (!coTruongPhong) {
			System.out.println("Khong co truong phong nao trong cong ty!");
			return;
		}

		int soLuongMax = soLuongNhanVienDuoiQuyenNhieuNhat();

		System.out.println("\nTRUONG PHONG CO SO LUONG NHAN VIEN DUOI QUYEN NHIEU NHAT:");
		OutputFormatter.printNhanVienHeader();

		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof TruongPhong && ((TruongPhong) nv).getSoNhanVien() == soLuongMax) {
				nv.XuatThongTinNhanVien();
			}
		}

		OutputFormatter.printNhanVienFooter();
		System.out.printf("So luong nhan vien duoi quyen nhieu nhat la: %d\n", soLuongMax);
	}

	private int soLuongNhanVienDuoiQuyenNhieuNhat() {
		int soLuongNhieuNhat = 0;
		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof TruongPhong && ((TruongPhong) nv).getSoNhanVien() > soLuongNhieuNhat) {
				soLuongNhieuNhat = ((TruongPhong) nv).getSoNhanVien();
			}
		}
		return soLuongNhieuNhat;
	}

	// Chuc nang 9: Sap xep nhan vien toan cong ty theo ten
	public void SapXepNhanVienTheoTen() {
		if (dsNhanVien == null || dsNhanVien.isEmpty()) {
			System.out.println("Danh sach nhan vien trong!");
			return;
		}

		Collections.sort(dsNhanVien, (nv1, nv2) -> nv1.LayTenNhanVien().compareTo(nv2.LayTenNhanVien()));

		OutputFormatter.printDanhSachNhanVien(dsNhanVien, "DANH SACH NHAN VIEN SAU KHI SAP XEP THEO TEN (A-Z)");
	}

	// Chuc nang 10: Sap xep nhan vien toan cong ty giam dan theo luong
	public void SapXepNhanVienGiamDanTheoLuong() {
		if (dsNhanVien == null || dsNhanVien.isEmpty()) {
			System.out.println("Danh sach nhan vien trong!");
			return;
		}

		Collections.sort(dsNhanVien, (nv1, nv2) -> Double.compare(nv2.tinhLuongThang(), nv1.tinhLuongThang()));

		OutputFormatter.printDanhSachNhanVien(dsNhanVien, "DANH SACH NHAN VIEN SAU KHI SAP XEP THEO LUONG (GIAM DAN)");
	}

	// Chuc nang 11: Tim giam doc co co phan nhieu nhat
	public void GiamDocCoCoPhanNhieuNhat() {
		if (dsNhanVien == null || dsNhanVien.isEmpty()) {
			System.out.println("Danh sach nhan vien trong!");
			return;
		}

		boolean coGiamDoc = false;
		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof GiamDoc) {
				coGiamDoc = true;
				break;
			}
		}

		if (!coGiamDoc) {
			System.out.println("Khong co giam doc nao trong cong ty!");
			return;
		}

		double phanTramMax = phanTramCoPhanNhieuNhat();

		System.out.println("\nGIAM DOC CO CO PHAN NHIEU NHAT:");
		OutputFormatter.printNhanVienHeader();

		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof GiamDoc && ((GiamDoc) nv).getCoPhan() == phanTramMax) {
				nv.XuatThongTinNhanVien();
			}
		}

		OutputFormatter.printNhanVienFooter();
		System.out.printf("Phan tram co phan nhieu nhat la: %.2f%%\n", phanTramMax);
	}

	private double phanTramCoPhanNhieuNhat() {
		double ptNhieuNhat = 0;
		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof GiamDoc && ((GiamDoc) nv).getCoPhan() > ptNhieuNhat) {
				ptNhieuNhat = ((GiamDoc) nv).getCoPhan();
			}
		}
		return ptNhieuNhat;
	}

	// Chuc nang 12: Tinh tong thu nhap tung giam doc
	public void TongThuNhapTungGiamDoc() {
		if (dsNhanVien == null || dsNhanVien.isEmpty()) {
			System.out.println("Danh sach nhan vien trong!");
			return;
		}

		boolean coGiamDoc = false;
		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof GiamDoc) {
				coGiamDoc = true;
				break;
			}
		}

		if (!coGiamDoc) {
			System.out.println("Khong co giam doc nao trong cong ty!");
			return;
		}

		double loiNhuan = LoiNhuanCongTy();

		OutputFormatter.printThuNhapGiamDocHeader();

		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof GiamDoc) {
				GiamDoc gd = (GiamDoc) nv;
				double thuNhap = gd.tinhLuongThang() + (gd.getCoPhan() / 100) * loiNhuan;
				OutputFormatter.printThuNhapGiamDocRow(gd.getMaNhanVien(), gd.getHoTen(), gd.getSoDienThoai(),
						gd.tinhLuongThang(), gd.getCoPhan(), thuNhap);
			}
		}
		OutputFormatter.printThuNhapGiamDocFooter();
		System.out.printf("Loi nhuan cong ty: %.2f\n", loiNhuan);
	}

	private double LoiNhuanCongTy() {
		return getDoanhThuThang() - TongLuongToanCongTy();
	}

	public String getTenCongTy() {
		return tenCongTy;
	}

	public void setTenCongTy(String tenCongTy) {
		this.tenCongTy = tenCongTy;
	}

	public String getMaSoThue() {
		return maSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		this.maSoThue = maSoThue;
	}

	public double getDoanhThuThang() {
		return doanhThuThang;
	}

	public void setDoanhThuThang(double doanhThuThang) {
		this.doanhThuThang = doanhThuThang;
	}

	public List<NhanVien> getDsNhanVien() {
		return dsNhanVien;
	}

	public void setDsNhanVien(List<NhanVien> dsNhanVien) {
		this.dsNhanVien = dsNhanVien;
	}

	public static Scanner getScanner() {
		return scanner;
	}

	public static KiemTraDieuKien getKt() {
		return kt;
	}

	public CongTy() {
		this.dsNhanVien = new ArrayList<>();
	}

	public CongTy(String tenCongTy, String maSoThue, double doanhThuThang) {
		this.tenCongTy = tenCongTy;
		this.maSoThue = maSoThue;
		this.doanhThuThang = doanhThuThang;
		this.dsNhanVien = new ArrayList<>();
	}

	public void HienThiThongTinCongTy() {
		OutputFormatter.printCongTyHeader();
		OutputFormatter.printCongTyRow(tenCongTy, maSoThue, doanhThuThang);
		OutputFormatter.printCongTyFooter();
	}

	@Override
	public String toString() {
		return String.format("| %-20s | %-15s | %-15.2f |", tenCongTy, maSoThue, doanhThuThang);
	}

}
