package org.myproject.hibernate;

import java.util.ArrayList;

import org.hibernate.Session;
import org.myproject.hibernate.dto.ParentDto;
import org.myproject.hibernate.dto.SequenceDto;
import org.myproject.hibernate.sequence.SequenceGeneratorList;
import org.myproject.hibernate.tablehandler.ParentTableHandler;
import org.myproject.hibernate.util.HibernateTransaction;
import org.myproject.hibernate.util.HibernateUtil;

public class TestInsertGenerator {

	public static void main(String[] args) throws Exception {	
		
		init();

	}

	/**
	 *  Init the process 
	 * @throws Exception
	 */
	private static void init() throws Exception {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		//System.out.println(session.isConnected());
		SequenceGeneratorList sGenerator = new SequenceGeneratorList(session);
		ArrayList<ParentDto> listDataParent = getListParent();
		int numberValuesSeq = listDataParent.size();
		ArrayList<SequenceDto> listSequenceParent = sGenerator.getSequenceList(numberValuesSeq, "SEQ_T_PARENT");
		ParentTableHandler parentHandler = new ParentTableHandler();
		String insertUnion = parentHandler.generateInsertUnion(listSequenceParent, listDataParent);
		HibernateTransaction transaction = new HibernateTransaction(session);
		transaction.addQuery(insertUnion);		
		String msj=transaction.commitTransaction();
		System.out.println("Message:  "+msj);
		
	}

	/**
	 *  Returns a list with  test cases 
	 * @return
	 */
	private static ArrayList<ParentDto> getListParent() {

		ArrayList<ParentDto> list = new ArrayList<ParentDto>();
		ParentDto dto1 = new ParentDto();
		dto1.setParName("Parent 1");
		list.add(dto1);
		ParentDto dto2 = new ParentDto();
		dto2.setParName("Parent 2");
		list.add(dto2);
		ParentDto dto3 = new ParentDto();
		dto3.setParName("Parent 3");
		list.add(dto3);
		ParentDto dto4 = new ParentDto();
		dto4.setParName("Parent 4");
		list.add(dto4);
		ParentDto dto5 = new ParentDto();
		dto5.setParName("Parent 5");
		list.add(dto5);
		return list;

	}

}
