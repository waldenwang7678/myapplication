package com.example.administrator.myapplication01.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * 
 * annotation:ListView的Adapter的基类
 * 
 */
public abstract class BaseListViewAdapter<T> extends BaseAdapter {

	private List<T> mDatas;
	private int mLayoutID;
	private MyCallBack mCallBack;

	public BaseListViewAdapter(List<T> datas, int layoutID, MyCallBack callBack)
	{
		this.mDatas = datas; 
		this.mLayoutID = layoutID;
		this.mCallBack = callBack;
	}
	
	@Override
	public int getCount() {
		
		return mDatas.size();
	}

	@Override
	public T getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//获取到ViewHolder类的对象
		BaseViewHolder viewHolder = BaseViewHolder.getInstance(convertView, parent.getContext(), mLayoutID);
		
		//抽取业务方法
		setShowData(viewHolder,position);
		
		return viewHolder.getRootView();
	}
	
	
	
	
	//获取条目中指定控件，并显示指定数据
	public  void setShowData(BaseViewHolder viewHolder,int position)
	{
		mCallBack.refresh(viewHolder, position);
	}
	
}
