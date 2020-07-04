package test;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Dao.Grade_Dao;
import Dao.Teacher_Dao;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelTestScore.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class ExcelTestScore {
	
	/** The Constant exportFilePath. */
	private final static String exportFilePath = "F://Desktop//��//Java�������-ʵѵ//java�������ʵѵ-���״�ѧ���Ϸ���//java�������ʵѵ-���״�ѧ���Ϸ���//";
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public Grade_Dao userDB = new Grade_Dao(driverName, uri);

	/**
	 * Export excel.
	 */
	// �ɼ�ֻҪ����
	public void exportExcel() {
			WritableWorkbook workBook = null;
			// �������ļ�
			String content = JOptionPane.showInputDialog(null, "��������Ҫ������Ŀ���ļ���", "��������", JOptionPane.INFORMATION_MESSAGE);
			File file = new File(exportFilePath+content);
			if (content != null) {
				int n = JOptionPane.showConfirmDialog(null, "ȷ�ϵ�����?", "ȷ�ϵ�����", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					try {
						workBook = Workbook.createWorkbook(file);
						WritableSheet sheet = workBook.createSheet("�ɼ���Ϣ", 0);// ����һ��ѧ����Ϣ��sheetҳ
						Label stuName = new Label(0, 0, "����");
						Label courseName = new Label(1, 0, "�γ�");
						Label score = new Label(2, 0, "�ɼ�");

						sheet.addCell(stuName);
						sheet.addCell(courseName);
						sheet.addCell(score);
						// ��ѯ���ݿ������
						String sql = "select stu.stu_name,cour.course_name,s.score from student as stu,course as cour"
								+ ",score as s where stu.stu_id = s.stu_id and s.course_id = cour.course_id";
						ResultSet rs = userDB.search_info(sql);
						int rowNumber = 1;

						while(rs.next()) {
							Label studentName = new Label(0, rowNumber, rs.getString(1));
							Label courseName1 = new Label(1, rowNumber, rs.getString(2));
							Label score1 = new Label(2, rowNumber, rs.getString(3));
						
							sheet.addCell(studentName);
							sheet.addCell(courseName1);
							sheet.addCell(score1);
							
							rowNumber++;
						} 
						// д������
						workBook.write();
						JOptionPane.showMessageDialog(null, "�����ɹ���");
					}catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally {
							try {
								workBook.close();
							} catch (WriteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} else if (n == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(null, "ȡ������");
					}
				}
			}
		}

