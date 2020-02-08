package action;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DB {


	public static String url = "jdbc:mysql://localhost/bookmgr?autoReconnect=true&useSSL=false";
	public static String user = "root";
	public static String pass = "root";

	//�{���̓��t�擾
	public static Date today = new Date(); //java.util.Date�^
	public static SimpleDateFormat DataFmt = new SimpleDateFormat("yyyy-MM-dd");
	public static String Today = DataFmt.format(today);

	public static Connection connection;
	public static Statement statement;
	public static ResultSet result;

	//DB�ڑ�
	public static void connect() {
		try {
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/BOOKMGR", "root", "root");
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3307/BOOKMGR", "root", "root");
            System.out.println("MySQL�ɐڑ��ł��܂����B");
		} catch (Exception a) {
		}
	}

	//DB�ؒf
	public static void close() {
		try {
			connection.close();

		} catch (Exception a) {
		}
	}

	//�\��ˑݏo�������̏���
	public static void changeBorrow() throws SQLException {
		//���ꂼ���PID�ɂ��đݏo�����ő�̓������߂�
		String getLendDay = "select num,pid,max(out_date) max from BOOKMGR.lend_list where in_date is null group by pid;";
		statement = connection.createStatement();
		result = statement.executeQuery(getLendDay);

		while(result.next()) {
			String outmax = result.getString("max");
			String outpid = result.getString("pid");
			if(outmax.equals(Today)) {//�ݏo�����̏ꍇ�̏���

				String changeState = "update BOOKMGR.book_list set state2='0' ,state='1' where pid='" + outpid + "';";
				statement = connection.createStatement();
				int rs = statement.executeUpdate(changeState);
			}
		}
		result.close();
	}

	//�ݏo�o�^�e�[�u��
	public static List<String[]> makelendtopTable() throws SQLException {
		String lendtop = "SELECT * FROM BOOKMGR.BOOK_LIST";
		statement = connection.createStatement();
		result = statement.executeQuery(lendtop);
		List<String[]> bookinfolist = new ArrayList<>();

		while(result.next()) {
			String[] bookinfo = new String[8];
			//�]�����l�̌ܓ��ˁ��ł̕\���ɕύX����
			Double evaluation = result.getDouble("AVE_EVA");
			BigDecimal ava = new BigDecimal(evaluation);
			ava = ava.setScale(1, BigDecimal.ROUND_HALF_UP);

			String evalprint = "";
			if(evaluation == 0) {
			} else if(evaluation < 1.5) {
				evalprint = ava + "�@��";
			} else if(evaluation <= 2.5) {
				evalprint = ava + "�@����";
			} else if(evaluation <= 3.5) {
				evalprint = ava + "�@������";
			} else if(evaluation <= 4.5) {
				evalprint = ava + "�@��������";
			} else if(evaluation <= 5) {
				evalprint = ava + "�@����������";
			}

			bookinfo[0] = result.getString("PID");
			bookinfo[1] = result.getString("TITLE");
			bookinfo[2] = result.getString("PUBLISHER");
			bookinfo[3] = result.getString("AUTHOR");
			bookinfo[4] = result.getString("GENRE");
			bookinfo[5] = evalprint;

			String state = result.getString("STATE");
			String state2 = result.getString("STATE2");

			if(state.equals("0")) {
				state = "";	//�݌ɒ�
			} else if(state.equals("1")) {
				state = "�ݏo��";
			}
			if(state2.equals("0")) {
				state2 = "";
			} else if(state2.equals("2")) {
				state2 = "�\��";
			}

			bookinfo[6] = state + " " + state2;
			bookinfo[7] = result.getString("AREA");
			bookinfolist.add(bookinfo);
		}

		return bookinfolist;

	}

	//�ݏo�҂̎擾
	public static String[] getLendName(String selectPID) throws SQLException {

		String getName = "SELECT BOOKMGR.LEND_LIST.NAME,BOOKMGR.LEND_LIST.NUM,BOOK_LIST.STATE, BOOKMGR.LEND_LIST.SCHE_DATE"
				+ " FROM BOOKMGR.LEND_LIST INNER JOIN BOOKMGR.BOOK_LIST ON BOOKMGR.LEND_LIST.PID=BOOK_LIST.PID "
				+ "WHERE BOOKMGR.BOOK_LIST.STATE='1' and BOOKMGR.LEND_LIST.in_DATE is null  and BOOKMGR.lend_list.out_date <= date(now())  and BOOKMGR.LEND_LIST.PID=" + selectPID + ";";

		statement = connection.createStatement();
		result = statement.executeQuery(getName);
		String[] info = new String[3];

		while(result.next()) {
			info[0] = result.getString("NAME");
			info[1] = result.getString("NUM");
			info[2] = result.getString("SCHE_DATE");
		}
		return info;
	}

	//�ݏo��ʌ���
	public static List<String[]> reserch(String Reserchsql) throws SQLException {
		String reserch = Reserchsql;
		statement = connection.createStatement();
		result = statement.executeQuery(reserch);
		List<String[]> bookinfolist = new ArrayList<>();

		while(result.next()) {
			String[] bookinfo = new String[8];
			//�]�����l�̌ܓ��ˁ��ł̕\���ɕύX����
			Double evaluation = result.getDouble("AVE_EVA");
			BigDecimal ava = new BigDecimal(evaluation);
			ava = ava.setScale(1, BigDecimal.ROUND_HALF_UP);

			String evalprint = "";
			if(evaluation == 0) {
			} else if(evaluation < 1.5) {
				evalprint = ava + "�@��";
			} else if(evaluation <= 2.5) {
				evalprint = ava + "�@����";
			} else if(evaluation <= 3.5) {
				evalprint = ava + "�@������";
			} else if(evaluation <= 4.5) {
				evalprint = ava + "�@��������";
			} else if(evaluation <= 5) {
				evalprint = ava + "�@����������";
			}

			bookinfo[0] = result.getString("PID");
			bookinfo[1] = result.getString("TITLE");
			bookinfo[2] = result.getString("PUBLISHER");
			bookinfo[3] = result.getString("AUTHOR");
			bookinfo[4] = result.getString("GENRE");
			bookinfo[5] = evalprint;

			String state = result.getString("STATE");
			String state2 = result.getString("STATE2");

			if(state.equals("0")) {
				state = "";	//�݌ɒ�
			} else if(state.equals("1")) {
				state = "�ݏo��";
			}
			if(state2.equals("0")) {
				state2 = "";
			} else if(state2.equals("2")) {
				state2 = "�\��";
			}

			bookinfo[6] = state + " " + state2;
			bookinfo[7] = result.getString("AREA");
			bookinfolist.add(bookinfo);
		}

		return bookinfolist;

	}

	//�ݏo�o�^
	public static void borrowregister(String setNum, String selectPID, String name, String bdate, String rtndate) throws SQLException {
		String insert = "INSERT INTO BOOKMGR.LEND_LIST"
				+ "(NUM,PID,NAME,OUT_DATE,SCHE_DATE,IN_DATE,EVALUATION) VALUES(?,?,?,?,?,?,?);";
		statement = connection.createStatement();
		PreparedStatement pstmt = connection.prepareStatement(insert);

		pstmt.setString(1, setNum);
		pstmt.setString(2, selectPID);
		pstmt.setString(3, name);
		pstmt.setString(4, bdate);
		pstmt.setString(5, rtndate);
		pstmt.setDate(6, null);
		pstmt.setInt(7, 0);
		int result = pstmt.executeUpdate();
	}

	//BOOK_LIST�̍X�V�iSTATE)
	public static void updatelendstate(String selectPID) throws SQLException {
		String lendstate = "UPDATE BOOK_LIST SET STATE='1' WHERE PID=" + selectPID + ";";
		statement = connection.createStatement();
		int rs = statement.executeUpdate(lendstate);

	}

	//BOOK_LIST�̍X�V�iSTATE2)
	public static void updatersvstate(String selectPID) throws SQLException {
		String rsvstate = "UPDATE BOOK_LIST SET STATE2='2' WHERE PID=" + selectPID + ";";
		statement = connection.createStatement();
		int rs = statement.executeUpdate(rsvstate);

	}

	//�ݏo���̎擾
	public static String[] getlendInfo(String PID) throws SQLException {
		statement = connection.createStatement();
		String getlendInfo = "SELECT LEND_LIST.NAME,OUT_DATE,SCHE_DATE,BOOK_LIST.STATE "
				+ " FROM LEND_LIST INNER JOIN BOOK_LIST ON LEND_LIST.PID=BOOK_LIST.PID "
				+ "WHERE BOOK_LIST.STATE='1' and LEND_LIST.in_DATE is null  and lend_list.out_date <= date(now()) "
				+ " and LEND_LIST.PID=" + PID + ";";
		result = statement.executeQuery(getlendInfo);
		String[] info = new String[3];
		if(result.next()) {
			info[0] = result.getString("NAME");
			info[1] = result.getString("OUT_DATE");
			info[2] = result.getString("SCHE_DATE");
		}
		return info;
	}

	//�\����̎擾
	public static ArrayList<String> getrsvinfo(String PID) throws SQLException {
		statement = connection.createStatement();
		String getrsvInfo = "SELECT LEND_LIST.NAME,OUT_DATE,SCHE_DATE,BOOK_LIST.STATE,LEND_LIST.NUM  "
				+ " FROM LEND_LIST INNER JOIN BOOK_LIST ON LEND_LIST.PID=BOOK_LIST.PID "
				+ "WHERE BOOK_LIST.STATE2='2' and lend_list.in_date is null and lend_list.out_date > date(now()) and  "
				+ "LEND_LIST.PID=" + PID + " order by out_date asc;";
		result = statement.executeQuery(getrsvInfo);

		ArrayList<String> rsvinfolist = new ArrayList<>();
		while(result.next()) {
			rsvinfolist.add(result.getString("NAME"));
			rsvinfolist.add(result.getString("OUT_DATE"));
			rsvinfolist.add(result.getString("SCHE_DATE"));
			rsvinfolist.add(result.getString("NUM"));

		}
		return rsvinfolist;
	}

	//�ԋp�\������擾
	public static String getRtndate(String selectPID) throws SQLException {
		String getRtndate = "select sche_date from lend_list where pid='" + selectPID + "' and in_date is null;";
		statement = connection.createStatement();
		result = statement.executeQuery(getRtndate);
		String sche_dt = null;
		if(result.next()) {
			sche_dt = result.getString("sche_date");
		}
		return sche_dt;
	}

	//�ݏo���ƕԋp�\������擾����i�ݏo�����ɕ��ׂ�j
	public static String[] getOutandSchedate(String selectPID) throws SQLException {
		String getOSdate = "select out_date,sche_date from lend_list "
				+ "where pid='" + selectPID + "' and in_date is null order by out_date asc;";
		statement = connection.createStatement();
		result = statement.executeQuery(getOSdate);
		String[] OSinfo = new String[2];
		while(result.next()) {
			OSinfo[0] = result.getString("out_date");
			OSinfo[1] = result.getString("sche_date");
		}
		return OSinfo;
	}

	//LendList num��PID���擾
	public static String getPID(String cancelnum) throws SQLException {
		statement = connection.createStatement();
		String getPID = "SELECT PID FROM LEND_LIST WHERE NUM=" + cancelnum + ";";
		result = statement.executeQuery(getPID);
		String PID = null;
		if(result.next()) {
			PID = result.getString("pid");
		}
		return PID;
	}

	//�I������Ă���PID�̗\�񌏐����擾����
	public static int getRsvCnt(String PID) throws SQLException {
		statement = connection.createStatement();
		String countRsv = "SELECT COUNT(LEND_LIST.NUM) cnt FROM LEND_LIST  "
				+ "INNER JOIN BOOK_LIST ON LEND_LIST.PID=BOOK_LIST.PID "
				+ "WHERE  BOOK_LIST.STATE2='2' and lend_list.in_date is null and lend_list.out_date > date(now())"
				+ " AND BOOK_LIST.PID=" + PID + ";";
		result = statement.executeQuery(countRsv);
		int cnt = 0;
		if(result.next()) {
			cnt = result.getInt("cnt");
		}
		return cnt;
	}

	//�\��̎��
	public static void cancel(String cancelnum) throws SQLException {
		statement = connection.createStatement();
		String cancel = "DELETE FROM LEND_LIST WHERE NUM='" + cancelnum + "';";
		int rs = statement.executeUpdate(cancel);

	}

	//BOOK_LIST state2=2��0�ւ̕ύX
	public static void changeRsvstate(String PID) throws SQLException {
		statement = connection.createStatement();
		String changeRsvstate = "update book_list set state2='0' where PID=" + PID + ";";
		int rs = statement.executeUpdate(changeRsvstate);

	}

	//PID�o�^�ő�l���擾
	public static int getMaxPid() throws SQLException {
		String getMax = "SELECT PID as max FROM BOOK_LIST WHERE PID=(SELECT MAX(PID)FROM BOOK_LIST);";
		statement = connection.createStatement();
		result = statement.executeQuery(getMax);
		int max = 0;
		if(result.next()) {
			max = result.getInt("max");
		}
		return max;
	}

	//NUM�o�^�ő�l���擾
	public static int getmaxNum() throws SQLException {
		String getMax = "SELECT NUM as max FROM LEND_LIST WHERE NUM=(SELECT MAX(NUM)FROM LEND_LIST);";
		statement = connection.createStatement();
		result = statement.executeQuery(getMax);
		int max = 0;
		if(result.next()) {
			max = result.getInt("max");
		}
		return max;
	}

	//�ԋp����
	public static String returnbook(String indate, int total_eval, String rtnnum, String name, String selectPID) throws SQLException {
		String totalPrice = "";
		statement = connection.createStatement();
		String rtnbook = "UPDATE LEND_LIST SET IN_DATE='" + indate + "' , evaluation='"
				+ total_eval + "' WHERE NUM='" + rtnnum + "';";
		int rs = statement.executeUpdate(rtnbook);

		//totalPrice���擾����(cnt�Ƃ���)
		String getPrice = "SELECT SUM(BOOK_LIST.PRICE) cnt FROM LEND_LIST INNER JOIN BOOK_LIST"
				+ " ON LEND_LIST.PID=BOOK_LIST.PID WHERE "
				+ "LEND_LIST.NAME='" + name + "' and LEND_LIST.IN_DATE is not null;";

		result = statement.executeQuery(getPrice);
		if(result.next()) {
			totalPrice = result.getString("cnt");

			//�]���̕��ϒl��BOOK_LIST�ɔ��f������
			String getEval = "SELECT AVG(evaluation) avg  FROM LEND_LIST WHERE PID='" + selectPID + "' and in_date is not null;";
			result = statement.executeQuery(getEval);

			if(result.next()) {
				String avg = result.getString("avg");

				String rtnUpdatebooklist = "UPDATE BOOK_LIST SET STATE='0' ,AVE_EVA=" + avg + " WHERE PID=" + selectPID + ";";
				int rsr = statement.executeUpdate(rtnUpdatebooklist);

			}
			result.close();
		}
		result.close();
		return totalPrice;
	}

	//��������
	public static void extenLend(String extendate, String extendnum) throws SQLException {
		String exten = "UPDATE LEND_LIST SET SCHE_DATE='" + extendate + "'  WHERE NUM=" + extendnum + ";";
		statement = connection.createStatement();
		int rs = statement.executeUpdate(exten);

	}

	//�ȉ��A���ЊǗ����

	//BOOK_LIST�ꗗ
	public static List<String[]> getbooklist() throws SQLException {
		statement = connection.createStatement();
		String booklist = "SELECT * FROM BOOKMGR.BOOK_LIST";
		result = statement.executeQuery(booklist);
		List<String[]> bookinfolist = new ArrayList<>();
		while(result.next()) {
			String[] bookinfo = new String[8];
			//�]�����l�̌ܓ��ˁ��ł̕\���ɕύX����
			Double evaluation = result.getDouble("AVE_EVA");
			BigDecimal ava = new BigDecimal(evaluation);
			ava = ava.setScale(1, BigDecimal.ROUND_HALF_UP);

			String evalprint = "";
			if(evaluation == 0) {
			} else if(evaluation < 1.5) {
				evalprint = ava + "�@��";
			} else if(evaluation <= 2.5) {
				evalprint = ava + "�@����";
			} else if(evaluation <= 3.5) {
				evalprint = ava + "�@������";
			} else if(evaluation <= 4.5) {
				evalprint = ava + "�@��������";
			} else if(evaluation <= 5) {
				evalprint = ava + "�@����������";
			}

			bookinfo[0] = result.getString("PID");
			bookinfo[1] = result.getString("TITLE");
			bookinfo[2] = result.getString("PUBLISHER");
			bookinfo[3] = result.getString("AUTHOR");
			bookinfo[4] = result.getString("GENRE");
			bookinfo[5] = evalprint;
			bookinfo[6] = result.getString("AREA");
			//�ʉ݌`���ɕύX
			NumberFormat nfCur = NumberFormat.getCurrencyInstance(Locale.JAPAN);
			bookinfo[7] = nfCur.format(result.getInt("PRICE"));
			bookinfolist.add(bookinfo);
		}
		return bookinfolist;
	}

	//���Ђ̐V�K�o�^
	public static void bookregister(String setNum, String title, String publisher, String author, String genre, String price, String selectarea) throws SQLException {
		statement = connection.createStatement();
		String register = "INSERT INTO BOOK_LIST(PID,TITLE,PUBLISHER,AUTHOR,GENRE,PRICE,STATE,STATE2,AVE_EVA,AREA) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement pstmt = connection.prepareStatement(register);

		pstmt.setString(1, setNum);
		pstmt.setString(2, title);
		pstmt.setString(3, publisher);
		pstmt.setString(4, author);
		pstmt.setString(5, genre);
		pstmt.setString(6, price);
		pstmt.setInt(7, 0);
		pstmt.setInt(8, 0);
		pstmt.setDouble(9, 0);
		pstmt.setString(10, selectarea);
		int result = pstmt.executeUpdate();

	}

	//���Џ��̍X�V
	public static void bookupdate(String title, String publisher, String author, String genre, String price, String selectarea, String PID) throws SQLException {
		statement = connection.createStatement();
		String update = "UPDATE BOOK_LIST SET TITLE='" + title + "', PUBLISHER='" + publisher + "', AUTHOR='" + author + "', GENRE='"
				+ genre + "', PRICE='" + price + "', AREA='" + selectarea + "' WHERE PID='" + PID + "';";
		int rs = statement.executeUpdate(update);

	}

	//���Ђ̍폜
	public static void delete(String PID) throws SQLException {
		statement = connection.createStatement();
		String delete = "DELETE FROM BOOK_LIST WHERE PID='" + PID + "';";
		String deleteRireki = "DELETE FROM LEND_LIST WHERE PID='" + PID + "';";
		int rs = statement.executeUpdate(delete);
		int rsr = statement.executeUpdate(deleteRireki);

	}

	//���Ђ̌���
	public static List<String[]> reserchbook(String keyword, String item) throws SQLException {
		statement = connection.createStatement();
		String reserchbook = "SELECT * FROM BOOK_LIST WHERE " + item + " LIKE '%" + keyword + "%';";
		result = statement.executeQuery(reserchbook);
		List<String[]> bookreserchlist = new ArrayList<>();

		while(result.next()) {
			String[] bookreserch = new String[8];

			//�]�����l�̌ܓ��ˁ��ł̕\���ɕύX����
			Double evaluation = result.getDouble("AVE_EVA");
			BigDecimal ava = new BigDecimal(evaluation);
			ava = ava.setScale(1, BigDecimal.ROUND_HALF_UP);

			String evalprint = "";
			if(evaluation == 0) {
			} else if(evaluation < 1.5) {
				evalprint = ava + "�@��";
			} else if(evaluation <= 2.5) {
				evalprint = ava + "�@����";
			} else if(evaluation <= 3.5) {
				evalprint = ava + "�@������";
			} else if(evaluation <= 4.5) {
				evalprint = ava + "�@��������";
			} else if(evaluation <= 5) {
				evalprint = ava + "�@����������";
			}

			bookreserch[0] = result.getString("PID");
			bookreserch[1] = result.getString("TITLE");
			bookreserch[2] = result.getString("PUBLISHER");
			bookreserch[3] = result.getString("AUTHOR");
			bookreserch[4] = result.getString("GENRE");
			bookreserch[5] = evalprint;
			bookreserch[6] = result.getString("AREA");
			//�ʉ݌`���ɕύX
			NumberFormat nfCur = NumberFormat.getCurrencyInstance(Locale.JAPAN);
			bookreserch[7] = nfCur.format(result.getInt("PRICE"));
			bookreserchlist.add(bookreserch);
		}
		return bookreserchlist;
	}

	//�ȉ��A�������

	public static List<String[]> getRireki() throws SQLException {
		statement = connection.createStatement();
		//�����ꗗ�̏����擾
		String print = "SELECT BOOK_LIST.title,BOOK_LIST.publisher,BOOK_LIST.author,BOOK_LIST.genre,"
				+ "LEND_LIST.num,LEND_LIST.name,LEND_LIST.evaluation,LEND_LIST.out_date,"
				+ "LEND_LIST.in_date,LEND_LIST.sche_date FROM BOOK_LIST "
				+ "INNER JOIN LEND_LIST ON LEND_LIST.PID=BOOK_LIST.PID;";
		result = statement.executeQuery(print);

		List<String[]> list = new ArrayList<>();
		while(result.next()) {
			String[] items = new String[10];

			String eval = result.getString("EVALUATION");
			if(eval.equals("1")) {
				eval = "��";
			} else if(eval.equals("2")) {
				eval = "����";
			} else if(eval.equals("3")) {
				eval = "������";
			} else if(eval.equals("4")) {
				eval = "��������";
			} else if(eval.equals("5")) {
				eval = "����������";
			} else if(eval.equals("0")) {	//�\��̏ꍇ�ɕ]���Ȃ�
				eval = "";
			}

			items[0] = result.getString("NUM");
			items[1] = result.getString("NAME");
			items[2] = result.getString("TITLE");
			items[3] = result.getString("PUBLISHER");
			items[4] = result.getString("AUTHOR");
			items[5] = result.getString("GENRE");
			items[6] = eval;
			items[7] = result.getString("OUT_DATE");
			items[8] = result.getString("SCHE_DATE");
			items[9] = result.getString("IN_DATE");

			list.add(items);

		}
		result.close();
		return list;
	}

	//�؂肽�{�����������L���O
	public static ArrayList<String> getLendbookRank() throws SQLException {
		statement = connection.createStatement();
		String lendLank = "SELECT NAME,COUNT(*)AS count FROM LEND_LIST "
				+ "Group BY NAME ORDER BY COUNT DESC LIMIT 3;";

		result = statement.executeQuery(lendLank);

		ArrayList<String> lendrankinfo = new ArrayList<>();
		while(result.next()) {
			lendrankinfo.add(result.getString("NAME"));
			lendrankinfo.add(result.getString("count"));
		}
		return lendrankinfo;
	}

	//�؂��ꂽ�񐔂����������L���O
	public static ArrayList<String> getpopularbookRank() throws SQLException {
		statement = connection.createStatement();
		String bookLank = "SELECT PID, COUNT(*)AS COUNT FROM LEND_LIST "
				+ "GROUP BY PID ORDER BY COUNT DESC LIMIT 3;";

		result = statement.executeQuery(bookLank);

		ArrayList<String> poprankinfo = new ArrayList<>();
		ArrayList<String> title = new ArrayList<>();
		while(result.next()) {
			poprankinfo.add(result.getString("count"));
			title.add(result.getString("PID"));
		}

		for(int i = 0; i < 3; i++) {
			//PID�˃^�C�g���ɕϊ�
			statement = connection.createStatement();
			String gettitle = "SELECT TITLE FROM BOOK_LIST WHERE PID='" + title.get(i) + "';";
			result = statement.executeQuery(gettitle);
			if(result.next()) {
				poprankinfo.add(result.getString("TITLE"));
			}
		}
		return poprankinfo;
	}

	//�]�������������L���O
	public static ArrayList<String> gethighevalbook() throws SQLException {
		statement = connection.createStatement();
		String hightEval = "SELECT TITLE,AVE_EVA FROM BOOK_LIST ORDER BY AVE_EVA DESC LIMIT 3;";

		result = statement.executeQuery(hightEval);
		ArrayList<String> evalrankinfo = new ArrayList<>();

		while(result.next()) {

			evalrankinfo.add(result.getString("TITLE"));
			double eval = result.getDouble("ave_eva");

			BigDecimal ava = new BigDecimal(eval);
			ava = ava.setScale(1, BigDecimal.ROUND_HALF_UP);
			evalrankinfo.add(ava.toString());
		}
		return evalrankinfo;
	}

}