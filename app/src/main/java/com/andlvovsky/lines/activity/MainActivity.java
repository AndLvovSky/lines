package com.andlvovsky.lines.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

import com.andlvovsky.lines.R;
import com.andlvovsky.lines.controller.LinesGameController;
import com.andlvovsky.lines.meta.GameConstants;
import com.andlvovsky.lines.view.CellView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinesGameController controller = LinesGameController.INSTANCE;

    Button restartButton;
    TableLayout boardLayout;
    List<CellView> cells = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void restart() {
        controller.startGame();
    }

    private void init() {
        setViews();
        createCells();
        setListeners();
        controller.startGame();
    }

    private void setViews() {
        restartButton = findViewById(R.id.restartButton);
        boardLayout = findViewById(R.id.boardTableLayout);
    }

    private void createCells() {
        for (int i = 0; i < GameConstants.SIZE; i++) {
            TableRow row = new TableRow(this);
            customizeRow(row);
            boardLayout.addView(row);
            for (int j = 0; j < GameConstants.SIZE; j++) {
                CellView cell = new CellView(this, i, j);
                customizeCell(cell);
                row.addView(cell);
                cells.add(cell);
            }
        }
    }

    private void customizeRow(TableRow row) {
        row.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT,
                1
        ));
    }

    private void customizeCell(CellView cell) {
        cell.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT,
                1
        ));
    }

    private void setListeners() {
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
            }
        });
        for (final CellView cell: cells)
            cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    controller.chooseCell(cell.iCoord(), cell.jCoord());
                }
            });
    }
}
