package screen;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import action.DB;


public class Top implements ActionListener {

	JFrame topframe;
	JButton lbtn, bbtn;
	JLabel llabel, blabel;

	public void Top() throws SQLException {
		// �����ɃR�[�h��}��

		topframe = new JFrame("�}���Ǘ��v���O���� TOP");
		topframe.setBounds(50, 50, 1000, 400);
		topframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		topframe.setVisible(true);

		JPanel tpnl = new JPanel();
		tpnl.setLayout(null);

		Container con = topframe.getContentPane();
		con.add(tpnl);

		lbtn = new JButton("�ݏo�Ǘ�");
		bbtn = new JButton("���ЊǗ�");

		lbtn.setBounds(100, 50, 300, 100);
		bbtn.setBounds(100, 170, 300, 100);

		lbtn.setFont(new Font("Meiryo UI", Font.PLAIN, 23));
		bbtn.setFont(new Font("Meiryo UI", Font.PLAIN, 23));

		lbtn.addActionListener(this);
		bbtn.addActionListener(this);

		llabel = new JLabel("������ ���Ђ̑ݏo�E�ԋp���͂����� ������");
		blabel = new JLabel(" ������  ���Ђ̓o�^�E�ҏW�͂�����  ������");

		llabel.setBounds(450, 50, 450, 100);
		blabel.setBounds(450, 170, 450, 100);

		llabel.setFont(new Font("Meiryo UI", Font.PLAIN, 23));
		blabel.setFont(new Font("Meiryo UI", Font.PLAIN, 23));

		tpnl.add(lbtn);
		tpnl.add(bbtn);

		tpnl.add(llabel);
		tpnl.add(blabel);

		DB.connect();
		DB.changeBorrow();//�ݏo�������̏���
		DB.close();

	}




	@Override
	public void actionPerformed(ActionEvent e) {
		//�ݏo�Ǘ����
		if(e.getSource() == lbtn) {
			topframe.dispose();
			LendTop lendtop = new LendTop();
			try {
				lendtop.lendTop();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			//���ЊǗ����
		} else if(e.getSource() == bbtn) {
			topframe.dispose();
			BookMTop bookMtop = new BookMTop();
			try {
				bookMtop.display_top();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
}