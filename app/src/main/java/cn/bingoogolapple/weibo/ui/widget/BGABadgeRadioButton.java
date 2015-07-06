package cn.bingoogolapple.weibo.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

import java.util.Random;

import cn.bingoogolapple.weibo.util.UIUtils;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/6 下午3:04
 * 描述:
 */
public class BGABadgeRadioButton extends RadioButton {
    private Paint mBadgePaint;
    /**
     * 标记背景色
     */
    private int mBadgeBgColor;
    /**
     * 标记文字的颜色
     */
    private int mBadgeTextColor;
    /**
     * 标记文字字体大小
     */
    private int mBadgeTextSize;
    /**
     * 标记背景与RadioButton边距间距离
     */
    private int mBadgeMargin;
    /***
     * 标记文字边缘与标记背景边缘间的距离
     */
    private int mBadgePadding;
    /**
     * 标记文字
     */
    private String mBadgeText;
    /**
     * 标记文字所占区域大小
     */
    private Rect mBadgeTextRect;

    public BGABadgeRadioButton(Context context) {
        this(context, null);
    }

    public BGABadgeRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.radioButtonStyle);
    }

    public BGABadgeRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefaultAttrs(context);
        initCustomAttrs(context, attrs);
    }

    private void initDefaultAttrs(Context context) {
        mBadgeTextRect = new Rect();
        mBadgeBgColor = Color.RED;
        mBadgeTextColor = Color.WHITE;
        mBadgeTextSize = UIUtils.dp2px(context, 10);

        mBadgePaint = new Paint();
        mBadgePaint.setAntiAlias(true);
        mBadgePaint.setStyle(Paint.Style.FILL);
        mBadgePaint.setStrokeWidth(UIUtils.dp2px(context, 1));
        mBadgePaint.setTextSize(mBadgeTextSize);
        // 设置mBadgeText居中，保证mBadgeText长度为1时也能居中
        mBadgePaint.setTextAlign(Paint.Align.CENTER);

        mBadgePadding = UIUtils.dp2px(getContext(), 5);
        mBadgeMargin = UIUtils.dp2px(getContext(), 3);

        mBadgeText = "" + new Random().nextInt(20);
    }

    private void initCustomAttrs(Context context, AttributeSet attrs) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!TextUtils.isEmpty(mBadgeText)) {
            // 获取文字宽所占宽高
            mBadgePaint.getTextBounds(mBadgeText, 0, mBadgeText.length(), mBadgeTextRect);
            Log.i("bingo", "width = " + mBadgeTextRect.width() + " height = " + mBadgeTextRect.height());
            // 计算标记背景的宽高
            float badgeHeight = mBadgeTextRect.height() + mBadgePadding * 2;
            float badgeWidth;
            // 当mBadgeText的长度为1时，计算出来的高度会比宽度大，此时设置宽度等于高度
            if (mBadgeText.length() == 1) {
                badgeWidth = badgeHeight;
            } else {
                badgeWidth = mBadgeTextRect.width() + mBadgePadding * 2;
            }
            // 计算标记背景上下左右的值
            float badgeTop = mBadgeMargin;
            float badgeBottom = badgeTop + badgeHeight;
            float badgeRight = getWidth() - mBadgeMargin;
            float badgeLeft = badgeRight - badgeWidth;
            // 设置标记背景色
            mBadgePaint.setColor(mBadgeBgColor);
            // 绘制标记背景
            canvas.drawOval(new RectF(badgeLeft, badgeTop, badgeRight, badgeBottom), mBadgePaint);

            // 设置标记文字颜色
            mBadgePaint.setColor(mBadgeTextColor);
            // initDefaultAttrs方法中设置了mBadgeText居中，此处的x为标记背景的中心点y
            float x = badgeLeft + badgeWidth / 2;
            // 注意：绘制文字时的y是指文字底部，而不是文字的中间
            float y = badgeBottom - mBadgePadding;
            // 绘制标记文字
            canvas.drawText(mBadgeText, x, y, mBadgePaint);
        }
    }
}