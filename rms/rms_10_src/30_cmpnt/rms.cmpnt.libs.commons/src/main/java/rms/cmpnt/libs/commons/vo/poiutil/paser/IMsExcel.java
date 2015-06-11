package rms.cmpnt.libs.commons.vo.poiutil.paser;

public interface IMsExcel extends IMsFile {
	
	/**
	 * 获得文档中sheet页数
	 * 
	 * @return
	 */
	public int getSheetCount();
	
	/**

	 * @param sheetIndex sheet索引
	 * @return
	 */
	public int getRowCountBySheet(int sheetIndex);
	
	/**
	 * 根据sheet索引与行索引，获得行的列数
	 * @param sheetIndex sheet索引
	 * @param rowIndex 行索引
	 * @return
	 */
	public int getCellCountByRow(int sheetIndex, int rowIndex);
	
}
