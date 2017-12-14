package com.zhuye.hougong.weidgt;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class DividerItemDecoration extends ItemDecoration {
	
	private int mOrientation = LinearLayoutManager.VERTICAL;
	private Drawable mDivider;
	private int[] attrs= new int[]{
			android.R.attr.listDivider
	};
	
	public DividerItemDecoration(Context context, int orientation) {
		TypedArray typedArray = context.obtainStyledAttributes(attrs);
		mDivider = typedArray.getDrawable(0);
		typedArray.recycle();
		setOrientation(orientation);
	}

	public void setOrientation(int orientation){
		if(orientation!=LinearLayoutManager.HORIZONTAL&&orientation!=LinearLayoutManager.VERTICAL){
			throw new IllegalArgumentException("����,����ma?��ˮƽ�����Ե�ö������");
		}
		this.mOrientation = orientation;
	}
	
	@Override
	public void onDraw(Canvas c, RecyclerView parent, State state) {
		//2������������Ʒ����� RecyclerView��ٵ��û��Ʒ���,��Ҫ���Լ�ȥ������Ŀ�ļ����
		if(mOrientation == LinearLayoutManager.VERTICAL){//��ֱ
			drawVertical(c,parent);
		}else{//ˮƽ
			drawHorizontal(c,parent);
		}
		
		super.onDraw(c, parent, state);
	}
	
	private void drawHorizontal(Canvas c, RecyclerView parent) {
		int top = parent.getPaddingTop();
		int bottom = parent.getHeight() - parent.getPaddingBottom();
		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount ; i++) {
			View child = parent.getChildAt(i);
			
			LayoutParams params = (LayoutParams) child.getLayoutParams();
			int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
			int right = left + mDivider.getIntrinsicHeight();
			mDivider.setBounds(left, top , right, bottom);
			mDivider.draw(c);
		}
	}

	private void drawVertical(Canvas c, RecyclerView parent) {
		// ��ˮƽ��
		int left = parent.getPaddingLeft();
		int right = parent.getWidth() - parent.getPaddingRight();
		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount ; i++) {
			View child = parent.getChildAt(i);
			
			LayoutParams params = (LayoutParams) child.getLayoutParams();
			int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
			int bottom = top + mDivider.getIntrinsicHeight();
			mDivider.setBounds(left, top , right, bottom);
			mDivider.draw(c);
		}
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
			State state) {
		//1.���ô˷��������Ȼ��Ȼ�ȡ��Ŀ֮��ļ�϶���---Rect��������
		// �����Ŀ��ƫ����(���е���Ŀ���ص���һ�θ÷���)
		if(mOrientation == LinearLayoutManager.VERTICAL){//��ֱ
			outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
		}else{//ˮƽ
			outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0 );
		}
		
	}

}
