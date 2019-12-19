package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
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

public class FrameChinhSua extends JDialog {
	JLabel lbMaHang, lbTen, lbLoaiHang, lbhang5, lbNgay, lbThang, lbNam, lbTieuDe;
	JTextField txtTimKiem, txtMaHang, txtTen, txtSoLuong, txtNgay, txtThang, txtNam, txtThemLoaiHang;
	JPanel hang1, hang2, hang3, hang4, hang4_1, hang5, hang6, hang6_1, hang6_2, hang7;
	JButton btnThem, btnTimKiem, btnChinhSua, btnLuu, btnHuy;
	JComboBox<NhomSanPham> jcbPhanLoai;
	ArrayListSP<NhomSanPham> dsNhom;
	JRadioButton rdThemPhanLoai, rdSuaMaHang, rdSuaTenHang, rdDoiPhanLoai, rdSuaSoLuongVaNgayNhap;
	static int rowSelected;
	static String loai;

	public FrameChinhSua() {
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
		btnHuy.setEnabled(true);
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
		lbTieuDe = new JLabel("XOA VA CHINH SUA");
		lbTieuDe.setAlignmentX(CENTER_ALIGNMENT);

		// hàng 1
		hang1 = new JPanel();
		txtTimKiem = new JTextField();
		btnTimKiem = new JButton("Tim kiem");
		Dimension dimTimKiem = new Dimension(200, 27);
		txtTimKiem.setPreferredSize(dimTimKiem);
		hang1.add(txtTimKiem);
		hang1.add(btnTimKiem);

		// hàng 2
		hang2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbTen = new JLabel("Ten san pham");

		Dimension dimlb = lbTen.getPreferredSize();
		Dimension dimtxt = new Dimension(300, 20);

		lbMaHang = new JLabel("Ma san pham");
		txtMaHang = new JTextField();
		rdSuaMaHang = new JRadioButton("Sua ma hang");

		lbMaHang.setPreferredSize(dimlb);
		txtMaHang.setPreferredSize(dimtxt);
		txtMaHang.setEditable(false);

		hang2.add(lbMaHang);
		hang2.add(txtMaHang);
		hang2.add(rdSuaMaHang);

		// hàng 3
		hang3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		txtTen = new JTextField();
		txtTen.setEditable(false);
		rdSuaTenHang = new JRadioButton("Sua ten hang");
		txtTen.setPreferredSize(dimtxt);
		hang3.add(lbTen);
		hang3.add(txtTen);
		hang3.add(rdSuaTenHang);

		// hàng 4
		hang4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		hang4_1 = new JPanel(new GridLayout(0, 1));
		lbLoaiHang = new JLabel("Loai hang");
		lbLoaiHang.setPreferredSize(dimlb);
		txtThemLoaiHang = new JTextField();
		txtThemLoaiHang.setPreferredSize(dimtxt);
		txtThemLoaiHang.setVisible(false);

		rdThemPhanLoai = new JRadioButton("Phan loai moi");
		rdDoiPhanLoai = new JRadioButton("Thay doi phan loai");
		ButtonGroup gr = new ButtonGroup();
		gr.add(rdDoiPhanLoai);
		gr.add(rdThemPhanLoai);
		hang4_1.add(rdDoiPhanLoai);
		hang4_1.add(rdThemPhanLoai);
		jcbPhanLoai = new JComboBox<NhomSanPham>();
		jcbPhanLoai.setPreferredSize(dimtxt);
		hang4.add(lbLoaiHang);
		hang4.add(jcbPhanLoai);
		hang4.add(txtThemLoaiHang);
		hang4.add(hang4_1);

		// hàng 5
		Dimension dimlb5 = new Dimension(30, 20);
		Dimension dimtxt5 = new Dimension(60, 20);
		hang5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		txtSoLuong = new JTextField();
		txtNgay = new JTextField();
		txtNgay.setPreferredSize(dimtxt5);
		txtThang = new JTextField();
		txtThang.setPreferredSize(dimtxt5);
		txtNam = new JTextField();
		txtNam.setPreferredSize(dimtxt5);
		setNotEdit();
		hang5.add(lbhang5 = new JLabel("So luong"));
		hang5.add(txtSoLuong);
		hang5.add(lbhang5 = new JLabel("Nhap vao ngay"));
		hang5.add(txtNgay);
		hang5.add(lbhang5 = new JLabel("Thang"));
		hang5.add(txtThang);
		hang5.add(lbhang5 = new JLabel("Nam"));
		rdSuaSoLuongVaNgayNhap = new JRadioButton();
		hang5.add(txtNam);
		hang5.add(rdSuaSoLuongVaNgayNhap);
		lbhang5.setPreferredSize(dimlb5);
		txtSoLuong.setPreferredSize(dimtxt5);

		// hàng 6
		hang6 = new JPanel();
		hang6_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		hang6_1.add(btnChinhSua = new JButton("Chinh sua tat ca"));
		btnChinhSua.setEnabled(false);
		hang6_2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnLuu = new JButton("Luu");
		btnHuy = new JButton("Phuc hoi");
		hang6_2.add(btnLuu);
		hang6_2.add(btnHuy);
		btnLuu.setEnabled(false);
		btnHuy.setEnabled(false);
		hang6.add(hang6_1);
		hang6.add(hang6_2);

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
//	 Tim kiem

		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int ID = Integer.parseInt(txtTimKiem.getText());
					if (FrameQuanLyBanHang.timKiemSPTheoMa(ID) != null) {
						SanPham value = FrameQuanLyBanHang.timKiemSPTheoMa(ID);
						rowSelected = value.getStt() - 1;
						txtMaHang.setText("" + ID);
						txtTen.setText(value.getTenSp());
						jcbPhanLoai.removeAllItems();

						jcbPhanLoai.addItem(value.getPhanLoai());
						txtSoLuong.setText("" + value.getSoLuong());
						txtNgay.setText("" + value.getNgayNhap().getNgay());
						txtThang.setText("" + value.getNgayNhap().getThang());
						txtNam.setText("" + value.getNgayNhap().getNam());
						btnChinhSua.setEnabled(true);
						btnLuu.setEnabled(true);
						btnHuy.setEnabled(true);

					} else {
						JOptionPane.showMessageDialog(null, "Khong tim thay SP !");
					}
				} catch (Exception e2) {
					txtTimKiem.setRequestFocusEnabled(true);
					JOptionPane.showMessageDialog(null, "ID khong hop le !");
				}
			}
		});
		btnChinhSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setEdit();
				jcbPhanLoai.setEditable(false);
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
				try {
					if (rdThemPhanLoai.isSelected()) {
						loai = txtThemLoaiHang.getText();
						duLieu.themPhanLoai(new NhomSanPham(loai));

						FrameQuanLyBanHang.jcbLoaiHang.addItem(new NhomSanPham(loai));
						jcbPhanLoai.addItem(new NhomSanPham(loai));
					} else {
						NhomSanPham nhomSelected = (NhomSanPham) jcbPhanLoai.getSelectedItem();
						loai = nhomSelected.getTenNhom();
					}
					SanPham sp = FrameQuanLyBanHang.listSP.get(rowSelected);
					sp.setId(Integer.parseInt(txtMaHang.getText()));
					sp.setTenSp(txtTen.getText());
					sp.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
					sp.setPhanLoai(new NhomSanPham(loai));
					sp.setNgayNhap(new Date(Integer.parseInt(txtNgay.getText()), Integer.parseInt(txtThang.getText()),
							Integer.parseInt(txtNam.getText())));
					FrameQuanLyBanHang.resetStt_SP();
					FrameQuanLyBanHang.resetDTM();
					FrameQuanLyBanHang.resetStt_DTM();

					setNotEdit();
				} catch (Exception e2) {
					txtMaHang.setRequestFocusEnabled(true);
					JOptionPane.showMessageDialog(null,
							"Mã hàng, Số lượng, Ngày, tháng, năm phải là kiểu số nguyên !!");
				}

			}
		});
		btnHuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(txtTimKiem.getText());
				txtThemLoaiHang.setText(null);
				rdThemPhanLoai.setSelected(false);
				txtThemLoaiHang.setVisible(false);
				jcbPhanLoai.setVisible(true);
				if (FrameQuanLyBanHang.timKiemSPTheoMa(ID) != null) {
					SanPham value = FrameQuanLyBanHang.timKiemSPTheoMa(ID);
					txtMaHang.setText("" + ID);
					txtTen.setText(value.getTenSp());
					jcbPhanLoai.removeAllItems();
					jcbPhanLoai.addItem(value.getPhanLoai());
					txtSoLuong.setText("" + value.getSoLuong());
					txtNgay.setText("" + value.getNgayNhap().getNgay());
					txtThang.setText("" + value.getNgayNhap().getThang());
					txtNam.setText("" + value.getNgayNhap().getNam());
				}
				setNotEdit();
			}
		});
		rdThemPhanLoai.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (rdThemPhanLoai.isSelected()) {
					jcbPhanLoai.setVisible(false);
					txtThemLoaiHang.setVisible(true);
				} else {
					jcbPhanLoai.setVisible(true);
					txtThemLoaiHang.setVisible(false);
				}
			}
		});
		rdDoiPhanLoai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdDoiPhanLoai.isSelected()) {
					txtThemLoaiHang.setVisible(false);
					jcbPhanLoai.setVisible(true);
					dsNhom = new ArrayListSP<NhomSanPham>();
					dsNhom = duLieu.dsNhom;
					jcbPhanLoai.removeAllItems();
					Iterator<NhomSanPham> iter = dsNhom.iterator();
					while (iter.hasNext()) {
						NhomSanPham value = iter.next();
						jcbPhanLoai.addItem(value);
					}
				}
			}
		});
		rdSuaMaHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdSuaMaHang.isSelected()) {

					txtMaHang.setEditable(true);
				} else
					txtMaHang.setEditable(false);
			}
		});
		rdSuaTenHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdSuaTenHang.isSelected()) {

					txtTen.setEditable(true);
				} else
					txtTen.setEditable(false);
			}
		});
		rdSuaSoLuongVaNgayNhap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdSuaSoLuongVaNgayNhap.isSelected()) {
					txtSoLuong.setEditable(true);
					txtNgay.setEditable(true);
					txtThang.setEditable(true);
					txtNam.setEditable(true);
				} else {
					txtSoLuong.setEditable(false);
					txtNgay.setEditable(false);
					txtThang.setEditable(false);
					txtNam.setEditable(false);
				}

			}
		});

	}

	private void hienThi() {
		setTitle("Công cụ");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
