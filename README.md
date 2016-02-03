# Generating an union all insert with sequence in Oracle 


By using  "union all" it's easy to get  an only  insert   to execute  in the DB,  the problem  is that you can't   put   directly  the    nexval  value of  a sequence into the  union all insert.

In order to achieve this, you can use something like this:

```sql
SELECT
    level                        id_aut,
    BD_TEST.SEQ_T_CHILD.nextval id_seq
FROM
    dual CONNECT BY level<= 10;
```

By executing  this  you can get  10  ids of  the sequence to put each one into the union all insert.  Besides this can be very handy  when  you have to   share these ids or maybe when  these ids are parts of a FK and it's  necessary  to generate other inserts.


With  Hibernate is quite easy to  do  this by using  <b>createSQLQuery</b>:

```java 
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
    
  private String getsequenceSql(int numberFields, String sequenceName){
			
		String sql= "SELECT "+
			" level                        id_aut, "+
			" BD_TEST."+sequenceName+".nextval id_seq "+		
    		" FROM"+
    		" dual CONNECT BY level<= "+numberFields;	
		return sql;
		
	}

```

So, by getting a  list of  ids  of a sequence   in one shot  and also  making   an union all insert, you   will  only execute two statements into the DB.

