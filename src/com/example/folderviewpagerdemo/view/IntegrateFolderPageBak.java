package com.example.folderviewpagerdemo.view;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.folderviewpagerdemo.R;
import com.example.folderviewpagerdemo.data.FolderInfo;
import com.example.folderviewpagerdemo.data.ItemInfo;

public class IntegrateFolderPageBak extends RelativeLayout{
	private FolderInfo info;
	private IntegrateFolderGridView gridview;
	
	private Context context;
	public void setFolderInfo(FolderInfo info){
		this.info = info;
		refresh();
	}
	
	private void refresh() {
		final List<ItemInfo> itemList = info.getItems();
		gridview.setAdapter(new DragDropGridAdapter() {

			public int itemCount() {
				return itemList.size();
			}

			public View view(int index) {
				LinearLayout layout = new LinearLayout(context);
				layout.setOrientation(LinearLayout.VERTICAL);
				
				ImageView icon = new ImageView(context);
				ItemInfo item = (ItemInfo) getItemAt(index);
				icon.setImageResource(item.getDrawable());
				icon.setPadding(15, 15, 15, 15);
				
				layout.addView(icon);
				
				TextView label = new TextView(context);
				label.setTag("text");
				label.setText(item.getName());	
				label.setTextColor(Color.BLACK);
				label.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
			
				label.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));

				layout.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				
				layout.addView(label);
				return layout;
			}

			public int rowCount() {
				return 3;
			}

			public int columnCount() {
				return 3;
			}

			public void printLayout() {
				
			}

			public void swapItems(int itemIndexA, int itemIndexB) {
				// TODO Auto-generated method stub
				
			}

			public void deleteItem(int itemIndex) {
				// TODO Auto-generated method stub
				
			}

			public Object getItemAt(int index) {
				return itemList.get(index);
			}});
	}

	public IntegrateFolderPageBak(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
	}

	public IntegrateFolderPageBak(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public IntegrateFolderPageBak(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		gridview = (IntegrateFolderGridView) findViewById(R.id.folder_content_grid);
	}
	
	
}
