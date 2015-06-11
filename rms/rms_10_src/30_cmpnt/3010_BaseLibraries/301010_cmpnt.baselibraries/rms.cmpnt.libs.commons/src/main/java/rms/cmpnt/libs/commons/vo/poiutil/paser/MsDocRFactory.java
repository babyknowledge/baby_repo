package rms.cmpnt.libs.commons.vo.poiutil.paser;

import java.io.IOException;

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
public class MsDocRFactory {
	private static MsDocRFactory factory = null;
	private MsDocRFactory(){
	}

	public static MsDocRFactory getInstance(){
		if (factory == null) {
			factory = new MsDocRFactory();
		}
		return factory;
	}

	public IMsWord parse(String fileName) throws IOException {
		IMsWord word = null;
		if (fileName.trim().endsWith(".doc")) {
			word = new MsDocR();
			word.init(fileName);
		}else if (fileName.trim().endsWith(".docx")) {
			word = new MsDocxR();
			word.init(fileName);
		}else {
			System.out.println(fileName+"初始化错误！目前仅支持Microsoft Office Word 2003，2007！");
		}

		return word;
	}
}
