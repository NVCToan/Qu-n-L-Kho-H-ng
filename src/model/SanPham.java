package model;

import javax.swing.JDialog;

public class SanPham {

	int stt;
	int id;
	public static int count_stt ;
	public static int count_id ;
	String tenSp;
	NhomSanPham phanLoai;
	int soLuong;
	 Date ngayNhap;

	public SanPham(int stt, int id, String tenSp, NhomSanPham phanLoai, int soLuong, Date ngayNhap) {
		super();
		this.stt = stt;
		this.id = id;
		this.tenSp = tenSp;
		this.phanLoai = phanLoai;
		this.soLuong = soLuong;
		this.ngayNhap = ngayNhap;
	}
	
	public SanPham() {
	}
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTenSp() {
		return tenSp;
	}
	public void setTenSp(String tenSp) {
		this.tenSp = tenSp;
	}
	public NhomSanPham getPhanLoai() {
		return phanLoai;
	}
	public void setPhanLoai(NhomSanPham phanLoai) {
		this.phanLoai = phanLoai;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public  Date getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return tenSp;
	}
	
}
	
