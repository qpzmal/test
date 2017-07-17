package cn.advu.workflow.common.golbal;

import java.util.HashMap;
import java.util.Map;

public class AjaxJson {
	private boolean success = true;
	private String msg = "操作成功";
	private Object obj = null;
	private Map<String, Object> attributes = new HashMap<String, Object>();

	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return this.obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "AjaxJson{" +
				"success=" + success +
				", msg='" + msg + '\'' +
				", obj=" + obj +
				", attributes=" + attributes +
				'}';
	}
}
