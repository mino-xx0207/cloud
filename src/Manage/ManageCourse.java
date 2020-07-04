package Manage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
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

import com.log.Log;

import Action.Course_increase;
import Choice.Manager_choice;
import Dao.Course_Dao;
import Dao.Teacher_Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class ManageCourse.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class ManageCourse {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The course DB. */
	public Course_Dao courseDB = new Course_Dao(driverName, uri);

	/** The frame course manage. */
	JFrame frame_course_manage;
	
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
	 * Instantiates a new manage course.
	 */
	public ManageCourse() {
		frame_course_manage = new JFrame("�γ���Ϣ");
		lb = new JLabel("��ѡ����Ҫ���еĲ���");
		lb.setFont(new Font("����", Font.BOLD, 17));

		btn_increase = new JButton("���ӿγ���Ϣ");
		btn_delete = new JButton("ɾ���γ���Ϣ");
		btn_search = new JButton("���ҿγ���Ϣ");
		btn_change = new JButton("�޸Ŀγ���Ϣ");
		btn_back = new JButton("����");

		panelNorth = new JPanel();
		panelCenter = new JPanel();
		panelSouth = new JPanel();

		frame_course_manage.setSize(300, 200);
		frame_course_manage.setLocation(800, 300);

		frame_course_manage.setLayout(new BorderLayout());
		panelNorth.setLayout(new FlowLayout());
		panelCenter.setLayout(new GridLayout(2, 2));
		panelSouth.setLayout(new FlowLayout());

		frame_course_manage.add(panelNorth, BorderLayout.NORTH);
		frame_course_manage.add(panelCenter, BorderLayout.CENTER);
		frame_course_manage.add(panelSouth, BorderLayout.SOUTH);

		panelNorth.add(lb);
		panelCenter.add(btn_increase);
		panelCenter.add(btn_delete);
		panelCenter.add(btn_search);
		panelCenter.add(btn_change);
		panelSouth.add(btn_back);

		frame_course_manage.setVisible(true);
		frame_course_manage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		btn_increase.addActionListener(new ActionListener() {// ����

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Course_increase();
			}

		});
		btn_delete.addActionListener(new ActionListener() {// ɾ��

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String content = JOptionPane.showInputDialog(null, "��������Ҫɾ���γ̵Ŀγ̺�", "ɾ���γ�",
						JOptionPane.INFORMATION_MESSAGE);
				if (content != null) {
					// ɾ��֮ǰ�Ȳ�ѯ�Ƿ����
					String sql1 = "select course_id from course where course_id = '" + content + "';";
					ResultSet rs = courseDB.search_info(sql1);
					try {
						if (rs.next()) {
							int n = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����?", "ȷ��ɾ����", JOptionPane.YES_NO_OPTION);
							if (n == JOptionPane.YES_OPTION) {
								String sql = "delete from course where course_id = '" + content + "';";
								int flag = courseDB.deleteCourseInfo(sql);
								if (flag == 1) {
									JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
									content = null;
								}
							} else if (n == JOptionPane.NO_OPTION) {
								JOptionPane.showMessageDialog(null, "ȡ��ɾ��");
							}
						} else {
							JOptionPane.showMessageDialog(null, "�˿γ̲�����");
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
		}

		);
		btn_search.addActionListener(new ActionListener() {// ����

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String content = JOptionPane.showInputDialog(null, "��������Ҫ���ҿγ̵Ŀγ̺�", "���ҿγ�",
						JOptionPane.INFORMATION_MESSAGE);
				if (content != null) {
					JFrame frame_course_search = new JFrame("��ѯ�γ���Ϣ");

					JLabel lb_course_id = new JLabel("�γ̺�");
					JLabel lb_course_name = new JLabel("�γ���");
					JLabel lb_hour = new JLabel("��ʱ");
					JLabel lb_credit = new JLabel("ѧ��");
					JLabel lb_course_nature = new JLabel("�γ�����");
					JLabel lb_course_category = new JLabel("�γ����");

					JTextField txt_course_id = new JTextField();
					JTextField txt_course_name = new JTextField();
					JTextField txt_hour = new JTextField();
					JTextField txt_credit = new JTextField();
					JTextField txt_course_nature = new JTextField();
					JTextField txt_course_category = new JTextField();

					JPanel panelCenter1 = new JPanel();

					frame_course_search.setSize(500, 300);
					frame_course_search.setLocation(800, 300);

					frame_course_search.setLayout(new BorderLayout());

					panelCenter1.setLayout(new GridLayout(6, 2));

					frame_course_search.add(panelCenter1, BorderLayout.CENTER);

					panelCenter1.add(lb_course_id);
					panelCenter1.add(txt_course_id);
					panelCenter1.add(lb_course_name);
					panelCenter1.add(txt_course_name);
					panelCenter1.add(lb_hour);
					panelCenter1.add(txt_hour);
					panelCenter1.add(lb_credit);
					panelCenter1.add(txt_credit);
					panelCenter1.add(lb_course_nature);
					panelCenter1.add(txt_course_nature);
					panelCenter1.add(lb_course_category);
					panelCenter1.add(txt_course_category);

					String sql = "select * from course where course_id = '" + content + "';";
					ResultSet rs = courseDB.search_info(sql);
					try {
						if (rs.next()) {
							txt_course_id.setText(rs.getString(1));
							txt_course_name.setText(rs.getString(2));
							txt_hour.setText(String.valueOf(rs.getInt(3)));
							txt_credit.setText(String.valueOf(rs.getInt(4)));
							txt_course_nature.setText(rs.getString(5));
							txt_course_category.setText(rs.getString(6));
							frame_course_search.setVisible(true);
							frame_course_search.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						} else {
							JOptionPane.showMessageDialog(null, "�ÿγ̲�����");
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
				String content = JOptionPane.showInputDialog(null, "��������Ҫ�޸ĵĿγ̺�", "���¿γ�",
						JOptionPane.INFORMATION_MESSAGE);
				// �Ȳ����Ƿ����ҵ�
				if (content != null) {
					JFrame frame_course_change = new JFrame("���¿γ���Ϣ");

					JLabel lb_course_id = new JLabel("�γ̺�");
					JLabel lb_course_name = new JLabel("�γ���");
					JLabel lb_hour = new JLabel("��ʱ");
					JLabel lb_credit = new JLabel("ѧ��");
					JLabel lb_course_nature = new JLabel("�γ�����");
					JLabel lb_course_category = new JLabel("�γ����");
					JButton btn_OK1 = new JButton("ȷ��");

					JTextField txt_course_id = new JTextField();
					JTextField txt_course_name = new JTextField();
					JTextField txt_hour = new JTextField();
					JTextField txt_credit = new JTextField();
					JTextField txt_course_nature = new JTextField();
					JTextField txt_course_category = new JTextField();

					JPanel panelCenter1 = new JPanel();
					JPanel panelSouth1 = new JPanel();

					frame_course_change.setSize(500, 300);
					frame_course_change.setLocation(800, 300);
					frame_course_change.setLayout(new BorderLayout());

					panelCenter1.setLayout(new GridLayout(6, 2));
					panelSouth1.setLayout(new FlowLayout());

					frame_course_change.add(panelCenter1, BorderLayout.CENTER);
					frame_course_change.add(panelSouth1, BorderLayout.SOUTH);
					
					panelCenter1.add(lb_course_id);
					panelCenter1.add(txt_course_id);
					panelCenter1.add(lb_course_name);
					panelCenter1.add(txt_course_name);
					panelCenter1.add(lb_hour);
					panelCenter1.add(txt_hour);
					panelCenter1.add(lb_credit);
					panelCenter1.add(txt_credit);
					panelCenter1.add(lb_course_nature);
					panelCenter1.add(txt_course_nature);
					panelCenter1.add(lb_course_category);
					panelCenter1.add(txt_course_category);

					panelSouth1.add(btn_OK1);
					
					String sql = "select * from course where course_id = '" + content + "';";
					ResultSet rs = courseDB.search_info(sql);
					try {
						if (rs.next()) {
							txt_course_id.setText(rs.getString(1));
							txt_course_name.setText(rs.getString(2));
							txt_hour.setText(String.valueOf(rs.getInt(3)));
							txt_credit.setText(String.valueOf(rs.getInt(4)));
							txt_course_nature.setText(rs.getString(5));
							txt_course_category.setText(rs.getString(6));
							frame_course_change.setVisible(true);
							frame_course_change.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							btn_OK1.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									String sql1 = "update course set course_name=?,hour=? ,credit=?,course_nature=?,course_category=?"
											+ "where course_id = '" + content+ "';";
									int flag = courseDB.updateCourseInfo(sql1, txt_course_name.getText(), Integer.parseInt(txt_hour.getText()),
											Integer.parseInt(txt_credit.getText()), txt_course_nature.getText(), txt_course_category.getText());
									if (flag == 1) {
										JOptionPane.showMessageDialog(null, "���³ɹ�");
										frame_course_change.setVisible(false);
									} else {
										JOptionPane.showMessageDialog(null, "����ʧ��");
									}
								}

							});

						} else {
							JOptionPane.showMessageDialog(null, "�ÿγ̲�����");
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
				frame_course_manage.setVisible(false);
				new Manager_choice();
			}

		});

	}
}
