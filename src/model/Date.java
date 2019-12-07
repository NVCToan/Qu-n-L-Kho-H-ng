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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ngay+ "/" + thang +"/" + nam;
	}
	
}
