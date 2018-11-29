package com.example.user.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random r;
    Button btn0;
    int id[] = new int[3];
    int x[] = new int[3];
    int y[] = new int[3];
    float conX=0;
    int conS;
    String result;
    TextView tv;
    Button left;
    Button right;
    Button reset;
    Button big;
    Button small;
    Button sReset;
    TextView con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        r=new Random();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.text1);
        btn0=(Button)findViewById(R.id.btn0);
        left=(Button)findViewById(R.id.left);
        right=(Button)findViewById(R.id.right);
        reset=(Button)findViewById(R.id.reset);
        con=(TextView)findViewById(R.id.con);
        big=(Button)findViewById(R.id.Big);
        small=(Button)findViewById(R.id.Small);
        sReset=(Button)findViewById(R.id.sReset);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn0.setText("clicked");
            }
        });
        conS=con.getWidth();
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                con.setBackgroundColor(Color.rgb(r.nextInt(2550),r.nextInt(2550),r.nextInt(2550)));
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                con.setLeft(0);
                con.setRight(0);
            }
        });
        btn0.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn0.setX(event.getX());
                btn0.setY(event.getY());
                // btn0.setY(event.getY());
                return true;
            }
        });
        /*
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                conX-=5;
                con.setX(conX);
                return true;
            }
        });
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                conX+=5;
                con.setX(conX);
                return true;
            }
        });
        reset.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                conX-=1;
                con.setX(conX);
                return false;
            }
        });
         */

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conX-=20;
                con.setX(conX);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conX+=20;
                con.setX(conX);
            }
        });
        big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conS+=20;
                con.setWidth(conS);
            }
        });
        small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conS-=20;
            }
        });
        LongPressRepeatListener lListener=new LongPressRepeatListener(200, (long)0.01, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conX-=4;
                con.setX(conX);
            }
        });
        LongPressRepeatListener rListener=new LongPressRepeatListener(200, (long)0.01, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conX+=4;
                con.setX(conX);
            }
        });
        LongPressRepeatListener bListener=new LongPressRepeatListener(200, (long)0.01, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conS+=10;
                con.setWidth(conS);
            }
        });
        LongPressRepeatListener sListener=new LongPressRepeatListener(200, (long)0.01, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conS-=10;
                con.setWidth(conS);
            }
        });
        left.setOnTouchListener(lListener);
        right.setOnTouchListener(rListener);
        big.setOnTouchListener(bListener);
        small.setOnTouchListener(sListener);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointer_count = event.getPointerCount(); //현재 터치 발생한 포인트 수를 얻는다.
        if(pointer_count > 3) pointer_count = 3; //4개 이상의 포인트를 터치했더라도 3개까지만 처리를 한다.

        switch(event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: //한 개 포인트에 대한 DOWN을 얻을 때.
                result = "싱글터치 : \n";
                id[0] = event.getPointerId(0); //터치한 순간부터 부여되는 포인트 고유번호.
                x[0] = (int) (event.getX());
                y[0] = (int) (event.getY());
                result = "싱글터치 : \n";
                result += "("+x[0]+","+y[0]+")";
                break;
            case MotionEvent.ACTION_POINTER_DOWN: //두 개 이상의 포인트에 대한 DOWN을 얻을 때.
                result = "멀티터치 :\n";
                for(int i = 0; i < pointer_count; i++) {
                    id[i] = event.getPointerId(i); //터치한 순간부터 부여되는 포인트 고유번호.
                    x[i] = (int) (event.getX(i));
                    y[i] = (int) (event.getY(i));
                    result += "id[" + id[i] + "] ("+x[i]+","+y[i]+")\n";
                }
                break;
            case MotionEvent.ACTION_MOVE:
                result = "멀티터치 MOVE:\n";
                for(int i = 0; i < pointer_count; i++) {
                    id[i] = event.getPointerId(i);
                    x[i] = (int) (event.getX(i));
                    y[i] = (int) (event.getY(i));
                    result += "id[" + id[i] + "] ("+x[i]+","+y[i]+")\n";
                }
                break;
            case MotionEvent.ACTION_UP:
                result = "";
                break;
        }
        tv.setText(result);
        return super.onTouchEvent(event);
    }

}
