package model;

public class Date {
	 int ngay;
	 int thang;
	 int nam;
	public Date(int ngay, int thang, int nam) {
		super();
		this.ngay = ngay;
		this.thang = thang;
		this.nam = nam;
	}



	public int getNgay() {
		return ngay;
	}



	public void setNgay(int ngay) {
		this.ngay = ngay;
	}



	public int getThang() {
		return thang;
	}



	public void setThang(int thang) {
		this.thang = thang;
	}



	public int getNam() {
		return nam;
	}



	public void setNam(int nam) {
		this.nam = nam;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ngay+ "/" + thang +"/" + nam;
	}
	
}
