package cc.niushuai.tools.everything.entity;

public class FileEntity {

	private String id;
	private String name;
	private String size;
	private String type;
	private String fileImei;
	private String disk;
	private String path;
	private String absolutePath;
	private String createTime;
	private String updateTime;
	
	public static String FIELD_ID = "id";
	public static String FIELD_NAME = "name";
	public static String FIELD_SIZE = "size";
	public static String FIELD_TYPE = "type";
	public static String FIELD_FILE_IMEI = "file_imei";
	public static String FIELD_DISK = "disk";
	public static String FIELD_PATH = "path";
	public static String FIELD_ABSOLUTE_PATH = "absolute_path";
	public static String FIELD_CREATE_TIME = "create_time";
	public static String FIELD_UPDATE_TIME = "update_time";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFileImei() {
		return fileImei;
	}
	public void setFileImei(String fileImei) {
		this.fileImei = fileImei;
	}
	public String getAbsolutePath() {
		return absolutePath;
	}
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getDisk() {
		return disk;
	}
	public void setDisk(String disk) {
		this.disk = disk;
	}
	
}
