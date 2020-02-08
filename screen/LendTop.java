package screen;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import action.DB;
import validate.Validate;

public class LendTop implements ActionListener, MouseListener {

	JFrame frame, lendframe, borrowframe, rtnframe, extenframe;
	JPanel top, borrow, rtn, exten;
	JLabel labelT1, labelT2, labelT3, labelT4, labelT5;
	JLabel labelB1, labelB2, labelB3, labelB4, labelB5, labelB7, labelB8;
	JLabel labelR1, labelR2, labelR3, labelR4, labelR5, labelR6, labelR7, labelR8;//R7�͎󂯓n���p
	JLabel labelE1, labelE2, labelE3, labelE4, labelE5, labelE6;//E6�͎󂯓n���p
	JSpinner spinner1, spinner2, spinner3, spinner4;
	JButton btntop, btnrch, btnrst, btnbr, btnrtn, hbtn, btnext, btntop1, btnLcl, btnRr, btnRcl, btnEr, btnEcl, btn1, btnc1, btnc2, btnc3;
	ButtonGroup group, groupe;
	JTextField tx1, tx11;
	SpinnerDateModel model1, model2, model3, model4;
	JComboBox serchcmb;
	JRadioButton eover2, eover3, eover4, all2, lendOK, borrowing, reserving, all;
	JLabel star1, star2, star3, star4, star5;

	String totalPrice;
	JLabel labelbar, label0, label1, label2, label3, label4, label5, label6, label7, label8, label9, labelmesg, labelmesg2;
	JLabel label0r, label3r, label4r, label5r, label6r;
	JLabel label2a, label3a, label4a, label5a;
	String cancelnum1 = "";
	String cancelnum2 = "";
	String cancelnum3 = "";

	JTable tbl;
	Container con;
	DefaultTableModel model;
	//mouseclisck�p(�ԋp���̕]���j
	int cnt1 = 0;
	int cnt2 = 0;
	int cnt3 = 0;
	int cnt4 = 0;
	int cnt5 = 0;
	int eval1 = 1;
	int eval2 = 1;
	int eval3 = 1;
	int eval4 = 0;
	int eval5 = 0;
	int stars = 0; //�]���̏����p
	int st = 5;	//�݌ɂ̏����p
	int st2 = 5;	//�݌ɂ̏����p

	//�{���̓��t�擾
	Date today = new Date(); //java.util.Date�^
	SimpleDateFormat DataFmt = new SimpleDateFormat("yyyy-MM-dd");
	String Today = DataFmt.format(today);

	//�ݏo�Ǘ�TOP���
	public void lendTop() throws SQLException {
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Meiryo UI", Font.PLAIN, 16)));
		UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Meiryo UI", Font.PLAIN, 16)));

		lendframe = new JFrame("�ݏo�Ǘ�");
		lendframe.setBounds(0, 0, 1350, 1000);
		lendframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lendframe.setVisible(true);

		top = new JPanel();
		top.setLayout(null);
		top.setBounds(10, 10, 1600, 880);
		top.setVisible(true);

		con = lendframe.getContentPane();
		con.add(top);

		//�e�[�u���̍쐬
		String[] header = { "PID", "�^�C�g��", "�o�Ŏ�", "����", "�W������", "�]��", "�ݏo��", "�G���A" };
		DB.connect();
		List<String[]> bookinfo = DB.makelendtopTable();
		DB.close();
		model = new DefaultTableModel(bookinfo.toArray(new String[0][0]), header);

		tbl = new JTable(model);
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//�����s�I���ł��Ȃ��悤�ɐݒ�
		tbl.setBounds(10, 300, 1600, 600);
		tbl.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		tbl.setRowHeight(28);
		//�w�b�_�[�̍���
		JTableHeader jh = tbl.getTableHeader();
		jh.setFont(new Font("Meiryo UI", Font.PLAIN, 18));

		//��̕��𒲐�����
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl.getColumnModel().getColumn(0).setPreferredWidth(100);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(300);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(150);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(150);
		tbl.getColumnModel().getColumn(4).setPreferredWidth(150);
		tbl.getColumnModel().getColumn(5).setPreferredWidth(160);
		tbl.getColumnModel().getColumn(6).setPreferredWidth(160);
		tbl.getColumnModel().getColumn(7).setPreferredWidth(85);

		//��������(�ݏo�󋵁E�G���A�j
		DefaultTableCellRenderer c_renderer = new DefaultTableCellRenderer();
		c_renderer.setHorizontalAlignment(SwingConstants.CENTER);
		tbl.getColumnModel().getColumn(6).setCellRenderer(c_renderer);
		tbl.getColumnModel().getColumn(7).setCellRenderer(c_renderer);

		JScrollPane sp;
		sp = new JScrollPane(tbl);
		sp.setBounds(10, 200, 1600, 600);
		top.add(sp);

		tbl.addMouseListener(this);

		//��������
		String[] serchList = { "�^�C�g��", "�o�Ŏ�", "����", "�W������" };
		serchcmb = new JComboBox(serchList);
		serchcmb.setBounds(500, 50, 150, 40);
		serchcmb.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		lendOK = new JRadioButton("�݌ɒ�");
		borrowing = new JRadioButton("�ݏo��");
		reserving = new JRadioButton("�\��");
		all = new JRadioButton("���ׂ�", true);

		group = new ButtonGroup();
		group.add(lendOK);
		group.add(borrowing);
		group.add(reserving);
		group.add(all);

		lendOK.setBounds(570, 130, 100, 40);
		borrowing.setBounds(680, 130, 100, 40);
		reserving.setBounds(790, 130, 100, 40);
		all.setBounds(990, 130, 100, 40);

		lendOK.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		borrowing.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		reserving.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		all.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		lendOK.addActionListener(this);
		borrowing.addActionListener(this);
		reserving.addActionListener(this);
		all.addActionListener(this);

		top.add(lendOK);
		top.add(borrowing);
		top.add(reserving);
		top.add(all);

		eover2 = new JRadioButton("����2�ȏ�");
		eover3 = new JRadioButton("������3�ȏ�");
		eover4 = new JRadioButton("��������4�ȏ�");
		all2 = new JRadioButton("���ׂ�", true);

		groupe = new ButtonGroup();
		groupe.add(eover2);
		groupe.add(eover3);
		groupe.add(eover4);
		groupe.add(all2);

		eover2.setBounds(570, 90, 110, 40);
		eover3.setBounds(690, 90, 130, 40);
		eover4.setBounds(830, 90, 160, 40);
		all2.setBounds(990, 90, 110, 40);

		eover2.setFont(new Font("Meiryo UI", Font.PLAIN, 18));
		eover3.setFont(new Font("Meiryo UI", Font.PLAIN, 18));
		eover4.setFont(new Font("Meiryo UI", Font.PLAIN, 18));
		eover4.setFont(new Font("Meiryo UI", Font.PLAIN, 18));
		all2.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		eover2.addActionListener(this);
		eover3.addActionListener(this);
		eover4.addActionListener(this);
		all2.addActionListener(this);

		top.add(eover2);
		top.add(eover3);
		top.add(eover4);
		top.add(all2);

		//�������[�h���͗p
		tx1 = new JTextField();
		tx1.setBounds(650, 50, 300, 40);
		tx1.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		labelT1 = new JLabel(" �]���F");
		labelT2 = new JLabel(" �݌ɁF");
		labelT3 = new JLabel("���Ώۂ̏��Ђ�I�����āA�Y���̃{�^���������Ă��������B");
		labelT4 = new JLabel("���Ώۂ̏��Ђ��E�N���b�N����ƁA�ݏo/�\���񂪕\������܂��B");
		labelT5 = new JLabel("���v���_�E�����獀�ڂ�I�����āA�L�[���[�h����͂��Ă��������B");

		labelT1.setBounds(500, 90, 150, 40);
		labelT1.setFont(new Font("Meiryo UI", Font.PLAIN, 17));
		labelT2.setBounds(500, 130, 150, 40);
		labelT2.setFont(new Font("Meiryo UI", Font.PLAIN, 17));
		labelT3.setBounds(10, 135, 600, 40);
		labelT3.setFont(new Font("Meiryo UI", Font.PLAIN, 17));
		labelT4.setBounds(10, 160, 600, 40);
		labelT4.setFont(new Font("Meiryo UI", Font.PLAIN, 17));
		labelT5.setBounds(500, 15, 600, 40);
		labelT5.setFont(new Font("Meiryo UI", Font.PLAIN, 17));

		top.add(serchcmb);
		top.add(tx1);
		top.add(labelT1);
		top.add(labelT2);
		top.add(labelT3);
		top.add(labelT4);
		top.add(labelT5);

		btntop = new JButton("TOP");
		btnrch = new JButton("���s");
		btnrst = new JButton("�N���A");
		btnbr = new JButton("�ݏo/�\��");
		btnrtn = new JButton("�ԋp");
		btnext = new JButton("����");

		hbtn = new JButton("�ݏo����");
		hbtn.setBounds(150, 20, 110, 40);
		hbtn.setFont(new Font("Meiryo UI", Font.PLAIN, 17));
		hbtn.addActionListener(this);

		btntop.setBounds(20, 20, 80, 40);
		btnrch.setBounds(1000, 40, 100, 40);
		btnrst.setBounds(1120, 40, 100, 40);
		btnbr.setBounds(20, 80, 120, 40);
		btnrtn.setBounds(150, 80, 120, 40);
		btnext.setBounds(280, 80, 120, 40);

		btntop.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		btnrch.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		btnrst.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		btnbr.setFont(new Font("Meiryo UI", Font.PLAIN, 18));
		btnrtn.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		btnext.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		top.add(btntop);
		top.add(btnrch);
		top.add(btnrst);
		top.add(btnbr);
		top.add(btnrtn);
		top.add(btnext);
		top.add(hbtn);

		btntop.addActionListener(this);
		btnrch.addActionListener(this);
		btnrst.addActionListener(this);
		btnbr.addActionListener(this);
		btnrtn.addActionListener(this);
		btnext.addActionListener(this);

		labelT2.addMouseListener(this);
		labelT3.addMouseListener(this);

	}

	//�ݏo/�\��o�^
	public void Borrow() {

		borrowframe = new JFrame("�ݏo�o�^");
		borrowframe.setBounds(50, 50, 700, 380);
		borrowframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borrowframe.setVisible(true);

		borrow = new JPanel();
		borrow.setLayout(null);
		borrowframe.add(borrow);
		borrow.setVisible(true);

		labelB1 = new JLabel("���^�C�g���F");
		labelB2 = new JLabel("���ݏo���F");
		labelB3 = new JLabel("���ؗp�Җ��F");
		labelB4 = new JLabel("���ԋp�\����F");
		labelB5 = new JLabel();//�^�C�g���\���p
		labelB7 = new JLabel("���ݏo������̓��t�œ��͂��Ă�������");
		labelB8 = new JLabel("");

		labelB1.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelB2.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelB3.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelB4.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelB5.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelB7.setFont(new Font("Meiryo UI", Font.PLAIN, 16));
		labelB8.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		labelB1.setBounds(15, 30, 150, 40);
		labelB2.setBounds(15, 80, 150, 40);
		labelB3.setBounds(15, 130, 200, 40);
		labelB4.setBounds(15, 180, 200, 40);
		labelB5.setBounds(155, 30, 500, 40);
		labelB7.setBounds(15, 220, 400, 40);
		labelB8.setBounds(340, 75, 400, 40);
		labelB8.setForeground(Color.blue);

		tx11 = new JTextField();
		tx11.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		tx11.setBounds(170, 130, 200, 35); 	//�ؗp�Җ�

		Calendar calendar = Calendar.getInstance();
		Date initDate = calendar.getTime();//�����̓��t�������l�ɂ���
		calendar.set(2017, 1, 1, 0, 0);
		Date startDate = calendar.getTime();
		model1 = new SpinnerDateModel(initDate, startDate, null, Calendar.DAY_OF_MONTH);

		//�ݏo��
		spinner1 = new JSpinner(model1);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner1, "yyyy-MM-dd");
		spinner1.setEditor(editor);
		spinner1.setBounds(160, 80, 160, 35);
		spinner1.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		//�ԋp�\���
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_MONTH, 7);
		Date inischeDate = calendar2.getTime();
		model2 = new SpinnerDateModel(inischeDate, startDate, null, Calendar.DAY_OF_MONTH);
		JSpinner spinner2 = new JSpinner(model2);
		JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spinner2, "yyyy-MM-dd");
		spinner2.setEditor(editor2);
		spinner2.setBounds(190, 180, 180, 35);
		spinner2.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		btntop1 = new JButton("�o�^");
		btntop1.setBounds(320, 250, 100, 40);
		btntop1.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		btnLcl = new JButton("���");
		btnLcl.setBounds(440, 250, 100, 40);
		btnLcl.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		btntop1.addActionListener(this);
		btnLcl.addActionListener(this);

		borrow.add(labelB1);
		borrow.add(labelB2);
		borrow.add(labelB3);
		borrow.add(labelB4);
		borrow.add(labelB5);
		borrow.add(labelB7);
		borrow.add(labelB8);
		borrow.add(btntop1);
		borrow.add(btnLcl);
		borrow.add(tx11);

		borrow.add(spinner1);
		borrow.add(spinner2);

	}

	//�ԋp
	public void Return() throws SQLException {

		rtnframe = new JFrame("�ԋp�o�^");
		rtnframe.setBounds(50, 50, 700, 400);
		rtnframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rtnframe.setVisible(true);

		rtn = new JPanel();
		rtn.setVisible(true);
		rtn.setLayout(null);

		rtnframe.add(rtn);

		labelR1 = new JLabel("�����O�F");
		labelR2 = new JLabel("���^�C�g���F");
		labelR3 = new JLabel("���ԋp���F");
		labelR4 = new JLabel("���]���F");
		labelR5 = new JLabel();	//���O�\���p
		labelR6 = new JLabel();	//�^�C�g���\���p
		labelR7 = new JLabel();	//NUM�󂯓n���p
		labelR8 = new JLabel("�i�����N���b�N����5�i�K�ŕ]�����Ă��������j");

		star1 = new JLabel("��");
		star2 = new JLabel("��");
		star3 = new JLabel("��");
		star4 = new JLabel("��");
		star5 = new JLabel("��");

		labelR1.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelR2.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelR3.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelR4.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelR5.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelR6.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelR8.setFont(new Font("Meiryo UI", Font.PLAIN, 16));

		star1.setFont(new Font("Meiryo UI", Font.PLAIN, 30));
		star2.setFont(new Font("Meiryo UI", Font.PLAIN, 30));
		star3.setFont(new Font("Meiryo UI", Font.PLAIN, 30));
		star4.setFont(new Font("Meiryo UI", Font.PLAIN, 30));
		star5.setFont(new Font("Meiryo UI", Font.PLAIN, 30));

		labelR1.setBounds(15, 40, 100, 40);
		labelR2.setBounds(15, 90, 130, 40);
		labelR3.setBounds(15, 140, 200, 40);
		labelR4.setBounds(15, 190, 200, 40);
		labelR5.setBounds(155, 40, 400, 40);
		labelR6.setBounds(155, 90, 600, 40);
		labelR8.setBounds(25, 225, 600, 40);

		star1.setBounds(145, 190, 30, 40);
		star2.setBounds(175, 190, 30, 40);
		star3.setBounds(205, 190, 30, 40);
		star4.setBounds(235, 190, 30, 40);
		star5.setBounds(265, 190, 30, 40);

		int row = tbl.getSelectedRow();
		String selectPID = tbl.getValueAt(row, 0).toString();//�I�����Ă���s��PID
		String selectTitle = tbl.getValueAt(row, 1).toString();//�I�����Ă���s�̃^�C�g��
		labelR6.setText(selectTitle);

		//�ݏo�҂��擾����
		DB.connect();
		String[] info = DB.getLendName(selectPID);
		DB.close();
		labelR5.setText(info[0]);
		labelR7.setText(info[1]);

		Calendar calendar = Calendar.getInstance();
		Date initDate = calendar.getTime();//�����̓��t�������l�ɂ���
		calendar.set(2017, 1, 1, 0, 0);
		Date startDate = calendar.getTime();
		model3 = new SpinnerDateModel(initDate, startDate, null, Calendar.DAY_OF_MONTH);
		JSpinner spinner3 = new JSpinner(model3);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner3, "yyyy-MM-dd");
		spinner3.setEditor(editor);
		spinner3.setBounds(155, 140, 160, 40);
		spinner3.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		btnRr = new JButton("�o�^");
		btnRr.setBounds(300, 270, 100, 40);
		btnRr.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		btnRcl = new JButton("���");
		btnRcl.setBounds(420, 270, 100, 40);
		btnRcl.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		btnRr.addActionListener(this);
		btnRcl.addActionListener(this);

		rtn.add(labelR1);
		rtn.add(labelR2);
		rtn.add(labelR3);
		rtn.add(labelR4);
		rtn.add(labelR5);
		rtn.add(labelR6);
		rtn.add(labelR8);
		rtn.add(btnRr);
		rtn.add(btnRcl);
		rtn.add(spinner3);
		rtn.add(star1);
		rtn.add(star2);
		rtn.add(star3);
		rtn.add(star4);
		rtn.add(star5);

		star1.addMouseListener(this);
		star2.addMouseListener(this);
		star3.addMouseListener(this);
		star4.addMouseListener(this);
		star5.addMouseListener(this);

	}

	//����
	public void Extend() throws SQLException {

		extenframe = new JFrame("�����o�^");
		extenframe.setBounds(50, 50, 700, 350);
		extenframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		extenframe.setVisible(true);

		exten = new JPanel();
		exten.setVisible(true);
		exten.setLayout(null);
		extenframe.add(exten);

		labelE1 = new JLabel("���^�C�g���F");
		labelE2 = new JLabel("�����O�F");
		labelE3 = new JLabel("���ԋp�\����F");
		labelE4 = new JLabel();	//�^�C�g���\���p
		labelE5 = new JLabel();	//���O�\���p
		labelE6 = new JLabel();	//NUM�󂯓n���p

		labelE1.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelE2.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelE3.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelE4.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelE5.setFont(new Font("Meiryo UI", Font.PLAIN, 25));

		labelE1.setBounds(15, 40, 150, 40);
		labelE2.setBounds(15, 90, 100, 40);
		labelE3.setBounds(15, 140, 200, 40);
		labelE4.setBounds(155, 40, 500, 40);
		labelE5.setBounds(155, 90, 200, 40);

		btnEr = new JButton("�o�^");
		btnEr.setBounds(300, 200, 100, 40);
		btnEr.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		btnEcl = new JButton("���");
		btnEcl.setBounds(420, 200, 100, 40);
		btnEcl.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		btnEr.addActionListener(this);
		btnEcl.addActionListener(this);

		exten.add(labelE1);
		exten.add(labelE2);
		exten.add(labelE3);
		exten.add(labelE4);
		exten.add(labelE5);
		exten.add(btnEr);
		exten.add(btnEcl);

		int row = tbl.getSelectedRow();
		String selectPID = tbl.getValueAt(row, 0).toString();//�I�����Ă���s��PID
		String selectTitle = tbl.getValueAt(row, 1).toString();//�I�����Ă���s�̃^�C�g��
		labelE4.setText(selectTitle);

		//�ݏo�҂��擾����
		DB.connect();
		String[] info = DB.getLendName(selectPID);
		DB.close();
		labelE5.setText(info[0]);//name
		labelE6.setText(info[1]);//num

		//String��int�ւ̕ϊ�
		int yy = (Integer.parseInt(info[2].substring(0, 4)));
		int mm = (Integer.parseInt(info[2].substring(5, 7)));
		int dd = (Integer.parseInt(info[2].substring(8, 10)));

		Calendar calendar4 = Calendar.getInstance();
		calendar4.set(yy, mm - 1, dd);	//����

		Date iniDate = calendar4.getTime();
		calendar4.set(2017, 1, 1, 0, 0);
		Date startDate = calendar4.getTime();

		model4 = new SpinnerDateModel(iniDate, startDate, null, Calendar.DAY_OF_MONTH);
		JSpinner spinner4 = new JSpinner(model4);
		JSpinner.DateEditor editor4 = new JSpinner.DateEditor(spinner4, "yyyy-MM-dd");
		spinner4.setEditor(editor4);
		spinner4.setBounds(155, 140, 160, 40);
		spinner4.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		spinner4.setBounds(190, 140, 160, 35);
		spinner4.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		exten.add(spinner4);

	}

	//�e�[�u���̃Z�b�g
	public void setTable() throws SQLException {
		DB.connect();
		List<String[]> bookinfo = DB.makelendtopTable();
		DB.close();
		model.setRowCount(0);
		for(int i = 0; i < bookinfo.size(); i++) {
			model.addRow(bookinfo.get(i));
		}

		cnt1 = 0;//���ׂď����l�ɖ߂�
		cnt2 = 0;
		cnt3 = 0;
		cnt4 = 0;
		cnt5 = 0;
		eval1 = 1;
		eval2 = 1;
		eval3 = 1;
		eval4 = 0;
		eval5 = 0;
	}

	//LEND_LIST��NUM������U��
	public String getsetNum() throws SQLException {
		DB.connect();
		int max = DB.getmaxNum();
		DB.close();
		String setNum = "";
		if(max < 9) {
			setNum = "0000" + (max + 1);
		} else if(max < 99) {
			setNum = "000" + (max + 1);
		} else if(max < 999) {
			setNum = "00" + (max + 1);
		} else if(max < 9999) {
			setNum = "0" + (max + 1);
		} else {
			setNum = Integer.toString(max + 1);
		}
		return setNum;
	}

	//����
	public void reserch() throws SQLException {
		String item = null;
		String a = "";
		String b = "";
		String c = "";
		String d = "";
		String Reserchstr = null;
		String keyword = tx1.getText();
		String selectItem = (String) serchcmb.getSelectedItem();
		if(selectItem.equals("�^�C�g��")) {
			item = "title";
		} else if(selectItem.equals("�o�Ŏ�")) {
			item = "publisher";
		} else if(selectItem.equals("����")) {
			item = "author";
		} else if(selectItem.equals("�W������")) {
			item = "genre";
		}
		if(eover2.isSelected()) {//�����p����
			stars = 2;
		} else if(eover3.isSelected()) {
			stars = 3;
		} else if(eover4.isSelected()) {
			stars = 4;
		} else if(all2.isSelected()) {
			stars = 0;
		}
		if(lendOK.isSelected()) {
			st = 0;
			st2 = 5;
		} else if(borrowing.isSelected()) {
			st = 1;
			st2 = 5;
		} else if(reserving.isSelected()) {
			st = 5;
			st2 = 2;
		} else if(all.isSelected()) {
			st = 5;
			st2 = 5;
		}

		if((keyword.length() != 0)) {
			a = "A";
		}
		if(stars == 0) {
			b = "B";
		}
		if(st == 5) {
			c = "C";
		}
		if(st2 == 5) {
			d = "D";
		}
		Reserchstr = a + b + c + d;
		String Reserchsql = "";
		switch (Reserchstr) {
		//�@�L�[���[�h�A�]���B�݌�or�ݏo�C�\��

		case "ABCD"://�@����Anull�Bnull�Cnull
			Reserchsql = "SELECT*FROM BOOK_LIST WHERE " + item + " LIKE '%" + keyword + "%';";
			break;
		case "ABC"://�@����Anull�Bnull�C�\��
			Reserchsql = "SELECT*FROM BOOK_LIST WHERE " + item + " LIKE '%" + keyword + "%'" + " and STATE2=2;";
			break;
		case "ABD"://�@����Anull�B�ݏoor�݌Ƀ`�F�b�N����
			Reserchsql = "SELECT*FROM BOOK_LIST WHERE " + item + " LIKE '%" + keyword + "%'" + " and STATE=" + st + ";";
			break;
		case "ACD"://�@����A�]������Bnull�Cnull
			Reserchsql = "SELECT*FROM BOOK_LIST WHERE " + item + " LIKE '%" + keyword + "%' and AVE_EVA >=" + stars + ";";
			break;
		case "AC"://�@����A�]������Bnull�C�\��
			Reserchsql = "SELECT*FROM BOOK_LIST WHERE " + item + " LIKE '%" + keyword + "%' and AVE_EVA >=" + stars + " and STATE2=" + st2 + ";";
			break;
		case "A"://�@����A�]������B�ݏoor�݌Ƀ`�F�b�N����
			Reserchsql = "SELECT*FROM BOOK_LIST WHERE " + item + " LIKE '%" + keyword + "%' and AVE_EVA >=" + stars + " and STATE=" + st + ";";
			break;
		case "BCD"://�@�����ɃL�[���[�h���͂Ȃ��A�]���Ȃ��B�ݏoor�݌Ƀ`�F�b�N�Ȃ��C�Ȃ�
			Reserchsql = "SELECT*FROM BOOK_LIST;";
			break;
		case "BC"://�@�����ɃL�[���[�h���͂Ȃ��A�]���Ȃ��B�ݏoor�݌Ƀ`�F�b�N�Ȃ��C�\�񂠂�
			Reserchsql = "SELECT*FROM BOOK_LIST WHERE STATE2='2';";
			break;
		case "BD"://�@�����ɃL�[���[�h���͂Ȃ��A�]���Ȃ��B�ݏoor�݌Ƀ`�F�b�N����
			Reserchsql = "SELECT*FROM BOOK_LIST WHERE STATE=" + st + ";";
			break;
		case "CD"://�@�����ɃL�[���[�h���͂Ȃ��A�]���`�F�b�N����B�݌ɂ̃`�F�b�N�Ȃ��C�\��`�F�b�N�Ȃ�
			Reserchsql = "SELECT*FROM BOOK_LIST WHERE AVE_EVA >=" + stars + ";";
			break;
		case "C"://�@�����ɃL�[���[�h���͂Ȃ��A�]���`�F�b�N����B�݌ɂ̃`�F�b�N�Ȃ��C�\��`�F�b�N����
			Reserchsql = "SELECT*FROM BOOK_LIST WHERE  AVE_EVA >=" + stars + " and STATE2=" + st2 + ";";
			break;
		case "D"://�@�����ɃL�[���[�h���͂Ȃ��A�]���`�F�b�N����B�݌ɂ̃`�F�b�N����C�\��`�F�b�N�Ȃ�
			Reserchsql = "SELECT*FROM BOOK_LIST WHERE  AVE_EVA >=" + stars + " and STATE=" + st + ";";
			break;
		case ""://�@�����ɃL�[���[�h���͂Ȃ��A�]���`�F�b�N����B�݌Ƀ`�F�b�N����C�\��`�F�b�N����
			Reserchsql = "SELECT*FROM BOOK_LIST WHERE  AVE_EVA >=" + stars + " and STATE=" + st + ";";
			break;
		}
		DB.connect();
		List<String[]> bookreserchinfo = DB.reserch(Reserchsql);
		DB.close();
		if(bookreserchinfo.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "�����Ɉ�v���鏑�Ђ͂���܂���B");
		} else {
			model.setRowCount(0);
			for(int i = 0; i < bookreserchinfo.size(); i++) {
				model.addRow(bookreserchinfo.get(i));
			}

		}
	}

	//�ݏo�E�\����̕\��
	public void showLendinfo(int row) throws SQLException {

		String PID = tbl.getValueAt(row, 0).toString();//�I�����Ă���s��PID
		String selectTitle = tbl.getValueAt(row, 1).toString();//�I�����Ă���s�̃^�C�g��

		frame = new JFrame("�ݏo/�\����");
		frame.setBounds(50, 50, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		JPanel pnl = new JPanel();
		pnl.setLayout(null);

		Container con = frame.getContentPane();
		con.add(pnl);

		label0 = new JLabel("���ݏo���");
		label0.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		label0.setBounds(15, 20, 300, 40);

		labelbar = new JLabel("========================");
		labelbar.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
		labelbar.setBounds(15, 220, 550, 40);

		pnl.add(label0);
		pnl.add(labelbar);
		DB.connect();
		String[] info = DB.getlendInfo(PID);

		if(info[0] == null) {
			labelmesg2 = new JLabel("�ݏo���͂���܂���B");
			labelmesg2.setFont(new Font("Meiryo UI", Font.PLAIN, 25));
			labelmesg2.setBounds(15, 100, 550, 40);
			pnl.add(labelmesg2);
		} else {

			label1 = new JLabel("�^�C�g���F");
			label2 = new JLabel("�ؗp�ҁF");
			label3 = new JLabel("�ݏo���F");
			label4 = new JLabel("�ԋp�\����F");
			label5 = new JLabel(selectTitle);
			label6 = new JLabel(info[0]);
			label7 = new JLabel(info[1]);
			label8 = new JLabel(info[2]);
			label9 = new JLabel();

			if(info[2].compareTo(Today) < 0) {
				label9.setText("���ԋp�������߂��Ă��܂�");
			}

			label1.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
			label2.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
			label3.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
			label4.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
			label5.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
			label6.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
			label7.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
			label8.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
			label9.setFont(new Font("Meiryo UI", Font.PLAIN, 18));

			label1.setBounds(15, 60, 100, 40);
			label2.setBounds(15, 100, 100, 40);
			label3.setBounds(15, 140, 100, 40);
			label4.setBounds(15, 180, 160, 40);
			label5.setBounds(120, 60, 500, 40);
			label6.setBounds(120, 100, 200, 40);
			label7.setBounds(120, 140, 200, 40);
			label8.setBounds(175, 180, 200, 40);
			label9.setBounds(340, 180, 200, 40);

			label9.setForeground(Color.red);

			pnl.add(label1);
			pnl.add(label2);
			pnl.add(label3);
			pnl.add(label4);
			pnl.add(label5);
			pnl.add(label6);
			pnl.add(label7);
			pnl.add(label8);
			pnl.add(label9);

		}

		label0r = new JLabel("���\����");
		label0r.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
		label0r.setBounds(15, 270, 300, 40);
		pnl.add(label0r);

		btnc1 = new JButton("�\����");
		btnc1.setBounds(400, 330, 100, 40);
		btnc1.setFont(new Font("Meiryo UI", Font.PLAIN, 15));
		btnc1.addActionListener(this);

		btnc2 = new JButton("�\����");
		btnc2.setBounds(400, 530, 100, 40);
		btnc2.setFont(new Font("Meiryo UI", Font.PLAIN, 15));
		btnc2.addActionListener(this);

		btnc3 = new JButton("�\����");
		btnc3.setBounds(400, 730, 100, 40);
		btnc3.setFont(new Font("Meiryo UI", Font.PLAIN, 15));
		btnc3.addActionListener(this);

		//�ݏo�ҁE�ݏo���E�ԋp�\������擾����

		int cnt = DB.getRsvCnt(PID);

		if(cnt == 0) {
			labelmesg = new JLabel("�\����͂���܂���B");
			labelmesg.setBounds(20, 310, 300, 40);
			labelmesg.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
			pnl.add(labelmesg);
			DB.close();
		} else {
			ArrayList<String> rsvinfo = DB.getrsvinfo(PID);

			for(int i = 0; i < cnt; i++) {
				frame.setBounds(50, 50, 600, 400 + (200 * cnt));

				//1���ڂ̕\��
				label3r = new JLabel("�ݏo�ҁF");
				label4r = new JLabel("�ݏo���F");
				label5r = new JLabel("�ԋp�\����F");
				label6r = new JLabel("No.");

				label3r.setBounds(15, 350 + (180 * i), 100, 40);
				label4r.setBounds(15, 390 + (180 * i), 100, 40);
				label5r.setBounds(15, 430 + (180 * i), 150, 40);
				label6r.setBounds(15, 310 + (180 * i), 150, 40);

				label3r.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
				label4r.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
				label5r.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
				label6r.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

				pnl.add(label3r);
				pnl.add(label4r);
				pnl.add(label5r);
				pnl.add(label6r);

				label2a = new JLabel(rsvinfo.get(0 + (4 * i)));//name
				label3a = new JLabel(rsvinfo.get(1 + (4 * i)));
				label4a = new JLabel(rsvinfo.get(2 + (4 * i)));
				label5a = new JLabel(rsvinfo.get(3 + (4 * i)));//num

				label2a.setBounds(145, 350 + (180 * i), 200, 40);
				label3a.setBounds(145, 390 + (180 * i), 200, 40);
				label4a.setBounds(175, 430 + (180 * i), 250, 40);
				label5a.setBounds(80, 310 + (180 * i), 250, 40);

				label2a.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
				label3a.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
				label4a.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
				label5a.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

				pnl.add(label2a);
				pnl.add(label3a);
				pnl.add(label4a);
				pnl.add(label5a);

				if(i == 0) {
					pnl.add(btnc1);
					cancelnum1 = rsvinfo.get(3);
				} else if(i == 1) {
					pnl.add(btnc2);
					cancelnum2 = rsvinfo.get(7);
				} else if(i == 2) {
					pnl.add(btnc3);
					cancelnum3 = rsvinfo.get(11);
				}
			}
			DB.close();
		}

		btn1 = new JButton("��");
		btn1.setBounds(470, 10, 70, 40);
		btn1.setFont(new Font("Meiryo UI", Font.PLAIN, 20));

		btn1.addActionListener(this);

		pnl.add(btn1);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btntop) {	//TOP
			lendframe.dispose();
			Top top = new Top();
			try {
				top.Top();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if(e.getSource() == hbtn) {	//����
			lendframe.dispose();
			Rireki rireki = new Rireki();
			try {
				rireki.rireki();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if(e.getSource() == btnrch) {	//���s�{�^��
			try {
				reserch();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		} else if(e.getSource() == btnrst) {	//���Z�b�g�{�^��
			try {
				setTable();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			tx1.setText("");
			stars = 0; //�]���̏����p
			st = 5;	//�݌ɂ̏����p
			st2 = 5;	//�݌ɂ̏����p
			group.clearSelection();
			groupe.clearSelection();
			all.setSelected(true);
			all2.setSelected(true);
		} else if(e.getSource() == btnbr) {	//�ݏo/�\��{�^��
			int row = tbl.getSelectedRow();
			if(row == -1) {
				Validate.noSelect();
			} else {
				String selectState = tbl.getValueAt(row, 6).toString();//�I�����Ă���s��state
				if(selectState.equals("�ݏo�� ") || selectState.equals("�ݏo�� �\��")) {//680�s�ڂɂ��킹�āA�ݏo���{���p�X�y�[�X�K�{
					String selectTitle = tbl.getValueAt(row, 1).toString();//�I�����Ă���s�̃^�C�g��
					Borrow();
					labelB8.setText("���ݏo���̂��ߗ\��̂݉\��");
					labelB5.setText(selectTitle);
				} else {
					String selectTitle = tbl.getValueAt(row, 1).toString();//�I�����Ă���s�̃^�C�g��
					Borrow();
					labelB5.setText(selectTitle);

				}
			}
		} else if(e.getSource() == btnrtn) {	//�ԋp
			int row = tbl.getSelectedRow();
			if(row == -1) {
				Validate.noSelect();
			} else {
				String selectstate = tbl.getValueAt(row, 6).toString();//�I�����Ă���s��state
				if(selectstate.equals(" ")) {
					Validate.NotOK();
				} else {
					try {
						Return();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		} else if(e.getSource() == btnext) {//����
			int row = tbl.getSelectedRow();
			if(row == -1) {
				Validate.noSelect();
			} else {
				String selectstate = tbl.getValueAt(row, 6).toString();//�I�����Ă���s��state
				if(selectstate.equals(" ") || selectstate.equals(" �\��")) {
					Validate.NotOK();
				} else {
					try {
						Extend();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		} else if(e.getSource() == btntop1) { //�ݏo�o�^�{�^��
			int row = tbl.getSelectedRow();
			String selectPID = tbl.getValueAt(row, 0).toString();//�I�����Ă���s��PID
			String selectState = tbl.getValueAt(row, 6).toString();//�I�����Ă���s��state

			//���͂��ꂽ�l�̎擾
			String name = tx11.getText();
			Date brd = model1.getDate();
			Date rtn = model2.getDate();

			String bdate = DataFmt.format(brd);
			String rtndate = DataFmt.format(rtn);

			Pattern p = Pattern.compile("[^0123456789-]");
			Matcher m = p.matcher(bdate);
			Matcher m2 = p.matcher(rtndate);

			if(name.length() == 0 || rtndate.length() == 0 || bdate.length() == 0) {
				Validate.Null();
			} else if(rtndate.compareTo(bdate) <= 0) {//�ԋp�����ݏo�����ߋ��i�����܂ށj
				Validate.DateCheck();
			} else if((bdate.compareTo(Today) < 0)) {//�ݏo�����������ߋ�
				Validate.DateCheck();
			} else if(m.find() || m2.find()) {
				Validate.hankaku();
			} else {//�G���[���Ȃ��ꍇ

				switch (selectState) {

				case "�ݏo�� ":
					//�ԋp�\������擾����
					String sche_dt = null;
					try {
						DB.connect();
						sche_dt = DB.getRtndate(selectPID);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(sche_dt.compareTo(bdate) > 0) {
						Validate.CannotRsv();
					} else {//�ʏ폈��
						String setNum = null;
						try {
							setNum = getsetNum();
							DB.borrowregister(setNum, selectPID, name, bdate, rtndate);
						} catch (SQLException e1) {

							e1.printStackTrace();
						}
						//BOOK_LIST �X�e�[�^�X�̊m�F�E�ύX
						if(bdate.compareTo(Today) == 0) {
							try {
								DB.updatelendstate(selectPID);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}

						} else if(bdate.compareTo(Today) > 0) {
							try {
								DB.updatersvstate(selectPID);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
						try {
							setTable();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(lendframe, "�o�^�������܂����B�ԋp������" + rtndate + "�ł��B");
						try {
							setTable();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						borrowframe.dispose();

					}
					DB.close();

				case "�ݏo�� �\��":
				case " �\��":

					//�ݏo���ƕԋp�\������擾����i�ݏo�����ɕ��ׂ�j
					String[] OSinfo = null;
					try {
						DB.connect();
						OSinfo = DB.getOutandSchedate(selectPID);
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					String out_dt = OSinfo[0];
					sche_dt = OSinfo[1];

					//���͒��̓��t���\����ƕԋp���̂�����
					if(out_dt.compareTo(bdate) < 0 && sche_dt.compareTo(bdate) > 0) {
						Validate.CannotRsv();

						//���͒��̕ԋp�\������\��̑ݏo���ƕԋp�\����̂�����
					} else if(out_dt.compareTo(rtndate) < 0 && sche_dt.compareTo(rtndate) > 0) {
						Validate.CannotRsv();

						//�\�����ɑݏo�A�\�����ɕԋp�̏ꍇ
					} else if(out_dt.compareTo(bdate) > 0 && sche_dt.compareTo(rtndate) < 0) {
						Validate.CannotRsv();

					} else {
						String setNum;
						try {
							setNum = getsetNum();
							DB.connect();
							DB.borrowregister(setNum, selectPID, name, bdate, rtndate);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						//BOOK_LIST �X�e�[�^�X�̊m�F�E�ύX
						if(bdate.compareTo(Today) == 0) {
							try {
								DB.connect();
								DB.updatelendstate(selectPID);
								DB.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}

						} else if(bdate.compareTo(Today) > 0) {
							try {
								DB.connect();
								DB.updatersvstate(selectPID);
								DB.close();
							} catch (SQLException e1) {

								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(lendframe, "�o�^�������܂����B�ԋp������" + rtndate + "�ł��B");
							try {
								setTable();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							borrowframe.dispose();
						}
					}

				case " ":
					String setNum;
					try {
						setNum = getsetNum();
						DB.connect();
						DB.borrowregister(setNum, selectPID, name, bdate, rtndate);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					//BOOK_LIST �X�e�[�^�X�̊m�F�E�ύX
					if(bdate.compareTo(Today) == 0) {
						try {
							DB.updatelendstate(selectPID);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}

					} else if(bdate.compareTo(Today) > 0) {
						try {
							DB.updatersvstate(selectPID);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					DB.close();
					JOptionPane.showMessageDialog(lendframe, "�o�^�������܂����B�ԋp������" + rtndate + "�ł��B");
					try {
						setTable();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					borrowframe.dispose();
				}
			}
		} else if(e.getSource() == btnLcl) {	//	�ݏo/�\��-���
			borrowframe.dispose();
		} else if(e.getSource() == btnRr) {	//�ԋp-�o�^
			int row = tbl.getSelectedRow();
			String selectPID = tbl.getValueAt(row, 0).toString();//�I�����Ă���s��PID
			Date idt = model3.getDate();
			String indate = DataFmt.format(idt);
			String name = labelR5.getText();
			String rtnnum = labelR7.getText();
			//�]��
			int total_eval = eval1 + eval2 + eval3 + eval4 + eval5;//���̍��v�l
			//�ԋp����
			try {
				DB.connect();
				totalPrice = DB.returnbook(indate, total_eval, rtnnum, name, selectPID);
				DB.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(lendframe, "�o�^�������܂����B"
					+ "���Ȃ��͗݌v" + totalPrice + "�~�̖{��ǔj���܂����I");

			rtnframe.dispose();
			try {
				setTable();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if(e.getSource() == btnRcl) {	//	�ԋp-���
			rtnframe.dispose();
		} else if(e.getSource() == btnEr) {	//	����-�o�^
			int row = tbl.getSelectedRow();
			String selectPID = tbl.getValueAt(row, 0).toString();//�I�����Ă���s��PID
			Date edt = model4.getDate();
			String extendate = DataFmt.format(edt);
			String extendnum = labelE6.getText();

			//�\�񌏐����擾
			int getrsv = 0;
			try {
				DB.connect();
				getrsv = DB.getRsvCnt(selectPID);
				DB.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			if(getrsv == 0) {//�\�񂪂Ȃ��ꍇ
				//�ʏ폈��
				try {
					DB.connect();
					DB.extenLend(extendate, extendnum);
					DB.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(lendframe, "�o�^�������܂����B");
				extenframe.dispose();
				try {
					setTable();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {//�\�񂪂ق��ɂ���ꍇ

				//�ݏo���ƕԋp�\������擾����i�ݏo�����ɕ��ׂ�j
				String[] OSinfo = null;
				try {
					DB.connect();
					OSinfo = DB.getOutandSchedate(selectPID);
					DB.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				String out_dt = OSinfo[0];
				String sche_dt = OSinfo[1];

				//���͒��̓��t���\����ƕԋp���̂�����
				if(out_dt.compareTo(extendate) < 0 && sche_dt.compareTo(extendate) > 0) {
					Validate.CannotRsv();
					//���͒��̕ԋp�\������\��̕ԋp�\����ȏ�̂Ƃ�
				} else if(sche_dt.compareTo(extendate) < 0) {
					Validate.CannotRsv();
					//�\�����ɑݏo�A�\�����ɕԋp�̏ꍇ
				} else {
					//�ʏ폈��
					try {
						DB.connect();
						DB.extenLend(extendate, extendnum);
						DB.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(lendframe, "�o�^�������܂����B");
					try {
						setTable();
						extenframe.dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}

		} else if(e.getSource() == btnEcl) {//����-����{�^��
			extenframe.dispose();
		}

		if(e.getSource() == btn1) {//����{�^��
			frame.dispose();
		} else if(e.getSource() == btnc1) {//�\����
			String PID = null;
			int cnt = 0;
			try {
				DB.connect();
				PID = DB.getPID(cancelnum1);
				cnt = DB.getRsvCnt(PID);
				DB.cancel(cancelnum1);

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			if(cnt == 1) {
				try {
					DB.changeRsvstate(PID);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			JOptionPane.showMessageDialog(frame, "�\����������܂����B");
			try {
				setTable();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			frame.dispose();
			DB.close();
		} else if(e.getSource() == btnc2) {//�\�����i2���ځj
			try {
				DB.connect();
				DB.cancel(cancelnum2);
				DB.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(frame, "�\����������܂����B");
			try {
				setTable();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			frame.dispose();
		} else if(e.getSource() == btnc3) {//�\�����i3���ځj
			try {
				DB.connect();
				DB.cancel(cancelnum3);
				DB.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			JOptionPane.showMessageDialog(frame, "�\����������܂����B");
			try {
				setTable();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			frame.dispose();
		}
	}

	@Override
	//�ԋp���]���p
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == star2) {
			cnt2++;
			if(cnt2 % 2 == 1) {
				star2.setText("��");
				eval2 = 0;
			} else if(cnt2 % 2 == 0) {
				star2.setText("��");
				eval2 = 1;
			}
		}
		if(e.getSource() == star3) {
			cnt3++;
			if(cnt3 % 2 == 1) {
				star3.setText("��");
				eval3 = 0;
			} else if(cnt3 % 2 == 0) {
				star3.setText("��");
				eval3 = 1;
			}
		}
		if(e.getSource() == star4) {	//������
			cnt4++;
			if(cnt4 % 2 == 0) {
				star4.setText("��");
				eval4 = 0;
			} else if(cnt4 % 2 == 1) {
				star4.setText("��");
				eval4 = 1;
			}
		}
		if(e.getSource() == star5) {	//������
			cnt5++;
			if(cnt5 % 2 == 0) {
				star5.setText("��");
				eval5 = 0;
			} else if(cnt5 % 2 == 1) {
				star5.setText("��");
				eval5 = 1;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {	//�ݏo�E�\����̕\��
		if(e.getButton() == MouseEvent.BUTTON3) { //�E�N���b�N

			int row = tbl.getSelectedRow();
			try {
				showLendinfo(row);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
