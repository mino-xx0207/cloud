package Dao;

import java.sql.Connection;
import java.sql.DriverManager;

// TODO: Auto-generated Javadoc
/**
 * The Class DBConnection.
 *
 * @date 2020-7-4
 * @author lisai
 * @version  v1.0
 */
public class DBConnection {
	
	/**
	 * Gets the connection.
	 *
	 * @param driverName the driver name
	 * @param uri the uri
	 * @return the connection
	 */
	// ͨ��������������ͳһ��Դ��λ���������ݿ������
	public static Connection getConnection(String driverName, String uri) {
		Connection conn = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(uri);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return conn;
		}
	}
}
