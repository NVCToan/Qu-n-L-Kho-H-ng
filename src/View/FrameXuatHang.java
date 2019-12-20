package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.SanPham;

public class FrameXuatHang extends JDialog {
	JTextField txtTimKiem, txtSoLuong;
	static JTextField txtSLxuat;
	JButton btnTimKiem, btnHuy, btnXuat, btnOK;
	String[] tenCot = { "STT", "Ma hang", "Ten hang", "Loai hang", "So luong hien co", "So luong xuat" };
	JTable table;
	JPanel hang1, hang2, hang3, hang4;
	JLabel lbSoLuong;
	static DefaultTableModel dtmXuatHang = new DefaultTableModel() {
		// double click ma khong thay doi truong du lieu
		@Override
		public boolean isCellEditable(int row, int column) {
				return false;
		}
	};

	public FrameXuatHang() {
		giaoDien();
		xuLiSuKien();
		hienThi();
	}

	private void giaoDien() {
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		JLabel lbTieuDe = new JLabel("XUAT HANG");
		lbTieuDe.setAlignmentX(CENTER_ALIGNMENT);
		txtSLxuat = new JTextField();
		txtSLxuat.setColumns(10);
		// hàng 1
		hang1 = new JPanel();
		Dimension dimTxt = new Dimension(200, 27);
		txtTimKiem = new JTextField();
		txtTimKiem.setPreferredSize(dimTxt);
		btnTimKiem = new JButton("Tim kiem");
		hang1.add(new JLabel("Nhap ma hang"));
		hang1.add(txtTimKiem);
		hang1.add(btnTimKiem);

		// hàng2
		hang2 = new JPanel();
		hang2.setLayout(new BoxLayout(hang2, BoxLayout.Y_AXIS));
		for (int i = 0; i < tenCot.length; i++) {
			dtmXuatHang.addColumn(tenCot[i]);
		}
		//
		table = new JTable(dtmXuatHang);
//		table.setSize(600,300);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		//
		JScrollPane jsc = new JScrollPane(table);
		Dimension dimTable = new Dimension(604, 300);
		jsc.setPreferredSize(dimTable);
		hang2.add(jsc);
		JPanel hang2_1 = new JPanel();
		lbSoLuong = new JLabel("So luong");
		Dimension dimSL = new Dimension(40, 25);
		hang2_1.add(lbSoLuong);
		hang2_1.add(txtSLxuat = new JTextField());
		txtSLxuat.setPreferredSize(dimSL);
		hang2_1.add(btnOK = new JButton("OK"));
		hang2.add(hang2_1);

		// hàng 3
		hang3 = new JPanel();
		hang3.add(btnXuat = new JButton("XUAT"));
		hang3.add(btnHuy = new JButton("HUY"));

		// hàng 4
		hang4 = new JPanel();
		JLabel lbVer = new JLabel("<html><i>Version 1.0</i></html>");
		lbVer.setAlignmentX(CENTER_ALIGNMENT);
		hang4.add(lbVer);

		add(lbTieuDe);
		add(hang1);
		add(hang2);
		add(hang3);
		add(hang4);
	}

	private void hienThi() {
		setTitle("Xuat hang");
		pack();
		System.out.println(table.getWidth());

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void xuLiSuKien() {
		table.isCellEditable(0, 5);
		this.addWindowListener((WindowListener) new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				dtmXuatHang.setRowCount(0);
			}
		});
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int indexRowSelected = table.getSelectedRow();
				if (indexRowSelected == -1) {
					JOptionPane.showMessageDialog(null, "Vui long chon dong can xuat hang !");
				} else {
					try {
						int soLuongXuat = Integer.parseInt(txtSLxuat.getText());
						int SLhienCo = (int) table.getValueAt(indexRowSelected, 4);
						if (soLuongXuat > SLhienCo) {
							JOptionPane.showMessageDialog(null, "So luong xuat lon hon so luong hien co !");
							txtSLxuat.setText(null);
						} else {
							table.setValueAt(soLuongXuat, indexRowSelected, 5);
						}
						txtSLxuat.setText(null);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "So luong nhap khong hop le !");
					}
				}
			}
		});

		btnXuat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rowCount = table.getRowCount();
				if (rowCount < 1) {
					JOptionPane.showMessageDialog(null, "Khong tim thay SP xuat !");
				} else {
					for (int i = 0; i < dtmXuatHang.getRowCount(); i++) {
						int SLxuat = (int) table.getValueAt(i, 5);
						int SLhienCo = (int) table.getValueAt(i, 4);
						int idSP = (int) table.getValueAt(i, 1);
						SanPham value = FrameQuanLyBanHang.timKiemSPTheoMa(idSP);
						value.setSoLuong(SLhienCo - SLxuat);
						table.setValueAt(FrameQuanLyBanHang.listSP.get(value.getStt() - 1).getSoLuong(), i, 4);
						table.setValueAt(null, i, 5);
					}
					JOptionPane.showMessageDialog(null, "Thanh cong !");
					FrameQuanLyBanHang.resetDTM();
					FrameQuanLyBanHang.resetStt_DTM();
				}
			}
		});
		btnHuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int idTim = Integer.parseInt(txtTimKiem.getText());
					if (FrameQuanLyBanHang.timKiemSPTheoMa(idTim) != null) {
						SanPham SP = FrameQuanLyBanHang.timKiemSPTheoMa(idTim);
						Object[] obj = { SP.getStt(), SP.getId(), SP.getTenSp(), SP.getPhanLoai(), SP.getSoLuong() };
						dtmXuatHang.addRow(obj);
						resetSttDTM_XuatHang();
						txtTimKiem.setText(null);
					} else {
						JOptionPane.showMessageDialog(null, "Khong tim thay SP !");
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ID khong hop le !");
				}
			}
		});
	}

	public static void resetSttDTM_XuatHang() {

		int rowCount = dtmXuatHang.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			dtmXuatHang.setValueAt(i + 1, i, 0);
		}
	}

}
