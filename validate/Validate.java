package validate;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Validate {
	//�t�H���g
	static void setFont(Component[] components, Font font) {
		if(components == null) {
			return;
		}
		for(int i = 0; i < components.length; i++) {
			components[i].setFont(font);
			setFont(
					((JComponent) components[i]).getComponents(),
					font);
		}
	}

	public static void Null() {
		JOptionPane optionPane = new JOptionPane("���͂���Ă��Ȃ����ڂ�����܂�", JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "�G���[_1000");
		setFont(dialog.getContentPane().getComponents(), new Font("Meiryo UI", Font.PLAIN, 18));
		dialog.setBounds(400, 400, 500, 150);
		dialog.setVisible(true);
	}

	public static void NotOK() {
		JOptionPane optionPane = new JOptionPane("���������Ђ�I�����Ă�������", JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "�G���[_1010");
		setFont(dialog.getContentPane().getComponents(), new Font("Meiryo UI", Font.PLAIN, 18));
		dialog.setBounds(400, 400, 500, 150);
		dialog.setVisible(true);

	}

	public static void DateCheck() {
		JOptionPane optionPane = new JOptionPane("���������t����͂��Ă�������", JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "�G���[_1020");
		setFont(dialog.getContentPane().getComponents(), new Font("Meiryo UI", Font.PLAIN, 18));
		dialog.setBounds(400, 400, 500, 150);
		dialog.setVisible(true);

	}

	public static void CannotRsv() {
		JOptionPane optionPane = new JOptionPane("���̓��t�ł͑ݏo���s���܂���", JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "�G���[_1030");
		setFont(dialog.getContentPane().getComponents(), new Font("Meiryo UI", Font.PLAIN, 18));
		dialog.setBounds(400, 400, 400, 150);
		dialog.setVisible(true);
	}

	public static void hankaku() {
		JOptionPane optionPane = new JOptionPane("���t�͔��p�����œ��͂��Ă�������", JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "�G���[_1040");
		setFont(dialog.getContentPane().getComponents(), new Font("Meiryo UI", Font.PLAIN, 18));
		dialog.setBounds(400, 400, 400, 150);
		dialog.setVisible(true);
	}

	public static void cntover() {
		JOptionPane optionPane = new JOptionPane("�����������𒴂��Ă��鍀�ڂ�����܂�", JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "�G���[_1060");
		setFont(dialog.getContentPane().getComponents(), new Font("Meiryo UI", Font.PLAIN, 18));
		dialog.setBounds(400, 400, 400, 150);
		dialog.setVisible(true);
	}

	public static void numcheck() {
		JOptionPane optionPane = new JOptionPane("�艿�͔��p������8���ȓ��œ��͂��Ă�������", JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "�G���[_1070");
		setFont(dialog.getContentPane().getComponents(), new Font("Meiryo UI", Font.PLAIN, 18));
		dialog.setBounds(400, 400, 500, 150);
		dialog.setVisible(true);
	}

	public static void noSelect() {
		JOptionPane optionPane = new JOptionPane("���Ђ��I������Ă��܂���", JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "�G���[_1080");
		setFont(dialog.getContentPane().getComponents(), new Font("Meiryo UI", Font.PLAIN, 18));
		dialog.setBounds(400, 400, 400, 150);
		dialog.setVisible(true);
	}

}