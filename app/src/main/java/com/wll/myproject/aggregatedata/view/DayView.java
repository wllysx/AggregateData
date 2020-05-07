package com.wll.myproject.aggregatedata.view;
/*
    Create by WLL on 2020/4/29 DATA: 12:55
*/

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.dsw.calendar.component.MonthView;
import com.dsw.calendar.utils.DateUtils;

public abstract class DayView extends MonthView {
    public DayView(Context context, AttributeSet attrs) {
        super( context, attrs );
    }


    @Override
    protected void drawLines(Canvas canvas, int rowsCount) {
        drawLines( canvas, rowsCount );
    }

    @Override
    protected void drawBG(Canvas canvas, int column, int row, int day) {
        drawBG( canvas, column, row, day );
    }

    @Override
    protected void drawDecor(Canvas canvas, int column, int row, int year, int month, int day) {
        drawDecor( canvas, column, row, year, month, day );
    }

    @Override
    protected void drawRest(Canvas canvas, int column, int row, int year, int month, int day) {
        drawRest( canvas, column, row, year, month, day );
    }

    @Override
    protected void drawText(Canvas canvas, int column, int row, int year, int month, int day) {
        drawText( canvas, column, row, year, month, day );
    }

    @Override
    protected void createTheme() {
        createTheme();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw( canvas );
    }

    private void drawDate(Canvas canvas,int year,int month,int startX,int startY){
        canvas.save();
        canvas.translate(startX,startY);
        NUM_ROWS =  getMonthRowNumber(year,month);
        columnSize = getWidth() *1.0F/ NUM_COLUMNS;
        rowSize = getHeight() * 1.0F / NUM_ROWS;
        daysString = new int[6][7];
        int mMonthDays = DateUtils.getMonthDays(year, month);
        int weekNumber = DateUtils.getFirstDayWeek(year, month);
        int column,row;
        drawLines(canvas,NUM_ROWS);
        for(int day = 0;day < mMonthDays;day++){
            column = (day+weekNumber - 1) % 7;
            row = (day+weekNumber - 1) / 7;
            daysString[row][column]=day + 1;
            drawBG(canvas,column,row,daysString[row][column]);
            drawDecor(canvas,column,row,year,month,daysString[row][column]);
            drawRest(canvas,column,row,year,month,daysString[row][column]);
            drawText(canvas,column,row,year,month,daysString[row][column]);
        }
        canvas.restore();
    }
}
