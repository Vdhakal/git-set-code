package com.example.git_set_code.step_view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.git_set_code.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 04/11/2021 11:48
 * <p/>
 */
public class VerticalStepViewIndicator extends View {
    private final String TAG_NAME = this.getClass().getSimpleName();

    // definition default height
    private int defaultStepIndicatorNum = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());

    private float mCompletedLineHeight;//     definition completed line height
    private float mCircleRadius;//  definition circle radius

    private Drawable mCompleteIcon;//    definition default completed icon
    private Drawable mAttentionIcon;//     definition default underway icon
    private Drawable mDefaultIcon;//  definition default unCompleted icon
    private float mCenterX;//
    private float mLeftY;
    private float mRightY;

    private int mStepNum = 0;//    there are currently few step
    private float mLinePadding;//  definition the spacing between the two circles

    private List<Float> mCircleCenterPointPositionList;// definition all of circles center point list
    private Paint mUnCompletedPaint;//Paint  definition mUnCompletedPaint
    private Paint mCompletedPaint;//paint      definition mCompletedPaint
    private int mUnCompletedLineColor = ContextCompat.getColor(getContext(), R.color.black);//  definition mUnCompletedLineColor
    private int mCompletedLineColor = Color.WHITE;//      definition mCompletedLineColor
    private PathEffect mEffects;

    private int mComplectingPosition;//position   underway position
    private Path mPath;

    private OnDrawIndicatorListener mOnDrawListener;
    private Rect mRect;
    private int mHeight;//    this view dynamic height
    private boolean mIsReverseDraw;//is reverse draw this view;


    /**
     * @param onDrawListener
     */
    public void setOnDrawListener(OnDrawIndicatorListener onDrawListener) {
        mOnDrawListener = onDrawListener;
    }

    /**
     * get circle radius
     *
     * @return
     */
    public float getCircleRadius() {
        return mCircleRadius;
    }


    public VerticalStepViewIndicator(Context context) {
        this(context, null);
    }

    public VerticalStepViewIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalStepViewIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * init
     */
    private void init() {
        mPath = new Path();
        mEffects = new DashPathEffect(new float[]{8, 8, 8, 8}, 1);

        mCircleCenterPointPositionList = new ArrayList<>();

        mUnCompletedPaint = new Paint();
        mCompletedPaint = new Paint();
        mUnCompletedPaint.setAntiAlias(true);
        mUnCompletedPaint.setColor(mUnCompletedLineColor);
        mUnCompletedPaint.setStyle(Paint.Style.STROKE);
        mUnCompletedPaint.setStrokeWidth(2);

        mCompletedPaint.setAntiAlias(true);
        mCompletedPaint.setColor(mCompletedLineColor);
        mCompletedPaint.setStyle(Paint.Style.STROKE);
        mCompletedPaint.setStrokeWidth(2);

        mUnCompletedPaint.setPathEffect(mEffects);
        mCompletedPaint.setStyle(Paint.Style.FILL);

        //set mCompletedLineHeight
        mCompletedLineHeight = 0.05f * defaultStepIndicatorNum;
        //  set mCircleRadius
        mCircleRadius = 0.28f * defaultStepIndicatorNum;
        //    set mLinePadding
        mLinePadding = 0.85f * defaultStepIndicatorNum;

        mCompleteIcon = ContextCompat.getDrawable(getContext(), R.drawable.complted);//已经完成的icon
        mAttentionIcon = ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_check_circle_24);//正在进行的icon
        mDefaultIcon = ContextCompat.getDrawable(getContext(), R.drawable.default_icon);//未完成的icon

        mIsReverseDraw = true;//default draw
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG_NAME, "onMeasure");
        int width = defaultStepIndicatorNum;
        mHeight = 0;
        if (mStepNum > 0) {
            //dynamic measure VerticalStepViewIndicator height
            mHeight = (int) (getPaddingTop() + getPaddingBottom() + mCircleRadius * 2 * mStepNum + (mStepNum - 1) * mLinePadding);
        }
        if (MeasureSpec.UNSPECIFIED != MeasureSpec.getMode(widthMeasureSpec)) {
            width = Math.min(width, MeasureSpec.getSize(widthMeasureSpec));
        }
        setMeasuredDimension(width, mHeight);

    }

    /**
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG_NAME, "onSizeChanged");
        mCenterX = getWidth() / 2;
        mLeftY = mCenterX - (mCompletedLineHeight / 2);
        mRightY = mCenterX + (mCompletedLineHeight / 2);

        for (int i = 0; i < mStepNum; i++) {
            //reverse draw VerticalStepViewIndicator
            if (mIsReverseDraw) {
                mCircleCenterPointPositionList.add(mHeight - (mCircleRadius + i * mCircleRadius * 2 + i * mLinePadding));
            } else {
                mCircleCenterPointPositionList.add(mCircleRadius + i * mCircleRadius * 2 + i * mLinePadding);
            }
        }
        /**
         * set listener
         */
        if (mOnDrawListener != null) {
            mOnDrawListener.ondrawIndicator();
        }
    }

    /**
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG_NAME, "onDraw");
        if (mOnDrawListener != null) {
            mOnDrawListener.ondrawIndicator();
        }
        mUnCompletedPaint.setColor(mUnCompletedLineColor);
        mCompletedPaint.setColor(mCompletedLineColor);

        //----------------------------draw line-----------------------------------------------
        for (int i = 0; i < mCircleCenterPointPositionList.size() - 1; i++) {
            //ComplectedXPosition
            final float preComplectedXPosition = mCircleCenterPointPositionList.get(i);
            //ComplectedXPosition
            final float afterComplectedXPosition = mCircleCenterPointPositionList.get(i + 1);

            if (i < mComplectingPosition) {
                if (mIsReverseDraw) {
                    canvas.drawRect(mLeftY, afterComplectedXPosition + mCircleRadius - 10, mRightY, preComplectedXPosition - mCircleRadius + 10, mCompletedPaint);
                } else {
                    canvas.drawRect(mLeftY, preComplectedXPosition + mCircleRadius - 10, mRightY, afterComplectedXPosition - mCircleRadius + 10, mCompletedPaint);
                }
            } else {
                if (mIsReverseDraw) {
                    mPath.moveTo(mCenterX, afterComplectedXPosition + mCircleRadius);
                    mPath.lineTo(mCenterX, preComplectedXPosition - mCircleRadius);
                    canvas.drawPath(mPath, mUnCompletedPaint);
                } else {
                    mPath.moveTo(mCenterX, preComplectedXPosition + mCircleRadius);
                    mPath.lineTo(mCenterX, afterComplectedXPosition - mCircleRadius);
                    canvas.drawPath(mPath, mUnCompletedPaint);
                }

            }
        }
        //---------------------------draw line-----------------------------------------------

        //--------------------------draw icon-----------------------------------------------
        for (int i = 0; i < mCircleCenterPointPositionList.size(); i++) {
            final float currentComplectedXPosition = mCircleCenterPointPositionList.get(i);
            mRect = new Rect((int) (mCenterX - mCircleRadius), (int) (currentComplectedXPosition - mCircleRadius), (int) (mCenterX + mCircleRadius), (int) (currentComplectedXPosition + mCircleRadius));

            if (i < mComplectingPosition) {
                mCompleteIcon.setBounds(mRect);
                mCompleteIcon.draw(canvas);
            } else if (i == mComplectingPosition && mCircleCenterPointPositionList.size() != 1 && i != mCircleCenterPointPositionList.size() - 1) {
                mCompletedPaint.setColor(getResources().getColor(R.color.site_pink));
                canvas.drawCircle(mCenterX, currentComplectedXPosition, mCircleRadius * 1.1f, mCompletedPaint);
            } else if (i == mCircleCenterPointPositionList.size() - 1) {
                mCompletedPaint.setColor(Color.WHITE);
                canvas.drawCircle(mCenterX, currentComplectedXPosition, mCircleRadius * 1.05f, mCompletedPaint);
                mAttentionIcon.setBounds(mRect);
                mAttentionIcon.draw(canvas);
            } else {
                mDefaultIcon.setBounds(mRect);
                mDefaultIcon.draw(canvas);
            }

        }
        //----------------------------draw icon-----------------------------------------------
    }


    /**
     * @return
     */
    public List<Float> getCircleCenterPointPositionList() {
        return mCircleCenterPointPositionList;
    }

    /**
     * @param stepNum
     */
    public void setStepNum(int stepNum) {
        this.mStepNum = stepNum;
        requestLayout();
    }

    /**
     * set linePadding proportion
     *
     * @param linePaddingProportion
     */
    public void setIndicatorLinePaddingProportion(float linePaddingProportion) {
        this.mLinePadding = linePaddingProportion * defaultStepIndicatorNum;
    }

    /**
     * @param complectingPosition
     */
    public void setComplectingPosition(int complectingPosition) {
        this.mComplectingPosition = complectingPosition;
        requestLayout();
    }

    /**
     * @param unCompletedLineColor
     */
    public void setUnCompletedLineColor(int unCompletedLineColor) {
        this.mUnCompletedLineColor = unCompletedLineColor;
    }

    /**
     * @param completedLineColor
     */
    public void setCompletedLineColor(int completedLineColor) {
        this.mCompletedLineColor = completedLineColor;
    }

    /**
     * is reverse draw
     */
    public void reverseDraw(boolean isReverseDraw) {
        this.mIsReverseDraw = isReverseDraw;
        invalidate();
    }

    /**
     * @param defaultIcon
     */
    public void setDefaultIcon(Drawable defaultIcon) {
        this.mDefaultIcon = defaultIcon;
    }

    /**
     * @param completeIcon
     */
    public void setCompleteIcon(Drawable completeIcon) {
        this.mCompleteIcon = completeIcon;
    }

    /**
     * @param attentionIcon
     */
    public void setAttentionIcon(Drawable attentionIcon) {
        this.mAttentionIcon = attentionIcon;
    }

    /**
     *
     */
    public interface OnDrawIndicatorListener {
        void ondrawIndicator();
    }
}
