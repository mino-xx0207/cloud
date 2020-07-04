package Choice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import info.Grade_info;
import info.Solo_info;

// TODO: Auto-generated Javadoc
/**
 * The Class Stu_choice.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class Stu_choice {
	
	/** The frame stu choice. */
	JFrame frame_stu_choice;
	
	/** The panel north. */
	JPanel panelNorth;
	
	/** The panel center. */
	JPanel panelCenter;
	// JPanel panelSouth;

	/** The lb title. */
	JLabel lb_title;
	
	/** The btn info. */
	JButton btn_info;
	
	/** The btn score. */
	JButton btn_score;

	/**
	 * Instantiates a new stu choice.
	 */
	public Stu_choice() {
		frame_stu_choice = new JFrame("ѧ����Ϣϵͳ");

		panelNorth = new JPanel();
		panelCenter = new JPanel();
		// panelSouth = new JPanel();

		lb_title = new JLabel("��ѡ��������еĲ���");
		lb_title.setFont(new Font("����", Font.PLAIN, 30));
		lb_title.setForeground(Color.BLUE);

		btn_info = new JButton("�鿴���˻�����Ϣ");
		btn_info.setFont(new Font("����", Font.PLAIN, 30));
		btn_info.setForeground(Color.RED);
		btn_info.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btn_score = new JButton("�鿴�ɼ�");
		btn_score.setFont(new Font("����", Font.PLAIN, 30));
		btn_score.setForeground(Color.RED);
		btn_score.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		frame_stu_choice.setSize(500, 300);
		frame_stu_choice.setLocation(800, 300);

		frame_stu_choice.setLayout(new BorderLayout());
		panelNorth.setLayout(new FlowLayout());
		panelCenter.setLayout(new FlowLayout());

		frame_stu_choice.add(panelNorth, BorderLayout.NORTH);
		frame_stu_choice.add(panelCenter, BorderLayout.CENTER);

		panelNorth.add(lb_title);

		panelCenter.add(btn_info);
		panelCenter.add(btn_score);

		frame_stu_choice.setVisible(true);
		frame_stu_choice.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// �鿴���˻�����Ϣ�¼����ɷ�����һҳ����ѡ��
		btn_info.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Solo_info();
				frame_stu_choice.setVisible(false);
			}

		});
		// ��ɼ��¼�
		btn_score.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//new Solo_info();
				new Grade_info();
				//frame_stu_choice.setVisible(false);
			}
			
		});
		//

	}
}
