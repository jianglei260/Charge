package com.wuxian.charge.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Button;

import com.wuxian.charge.R;

/**
 * Created by jianglei on 2017/3/6.
 */

public class CustomButton extends AppCompatButton {
    public CustomButton(Context context) {
        this(context, null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.buttonStyle);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomButton, defStyleAttr, 0);
        int drawableHeight = ta.getDimensionPixelSize(R.styleable.CustomButton_drawable_height, 0);
        int drawableWidth = ta.getDimensionPixelSize(R.styleable.CustomButton_drawable_width, 0);
        Drawable drawables[] = getCompoundDrawables();
        for (Drawable drawable : drawables) {
            if (drawable != null && drawableHeight > 0 && drawableWidth > 0)
                drawable.setBounds(0, 0, drawableWidth, drawableHeight);
        }
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

}
