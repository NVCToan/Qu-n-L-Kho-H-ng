package model;

import javax.swing.JDialog;

public class SanPham {

	int stt;
	String id;
	String tenSp;
	String phanLoai;
	int soLuong;
	Date ngayNhap;
	public static int count = 0;
	public static int count_Id = 100000;
	
	
	public SanPham(int stt, String id, String tenSp, String phanLoai, int soLuong, Date ngayNhap) {
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTenSp() {
		return tenSp;
	}
	public void setTenSp(String tenSp) {
		this.tenSp = tenSp;
	}
	public String getPhanLoai() {
		return phanLoai;
	}
	public void setPhanLoai(String phanLoai) {
		this.phanLoai = phanLoai;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public Date getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	
	
}
	
