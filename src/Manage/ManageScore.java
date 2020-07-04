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

import Action.Score_change;
import Action.Score_delete;
import Action.Score_increase;
import Action.Score_show;
import Choice.Manager_choice;
import Dao.Grade_Dao;
import test.ExcelTestScore;
import test.ExcelTestTeacher;

// TODO: Auto-generated Javadoc
/**
 * The Class ManageScore.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class ManageScore {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public Grade_Dao userDB = new Grade_Dao(driverName, uri);
	
	/** The frame score manage. */
	JFrame frame_score_manage;
    
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
    
    /** The btn export. */
    JButton btn_export;
    
    /** The btn back. */
    JButton btn_back;//���ذ�ť�ɷ���ѡ�����������
    
    /** The panel north. */
    JPanel panelNorth;
    
    /** The panel center. */
    JPanel panelCenter;
    
    /** The panel south. */
    JPanel panelSouth;
    
    /**
     * Instantiates a new manage score.
     */
    public ManageScore(){
    	frame_score_manage = new JFrame("�ɼ���Ϣ");
    	lb = new JLabel("��ѡ����Ҫ���еĲ���");
    	lb.setFont(new Font("����", Font.BOLD, 17));
    	
    	btn_increase = new JButton("���ӳɼ���Ϣ");
    	btn_delete = new JButton("ɾ���ɼ���Ϣ");
    	btn_search = new JButton("���ҳɼ���Ϣ");
    	btn_change = new JButton("�޸ĳɼ���Ϣ");
    	btn_export = new JButton("�����ɼ�");
    	btn_back = new JButton("����");
    	
    	panelNorth = new JPanel();
    	panelCenter = new JPanel();
    	panelSouth = new JPanel();
    	
    	frame_score_manage.setSize(400, 200);
    	frame_score_manage.setLocation(800, 300);
		
    	frame_score_manage.setLayout(new BorderLayout());
        panelNorth.setLayout(new FlowLayout());
        panelCenter.setLayout(new GridLayout(2,2));
        panelSouth.setLayout(new FlowLayout());
        
        frame_score_manage.add(panelNorth,BorderLayout.NORTH);
        frame_score_manage.add(panelCenter,BorderLayout.CENTER);
        frame_score_manage.add(panelSouth,BorderLayout.SOUTH);
        
        panelNorth.add(lb);
        panelCenter.add(btn_increase);
        panelCenter.add(btn_delete);
        panelCenter.add(btn_search);
        panelCenter.add(btn_change);
		panelCenter.add(btn_export);
        panelSouth.add(btn_back);
   
        frame_score_manage.setVisible(true);
		frame_score_manage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		btn_increase.addActionListener(new ActionListener() {//����

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			new Score_increase();
			}
		});
		btn_delete.addActionListener(new ActionListener() {//ɾ��

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Score_delete();
			}
		});
		btn_search.addActionListener(new ActionListener() {//����

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		//���ݰ༶����
				new Score_show();
			}
			
		});
		btn_change.addActionListener(new ActionListener() {//�޸�

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Score_change();
			}
			
		});
		btn_export.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			new ExcelTestScore().exportExcel();
			}	
		});
		
		//���ذ�ť������һ����
		btn_back.addActionListener(new ActionListener() {//����

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame_score_manage.setVisible(false);
				new Manager_choice();
			}
			
		});
    }
}
