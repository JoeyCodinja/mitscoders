package mits.uwi.com.ourmobileenvironment.customviews;

/**
 * Created by Danuel on 23/07/2015.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.content.res.TypedArray;
import android.view.View;

import java.util.ArrayList;


import mits.uwi.com.ourmobileenvironment.R;

public class CardCarousel extends View {

    private String labelTitle;
    private String labelBody;

    public String getLabelTitle() {
        return labelTitle;
    }

    public void setLabelTitle(String labelTitle) {
        this.labelTitle = labelTitle;
        invalidate();
        requestLayout();
    }

    public String getLabelBody() {
        return labelBody;
    }

    public void setLabelBody(String labelBody) {
        this.labelBody = labelBody;
        invalidate();
        requestLayout();
    }

    public int getLabelBackgroundColor() {
        return labelBackgroundColor;
    }

    public void setLabelBackgroundColor(int labelBackgroundColor) {
        this.labelBackgroundColor = labelBackgroundColor;
        invalidate();
        requestLayout();
    }

    private int labelBackgroundColor;
    private Paint cardPaint;

    private ArrayList<String> cardTitles = new ArrayList<String>();
    private ArrayList<String> cardBody   = new ArrayList<String>();

    public CardCarousel(Context context, AttributeSet attrs) {
        super(context, attrs);

        cardPaint = new Paint();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CardCarousel, 0, 0);
        try {
            labelTitle = a.getString(R.styleable.CardCarousel_labelTitle);
            labelBody = a.getString(R.styleable.CardCarousel_labelBody);
            labelBackgroundColor = a.getInt(R.styleable.CardCarousel_labelBackgroundColor, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cardHeight = this.getHeight();
        int cardWidth = this.getWidth();

        //Creates three Rectangles, on in the foreground, and two in the
        //background with the foreground rectangle casting a shadow on bo.th
        //rectangles in the background.
        Rect foregroundRect = new Rect(cardWidth/2-100, cardHeight/2+100, cardWidth/2+100, cardHeight/2+200);

        cardPaint.setStyle(Paint.Style.FILL);
        cardPaint.setAntiAlias(true);
        cardPaint.setColor(labelBackgroundColor);

        canvas.drawRect(foregroundRect, cardPaint);

        cardPaint.setTextSize(30);
        cardPaint.setColor(Color.BLACK);
        cardPaint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(labelTitle,100,250, cardPaint);

        cardPaint.setTextSize(15f);
        cardPaint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(labelBody,cardHeight/3,cardWidth/3,cardPaint);

    }
}