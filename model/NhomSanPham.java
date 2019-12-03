package model;

import java.util.Vector;

public class NhomSanPham {
	private String tenNhom;
	private Vector<SanPham> dsSanPham;
		public void themSanPham(SanPham sp) {
			dsSanPham.add(sp);
		}
	
	public String getTenNhom() {
		return tenNhom;
	}
	public void setTenNhom(String tenNhom) {
		this.tenNhom = tenNhom;
	}
	public Vector<SanPham> getDsSanPham() {
		return dsSanPham;
	}
	public void setDsSanPham(Vector<SanPham> dsSanPham) {
		this.dsSanPham = dsSanPham;
	}
	public NhomSanPham(String tenNhom, Vector<SanPham> dsSanPham) {
		super();
		this.tenNhom = tenNhom;
		this.dsSanPham = dsSanPham;
		this.dsSanPham = new Vector<SanPham>();
	}
	public NhomSanPham() {
		super();
		this.dsSanPham = new Vector<SanPham>();
	}
	

}
