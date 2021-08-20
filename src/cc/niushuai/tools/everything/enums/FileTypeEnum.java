package cc.niushuai.tools.everything.enums;

public enum FileTypeEnum {
	
	FILE("file", "文件"),
	DIRECTORY("directory", "文件夹");
	
	private String code;
	private String desc;
	
	FileTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
