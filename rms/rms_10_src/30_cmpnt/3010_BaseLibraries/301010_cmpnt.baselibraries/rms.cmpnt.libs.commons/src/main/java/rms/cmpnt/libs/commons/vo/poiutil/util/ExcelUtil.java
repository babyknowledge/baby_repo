package rms.cmpnt.libs.commons.vo.poiutil.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import rms.cmpnt.libs.commons.exception.ServiceException;
import rms.cmpnt.libs.commons.vo.poiutil.write.MsExcelW;
import sun.misc.BASE64Decoder;

public class ExcelUtil extends MsExcelW
{
	//private final MsExcelW excel = new MsExcelW();

	/**
	 *
	 * <p>Discription:根据报表模板的路径和输出流初始化NtasReport类</p>
	 * @coustructor 方法.
	 */
	public void init(String model_file, String report_file) throws Exception {
		createFolder(new File(report_file).getParentFile().getPath());
		openExcel(model_file);
	}
	/**
	 * 创建目录
	 * @param path
	 * @throws IOException
	 */
	private static void createFolder(String path) throws IOException {
		File fileDir = new File(path);
		if (!fileDir.exists())
			FileUtils.forceMkdir(fileDir);
	}
	/**
	 * 打开已知的excel文档
	 *
	 * @param docPath
	 *            文档路径
	 * @throws IOException
	 */
	public boolean openExcel(final String docPath) throws IOException
	{
		return init(docPath);
	}

	/**
	 * 关闭excel应用程序
	 * @throws IOException 
	 *
	 * @throws Exception
	 */
	public void closeExcel(final String excelPath) throws IOException
	{
		writeExcelx(excelPath);
	}
	
	/**
	 * 关闭excel应用程序
	 * @throws IOException 
	 *
	 * @throws Exception
	 */
	public void closeExcel(final OutputStream out) throws IOException
	{
		writeExcelx(out);
	}

	/**
	 * 向表格的第一个sheet页0行0列插入图片
	 */
	public void insertImage(int sheetIndex, byte[] bytes) {
		gotoInsertPicture(bytes, sheetIndex, 0, 0);
	}
	
	/**
	 * 根据模板将数据写入excel并令存
	 * 
	 * @param templateFileUrl 模板文件访问地址
	 * @param targetFileUrl 目标文件访问地址
	 * @param startLen 模板中起始行写入
	 * @param keys 写入数据字段顺序
	 * @param listMap 待写入数据
	 * @throws IOException
	 */
	public void writeExcel(String templateFileUrl,String targetFileUrl,int startLen,String[] keys,List<Map<String, String>> listMap) throws IOException {
		int len = 1;
		if (!openExcel(templateFileUrl)) throw new ServiceException("模板：[" + templateFileUrl + "]打开失败！");
		for (Map<String, String> map : listMap) {
			int cellIndex = 1;
			gotoCellWriteText(0, startLen, cellIndex, String.valueOf(len));
			len++;
			for (String key : keys) {
				cellIndex++;
				if (map.get(key) != null && !"null".equals(map.get(key))) {
					gotoCellWriteText(0, startLen, cellIndex, map.get(key));
				}
			}
			startLen++;
		}
		closeExcel(targetFileUrl);
	}
	
	
	
	/**
	 * 按照多个sheet 写入Excel
	 * 
	 * @param dataMapArr   数据数组
	 * @param templateFileUrl 模板文件访问地址
     * @param targetFileUrl 目标文件访问地址
     * @param startLen 模板中起始行写入
     * @param keys 写入数据字段顺序
	 * @throws IOException
	 */
    public void writeExcel(List<List<Map<String, String>>> dataMapArr, String templateFileUrl,String targetFileUrl,int startLen,String[] keys) throws IOException {
      
        if( null ==dataMapArr ){
            throw new ServiceException("dataMapArr 不能为空！");
        }
        if (!openExcel(templateFileUrl)) throw new ServiceException("模板：[" + templateFileUrl + "]打开失败！");
        for(int i= 0 ; i<dataMapArr.size(); i ++){
            int starRow = startLen;
            int len = 1;
            for (Map<String, String> map : dataMapArr.get(i)) {
                int cellIndex = 1;
                gotoCellWriteText(i, starRow, cellIndex, String.valueOf(len));
                len++;
                for (String key : keys) {
                    cellIndex++;
                    if (map.get(key) != null && !"null".equals(map.get(key))) {
                        gotoCellWriteText(i, starRow, cellIndex, map.get(key));
                    }
                }
                starRow++;
            }
        }
      
        closeExcel(targetFileUrl);
    }
    
    /**
     * 上传收测卡片 写入错误信息
     * @param dataMapArr
     * @param templateFileUrl
     * @param targetFileUrl
     * @param startLen
     * @param keys
     * @throws IOException
     */
    public void writeExcel(List<List<Map<String, String>>> dataMapArr, String templateFileUrl,String targetFileUrl,int startLen,String[] keys,List<Map<String, String>> dateMap,int dateLen) throws IOException {
        
        if( null ==dataMapArr ){
            throw new ServiceException("dataMapArr 不能为空！");
        }
        if (null == dateMap) {
        	throw new ServiceException("dateMap 不能为空！");
		}
        
        if (!openExcel(templateFileUrl)) throw new ServiceException("模板：[" + templateFileUrl + "]打开失败！");
        
        for (int i = 0; i < dateMap.size(); i++) {
        	Map<String, String> map = dateMap.get(i);
        		 int cellIndex = 1;
        		 for(String key : keys) {
    			  cellIndex++;
                  if (map.get(key) != null && !"null".equals(map.get(key))) {
                      gotoCellWriteText(i, dateLen, cellIndex, map.get(key));
                  }
        		 }
        	 
		}
        
        for(int i= 0 ; i<dataMapArr.size(); i ++){
            int starRow = startLen;
            int len = 1;
            for (Map<String, String> map : dataMapArr.get(i)) {
                int cellIndex = 1;
                gotoCellWriteText(i, starRow, cellIndex, String.valueOf(len));
                len++;
                for (String key : keys) {
                    cellIndex++;
                    if (map.get(key) != null && !"null".equals(map.get(key))) {
                        gotoCellWriteText(i, starRow, cellIndex, map.get(key));
                    }
                }
                starRow++;
            }
        }
      
        closeExcel(targetFileUrl);
    }
	
	
	
	
	
	/**
	 * 根据模板将数据写入excel格式流
	 * 
	 * @param templateFileUrl 模板文件访问地址
	 * @param startLen 模板中起始行写入
	 * @param keys 写入数据字段顺序
	 * @param listMap 待写入数据
	 * @throws IOException
	 */
	public void writeExcel(String templateFileUrl,OutputStream out,int startLen,String[] keys,List<Map<String, String>> listMap,List<ImageBase64DTO> imgDTOList, int sheetIndex, int row, int coll) throws IOException {
		int len = 1;
		if (!openExcel(templateFileUrl)) throw new ServiceException("模板：[" + templateFileUrl + "]打开失败！");
		for (Map<String, String> map : listMap) {
			int cellIndex = 1;
			gotoCellWriteText(0, startLen, cellIndex, String.valueOf(len));
			len++;
			for (String key : keys) {
				cellIndex++;
				if (map.get(key) != null && !"null".equals(map.get(key))) {
					gotoCellWriteText(0, startLen, cellIndex, map.get(key));
				}
			}
			startLen++;
		}
		
		int imgHeight = 0;
		//int imgWidth = 0;
		for (ImageBase64DTO dto : imgDTOList) {
			//System.out.println(dto.getWidth());
			//System.out.println(dto.getHeight());
			gotoInsertPicture(base64StringToImage(dto.getBase64Img()),sheetIndex + 1,imgHeight,coll);
			imgHeight += row + Integer.valueOf(dto.getHeight()) / 15;
		}
		
		closeExcel(out);
	}
	
	/**
	 * 将图片串转换成byte流数据
	 */
	static byte[] base64StringToImage(String base64String) {
		byte[] bytes = null;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			bytes = decoder.decodeBuffer(base64String);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	/**
	 * xls 文件读取
	 * @param fileUrl
	 * @param keys
	 * @param startLen
	 * @return
	 * @throws Exception
	 */
	public List<List<Map<String, String>>> readExcel(String fileUrl,String[] keys,int startLen) throws Exception {
		InputStream is=null;
		HSSFWorkbook wb = null;
		POIFSFileSystem fs = null;
		try {
			is = new FileInputStream(fileUrl);
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (FileNotFoundException e1) {
			throw new IllegalAccessException("文件读取失败");
		}
		Map<String, String> map =null;
		List<List<Map<String, String>>> list = new ArrayList<List<Map<String, String>>>();
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
			HSSFSheet sheet = wb.getSheetAt(i);
			if (sheet==null) {
				continue;
			}
			//sheet.getLastRowNum();
			int sheetNum = sheet.getLastRowNum();
			for (int j = startLen; j < sheetNum; j++) {
				HSSFRow row = sheet.getRow(j);
				if (null==row||j==startLen+1) {
					continue;
				}
//				if (j==startLen) {
//					
//				}
				int cellIndex = 1;
				map = new HashMap<String, String>();
				for (String key : keys) {
					cellIndex++;
					
					map.put(key, getValue(row.getCell(cellIndex)));
				}
				
				listMap.add(map);
			}
			
			list.add(listMap);
		}
		
		return list;
	}
	
	private String getValue(HSSFCell cell){
		if (null==cell) {
			
			return null;
		} else if(cell.getCellType()==cell.CELL_TYPE_NUMERIC){
			
			return String.valueOf(cell.getNumericCellValue());
		} else if(cell.getCellType()==cell.CELL_TYPE_BOOLEAN){
			
			return String.valueOf(cell.getBooleanCellValue());
		} else {
			
			return String.valueOf(cell.getStringCellValue());
		}
		
	}
}
