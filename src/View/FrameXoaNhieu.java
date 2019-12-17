package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import View.FrameQuanLyBanHang.duLieu;
import controller.ArrayListSP;
import model.NhomSanPham;
import model.SanPham;

public class FrameXoaNhieu extends JDialog{
	JTextField txtTimKiem;
	JButton btnTimKiem, btnXoa;
	JTextArea areaKQ, areaXoa;
	JPanel pnMain, pnChua, hang1, hang2, hang3;
	JScrollPane jscKhungLeft, jscKhungRight;
	JComboBox<NhomSanPham> jcbPhanLoai;
	ArrayListSP<NhomSanPham> dsNhom;
	JTable table_KQ,table_DaChon;
	DefaultTableModel dTM_XoaNhieu = new DefaultTableModel() {
		// double click ma khong thay doi truong du lieu
		@Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }
	};
	DefaultTableModel dTM_DaChon = new DefaultTableModel() {
		// double click ma khong thay doi truong du lieu
		@Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }
	};

	public FrameXoaNhieu() {
		giaoDien();
		xuLiSuKien();
		hienThi();
	}

	private void giaoDien() {

		pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		pnChua = new JPanel();
		pnChua.setLayout(new BorderLayout());
		JLabel lbTieuDe = new JLabel("XOA");
		lbTieuDe.setAlignmentX(CENTER_ALIGNMENT);

		// hàng 1
		hang1 = new JPanel();
		Dimension dimTimKiem = new Dimension(220, 27);
		Dimension dimjcb = new Dimension(130, 25);
		hang1.add(new JLabel("Phan loai "));
		jcbPhanLoai = new JComboBox<NhomSanPham>();
		jcbPhanLoai.setPreferredSize(dimjcb);
		dsNhom = new ArrayListSP<NhomSanPham>();
		dsNhom = duLieu.dsNhom;
		Iterator<NhomSanPham> iter = dsNhom.iterator();
		while (iter.hasNext()) {
			NhomSanPham value = iter.next();
			jcbPhanLoai.addItem(value);
		}
		hang1.add(jcbPhanLoai);
		hang1.add(txtTimKiem = new JTextField());
		txtTimKiem.setPreferredSize(dimTimKiem);
		hang1.add(btnTimKiem = new JButton("Tim kiem"));

		// hang 2
		hang2 = new JPanel();
		hang2.setLayout(new BorderLayout());
		JPanel khungLeft = new JPanel();
		khungLeft.setBorder(new TitledBorder("Ket qua"));
		// chen bang vao
		for (int i = 0; i < FrameQuanLyBanHang.tenCot.length; i++) {
			dTM_XoaNhieu.addColumn(FrameQuanLyBanHang.tenCot[i]);
		}
		table_KQ = new JTable(dTM_XoaNhieu);
		jscKhungLeft = new JScrollPane(table_KQ, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		Dimension dimtable = new Dimension(400, 270);
		jscKhungLeft.setPreferredSize(dimtable);
		khungLeft.add(jscKhungLeft);
		hang2.add(khungLeft, BorderLayout.WEST);
		table_KQ.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table_KQ.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_KQ.getColumnModel().getColumn(1).setPreferredWidth(90);
		table_KQ.getColumnModel().getColumn(2).setPreferredWidth(150);
		table_KQ.getColumnModel().getColumn(3).setPreferredWidth(90);
		table_KQ.getColumnModel().getColumn(4).setPreferredWidth(90);
		table_KQ.getColumnModel().getColumn(5).setPreferredWidth(130);
		
		JPanel khungRight = new JPanel();
		khungRight.setBorder(new TitledBorder("San pham da chon"));
		for (int i = 0; i < FrameQuanLyBanHang.tenCot.length; i++) {
			dTM_DaChon.addColumn(FrameQuanLyBanHang.tenCot[i]);
		}
		table_DaChon = new JTable(dTM_DaChon);
		jscKhungRight = new JScrollPane(table_DaChon, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jscKhungRight.setPreferredSize(dimtable);
		khungRight.add(jscKhungRight);
		hang2.add(khungRight, BorderLayout.EAST);
		table_DaChon.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table_DaChon.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_DaChon.getColumnModel().getColumn(1).setPreferredWidth(90);
		table_DaChon.getColumnModel().getColumn(2).setPreferredWidth(150);
		table_DaChon.getColumnModel().getColumn(3).setPreferredWidth(90);
		table_DaChon.getColumnModel().getColumn(4).setPreferredWidth(90);
		table_DaChon.getColumnModel().getColumn(5).setPreferredWidth(130);

		// hàng 4
		hang3 = new JPanel();
		hang3.add(btnXoa = new JButton("XOA"));

		pnChua.add(hang1, BorderLayout.NORTH);
		pnChua.add(hang2, BorderLayout.CENTER);
		pnChua.add(hang3, BorderLayout.SOUTH);

		JLabel lbver = new JLabel("Version 1.0");
		lbver.setAlignmentX(CENTER_ALIGNMENT);

		pnMain.add(lbTieuDe);
		pnMain.add(pnChua);
		pnMain.add(lbver);

		add(pnMain);
	}

	private void xuLiSuKien() {
		jcbPhanLoai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dTM_XoaNhieu.setRowCount(0);
				NhomSanPham nhomselected = (NhomSanPham) jcbPhanLoai.getSelectedItem();
				if (jcbPhanLoai.getSelectedIndex() == 0) {
					Iterator<SanPham> iters = FrameQuanLyBanHang.listSP.iterator();
					while (iters.hasNext()) {
						SanPham value = iters.next();
						Object[] obj = { value.getStt(), value.getId(), value.getTenSp(), value.getPhanLoai(),
								value.getSoLuong(), value.getNgayNhap() };
						dTM_XoaNhieu.addRow(obj);
					}
					resetDTM_XoaNhieu();
				} else {
					Iterator<SanPham> iter = FrameQuanLyBanHang.listSP.iterator();
					while (iter.hasNext()) {
						SanPham value = iter.next();
						if (value.getPhanLoai().getTenNhom() == nhomselected.getTenNhom()) {
							Object[] obj = { value.getStt(), value.getId(), value.getTenSp(),
									value.getPhanLoai().getTenNhom(), value.getSoLuong(), value.getNgayNhap() };
							dTM_XoaNhieu.addRow(obj);
						}
					}
					resetStt_XoaNhieu();
				}
			}
		});
		
		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int ID = Integer.parseInt(txtTimKiem.getText());
					if (FrameQuanLyBanHang.timKiemSP(ID)!=null) {
						SanPham value = FrameQuanLyBanHang.timKiemSP(ID);
//						rowSelected = value.getStt() - 1;
						Object[] obj = { value.getStt(), value.getId(), value.getTenSp(), value.getPhanLoai(),
								value.getSoLuong(), value.getNgayNhap() };
						dTM_XoaNhieu.setRowCount(0);
						dTM_XoaNhieu.addRow(obj);
					}else {
						JOptionPane.showMessageDialog(null, "Khong tim thay SP !");
						txtTimKiem.setText(null);
					}
				} catch (Exception e2) {
					txtTimKiem.setRequestFocusEnabled(true);
					JOptionPane.showMessageDialog(null, "ID khong hop le !");
					txtTimKiem.setText(null);
				}

			}
		});

		// su kien cho jtable
				table_KQ.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	 if (evt.getClickCount() == 2) {
		            int row=table_KQ.getSelectedRow();
		             System.out.println(row);
		             FrameQuanLyBanHang.testConsole();
		        }
		    }
		});
	
	}

	private void hienThi() {
		setTitle("Xoa nhieu SP");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void resetDTM_XoaNhieu() {
		Iterator<SanPham> iters = FrameQuanLyBanHang.listSP.iterator();
		dTM_XoaNhieu.setRowCount(0);
		while (iters.hasNext()) {
			SanPham value = iters.next();
			Object[] obj = { value.getStt(), value.getId(), value.getTenSp(), value.getPhanLoai(), value.getSoLuong(),
					value.getNgayNhap() };
			dTM_XoaNhieu.addRow(obj);
		}
	}
	private void resetStt_XoaNhieu() {
		for (int i = 0; i < dTM_XoaNhieu.getRowCount(); i++) {
			dTM_XoaNhieu.setValueAt(i+1,i,0);
		}
	}
}
