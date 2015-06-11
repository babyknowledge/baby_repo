package rms.cmpnt.libs.commons.vo.poiutil.paser;

import java.io.IOException;

/**
* 项目名称: <br>
* 模块名称: <br>
* 功能描述: <br>
* 创建日期: Nov 9, 2011 <br>
* 版权信息: Copyright (c) 2011<br>
* 公司信息: 东软集团股份有限公司 电信事业部-网管产品与系统部<br> 
* @author <a href="mailto: cheng-gl@neusoft.com">程国梁</a>
* @version v1.0
* <pre>
* 修改历史
*   序号      日期          修改人       修改原因
*    1    Nov 9, 2011        cheng-gl       创建
* </pre>
*/
public class FileTestDemo {

	public static void main(String[] args) {
		final String fileName1 = "C:\\Users\\名单.xls";
//		final String fileName2 = "F:\\chenggl\\MSword.docx";
//		final String fileName3 = "F:\\chenggl\\MSword.docxx";
//		MsDocFactory factory = MsDocFactory.getInstance();
//		IMsFile word = null;
		IMsFile excel = null;
//		IMsFile word3 = null;
		try {
			String path = "C:\\Users\\";
			excel = new MsExcelR();
			excel.init(fileName1);
			int picturesCount = excel.getPicturesCount();
			for (int i=0;i<picturesCount;i++) {
				excel.savePicturesToPath(path, "", i);
			}
			
			//word = factory.parse(fileName2);

		} catch (IOException e1) {
			e1.printStackTrace();
			return ;
		}
	}
}
