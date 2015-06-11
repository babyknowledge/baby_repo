package rms.cmpnt.libs.commons.vo.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ExcelFileRow {
	private List cells;
	
	private Iterator itCells ;
	
	private Map mpKeys;
	
	public ExcelFileRow(){
		cells = new ArrayList();
		mpKeys = new HashMap();
	}
	
	public void setColumnKey(String key ,int index){
		mpKeys.put(key,new Integer(index));
	}
	public void setColumnKeys(Map columnKeys){
		mpKeys.putAll(columnKeys);
	}
	
	public List getCells() {
		return cells;
	}
	public void setcells(List cells) {
		this.cells = cells;
	}
	
	public boolean hasNextCell(){
		if(itCells == null)itCells = cells.iterator();
		
		return itCells.hasNext();
	}
	
	public ExcelCellValue nextCell(){
		ExcelCellValue value = null;
		if(hasNextCell())value = (ExcelCellValue)itCells.next();
		return value;
	}
	
	public void addCell(ExcelCellValue cell){
		cells.add(cell);
	}
	public ExcelCellValue getCell(int index){
		return (ExcelCellValue)cells.get(index);
	}
	
	public boolean isEmpty(){
		boolean isEmpty = false;
		if(cells.size() == 0 ) isEmpty = true;
		return isEmpty;
	}
	
	public ExcelCellValue getCell(String columnKey){
		ExcelCellValue vo = null;
		String key = columnKey.trim();
		Integer oIndex = (Integer)mpKeys.get(key);
		if(oIndex != null && oIndex.intValue() < cells.size() ) vo = (ExcelCellValue)cells.get(oIndex.intValue());
		
		return vo==null?new ExcelCellValue(""):vo;
	}
	
	public  static void main(String[] args){
		
	}

	public Map getMpKeys() {
		return mpKeys;
	}

	public void setMpKeys(Map mpKeys) {
		this.mpKeys = mpKeys;
	}
	
}
