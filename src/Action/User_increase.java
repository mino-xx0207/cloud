package Action;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Dao.Course_Dao;
import Dao.User_Dao;

// TODO: Auto-generated Javadoc
//����Ա�����û���Ϣ
/**
 * The Class User_increase.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
//����Ա���˵���Ѿ�������Աע�ᣬ�û���ֱ�ӵ�¼
public class User_increase {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public User_Dao userDB = new User_Dao(driverName, uri);
	
	/** The frame user increase. */
	JFrame frame_user_increase;
	
	/** The lb title. */
	JLabel lb_title;
	
	/** The lb user id. */
	JLabel lb_user_id;
	
	/** The lb pswd. */
	JLabel lb_pswd;
	
	/** The cb 1. */
	JComboBox<String> cb1;
	
	/** The txt user id. */
	JTextField txt_user_id;
	
	/** The txt pswd. */
	JTextField txt_pswd;
	
	/** The btn save. */
	JButton btn_save;
	
	/** The btn cancle. */
	JButton btn_cancle;
	
	/** The panel north. */
	JPanel panelNorth;
	
	/** The panel center. */
	JPanel panelCenter;
	
	/** The panel south. */
	JPanel panelSouth;
	
	/**
	 * Instantiates a new user increase.
	 */
	public User_increase() {
		 frame_user_increase = new JFrame("����û���Ϣ");
		
		 lb_title = new JLabel("������û��˺���Ϣ");
		 lb_title.setFont(new Font("����", Font.PLAIN, 40));
		 
		lb_user_id = new JLabel("�û�id");
		lb_pswd = new JLabel("�û�����");
		lb_user_id.setFont(new Font("����", Font.BOLD, 17));
		lb_pswd.setFont(new Font("����", Font.BOLD, 17));
		txt_user_id = new JTextField();
		txt_pswd = new JTextField();
		
		cb1 = new JComboBox<String>();
		cb1.addItem("ѧ��");
		cb1.addItem("����Ա");
		
		btn_save = new JButton("����");
		btn_cancle = new JButton("ȡ��");

		panelNorth = new JPanel();
		panelCenter = new JPanel();
		panelSouth = new JPanel();
		
		frame_user_increase.setSize(500, 200);
		frame_user_increase.setLocation(800, 300);

		frame_user_increase.setLayout(new BorderLayout());
		
		panelNorth.setLayout(new FlowLayout());
		panelCenter.setLayout(new GridLayout(2, 2));
		panelSouth.setLayout(new FlowLayout());
		
		frame_user_increase.add(panelNorth,BorderLayout.NORTH);
		frame_user_increase.add(panelCenter, BorderLayout.CENTER);
		frame_user_increase.add(panelSouth, BorderLayout.SOUTH);
		
		panelNorth.add(lb_title);
		panelCenter.add(lb_user_id);
		panelCenter.add(txt_user_id);
		panelCenter.add(lb_pswd);
		panelCenter.add(txt_pswd);
		
		panelSouth.add(cb1);
		panelSouth.add(btn_save);
		panelSouth.add(btn_cancle);

		frame_user_increase.setVisible(true);
		frame_user_increase.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// �����û���Ϣ
				btn_save.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String id = txt_user_id.getText();// �õ�����γ̺�
						//String privilege = cb1.getSelectedItem().toString();// ��ȡע���û�ѡ��Ĺ���Ȩ�ޣ��������ݿ���
						
						String sql = "select user_id from user_account where user_id='" + id + "';";
						ResultSet rs = userDB.queryUserInfo(sql);
						try {
							if (rs.next()) {
								JOptionPane.showMessageDialog(null, "�û�id�ظ������������");
								// �ÿ������ı���
								txt_user_id.setText("");
								txt_pswd.setText("");

							} else {
								String sql1 = "insert into user_account(user_id,pswd,power) "
										+ "values(?,?,?)";
								String user_name1 = txt_user_id.getText();
								String pswd1 = txt_pswd.getText();
								String privilege = cb1.getSelectedItem().toString();// ��ȡע���û�ѡ��Ĺ���Ȩ�ޣ��������ݿ���
								
								userDB.saveUserInfo(sql1, user_name1, pswd1, privilege);
								JOptionPane.showMessageDialog(null, "����ɹ���");
								// ����ɹ�֮���ÿ������ı����Ա㱣����һ���û���Ϣ
								txt_user_id.setText("");
								txt_pswd.setText("");
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

				});
				// ȡ����ť�¼�
				btn_cancle.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						frame_user_increase.setVisible(false);
					}

				});
	}
	
}
