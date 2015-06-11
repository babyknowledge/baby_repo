package rms.cmpnt.libs.commons.vo.poiutil.paser;

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
public class ImageObject {
	/**
	 * info of the Image
	 */
	private byte[] imageInfo = null;
	/**
	 * File Extension (png,jpg)
	 */
	private String imageType = null;
	public ImageObject(byte[] info, String type){
		imageInfo = info;
		imageType = type;
	}
	public byte[] getImageInfo() {
		return imageInfo;
	}
	public void setImageInfo(byte[] imageInfo) {
		this.imageInfo = imageInfo;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	
}
