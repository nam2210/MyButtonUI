package com.hnam.uibutton;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.StateSet;
import android.widget.Button;

/**
 * Created by hnam on 12/21/2016.
 */

public class MyButton extends Button{

    private static final int STROKE = 0;
    public static final int SOLID = 1;
    private final int DEFAULT_BG = R.drawable.bg_default;

    private int mType = SOLID;
    private int mStrokeColor;
    private int mSolidColor;
    private int mStrokeWidth;
    private float mRadiusCorner;
    private Resources mResources;

    public MyButton(Context context) {
        super(context);
        initDefaultValue();
        renderUi();
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefaultValue();
        initAttrs(attrs);
        renderUi();
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefaultValue();
        initAttrs(attrs);
        renderUi();
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null){
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyButton);
            mType = a.getInt(R.styleable.MyButton_type, SOLID);
            mStrokeColor = a.getColor(R.styleable.MyButton_strokeColor,
                    ContextCompat.getColor(getContext(), android.R.color.black));
            mSolidColor = a.getColor(R.styleable.MyButton_solidColor,
                    ContextCompat.getColor(getContext(), android.R.color.black));
            mStrokeWidth = (int) a.getDimension(R.styleable.MyButton_strokeWidth,
                    getResources().getDimension(R.dimen.default_stoke_width));
            mRadiusCorner = a.getDimension(R.styleable.MyButton_radiusCorner,
                    getResources().getDimension(R.dimen.default_radius_corner));
            a.recycle();
        }
    }

    private void initDefaultValue(){
        mResources = getResources();
        mType = SOLID;
        mStrokeColor = ContextCompat.getColor(getContext(), android.R.color.black);
        mSolidColor = ContextCompat.getColor(getContext(), android.R.color.black);
        mStrokeWidth = (int) getResources().getDimension(R.dimen.default_stoke_width);
        mRadiusCorner = getResources().getDimension(R.dimen.default_radius_corner);
    }

    private void renderUi(){
        switch (mType) {
            case STROKE: {
                renderStrokeUi();
                break;
            }
            case SOLID:
            default: {
                renderSolidUi();
                break;
            }
        }
    }

    private void renderStrokeUi(){
        //if stroke
        Drawable strokeBg = mResources.getDrawable(R.drawable.bg_default).mutate();

        ((GradientDrawable) strokeBg).setColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        ((GradientDrawable) strokeBg).setStroke(mStrokeWidth, mStrokeColor);
        ((GradientDrawable) strokeBg).setCornerRadius(mRadiusCorner);

        //set text color is same as stroke color
        setTextColor(mStrokeColor);

        //create drawable when user click view
        GradientDrawable maskDrawable = (GradientDrawable) mResources.getDrawable(R.drawable.bg_default).mutate();
        maskDrawable.setStroke(mStrokeWidth, mStrokeColor);
        int maskColor = Color.argb(50, Color.red(mStrokeColor), Color.green(mStrokeColor), Color.blue(mStrokeColor));
        maskDrawable.setColor(maskColor);
        maskDrawable.setCornerRadius(mRadiusCorner);

        //create StateListDrawable
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, maskDrawable);
        stateListDrawable.addState(StateSet.WILD_CARD, strokeBg);

        //set background
        setBackground(stateListDrawable);
    }

    private void renderSolidUi(){
        Drawable solidBg = mResources.getDrawable(R.drawable.bg_default).mutate();
        ((GradientDrawable) solidBg).setColor(mSolidColor);
        ((GradientDrawable) solidBg).setCornerRadius(mRadiusCorner);

        //set text color is white
        setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));


        //create drawable when user click view
        GradientDrawable maskDrawable = (GradientDrawable) mResources.getDrawable(R.drawable.bg_default).mutate();
        int maskColor = Color.argb(50, Color.red(mSolidColor), Color.green(mSolidColor), Color.blue(mSolidColor));
        maskDrawable.setColor(maskColor);
        maskDrawable.setCornerRadius(mRadiusCorner);

        //create StateListDrawable
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, maskDrawable);
        stateListDrawable.addState(StateSet.WILD_CARD, solidBg);

        //set background
        setBackground(stateListDrawable);
    }
}
