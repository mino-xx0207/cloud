package com.log;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Choice.Manager_choice;
import Choice.Stu_choice;
import Dao.User_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Log.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Log {

	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public User_Dao userDB = new User_Dao(driverName, uri);

	/** The user name. */
	// �û���������
	public static JTextField userName;
	
	/** The pass word. */
	private JPasswordField passWord;

	/** The frame log. */
	private JFrame frame_log;// ������

	/** The panel north. */
	private JPanel panelNorth;// ���
	
	/** The panel center. */
	private JPanel panelCenter;
	
	/** The panel south. */
	private JPanel panelSouth;
	
	/** The title. */
	// ��ǩ
	private JLabel title;// �������
	
	/** The lb 1 user. */
	private JLabel lb1_user;
	
	/** The lb 2 psd. */
	private JLabel lb2_psd;
	
	/** The lb 3 remember psd. */
	private JLabel lb3_rememberPsd;
	
	/** The lb 4 log. */
	private JLabel lb4_log;

	/** The bt 1 register. */
	// ��ť
	private JButton bt1_register;
	
	/** The bt 2 log. */
	private JButton bt2_log;

/** The cb 1. */
//�б��
	private JComboBox<String> cb1;

	/**
	 * Instantiates a new log.
	 */
	public Log() {
		// ׼�����
		frame_log = new JFrame("ѧ����Ϣϵͳ");

		panelNorth = new JPanel();
		title = new JLabel("ѧ����Ϣϵͳ");
		title.setFont(new Font("����", Font.PLAIN, 50));

		panelCenter = new JPanel();
		lb1_user = new JLabel("�û�����");
		lb1_user.setFont(new Font("����", Font.BOLD, 17));
		userName = new JTextField(10);
		lb2_psd = new JLabel("���룺");
		lb2_psd.setFont(new Font("����", Font.BOLD, 17));
		passWord = new JPasswordField();

		panelSouth = new JPanel();
		cb1 = new JComboBox<String>();
		cb1.addItem("ѧ��");
		cb1.addItem("����Ա");

		bt1_register = new JButton("ע��");

		bt2_log = new JButton("��¼");
		bt2_log.setFont(new Font("����", Font.PLAIN, 24));
		bt2_log.setForeground(Color.RED);
		bt2_log.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// ��������
		frame_log.setSize(500, 300);
		frame_log.setLocation(800, 300);
		frame_log.setResizable(false);
		// ����������������
		frame_log.setLayout(new BorderLayout());// �߿򲼾�
		panelNorth.setLayout(new FlowLayout());
		panelCenter.setLayout(new GridLayout(3, 2));
		panelSouth.setLayout(new FlowLayout());
		// �򶥲���������Ƕ��ʽ����
		frame_log.add(panelNorth, BorderLayout.NORTH);
		frame_log.add(panelCenter, BorderLayout.CENTER);
		frame_log.add(panelSouth, BorderLayout.SOUTH);
		// ��Ƕ��ʽ��������������
		panelNorth.add(title);

		panelCenter.add(lb1_user);
		panelCenter.add(userName);
		panelCenter.add(lb2_psd);
		panelCenter.add(passWord);

		panelSouth.add(cb1);
		panelSouth.add(bt1_register);

		panelSouth.add(bt2_log);

		// ��ʾ��������
		frame_log.setVisible(true);
		frame_log.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// ע���¼�
		bt1_register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {// ����ע�����
				// TODO Auto-generated method stub
				new Register();
				//frame_log.setVisible(false);
			}

		});
		// ��¼�¼�
		bt2_log.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {// Ҫ�����ݿ��ж��˺�����һһƥ��
				// TODO Auto-generated method stub
				// PreparedStatement psmt = null;
				// User user = new User();
				String user_name = userName.getText();
				String pswd = passWord.getText();
				String privilege = cb1.getSelectedItem().toString();
				String sql = "select * from user_account";
				// user.setUserName(user_name);
				// user.setPassword(pswd);
				try {
					int flag = 0;
					// psmt = new Dao().getConnection().prepareStatement(sql);
					ResultSet rs = userDB.queryUserInfo(sql);
					while (rs.next()) {
						if (user_name.equalsIgnoreCase(rs.getString(1)) && pswd.equalsIgnoreCase(rs.getString(2))
								&& privilege.equalsIgnoreCase(rs.getString(3))) {
							flag = 1;
							break;
						}
					}
					if (flag == 1) {
						// JOptionPane.showMessageDialog(null, "��¼�ɹ�");
						// ��¼�ɹ��ͽ���ѧ���������(���� Ȩ�����֣��ֱ���벻ͬ�˵Ľ���)
						if (cb1.getSelectedItem().toString().equalsIgnoreCase("ѧ��")) {
							new Stu_choice();
							frame_log.setVisible(false);
                            
						} else if (cb1.getSelectedItem().toString().equalsIgnoreCase("����Ա")) {
							new Manager_choice();
							frame_log.setVisible(false);
						}
					} else {
						JOptionPane.showMessageDialog(null, "�û����������Ȩ�޴���");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
	}
}
