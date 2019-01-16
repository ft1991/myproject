package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import dao.ILineinforDao;
import entity.Lineinfor;
import util.DBUtil;

public class LineinforDaoImpl implements ILineinforDao {
	Connection conn = DBUtil.getConnection();
	PreparedStatement ps = null;
	ResultSet rs = null;
	int line;
	@Override
	public List<Lineinfor> queryAll() {
		List<Lineinfor> list = new ArrayList<Lineinfor>();
		try {
			ps = conn.prepareStatement("select * from Lineinfor");
			rs = ps.executeQuery();
			while(rs.next()){
				Lineinfor lineinfor = new Lineinfor();
				lineinfor.setRnumber(rs.getString(1));
				lineinfor.setDeparture(rs.getString(2));
				lineinfor.setDestination(rs.getString(3));
				lineinfor.setStartTime(rs.getTime(4));
				lineinfor.setEndTime(rs.getTime(5));
				list.add(lineinfor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int addLineinfor(Lineinfor lineinfor) {
		try {
			ps = conn.prepareStatement("insert into Lineinfor values(?,?,?,?,?)");
			ps.setString(1, lineinfor.getRnumber());
			ps.setString(2, lineinfor.getDeparture());
			ps.setString(3, lineinfor.getDestination());
			ps.setTime(4, lineinfor.getStartTime());
			ps.setTime(5, lineinfor.getEndTime());
			line = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
	}

	@Override
	public int delLineinfor(String rnumber) {
		try {
			ps = conn.prepareStatement("delete from Lineinfor where Rnumber = ? ");
			ps.setString(1, rnumber);
			line = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
	}
	
	/*public static void main(String[] args) {
		LineinforDaoImpl ldi = new LineinforDaoImpl();
		
		String str1 = "09:00:00";  
		String str2 = "10:00:00"; 
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		try {
			java.util.Date d1 = format.parse(str1);
			java.util.Date d2= format.parse(str2);
			java.sql.Time date1 = new java.sql.Time(d1.getTime());
			java.sql.Time date2 = new java.sql.Time(d2.getTime());
			Lineinfor l = new Lineinfor("10014","Î÷°²","±¦¼¦", date1,date2);
			
			System.out.println(ldi.addLineinfor(l));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		
		
		
		
	}*/

	@Override
	public List<Lineinfor> pageFind(int start, int end) {
		List<Lineinfor> list = new ArrayList<Lineinfor>();
		String sql = "select * from  Lineinfor limit ?,?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while(rs.next()){
				Lineinfor line = new Lineinfor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTime(4), rs.getTime(5));
				list.add(line);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int findCount() {
		int row = 0;
		String sql = "select count(1) co from Lineinfor";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next())
			{
				row  = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	public static void main(String[] args) {
		
		LineinforDaoImpl dao = new LineinforDaoImpl();
		int row = dao.findCount();
		System.out.println(row);
		
	}
		
}


