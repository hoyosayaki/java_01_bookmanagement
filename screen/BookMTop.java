package screen;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import action.DB;
import validate.Validate;

public class BookMTop implements ActionListener {

	JLabel labelr17, labelr18, labelr19, labelr20, shiftPID;
	JTextField txr1, txr2, txr3, txr4, txr5;
	JButton btntop, btnrch, btnrst, btnr, btnup, btndlt, btnir, btnr1, btnr2, btnr3, btnr4;

	JFrame bmframe, rframe;
	JTextField getkeyword;
	JPanel top, registerp, updatep;
	JTable tbl;
	DefaultTableModel model;

	JComboBox combo, cor6;
	String[] header = { "PID", "�^�C�g��", "�o�Ŏ�", "����", "�W������", "�]��", "�G���A", "�艿" };

	public void display_top() throws SQLException {
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Meiryo UI", Font.PLAIN, 20)));
		UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Meiryo UI", Font.PLAIN, 16)));

		bmframe = new JFrame("���Џ��o�^");
		bmframe.setBounds(0, 0, 1650, 1050);
		bmframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bmframe.setVisible(true);

		top = new JPanel();
		top.setLayout(null);
		top.setBounds(10, 10, 1600, 750);
		top.setVisible(true);
		Container con;
		con = bmframe.getContentPane();
		con.add(top);

		//���Јꗗ���擾
		DB.connect();
		List<String[]> bookinfo = DB.getbooklist();
		DB.close();
		model = new DefaultTableModel(bookinfo.toArray(new String[0][0]), header);
		tbl = new JTable(model);

		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//�����s�I���ł��Ȃ��悤�ɐݒ�
		tbl.setBounds(10, 250, 1550, 600);
		tbl.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		tbl.setRowHeight(28);

		JTableHeader jh = tbl.getTableHeader();
		jh.setFont(new Font("Meiryo UI", Font.PLAIN, 18));

		//��̕��𒲐�����
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl.getColumnModel().getColumn(0).setPreferredWidth(100);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(300);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(180);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(180);
		tbl.getColumnModel().getColumn(4).setPreferredWidth(150);
		tbl.getColumnModel().getColumn(5).setPreferredWidth(190);
		tbl.getColumnModel().getColumn(6).setPreferredWidth(80);
		tbl.getColumnModel().getColumn(7).setPreferredWidth(125);

		//�E����(�艿�j
		DefaultTableCellRenderer r_renderer = new DefaultTableCellRenderer();
		r_renderer.setHorizontalAlignment(SwingConstants.RIGHT);
		tbl.getColumnModel().getColumn(7).setCellRenderer(r_renderer);

		//���������i�G���A�j
		DefaultTableCellRenderer c_renderer = new DefaultTableCellRenderer();
		c_renderer.setHorizontalAlignment(SwingConstants.CENTER);
		tbl.getColumnModel().getColumn(6).setCellRenderer(c_renderer);
		JScrollPane sp;
		sp = new JScrollPane(tbl);
		sp.setBounds(30, 250, 1550, 600);

		top.add(sp);

		String[] combodata = { "�^�C�g��", "�o�Ŏ�", "����", "�W������" };
		combo = new JComboBox(combodata);
		combo.setBounds(30, 100, 200, 50);
		combo.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		getkeyword = new JTextField();
		getkeyword.setBounds(230, 100, 300, 50);
		getkeyword.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		top.add(combo);
		top.add(getkeyword);

		btntop = new JButton("TOP");
		btnrch = new JButton("���s");
		btnrst = new JButton("�N���A");
		btnr = new JButton("�V�K�o�^");
		btnup = new JButton("�ҏW");
		btndlt = new JButton("�폜");
		btnir = new JButton("�y���p�z�V�K�o�^");

		btntop.setBounds(30, 10, 100, 40);
		btnrch.setBounds(550, 100, 100, 40);
		btnrst.setBounds(670, 100, 100, 40);
		btnr.setBounds(550, 10, 100, 40);
		btnup.setBounds(670, 10, 100, 40);
		btndlt.setBounds(930, 10, 100, 40);
		btnir.setBounds(780, 10, 140, 40);

		btntop.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		btnrch.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		btnrst.setFont(new Font("Meiryo UI", Font.PLAIN, 18));
		btnr.setFont(new Font("Meiryo UI", Font.PLAIN, 14));
		btnup.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		btndlt.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		btnir.setFont(new Font("Meiryo UI", Font.PLAIN, 14));

		btntop.addActionListener(this);
		btnrch.addActionListener(this);
		btnrst.addActionListener(this);
		btnr.addActionListener(this);
		btnup.addActionListener(this);
		btndlt.addActionListener(this);
		btnir.addActionListener(this);

		JLabel label1, label2, label3;
		label1 = new JLabel("���v���_�E�����獀�ڂ�I�����āA�L�[���[�h����͂��Ă��������B");
		label1.setFont(new Font("Meiryo UI", Font.PLAIN, 18));
		label1.setBounds(30, 70, 600, 30);

		label2 = new JLabel("���V�K�o�^�̏ꍇ�́u�V�K�o�^�{�^���v�������Ă��������B");
		label3 = new JLabel("���ҏW�E�폜�̏ꍇ�́A�Ώۂ̏��Ђ�I�����Ă��ꂼ��̃{�^���������Ă��������B");
		label2.setBounds(30, 190, 800, 20);
		label3.setBounds(30, 210, 800, 20);
		label2.setFont(new Font("Meiryo UI", Font.PLAIN, 18));
		label3.setFont(new Font("Meiryo UI", Font.PLAIN, 18));

		top.add(btntop);
		top.add(btnrch);
		top.add(btnrst);
		top.add(btnr);
		top.add(btnup);
		top.add(btndlt);
		top.add(btnir);
		top.add(label1);
		top.add(label2);
		top.add(label3);

	}

	//�o�^�E�ҏW���
	public void iregisterpnl() {

		rframe = new JFrame("���Џ��o�^");
		rframe.setBounds(50, 50, 650, 670);
		rframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rframe.setVisible(true);

		registerp = new JPanel();
		registerp.setLayout(null);

		rframe.add(registerp);
		JLabel labelr1, labelr2, labelr3, labelr4, labelr5, labelr6, labelr7;
		labelr1 = new JLabel("�^�C�g���F");
		labelr2 = new JLabel("�o�ŎЁF");
		labelr3 = new JLabel("���ҁF");
		labelr4 = new JLabel("�W�������F");
		labelr5 = new JLabel("�艿�F");
		labelr6 = new JLabel("�G���A�F");

		labelr7 = new JLabel(); //�ҏW���󂯓n���p
		JLabel labelr11, labelr12, labelr13, labelr14, labelr15, labelr16;
		labelr11 = new JLabel("���S�p�E���p���͉�");
		labelr12 = new JLabel("���S�p�E���p���͉�");
		labelr13 = new JLabel("���S�p�E���p���͉�");
		labelr14 = new JLabel("���S�p�E���p���͉�");
		labelr15 = new JLabel("�����p�����œ��́B");
		labelr16 = new JLabel("���v���_�E�����I���B");
		labelr17 = new JLabel("���ő�S�p20������");
		labelr18 = new JLabel("���ő�S�p10������");
		labelr19 = new JLabel("���ő�S�p10������");
		labelr20 = new JLabel("���ő�S�p10������");

		txr1 = new JTextField();
		txr2 = new JTextField();
		txr3 = new JTextField();
		txr4 = new JTextField();
		txr5 = new JTextField();

		String[] arealist = { "A", "B", "C", "D", "E", "F", "G", "H" };
		cor6 = new JComboBox(arealist);

		labelr1.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelr2.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelr3.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelr4.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelr5.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelr6.setFont(new Font("Meiryo UI", Font.PLAIN, 25));

		labelr11.setFont(new Font("Meiryo UI", Font.PLAIN, 15));
		labelr12.setFont(new Font("Meiryo UI", Font.PLAIN, 15));
		labelr13.setFont(new Font("Meiryo UI", Font.PLAIN, 15));
		labelr14.setFont(new Font("Meiryo UI", Font.PLAIN, 15));
		labelr15.setFont(new Font("Meiryo UI", Font.PLAIN, 15));
		labelr16.setFont(new Font("Meiryo UI", Font.PLAIN, 15));

		labelr17.setFont(new Font("Meiryo UI", Font.PLAIN, 17));
		labelr18.setFont(new Font("Meiryo UI", Font.PLAIN, 17));
		labelr19.setFont(new Font("Meiryo UI", Font.PLAIN, 17));
		labelr20.setFont(new Font("Meiryo UI", Font.PLAIN, 17));

		txr1.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		txr2.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		txr3.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		txr4.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		txr5.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		cor6.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		labelr1.setBounds(15, 40, 100, 25);
		labelr11.setBounds(115, 43, 400, 25);
		txr1.setBounds(15, 70, 400, 32);
		labelr2.setBounds(15, 120, 100, 25);
		labelr12.setBounds(115, 123, 400, 25);
		txr2.setBounds(15, 150, 400, 32);
		labelr3.setBounds(15, 200, 100, 25);
		labelr13.setBounds(115, 203, 400, 25);
		txr3.setBounds(15, 230, 400, 32);
		labelr4.setBounds(15, 280, 120, 25);
		labelr14.setBounds(115, 283, 400, 25);
		txr4.setBounds(15, 310, 400, 32);
		labelr5.setBounds(15, 360, 100, 25);
		labelr15.setBounds(115, 363, 400, 25);
		txr5.setBounds(15, 390, 400, 32);
		labelr6.setBounds(15, 440, 100, 25);
		labelr16.setBounds(115, 443, 400, 25);
		cor6.setBounds(15, 470, 400, 32);

		labelr17.setBounds(435, 70, 400, 32);
		labelr18.setBounds(435, 150, 400, 32);
		labelr19.setBounds(435, 230, 400, 32);
		labelr20.setBounds(435, 310, 400, 32);

		btnr2 = new JButton("���");
		btnr2.setBounds(440, 550, 100, 40);
		btnr2.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		registerp.add(labelr1);
		registerp.add(labelr2);
		registerp.add(labelr3);
		registerp.add(labelr4);
		registerp.add(labelr5);
		registerp.add(labelr6);
		registerp.add(labelr7);
		registerp.add(labelr11);
		registerp.add(labelr12);
		registerp.add(labelr13);
		registerp.add(labelr14);
		registerp.add(labelr15);
		registerp.add(labelr16);
		registerp.add(labelr17);
		registerp.add(labelr18);
		registerp.add(labelr19);
		registerp.add(labelr20);

		registerp.add(btnr2);
		registerp.add(txr1);
		registerp.add(txr2);
		registerp.add(txr3);
		registerp.add(txr4);
		registerp.add(txr5);
		registerp.add(cor6);

		btnr2.addActionListener(this);
	}

	//�̔Ԃ���PID�ԍ���
	public String getsetPID() throws SQLException {
		DB.connect();
		int max = DB.getMaxPid();
		DB.close();
		String setPID = "";
		if(max < 9) {
			setPID = "000" + (max + 1);
		} else if(max < 99) {
			setPID = "00" + (max + 1);
		} else if(max < 999) {
			setPID = "0" + (max + 1);
		} else {
			setPID = Integer.toString(max + 1);
		}
		return setPID;
	}

	public String[] registercheck() throws UnsupportedEncodingException {
		String registerinfo[] = null;
		//���͂��ꂽ�l�̎擾
		String title = txr1.getText();
		String publisher = txr2.getText();
		String author = txr3.getText();
		String genre = txr4.getText();
		String price = txr5.getText();

		//�o�C�g��
		int titlebyt = title.getBytes("Shift-JIS").length;
		int publisherbyt = publisher.getBytes("Shift-JIS").length;
		int authorbyt = author.getBytes("Shift-JIS").length;
		int genrebyt = genre.getBytes("Shift-JIS").length;

		//���p�����`�F�b�N
		Pattern p = Pattern.compile("[^0123456789]");
		Matcher m = p.matcher(price);

		if(title.length() == 0 || publisher.length() == 0 || author.length() == 0 || genre.length() == 0 || price.length() == 0) {
			Validate.Null();
		} else if(price.length() > 9) {
			Validate.numcheck();
		} else if(m.find()) {//���p�����`�F�b�N
			Validate.numcheck();
		} else if(titlebyt > 40 || publisherbyt > 20 || authorbyt > 20 || genrebyt > 20) {
			Validate.cntover();
			labelr17.setForeground(Color.red);
			labelr18.setForeground(Color.red);
			labelr19.setForeground(Color.red);
			labelr20.setForeground(Color.red);

			if(titlebyt > 40) {
				labelr17.setText(titlebyt - 40 + "�o�C�g�I�[�o�[");
			} else {
				labelr17.setText("OK");
			}
			if(publisherbyt > 20) {
				labelr18.setText(publisherbyt - 20 + "�o�C�g�I�[�o�[");
			} else {
				labelr18.setText("OK");
			}
			if(authorbyt > 20) {
				labelr19.setText(authorbyt - 20 + "�o�C�g�I�[�o�[");
			} else {
				labelr19.setText("OK");
			}
			if(genrebyt > 20) {
				labelr20.setText(genrebyt - 20 + "�o�C�g�I�[�o�[");
			} else {
				labelr20.setText("OK");
			}

		} else {
			registerinfo = new String[6];
			registerinfo[0] = txr1.getText();
			registerinfo[1] = txr2.getText();
			registerinfo[2] = txr3.getText();
			registerinfo[3] = txr4.getText();
			registerinfo[4] = txr5.getText();
			registerinfo[5] = cor6.getSelectedItem().toString();//selectarea

		}
		return registerinfo;
	}

	public void setbookTable() throws SQLException {
		DB.connect();
		List<String[]> bookinfo = DB.getbooklist();
		DB.close();

		model.setRowCount(0);
		for(int i = 0; i < bookinfo.size(); i++) {
			model.addRow(bookinfo.get(i));
		}

	}

	public void reserch() throws SQLException {
		String keyword = getkeyword.getText();	//���͂����L�[���[�h
		String item = null;
		String selectItem = (String) combo.getSelectedItem(); //�v���_�E������I����������
		if(selectItem.equals("�^�C�g��")) {
			item = "title";
		} else if(selectItem.equals("�o�Ŏ�")) {
			item = "publisher";
		} else if(selectItem.equals("����")) {
			item = "author";
		} else if(selectItem.equals("�W������")) {
			item = "genre";
		}
		DB.connect();
		List<String[]> bookreserchinfo = DB.reserchbook(keyword, item);
		DB.close();
		if(bookreserchinfo.isEmpty()) {
			JOptionPane.showMessageDialog(rframe, "�����Ɉ�v���鏑�Ђ͂���܂���B");
		} else {
			model.setRowCount(0);
			for(int i = 0; i < bookreserchinfo.size(); i++) {
				model.addRow(bookreserchinfo.get(i));
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// �����ɃR�[�h��}��
		int row = tbl.getSelectedRow(); //�I�����ꂽ�s

		if(e.getSource() == btntop) {		//TOP
			bmframe.dispose();
			Top top = new Top();
			try {
				top.Top();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if(e.getSource() == btnrch) {		//�������s�{�^��
			try {
				reserch();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if(e.getSource() == btnrst) {		//�������Z�b�g�{�^��
			try {
				setbookTable();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			getkeyword.setText("");

		} else if(e.getSource() == btnr) {	//�V�K�o�^�{�^��
			iregisterpnl();

			btnr1 = new JButton("�o�^");
			btnr1.setBounds(320, 550, 100, 40);
			btnr1.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
			btnr1.addActionListener(this);
			registerp.add(btnr1);

		} else if(e.getSource() == btnir) {	//���p�[�V�K�o�^�{�^��
			row = tbl.getSelectedRow();
			if(row == -1) {		//�ǂ̃Z�����I������Ă��Ȃ��ꍇ�̃G���[����
				Validate.noSelect();
			} else {
				String selectT = tbl.getValueAt(row, 1).toString();//�I�����Ă���s�̃^�C�g��
				String selectP = tbl.getValueAt(row, 2).toString();//�I�����Ă���s�̏o�Ŏ�
				String selectA = tbl.getValueAt(row, 3).toString();//�I�����Ă���s�̒���
				String selectG = tbl.getValueAt(row, 4).toString();//�I�����Ă���s�̃W������
				String selectPr = tbl.getValueAt(row, 7).toString();//�I�����Ă���s�̒艿
				String selectAr = tbl.getValueAt(row, 6).toString();//�I�����Ă���s�̃G���A

				iregisterpnl();
				btnr3 = new JButton("�o�^");
				btnr3.setBounds(320, 550, 100, 40);
				btnr3.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
				btnr3.addActionListener(this);
				registerp.add(btnr3);

				//�e�L�X�g�t�B�[���h�ɒl���Z�b�g����
				txr1.setText(selectT);
				txr2.setText(selectP);
				txr3.setText(selectA);
				txr4.setText(selectG);
				txr5.setText((selectPr.substring(1)).replace(",", ""));//�ʉ݌`���ː��l�ւ̕ϊ�
				cor6.setSelectedItem(selectAr);

			}
		} else if(e.getSource() == btnup) {	//�ҏW�{�^��
			row = tbl.getSelectedRow();
			String PID = tbl.getValueAt(row, 0).toString();
			if(row == -1) {		//�ǂ̃Z�����I������Ă��Ȃ��ꍇ�̃G���[����
				Validate.noSelect();
			} else {
				String selectT = tbl.getValueAt(row, 1).toString();//�I�����Ă���s�̃^�C�g��
				String selectP = tbl.getValueAt(row, 2).toString();//�I�����Ă���s�̏o�Ŏ�
				String selectA = tbl.getValueAt(row, 3).toString();//�I�����Ă���s�̒���
				String selectG = tbl.getValueAt(row, 4).toString();//�I�����Ă���s�̃W������
				String selectPr = tbl.getValueAt(row, 7).toString();//�I�����Ă���s�̒艿
				String selectAr = tbl.getValueAt(row, 6).toString();//�I�����Ă���s�̃G���A

				iregisterpnl();
				btnr4 = new JButton("�o�^");
				btnr4.setBounds(320, 550, 100, 40);
				btnr4.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
				btnr4.addActionListener(this);
				registerp.add(btnr4);
				shiftPID = new JLabel(PID);

				//�e�L�X�g�t�B�[���h�ɒl���Z�b�g����
				txr1.setText(selectT);
				txr2.setText(selectP);
				txr3.setText(selectA);
				txr4.setText(selectG);
				txr5.setText((selectPr.substring(1)).replace(",", ""));//�ʉ݌`���ː��l�ւ̕ϊ�
				cor6.setSelectedItem(selectAr);

			}

		} else if(e.getSource() == btndlt) {//�폜�{�^��
			row = tbl.getSelectedRow();

			if(row == -1) {
				Validate.noSelect();
			} else {
				String PID = tbl.getValueAt(row, 0).toString();
				int option = JOptionPane.showConfirmDialog(null, "�{���ɍ폜���܂����H",
						"DELETE�m�F", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(option == JOptionPane.OK_OPTION) {
					try {
						DB.connect();
						DB.delete(PID);
						DB.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					//�폜�����s���폜����
					model.removeRow(row);
				}
			}
		} else if(e.getSource() == btnr3 || e.getSource() == btnr1) {	//�V�K�o�^-�o�^�{�^��

			try {
				String[] registerinfo = registercheck();
				if(registerinfo != null) {
					String setNum = getsetPID();
					DB.connect();
					DB.bookregister(setNum, registerinfo[0], registerinfo[1], registerinfo[2], registerinfo[3], registerinfo[4], registerinfo[5]);
					DB.close();
					rframe.dispose();
					setbookTable();
					JOptionPane.showMessageDialog(bmframe, "�o�^�������܂����B");
				}
			} catch (UnsupportedEncodingException | SQLException e1) {
				e1.printStackTrace();
			}

		} else if(e.getSource() == btnr2) {	//�V�K�o�^-����{�^��
			rframe.dispose();
		} else if(e.getSource() == btnr4) {	//�ҏW-�o�^�{�^��
			String PID = shiftPID.getText();
			try {
				String[] registerinfo = registercheck();
				if(registerinfo != null) {
					DB.connect();
					DB.bookupdate(registerinfo[0], registerinfo[1], registerinfo[2], registerinfo[3], registerinfo[4], registerinfo[5], PID);
					DB.close();
					rframe.dispose();
					setbookTable();
					JOptionPane.showMessageDialog(bmframe, "�o�^�������܂����B");
				}
			} catch (UnsupportedEncodingException | SQLException e1) {
				e1.printStackTrace();
			}

		}
	}
}
