package com.example.folderviewpagerdemo.data;

import java.util.ArrayList;
import java.util.List;

import com.example.folderviewpagerdemo.R;

public class DataFactroy {

	private static List<FolderInfo> folderInfoList;

	public static List<FolderInfo> getFolderInfoList() {
		if (folderInfoList == null) {
			folderInfoList = new ArrayList<FolderInfo>();
			FolderInfo folder1 = new FolderInfo("文件夹一");
			List<ItemInfo> items = new ArrayList<ItemInfo>();
			items.add(new ItemInfo(1, "Item 1", R.drawable.ic_launcher));
			items.add(new ItemInfo(2, "Item 2", R.drawable.ic_launcher));
			items.add(new ItemInfo(3, "Item 3", R.drawable.ic_launcher));
			folder1.setItems(items);

			FolderInfo folder2 = new FolderInfo("文件夹二");
			items = new ArrayList<ItemInfo>();
			items.add(new ItemInfo(4, "Item 4", R.drawable.ic_launcher));
			items.add(new ItemInfo(5, "Item 5", R.drawable.ic_launcher));
			items.add(new ItemInfo(6, "Item 6", R.drawable.ic_launcher));
			items.add(new ItemInfo(7, "Item 7", R.drawable.ic_launcher));
			items.add(new ItemInfo(8, "Item 8", R.drawable.ic_launcher));
			folder2.setItems(items);
			FolderInfo folder3 = new FolderInfo("文件夹三");
			items = new ArrayList<ItemInfo>();
			items.add(new ItemInfo(9, "Item 9", R.drawable.ic_launcher));
			items.add(new ItemInfo(10, "Item 10", R.drawable.ic_launcher));
			items.add(new ItemInfo(11, "Item 11", R.drawable.ic_launcher));
			folder3.setItems(items);

			folderInfoList.add(folder1);
			folderInfoList.add(folder2);
			folderInfoList.add(folder3);
		}
		return folderInfoList;
	}
}
