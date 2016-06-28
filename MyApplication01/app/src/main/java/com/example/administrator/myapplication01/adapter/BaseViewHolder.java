package com.example.administrator.myapplication01.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

public class BaseViewHolder {

	//定义SparseArray集合,该集合效率高
	private SparseArray mSparseArray = new SparseArray();
	
	//定义变量，记录条目控件
	private View mViewItem;
	
	
	private  static BaseViewHolder mViewHolder ;
	
	public BaseViewHolder(View viewItem)
	{
			this.mViewItem = viewItem;
	}
	
	//获取到ViewHolder2对象
	public static BaseViewHolder getInstance(View viewItem, Context context, int layoutID)
	{
		/**
		 * 判断缓存的条目控件是否为空
		 */
		if (viewItem == null) {
			
			//创建条目控件
			viewItem = View.inflate(context, layoutID, null);
			
			//创建ViewHolder2对象
			BaseViewHolder holder = new BaseViewHolder(viewItem);
			
			//将ViewHolder2对象与条目控件绑定
			viewItem.setTag(holder);
			
			mViewHolder = holder;
		}else
		{
			mViewHolder = (BaseViewHolder) viewItem.getTag();
		}
		return mViewHolder;
	}
	
	
	
	
	
	//获取条目中之指定的控件
	public View getView(int viewID)
	{
		/**
		 * 1.从集合中获取指定的控件
		 * 2.若集合中没有控件，从条目控件中获取，并保存到集合中
		 */
		//从集合中获取指定的控件
		View view = (View)mSparseArray.get(viewID);
		
		//若集合中没有该控件对象
		if (view == null) {
			//从条目控件中查找
			view  = mViewItem.findViewById(viewID);
			//将控件保存到集合中
			mSparseArray.put(viewID, view);
		}
		
		return view;
	}
	
	//获取条目控件
	public View getRootView()
	{
		return this.mViewItem;
	}
	
}
