package com.domoyun.hibernate.LabelrequestRecord;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.Test;

import com.domoyun.hibernate.utils.HibernateUtils;

/**
 * HQL的查询方式的测试类
 * 
 * @author jt
 *
 */
public class SelectSQL {
	@Test
	public static List<LabelRequestRecord> demo9() {
		System.out.println("2144");
//		Configuration configuration2 = new Configuration();
//		System.out.println("21443");
//		Configuration configure = configuration2.configure();
		Configuration configuration = new Configuration().configure();
		System.out.println("21445");
		SessionFactory sf = configuration.buildSessionFactory();
		System.out.println("21446");

		Session session = sf.openSession();
		System.out.println("21448");
		Transaction tx = session.beginTransaction();
//		LabelRequestRecord labelRequestRecord = new LabelRequestRecord();
//		labelRequestRecord_copy2.setChannelName("FEDEX");
//		session.save(labelRequestRecord_copy2);
		//--select * from LableRequestRecord where LabelStatus<>3 and Created=7103 order by id desc
//		LableRequestRecord obj1 = session.get(LableRequestRecord.class, 1L);
//		System.out.println(obj1);

		//select * from LabelRequestRecord where LabelStatus<>3 and Created=7103 and CreateDate>'2019-7-14 00:00:00' order by id desc
//		SQLQuery createSQL = session.createSQLQuery("select * from LabelRequestRecord where LabelStatus<>3 and Created=7103 and CreateDate>'2019-7-14 00:00:00' order by id desc");
//		List<Object[]> list2 = createSQL.list();
//		for (Object[] lableRequestRecord3 : list2) {
//			System.out.println(Arrays.toString(lableRequestRecord3));
//		}
		
		
		Query createQuery = session.createQuery("from LabelRequestRecord where LabelStatus<>3 and CreateDate>'"+domo10()+"' order by id desc"); //
		List<LabelRequestRecord> list = createQuery.list(); 
		for(LabelRequestRecord lableRequestRecord2 : list) { 
		System.out.println(lableRequestRecord2.toString());  }
		tx.commit();
		session.close();
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
			return syear+"-"+smon+"-"+sday+" 00:00:00";
		}
}
