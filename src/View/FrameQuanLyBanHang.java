package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Dimension2D;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import View.FrameQuanLyBanHang.duLieu;
import controller.ArrayListSP;
import controller.MyComparator;
import model.NhomSanPham;
import model.SanPham;

public class FrameQuanLyBanHang extends JFrame {

	JMenuBar jmb = new JMenuBar();
	JMenu jmnCongCu;
	JMenuItem jmnThoat;
	JMenuItem jmnThemSP;
	JMenuItem jmnChinhSua;
	public JButton btnXoa, btnTimKiem, btnXuatHang;
	// static JButton btnHuy; tai sao btnHuy lai cho static
	JLabel lbTieuDe, lbLoaiHang, lbSapXep, lbversion;
	static JLabel lbSucChua, lbThongBao;
	JPanel hang1, hang1_1, hang1_2, hang1_3, hang2, hang2_1, hang3;
	JTextField txtTimKiem;
	static DefaultTableModel dTM = new DefaultTableModel() {
		// double click ma khong thay doi truong du lieu
		@Override
		public boolean isCellEditable(int row, int column) {
			// all cells false
			return false;
		}
	};
	static JTable table;
	NhomSanPham nhomselected = null;
	static String[] tenCot = { "STT", "Ma hang", "Ten hang", "Loai hang", "So luong", "Ngay nhap" };
	static String[] optionSapXep = { "None", "Ma hang", "Loai hang", "So luong", "Ngay nhap" };
	static String[] optionTimKiem = { "Tìm kiếm....", "Mã hàng", "Tên", "Ngày" };

	static JComboBox<NhomSanPham> jcbLoaiHang;
	static JComboBox<String> jcbSapXep = new JComboBox<String>();
	static JComboBox<String> jcbTimKiem = new JComboBox<String>();
	FrameThemHang themHangUI = new FrameThemHang();
	FrameXuatHang xuatHangUI = new FrameXuatHang();
	FrameChinhSua congCu = new FrameChinhSua();
	FrameXoaNhieu xoaNhieuUI = new FrameXoaNhieu();
	static ArrayListSP<SanPham> listSP = new ArrayListSP<SanPham>();// suc chua mac dinh
	static int rowSelected;

	public FrameQuanLyBanHang() {
		super("Quan ly kho hang");
		giaoDien();
		new duLieu();
		xuLiSuKien();
		testConsole();
		hienThi();
	}

	private void giaoDien() {

		jmnCongCu = new JMenu("Cong Cu");
		jmnThoat = new JMenuItem("Thoat");
		jmnThemSP = new JMenuItem("Them SP");
		jmnChinhSua = new JMenuItem("Chinh Sua SP");

		jmnCongCu.add(jmnThemSP);
		jmnCongCu.add(jmnChinhSua);
		jmb.add(jmnCongCu);
		jmb.add(jmnThoat);
		setJMenuBar(jmb);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());

		// tieu de
		lbTieuDe = new JLabel("QUAN LY KHO HANG");
		lbTieuDe.setAlignmentX(CENTER_ALIGNMENT);
		add(lbTieuDe);
		add(pnMain);
		// hang 1
		Dimension dimTxT = new Dimension(130, 25);
		Dimension dimButton = new Dimension(30, 25);
		hang1 = new JPanel(new FlowLayout());

		hang1_2 = new JPanel();
		hang1_2.add(lbLoaiHang = new JLabel("Loai hang"));
		jcbLoaiHang = new JComboBox<NhomSanPham>();
		jcbLoaiHang.setPreferredSize(dimTxT);

		Iterator<NhomSanPham> iter = duLieu.dsNhom.iterator();
		while (iter.hasNext()) {
			NhomSanPham value = iter.next();
			jcbLoaiHang.addItem(value);
		}
		hang1_2.add(jcbLoaiHang);
		hang1.add(hang1_2);

		hang1_3 = new JPanel();
		hang1_3.add(lbSapXep = new JLabel("Sap Xep"));
		jcbSapXep.setPreferredSize(dimTxT);
		for (int i = 0; i < optionSapXep.length; i++) {
			jcbSapXep.addItem(optionSapXep[i]);
		}
		hang1_3.add(jcbSapXep);
		hang1.add(hang1_3);
		btnXoa = new JButton("Xoa");
		hang1_1 = new JPanel();
		txtTimKiem = new JTextField();
		txtTimKiem.setPreferredSize(dimTxT);
		hang1_1.add(txtTimKiem);
		jcbTimKiem.setPreferredSize(dimTxT);
		for (int i = 0; i < optionTimKiem.length; i++) {
			jcbTimKiem.addItem(optionTimKiem[i]);
		}
		hang1_1.add(jcbTimKiem);
		hang1.add(hang1_1);
		hang1.add(btnXoa);

		// hang2
		hang2 = new JPanel();
		hang2.setLayout(new BorderLayout());
		for (int i = 0; i < tenCot.length; i++) {
			dTM.addColumn(tenCot[i]);

		}
		duLieu.taoDoiTuong();
		resetDTM();
		resetStt_SP();
		resetStt_DTM();

		table = new JTable(dTM);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(350);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(130);

		JScrollPane hehe = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		Dimension dimTable = new Dimension(400, 270);
		hehe.setPreferredSize(dimTable);

		// phan hien thi thong bao
		hang2_1 = new JPanel();
		hang2_1.setLayout(new BorderLayout());
		JPanel pnThongBao = new JPanel();
		pnThongBao.add(new JLabel("Ket qua :"));
		pnThongBao.add(lbThongBao = new JLabel("Da tim thay " + FrameQuanLyBanHang.listSP.getSize() + " san pham"));
		JPanel pnSucChua = new JPanel();
		pnSucChua.add(new JLabel("Suc chua :"));
		pnSucChua.add(
				lbSucChua = new JLabel(FrameQuanLyBanHang.listSP.getSize() + "/" + FrameQuanLyBanHang.listSP.CAPACITY));
		hang2_1.add(pnThongBao, BorderLayout.WEST);
		hang2_1.add(pnSucChua, BorderLayout.EAST);
		hang2.add(hang2_1, BorderLayout.NORTH);
		hang2.add(hehe, BorderLayout.CENTER);

		// hang 3
		hang3 = new JPanel();
		hang3.add(btnXuatHang = new JButton("Xuat hang"));

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

		lbversion = new JLabel("Version 1.0");
		lbversion.setAlignmentX(CENTER_ALIGNMENT);
		add(lbversion);
	}

	private void hienThi() {
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void xuLiSuKien() {
		jmnThemSP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int indexSpCuoi = FrameQuanLyBanHang.listSP.getSize() - 1;
				int tangId = FrameQuanLyBanHang.listSP.get(indexSpCuoi).getId() + 1;
				int tangStt = FrameQuanLyBanHang.listSP.get(indexSpCuoi).getStt() + 1;
				themHangUI.txtMaHang.setText("" + tangId);
				themHangUI.txtStt.setText("" + tangStt);
				themHangUI.setModal(true);
				themHangUI.setVisible(true);
			}
		});

		jmnChinhSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				congCu.setModal(true);
				congCu.setVisible(true);
			}
		});
		jmnThoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int n = JOptionPane.showConfirmDialog(null, "Anh oi o lai ... >_<", "Thoat", JOptionPane.YES_NO_OPTION);
				if (n == 1) { // 0 la yes, 1 la no
					System.exit(0);
				}
			}
		});

		btnXuatHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				xuatHangUI.setModal(true);
				xuatHangUI.setVisible(true);
			}
		});

		btnXoa.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int id = table.getSelectedRow();
				if (id == -1) {
					JOptionPane.showMessageDialog(null, "Vui long chon SP can xoa !");
				} else {
					int rowCount = (int) table.getSelectedRowCount();
					if (rowCount == 1) {
						int obj = (int) table.getValueAt(id, 1);
						int n = JOptionPane.showConfirmDialog(null,
								"Ban co chac muon xoa '" + listSP.get(id).getTenSp() + "'  ?", "XOA SP",
								JOptionPane.YES_NO_OPTION);
						if (n == 0) { // 0 la yes, 1 la no
							SanPham value = timKiemSPTheoMa(obj);
							int idCanXoa = value.getStt() - 1;
							FrameQuanLyBanHang.listSP.remove(idCanXoa);
							resetSucChua();
							resetDTM();
							resetThongBao();
							resetStt_DTM();
							resetStt_SP();	
							JOptionPane.showMessageDialog(null, "Xoa thanh cong !");
							testConsole();
							txtTimKiem.setText(null);
						}
					} else {
						int[] listRowSelected = table.getSelectedRows();
						int a = JOptionPane.showConfirmDialog(null, "Ban co chac muon xoa " + rowCount + "  SP ?",
								"XOA SP", JOptionPane.YES_NO_OPTION);
						if (a == 0) {
							for (int i = 0; i < listRowSelected.length; i++) {
								int objs = (int) table.getValueAt(listRowSelected[i], 1);
								SanPham valueNhieu = timKiemSPTheoMa(objs);
								int idCanXoaNhieu = valueNhieu.getStt() - 1;
								FrameQuanLyBanHang.listSP.remove(idCanXoaNhieu);
								resetStt_SP();
							}
							resetSucChua();
							resetDTM();
							resetThongBao();
							resetStt_DTM();
							resetStt_SP();
							JOptionPane.showMessageDialog(null, "Xoa thanh cong !");
							testConsole();
							txtTimKiem.setText(null);
						}
					}
				}
			}
		});

		jcbTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (jcbTimKiem.getSelectedIndex() < 0)
					return;
				if (jcbTimKiem.getSelectedIndex() == 1) {
					try {
						int ID = Integer.parseInt(txtTimKiem.getText());
						if (timKiemSPTheoMa(ID) != null) {
							SanPham value = timKiemSPTheoMa(ID);
							Object[] obj = { value.getStt(), value.getId(), value.getTenSp(), value.getPhanLoai(),
									value.getSoLuong(), value.getNgayNhap() };
							dTM.setRowCount(0);
							dTM.addRow(obj);
							resetThongBao();
						} else {
							JOptionPane.showMessageDialog(null, "Khong tim thay SP !");
							txtTimKiem.setText(null);
						}
					} catch (Exception e2) {
						txtTimKiem.setRequestFocusEnabled(true);
						JOptionPane.showMessageDialog(null, "ID khong hop le !");
						txtTimKiem.setText(null);
					}
				}
				if (jcbTimKiem.getSelectedIndex() == 2) {
					String ten = txtTimKiem.getText();
					ten = chuanHoaChuoi(ten);
					if (timKiemSPTheoTen(ten) != null) {
						dTM.setRowCount(0);
						Iterator<SanPham> iter = listSP.iterator();
						while (iter.hasNext()) {
							SanPham value = iter.next();
							if (value.getTenSp().compareTo(ten) == 0) {
								Object[] obj = { value.getStt(), value.getId(), value.getTenSp(),
										value.getPhanLoai().getTenNhom(), value.getSoLuong(), value.getNgayNhap() };
								dTM.addRow(obj);
							}
						}

					} else {
						JOptionPane.showMessageDialog(null, "Khong tim thay SP !");
						txtTimKiem.setText(null);
					}

				}

				if (jcbTimKiem.getSelectedIndex() == 3) {
					try {
						int ngay = Integer.parseInt(txtTimKiem.getText());
						if (timKiemSPTheoNgay(ngay) != null) {
							dTM.setRowCount(0);
							Iterator<SanPham> iter = FrameQuanLyBanHang.listSP.iterator();
							while (iter.hasNext()) {
								SanPham value = iter.next();
								if (value.getNgayNhap().getNgay() == ngay) {
									Object[] obj = { value.getStt(), value.getId(), value.getTenSp(),
											value.getPhanLoai(), value.getSoLuong(), value.getNgayNhap() };
									dTM.addRow(obj);
//									resetThongBao();

								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "Khong tim thay SP !");
							txtTimKiem.setText(null);
						}
					} catch (Exception e2) {
						txtTimKiem.setRequestFocusEnabled(true);
						JOptionPane.showMessageDialog(null, "ID khong hop le !");
						txtTimKiem.setText(null);
					}
				}

			}
		});
		jcbLoaiHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dTM.setRowCount(0);
				nhomselected = (NhomSanPham) jcbLoaiHang.getSelectedItem();
				if (jcbLoaiHang.getSelectedIndex() == 0) {
					FrameQuanLyBanHang.resetDTM();
					resetThongBao();
					FrameQuanLyBanHang.resetStt_DTM();
				} else {
					Iterator<SanPham> iter = listSP.iterator();
					while (iter.hasNext()) {
						SanPham value = iter.next();
						if (value.getPhanLoai().getTenNhom() == nhomselected.getTenNhom()) {
							Object[] obj = { value.getStt(), value.getId(), value.getTenSp(),
									value.getPhanLoai().getTenNhom(), value.getSoLuong(), value.getNgayNhap() };
							dTM.addRow(obj);
						}
					}
					resetStt_DTM();
					resetThongBao();
				}
			}
		});

		jcbSapXep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = jcbSapXep.getSelectedIndex();
				if (selected == -1)
					return;
				if (selected == 0) {
					MyComparator<SanPham> x = new MyComparator<SanPham>() {
						@Override
						public int compare(SanPham sp1, SanPham sp2) {

							if (sp1.getNgayNhap().getNam() == sp2.getNgayNhap().getNam()) {
								if (sp1.getNgayNhap().getThang() == sp2.getNgayNhap().getThang()) {
									if (sp1.getNgayNhap().getNgay() == sp2.getNgayNhap().getNgay())
										return 0;
									else if (sp1.getNgayNhap().getNgay() > sp2.getNgayNhap().getNgay())
										return 1;
									else
										return -1;
								} else if (sp1.getNgayNhap().getThang() > sp2.getNgayNhap().getThang())
									return 1;
								else
									return -1;
							} else if (sp1.getNgayNhap().getNam() > sp2.getNgayNhap().getNam())
								return 1;
							else
								return -1;

						}

						@Override
						public int compare(SanPham e) {
							// TODO Auto-generated method stub
							return 0;
						}

					};
					listSP.interchangeSortZA(x);
					resetStt_SP();
					resetDTM();
				}
				if (selected == 1) {
					MyComparator<SanPham> x = new MyComparator<SanPham>() {
						@Override
						public int compare(SanPham sp1, SanPham sp2) {

							if (sp1.getId() == sp2.getId())
								return 0;
							else if (sp1.getId() > sp2.getId())
								return 1;
							else
								return -1;
						}

						@Override
						public int compare(SanPham e) {
							// TODO Auto-generated method stub
							return 0;
						}
					};
					listSP.interchangeSortAZ(x);
					resetStt_SP();
					resetDTM();

				}
				if (selected == 2) {
					MyComparator<SanPham> x = new MyComparator<SanPham>() {
						@Override
						public int compare(SanPham sp1, SanPham sp2) {

							return sp1.getPhanLoai().getTenNhom().compareTo(sp2.getPhanLoai().getTenNhom());

						}

						@Override
						public int compare(SanPham e) {
							// TODO Auto-generated method stub
							return 0;
						}
					};
					listSP.interchangeSortAZ(x);
					resetStt_SP();
					resetDTM();
				}
				if (selected == 3) {
					MyComparator<SanPham> x = new MyComparator<SanPham>() {
						@Override
						public int compare(SanPham sp1, SanPham sp2) {

							if (sp1.getSoLuong() == sp2.getSoLuong())
								return 0;
							else if (sp1.getSoLuong() > sp2.getSoLuong())
								return 1;
							else
								return -1;
						}

						@Override
						public int compare(SanPham e) {
							// TODO Auto-generated method stub
							return 0;
						}
					};
					listSP.interchangeSortAZ(x);
					resetStt_SP();
					resetDTM();
				}
				if (selected == 4) {
					MyComparator<SanPham> x = new MyComparator<SanPham>() {
						@Override
						public int compare(SanPham sp1, SanPham sp2) {

							if (sp1.getNgayNhap().getNam() == sp2.getNgayNhap().getNam()) {
								if (sp1.getNgayNhap().getThang() == sp2.getNgayNhap().getThang()) {
									if (sp1.getNgayNhap().getNgay() == sp2.getNgayNhap().getNgay())
										return 0;
									else if (sp1.getNgayNhap().getNgay() > sp2.getNgayNhap().getNgay())
										return 1;
									else
										return -1;
								} else if (sp1.getNgayNhap().getThang() > sp2.getNgayNhap().getThang())
									return 1;
								else
									return -1;
							} else if (sp1.getNgayNhap().getNam() > sp2.getNgayNhap().getNam())
								return 1;
							else
								return -1;

						}

						@Override
						public int compare(SanPham e) {
							// TODO Auto-generated method stub
							return 0;
						}

					};
					listSP.interchangeSortAZ(x);
					resetStt_SP();
					resetDTM();
				}

			}
		});
		// xuat hang
				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent me) {
						if (me.getClickCount() == 2) { // to detect doble click events
							int id = table.getSelectedRow();
							int obj = (int) table.getValueAt(id, 1);
							SanPham value = timKiemSPTheoMa(obj);
							int idCanXoa = value.getStt() - 1;
							Object[] objs = {value.getStt(),
									value.getId(),
									value.getTenSp(),
									value.getPhanLoai(),
									value.getSoLuong(),
									};
							xuatHangUI.dtmXuatHang.addRow(objs);
							System.out.println(value.getStt());
							xuatHangUI.resetSttDTM_XuatHang();
							xuatHangUI.setVisible(true);
							
						}
					}
				});

	}

	static class duLieu {
		static ArrayListSP<NhomSanPham> dsNhom = new ArrayListSP<NhomSanPham>();

		public static void themPhanLoai(NhomSanPham loai) {
			dsNhom.Add(loai);
		}

		public static void taoDoiTuong() {// dung trong function giaoDien

			SanPham sp1 = new SanPham(1, 10000, "a", new NhomSanPham("l"), 20, new model.Date(2, 01, 2023));
			SanPham sp2 = new SanPham(2, 10001, "San pham 2", new NhomSanPham("a"), 40, new model.Date(12, 5, 2010));
			SanPham sp3 = new SanPham(3, 10005, "San pham 3", new NhomSanPham("c"), 25, new model.Date(8, 1, 2000));
			SanPham sp4 = new SanPham(4, 10008, "San pham 4", new NhomSanPham("k"), 520, new model.Date(2, 5, 1990));
			SanPham sp5 = new SanPham(5, 10004, "San pham 5", new NhomSanPham("Loai 2"), 79,
					new model.Date(7, 4, 2002));
			SanPham sp6 = new SanPham(1, 10002, "San pham 1", new NhomSanPham("Loai 1"), 20,
					new model.Date(2, 01, 2023));
			SanPham sp7 = new SanPham(2, 10006, "San pham 2", new NhomSanPham("Loai 2"), 40,
					new model.Date(12, 5, 2010));
			SanPham sp8 = new SanPham(3, 10007, "San pham 3", new NhomSanPham("Loai 3"), 25,
					new model.Date(8, 1, 2000));
			SanPham sp9 = new SanPham(4, 10003, "San pham 4", new NhomSanPham("b"), 520, new model.Date(2, 5, 1990));
			SanPham sp10 = new SanPham(5, 10009, "San pham 5", new NhomSanPham("Loai 1"), 79,
					new model.Date(7, 4, 2002));
			SanPham sp11 = new SanPham(1, 10010, "San pham 1", new NhomSanPham("Loai 1"), 20,
					new model.Date(2, 01, 2023));
			SanPham sp12 = new SanPham(2, 10011, "San pham 2", new NhomSanPham("Loai 2"), 40,
					new model.Date(12, 5, 2010));
			SanPham sp13 = new SanPham(3, 10012, "San pham 3", new NhomSanPham("Loai 3"), 25,
					new model.Date(8, 1, 2000));
			SanPham sp14 = new SanPham(4, 10013, "San pham 4", new NhomSanPham("Loai 2"), 520,
					new model.Date(2, 5, 1990));
			SanPham sp15 = new SanPham(5, 10014, "San pham 5", new NhomSanPham("Loai 1"), 79,
					new model.Date(7, 4, 2002));
			
			listSP.Add(sp1);
			listSP.Add(sp2);
			listSP.Add(sp3);
			listSP.Add(sp4);
			listSP.Add(sp5);
			listSP.Add(sp6);
			listSP.Add(sp7);
			listSP.Add(sp8);
			listSP.Add(sp9);
			listSP.Add(sp10);
			listSP.Add(sp11);
			listSP.Add(sp12);
			listSP.Add(sp13);
			listSP.Add(sp14);
			listSP.Add(sp15);

			SanPham.count_id = FrameQuanLyBanHang.listSP.get(FrameQuanLyBanHang.listSP.getSize() - 1).getId() + 1;
			SanPham.count_stt = FrameQuanLyBanHang.listSP.getSize() + 1; // Tu dong set theo stt va ID hien co trong
																			// danh sach
		}

		public static ArrayListSP<NhomSanPham> duLieuDSNhom() {

			NhomSanPham nhom0 = new NhomSanPham("ALL");
			NhomSanPham nhom1 = new NhomSanPham("Loai 1");
			NhomSanPham nhom2 = new NhomSanPham("Loai 2");
			NhomSanPham nhom3 = new NhomSanPham("Loai 3");
			dsNhom.Add(nhom0);
			dsNhom.Add(nhom1);
			dsNhom.Add(nhom2);
			dsNhom.Add(nhom3);
//			Iterator<NhomSanPham> iter = dsNhom.iterator();
//			while (iter.hasNext()) {
//				NhomSanPham value = iter.next();
//				jcbLoaiHang.addItem(value);
//			}
			return dsNhom;

		}

	}

	public static void testConsole() {
		StringBuffer sb = new StringBuffer();
		sb.append("Stt-SP" + "\t" + "Ten-SP" + "\t\t" + "Ma-SP" + "\n");
		for (int i = 0; i < FrameQuanLyBanHang.listSP.getSize(); i++) {
			sb.append(FrameQuanLyBanHang.listSP.get(i).getStt() + "\t\t" + FrameQuanLyBanHang.listSP.get(i).getTenSp()
					+ "\t" + FrameQuanLyBanHang.listSP.get(i).getId() + "\n");
		}
		sb.append("------------------------------------------");
		System.out.println(sb);

	}

	public static void resetStt_DTM() {
		for (int i = 0; i < FrameQuanLyBanHang.dTM.getRowCount(); i++) {
			FrameQuanLyBanHang.dTM.setValueAt(i + 1, i, 0);
		}
	}

	public static void resetDTM() {
		Iterator<SanPham> iter = FrameQuanLyBanHang.listSP.iterator();
		dTM.setRowCount(0);
		while (iter.hasNext()) {
			SanPham value = iter.next();
			Object[] obj = { value.getStt(), value.getId(), value.getTenSp(), value.getPhanLoai(), value.getSoLuong(),
					value.getNgayNhap() };
			dTM.addRow(obj);
		}
	}

	public static void resetStt_SP() {
		for (int i = 0; i < FrameQuanLyBanHang.listSP.getSize(); i++) {
			FrameQuanLyBanHang.listSP.get(i).setStt(i + 1);
		}
	}

	public static void resetSucChua() {
		FrameQuanLyBanHang.lbSucChua
				.setText(FrameQuanLyBanHang.listSP.getSize() + "/" + FrameQuanLyBanHang.listSP.CAPACITY);
		;
	}

	public static void resetThongBao() {
		FrameQuanLyBanHang.lbThongBao.setText("Da tim thay " + FrameQuanLyBanHang.dTM.getRowCount() + " san pham");
	}

	public static SanPham timKiemSPTheoMa(int id) {
		Iterator<SanPham> iter = FrameQuanLyBanHang.listSP.iterator();
		while (iter.hasNext()) {
			SanPham value = iter.next();
			if (value.getId() == id) {
				return value;
			}
		}
		return null;
	}

	public static SanPham timKiemSPTheoTen(String ten) {
		Iterator<SanPham> iter = FrameQuanLyBanHang.listSP.iterator();
		while (iter.hasNext()) {
			SanPham value = iter.next();
			if (value.getTenSp().compareTo(ten) == 0) {
				return value;
			}
		}
		return null;
	}

	public static SanPham timKiemSPTheoNgay(int ngay) {
		Iterator<SanPham> iter = FrameQuanLyBanHang.listSP.iterator();
		while (iter.hasNext()) {
			SanPham value = iter.next();
			if (value.getNgayNhap().getNgay() == ngay) {
				return value;
			}
		}
		return null;
	}

	public static String chuanHoaChuoi(String str) {
		str = str.trim();
		str = str.replaceAll("\\s+", " ");
		return str;
	}

}
