package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * The Class User_Dao.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class User_Dao {
	
	/** The pstmt. */
	private PreparedStatement pstmt;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn = null;

	/**
	 * Instantiates a new user dao.
	 *
	 * @param driverName the driver name
	 * @param uri the uri
	 */
	public User_Dao(String driverName, String uri) {
		conn = DBConnection.getConnection(driverName, uri);
	}

	//
	/**
	 * Save user info.
	 *
	 * @param sql the sql
	 * @param user the user
	 * @param password the password
	 * @param privilege the privilege
	 */
	// �����û���Ϣ
	public void saveUserInfo(String sql, String user, String password, String privilege) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setString(2, password);
			pstmt.setString(3, privilege);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	  
  	/**
  	 * Delete user info.
  	 *
  	 * @param sql the sql
  	 * @return the int
  	 */
  	//�����ݿ���ɾ��
		public int deleteUserInfo(String sql) {
			int i = 0;
			try {
				pstmt = conn.prepareStatement(sql);
				i = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return i;
		
		}

	/**
	 * Query user info.
	 *
	 * @param sql the sql
	 * @return the result set
	 */
	// ��ѯ�û���Ϣ�����ؽ����
	public ResultSet queryUserInfo(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return rs;
		}
	}
	
	/**
	 * Update user info.
	 *
	 * @param sql the sql
	 * @param password the password
	 * @param privilege the privilege
	 * @return the int
	 */
	//������Ϣ
		public int updateUserInfo(String sql, String password, String privilege) {
			int flag = 0;
			try {
				pstmt = conn.prepareStatement(sql);
				//pstmt.setString(1, user);�������ñ�
				pstmt.setString(1, password);
				pstmt.setString(2, privilege);
			    flag = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return flag;
		}
	
	/**
	 * Close all.
	 */
	// �ر���Դ
	public void closeAll() {
		try {
			if (!(rs == null)) {
				rs.close();
				pstmt.close();
				//stmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
