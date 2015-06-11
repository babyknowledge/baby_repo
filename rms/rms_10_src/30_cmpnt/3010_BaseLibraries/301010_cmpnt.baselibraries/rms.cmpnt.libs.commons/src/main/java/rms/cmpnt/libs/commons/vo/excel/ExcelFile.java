package rms.cmpnt.libs.commons.vo.excel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ExcelFile implements IUploadFile{
	private List rows;
	
	private Iterator itRows ;
	
	public ExcelFile(){
		rows = new ArrayList();
	}
	
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	public boolean hasNextRow(){
		if(itRows == null)
			itRows = rows.iterator();
		
		return itRows.hasNext();
	}
	
	public ExcelFileRow nextRow(){
		ExcelFileRow lst = null;
		if(hasNextRow())
			lst = (ExcelFileRow)itRows.next();
		return lst;
	}
	
	public void addRow(ExcelFileRow row){
		rows.add(row);
	}
	
	public  static void main(String[] args){
	
	}
	
}
