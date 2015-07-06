package cn.bingoogolapple.badgeview;

import android.graphics.Canvas;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/6 下午10:42
 * 描述:
 */
public interface BGABadgeable {
    /**
     * 在绘制标记前调用，暂时用不到，预留着
     *
     * @param canvas
     */
    void beforeDrawBadge(Canvas canvas);

    /**
     * 显示圆点标记
     */
    void showBadge();

    /**
     * 显示文字标记
     * @param badgeText
     */
    void showBadge(String badgeText);

    /**
     * 隐藏标记
     */
    void hiddenBadge();

    int getWidth();

    int getHeight();

    void postInvalidate();
}