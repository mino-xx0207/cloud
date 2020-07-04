package Manage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Action.User_increase;
import Choice.Manager_choice;
import Dao.Teacher_Dao;
import Dao.User_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class ManageAccount.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class ManageAccount {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public User_Dao userDB = new User_Dao(driverName, uri);

	/** The frame account manage. */
	JFrame frame_account_manage;
	
	/** The lb. */
	JLabel lb;

	/** The btn increase. */
	JButton btn_increase;
	
	/** The btn delete. */
	JButton btn_delete;
	
	/** The btn search. */
	JButton btn_search;
	
	/** The btn change. */
	JButton btn_change;
	
	/** The btn back. */
	JButton btn_back;// ���ذ�ť�ɷ���ѡ�����������

	/** The panel north. */
	JPanel panelNorth;
	
	/** The panel center. */
	JPanel panelCenter;
	
	/** The panel south. */
	JPanel panelSouth;

	/**
	 * Instantiates a new manage account.
	 */
	public ManageAccount() {
		frame_account_manage = new JFrame("�û���Ϣ");
		lb = new JLabel("��ѡ����Ҫ���еĲ���");
		lb.setFont(new Font("����", Font.BOLD, 17));

		btn_increase = new JButton("�����û���Ϣ");
		btn_delete = new JButton("ɾ���û���Ϣ");
		btn_search = new JButton("�����û���Ϣ");
		btn_change = new JButton("�޸��û���Ϣ");
		btn_back = new JButton("����");

		panelNorth = new JPanel();
		panelCenter = new JPanel();
		panelSouth = new JPanel();

		frame_account_manage.setSize(300, 200);
		frame_account_manage.setLocation(800, 300);

		frame_account_manage.setLayout(new BorderLayout());
		panelNorth.setLayout(new FlowLayout());
		panelCenter.setLayout(new GridLayout(2, 2));
		panelSouth.setLayout(new FlowLayout());

		frame_account_manage.add(panelNorth, BorderLayout.NORTH);
		frame_account_manage.add(panelCenter, BorderLayout.CENTER);
		frame_account_manage.add(panelSouth, BorderLayout.SOUTH);

		panelNorth.add(lb);
		panelCenter.add(btn_increase);
		panelCenter.add(btn_delete);
		panelCenter.add(btn_search);
		panelCenter.add(btn_change);
		panelSouth.add(btn_back);

		frame_account_manage.setVisible(true);
		frame_account_manage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		btn_increase.addActionListener(new ActionListener() {// ����

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new User_increase();
			}

		});
		btn_delete.addActionListener(new ActionListener() {// ɾ��

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String content = JOptionPane.showInputDialog(null, "��������Ҫɾ���û������id", "ɾ���û�",
						JOptionPane.INFORMATION_MESSAGE);
				if (content != null) {
					// ɾ��֮ǰ�Ȳ�ѯ�Ƿ����
					String sql1 = "select user_id from user_account where user_id = '" + content + "';";
					ResultSet rs = userDB.queryUserInfo(sql1);
					try {
						if (rs.next()) {// ���ڵĻ���ɾ��
							int n = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����?", "ȷ��ɾ����", JOptionPane.YES_NO_OPTION);
							if (n == JOptionPane.YES_OPTION) {
								String sql = "delete from user_account where user_id = '" + content + "';";
								int flag = userDB.deleteUserInfo(sql);
								if (flag == 1) {
									JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
									content = null;
								}
							} else if (n == JOptionPane.NO_OPTION) {
								JOptionPane.showMessageDialog(null, "ȡ��ɾ��");
							}
						} else {
							JOptionPane.showMessageDialog(null, "�˺Ų�����");
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
		btn_search.addActionListener(new ActionListener() {// ����

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String content = JOptionPane.showInputDialog(null, "��������Ҫ�����û���id", "�����û�",
						JOptionPane.INFORMATION_MESSAGE);
				if (content != null) {
					JFrame frame_account_search = new JFrame("��ѯ�û���Ϣ");

					JLabel lb_user_id = new JLabel("�û�id");
					JLabel lb_pswd = new JLabel("�û�����");
					JLabel lb_power = new JLabel("�û�Ȩ��");

					JTextField txt_user_id = new JTextField();
					JTextField txt_pswd = new JTextField();
					JTextField txt_power = new JTextField();

					JPanel panelCenter1 = new JPanel();

					frame_account_search.setSize(500, 300);
					frame_account_search.setLocation(800, 300);

					frame_account_search.setLayout(new BorderLayout());

					panelCenter1.setLayout(new GridLayout(3, 2));

					frame_account_search.add(panelCenter1, BorderLayout.CENTER);

					panelCenter1.add(lb_user_id);
					panelCenter1.add(txt_user_id);
					panelCenter1.add(lb_pswd);
					panelCenter1.add(txt_pswd);
					panelCenter1.add(lb_power);
					panelCenter1.add(txt_power);

					String sql = "select * from user_account where user_id = '" + content + "';";
					ResultSet rs = userDB.queryUserInfo(sql);
					try {
						if (rs.next()) {
							txt_user_id.setText(rs.getString(1));
							txt_pswd.setText(rs.getString(2));
							txt_power.setText(rs.getString(3));
							frame_account_search.setVisible(true);
							frame_account_search.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						} else {
							JOptionPane.showMessageDialog(null, "���û�������");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_change.addActionListener(new ActionListener() {// �޸�

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String content = JOptionPane.showInputDialog(null, "��������Ҫ�޸��û���id", "�����û�",
						JOptionPane.INFORMATION_MESSAGE);
				// �Ȳ����Ƿ����ҵ�
				if (content != null) {
					JFrame frame_account_change = new JFrame("�����û���Ϣ");

					JLabel lb_user_id = new JLabel("�û�id");
					JLabel lb_pswd = new JLabel("�û�����");
					JLabel lb_power = new JLabel("�û�Ȩ��");

					JTextField txt_user_id = new JTextField();
					JTextField txt_pswd = new JTextField();
					JTextField txt_power = new JTextField();

					JButton btn_OK1 = new JButton("ȷ��");

					JPanel panelCenter1 = new JPanel();
					JPanel panelSouth1 = new JPanel();

					frame_account_change.setSize(500, 300);
					frame_account_change.setLocation(800, 300);

					frame_account_change.setLayout(new BorderLayout());

					panelCenter1.setLayout(new GridLayout(3, 2));
					panelSouth1.setLayout(new FlowLayout());
					frame_account_change.add(panelCenter1, BorderLayout.CENTER);
					frame_account_change.add(panelSouth1, BorderLayout.SOUTH);

					panelCenter1.add(lb_user_id);
					panelCenter1.add(txt_user_id);
					panelCenter1.add(lb_pswd);
					panelCenter1.add(txt_pswd);
					panelCenter1.add(lb_power);
					panelCenter1.add(txt_power);

					panelSouth1.add(btn_OK1);
					String sql = "select * from user_account where user_id = '" + content + "';";
					ResultSet rs = userDB.queryUserInfo(sql);
					try {
						if (rs.next()) {
							txt_user_id.setText(rs.getString(1));
							txt_pswd.setText(rs.getString(2));
							txt_power.setText(rs.getString(3));
							frame_account_change.setVisible(true);
							frame_account_change.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							btn_OK1.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									String sql1 = "update user_account set pswd=?,power=? where user_id = '"+content+"';";
									int flag=userDB.updateUserInfo(sql1, txt_pswd.getText(),
											txt_power.getText());
									if(flag==1) {
									JOptionPane.showMessageDialog(null, "���³ɹ�");
									frame_account_change.setVisible(false);
									}else {
										JOptionPane.showMessageDialog(null, "����ʧ��");
									}
								}

							});

						} else {
							JOptionPane.showMessageDialog(null, "���û�������");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
		// ���ذ�ť������һ����
				btn_back.addActionListener(new ActionListener() {// ����

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						frame_account_manage.setVisible(false);
						new Manager_choice();
					}

				});
	}
}
