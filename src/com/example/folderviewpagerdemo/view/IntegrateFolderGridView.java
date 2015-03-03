package com.example.folderviewpagerdemo.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class IntegrateFolderGridView extends ViewGroup {

	private DragDropGridAdapter adapter;

	/**
	 * view集合
	 */
	private List<View> views = new ArrayList<View>();

	private int columnWidthSize;
	private int rowHeightSize;
	private int biggestChildWidth;
	private int biggestChildHeight;
	private int computedColumnCount;
	private int computedRowCount;

	public IntegrateFolderGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public IntegrateFolderGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public IntegrateFolderGridView(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);

		Display display = wm.getDefaultDisplay();

		widthSize = acknowledgeWidthSize(widthMode, widthSize, display);
		heightSize = acknowledgeHeightSize(heightMode, heightSize, display);

		// 测量内部view的宽高
		adaptChildrenMeasuresToViewSize(widthSize, heightSize);
		// 搜索最大的子view
		searchBiggestChildMeasures();
		// 计算行列数
		computeGridMatrixSize(widthSize, heightSize);
		// 计算每个格子的宽高
		computeColumnsAndRowsSizes(widthSize, heightSize);
		// 设置当前视图大小
		setMeasuredDimension(widthSize, heightSize);
	}

	private void computeColumnsAndRowsSizes(int widthSize, int heightSize) {
		columnWidthSize = widthSize / computedColumnCount;
		rowHeightSize = heightSize / computedRowCount;
	}

	private void computeGridMatrixSize(int widthSize, int heightSize) {
		if (adapter.columnCount() != -1 && adapter.rowCount() != -1) {
			computedColumnCount = adapter.columnCount();
			computedRowCount = adapter.rowCount();
		} else {
			if (biggestChildWidth > 0 && biggestChildHeight > 0) {
				computedColumnCount = widthSize / biggestChildWidth;
				computedRowCount = heightSize / biggestChildHeight;
			}
		}

		if (computedColumnCount == 0) {
			computedColumnCount = 1;
		}

		if (computedRowCount == 0) {
			computedRowCount = 1;
		}
	}

	private void searchBiggestChildMeasures() {
		biggestChildWidth = 0;
		biggestChildHeight = 0;
		for (int index = 0; index < getItemViewCount(); index++) {
			View child = getChildAt(index);

			if (biggestChildHeight < child.getMeasuredHeight()) {
				biggestChildHeight = child.getMeasuredHeight();
			}

			if (biggestChildWidth < child.getMeasuredWidth()) {
				biggestChildWidth = child.getMeasuredWidth();
			}
		}
	}

	/**
	 * 返回所有页面子view总和
	 * 
	 * @return
	 */
	private int getItemViewCount() {
		return views.size();
	}

	public void setAdapter(DragDropGridAdapter adapter) {
		this.adapter = adapter;
		addChildViews();
	}

	private void addChildViews() {
		for (int item = 0; item < adapter.itemCount(); item++) {
			View v = adapter.view(item);
			v.setTag(adapter.getItemAt(item));
			removeView(v);
			addView(v);
			views.add(v);
		}
	}

	/**
	 * 测量内部view的宽高
	 * 
	 * @param widthSize
	 * @param heightSize
	 */
	private void adaptChildrenMeasuresToViewSize(int widthSize, int heightSize) {
		if (adapter.columnCount() != -1 && adapter.rowCount() != -1) {
			// 行列数有指定
			// 平均布局
			int desiredGridItemWidth = widthSize / adapter.columnCount();
			int desiredGridItemHeight = heightSize / adapter.rowCount();
			measureChildren(MeasureSpec.makeMeasureSpec(desiredGridItemWidth, MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(desiredGridItemHeight, MeasureSpec.AT_MOST));
		} else {
			// 行列数无指定
			measureChildren(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
		}
	}

	private int acknowledgeWidthSize(int widthMode, int widthSize, Display display) {
		if (widthMode == MeasureSpec.UNSPECIFIED) {
			widthSize = display.getWidth();
		}
		return widthSize;
	}

	private int acknowledgeHeightSize(int heightMode, int heightSize, Display display) {
		if (heightMode == MeasureSpec.UNSPECIFIED) {
			heightSize = display.getHeight();
		}
		return heightSize;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// 一个页面的宽度
		int pageWidth = (r + l);
		layoutPage(pageWidth);
	}

	private void layoutPage(int pageWidth) {
		int col = 0;
		int row = 0;
		for (int childIndex = 0; childIndex < adapter.itemCount(); childIndex++) {
			layoutAChild(pageWidth, col, row, childIndex);
			col++;
			if (col == computedColumnCount) {
				col = 0;
				row++;
			}
		}
	}

	/**
	 * 布局单个view
	 * 
	 * @param pageWidth
	 * @param page
	 * @param col
	 * @param row
	 * @param childIndex
	 */
	private void layoutAChild(int pageWidth, int col, int row, int childIndex) {
		int position = positionOfItem(childIndex);
		View child = views.get(position);
		int left = 0;
		int top = 0;
		left = (col * columnWidthSize) + ((columnWidthSize - child.getMeasuredWidth()) / 2);
		top = (row * rowHeightSize) + ((rowHeightSize - child.getMeasuredHeight()) / 2);
		child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());
	}

	/**
	 * 确认当前 长按的view在当前页面的index
	 * 
	 * @param pageIndex
	 * @param childIndex
	 * @return
	 */
	private int positionOfItem(int childIndex) {
		int currentGlobalIndex = 0;
		int itemCount = adapter.itemCount();
		for (int currentItemIndex = 0; currentItemIndex < itemCount; currentItemIndex++) {
			if (childIndex == currentItemIndex) {
				return currentGlobalIndex;
			}
			currentGlobalIndex++;
		}
		return -1;
	}
}
