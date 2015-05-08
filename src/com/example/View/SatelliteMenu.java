package com.example.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.marshmallow.R;

public class SatelliteMenu extends ViewGroup implements OnClickListener {
	private static final int POS_LEFT_TOP = 0;
	private static final int POS_LEFT_BOTTOM = 1;
	private static final int POS_RIGHT_TOP = 2;
	private static final int POS_RIGHT_BOTTOM = 3;
	
	private View mCButton;

	private OnMenuItemClickListener mMenuItemClickListener;
	private Position mPosition = Position.LEFT_TOP;
	private int mRadius;
	private Status mCurrentStatus = Status.CLOSE;

	public enum Status {
		OPEN, CLOSE
	};

	public enum Position {
		LEFT_TOP, RIGHT_TOP, LEFT_BOTTOM, RIGHT_BOTTOM

	}

	public SatelliteMenu(Context context) {
		super(context, null);
		// TODO Auto-generated constructor stub
	}

	public SatelliteMenu(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public SatelliteMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				100, getResources().getDisplayMetrics());
		TypedArray type = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.ArcMenu, defStyle, 0);
		int pos = type.getInt(R.styleable.ArcMenu_position, POS_LEFT_TOP);
		switch (pos) {
		case POS_LEFT_TOP:
			this.mPosition = Position.LEFT_TOP;
			break;
		case POS_LEFT_BOTTOM:
			this.mPosition = Position.LEFT_BOTTOM;
			break;
		case POS_RIGHT_TOP:
			this.mPosition = Position.RIGHT_TOP;
			break;
		case POS_RIGHT_BOTTOM:
			this.mPosition = Position.RIGHT_BOTTOM;
			break;
		}
		mRadius = type.getDimensionPixelSize(R.styleable.ArcMenu_radius, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				100, getResources().getDisplayMetrics()));
		// TODO Auto-generated constructor stub
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	};

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		if (changed)
		{
			layoutCButton();

			int count = getChildCount();

			for (int i = 0; i < count - 1; i++)
			{
				View child = getChildAt(i + 1);

				child.setVisibility(View.GONE);

				int cl = (int) (mRadius * Math.sin(Math.PI / 2 / (count - 2)
						* i));
				int ct = (int) (mRadius * Math.cos(Math.PI / 2 / (count - 2)
						* i));

				int cWidth = child.getMeasuredWidth();
				int cHeight = child.getMeasuredHeight();

				// 如果菜单位置在底部 左下，右下
				if (mPosition == Position.LEFT_BOTTOM
						|| mPosition == Position.RIGHT_BOTTOM)
				{
					ct = getMeasuredHeight() - cHeight - ct;
				}
				// 右上，右下
				if (mPosition == Position.RIGHT_TOP
						|| mPosition == Position.RIGHT_BOTTOM)
				{
					cl = getMeasuredWidth() - cWidth - cl;
				}
				child.layout(cl, ct, cl + cWidth, ct + cHeight);

			}

		}

	}

	/**
	 * 定位主菜单按钮
	 */
	private void layoutCButton()
	{
		mCButton = getChildAt(0);
		mCButton.setOnClickListener(this);

		int l = 0;
		int t = 0;

		int width = mCButton.getMeasuredWidth();
		int height = mCButton.getMeasuredHeight();

		switch (mPosition)
		{
		case LEFT_TOP:
			l = 0;
			t = 0;
			break;
		case LEFT_BOTTOM:
			l = 0;
			t = getMeasuredHeight() - height;
			break;
		case RIGHT_TOP:
			l = getMeasuredWidth() - width;
			t = 0;
			break;
		case RIGHT_BOTTOM:
			l = getMeasuredWidth() - width;
			t = getMeasuredHeight() - height;
			break;
		}
		mCButton.layout(l, t, l + width, t + width);
	}

	public interface OnMenuItemClickListener {
		void onClick(View view, int pos);
	}

	public void setOnMenuItemClickListener(
			OnMenuItemClickListener mMenuItemClickListener) {
		this.mMenuItemClickListener = mMenuItemClickListener;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
