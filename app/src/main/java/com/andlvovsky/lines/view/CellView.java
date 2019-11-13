package com.andlvovsky.lines.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class CellView extends View {
    private static final int MARGIN = 10;

    private final int iCoord;
    private final int jCoord;

    private Rect boardRect = new Rect();
    private Paint paint = new Paint();

    public CellView(Context context) {
        super(context);
        iCoord = jCoord = 0;
    }

    public CellView(Context context, int iCoord, int jCoord) {
        super(context);
        this.iCoord = iCoord;
        this.jCoord = jCoord;
    }

    public int iCoord() {
        return iCoord;
    }

    public int jCoord() {
        return jCoord;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        boardRect.set(MARGIN, MARGIN, canvas.getWidth() - MARGIN, canvas.getHeight() - MARGIN);
        paint.setARGB(255, 0, 0, 255);
        canvas.drawRect(boardRect, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }
}
