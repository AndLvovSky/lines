package com.andlvovsky.lines.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.andlvovsky.lines.controller.LinesGameController;
import com.andlvovsky.lines.game.LinesGame;
import com.andlvovsky.lines.meta.Colors;
import com.andlvovsky.lines.util.Coordinates;

public class CellView extends View {
    private final int iCoord;
    private final int jCoord;

    private LinesGame game = LinesGame.INSTANCE;
    private LinesGameController controller = LinesGameController.INSTANCE;

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
        int a = getWidth();
        drawBorder(canvas, a);
        drawBallIfExists(canvas, a);
    }

    // make cell height equal to width
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }

    private void drawBorder(Canvas canvas, int a) {
        if (controller.getChosenCells().contains(new Coordinates(iCoord, jCoord))) {
            drawHighlightedBorder(canvas, a);
        } else {
            drawNormalBorder(canvas, a);
        }
    }

    private void drawBallIfExists(Canvas canvas, int a) {
        if (game.getBoard().isEmpty(iCoord, jCoord)) return;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(game.getBoard().getColor(iCoord, jCoord));
        canvas.drawCircle((float)a / 2, (float)a / 2, (float)a / 3, paint);
    }

    private void drawHighlightedBorder(Canvas canvas, int a) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Colors.HIGHLIGHTED_BORDER);
        canvas.drawRect(1, 1, a - 1, a - 1, paint);

    }

    private void drawNormalBorder(Canvas canvas, int a) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Colors.NORMAL_BORDER);
        canvas.drawRect(1,  1, a - 1, a - 1, paint);
    }
}
