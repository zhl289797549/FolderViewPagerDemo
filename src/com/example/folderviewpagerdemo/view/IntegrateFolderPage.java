package com.example.folderviewpagerdemo.view;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.folderviewpagerdemo.R;
import com.example.folderviewpagerdemo.data.FolderInfo;
import com.example.folderviewpagerdemo.data.ItemInfo;
import com.example.folderviewpagerdemo.view.grid.BaseDynamicGridAdapter;
import com.example.folderviewpagerdemo.view.grid.DynamicGridView;

public class IntegrateFolderPage extends RelativeLayout{
	protected static final String TAG = "IntegrateFolderPage";
	private FolderInfo info;
	private DynamicGridView gridview;
	
	private Context context;
	public void setFolderInfo(FolderInfo info){
		this.info = info;
		refresh();
	}
	
	private void refresh() {
		final List<ItemInfo> itemList = info.getItems();
		gridview.setAdapter(new CheeseDynamicAdapter(context,itemList,4));
	}

	public IntegrateFolderPage(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
	}

	public IntegrateFolderPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public IntegrateFolderPage(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		gridview = (DynamicGridView) findViewById(R.id.folder_content_grid);
		
//		gridview.setNumColumns(2);
//		gridview.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		
		gridview.setOnDragListener(new DynamicGridView.OnDragListener() {
            public void onDragStarted(int position) {
                Log.d(TAG, "drag started at position " + position);
            }

            public void onDragPositionsChanged(int oldPosition, int newPosition) {
                Log.d(TAG, String.format("drag item position changed from %d to %d", oldPosition, newPosition));
            }
        });
		gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            	gridview.startEditMode(position);
                return true;
            }
        });

		gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, parent.getAdapter().getItem(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
	}
	
	
	class CheeseDynamicAdapter extends BaseDynamicGridAdapter {
	    public CheeseDynamicAdapter(Context context, List<?> items, int columnCount) {
	        super(context, items, columnCount);
	    }

	    public View getView(int position, View convertView, ViewGroup parent) {
	        CheeseViewHolder holder;
	        if (convertView == null) {
	            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid, null);
	            holder = new CheeseViewHolder(convertView);
	            convertView.setTag(holder);
	        } else {
	            holder = (CheeseViewHolder) convertView.getTag();
	        }
	        holder.build(getItem(position));
	        return convertView;
	    }

	    private class CheeseViewHolder {
	        private TextView titleText;
	        private ImageView image;
	        private View view;
	        private CheeseViewHolder(View view) {
	        	this.view = view;
	            titleText = (TextView) view.findViewById(R.id.item_title);
	            image = (ImageView) view.findViewById(R.id.item_img);

	        }

	        void build(Object info) {
	        	ItemInfo itemInfo = (ItemInfo) info;
	            titleText.setText(itemInfo.getName());
	            image.setImageResource(itemInfo.getDrawable());
	            view.setTag(R.string.app_name, itemInfo.getName());
	        }
	    }
	}
	
}
