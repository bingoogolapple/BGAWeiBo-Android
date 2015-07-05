package cn.bingoogolapple.titlebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/4 下午4:51
 * 描述:
 */
public class BGATitlebar extends RelativeLayout implements View.OnClickListener {
    private AppCompatCheckedTextView mTitleCtv;
    private AppCompatButton mLeftBtn;
    private AppCompatButton mRightBtn;
    private BGATitlebarDelegate mDelegate;

    public BGATitlebar(Context context) {
        this(context, null);
    }

    public BGATitlebar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BGATitlebar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_bgatitlebar, this);
        initView();
        setListener();
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BGATitlebar);
        final int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            initAttr(typedArray.getIndex(i), typedArray);
        }
        typedArray.recycle();
    }

    protected void initView() {
        mLeftBtn = getViewById(R.id.btn_bgatitlebar_left);
        mRightBtn = getViewById(R.id.btn_bgatitlebar_right);
        mTitleCtv = getViewById(R.id.ctv_bgatitlebar_title);
    }

    protected void setListener() {
        mLeftBtn.setOnClickListener(this);
        mTitleCtv.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);
    }

    protected void initAttr(int attr, TypedArray typedArray) {
        if (attr == R.styleable.BGATitlebar_bgatitlebar_leftText) {
            setLeftText(typedArray.getText(attr));
        } else if (attr == R.styleable.BGATitlebar_bgatitlebar_titleText) {
            setTitleText(typedArray.getText(attr));
        } else if (attr == R.styleable.BGATitlebar_bgatitlebar_rightText) {
            setRightText(typedArray.getText(attr));
        } else if (attr == R.styleable.BGATitlebar_bgatitlebar_leftDrawable) {
            setLeftDrawable(typedArray.getDrawable(attr));
        } else if (attr == R.styleable.BGATitlebar_bgatitlebar_titleDrawable) {
            setTitleDrawable(typedArray.getDrawable(attr));
        } else if (attr == R.styleable.BGATitlebar_bgatitlebar_rightDrawable) {
            setRightDrawable(typedArray.getDrawable(attr));
        } else if (attr == R.styleable.BGATitlebar_bgatitlebar_leftAndRightTextSize) {
            int textSize = typedArray.getDimensionPixelSize(attr, sp2px(getContext(), 12));
            mLeftBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            mRightBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        } else if (attr == R.styleable.BGATitlebar_bgatitlebar_titleTextSize) {
            mTitleCtv.setTextSize(TypedValue.COMPLEX_UNIT_PX, typedArray.getDimensionPixelSize(attr, sp2px(getContext(), 16)));
        } else if (attr == R.styleable.BGATitlebar_bgatitlebar_leftAndRightTextColor) {
            mLeftBtn.setTextColor(typedArray.getColorStateList(attr));
            mRightBtn.setTextColor(typedArray.getColorStateList(attr));
        } else if (attr == R.styleable.BGATitlebar_bgatitlebar_titleTextColor) {
            mTitleCtv.setTextColor(typedArray.getColorStateList(attr));
        } else if (attr == R.styleable.BGATitlebar_bgatitlebar_titleDrawablePadding) {
            mTitleCtv.setCompoundDrawablePadding(typedArray.getDimensionPixelSize(attr, dp2px(getContext(), 3)));
        } else if (attr == R.styleable.BGATitlebar_bgatitlebar_leftDrawablePadding) {
            mLeftBtn.setCompoundDrawablePadding(typedArray.getDimensionPixelSize(attr, dp2px(getContext(), 3)));
        } else if (attr == R.styleable.BGATitlebar_bgatitlebar_rightDrawablePadding) {
            mRightBtn.setCompoundDrawablePadding(typedArray.getDimensionPixelSize(attr, dp2px(getContext(), 3)));
        } else if (attr == R.styleable.BGATitlebar_bgatitlebar_leftAndRightPadding) {
            int leftAndRightPadding = typedArray.getDimensionPixelSize(attr, dp2px(getContext(), 10));
            mLeftBtn.setPadding(leftAndRightPadding, 0, leftAndRightPadding, 0);
            mRightBtn.setPadding(leftAndRightPadding, 0, leftAndRightPadding, 0);
        } else if (attr == R.styleable.BGATitlebar_bgatitlebar_leftMaxWidth) {
            setLeftBtnMaxWidth(typedArray.getDimensionPixelSize(attr, dp2px(getContext(), 85)));
        } else if (attr == R.styleable.BGATitlebar_bgatitlebar_rightMaxWidth) {
            setRightBtnMaxWidth(typedArray.getDimensionPixelSize(attr, dp2px(getContext(), 85)));
        } else if (attr == R.styleable.BGATitlebar_bgatitlebar_titleMaxWidth) {
            setTitleCtvMaxWidth(typedArray.getDimensionPixelSize(attr, dp2px(getContext(), 144)));
        }
    }

    public void setLeftBtnMaxWidth(int maxWidth) {
        mLeftBtn.setMaxWidth(maxWidth);
    }

    public void setRightBtnMaxWidth(int maxWidth) {
        mRightBtn.setMaxWidth(maxWidth);
    }

    public void setTitleCtvMaxWidth(int maxWidth) {
        mTitleCtv.setMaxWidth(maxWidth);
    }

    public void hiddenLeftBtn() {
        mLeftBtn.setVisibility(GONE);
    }

    public void showLeftBtn() {
        mLeftBtn.setVisibility(VISIBLE);
    }

    public void setLeftText(@StringRes int resid) {
        setLeftText(getResources().getString(resid));
    }

    public void setLeftText(CharSequence text) {
        mLeftBtn.setText(text);
        showLeftBtn();
    }

    public void setLeftDrawable(Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        mLeftBtn.setCompoundDrawables(drawable, null, null, null);
        showLeftBtn();
    }

    public void hiddenTitleCtv() {
        mTitleCtv.setVisibility(GONE);
    }

    public void showTitleCtv() {
        mTitleCtv.setVisibility(VISIBLE);
    }

    public void setTitleText(CharSequence text) {
        mTitleCtv.setText(text);
        showTitleCtv();
    }

    public void setTitleText(@StringRes int resid) {
        setTitleText(getResources().getString(resid));
    }

    public void setTitleDrawable(Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        mTitleCtv.setCompoundDrawables(null, null, drawable, null);
        showTitleCtv();
    }

    public void hiddenRightBtn() {
        mRightBtn.setVisibility(GONE);
    }

    public void showRightBtn() {
        mRightBtn.setVisibility(VISIBLE);
    }

    public void setRightText(CharSequence text) {
        mRightBtn.setText(text);
        showRightBtn();
    }

    public void setRightText(@StringRes int resid) {
        setRightText(getResources().getString(resid));
    }

    public void setRightDrawable(Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        mRightBtn.setCompoundDrawables(null, null, drawable, null);
        showRightBtn();
    }

    public void setTitleCtvChecked(boolean checked) {
        mTitleCtv.setChecked(checked);
    }

    public AppCompatButton getLeftBtn() {
        return mLeftBtn;
    }

    public AppCompatButton getRightBtn() {
        return mRightBtn;
    }

    public AppCompatCheckedTextView getTitleCtv() {
        return mTitleCtv;
    }

    @Override
    public void onClick(View v) {
        if (mDelegate != null) {
            int id = v.getId();
            if (id == R.id.btn_bgatitlebar_left) {
                mDelegate.onClickLeftBtn();
            } else if (id == R.id.ctv_bgatitlebar_title) {
                mDelegate.onClickTitleCtv();
            } else if (id == R.id.btn_bgatitlebar_right) {
                mDelegate.onClickRightBtn();
            }
        }
    }

    public void setDelegate(BGATitlebarDelegate delegate) {
        mDelegate = delegate;
    }

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

    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }

    /**
     * 根据实际业务重写相应地方法
     */
    public static class BGATitlebarDelegate {
        public void onClickLeftBtn() {
        }

        public void onClickTitleCtv() {
        }

        public void onClickRightBtn() {
        }
    }
}