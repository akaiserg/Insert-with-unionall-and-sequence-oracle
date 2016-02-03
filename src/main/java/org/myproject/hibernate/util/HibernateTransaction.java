package org.myproject.hibernate.util;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
/**
 * 
 * Handler the transactions 
 *
 */
public class HibernateTransaction {

	/**
	 *  Hibernate session
	 */
	private Session session;
	/**
	 *  List  with  the  queries 
	 */
	private ArrayList<String> queryList;

	/**
	 * constructor 
	 * @param session
	 */
	public HibernateTransaction(Session session) {

		this.session = session;
		this.queryList= new ArrayList<String>();
	}
	
	/**
	 * Adds a  query to execute later 
	 * @param query
	 */
	public void  addQuery(String query){

		this.queryList.add(query);
		
	}
	
	/**
	 * Initializes  the transaction and  executes all the queries which  were  added before
	 * @return
	 */
	public String  commitTransaction(){
		
		Iterator<String> queryIterator = this.queryList.iterator();	
		String msj= "";
		try{
			this.session.getTransaction().begin();	
			while(queryIterator.hasNext()) {
				String query=queryIterator.next();
				SQLQuery  queryUpdate=this.session.createSQLQuery(query);
				queryUpdate.executeUpdate();		
			}
			this.session.getTransaction().commit();
			msj="Data has been saved..";
		}catch(Exception e){
			msj=e.getMessage();
			//System.out.println(e.getMessage());
			this.session.getTransaction().rollback();
		}
		return msj;
		
	}

}
