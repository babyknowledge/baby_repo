package rms.cmpnt.libs.commons.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import rms.cmpnt.libs.commons.exception.BeanUtilException;
import rms.cmpnt.libs.commons.exception.ServiceException;
import rms.cmpnt.libs.commons.vo.excel.ExcelCellValue;
import rms.cmpnt.libs.commons.vo.excel.ExcelFile;
import rms.cmpnt.libs.commons.vo.excel.ExcelFileRow;

public class ExcelFileUtil {
    /**
     * Logger for this class
     */
    private static final String SATAIC_DATA_CELL_TAG = "*";

    private static final String SATAIC_DATA_END_TAG = "END";

    private static final String SATAIC_COLUMN_START_TAG = "&";

    // private Upload upload = new Upload();

    // public UploadFileUtil(HttpServletRequest request,
    // HttpServletResponse response) {
    // init(request, response);
    // }

    // public void init(HttpServletRequest request, HttpServletResponse
    // response) {
    // try {
    // upload.initialize(request, response);
    // upload.upload();
    // upload.getRequest();
    // upload.setForcePhysicalPath(true);
    // upload.setDenyPhysicalPath(false);
    // upload.setTotalMaxFileSize(4194304); // �����ļ���С.
    // } catch (ServletException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // } catch (UploadException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }

    // public Object getParameter(String key) {
    // return upload.getRequest().getParameter(key);
    // }
    //
    // public Files getFiles() {
    // return upload.getFiles();
    // }
    
    public static ExcelFile parseExcel(String fileUrl) throws IOException,FileNotFoundException {
        InputStream is = null;
        ExcelFile excelFile = null;
        
        File sourceFile = new File(fileUrl);
        is = new FileInputStream(sourceFile);
        
        try{
            excelFile = parseExcel(is);
        }finally{
            if(is!=null)
                is.close();
        }
        
        return excelFile;
    }

    public static ExcelFile parseExcel(InputStream is) throws IOException {

        ExcelFile uploadFile = new ExcelFile();

        HSSFRow row = null;
        HSSFCell cell = null;
        POIFSFileSystem fs = new POIFSFileSystem(is);
        HSSFWorkbook hssfworkbook = new HSSFWorkbook(fs);

        HSSFSheet sheet = hssfworkbook.getSheetAt(0);
        Map mpColumnKeys = null;
        // �ӵ�1�п�ʼȡֵ
        for (short rowIndex = 0; rowIndex < sheet.getPhysicalNumberOfRows()
                && rowIndex < Short.MAX_VALUE; rowIndex++) {
            try{
            ExcelFileRow rowVO = null;
            row = sheet.getRow(rowIndex);
            cell = row.getCell((short) 0);
            String cellValue = cell.getStringCellValue();

            if (cellValue == null)
                continue;

            /**
             * ���ñ�ͷ��Ϣ,�����Ժ��ȡ
             */
            if (cellValue.trim().equals(SATAIC_COLUMN_START_TAG)) {
                rowVO = parseColumn(row);
                mpColumnKeys = rowVO.getMpKeys();
            } else if (cellValue.trim().equals(SATAIC_DATA_END_TAG)) {
                break;
            } else if (cellValue.trim().equals("#")) {
                continue;
            } else {
                rowVO = parseRow(row);
                rowVO.setColumnKeys(mpColumnKeys);
            }

            if (!rowVO.isEmpty()) {
                uploadFile.addRow(rowVO);
            }
            }catch(Exception e){
                throw new ServiceException("解析导入文件失败！");
            }
        }

        return uploadFile;
    }

    private static ExcelFileRow parseRow(HSSFRow row) {
        ExcelFileRow rowVO = new ExcelFileRow();
        HSSFCell cell = null;
        for (short cellIndex = 0; cellIndex < row.getPhysicalNumberOfCells(); cellIndex++) {
            cell = row.getCell((short) cellIndex);
            ExcelCellValue cellValueVO = getCellValue(cell);
            rowVO.addCell(cellValueVO);

        }

        return rowVO;
    }

    private static ExcelFileRow parseColumn(HSSFRow row) {
        ExcelFileRow rowVO = new ExcelFileRow();
        HSSFCell cell = null;

        for (short cellIndex = 0; cellIndex < row.getPhysicalNumberOfCells(); cellIndex++) {
            cell = row.getCell((short) cellIndex);
            String columnKey = getCellComment(cell);
            if (columnKey != null && !columnKey.equals("")) {
                rowVO.setColumnKey(columnKey, cellIndex);
            }
        }

        return rowVO;
    }

    private static ExcelCellValue getCellValue(HSSFCell cell) {
        ExcelCellValue cellValueVO = null;

        if (cell == null) {
            // �յ�Ԫ����
            return new ExcelCellValue("");
        }

        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            // ��Ԫ���������Ϊ�ַ���
            cellValueVO = new ExcelCellValue(cell.getRichStringCellValue()
                    .getString());
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:

            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                cellValueVO = new ExcelCellValue(sdf.format(date));
            } else {
                // ��Ԫ���������Ϊ�ַ���
                cellValueVO = new ExcelCellValue(cell.getNumericCellValue());
            }
            break;

        case HSSFCell.CELL_TYPE_BOOLEAN:
            // ��Ԫ���������Ϊ������
            cellValueVO = new ExcelCellValue(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_FORMULA:
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String value = cell.getRichStringCellValue().toString();
            if (value.equals("")) {
                int iValue = (int) cell.getNumericCellValue();
                value = String.valueOf(iValue);
            }
            cellValueVO = new ExcelCellValue(value);
            break;
        default:
            cell.getDateCellValue();
            cellValueVO = new ExcelCellValue("");
            break;
        }

        return cellValueVO;
    }

    private static String getCellComment(HSSFCell cell) {
        String columnKey = null;
        try {
            HSSFComment comment = cell.getCellComment();
            if (comment != null) {
                HSSFRichTextString txtString = comment.getString();

                if (txtString != null)
                    columnKey = txtString.toString().trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return columnKey;
    }

    public static String getDateValueFromCell(String cellValue) {
        long bTime = (Long.valueOf(cellValue) - 25569) * 24 * 60 * 60 * 1000;
        cellValue = DateUtil.dateToString(new Date(bTime), "yyyy-MM-dd");
        return cellValue;
    }
    
    
    /**
     * 根据导入的文件和传入的空对象，转换成带值的对象返回。
     * 例子：传入new DeviceVO(),返回值的Object中DeviceVO属性均被赋值。
     * 
     * @param obj
     * @param mpUpLoadFiles
     * @return
     * @throws ServiceException
     */
    private static List<Object> excelFile2Obj(Class<?> clz, Map<String, ExcelFile> mpUpLoadFiles)
            throws ServiceException {
        List<Object> result = new ArrayList<Object>();
        List<Map<String, Object>> dataMap = excelFile2Map(mpUpLoadFiles);
        try {
            for(Map<String, Object> mp : dataMap){
                Object objTmp = BeanUtil.createObject(clz, mp);
                result.add(objTmp);
            }
        } catch (BeanUtilException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据导入的文件和传入的空对象，转换成带值的对象返回。
     * 例子：传入new DeviceVO(),返回值的Object中DeviceVO属性均被赋值。
     * 
     * @param obj
     * @param mpUpLoadFiles
     * @return
     * @throws ServiceException
     */
    private static List<Map<String, Object>> excelFile2Map(
            Map<String, ExcelFile> mpUpLoadFiles) throws ServiceException {
        Set fileNames = mpUpLoadFiles.keySet();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Iterator it = fileNames.iterator(); it.hasNext();) {
            ExcelFile file = (ExcelFile) mpUpLoadFiles.get(it.next());
            while (file.hasNextRow()) {
                ExcelFileRow row = file.nextRow();
                Iterator mpKeys = row.getMpKeys().keySet().iterator();
                Map<String, Object> mp = new HashMap<String, Object>();
                while (mpKeys.hasNext()) {
                    String mpKey = (String) mpKeys.next();
                    mp.put(mpKey, row.getCell(mpKey).getStringValue());
                }
                result.add(mp);
            }
        }
        return result;
    }

    /**
     * 将导入的文件转换为Map
     * 
     * @param mpUpLoadFiles
     * @return
     * @throws ServiceException
     */
    public static List<Object> excelFile2Obj(String fileName,Class<?> clz, InputStream in)
            throws ServiceException {
        ExcelFile excelFile = null;
        List<Object> result = null;
        try {
            excelFile = parseExcel(in);
            Map<String, ExcelFile> mpUpLoadFiles = new HashMap<String, ExcelFile>();
            mpUpLoadFiles.put(fileName, excelFile);
            result = excelFile2Obj(clz, mpUpLoadFiles);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("导入文件解析失败！");
        }
        return result;
    }

    /**
     * 将导入的文件转换为Map
     * 
     * @param mpUpLoadFiles
     * @return
     * @throws ServiceException
     */
    public static List<Map<String, Object>> excelFile2Map(String fileName,InputStream in)
            throws ServiceException {
        ExcelFile excelFile = null;
        List<Map<String, Object>> result = null;
        try {
            excelFile = parseExcel(in);
            Map<String, ExcelFile> mpUpLoadFiles = new HashMap<String, ExcelFile>();
            mpUpLoadFiles.put(fileName, excelFile);
            result = excelFile2Map(mpUpLoadFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
