package rms.cmpnt.libs.commons.vo.poiutil.paser;

 /**
* 项目名称: <br>
* 模块名称: <br>
* 功能描述: <br>
* 创建日期: Nov 16, 2011 <br>
* 版权信息: Copyright (c) 2011<br>
* 公司信息: 东软集团股份有限公司 电信事业部-网管产品与系统部<br> 
* @author <a href="mailto: cheng-gl@neusoft.com">程国梁</a>
* @version v1.0
* <pre>
* 修改历史
*   序号      日期          修改人       修改原因
*    1    Nov 16, 2011        cheng-gl       创建
* </pre>
*/
public interface IMsWord extends IMsFile{
	
	/**
	 * 文档中表格的个数
	 * 
	 * @return
	 */
	public int getTableCount();

	/**
	 * 根据表格的索引获得表的行数
	 * 
	 * @param tableIndex sheet索引或表索引
	 * @return
	 */
	public int getRowCountForTableIndex(int tableIndex);
	
	/**
	 * 根据表格的索引获得表的列数(假设每行一样的列数)
	 * 
	 * @param tableIndex sheet索引或表索引
	 * @return
	 */
	public int getCellCountForTableIndex(int tableIndex);
}
