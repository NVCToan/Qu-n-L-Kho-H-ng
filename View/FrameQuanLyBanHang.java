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
import java.util.ArrayList;
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
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import controller.ArrayListSP;
import model.NhomSanPham;
import model.SanPham;

public class FrameQuanLyBanHang extends JFrame {

	public JButton btnThemHang, btnXoa, btnTimKiem, btnCongCu, btnXuatHang;
	JLabel lbTieuDe, lbLoaiHang, lbversion;
	JPanel hang1, hang1_1, hang1_2, hang2, hang3;
	JTextField txtTimKiem;
	static DefaultTableModel dTM = new DefaultTableModel();
	static JTable table;
	String[] tenCot = { "Sá»‘ thá»© tá»±", "MÃ£ HÃ ng", "TÃªn HÃ ng", "Loáº¡i HÃ ng", "Sá»‘ LÆ°á»£ng", "NgÃ y Nháº­p" };
	String[] listPhanLoai = {"Loai 1", "Loai 2", "Loai 3"};
	JComboBox<String> jcbLoaiHang = new JComboBox<String>(listPhanLoai); // Sá»­a láº¡i kiá»ƒu dá»¯ liá»‡u
	FrameThemHang themHangUI = new FrameThemHang();
	FrameXuatHang xuatHangUI = new FrameXuatHang();
	FrameCongCu congCu = new FrameCongCu();
	//ArrayListSP<SanPham> listSP = new ArrayListSP<SanPham>();// suc chua mac dinh
	public static ArrayList<SanPham> list = new ArrayList<SanPham>();
	
	public FrameQuanLyBanHang() {
		super("Quáº£n lÃ½ kho hÃ ng");
		giaoDien();
		xuLiSuKien();
		hienThi();
	}

	private void taoDoiTuong() {// dung trong function giaoDien
		SanPham sp1 = new SanPham(1,10000 ,"San Pham 1", "Loai 1", 20, new model.Date(1, 1, 2000));
//		SanPham sp2 = new SanPham(++SanPham.count,"#"+SanPham.count_Id++ ,"San Pham 2", "Loai 2", 21, new model.Date(17, 12, 2000));
//		SanPham sp3 = new SanPham(++SanPham.count,"#"+SanPham.count_Id++ ,"San Pham 3", "Loai 2", 15, new model.Date(6, 10, 2000));
//		SanPham sp4 = new SanPham(++SanPham.count,"#"+SanPham.count_Id++ ,"San Pham 4", "Loai 3", 10, new model.Date(9, 8, 2000));
//		SanPham sp5 = new SanPham(++SanPham.count,"#"+SanPham.count_Id++ ,"San Pham 5", "Loai 1", 76, new model.Date(1, 5, 2000));
		list.add(sp1);
//		list.add(sp2);
//		list.add(sp3);
//		list.add(sp4);
//		list.add(sp5);
	}
	
	private void giaoDien() {

		
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		// tieu de
		lbTieuDe = new JLabel("QUáº¢N LÃ½ KHO HÃ€NG");
		lbTieuDe.setAlignmentX(CENTER_ALIGNMENT);

		// hang 1
		Dimension dimTxT = new Dimension(130, 25);
		Dimension dimButton = new Dimension(30, 25);
		hang1 = new JPanel(new FlowLayout());
		btnThemHang = new JButton("ThÃªm hÃ ng");
		btnXoa = new JButton("XoÃ¡");
		hang1.add(btnThemHang);
		hang1.add(btnXoa);
		hang1_1 = new JPanel();
		txtTimKiem = new JTextField();
		txtTimKiem.setPreferredSize(dimTxT);
		hang1_1.add(txtTimKiem);
		hang1_1.add(btnTimKiem = new JButton("TÃ¬m kiáº¿m"));
		hang1.add(hang1_1);
		hang1_2 = new JPanel();
		hang1_2.add(lbLoaiHang = new JLabel("Loáº¡i hÃ ng"));
		jcbLoaiHang.setPreferredSize(dimTxT);
		hang1_2.add(jcbLoaiHang);
		hang1.add(hang1_2);
		hang1.add(btnCongCu = new JButton("CÃ´ng cá»¥"));
		hang1.add(btnXuatHang = new JButton("Xuáº¥t hÃ ng"));
		
		// hang2
		hang2 = new JPanel();
		for (int i = 0; i < tenCot.length; i++) {
			dTM.addColumn(tenCot[i]);
		}
		//them sp
		taoDoiTuong();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = {list.get(i).getStt(),list.get(i).getId(),list.get(i).getTenSp(),list.get(i).getPhanLoai(),list.get(i).getSoLuong(),list.get(i).getNgayNhap()};
			dTM.addRow(obj);
		}
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
		// ThÃªm hÃ ng
		btnThemHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				themHangUI.txtMaHang.setText(""+SanPham.count_id);
				themHangUI.txtStt.setText(""+SanPham.count_stt);
				themHangUI.setModal(true); // chá»©c nÄƒng Ä‘á»ƒ frame cÃ³ thá»ƒ á»Ÿ lá»›p trÃªn máº·t (dÃ¹ng Ä‘Æ°á»£c khi Ä‘Ã£ extends
											// JDialog)
				themHangUI.setVisible(true);
			}
		});

		// xuáº¥t hÃ ng
		btnXuatHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				xuatHangUI.setModal(true); // chá»©c nÄƒng Ä‘á»ƒ frame cÃ³ thá»ƒ á»Ÿ lá»›p trÃªn máº·t (dÃ¹ng Ä‘Æ°á»£c khi Ä‘Ã£ extends
											// JDialog)
				xuatHangUI.setVisible(true);
			}
		});

		// cÃ´ng cá»¥
		btnCongCu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				congCu.setModal(true); // chá»©c nÄƒng Ä‘á»ƒ frame cÃ³ thá»ƒ á»Ÿ lá»›p trÃªn máº·t (dÃ¹ng Ä‘Æ°á»£c khi Ä‘Ã£ extends JDialog)
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
				dTM.removeRow(row); // XoÃ¡ dÃ²ng Ä‘ang chá»�n

			}
		});
	}
}
//		jcbLoaiHang.addMouseListener(new MouseListener() {
//			
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				if(jcbLoaiHang.getSelectedIndex()==-1) return;
//				nhomSelected = (NhomSanPham) jcbLoaiHang.getSelectedItem();
////				for(int i =0 ; i<nhomSelected.getDsSanPham().size();i++) {
////					dTM.addRow(nhomSelected.getDsSanPham());
////				}
//				dTM.addRow(nhomSelected.getDsSanPham());
//			}
//		});
//
//	}


