package asiainfo.com.aaaaa.ui.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import asiainfo.com.aaaaa.R;


/**
 * 自定义带描边的圆形icon
 *
 * @author guofl
 */
@SuppressLint("NewApi")
public class CustomCircleIcon extends View {
    public interface OnMyAfterChangeListener {
        void myAfterChange(String text, int icon);
    }

    private OnMyAfterChangeListener myAfterChangeListener;

    public void setMyAfterChangeListener(OnMyAfterChangeListener myAfterChangeListener) {
        this.myAfterChangeListener = myAfterChangeListener;
    }

    /**
     * 描边的颜色
     */
    private int mStrokeColor;
    /**
     * 填充的颜色
     */
    private int mSolidColor;
    /**
     * 中间的icon
     */
    private Bitmap mIcon;
    private int mHeight;
    private int mWidth;
    private Paint mPaint;
    private int mIconDrawable;

    /**
     * 文字大小
     */
    private int mTextSize;
    /**
     * 文字颜色
     */
    private int mTextColor;

    public CustomCircleIcon(Context context) {
        this(context, null);
    }

    public CustomCircleIcon(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private Context context;
    private Canvas mCanvas;
    private String mMenuText;

    public CustomCircleIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleIconStyle, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CircleIconStyle_circle_icon:
                    mIcon = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.CircleIconStyle_circle_icon_solid_color:
                    mSolidColor = a.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.CircleIconStyle_circle_icon_stroke_color:
                    mStrokeColor = a.getColor(attr, Color.GRAY);
                    break;
                case R.styleable.CircleIconStyle_circle_text_color:
                    mTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CircleIconStyle_circle_text_size:
                    mTextSize = (int) a.getDimension(attr, 10.0f);
                    break;
            }
        }
        a.recycle();
        // 获取自定义属性后,初始化画笔等工具
        init();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.e("CustomView", "------onSizeChanged");

        mHeight = h;
        mWidth = w;
        if (myAfterChangeListener != null) {
            myAfterChangeListener.myAfterChange(mMenuText, mIconDrawable);
            postInvalidate();
        }


    }

    /**
     * 初始化画笔
     */
    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("CustomView", "------onDraw");


        mCanvas = canvas;
        mPaint.setColor(mStrokeColor);
        float cx = mWidth / 2;
        float cy = mWidth / 2;
        canvas.drawCircle(cx, cy, cx, mPaint);

        mPaint.setColor(mSolidColor);
        canvas.drawCircle(cx, cy, cx - 1, mPaint);


        Bitmap tempICON = BitmapFactory.decodeResource(context.getResources(), mIconDrawable);
        Bitmap zoomImage = zoomImage(tempICON, (mWidth / 3) * 2, (mWidth / 3) * 2);
        canvas.drawBitmap(zoomImage, cx - zoomImage.getWidth() / 2, cx - zoomImage.getHeight() / 2, mPaint);

        RectF textRectf = new RectF(0, mHeight, mWidth, mHeight);
        Path path = new Path();
        path.addRect(textRectf, Path.Direction.CCW);

        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
//        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mTextSize, context.getResources().getDisplayMetrics()));
        TextPaint tp = new TextPaint(mPaint);
        float textLength = tp.measureText(mMenuText);
        canvas.drawTextOnPath(mMenuText, path, cx - textLength / 2, -10, mPaint);

    }

    public int getTextHeight(String text, Paint paint) {
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int height = bounds.bottom + bounds.height();
        return height;
    }


    /***
     * 图片的缩放方法
     *
     * @param bgimage   ：源图片资源
     * @param newWidth  ：缩放后宽度
     * @param newHeight ：缩放后高度
     * @return
     */
    public static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width, (int) height, matrix, true);
        return bitmap;
    }

    public String getmMenuText() {
        return mMenuText;
    }

    public void setmMenuText(String mMenuText) {
        this.mMenuText = mMenuText;

    }

    public void setmIconDrawable(int mIconDrawable) {
        this.mIconDrawable = mIconDrawable;
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("CustomView", "------onLayout");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("CustomView", "------onMeasure");

    }


}
