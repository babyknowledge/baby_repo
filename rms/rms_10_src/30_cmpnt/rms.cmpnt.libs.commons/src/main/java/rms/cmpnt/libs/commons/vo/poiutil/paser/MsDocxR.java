package rms.cmpnt.libs.commons.vo.poiutil.paser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

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
public class MsDocxR implements IMsWord{
	/**
	 * 文档对象
	 */
	protected XWPFDocument msWord = null;
	/**
	 * 图片对象的 CharacterRun List
	 */
	protected List<XWPFPictureData> pictureList = null;
	/**
	 * 表格对象的 List
	 */
	protected List<XWPFTable> tableList = null;
	/**
	 * 段落对象的 List
	 */
	protected List<XWPFParagraph> paragrapList = null;
	/**
	 * 图片个数
	 */
	private int pictureCount = 0;
	/**
	 * 表格个数
	 */
	private int tableCount = 0;
	/**
	 * 表格个数
	 */
	private int paragrapCount = 0;
	
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
		
		msWord = new XWPFDocument(stream);
		stream.close();

		tableList = msWord.getTables();
		
		tableCount = tableList.size();
		
		pictureList = msWord.getAllPictures();
		
		pictureCount = pictureList.size();
		
		paragrapList = msWord.getParagraphs();
		
		paragrapCount = paragrapList.size();
		
		return true;
	}

	/**
	 * 文档中图片的个数
	 * 
	 */
	public int getParagrapCount() {
		//return msWord.getParagraphs().size();
		return paragrapCount;
	}

	/**
	 * 文档中图片的个数
	 * 
	 */
	@Override
	public int getPicturesCount() {
		//return msWord.getAllPictures().size();
		return pictureCount;
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
	 * 根据图片索引，获得图片大小
	 * @param pictureIndex 图片索引
	 * @return
	 */
	@Override
	public int getPicturesSizeByIndex(int pictureIndex) {
		try {
			return pictureList.get(pictureIndex).getData().length;
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
			int type = pictureList.get(pictureIndex).getPictureType();
			return String.valueOf(type);
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
	 * 根据图片索引，获得图片数据
	 * @param pictureIndex 图片索引
	 * @return
	 */
	@Override
	public byte[] getPicturesByIndex(int pictureIndex) {
		try {
			return pictureList.get(pictureIndex).getData();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取第一个具有该关键字的段落对象
	 * @param key 关键字
	 * @return
	 */
	public XWPFParagraph getParagraphObjectForKey(String key) {
		int size = paragrapList.size();
		for (int i=0;i<size;i++) {
			XWPFParagraph paragraph = paragrapList.get(i);
			if (paragraph.getText().contains(key)) {
				return paragraph;
			}
		}
		return null;
	}

	/**
	 * 获取第一个具有该关键字的段落文本
	 * @param key 关键字
	 * @return
	 */
	public String getParagraphStringForKey(String key) {
		int size = paragrapList.size();
		for (int i=0;i<size;i++) {
			XWPFParagraph paragraph = paragrapList.get(i);
			if (paragraph.getText().contains(key)) {
				return paragraph.getText();
			}
		}
		return null;
	}

	/**
	 * 根据索引获取段落对象
	 * @param key 关键字
	 * @return
	 */
	public XWPFParagraph getParagraphObjectForIndex(int index) {
		if (paragrapList.size() >= index) {
			return paragrapList.get(index);
		}
		return null;
	}

	/**
	 * 根据索引获取段落文本
	 * @param key 关键字
	 * @return
	 */
	public String getParagraphStringForIndex(int index) {
		if (paragrapList.size() >= index) {
			return paragrapList.get(index).getText();
		}
		return null;
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
	public String getCellText(int tableIndex, int rowIndex, int cellIndex) {
		String cellText = null;
		XWPFTable table = tableList.get(tableIndex);
		if (table == null) {
			return cellText;
		} else {
			if (rowIndex > table.getNumberOfRows()) {
				System.out.println("ERROR！第" + tableIndex + "个表格，只有" + table.getNumberOfRows() + "行,小于" + rowIndex);
			} else {
				if (cellIndex > table.getRow(rowIndex).getTableCells().size()) {
					System.out.println("ERROR！第" + tableIndex + "个表格，的第" + rowIndex + "行,只有" + table.getRow(rowIndex).getTableCells().size() + "个单元格。");
				} else {
					cellText = table.getRow(rowIndex).getCell(cellIndex).getText().trim();
				}
			}
		}
		//System.out.println(tableIndex + "-" + rowIndex + "-" + cellIndex + "=" + cellText);
		return cellText;
	}
	
	/**
	 * 取得第tbl个表格的，第row行，列包含cellValue内容的列索引
	 * 
	 * @param tableIndex 表索引
	 * @param rowIndex 行索引
	 * @param cellValue 列索引
	 * @return
	 */
	@Override
	public int getCellIndex(int tableIndex, int rowIndex, String cellValue) {
		XWPFTable table = tableList.get(tableIndex);
		if (table == null) {
			return -1;
		} else {
			int colSize = table.getNumberOfRows();
			for (int i=0;i<colSize;i++) {
				if (table.getRow(rowIndex).getCell(i).getText().trim().contains(cellValue)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * 根据表格的索引获得表的行数
	 * 
	 * @param tableIndex 表索引
	 * @return
	 */
	@Override
	public int getRowCountForTableIndex(int tableIndex) {
		XWPFTable table = tableList.get(tableIndex);
		return table.getNumberOfRows();
	}
	
	/**
	 * 根据表格的索引获得表的列数(假设每行一样的列数)
	 * 
	 * @param tableIndex 表索引
	 * @return
	 */
	@Override
	public int getCellCountForTableIndex(int tableIndex) {
		XWPFTable table = tableList.get(tableIndex);
		return table.getRow(0).getTableCells().size();
	}
	
	/**
	 * 根据表格的索引获得表对象
	 * 
	 * @param tableIndex 表索引
	 * @return
	 */
	public XWPFTable getTableForTableIndex(int tableIndex) {
		if (tableIndex <= tableList.size()) {
			return tableList.get(tableIndex);
		} else {
			return null;
		}
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
	 * 显示一个Table的详细信息。几行几列和每个单元格的内容(字符串)
	 * 
	 * @param tb
	 * @return
	 */
	public String showTalbeInfo(XWPFTable tb) {
		String info = "";
		System.out.println("此表格的行数为" + tb.getNumberOfRows());
		for (int i = 0; i < tb.getNumberOfRows(); i++) {
			XWPFTableRow tr = tb.getRow(i);
			System.out.println("第" + (i + 1) + "行的单元格数为:"
					+ tr.getTableCells().size());
		}
		for (int i = 0; i < tb.getNumberOfRows(); i++) {
			XWPFTableRow tr = tb.getRow(i);
			// 迭代列，默认从0开始
			for (int j = 0; j < tr.getTableCells().size(); j++) {
				XWPFTableCell tc = tr.getCell(j);// 取得单元格
				System.out.println("[" + i + "," + j + "]文本串为["
						+ tc.getText().trim() + "]");
			}
		}
		return info;
	}

	public static void main(String[] args) {
		final String filename2 = "D:\\报告.docx";
		try {
			MsDocxR doc = new MsDocxR();
			doc.init(filename2);
			// System.out.println(doc.extractMSWordText());
			// doc.extractParagraphTexts();
//			System.out.println(doc.countTables());
//			doc.getCellText(1, 1, 1);
//			doc.getCellText(1, 2, 1);
//			doc.getCellText(2, 2, 2);
//			doc.getCellText(1, 2, 3);
//			doc.getCellText(1, 3, 7);
//			doc.getCellText(1, 3, 8);
//			doc.getCellText(1, 5, 1);
//			OutputStream out = new FileOutputStream(new File("F:\\chenggl\\test\\testbytedocx.png"));
//			doc.getImage(1);
//			out.write(doc.getImage(1));
//			out.close();
//			for (int i = 0; i < doc.getTables().size(); i++) {
//				doc.showTalbeInfo(doc.getTables().get(i));
//			}
//			doc.exportImages("F:\\chenggl\\test\\", 1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
