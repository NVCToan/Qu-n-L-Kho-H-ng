package model;

import java.util.Vector;

public class NhomSanPham {
	 String tenNhom;
	private static  Vector<String> dsSanPham;
		public void themSanPham() {
			
			dsSanPham.add("a");
		}
	public  String getTenNhom() {
		return tenNhom;
	}
	public void setTenNhom(String tenNhom) {
		this.tenNhom = tenNhom;
	}
	public static Vector<String> getDsSanPham() {
		return dsSanPham;
	}
	public void setDsSanPham(Vector<String> dsSanPham) {
		this.dsSanPham = dsSanPham;
	}
	public NhomSanPham(String tenNhom) {
		super();
		this.tenNhom = tenNhom;
		this.dsSanPham = new Vector<String>();
	}
	public NhomSanPham() {
		super();
		this.dsSanPham = new Vector<String>();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return tenNhom;
	}

}
