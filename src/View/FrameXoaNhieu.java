package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


import View.FrameQuanLyBanHang.duLieu;
import controller.ArrayListSP;
import model.NhomSanPham;
import model.SanPham;

public class FrameXoaNhieu extends JDialog {
	JTextField txtTimKiem;
	JButton btnTimKiem, btnXoa;
	JTextArea areaKQ, areaXoa;
	JPanel pnMain, pnChua, hang1, hang2, hang3;
	JScrollPane jscKhungLeft, jscKhungRight;
	JComboBox<NhomSanPham> jcbPhanLoai;
	ArrayListSP<NhomSanPham> dsNhom;
	JTable table_XoaNhieu;
	DefaultTableModel dTM_XoaNhieu = new DefaultTableModel();

	public FrameXoaNhieu() {
		giaoDien();
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
//		Object[] obj = {"1","2","3","4","6"};
// 		dTM_XoaNhieu.addRow(obj);
		resetStt_DTM_XoaNhieu();
//		Iterator<SanPham> iterList = FrameQuanLyBanHang.listSP.iterator();
//		while (iterList.hasNext()) {
//			SanPham value = iterList.next();
//			Object[] obj = { value.getStt(), value.getId(), value.getTenSp(), value.getPhanLoai().getTenNhom(),
//					value.getSoLuong(), value.getNgayNhap() };
//			dTM_XoaNhieu.addRow(obj);
//		}
		table_XoaNhieu = new JTable(dTM_XoaNhieu);
		jscKhungLeft = new JScrollPane(table_XoaNhieu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		Dimension dimtable = new Dimension(400, 270);
		jscKhungLeft.setPreferredSize(dimtable);
		khungLeft.add(jscKhungLeft);
		hang2.add(khungLeft, BorderLayout.WEST);

		JPanel khungRight = new JPanel();
		khungRight.setBorder(new TitledBorder("San pham da chon"));
		areaXoa = new JTextArea(15, 45);
		jscKhungRight = new JScrollPane(areaXoa);
		khungRight.add(jscKhungRight);
		hang2.add(khungRight, BorderLayout.EAST);

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

	private void hienThi() {
		setTitle("Xoa nhieu SP");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void resetStt_DTM_XoaNhieu() {
		Iterator<SanPham> iter = FrameQuanLyBanHang.listSP.iterator();
		dTM_XoaNhieu.setRowCount(0);
		while (iter.hasNext()) {
			SanPham value = iter.next();
			Object[] obj = { value.getStt(), value.getId(), value.getTenSp(), value.getPhanLoai(), value.getSoLuong(),
					value.getNgayNhap() };
			dTM_XoaNhieu.addRow(obj);
		}
	}
}
