package rms.cmpnt.libs.commons.vo.poiutil.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import rms.cmpnt.libs.commons.vo.poiutil.write.MsDocxW;

public class DocxUtil extends MsDocxW
{
	//private final MsDocxW	docx = new MsDocxW();

	/**
	 * 打开已知的word文档
	 *
	 * @param docPath
	 *            文档路径
	 * @throws IOException
	 */
	public boolean openWord(final String docPath) throws IOException
	{
		return init(docPath);
	}

	/**
	 * 关闭word应用程序
	 */
	public void closeWord(final String docPath)
	{
		writeDocx(docPath);
	}


	/**
	 * 插入一条数据到报表中
	 */
	public void insertText(String string)
	{
		//System.out.println(string);
		XWPFRun xwpfR = createParagraph();
		xwpfR.setText(string);
	}

	/**
	 * 填写单次试车报告单
	 */
	public void setReportResult(int index,String result)
	{
		gotoIndexWriteText(index, 0, result);
	}
}
