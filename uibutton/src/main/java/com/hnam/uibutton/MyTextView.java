package com.hnam.uibutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
        init(null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyTextView);
            String fontName = a.getString(R.styleable.MyTextView_myFontName);

            if (fontName != null) {
                Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                setTypeface(tf);
            }
            a.recycle();
        }

    }

    @Override
    public boolean isInEditMode() {
        return super.isInEditMode();
    }
}
