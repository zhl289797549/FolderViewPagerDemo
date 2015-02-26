package com.example.folderviewpagerdemo.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.folderviewpagerdemo.R;
import com.example.folderviewpagerdemo.data.DataFactroy;
import com.example.folderviewpagerdemo.data.FolderInfo;

public class IntegrateFolder extends RelativeLayout {

	private ViewPager viewPager;
	private PagerTabStrip pagerTabStrip;

	private List<IntegrateFolderPage> folderPageList;
	private List<FolderInfo> folderInfoList;
	private Context context;

	public IntegrateFolder(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public IntegrateFolder(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public IntegrateFolder(Context context) {
		super(context);
		init(context);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initView();
	}
	
	private void initView() {
		viewPager = (ViewPager) findViewById(R.id.viewpager1);
		pagerTabStrip = (PagerTabStrip)findViewById(R.id.pagertab1);
		pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.gold));
		pagerTabStrip.setDrawFullUnderline(false);
		pagerTabStrip
				.setBackgroundColor(getResources().getColor(R.color.azure));
		pagerTabStrip.setTextSpacing(50);

		folderPageList = new ArrayList<IntegrateFolderPage>();
		LayoutInflater lf = LayoutInflater.from(context);
		for (FolderInfo info : folderInfoList) {
			IntegrateFolderPage page = (IntegrateFolderPage) lf.inflate(
					R.layout.user_folder_integrate_page, null);
			page.setFolderInfo(info);
			folderPageList.add(page);
		}

		PagerAdapter pagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {

				return arg0 == arg1;
			}

			@Override
			public int getCount() {

				return folderInfoList.size();
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(folderPageList.get(position));
			}

			@Override
			public int getItemPosition(Object object) {

				return super.getItemPosition(object);
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return folderInfoList.get(position).getTitle();
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				container.addView(folderPageList.get(position));
				return folderPageList.get(position);
			}

		};
		viewPager.setAdapter(pagerAdapter);
	}

	private void init(Context context) {
		this.context = context;
		folderInfoList = DataFactroy.getFolderInfoList();
	}
}
