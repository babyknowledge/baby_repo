package rms.cmpnt.libs.commons.vo.poiutil.paser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ddf.EscherClientAnchorRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;

public class MsExcelR implements IMsExcel{

	/**
	 * 文档对象
	 */
	protected Workbook wb = null;
	/**
	 * sheet对象 List
	 */
	protected List<Sheet> sheetList = new ArrayList<Sheet>();
	/**
	 * sheet个数
	 */
	private int sheetCount = 0;
	/**
	 * 图片个数
	 */
	private int pictureCount = 0;
	/**
	 * 图片对象 List
	 */
	protected List<HSSFPictureData> pictureList = null;

	/**
	 * 初始化文档对象
	 * @param filePath 要保存到的目录
	 * @return
	 * @throws IOException
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean init(String filePath) throws IOException {
		File fileTmp = new File(filePath);
		if (!fileTmp.exists()) {
			return false;
		}
		
		if (filePath.endsWith(".xls")) {
			FileInputStream stream = new FileInputStream(filePath);
			wb = new HSSFWorkbook(stream);
			stream.close();
		} else if (filePath.endsWith(".xlsx")) {
			FileInputStream stream = new FileInputStream(filePath);
			wb = new XSSFWorkbook(stream);
			stream.close();
		} else if (filePath.endsWith(".xlt")) {
			FileInputStream stream = new FileInputStream(filePath);
			wb = new HSSFWorkbook(stream);
			stream.close();
		} else {
			System.out.println("");
			return false;
		}

		sheetCount = wb.getNumberOfSheets();
		for (int i = 0; i < sheetCount; i++) {
			sheetList.add(wb.getSheetAt(i));
		}

		pictureList = (List<HSSFPictureData>) wb.getAllPictures();
		pictureCount = pictureList.size();
		
		return true;
	}

	/**
	 * 获得文档中sheet页数
	 * 
	 * @return
	 */
	@Override
	public int getSheetCount() {
		return sheetCount;
	}
	
	/**
	 * 获得文档中图片的个数
	 * 
	 * @return
	 */
	@Override
	public int getPicturesCount() {
		return pictureCount;
	}

	/**
	 * 根据sheet索引，获得行数
	 * @param sheetIndex sheet索引
	 * @return
	 */
	@Override
	public int getRowCountBySheet(int sheetIndex) {
		try {
			return sheetList.get(sheetIndex).getLastRowNum();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 根据sheet索引与行索引，获得行的列数
	 * @param sheetIndex sheet索引
	 * @param rowIndex 行索引
	 * @return
	 */
	@Override
	public int getCellCountByRow(int sheetIndex, int rowIndex) {
		try {
			return sheetList.get(sheetIndex).getRow(rowIndex).getPhysicalNumberOfCells();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 根据sheet索引、行索引、列索引，获得单元格数据
	 * @param sheetIndex sheet索引
	 * @param rowIndex 行索引
	 * @param cellIndex 列索引
	 * @return
	 */
	@Override
	public String getCellText(int sheetIndex, int rowIndex, int cellIndex) {
		String obj_content = "";
		Cell cell = null;
		try {
			cell = sheetList.get(sheetIndex).getRow(rowIndex).getCell(cellIndex);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		if (cell == null) {
			return null;
		}

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC: //0 数值型
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				obj_content = cell.getDateCellValue().toString();
			} else {
				Integer numInteger = new Integer((int) cell.getNumericCellValue());
				obj_content = String.valueOf(numInteger);
			}
			break;
		case HSSFCell.CELL_TYPE_STRING: //1 字符串型
			obj_content = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA: //2 公式型
			break;
		case HSSFCell.CELL_TYPE_BLANK: //3 空值
			obj_content = "";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN: //4 布尔值
			obj_content = "";
			break;
		case HSSFCell.CELL_TYPE_ERROR: //5 错误
			obj_content = "";
			break;
		default:
			obj_content = "";
		}
		return obj_content;
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
	public int getCellIndex(int sheetIndex, int rowIndex, String cellValue) {
		String obj_content = "";
		Cell cell = null;
		try {
			int colSize = sheetList.get(sheetIndex).getRow(rowIndex).getLastCellNum();
			for (int i=0;i<colSize;i++) {
				cell = sheetList.get(sheetIndex).getRow(rowIndex).getCell(i);
				if (cell == null) {
					return -1;
				}
				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC: //0 数值型
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						obj_content = cell.getDateCellValue().toString();
					} else {
						Integer numInteger = new Integer((int) cell.getNumericCellValue());
						obj_content = String.valueOf(numInteger);
					}
					break;
				case HSSFCell.CELL_TYPE_STRING: //1 字符串型
					obj_content = cell.getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_FORMULA: //2 公式型
					break;
				case HSSFCell.CELL_TYPE_BLANK: //3 空值
					obj_content = "";
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN: //4 布尔值
					obj_content = "";
					break;
				case HSSFCell.CELL_TYPE_ERROR: //5 错误
					obj_content = "";
					break;
				default:
					obj_content = "";
				}
				if (obj_content.contains(cellValue)) {
					return i;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
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
	

	/*
	 * 以下代码解析图片信息，还未正式确认
	 */
	
	public List<MyPictureData> getAllPictures(HSSFWorkbook workbook) {
        List<MyPictureData> list = new ArrayList<MyPictureData>();

        List<HSSFPictureData> pictureList = workbook.getAllPictures();
        List<ClientAnchorInfo> clientAnchorRecords = getClientAnchorRecords(workbook);
        
        if (pictureList.size() != clientAnchorRecords.size()) {
            throw new RuntimeException("解析文件中的图片信息出错，找到的图片数量和图片位置信息数量不匹配");
        }

    	for (int i = 0; i < pictureList.size(); i++) {
            HSSFPictureData pictureData = pictureList.get(i);
            ClientAnchorInfo anchor = clientAnchorRecords.get(i);
            HSSFSheet sheet = anchor.sheet;
            EscherClientAnchorRecord clientAnchorRecord = anchor.clientAnchorRecord;

            MyPictureData myp = new MyPictureData(workbook, sheet, pictureData, clientAnchorRecord);
            list.add(myp);
        }

        return list ;
    }

	private class ClientAnchorInfo {
		public HSSFSheet sheet;
		public EscherClientAnchorRecord clientAnchorRecord;

		public ClientAnchorInfo(HSSFSheet sheet, EscherClientAnchorRecord clientAnchorRecord) {
			super();
			this.sheet = sheet;
			this.clientAnchorRecord = clientAnchorRecord;
		}
	}

	private List<ClientAnchorInfo> getClientAnchorRecords(HSSFWorkbook workbook) {
		List<ClientAnchorInfo> list = new ArrayList<ClientAnchorInfo>();

		EscherAggregate drawingAggregate = null;
		HSSFSheet sheet = null;
		List<EscherRecord> recordList = null;
		Iterator<EscherRecord> recordIter = null;
		int numSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numSheets; i++) {
			sheet = workbook.getSheetAt(i);
			drawingAggregate = sheet.getDrawingEscherAggregate();
			if (drawingAggregate != null) {
				recordList = drawingAggregate.getEscherRecords();
				recordIter = recordList.iterator();
				while (recordIter.hasNext()) {
					getClientAnchorRecords(sheet, recordIter.next(), 1, list);
				}
			}
		}

		return list;
	}

	private void getClientAnchorRecords(HSSFSheet sheet, EscherRecord escherRecord, int level, List<ClientAnchorInfo> list) {
		List<EscherRecord> recordList = null;
		Iterator<EscherRecord> recordIter = null;
		EscherRecord childRecord = null;
		recordList = escherRecord.getChildRecords();
		recordIter = recordList.iterator();
		while (recordIter.hasNext()) {
			childRecord = recordIter.next();
			if (childRecord instanceof EscherClientAnchorRecord) {
				ClientAnchorInfo e = new ClientAnchorInfo(sheet, (EscherClientAnchorRecord) childRecord);
				list.add(e);
			}
			if (childRecord.getChildRecords().size() > 0) {
				getClientAnchorRecords(sheet, childRecord, level + 1, list);
			}
		}
	}

	public static void main(String[] args) {
		final String filename = "C:\\Users\\名单.xls";
		try {
			MsExcelR xls = new MsExcelR();
			xls.init(filename);

			System.out.println("getPicturesCount():" + xls.getPicturesCount());
			System.out.println("getPicturesByIndex(1):" + xls.getPicturesByIndex(1));
			System.out.println("getSheetCount():" + xls.getSheetCount());
			System.out.println("getRowCountBySheet(0):" + xls.getRowCountBySheet(0));
			System.out.println("getCellCountByRow(0, 1):" + xls.getCellCountByRow(0, 1));
			System.out.println("getCellData(0, 2, 2):" + xls.getCellText(0, 2, 2));
			System.out.println("getCellData(0, 1, 2):" + xls.getCellText(0, 1, 2));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
