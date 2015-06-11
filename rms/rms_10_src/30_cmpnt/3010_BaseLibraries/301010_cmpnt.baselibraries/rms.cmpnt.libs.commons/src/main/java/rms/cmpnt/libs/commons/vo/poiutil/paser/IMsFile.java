package rms.cmpnt.libs.commons.vo.poiutil.paser;

import java.io.IOException;

public interface IMsFile {

	/**
	 * 初始化文档对象
	 * @param filePath 要保存到的目录
	 * @return
	 * @throws IOException
	 */
	public boolean init(String filePath) throws IOException;
	
	/**
	 * 获得文档中图片的个数
	 *
	 * @return
	 */
	public int getPicturesCount();
	
	/**
	 * 根据图片索引，获得图片数据
	 * @param pictureIndex 图片索引
	 * @return
	 */
	public byte[] getPicturesByIndex(int pictureIndex);
	
	/**
	 * 根据图片索引，获得图片大小
	 * @param pictureIndex 图片索引
	 * @return
	 */
	public int getPicturesSizeByIndex(int pictureIndex);
	
	/**
	 * 根据图片索引，获得图片类型
	 * @param pictureIndex 图片索引
	 * @return
	 */
	public String getPicturesTypeByIndex(int pictureIndex);
	
	/**
	 * 根据图片索引，获得图片扩展名
	 * @param pictureIndex 图片索引
	 * @return
	 */
	public String getPicturesExtensionByIndex(int pictureIndex);

	/**
	 * 取得第tbl个表格的，第row行，col列的内容(String)
	 * 
	 * @param tableIndex sheet索引或表索引
	 * @param rowIndex 行索引
	 * @param cellIndex 列索引
	 * @return
	 */
	public String getCellText(int tableIndex, int rowIndex, int cellIndex);
	
	/**
	 * 取得第tbl个表格的，第row行，列包含cellValue内容的列索引
	 * 
	 * @param tableIndex sheet索引或表索引
	 * @param rowIndex 行索引
	 * @param cellValue 列索引
	 * @return
	 */
	public int getCellIndex(int tableIndex, int rowIndex, String cellValue);

	/**
	 * 根据图片索引，将文档中的图片保存在指定目录下
	 * 
	 * @param path 文件存放路径
	 * @param fileName 文件名
	 * @param pictureIndex 图片索引
	 * @return
	 */
	public boolean savePicturesToPath(String path, String fileName, int pictureIndex);
	
	/**
	 * 将文档中的图片保存在制定目录下
	 * 
	 * @param path 文件存放路径
	 * @return
	 */
	public boolean savePicturesAllToPath(String path);
	
}
