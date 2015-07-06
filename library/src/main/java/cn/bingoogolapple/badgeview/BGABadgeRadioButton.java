package cn.bingoogolapple.badgeview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/6 下午3:04
 * 描述:
 */
public class BGABadgeRadioButton extends AppCompatRadioButton implements BGABadgeable {
    private BGABadgeViewHelper mBadgeViewHeler;

    public BGABadgeRadioButton(Context context) {
        this(context, null);
    }

    public BGABadgeRadioButton(Context context, AttributeSet attrs) {
        // 记得传递android.R.attr.radioButtonStyle参数，否则放到RadioGroup中时点击无效
        this(context, attrs, android.R.attr.radioButtonStyle);
    }

    public BGABadgeRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBadgeViewHeler = new BGABadgeViewHelper(this, context, attrs, BGABadgeViewHelper.BadgeGravity.RightTop);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBadgeViewHeler.drawBadge(canvas);
    }

    @Override
    public void beforeDrawBadge(Canvas canvas) {
    }

    @Override
    public void showBadge() {
        mBadgeViewHeler.showBadge();
    }

    @Override
    public void showBadge(String badgeText) {
        mBadgeViewHeler.showBadge(badgeText);
    }

    @Override
    public void hiddenBadge() {
        mBadgeViewHeler.hiddenBadge();
    }

}