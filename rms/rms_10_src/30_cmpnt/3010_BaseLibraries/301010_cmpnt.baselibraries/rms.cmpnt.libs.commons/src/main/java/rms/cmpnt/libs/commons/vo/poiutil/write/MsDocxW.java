package rms.cmpnt.libs.commons.vo.poiutil.write;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import rms.cmpnt.libs.commons.vo.poiutil.paser.MsDocxR;
import rms.cmpnt.libs.commons.vo.poiutil.util.DocxUtil;

public class MsDocxW extends MsDocxR
{
	public MsDocxW() {
		super();
	}

	/**
	 * 保存docx对象到文件
	 * @param fileName 文件名称
	 * @return
	 */
	public void writeDocx(String fileName) {
        try
		{
    		FileOutputStream out = new FileOutputStream(fileName);
    		msWord.write(out);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 定位段落关键字写文本
	 * @param key 关键字
	 * @param index 偏移量
	 * @param text 写入文本
	 * @return
	 */
	public void gotoKeyWriteText(int offset, String key, String text) {
		XWPFParagraph paragraph = getParagraphObjectForKey(key);
		if (paragraph != null) {
			XWPFRun xwpfR = null;
			if (paragraph.getRuns().size() > 0) {
				xwpfR = paragraph.getRuns().get(0);
			} else {
				xwpfR = paragraph.createRun();
			}
			xwpfR.setTextPosition(offset);
			xwpfR.setText(text);
		}
	}

	/**
	 * 定位段落索引写文本
	 * @param index 段落索引
	 * @param offset 偏移量
	 * @param text 写入文本
	 * @return
	 */
	public void gotoIndexWriteText(int index, int offset, String text) {
		XWPFParagraph paragraph = getParagraphObjectForIndex(index);
		if (paragraph != null) {
			XWPFRun xwpfR = null;
			if (paragraph.getRuns().size() > 0) {
				xwpfR = paragraph.getRuns().get(0);
			} else {
				xwpfR = paragraph.createRun();
			}
			xwpfR.setTextPosition(offset);
			xwpfR.setText(text);
		}
	}

	/**
	 * 定位段落索引后再定位关键字写文本
	 * @param index 段落索引
	 * @param offset 偏移量
	 * @param key 关键字
	 * @param text 写入文本
	 * @return
	 */
	public void gotoIndexAndKeyWriteText(int index, int offset, String key, String text) {
		XWPFParagraph paragraph = getParagraphObjectForIndex(index);
		if (paragraph != null) {
			if (paragraph.getText().contains(key)) {
				XWPFRun xwpfR = null;
				if (paragraph.getRuns().size() > 0) {
					xwpfR = paragraph.getRuns().get(0);
				} else {
					xwpfR = paragraph.createRun();
				}
				xwpfR.setTextPosition(offset);
				xwpfR.setText(text);
			}
		}
	}

	/**
	 * 创建一个新段落
	 * @param
	 * @return
	 */
	public XWPFRun createParagraph() {
		XWPFParagraph paragraph = msWord.createParagraph();
		return paragraph.createRun();
	}

	private int getFormat(String imgFileType) {
		int format = XWPFDocument.PICTURE_TYPE_EMF;
		if(imgFileType.equals("emf")) format = XWPFDocument.PICTURE_TYPE_EMF;
        else if(imgFileType.equals("wmf")) format = XWPFDocument.PICTURE_TYPE_WMF;
        else if(imgFileType.equals("pict")) format = XWPFDocument.PICTURE_TYPE_PICT;
        else if(imgFileType.equals("jpeg") || imgFileType.equals("jpg")) format = XWPFDocument.PICTURE_TYPE_JPEG;
        else if(imgFileType.equals("png")) format = XWPFDocument.PICTURE_TYPE_PNG;
        else if(imgFileType.equals("dib")) format = XWPFDocument.PICTURE_TYPE_DIB;
        else if(imgFileType.equals("gif")) format = XWPFDocument.PICTURE_TYPE_GIF;
        else if(imgFileType.equals("tiff")) format = XWPFDocument.PICTURE_TYPE_TIFF;
        else if(imgFileType.equals("eps")) format = XWPFDocument.PICTURE_TYPE_EPS;
        else if(imgFileType.equals("bmp")) format = XWPFDocument.PICTURE_TYPE_BMP;
        else if(imgFileType.equals("wpg")) format = XWPFDocument.PICTURE_TYPE_WPG;
        else {
            System.err.println("Unsupported picture: " + imgFileType + ". Expected emf|wmf|pict|jpeg|png|dib|gif|tiff|eps|bmp|wpg");
        }
		return format;
	}

	/**
	 * 定位段落索引后插入图片
	 * @param index 段落索引
	 * @param picture 图片文件流或文件字节流
	 * @param imgFileType 图片类型（后缀名）
	 * @param width 图片宽（单位像素）
	 * @param height 图片高（单位像素）
	 * @return
	 */
	public void gotoIndexWritePicture(int index, FileInputStream picture, String imgFileType, int width, int height) {
        XWPFParagraph paragraph = getParagraphObjectForIndex(index);
        XWPFRun xwpfRun = paragraph.createRun();
        xwpfRun.addCarriageReturn();
        try {
        	xwpfRun.addPicture((FileInputStream)picture, getFormat(imgFileType), imgFileType, Units.toEMU(width), Units.toEMU(height));
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        xwpfRun.addBreak();
	}

	/**
	 * 根据关键字定位段落插入图片
	 * @param offset 偏移量
	 * @param key 关键字
	 * @param imgFileStream 图片文件流
	 * @param imgFileType 图片类型（后缀名）
	 * @param width 图片宽（单位像素）
	 * @param height 图片高（单位像素）
	 * @return
	 */
	public void gotoKeyWritePicture(int offset, String key, FileInputStream picture, String imgFileType, int width, int height) {
		XWPFParagraph paragraph = getParagraphObjectForKey(key);
		if (paragraph != null) {
			XWPFRun xwpfRun = null;
			if (paragraph.getRuns().size() > 0) {
				xwpfRun = paragraph.getRuns().get(0);
			} else {
				xwpfRun = paragraph.createRun();
			}
			xwpfRun.setTextPosition(offset);
			try {
	        	xwpfRun.addPicture((FileInputStream)picture, getFormat(imgFileType), imgFileType, Units.toEMU(width), Units.toEMU(height));
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		final String filename = "D:\\记录单.docx";
		DocxUtil docxU = new DocxUtil();
		try
		{
			docxU.openWord(filename);
			HashMap<String, String> hashMap = new HashMap<String, String>();

			docxU.closeWord("D:\\录单.docx");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
