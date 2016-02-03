package org.myproject.hibernate.sequence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.myproject.hibernate.dto.SequenceDto;
/**
 * 
 * Generates  a DTO with N values of  a sequence 
 *
 */
public class SequenceGeneratorList {
	
	/**
	 * Hibernate session 
	 */
	private Session session;
	
	/**
	 * Constructor 
	 * @param session
	 */
	public SequenceGeneratorList(Session session){
		
		this.session=session;
	}
	
	/**
	 * Returns  a list with a N  values of a sequence 
	 * @param numberFields: Number of  values of the sequence  needed
	 * @param sequenceName: Name of the sequence
	 * @return
	 */
	public ArrayList<SequenceDto>  getSequenceList(int numberFields, String sequenceName  ){
		
		ArrayList<SequenceDto> listDto= new ArrayList<SequenceDto>();
		SQLQuery sqlQuery = this.session.createSQLQuery(this.getsequenceSql(numberFields, sequenceName));
		List<Object[]> list = (List<Object[]>)sqlQuery.list();		
		for (Object[] entity : list) {			 	
			SequenceDto dto= new SequenceDto();			
		    dto.setCounter((BigDecimal)entity[0]);
		    dto.setSequence((BigDecimal)entity[1]);
		    listDto.add(dto);
	    }		
		return listDto;
	}
	
	
	/**
	 *  Returns  the sql to execute  in order to get the sequence values 
	 * @param numberFields: Number of  values of the sequence  needed
	 * @param sequenceName: Name of the sequence
	 * @return
	 */
	private String getsequenceSql(int numberFields, String sequenceName){
			
		String sql= "SELECT "+
			" level                        id_aut, "+
			" BD_TEST."+sequenceName+".nextval id_seq "+		
    		" FROM"+
    		" dual CONNECT BY level<= "+numberFields;	
		return sql;
		
	}	

}