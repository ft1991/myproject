package dao;

import java.util.List;
import entity.Lineinfor;

public interface ILineinforDao {
	public List<Lineinfor> queryAll();
	public int addLineinfor(Lineinfor lineinfor);
	public int delLineinfor(String rnumber);
	
	//��ҳ����   ��ʾ�� start�� �� end�� ������
	public List<Lineinfor> pageFind(int start,int end);
	
	//��ѯ������
	public int findCount();
}
