package rms.cmpnt.libs.commons.vo.poiutil.paser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;

/**
* 项目名称: <br>
* 模块名称: <br>
* 功能描述: <br>
* 创建日期: Nov 10, 2011 <br>
* 版权信息: Copyright (c) 2011<br>
* 公司信息: 东软集团股份有限公司 电信事业部-网管产品与系统部<br> 
* @author <a href="mailto: cheng-gl@neusoft.com">程国梁</a>
* @version v1.0
* <pre>
* 修改历史
*   序号      日期          修改人       修改原因
*    1    Nov 10, 2011        cheng-gl       创建
* </pre>
*/
public class MsDocR implements IMsWord {
	/**
	 * 文档对象
	 */
	private HWPFDocument msWord = null;
	/**
	 * 图片对象 List
	 */
	private List<Picture> pictureList = new ArrayList<Picture>();
	/**
	 * 表格对象 List
	 */
	private ArrayList<Table> tableList = new ArrayList<Table>();
	/**
	 * 图片个数
	 */
	private static int pictureCount = 0;
	/**
	 * 表格个数
	 */
	private static int tableCount = 0;

	/**
	 * 初始化文档对象
	 * @param filePath 要保存到的目录
	 * @return
	 * @throws IOException
	 */
	@Override
	public boolean init(String filePath) throws IOException{
		File fileTmp = new File(filePath);
		if (!fileTmp.exists()) {
			return false;
		}
		
		FileInputStream stream = new FileInputStream(filePath);
		
		msWord = new HWPFDocument(stream);
		// 初始化图片List
		pictureList = msWord.getPicturesTable().getAllPictures();
		
		pictureCount = pictureList.size();
		
		//msWord.getTextTable().getTextPieces().get(1)
		// 初始化表格列表
		TableIterator it = new TableIterator(msWord.getRange());
		while (it.hasNext()) {
			Table tb = (Table) it.next();
			tableList.add(tb);
		}
		
		tableCount = tableList.size();

		return true;
	}

	/**
	 * 文档中图片的个数
	 * @throws IOException
	 */
	@Override
	public int getPicturesCount() {
		return pictureCount;
	}
	
	/**
	 * 根据图片索引，获得图片大小
	 * @param pictureIndex 图片索引
	 * @return
	 */
	@Override
	public int getPicturesSizeByIndex(int pictureIndex) {
		try {
			return pictureList.get(pictureIndex).getSize();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 根据图片索引，获得图片类型
	 * @param pictureIndex 图片索引
	 * @return
	 */
	@Override
	public String getPicturesTypeByIndex(int pictureIndex) {
		try {
			return pictureList.get(pictureIndex).getMimeType();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据图片索引，获得图片扩展名
	 * @param pictureIndex 图片索引
	 * @return
	 */
	@Override
	public String getPicturesExtensionByIndex(int pictureIndex) {
		try {
			return pictureList.get(pictureIndex).suggestFileExtension();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 文档中表格的个数
	 * 
	 * @return
	 */
	@Override
	public int getTableCount() {
		return tableCount;
	}

	/**
	 * 根据图片索引，获得图片数据
	 * @param pictureIndex 图片索引
	 * @return
	 */
	@Override
	public byte[] getPicturesByIndex(int pictureIndex) {
		try {
			return pictureList.get(pictureIndex).getContent();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据表格的索引获得表的行数
	 * 
	 * @param tableIndex sheet索引或表索引
	 * @return
	 */
	@Override
	public int getRowCountForTableIndex(int tableIndex) {
		Table table = tableList.get(tableIndex);
		return table.numRows();
	}
	
	/**
	 * 根据表格的索引获得表的列数
	 * 
	 * @param tableIndex sheet索引或表索引
	 * @return
	 */
	@Override
	public int getCellCountForTableIndex(int tableIndex) {
		Table table = tableList.get(tableIndex);
		TableRow tr = table.getRow(0);
		return tr.numCells();
	}

	/**
	 * 取得第tbl个表格的，第line行第col列的，单元格的内容
	 * 
	 * @param tableIndex 第几个表格
	 * @param rowIndex 行索引
	 * @param cellIndex 列索引
	 * @return String
	 */
	@Override
	public String getCellText(int tableIndex, int rowIndex, int cellIndex){
		String cellText = "";
		Table table = tableList.get(tableIndex);
		if (table == null || rowIndex < 0 || cellIndex < 0) {
			return null;
		} else {
			if (rowIndex > table.numRows()) {
				System.out.println("ERROR！第"+tableIndex+"个表格，只有"+table.numRows()+"行,小于"+rowIndex);
			} else {
				if (cellIndex > table.getRow(rowIndex).numCells()) {
					System.out.println("ERROR！第"+tableIndex+"个表格，的第"+rowIndex+"行,只有"+table.getRow(rowIndex).numCells()+"个单元格。");
				} else{
					TableCell tc = table.getRow(rowIndex).getCell(cellIndex);//取得单元格
					// 取得单元格的内容
					cellText = tc.getParagraph(0).text().trim();
					/*for (int k = 0; k < tc.numParagraphs(); k++) {
						Paragraph para = tc.getParagraph(k);
						cellText += para.text().toString().trim();
					}*/
				}
			}
		}
		//System.out.println(tableIndex+ "-"+rowIndex+"-"+cellIndex+"="+cellText);
		return cellText;
	}
	
	/**
	 * 取得第tbl个表格的，第row行，列包含cellValue内容的列索引
	 * 
	 * @param tableIndex sheet索引或表索引
	 * @param rowIndex 行索引
	 * @param cellValue 列索引
	 * @return
	 */
	@Override
	public int getCellIndex(int tableIndex, int rowIndex, String cellValue) {
		Table table = tableList.get(tableIndex);
		if (table == null || rowIndex < 0 || (cellValue != null)) {
			return -1;
		} else {
			if (rowIndex > table.numRows()) {
				System.out.println("ERROR！第"+tableIndex+"个表格，只有"+table.numRows()+"行,小于"+rowIndex);
			} else {
				int colSize = table.getRow(rowIndex).numCells();
				for (int i=0;i<colSize;i++) {
					TableCell tc = table.getRow(rowIndex).getCell(i);//取得单元格
					if (tc.getParagraph(0).text().trim().contains(cellValue)) {
						return i;
					}
				}
			}
		}
		return -1;
	}
	
	/**
	 * 根据图片索引，将文档中的图片保存在制定目录下
	 * 
	 * @param path 文件存放路径
	 * @param fileName 文件名
	 * @param pictureIndex 图片索引
	 * @return
	 */
	@Override
	public boolean savePicturesToPath(String path, String fileName, int pictureIndex) {
		String fileFullPathName = "";
		if (fileName.length() > 0) {
			fileFullPathName = path + fileName;
		} else {
			fileFullPathName = path + String.valueOf(pictureIndex);
		}
		try {
			OutputStream out = new FileOutputStream(new File(fileFullPathName) + "." + getPicturesExtensionByIndex(pictureIndex));
			out.write(getPicturesByIndex(pictureIndex));
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 将文档中的图片保存在制定目录下
	 * 
	 * @param path 文件存放路径
	 * @return
	 */
	@Override
	public boolean savePicturesAllToPath(String path) {
		boolean bResult = true;
		int picturesCount = getPicturesCount();
		for (int i=0;i<picturesCount;i++) {
			bResult = bResult && savePicturesToPath(path, "", i);
		}
		return bResult;
	}

	/**
	 * 输出一个表格的详细信息，包括几行几列,每个单元格的内容
	 * 
	 * @param tb
	 * @return
	 */
	public String showTalbeInfo(Table tb){
		String info = "";
		System.out.println("此表格的行数为"+tb.numRows());
		info += "此表格的行数为"+tb.numRows();
		for (int i = 0; i < tb.numRows(); i++) {
			TableRow tr = tb.getRow(i);
			System.out.println(" 第"+(i+1)+"行的单元格数为:"+tr.numCells());
			info += " 第"+(i+1)+"行的单元格数为:"+tr.numCells();
		}
		for (int i = 0; i < tb.numRows(); i++) {
			TableRow tr = tb.getRow(i);
			// 迭代列，默认从0开始
			for (int j = 0; j < tr.numCells(); j++) {
				TableCell tc = tr.getCell(j);//取得单元格
				int cellWidth = tc.getWidth();
				System.out.print("["+i+","+j+"]的宽度"+cellWidth+"=>");
			
				// 取得单元格的内容
				for (int k = 0; k < tc.numParagraphs(); k++) {
					Paragraph para = tc.getParagraph(k);
					String s = para.text().toString().trim();
					System.out.println("文本串为["+s+"]");
				}
			}
		}
		return info;
	}

	public static void main(String[] args) {
		final String filename = "F:\\chenggl\\DXW11012.doc";
		try {
			MsDocR doc = new MsDocR();
			doc.init(filename);
//			System.out.println(doc.extractMSWordText());
//			doc.extractParagraphTexts();
//			System.out.println(doc.countTables());
//			doc.getCellText(1, 2, 1);
//			doc.getCellText(1, 2, 9);
//			doc.getCellText(1, 4, 1);
//			doc.getCellText(2, 2, 1);
//			doc.getCellText(2, 5, 5);
//			doc.getCellText(3, 1, 1);
//			System.out.println(doc.countTables());
//			OutputStream out = new FileOutputStream(new File("F:\\chenggl\\test\\testbyte.png"));
//			doc.getImage(3);
//			out.write(doc.getImage(3));
//			out.close();
//			doc.exportImages("F:\\chenggl\\test\\",3);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
