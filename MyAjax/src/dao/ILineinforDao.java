package dao;

import java.util.List;
import entity.Lineinfor;

public interface ILineinforDao {
	public List<Lineinfor> queryAll();
	public int addLineinfor(Lineinfor lineinfor);
	public int delLineinfor(String rnumber);
	
	//分页方法   显示从 start行 → end行 的数据
	public List<Lineinfor> pageFind(int start,int end);
	
	//查询总行数
	public int findCount();
}
