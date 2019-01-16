package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {
/**
 * 数据源工具类
 */
	private static DataSource dataSource = new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	public static DataSource getDataSource()
	{
		return dataSource;
	}
	/**
	 * 如果需要手动控制事物，可以调用方法获得一个连接
	 */
	public static Connection getConnection()
	{
		Connection conn = tl.get();
		if(conn == null)
		{
			try {
				conn = dataSource.getConnection();
				tl.set(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
	/**
	 * 开始事物
	 */
	public static void startTransaction()
	{
		Connection conn = getConnection();
		if(conn != null)
		{
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 从ThreadLocal中关闭CONN，并结事物
	 */
	 public static  void releaseAndCloseConnection()
	 {
		 Connection conn = getConnection();
		 if(conn!=null)
		 {
			 try {
				//conn.commit();
				tl.remove();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
	public static void rollback()
	{
		Connection conn = getConnection();
		if(conn != null)
		{
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public static void main(String[] args) {
		try {

			ResultSet rs = DBUtil.getConnection().prepareStatement("select * from lineinfor ").executeQuery();

			while(rs.next())
			{
				System.out.println(rs.getString(1)+"----"+rs.getString(2)+"-----"+rs.getString(3)+"-----"+rs.getTime(4)+"-----"+rs.getTime(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
