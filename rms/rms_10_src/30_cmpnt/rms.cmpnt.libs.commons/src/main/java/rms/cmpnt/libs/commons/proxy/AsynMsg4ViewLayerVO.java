package rms.cmpnt.libs.commons.proxy;

import java.io.Serializable;

public class AsynMsg4ViewLayerVO implements Serializable {

	private String type = null;
	
	private String message = null;
	
	private Object data = null;
	
	private String time = null;
	
	private int visible = 0;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}
	
	public String toString(){
		return "[type]:"+type+"[message]:"+message+"[data]:"+data+"[time]:"+time+"[visible]:"+visible;
	}
}
