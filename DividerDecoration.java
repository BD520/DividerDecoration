package com.stark.widget;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by 陈剑虹 on 16-8-12.
 * RecyclerView 列表项分割线装饰
 * 可设置是否绘制第一项顶部分割线及高度
 * 可设置是否绘制最后一项底部分割线
 */
public class DividerDecoration extends RecyclerView.ItemDecoration {
    private boolean isDrawLastDivider = false;//是否绘制最后一条分割线
    private boolean isDrawFirstDividerTop = false;//是否绘制第一个条目的顶部分割线
    private int mMarginLeft = 0;//左边距
    private int mMarginRight = 0;//右边距
    private int mHeight = 0;//默认分割线高度
    private int mTopHeight = 0;//第一条顶部分割线高度
    private ColorDrawable mColorDrawable;

    /**
     * 初始化分割线装饰
     *
     * @param color  分割线颜色,The color to draw.
     * @param height 分割线高度,The divider Height.
     */
    public DividerDecoration(int color, int height) {
        this.mColorDrawable = new ColorDrawable(color);
        this.mHeight = height;
    }

    public DividerDecoration(int color, int leftMargin, int rightMargin, int height) {
        this.mColorDrawable = new ColorDrawable(color);
        this.mMarginLeft = leftMargin;
        this.mMarginRight = rightMargin;
        this.mHeight = height;
    }

    /**
     * 设置是否绘制第一条顶部分割线,不包含Header,Header配合 EasyRecyclerView使用.
     *
     * @param isDrawFirstDividerTop 是否绘制第一条顶部分割线
     */
    public void setDrawFirstDividerTop(boolean isDrawFirstDividerTop) {
        this.isDrawFirstDividerTop = isDrawFirstDividerTop;
    }

    /**
     * 设置是否绘制第一条顶部分割线,不包含Header,Header配合 EasyRecyclerView使用.
     *
     * @param isDrawFirstDividerTop 是否绘制第一条顶部分割线
     * @param topHeight             顶部分割线高度
     */
    public void setDrawFirstDividerTop(boolean isDrawFirstDividerTop, int topHeight) {
        this.isDrawFirstDividerTop = isDrawFirstDividerTop;
        this.mTopHeight = topHeight;
    }

    /**
     * 设置是否绘制最后一条分割线
     * @param isDrawLastDivider
     */
    public void setDrawLastDivider(boolean isDrawLastDivider) {
        this.isDrawLastDivider = isDrawLastDivider;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int dataCount;
        
        dataCount = parent.getAdapter().getItemCount();
        

        int start, end;
        start = parent.getPaddingLeft() + mMarginLeft;
        end = parent.getWidth() - parent.getPaddingRight() - mMarginRight;
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int top,bottom;
            int position = parent.getChildAdapterPosition(child);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            if (position == 0 && isDrawFirstDividerTop) {
                top = params.topMargin;
                if (mTopHeight != 0) {
                    bottom = top + mTopHeight;
                } else {
                    bottom = top + mHeight;
                }

                mColorDrawable.setBounds(start, top, end, bottom);
                mColorDrawable.draw(c);
            }
            top = child.getBottom() + params.bottomMargin;
            bottom = top + mHeight;
            mColorDrawable.setBounds(start, top, end, bottom);
            mColorDrawable.draw(c);

        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        //如果是最后一条并且不绘制分割线就跳过
        if (!((position == parent.getAdapter().getItemCount() - 1) && !isDrawLastDivider))
            outRect.bottom = this.mHeight;
        if (isDrawFirstDividerTop && position == 0) {//如果需要绘制第一个条目顶部分割线并且是第一个条目
            if (mTopHeight != 0)
                outRect.top = this.mTopHeight;
            else
                outRect.top = this.mHeight;
        }

    }
}
