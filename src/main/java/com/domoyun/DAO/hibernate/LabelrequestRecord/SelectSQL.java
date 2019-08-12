package com.domoyun.DAO.hibernate.LabelrequestRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import com.domoyun.DAO.hibernate.utils.HibernateUtils;
/**
 * HQL的查询方式的测试类
 * 
 * @author pangluo
 *
 */
public class SelectSQL {
	@Test
	public static List<LabelRequestRecord> demo9() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();

		Query createQuery = session.createQuery("from LabelRequestRecord where CreateDate>'"+domo10()+"' order by id desc"); //
		List<LabelRequestRecord> list = createQuery.list(); 
		for(LabelRequestRecord lableRequestRecord2 : list) { 
		System.out.println(lableRequestRecord2.toString());  }
		tx.commit();
		return list;
	}
		@Test
		public static String domo10() {
			Date date = new Date();
			SimpleDateFormat sy=new SimpleDateFormat("yyyy");   
			SimpleDateFormat sm=new SimpleDateFormat("MM");   
			SimpleDateFormat sd=new SimpleDateFormat("dd");   
			String syear=sy.format(date);   
			String smon=sm.format(date);   
			String sday=sd.format(date);   
			System.out.println(syear+"-"+smon+"-"+sday);
			System.out.println(date);
			return syear+"-"+smon+"-"+sday+" 00:00:00";
		}
		public static void main(String[] args) {
			System.out.println(domo10());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		}
}