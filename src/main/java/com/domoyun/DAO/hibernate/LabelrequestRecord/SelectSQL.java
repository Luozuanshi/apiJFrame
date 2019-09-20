package com.domoyun.DAO.hibernate.LabelrequestRecord;

import java.text.SimpleDateFormat;
import java.time.Instant;
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
	/**
	 * 查询TMS当天订单数据
	 * @return 返回LabelRequestRecord对象List集合
	 */
	@Test
	public static List<LabelRequestRecord> demo9() {
		//创建session,打开事务Transaction
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();

		Query createQuery = session.createQuery("from LabelRequestRecord where CreateDate>'"+CurrentDateTime()+"' order by id desc"); //
		List<LabelRequestRecord> list = createQuery.list();
		for(LabelRequestRecord lableRequestRecord2 : list) {
		System.out.println(lableRequestRecord2.toString());  }
		//提交事务
		tx.commit();
		return list;
	}

		public static String CurrentDateTime() {
			return new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date());
		}

		///测试代码
		public static void main(String[] args) {
				System.out.println(CurrentDateTime());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
			}
}