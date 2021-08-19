package cc.niushuai.tools.everything.util;

import java.util.ArrayList;
import java.util.List;

public class VariableConstant {
	
	public static final List<String> FILTER_COMBOX_ITEM_LIST = new ArrayList<String>();
	
	
	static {
		addItemName();
	}


	private static void addItemName() {
		FILTER_COMBOX_ITEM_LIST.add("所有");
		FILTER_COMBOX_ITEM_LIST.add("图片");
		FILTER_COMBOX_ITEM_LIST.add("音频");
		FILTER_COMBOX_ITEM_LIST.add("视频");
		FILTER_COMBOX_ITEM_LIST.add("文档");
		FILTER_COMBOX_ITEM_LIST.add("压缩文件");
		FILTER_COMBOX_ITEM_LIST.add("可执行文件");
		FILTER_COMBOX_ITEM_LIST.add("文件夹");
	}
}
