package bilgerat.wizzy.avast.Support;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import bilgerat.wizzy.avast.R;

/**
 * Created by Anton on 12/6/2015.
 *
 */
public class VirusView extends View{
    /*
     * Extends view
     * Custom class for showing virus images
     */

    private int[] colors = new int[13];
    private static final int[] colorSources = {R.styleable.VirusView_bodyColor, R.styleable.VirusView_color1, R.styleable.VirusView_color2, R.styleable.VirusView_color3, R.styleable.VirusView_color4, R.styleable.VirusView_color5, R.styleable.VirusView_color6, R.styleable.VirusView_color7, R.styleable.VirusView_color8, R.styleable.VirusView_color9, R.styleable.VirusView_color10, R.styleable.VirusView_color11, R.styleable.VirusView_color12};
    private int[] shapes = new int[12];
    private static final int[] shapeSources = {R.styleable.VirusView_shape1, R.styleable.VirusView_shape2, R.styleable.VirusView_shape3, R.styleable.VirusView_shape4, R.styleable.VirusView_shape5, R.styleable.VirusView_shape6, R.styleable.VirusView_shape7, R.styleable.VirusView_shape8, R.styleable.VirusView_shape9, R.styleable.VirusView_shape10, R.styleable.VirusView_shape11, R.styleable.VirusView_shape12};
    public static final int CIRCLE = 0,SQUARE = 1, TRIANGLE = 2, FORK = 3, GONE = 4;

    private Paint drawPaint, overlayPaint;
    private Bitmap cell;
    private Matrix cellScale;
    private float overlaySize;

    public VirusView(Context context) {
        super(context);
        init();
    }
    public VirusView(Context context, AttributeSet attrs) {
        super(context,attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VirusView, 0, 0);
        try {
            for (int i=0; i<colors.length; i++) {
                colors[i] = a.getColor(colorSources[i], 0x777777);
            }
            for (int i=0; i<shapes.length; i++) {
                shapes[i] = a.getInt(shapeSources[i], 0);
            }
        }finally {
            a.recycle();
        }
        init();
    }

    private void init() {
        drawPaint = new Paint(0);
        overlayPaint = new Paint(0);
        overlayPaint.setAlpha(128);
        cell = BitmapFactory.decodeResource(getResources(), R.mipmap.cell, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        float xpad = (float)(getPaddingLeft() + getPaddingRight());
        float ypad = (float)(getPaddingTop() + getPaddingBottom());

        float ww = (float)w - xpad;
        float hh = (float)h - ypad;

        float size = Math.min(ww, hh);
        float oldsize = cell.getWidth();
        float scale = size / oldsize;
        cellScale = new Matrix();
        cellScale.postScale(scale, scale);
        overlaySize = size/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(cell, cellScale, drawPaint);
        overlayPaint.setColor(colors[0]);
        canvas.drawCircle(overlaySize, overlaySize, overlaySize, overlayPaint);
    }

    public int getShape(int shapenum) {
        return shapes[shapenum-1];
    }
    public void setShape(int shapenum, int shape) {
        shapes[shapenum-1] = shape;
        invalidate();
        requestLayout();
    }
    public int getColor(int index) {
        return colors[index];
    }
    public void setColor(int index, int color) {
        colors[index] = color;
        invalidate();
        requestLayout();
    }
}
