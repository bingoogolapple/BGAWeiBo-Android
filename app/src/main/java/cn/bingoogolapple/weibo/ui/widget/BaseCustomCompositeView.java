package cn.bingoogolapple.weibo.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/3 上午1:30
 * 描述:
 */
public abstract class BaseCustomCompositeView extends RelativeLayout {

    public BaseCustomCompositeView(Context context) {
        this(context, null);
    }

    public BaseCustomCompositeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseCustomCompositeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, getLayoutId(), this);
        initView();
        setListener();
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, getAttrs());
        final int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            initAttr(typedArray.getIndex(i), typedArray);
        }
        typedArray.recycle();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    /**
     * 给View控件添加事件监听器
     */
    protected void setListener() {
    }

    protected abstract int[] getAttrs();

    protected abstract void initAttr(int attr, TypedArray typedArray);

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }
}