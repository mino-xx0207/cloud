package test;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

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
 * The Class ExcelTestTeacher.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class ExcelTestTeacher {
	
	/** The Constant exportFilePath. */
	private final static String exportFilePath="F://Desktop//��//Java�������-ʵѵ//java�������ʵѵ-���״�ѧ���Ϸ���//java�������ʵѵ-���״�ѧ���Ϸ���//";
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";

	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=123456&useSSL=true";
	
	/** The user DB. */
	public Teacher_Dao userDB = new Teacher_Dao(driverName, uri);

	/**
	 * Import excel.
	 */
	public void importExcel() {
		Workbook workbook = null;
		Sheet sheet = null;
		String content = JOptionPane.showInputDialog(null, "��������Ҫ������ļ���", "��������", JOptionPane.INFORMATION_MESSAGE);
		// Excel�ļ�
		if (content != null) {
			File file = new File(exportFilePath+content);
			// ����workbook
			try {
				workbook = Workbook.getWorkbook(file);
				// ��ȡsheetҳ
				sheet = workbook.getSheet(0);
			
				int rows = sheet.getRows();
				// ��ʼ��student����
				// ��forѭ��/while ѭ����Ҫ��ʼ������
				for (int i = 1; i <= rows; i++) {
					// ֱ�ӻ�ȡ��Ԫ��
					Cell teacher_id_cell = sheet.getCell(0, i);
					Cell tea_name_cell = sheet.getCell(1, i);
					Cell college_cell = sheet.getCell(2, i);
					Cell job_title_cell = sheet.getCell(3, i);
					Cell job_time_cell = sheet.getCell(4, i);
					// ��ȡ��Ԫ�����������
					String teacherIdstr = teacher_id_cell.getContents();
					String teacherNamestr = tea_name_cell.getContents();
					String collegeStr = college_cell.getContents();
					String job_titleStr = job_title_cell.getContents();
					String job_timeStr = job_time_cell.getContents();

					String sql1 = "insert into teacher(tea_id,tea_name,college,job_title,job_time) "
							+ "values(?,?,?,?,?)";
					userDB.saveStuInfo(sql1, teacherIdstr, teacherNamestr, collegeStr, job_titleStr, job_timeStr);
				}
				JOptionPane.showMessageDialog(null, "����ɹ���");
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Export excel.
	 */
	public void exportExcel() {
		WritableWorkbook workBook = null;
		// �������ļ�
		//������ļ����ơ��������������������Եģ����Ǹ��ļ���
		String content = JOptionPane.showInputDialog(null, "��������Ҫ������Ŀ���ļ���", "��������", JOptionPane.INFORMATION_MESSAGE);
		File file = new File(exportFilePath+content);
		if (content != null) {
			int n = JOptionPane.showConfirmDialog(null, "ȷ�ϵ�����?", "ȷ�ϵ�����", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				try {
					workBook = Workbook.createWorkbook(file);
					WritableSheet sheet = workBook.createSheet("��ʦ��Ϣ", 0);// ����һ��ѧ����Ϣ��sheetҳ
					Label teacherNoHeader = new Label(0, 0, "����");
					Label teacherNameHeader = new Label(1, 0, "����");
					Label teacherCollege = new Label(2, 0, "ѧԺ");
					Label teacher_job_title = new Label(3, 0, "ְ��");
					Label teacher_job_time = new Label(4, 0, "��ְʱ��");

					sheet.addCell(teacherNoHeader);
					sheet.addCell(teacherNameHeader);
					sheet.addCell(teacherCollege);
					sheet.addCell(teacher_job_title);
					sheet.addCell(teacher_job_time);
					// ��ѯ���ݿ������
					String sql = "select * from teacher";
					ResultSet rs = userDB.search_info(sql);
					int rowNumber = 1;

					while(rs.next()) {
						Label teacherNO = new Label(0, rowNumber, rs.getString(1));
						Label teacherName = new Label(1, rowNumber, rs.getString(2));
						Label college = new Label(2, rowNumber, rs.getString(3));
						Label job_title = new Label(3, rowNumber, rs.getString(4));
						Label job_time = new Label(4, rowNumber, rs.getString(5));

						sheet.addCell(teacherNO);
						sheet.addCell(teacherName);
						sheet.addCell(college);
						sheet.addCell(job_title);
						sheet.addCell(job_time);
						rowNumber++;
					}
					// д������
					workBook.write();
					JOptionPane.showMessageDialog(null, "�����ɹ���");
				} catch (Exception e) {
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
