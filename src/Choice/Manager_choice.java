package Choice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Manage.ManageAccount;
import Manage.ManageCourse;
import Manage.ManageScore;
import Manage.ManageStu;
import Manage.ManageTea;

// TODO: Auto-generated Javadoc
/**
 * The Class Manager_choice.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Manager_choice {
	
	/** The frame manager choice. */
	JFrame frame_manager_choice;
	
	/** The lb. */
	JLabel lb;
	
	/** The btn stu. */
	JButton btn_stu;
	
	/** The btn course. */
	JButton btn_course;
	
	/** The btn teacher. */
	JButton btn_teacher;
	
	/** The btn score. */
	JButton btn_score;
	
	/** The btn account. */
	JButton btn_account;

	/** The panel north. */
	JPanel panelNorth;
	
	/** The panel center. */
	JPanel panelCenter;
	
	/** The panel south. */
	JPanel panelSouth;

	/**
	 * Instantiates a new manager choice.
	 */
	public Manager_choice() {
		frame_manager_choice = new JFrame("ѧ����Ϣϵͳ");

		lb = new JLabel("��ѡ������Ҫ�Ĳ���");
		lb.setFont(new Font("����", Font.PLAIN, 30));
		lb.setForeground(Color.BLUE);

		btn_stu = new JButton("ѧ��������Ϣ����");
		btn_course = new JButton("�γ���Ϣ����");
		btn_teacher = new JButton("��ʦ��Ϣ����");
		btn_score = new JButton("�ɼ���Ϣ����");
		btn_account = new JButton("�˺���Ϣ����");

		panelNorth = new JPanel();
		panelCenter = new JPanel();
		panelSouth = new JPanel();

		frame_manager_choice.setSize(500, 300);
		frame_manager_choice.setLocation(800, 300);
		frame_manager_choice.setLayout(new BorderLayout());

		panelNorth.setLayout(new FlowLayout());
		panelCenter.setLayout(new FlowLayout());
		// panelSouth.setLayout(new FlowLayout());

		frame_manager_choice.add(panelNorth, BorderLayout.NORTH);
		frame_manager_choice.add(panelCenter, BorderLayout.CENTER);
		frame_manager_choice.add(panelSouth, BorderLayout.SOUTH);
		panelNorth.add(lb);
		panelCenter.add(btn_stu);
		panelCenter.add(btn_course);
		panelCenter.add(btn_teacher);
		panelCenter.add(btn_score);
		panelCenter.add(btn_account);
		frame_manager_choice.setVisible(true);
		frame_manager_choice.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// ��ɾ��ġ����루�ɷ�����һҳ����ѡ�������
		btn_stu.addActionListener(new ActionListener() {//����ѧ���������

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManageStu();
				frame_manager_choice.setVisible(false);
			}
			
		});
		btn_course.addActionListener(new ActionListener() {//����γ̹�����棨��ɾ��ģ�

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManageCourse();
				frame_manager_choice.setVisible(false);
			}
			
		});
		btn_teacher.addActionListener(new ActionListener() {//������ʦ������棨��ɾ��ģ�

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManageTea();
				frame_manager_choice.setVisible(false);
			}
		
		});
		btn_score.addActionListener(new ActionListener() {//����ɼ�������棨��ɾ��ģ�

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManageScore();
				frame_manager_choice.setVisible(false);
			}
		
		});
		btn_account.addActionListener(new ActionListener() {//�����˻�������棨��ɾ��ģ�

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManageAccount();
				frame_manager_choice.setVisible(false);
			}
		
		});
		// �ɷֵ�һҳ���ڶ�ҳ��
	}
}
