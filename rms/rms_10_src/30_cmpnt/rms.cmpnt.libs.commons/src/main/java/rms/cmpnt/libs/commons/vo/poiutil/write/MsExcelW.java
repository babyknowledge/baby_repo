package rms.cmpnt.libs.commons.vo.poiutil.write;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rms.cmpnt.libs.commons.vo.poiutil.paser.MsExcelR;
import rms.cmpnt.libs.commons.vo.poiutil.util.ExcelUtil;

public class MsExcelW extends MsExcelR {
	
	public MsExcelW() {
		super();
	}

	/**
	 * 保存excel对象到文件
	 *
	 * @param fileName
	 *            文件名称
	 * @return
	 * @throws IOException 
	 */
	public void writeExcelx(String fileName) throws IOException {
		FileOutputStream out = new FileOutputStream(fileName);
		wb.write(out);
		out.close();
	}
	
	/**
	 * 保存excel对象到流
	 *
	 * @param fileName
	 *            文件名称
	 * @return
	 * @throws IOException 
	 */
	public void writeExcelx(OutputStream out) throws IOException {
		wb.write(out);
		out.close();
	}

	/**
	 * 定位单元格写文字
	 *
	 * @param sheetIndex
	 *            sheet索引
	 * @param rowIndex
	 *            行索引
	 * @param cellIndex
	 *            列索引
	 * @param text
	 *            写入文本
	 * @return
	 */
	public void gotoCellWriteText(int sheetIndex, int rowIndex, int cellIndex, String text) {
		Sheet sheet = sheetList.get(sheetIndex);
		if (sheet == null) {
			sheet = wb.createSheet();
			sheetList.add(sheet);
		}
		Row row = sheet.getRow(rowIndex);
		if (row == null) {
			row = sheet.createRow(rowIndex);
		}
		Cell cell = row.getCell(cellIndex);
		if (cell == null) {
			cell = row.createCell(cellIndex);
		}
		cell.setCellValue(text);
	}

	/**
	 * 向表格的指定位置入图片(如果连续插入多张图片时需要外部计算图片的长和宽，调整行列参数位置)
	 *
	 * @param bytes
	 *            图片字节流
	 * @param sheetIndex
	 *            sheet索引
	 * @param row
	 *            插入图片的行
	 * @param coll
	 *            插入图片的列
	 * @return
	 */
	public void gotoInsertPicture(byte[] bytes, int sheetIndex, int row, int coll) {
		CreationHelper helper = wb.getCreationHelper();
		int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
		// create drawing
		Drawing drawing = sheetList.get(sheetIndex).createDrawingPatriarch();
		// add a picture shape
		ClientAnchor anchor = helper.createClientAnchor();
		anchor.setCol1(coll);
		anchor.setRow1(row);
		Picture pict = drawing.createPicture(anchor, pictureIdx);
		// auto-size picture
		pict.resize(1);
	}

	/**
	 * 新建sheet，并定位单元格写文字
	 *
	 * @param rowIndex
	 *            行索引
	 * @param cellIndex
	 *            列索引
	 * @param text
	 *            写入文本
	 * @return
	 */
	public void createSheetGotoCellWriteText(String sheetName, int rowIndex, int cellIndex, String text) {
		Sheet sheet = wb.createSheet();
		Cell cell = sheet.getRow(rowIndex).getCell(cellIndex);
		if (cell != null) {
			cell.setCellValue(text);
		}
	}

	public static void main(String[] args) {
		try {
			// create a new workbook
			Workbook wb = new org.apache.poi.xssf.usermodel.XSSFWorkbook(); // or
																			// new
																			// HSSFWorkbook();
			CreationHelper helper = wb.getCreationHelper();
			// add a picture in this workbook.
			java.io.InputStream is = new java.io.FileInputStream("aaa.PNG");
			byte[] bytes = IOUtils.toByteArray(is);
			is.close();
			int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
			// create sheet
			Sheet sheet = wb.createSheet();
			// create drawing
			Drawing drawing = sheet.createDrawingPatriarch();
			// add a picture shape
			ClientAnchor anchor = helper.createClientAnchor();
			anchor.setCol1(1);
			anchor.setRow1(1);
			Picture pict = drawing.createPicture(anchor, pictureIdx);
			// auto-size picture
			pict.resize(2);
			// save workbook
			String file = "picture.xls";
			if (wb instanceof XSSFWorkbook)
				file += "x";
			FileOutputStream fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();

			final String filename = "D:\\记录单.xlsx";
			ExcelUtil excelU = new ExcelUtil();
			excelU.openExcel(filename);
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("KPI_0", "value0");
			hashMap.put("KPI_1", "value1");
			hashMap.put("KPI_2", "value2");
			hashMap.put("KPI_3", "value3");
			hashMap.put("KPI_4", "value4");
			hashMap.put("KPI_5", "value5");
			hashMap.put("KPI_6", "value6");
			hashMap.put("KPI_7", "value7");
			hashMap.put("KPI_8", "value8");
			hashMap.put("KPI_9", "value9");
			hashMap.put("KPI_10", "value10");
			hashMap.put("KPI_11", "value11");
			hashMap.put("KPI_12", "value12");
			hashMap.put("KPI_13", "value13");
			// excelU.putTxtToRow(0, hashMap);
			// excelU.putTxtToRow(0, hashMap);
			// doc.gotoIndexWriteText(1, 0,
			// doc.gotoIndexWriteText(2, 0,
			excelU.closeExcel("D:\\录单.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
