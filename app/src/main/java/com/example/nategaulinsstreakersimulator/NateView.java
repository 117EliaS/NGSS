package com.example.nategaulinsstreakersimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

public class NateView extends View {
    private Bitmap nate;

    public NateView(Context context){
        super(context);

        nate = BitmapFactory.decodeResource(getResources(), R.);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawBitmap(nate, 0, 0, null);
    }
}
