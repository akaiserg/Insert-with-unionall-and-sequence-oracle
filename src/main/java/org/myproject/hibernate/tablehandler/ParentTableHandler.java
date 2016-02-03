package org.myproject.hibernate.tablehandler;

import java.util.ArrayList;
import java.util.Iterator;

import org.myproject.hibernate.dto.ParentDto;
import org.myproject.hibernate.dto.SequenceDto;
/**
 * Handler the table Parent
 * 
 *
 */
public class ParentTableHandler {
	
	/**
	 * Generates an only insert with all the values 
	 * @param listSequence: Sequence values 
	 * @param listData: Data 
	 * @return
	 */
	public String  generateInsertUnion(ArrayList<SequenceDto> listSequence, ArrayList<ParentDto> listData ){
		
		 listData = this.fillDto(listSequence, listData);
		 return this.generateInsertUnion(listData);
		
	}

	/**
	 *  Sets the value  of the sequence into the  DTOs
	 * @param listSequence: Sequence values
	 * @param listData    : Data 
	 * @return
	 */
	private ArrayList<ParentDto> fillDto(ArrayList<SequenceDto> listSequence,ArrayList<ParentDto> listData) {
	
		Iterator<SequenceDto>sequenceIterator = listSequence.iterator();
		//System.out.println(listData.get(0).getParName());
		int cont=0;
		while(sequenceIterator.hasNext()) {
			SequenceDto seqDto=sequenceIterator.next();			
			listData.get(cont).setParId(seqDto.getSequence());
			cont++;
		}
		return listData;
		
	}
	
	/**
	 *  Generates the  insert with the data 
	 * @param listData :data
	 * @return
	 */
	private String generateInsertUnion(ArrayList<ParentDto> listData){
		
		String initPart= "insert into T_PARENT ";
		String bodyPart="";
		Iterator<ParentDto>dataIterator = listData.iterator();
		int count= listData.size();
		//System.out.println(listData.get(0).getParName());
		int cont=0;
		while(dataIterator.hasNext()) {
			ParentDto dto=dataIterator.next();
			bodyPart=bodyPart+this.generateSelect(dto);
			if((count-1)>cont){
				bodyPart=this.addUnionAll(bodyPart);
			}
			cont++;			
		}
		return initPart+bodyPart;
		
	}
	
	/**
	 *  Takes the dto and  generates a string with the values
	 * @param dto: dto with the data 
	 * @return
	 */
	private String generateSelect(ParentDto dto){
		
		String select="Select  "+dto.getParId()+" , "+ "'"+dto.getParName()+"' from dual ";
		return select;
		
	}
	
	/**
	 *  Adds  union all  at the end of the string 
	 * @param select: Select which is part of the  final insert 
	 * @return
	 */
	private String addUnionAll(String select){
		
		return select+" UNION ALL ";
	}

}
