package cn.advu.workflow.web.facade.workflow.vo;

public class WebuploaderVO {
//	private String id; // 上传文件id WU_FILE_0
	private String name; // 上传文件名 diagrams.zip
	private String type; // 上传文件类型  application/x-zip-compressed
	private String lastModifiedDate; // 上传文件最后修改时间 Sat Jun 10 2017 23:30:16 GMT+0800

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
