package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import View.FrameQuanLyBanHang.duLieu;
import controller.ArrayListSP;
import model.Date;
import model.NhomSanPham;
import model.SanPham;

public class FrameCongCu extends JDialog {
	JLabel lbMaHang, lbTen, lbLoaiHang, lbhang5, lbNgay, lbThang, lbNam, lbTieuDe;
	JTextField txtTimKiem, txtMaHang, txtTen, txtSoLuong, txtNgay, txtThang, txtNam;
	JPanel hang1, hang2, hang3, hang4, hang5, hang6, hang7;
	JRadioButton rdMacDinh, rdEdit;
	JButton btnThem, btnTimKiem, btnXoa, btnXoaNhieu, btnChinhSua, btnLuu;
	JComboBox<NhomSanPham> jcbPhanLoai;
	ArrayListSP<NhomSanPham> dsNhom;
	static int rowSelected;
	FrameCongCu_XoaNhieu xoaNhieuUI = new FrameCongCu_XoaNhieu();

	public FrameCongCu() {
		giaoDien();
		xuLiSuKien();
		hienThi();
	}

	private void setEdit() {
		// TODO Auto-generated method stub
		txtMaHang.setEditable(true);
		txtTen.setEditable(true);
		txtSoLuong.setEditable(true);
		txtNgay.setEditable(true);
		txtThang.setEditable(true);
		txtNam.setEditable(true);
		jcbPhanLoai.setEditable(true);
	}

	private void setNotEdit() {
		// TODO Auto-generated method stub
		txtMaHang.setEditable(false);
		txtTen.setEditable(false);
		txtSoLuong.setEditable(false);
		txtNgay.setEditable(false);
		txtThang.setEditable(false);
		txtNam.setEditable(false);
		jcbPhanLoai.setEditable(false);
	}

	private void giaoDien() {
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		lbTieuDe = new JLabel("XÓA  &  CHỈNH SỬA");
		lbTieuDe.setAlignmentX(CENTER_ALIGNMENT);

		// hàng 1
		hang1 = new JPanel();
		Dimension dimTimKiem = new Dimension(200, 27);
		hang1.add(txtTimKiem = new JTextField());
		txtTimKiem.setPreferredSize(dimTimKiem);
		hang1.add(btnTimKiem = new JButton("Tìm kiếm"));

		// hàng 2
		hang2 = new JPanel();
		Dimension dimlb3 = new Dimension(120, 20);
		Dimension dimtxt3 = new Dimension(300, 20);
		hang2.add(lbLoaiHang = new JLabel("Mã sản phẩm"));
		hang2.add(txtMaHang = new JTextField());
		lbLoaiHang.setPreferredSize(dimlb3);
		txtMaHang.setPreferredSize(dimtxt3);
		txtMaHang.setEditable(false);

		// hàng 3
		hang3 = new JPanel();
		lbTen = new JLabel("Tên sản phẩm");
		lbTen.setPreferredSize(dimlb3);
		txtTen = new JTextField();
		txtTen.setEditable(false);
		txtTen.setPreferredSize(dimtxt3);
		hang3.add(lbTen);
		hang3.add(txtTen);

		// hàng 4
		hang4 = new JPanel();
		lbLoaiHang = new JLabel("Loại hàng");
		lbLoaiHang.setPreferredSize(dimlb3);
		hang4.add(lbLoaiHang);
		jcbPhanLoai = new JComboBox<NhomSanPham>();
		jcbPhanLoai.setPreferredSize(dimtxt3);
//		
		hang4.add(jcbPhanLoai);

		// hàng 5
		Dimension dimlb5 = new Dimension(30, 20);
		Dimension dimtxt5 = new Dimension(60, 20);
		hang5 = new JPanel();
		txtSoLuong = new JTextField();
		txtNgay = new JTextField();
		txtNgay.setPreferredSize(dimtxt5);
		txtThang = new JTextField();
		txtThang.setPreferredSize(dimtxt5);
		txtNam = new JTextField();
		txtNam.setPreferredSize(dimtxt5);
		setNotEdit();
		hang5.add(lbhang5 = new JLabel("Số lượng"));
		hang5.add(txtSoLuong);
		hang5.add(lbhang5 = new JLabel("Nhập vào ngày"));
		hang5.add(txtNgay);
		hang5.add(lbhang5 = new JLabel("Tháng"));
		hang5.add(txtThang);
		hang5.add(lbhang5 = new JLabel("Năm"));
		hang5.add(txtNam);
		lbhang5.setPreferredSize(dimlb5);
		txtSoLuong.setPreferredSize(dimtxt5);

		// hàng 6
		hang6 = new JPanel();
		hang6.add(btnXoa = new JButton("XOA SAN PHAM"));
		hang6.add(btnChinhSua = new JButton("CHỈNH SỬA"));
		hang6.add(btnLuu = new JButton("LƯU"));
		hang6.add(btnXoaNhieu = new JButton("XÓA NHIỀU"));

		// hàng 7
		hang7 = new JPanel();
		JLabel lbVer = new JLabel("<html><i>Version 1.0</i></html>");
		lbVer.setAlignmentX(CENTER_ALIGNMENT);
		hang7.add(lbVer);

		add(lbTieuDe);
		add(hang1);
		add(hang2);
		add(hang3);
		add(hang4);
		add(hang5);
		add(hang6);
		add(hang7);
	}

	private void xuLiSuKien() {
		btnXoaNhieu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				xoaNhieuUI.setVisible(true);
				dispose();
			}
		});
//	 Tim kiem

		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					duLieu.taoDoiTuong();
					Iterator<SanPham> iter = FrameQuanLyBanHang.listSP.iterator();
					while (iter.hasNext()) {
						SanPham value = iter.next();

						int ID = Integer.parseInt(txtTimKiem.getText());
						if (value.getId() == ID) {
							rowSelected = value.getStt() - 1;
							txtMaHang.setText("" + ID);
							txtTen.setText(value.getTenSp());
							jcbPhanLoai.addItem(value.getPhanLoai());
							txtSoLuong.setText("" + value.getSoLuong());
							txtNgay.setText("" + value.getNgayNhap().getNgay());
							txtThang.setText("" + value.getNgayNhap().getThang());
							txtNam.setText("" + value.getNgayNhap().getNam());
						}

					}
				} catch (Exception e2) {
					txtTimKiem.setRequestFocusEnabled(true);
					JOptionPane.showMessageDialog(null, "ID khong ton tai");
				}

			}
		});
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rowSelected == -1)
					return;
				FrameQuanLyBanHang.dTM.removeRow(rowSelected);
				rowSelected = -1;
			}
		});
		btnChinhSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setEdit();
				dsNhom = new ArrayListSP<NhomSanPham>();
				dsNhom = duLieu.dsNhom;
				jcbPhanLoai.removeAllItems();
				Iterator<NhomSanPham> iter = dsNhom.iterator();
				while (iter.hasNext()) {
					NhomSanPham value = iter.next();
					jcbPhanLoai.addItem(value);
				}

			}

		});
		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SanPham sp = FrameQuanLyBanHang.listSP.get(rowSelected);
				sp.setId(Integer.parseInt(txtMaHang.getText()));
				sp.setTenSp(txtTen.getText());
				sp.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
				sp.setPhanLoai(new NhomSanPham("" + jcbPhanLoai.getSelectedItem()));
				sp.setNgayNhap(new Date(Integer.parseInt(txtNgay.getText()), Integer.parseInt(txtThang.getText()),
						Integer.parseInt(txtNam.getText())));
				// Xoa dong chinh sua
				FrameQuanLyBanHang.dTM.removeRow(rowSelected);
				// Tao va chen lai dong
				Object[] obj = { sp.getStt(), sp.getId(), sp.getTenSp(), sp.getPhanLoai(), sp.getSoLuong(),
						sp.getNgayNhap() };
				FrameQuanLyBanHang.dTM.insertRow(rowSelected, obj);
				setNotEdit();
			}
		});

	}

	private void hienThi() {
		setTitle("Công cụ");
		pack();
//		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
