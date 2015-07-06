package cn.bingoogolapple.weibo.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.util.Log;

import java.util.Random;

import cn.bingoogolapple.weibo.R;
import cn.bingoogolapple.weibo.util.UIUtils;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/6 下午3:04
 * 描述:
 */
public class BGABadgeRadioButton extends AppCompatRadioButton {
    /**
     * 当mBadgeNumber等于Integer.MIN_VALUE时则不显示数字时，只显示背景圆点。应用场景：当微信朋友圈有新动态时，微信主界面的朋友圈tab右上角只显示小红点
     */
    private static final int NOT_SHOW_BADGE_NUMBER = Integer.MIN_VALUE;
    private Paint mBadgePaint;
    /**
     * 标记背景色
     */
    private int mBadgeBgColor;
    /**
     * 标记数字的颜色
     */
    private int mBadgeTextColor;
    /**
     * 标记数字字体大小
     */
    private int mBadgeTextSize;
    /**
     * 标记背景与控件上边缘间距离
     */
    private int mBadgeTopMargin;
    /**
     * 标记背景与控件右边缘间距离
     */
    private int mBadgeRightMargin;
    /***
     * 标记数字边缘与标记背景边缘间的距离
     */
    private int mBadgePadding;
    /**
     * 标记数字
     */
    private int mBadgeNumber;
    /**
     * 标记数字所占区域大小
     */
    private Rect mBadgeNumberRect;
    /**
     * 是否显示Badge
     */
    private boolean mIsShowBadge;
    /**
     * 标记是否在控件右侧垂直居中
     */
    private boolean mIsCenterVertical;

    public BGABadgeRadioButton(Context context) {
        this(context, null);
    }

    public BGABadgeRadioButton(Context context, AttributeSet attrs) {
        // 记得传递android.R.attr.radioButtonStyle参数，否则放到RadioGroup中时点击无效
        this(context, attrs, android.R.attr.radioButtonStyle);
    }

    public BGABadgeRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefaultAttrs(context);
        initCustomAttrs(context, attrs);
        afterInitDefaultAndCustomAttrs();
    }

    private void initDefaultAttrs(Context context) {
        mBadgeNumberRect = new Rect();
        mBadgeBgColor = Color.RED;
        mBadgeTextColor = Color.WHITE;
        mBadgeTextSize = UIUtils.dp2px(context, 10);

        mBadgePaint = new Paint();
        mBadgePaint.setAntiAlias(true);
        mBadgePaint.setStyle(Paint.Style.FILL);
        // 设置mBadgeText居中，保证mBadgeText长度为1时，数字也能居中
        mBadgePaint.setTextAlign(Paint.Align.CENTER);

        mBadgePadding = UIUtils.dp2px(getContext(), 3);
        mBadgeTopMargin = UIUtils.dp2px(getContext(), 3);
        mBadgeRightMargin = UIUtils.dp2px(getContext(), 3);

        mIsCenterVertical = false;
        mIsShowBadge = false;

        mBadgeNumber = new Random().nextInt(20);
        mBadgeNumber = mBadgeNumber % 2 == 0 ? NOT_SHOW_BADGE_NUMBER : mBadgeNumber;
    }

    private void initCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BGABadgeView);
        final int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            initCustomAttr(typedArray.getIndex(i), typedArray);
        }
        typedArray.recycle();
    }

    private void initCustomAttr(int attr, TypedArray typedArray) {
        if (attr == R.styleable.BGABadgeView_badge_bgColor) {
            mBadgeBgColor = typedArray.getColor(attr, mBadgeBgColor);
        } else if (attr == R.styleable.BGABadgeView_badge_textColor) {
            mBadgeTextColor = typedArray.getColor(attr, mBadgeTextColor);
        } else if (attr == R.styleable.BGABadgeView_badge_textSize) {
            mBadgeTextSize = typedArray.getDimensionPixelSize(attr, mBadgeTextSize);
        } else if (attr == R.styleable.BGABadgeView_badge_topMargin) {
            mBadgeTopMargin = typedArray.getDimensionPixelSize(attr, mBadgeTopMargin);
        } else if (attr == R.styleable.BGABadgeView_badge_rightMargin) {
            mBadgeRightMargin = typedArray.getDimensionPixelSize(attr, mBadgeRightMargin);
        } else if (attr == R.styleable.BGABadgeView_badge_padding) {
            mBadgePadding = typedArray.getDimensionPixelSize(attr, mBadgePadding);
        }
    }

    private void afterInitDefaultAndCustomAttrs() {
        mBadgePaint.setTextSize(mBadgeTextSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mIsShowBadge) {
            String badgeText = "";
            if (mBadgeNumber != NOT_SHOW_BADGE_NUMBER) {
                badgeText = String.valueOf(mBadgeNumber);
            }
            // 获取数字宽所占宽高
            mBadgePaint.getTextBounds(badgeText, 0, badgeText.length(), mBadgeNumberRect);
            Log.i("bingo", "width = " + mBadgeNumberRect.width() + " height = " + mBadgeNumberRect.height());
            // 计算标记背景的宽高
            float badgeHeight = mBadgeNumberRect.height() + mBadgePadding * 2;
            float badgeWidth;
            // 当mBadgeText的长度为1时，计算出来的高度会比宽度大，此时设置宽度等于高度
            if (badgeText.length() == 1) {
                badgeWidth = badgeHeight;
            } else {
                badgeWidth = mBadgeNumberRect.width() + mBadgePadding * 2;
            }
            // 计算标记背景上下左右的值
            float badgeTop = mBadgeTopMargin;
            if (mIsCenterVertical) {
                badgeTop = (getHeight() - badgeHeight) / 2;
            }
            float badgeBottom = badgeTop + badgeHeight;
            float badgeRight = getWidth() - mBadgeRightMargin;
            float badgeLeft = badgeRight - badgeWidth;
            // 设置标记背景色
            mBadgePaint.setColor(mBadgeBgColor);
            // 绘制标记背景
            canvas.drawOval(new RectF(badgeLeft, badgeTop, badgeRight, badgeBottom), mBadgePaint);

            if (mBadgeNumber != NOT_SHOW_BADGE_NUMBER) {
                // 设置标记数字颜色
                mBadgePaint.setColor(mBadgeTextColor);
                // initDefaultAttrs方法中设置了mBadgeText居中，此处的x为标记背景的中心点y
                float x = badgeLeft + badgeWidth / 2;
                // 注意：绘制数字时的y是指数字底部，而不是数字的中间
                float y = badgeBottom - mBadgePadding;
                // 绘制标记数字
                canvas.drawText(badgeText, x, y, mBadgePaint);
            }
        }
    }

    public void showBadge() {
        showBadge(NOT_SHOW_BADGE_NUMBER);
    }

    public void showBadge(int badgeNumber) {
        mIsShowBadge = true;
        mBadgeNumber = badgeNumber;
        postInvalidate();
    }

    public void hiddenBadge() {
        mIsShowBadge = false;
        postInvalidate();
    }
}