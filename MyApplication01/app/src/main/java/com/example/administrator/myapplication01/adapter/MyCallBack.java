package com.example.administrator.myapplication01.adapter;

/**
 * annotation:ListView业务回调接口
 */
public interface MyCallBack {
	/*
	 * 参数1:Adapter的ViewHoldr
	 * 参数2:数据在集合中的位置
	 */
	public void refresh(BaseViewHolder viewHolder, int position);

}
