package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.ArrayListSP;
import model.NhomSanPham;
import model.SanPham;

public class FrameQuanLyBanHang extends JFrame {
	
	public JButton btnThemHang, btnXoa, btnTimKiem, btnCongCu, btnXuatHang;
	JLabel lbTieuDe, lbLoaiHang, lbversion;
	JPanel hang1, hang1_1, hang1_2, hang2, hang3;
	JTextField txtTimKiem;
	DefaultTableModel dTM = new DefaultTableModel();
	JTable table;
	NhomSanPham nhomSelected=null;
	SanPham spSelected=null;
	 static ArrayListSP<NhomSanPham> dsNhom;
//		ArrayListSP<SanPham> dsSanPham;
		ArrayListSP<String> dsSanPham;
		SanPham sp1 = new SanPham(1234, "Loa máy tính",	 5, new model.Date(1, 12, 2019));

	String[] tenCot = { "Số thứ tự", "Mã Hàng", "Tên Hàng", "Loại Hàng", "Số Lượng", "Ngày Nhập" };
//	Object[] datas = {3, 12,12,23,3,3,4,6,7,7,4,2};
	Vector<String> datas1 = new Vector<String>();
	Vector<String> datas2 = new Vector<String>();
	static JComboBox<NhomSanPham> jcbLoaiHang = new JComboBox<NhomSanPham>(); // Sửa lại kiểu dữ liệu
	FrameThemHang themHangUI = new FrameThemHang();
	FrameXuatHang xuatHangUI = new FrameXuatHang();
	FrameCongCu congCu = new FrameCongCu();

	public FrameQuanLyBanHang() {
		super("Quản lý kho hàng");
		giaoDien();
		new duLieu();
		xuLiSuKien();
		hienThi();
	}

	private void giaoDien() {

		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		// tieu de
		lbTieuDe = new JLabel("QUẢN Lý KHO HÀNG");
		lbTieuDe.setAlignmentX(CENTER_ALIGNMENT);

		// hang 1
		Dimension dimTxT = new Dimension(130, 25);
		Dimension dimButton = new Dimension(30, 25);
		hang1 = new JPanel(new FlowLayout());
		btnThemHang = new JButton("Thêm hàng");
		btnXoa = new JButton("Xoá");
		hang1.add(btnThemHang);
		hang1.add(btnXoa);
		hang1_1 = new JPanel();
		txtTimKiem = new JTextField();
		txtTimKiem.setPreferredSize(dimTxT);
		hang1_1.add(txtTimKiem);
		hang1_1.add(btnTimKiem = new JButton("Tìm kiếm"));
		hang1.add(hang1_1);
		hang1_2 = new JPanel();
		hang1_2.add(lbLoaiHang = new JLabel("Loại hàng"));
		jcbLoaiHang.setPreferredSize(dimTxT);
		hang1_2.add(jcbLoaiHang);
		hang1.add(hang1_2);
		hang1.add(btnCongCu = new JButton("Công cụ"));
		hang1.add(btnXuatHang = new JButton("Xuất hàng"));
		
		datas1.add("1");
		datas1.add("2");
		datas1.add("3");
		datas1.add("4");
		datas1.add("5");
		datas1.add("6");
		datas2.add("7");
		datas2.add("8");
		datas2.add("9");
		datas2.add("10");
		datas2.add("11");
		datas2.add("12");
		// hang2
		hang2 = new JPanel();
		for (int i = 0; i < tenCot.length; i++) {
			dTM.addColumn(tenCot[i]);
		}
		dTM.addRow(datas1);
		table = new JTable(dTM);
		JScrollPane hehe = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		hang2.add(hehe);

		// hang 3
		hang3 = new JPanel();
		lbversion = new JLabel("Version 1.0");
		lbversion.setAlignmentX(CENTER_ALIGNMENT);
		hang3.add(lbversion);

		add(lbTieuDe);
		add(hang1);
		add(hang2);
		add(hang3);
	}

	 
	private void hienThi() {
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void xuLiSuKien() {
		// Thêm hàng
		btnThemHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				themHangUI.txtMaHang.setText("" + SanPham.id);
				themHangUI.txtStt.setText(""+SanPham.stt);
				themHangUI.setModal(true); // chức năng để frame có thể ở lớp trên mặt (dùng được khi đã extends
											// JDialog)
				themHangUI.setVisible(true);
			}
		});

		// xuất hàng
		btnXuatHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				xuatHangUI.setModal(true); // chức năng để frame có thể ở lớp trên mặt (dùng được khi đã extends
											// JDialog)
				xuatHangUI.setVisible(true);
			}
		});

		// công cụ
		btnCongCu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				congCu.setModal(true); // chức năng để frame có thể ở lớp trên mặt (dùng được khi đã extends JDialog)
				congCu.setVisible(true);
			}
		});
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (row == -1)
					return;
				dTM.removeRow(row); // Xoá dòng đang chọn

			}
		});
		jcbLoaiHang.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(jcbLoaiHang.getSelectedIndex()==-1) return;
				nhomSelected = (NhomSanPham) jcbLoaiHang.getSelectedItem();
//				for(int i =0 ; i<nhomSelected.getDsSanPham().size();i++) {
//					dTM.addRow(nhomSelected.getDsSanPham());
//				}
				dTM.addRow(nhomSelected.getDsSanPham());
			}
		});

	}
	static class  duLieu {
		// TODO Auto-generated method stub
		
	
		public static ArrayListSP<NhomSanPham> duLieuDSNhom() {
			SanPham sp1 = new SanPham(1234, "Loa máy tính",	 5, new model.Date(1, 12, 2019));
			SanPham sp2 = new SanPham(1234, "Chuột không dây",	 5, new model.Date(1, 12, 2019));
			SanPham sp3 = new SanPham(1234, "Phím cơ",	 5, new model.Date(1, 12, 2019));
			SanPham sp4 = new SanPham(1234, "Router",	 5, new model.Date(1, 12, 2019));
			SanPham sp5 = new SanPham(1234, "LCD",	 5, new model.Date(1, 12, 2019));
		dsNhom = new ArrayListSP<NhomSanPham>();
		NhomSanPham nhom1 = new NhomSanPham("Phụ kiện máy tính");
		NhomSanPham nhom2 = new NhomSanPham("Chuột máy tính");
		NhomSanPham nhom3 = new NhomSanPham("Bàn phím");
		NhomSanPham nhom4 = new NhomSanPham("Thiết bị mạng");
		NhomSanPham nhom5 = new NhomSanPham("Màn hình");
		dsNhom.Add(nhom1);
		dsNhom.Add(nhom2);
		dsNhom.Add(nhom3);
		dsNhom.Add(nhom4);
		dsNhom.Add(nhom5);
		Iterator<NhomSanPham> iter = dsNhom.iterator();
		while (iter.hasNext()) {
			NhomSanPham value = iter.next();
			jcbLoaiHang.addItem(value);
		}
		return dsNhom;

	}
//		public static ArrayListSP<NhomSanPham> mtDSNhom()
//		{
//			return dsNhom;
//		}
}
}