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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import View.FrameQuanLyBanHang.duLieu;
import controller.ArrayListSP;
import model.NhomSanPham;
import model.SanPham;

public class FrameQuanLyBanHang extends JFrame {

	public JButton btnThemHang, btnXoa, btnTimKiem, btnCongCu, btnXuatHang;
	static JButton btnHuy;
	JLabel lbTieuDe, lbLoaiHang, lbversion;
	JPanel hang1, hang1_1, hang1_2, hang2, hang3;
	JTextField txtTimKiem;
	static DefaultTableModel dTM = new DefaultTableModel();
	static JTable table;
	int rowSelected;
	NhomSanPham nhomselected = null;
	String[] tenCot = { "STT", "Ma hang", "Ten hang", "Loai hang", "So luong", "Ngay nhap" };
//	static String[] listPhanLoai = {"Loai 1", "Loai 2", "Loai 3"};
//	JComboBox<String> jcbLoaiHang = new JComboBox<String>(listPhanLoai); // Sá»­a láº¡i kiá»ƒu dá»¯ liá»‡u
	static JComboBox<NhomSanPham> jcbLoaiHang = new JComboBox<NhomSanPham>();
	FrameThemHang themHangUI = new FrameThemHang();
	FrameXuatHang xuatHangUI = new FrameXuatHang();
	FrameCongCu congCu = new FrameCongCu();
	static ArrayListSP<SanPham> listSP = new ArrayListSP<SanPham>();// suc chua mac dinh
//	public static ArrayList<SanPham> list = new ArrayList<SanPham>(); // Khong dung java.util

	public FrameQuanLyBanHang() {
		super("Quan ly kho hang");
		giaoDien();
		new duLieu();
		xuLiSuKien();
		hienThi();
	}
//	private void taoDoiTuong() {// dung trong function giaoDien
//		SanPham sp1 = new SanPham(1,10000 ,"Day la san pham loai 1", "Loai 1", 20, new model.Date(1, 1, 2000));
////		SanPham sp2 = new SanPham(++SanPham.count,"#"+SanPham.count_Id++ ,"San Pham 2", "Loai 2", 21, new model.Date(17, 12, 2000));
////		SanPham sp3 = new SanPham(++SanPham.count,"#"+SanPham.count_Id++ ,"San Pham 3", "Loai 2", 15, new model.Date(6, 10, 2000));
////		SanPham sp4 = new SanPham(++SanPham.count,"#"+SanPham.count_Id++ ,"San Pham 4", "Loai 3", 10, new model.Date(9, 8, 2000));
////		SanPham sp5 = new SanPham(++SanPham.count,"#"+SanPham.count_Id++ ,"San Pham 5", "Loai 1", 76, new model.Date(1, 5, 2000));
//		list.add(sp1);
////		list.add(sp2);
////		list.add(sp3);
////		list.add(sp4);
////		list.add(sp5);
//	}

	private void giaoDien() {

		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		add(pnMain);
		// tieu de
		lbTieuDe = new JLabel("QUAN LY KHO HANG");
		lbTieuDe.setAlignmentX(CENTER_ALIGNMENT);

		// hang 1
		Dimension dimTxT = new Dimension(130, 25);
		Dimension dimButton = new Dimension(30, 25);
		hang1 = new JPanel(new FlowLayout());
		btnThemHang = new JButton("Them hang");
		btnXoa = new JButton("Xoa");
		hang1.add(btnThemHang);
		hang1.add(btnXoa);
		hang1_1 = new JPanel();
		txtTimKiem = new JTextField();
		txtTimKiem.setPreferredSize(dimTxT);
		hang1_1.add(txtTimKiem);
		hang1_1.add(btnTimKiem = new JButton("Tim kiem"));
		hang1_1.add(btnHuy = new JButton("Huy"));
		btnHuy.setEnabled(false);
		hang1.add(hang1_1);
		hang1_2 = new JPanel();
		hang1_2.add(lbLoaiHang = new JLabel("Loai hang"));
		jcbLoaiHang.setPreferredSize(dimTxT);
		hang1_2.add(jcbLoaiHang);
		hang1.add(hang1_2);
		hang1.add(btnCongCu = new JButton("Cong cu"));
		hang1.add(btnXuatHang = new JButton("Xuat hang"));

		// hang2
		hang2 = new JPanel();
		hang2.setLayout(new BorderLayout());
		for (int i = 0; i < tenCot.length; i++) {
			dTM.addColumn(tenCot[i]);

		}
		duLieu.taoDoiTuong();
		Iterator<SanPham> iter = listSP.iterator();
		while (iter.hasNext()) {
			SanPham value = iter.next();
			Object[] obj = { value.getStt(), value.getId(), value.getTenSp(), value.getPhanLoai().getTenNhom(),
					value.getSoLuong(), value.getNgayNhap() };
			dTM.addRow(obj);
		}
//		new duLieu();
//		for (int i = 0; i < listSP.getSize(); i++) {
//			Object[] obj = {listSP.getData()[i].getStt(),listSP.getData()[i].getId(),listSP.getData()[i].getTenSp(),listSP.getData()[i].getPhanLoai(),listSP.getData()[i].getSoLuong(),listSP.getData()[i].getNgayNhap()};
//			dTM.addRow(obj);
//			
//		}
		table = new JTable(dTM);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(350);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(130);
		table.disable();

		JScrollPane hehe = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		hang2.add(hehe, BorderLayout.CENTER);

		// hang 3
		hang3 = new JPanel();
		lbversion = new JLabel("Version 1.0");
		lbversion.setAlignmentX(CENTER_ALIGNMENT);
		hang3.add(lbversion);

		JPanel pnEast = new JPanel();
		pnEast.setPreferredSize(new Dimension(31, 0));
		JPanel pnWest = new JPanel();
		pnWest.setPreferredSize(new Dimension(31, 0));
//		pnMain.add(lbTieuDe);
		pnMain.add(hang1, BorderLayout.NORTH);
		pnMain.add(hang2, BorderLayout.CENTER);
		pnMain.add(hang3, BorderLayout.SOUTH);
		pnMain.add(pnEast, BorderLayout.EAST);
		pnMain.add(pnWest, BorderLayout.WEST);

	}
	public static void resetSTT() {
		for (int i = 1; i <= FrameQuanLyBanHang.listSP.getSize(); i++) {

			FrameQuanLyBanHang.dTM.setValueAt(i,i-1,0);
		}
	}
	public static void resetDTM() {
		Iterator<SanPham> iter = FrameQuanLyBanHang.listSP.iterator();
		dTM.setRowCount(0);
		while (iter.hasNext()) {
			SanPham value = iter.next();
				Object[] obj = { value.getStt(), value.getId(), value.getTenSp(), value.getPhanLoai(),
						value.getSoLuong(), value.getNgayNhap() };
				dTM.addRow(obj);

			}

	}

	private void hienThi() {
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void xuLiSuKien() {
		// ThÃªm hÃ ng
		btnThemHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				themHangUI.txtMaHang.setText("" + SanPham.count_id);
				themHangUI.txtStt.setText("" + SanPham.count_stt);
				themHangUI.setModal(true); // chá»©c nÄƒng Ä‘á»ƒ frame cÃ³ thá»ƒ á»Ÿ lá»›p trÃªn máº·t (dÃ¹ng Ä‘Æ°á»£c
											// khi Ä‘Ã£ extends
											// JDialog)
				themHangUI.setVisible(true);
			}
		});

		// xuáº¥t hÃ ng
		btnXuatHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				xuatHangUI.setModal(true); // chá»©c nÄƒng Ä‘á»ƒ frame cÃ³ thá»ƒ á»Ÿ lá»›p trÃªn máº·t (dÃ¹ng Ä‘Æ°á»£c
											// khi Ä‘Ã£ extends
											// JDialog)
				xuatHangUI.setVisible(true);
			}
		});

		// cÃ´ng cá»¥
		btnCongCu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				congCu.setModal(true); // chá»©c nÄƒng Ä‘á»ƒ frame cÃ³ thá»ƒ á»Ÿ lá»›p trÃªn máº·t (dÃ¹ng Ä‘Æ°á»£c khi
										// Ä‘Ã£ extends JDialog)
				congCu.setVisible(true);
			}
		});
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = rowSelected;
				if(rowSelected ==-1) return;
//				dTM.removeRow(row); // XoÃ¡ dÃ²ng Ä‘ang chá»�n
				FrameQuanLyBanHang.listSP.remove(rowSelected);
				rowSelected = -1;
				resetDTM();
				resetSTT();
			}
		});
		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Iterator<SanPham> iter = FrameQuanLyBanHang.listSP.iterator();
					while (iter.hasNext()) {
						SanPham value = iter.next();

						int ID = Integer.parseInt(txtTimKiem.getText());
						if (value.getId()==ID) {
							rowSelected = value.getStt() - 1;
							Object[] obj = { value.getStt(), value.getId(), value.getTenSp(), value.getPhanLoai(),
									value.getSoLuong(), value.getNgayNhap() };
							dTM.setRowCount(0);
							dTM.addRow(obj);

						}
						
					}
					btnHuy.setEnabled(true);

				} catch (Exception e2) {
					txtTimKiem.setRequestFocusEnabled(true);
					JOptionPane.showMessageDialog(null, "ID khong ton tai");
				}

			}
		});
		btnHuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Iterator<SanPham> iter = listSP.iterator();
				dTM.setRowCount(0);
				while (iter.hasNext()) {
					SanPham value = iter.next();
					Object[] obj = { value.getStt(), value.getId(), value.getTenSp(), value.getPhanLoai().getTenNhom(),
							value.getSoLuong(), value.getNgayNhap() };
					dTM.addRow(obj);

				}
				btnHuy.setEnabled(false);
			}

		});
		jcbLoaiHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dTM.setRowCount(0);
				nhomselected = (NhomSanPham) jcbLoaiHang.getSelectedItem();
				if (jcbLoaiHang.getSelectedIndex() == -1)
					return;
				if (jcbLoaiHang.getSelectedIndex() == 0) {
					FrameQuanLyBanHang.resetDTM();
					FrameQuanLyBanHang.resetSTT();
				}
				Iterator<SanPham> iter = listSP.iterator();
				while (iter.hasNext()) {

					SanPham value = iter.next();
					if (value.getPhanLoai().getTenNhom() == nhomselected.getTenNhom()) {
						Object[] obj = { value.getStt(), value.getId(), value.getTenSp(),
								value.getPhanLoai().getTenNhom(), value.getSoLuong(), value.getNgayNhap() };
						dTM.addRow(obj);
					}
				}
			}
		});
		
	}

	static class duLieu {
		static ArrayListSP<NhomSanPham> dsNhom = new ArrayListSP<NhomSanPham>();
		// TODO Auto-generated method stub
		public static void taoDoiTuong() {// dung trong function giaoDien
			SanPham sp1 = new SanPham(1, 10000, "San pham 1", new NhomSanPham("Loai 1"), 20,
					new model.Date(2, 01, 2023));
			SanPham sp2 = new SanPham(2, 10001, "San pham 2", new NhomSanPham("Loai 2"), 40,
					new model.Date(12, 5, 2010));
			SanPham sp3 = new SanPham(3, 10002, "San pham 3", new NhomSanPham("Loai 3"), 25,
					new model.Date(8, 1, 2000));
			SanPham sp4 = new SanPham(4, 10003, "San pham 4", new NhomSanPham("Loai 2"), 520,
					new model.Date(2, 5, 1990));
			SanPham sp5 = new SanPham(5, 10004, "San pham 5", new NhomSanPham("Loai 1"), 79,
					new model.Date(7, 4, 2002));
			listSP.Add(sp1);
			listSP.Add(sp2);
			listSP.Add(sp3);
			listSP.Add(sp4);
			listSP.Add(sp5);

			SanPham.count_id = FrameQuanLyBanHang.listSP.get(FrameQuanLyBanHang.listSP.getSize() - 1).getId() + 1;
			SanPham.count_stt = FrameQuanLyBanHang.listSP.getSize() + 1; // Tu dong set theo stt va ID hien co trong
																			// danh sach
		}

		public static ArrayListSP<NhomSanPham> duLieuDSNhom() {
			
			NhomSanPham nhom0 = new NhomSanPham("");
			NhomSanPham nhom1 = new NhomSanPham("Loai 1");
			NhomSanPham nhom2 = new NhomSanPham("Loai 2");
			NhomSanPham nhom3 = new NhomSanPham("Loai 3");
			dsNhom.Add(nhom0);
			dsNhom.Add(nhom1);
			dsNhom.Add(nhom2);
			dsNhom.Add(nhom3);
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
