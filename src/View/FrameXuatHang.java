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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrameXuatHang extends JDialog {
	JTextField txtTimKiem;
	static JTextField txtSLxuat;
	JButton btnTimKiem, btnHuy, btnXuat;
	String[] tenCot = { "STT", "Ma hang", "Ten hang", "Loai hang", "So luong hien co", "So luong xuat" };
	JTable table;
	JPanel hang1, hang2, hang3, hang4;
	static DefaultTableModel dtmXuatHang = new DefaultTableModel() {
		// double click ma khong thay doi truong du lieu
		@Override
		public boolean isCellEditable(int row, int column) {
			if (column == 5)
				return true;
			else
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
		for (int i = 0; i < tenCot.length; i++) {
			dtmXuatHang.addColumn(tenCot[i]);
		}
		//
		table = new JTable(dtmXuatHang);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		//
		JScrollPane jsc = new JScrollPane(table);
		Dimension dimTable = new Dimension(400, 270);
		jsc.setPreferredSize(dimTable);
		hang2.add(jsc);

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
		btnXuat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

				} catch (Exception e2) {
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
