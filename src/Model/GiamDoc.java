package Model;

public class GiamDoc extends NhanVien {
    private double phanTramCoPhan;

    public GiamDoc() {
        super();
        this.luongMotNgay = 300;
        this.phanTramCoPhan = 0;
    }

    public GiamDoc(String maNhanVien, String hoTen, String soDienThoai, int soNgayLamViec, double coPhan) {
        super(maNhanVien, hoTen, soDienThoai, soNgayLamViec);
        this.luongMotNgay = 300;
        setCoPhan(coPhan);
    }

    @Override
    public double tinhLuongThang() {
        return luongMotNgay * soNgayLamViec;
    }

    public double getCoPhan() {
        return phanTramCoPhan;
    }

    public void setCoPhan(double coPhan) {
        if (coPhan < 0) {
            this.phanTramCoPhan = 0;
        } else if (coPhan > 100) {
            this.phanTramCoPhan = 100;
        } else {
            this.phanTramCoPhan = coPhan;
        }
    }

    public void setPhanTramCoPhan(double phanTramCoPhan) {
        setCoPhan(phanTramCoPhan);
    }
    
    @Override
	public String toString() {
		return String.format("| %-15s | %-20s | %-15s | %-15d | %-15.2f | %-15.2f%% |", maNhanVien, hoTen, soDienThoai,
				soNgayLamViec, luongMotNgay, phanTramCoPhan);
	}
	
	@Override
	public void NhapThongTinNhanVien() {
		super.NhapThongTinNhanVien();
		nhapSoNgayLamViec();
		NhapPhanTramCoPhieu();
	}

	private void NhapPhanTramCoPhieu() {
		System.out.print("Nhap phan tram co phieu: ");
		double ptCoPhieu = Double.parseDouble(scanner.nextLine());
		while(true) {
			if(kt.KiemTraPhanTramCoPhieu(ptCoPhieu)) break;
			else ptCoPhieu = Double.parseDouble(scanner.nextLine());
		}
		setCoPhan(ptCoPhieu);
	}
}
