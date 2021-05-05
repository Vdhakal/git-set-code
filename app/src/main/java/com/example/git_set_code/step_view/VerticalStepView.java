package com.example.git_set_code.step_view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.git_set_code.R;

import java.util.List;

/**
 * ：04/11/2021 11:48
 * <p/>
 */
public class VerticalStepView extends LinearLayout implements VerticalStepViewIndicator.OnDrawIndicatorListener {
    private RelativeLayout mTextContainer;
    private VerticalStepViewIndicator mStepsViewIndicator;
    private List<String> mTexts;
    private int mComplectingPosition;
    private int mUnComplectedTextColor = ContextCompat.getColor(getContext(), R.color.black);
    private int mComplectedTextColor = ContextCompat.getColor(getContext(), android.R.color.white);

    private int mTextSize = 14;//default textSize
    private TextView mTextView;


    public VerticalStepView(Context context) {
        this(context, null);
    }

    public VerticalStepView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalStepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_vertical_stepsview, this);
        mStepsViewIndicator = (VerticalStepViewIndicator) rootView.findViewById(R.id.steps_indicator);
        mStepsViewIndicator.setOnDrawListener(this);
        mTextContainer = (RelativeLayout) rootView.findViewById(R.id.rl_text_container);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * @param texts
     * @return
     */
    public VerticalStepView setStepViewTexts(List<String> texts) {
        mTexts = texts;
        if (texts != null) {
            mStepsViewIndicator.setStepNum(mTexts.size());
        } else {
            mStepsViewIndicator.setStepNum(0);
        }
        return this;
    }

    /**
     * position
     *
     * @param complectingPosition
     * @return
     */
    public VerticalStepView setStepsViewIndicatorComplectingPosition(int complectingPosition) {
        mComplectingPosition = complectingPosition;
        mStepsViewIndicator.setComplectingPosition(complectingPosition);
        return this;
    }

    /**
     * @param unComplectedTextColor
     * @return
     */
    public VerticalStepView setStepViewUnComplectedTextColor(int unComplectedTextColor) {
        mUnComplectedTextColor = unComplectedTextColor;
        return this;
    }

    /**
     * @param complectedTextColor
     * @return
     */
    public VerticalStepView setStepViewComplectedTextColor(int complectedTextColor) {
        this.mComplectedTextColor = complectedTextColor;
        return this;
    }

    /**
     * @param unCompletedLineColor
     * @return
     */
    public VerticalStepView setStepsViewIndicatorUnCompletedLineColor(int unCompletedLineColor) {
        mStepsViewIndicator.setUnCompletedLineColor(unCompletedLineColor);
        return this;
    }

    /**
     * StepsViewIndicator
     *
     * @param completedLineColor
     * @return
     */
    public VerticalStepView setStepsViewIndicatorCompletedLineColor(int completedLineColor) {
        mStepsViewIndicator.setCompletedLineColor(completedLineColor);
        return this;
    }

    /**
     * StepsViewIndicator
     *
     * @param defaultIcon
     */
    public VerticalStepView setStepsViewIndicatorDefaultIcon(Drawable defaultIcon) {
        mStepsViewIndicator.setDefaultIcon(defaultIcon);
        return this;
    }

    /**
     * StepsViewIndicator
     *
     * @param completeIcon
     */
    public VerticalStepView setStepsViewIndicatorCompleteIcon(Drawable completeIcon) {
        mStepsViewIndicator.setCompleteIcon(completeIcon);
        return this;
    }

    /**
     * @param attentionIcon
     */
    public VerticalStepView setStepsViewIndicatorAttentionIcon(Drawable attentionIcon) {
        mStepsViewIndicator.setAttentionIcon(attentionIcon);
        return this;
    }

    /**
     * is reverse draw
     *
     * @param isReverSe default is true
     * @return
     */
    public VerticalStepView reverseDraw(boolean isReverSe) {
        this.mStepsViewIndicator.reverseDraw(isReverSe);
        return this;
    }

    /**
     * set linePadding  proportion
     *
     * @param linePaddingProportion
     * @return
     */
    public VerticalStepView setLinePaddingProportion(float linePaddingProportion) {
        this.mStepsViewIndicator.setIndicatorLinePaddingProportion(linePaddingProportion);
        return this;
    }


    /**
     * set textSize
     *
     * @param textSize
     * @return
     */
    public VerticalStepView setTextSize(int textSize) {
        if (textSize > 0) {
            mTextSize = textSize;
        }
        return this;
    }

    /**
     * set textSize
     *
     * @param typeface
     * @return
     */
    public VerticalStepView setFont(Typeface typeface) {
        mTextView.setTypeface(typeface);
        return this;
    }

    @Override
    public void ondrawIndicator() {
        if (mTextContainer != null) {
            mTextContainer.removeAllViews();//clear ViewGroup
            List<Float> complectedXPosition = mStepsViewIndicator.getCircleCenterPointPositionList();
            if (mTexts != null && complectedXPosition != null && complectedXPosition.size() > 0) {
                for (int i = 0; i < mTexts.size(); i++) {
                    mTextView = new TextView(getContext());
                    mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
                    mTextView.setText(mTexts.get(i));
                    mTextView.setY(complectedXPosition.get(i) - mStepsViewIndicator.getCircleRadius() / 2);
                    mTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.quick_regular);
                    mTextView.setTypeface(typeface, Typeface.BOLD);
                    if (i <= mComplectingPosition - 1) {
                        mTextView.setTextColor(mComplectedTextColor);
                    } else {
                        mTextView.setTextColor(mUnComplectedTextColor);
                    }

                    mTextContainer.addView(mTextView);
                }
            }
        }
    }
}

