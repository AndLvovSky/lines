package com.andlvovsky.lines.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class BoardView extends View {
    private Rect boardRect = new Rect();
    private Paint paint = new Paint();

    public BoardView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        boardRect.set(0, 0, canvas.getWidth(), canvas.getHeight());
        paint.setARGB(255, 0, 0, 255);
        canvas.drawRect(boardRect, paint);
    }
}
