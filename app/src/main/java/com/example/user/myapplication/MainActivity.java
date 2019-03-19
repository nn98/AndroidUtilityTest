package com.example.user.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random r;
    Button btn0;
    int id[] = new int[3];
    int x[] = new int[3];
    int y[] = new int[3];
    int[] conInfo = new int[4];
    float conX;
    float conY;
    int conW;
    int conH;
    int c = 16777215;
    boolean first = true;

    Point point;

    String result;
    TextView tv;
    Button reset;
    Button left;
    Button right;
    Button up;
    Button down;
    Button wInc;
    Button wDec;
    Button hInc;
    Button hDec;
    Button sReset;

    TextView con;
    float oldXvalue;
    float oldYvalue;

    TextView tConX;
    TextView tConY;
    ViewGroup.LayoutParams p;
    TextView tConW;
    TextView tConH;
    TextView tConC;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        r = new Random();
        point = new Point();
        tv = (TextView) findViewById(R.id.text1);
        btn0 = (Button) findViewById(R.id.btn0);
        reset = (Button) findViewById(R.id.reset);
        left = (Button) findViewById(R.id.left);
        right = (Button) findViewById(R.id.right);
        up = (Button) findViewById(R.id.up);
        down = (Button) findViewById(R.id.down);
        con = (TextView) findViewById(R.id.con);
        wInc = (Button) findViewById(R.id.wInc);
        wDec = (Button) findViewById(R.id.wDec);
        hInc = (Button) findViewById(R.id.hInc);
        hDec = (Button) findViewById(R.id.hDec);
        sReset = (Button) findViewById(R.id.sReset);
        tConX = (TextView) findViewById(R.id.conX);
        tConY = (TextView) findViewById(R.id.conY);
        tConW = (TextView) findViewById(R.id.conW);
        tConH = (TextView) findViewById(R.id.conH);
        tConC = (TextView) findViewById(R.id.conC);

        i=new Intent(this,DrawActivity.class);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn0.setText("Clicked");
            }
        });

        con.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int width = ((ViewGroup) v.getParent()).getWidth() - v.getWidth();
                int height = ((ViewGroup) v.getParent()).getHeight() - v.getHeight();

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    oldXvalue = event.getX();
                    oldYvalue = event.getY();
                    //  Log.i("Tag1", "Action Down X" + event.getX() + "," + event.getY());
                    //  Log.i("Tag1", "Action Down rX " + event.getRawX() + "," + event.getRawY());
                    return false;
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

                    float x = event.getRawX() - oldXvalue;
                    v.setX(x);
                    tConX.setText("" + (int) x);
                    conX = x;

                    float y = event.getRawY() - oldYvalue;
                    v.setY(y);
                    tConY.setText("" + (int) y);
                    conY = y;

                    //  Log.i("Tag2", "Action Down " + me.getRawX() + "," + me.getRawY());
                    return false;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (v.getX() > width && v.getY() > height) {
                        v.setX(width);
                        v.setY(height);
                    } else if (v.getX() < 0 && v.getY() > height) {
                        v.setX(0);
                        v.setY(height);
                    } else if (v.getX() > width && v.getY() < 0) {
                        v.setX(width);
                        v.setY(0);
                    } else if (v.getX() < 0 && v.getY() < 0) {
                        v.setX(0);
                        v.setY(0);
                    } else if (v.getX() < 0 || v.getX() > width) {
                        if (v.getX() < 0) {
                            v.setX(0);
                            v.setY(event.getRawY() - oldYvalue - v.getHeight());
                        } else {
                            v.setX(width);
                            v.setY(event.getRawY() - oldYvalue - v.getHeight());
                        }
                    } else if (v.getY() < 0 || v.getY() > height) {
                        if (v.getY() < 0) {
                            v.setX(event.getRawX() - oldXvalue);
                            v.setY(0);
                        } else {
                            v.setX(event.getRawX() - oldXvalue);
                            v.setY(height);
                        }
                    }

                    return false;

                }
                return true;
            }
        });

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Color.rgb(r.nextInt(2550), r.nextInt(2550), r.nextInt(2550));
                con.setBackgroundColor(c);
                tConC.setText(String.format("#%x", c));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (first) {
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    con.setVisibility(View.VISIBLE);
                    sReset.setVisibility(View.VISIBLE);
                    btn0.setVisibility(View.VISIBLE);
                    //View control
                    reset.setText("RESET");
                    left.setVisibility(LinearLayout.VISIBLE);
                    right.setVisibility(LinearLayout.VISIBLE);
                    up.setVisibility(LinearLayout.VISIBLE);
                    down.setVisibility(LinearLayout.VISIBLE);
                    wInc.setVisibility(LinearLayout.VISIBLE);
                    wDec.setVisibility(LinearLayout.VISIBLE);
                    hInc.setVisibility(LinearLayout.VISIBLE);
                    hDec.setVisibility(LinearLayout.VISIBLE);
                    //Location control
                    con.getLocationOnScreen(conInfo);
                    con.setX(conInfo[0]);
                    conX = conInfo[0];
                    tConX.setText("" + conX);
                    con.setY(conInfo[1]);
                    conY = conInfo[1];
                    tConY.setText("" + conY);
                    //Size control
                    p = con.getLayoutParams();
                    conInfo[2] = p.width;
                    conInfo[3] = p.height;
                    conW = p.width;
                    conH = p.height;
                    tConW.setText("" + conW);
                    tConH.setText("" + conH);
                    //Color control
                    tConC.setText(String.format("#%x", c));

                    first = false;
                } else {
                    System.out.println(Arrays.toString(conInfo));
                    p.width = conInfo[2];
                    conW = conInfo[2];
                    tConW.setText("" + conW);
                    p.height = conInfo[3];
                    conH = conInfo[3];
                    tConH.setText("" + conH);
                    con.setLayoutParams(p);
                    conX = conInfo[0];
                    con.setX(conInfo[0]+(conInfo[2]-con.getWidth())/2);
                    tConX.setText("" + conX);
                    conY = conInfo[1];
                    con.setY(conInfo[1]+(conInfo[3]-con.getHeight())/2);
                    tConY.setText("" + conY);
                    con.setBackgroundColor(Color.rgb(255, 255, 255));
                }
            }
        });

        /*
        btn0.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn0.setX(event.getX());
                btn0.setY(event.getY());
                // btn0.setY(event.getY());
                return true;
            }
        });
         */
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
                conX -= 20;
                con.setX(conX);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conX += 20;
                con.setX(conX);
            }
        });
        /*

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
                con.setWidth(conS);
            }
        });

         */
        LongPressRepeatListener lListener = new LongPressRepeatListener(200, (long) 0.01, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conX -= 4;
                con.setX(conX);
                tConX.setText("" + conX);
            }
        });
        LongPressRepeatListener rListener = new LongPressRepeatListener(200, (long) 0.01, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conX += 4;
                con.setX(conX);
                tConX.setText("" + conX);
            }
        });
        LongPressRepeatListener uListener = new LongPressRepeatListener(200, (long) 0.01, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conY -= 4;
                con.setY(conY);
                tConY.setText("" + conY);
            }
        });
        LongPressRepeatListener dListener = new LongPressRepeatListener(200, (long) 0.01, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conY += 4;
                con.setY(conY);
                tConY.setText("" + conY);
            }
        });
        LongPressRepeatListener wIListener = new LongPressRepeatListener(200, (long) 0.01, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conW += 2;
                conX -= 1;
                p = con.getLayoutParams();
                p.width = conW;
                con.setLayoutParams(p);
                tConW.setText("" + conW);
                tConX.setText("" + conX);
            }
        });
        LongPressRepeatListener wDListener = new LongPressRepeatListener(200, (long) 0.01, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conW -= 2;
                conX += 1;
                p = con.getLayoutParams();
                p.width = conW;
                con.setLayoutParams(p);
                tConW.setText("" + conW);
                tConX.setText("" + conX);
            }
        });
        LongPressRepeatListener hIListener = new LongPressRepeatListener(200, (long) 0.01, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conH += 2;
                conY -= 1;
                ViewGroup.LayoutParams p = con.getLayoutParams();
                p.height = conH;
                con.setLayoutParams(p);
                tConH.setText("" + conH);
                tConY.setText("" + conY);
            }
        });
        LongPressRepeatListener hDListener = new LongPressRepeatListener(200, (long) 0.01, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conH -= 2;
                conY += 1;
                ViewGroup.LayoutParams p = con.getLayoutParams();
                p.height = conH;
                con.setLayoutParams(p);
                tConH.setText("" + conH);
                tConY.setText("" + conY);
            }
        });

        left.setOnTouchListener(lListener);
        right.setOnTouchListener(rListener);
        up.setOnTouchListener(uListener);
        down.setOnTouchListener(dListener);
        wInc.setOnTouchListener(wIListener);
        wDec.setOnTouchListener(wDListener);
        hInc.setOnTouchListener(hIListener);
        hDec.setOnTouchListener(hDListener);

        sReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointer_count = event.getPointerCount(); //현재 터치 발생한 포인트 수를 얻는다.
        if (pointer_count > 3) pointer_count = 3; //4개 이상의 포인트를 터치했더라도 3개까지만 처리를 한다.

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: //한 개 포인트에 대한 DOWN을 얻을 때.
                result = "싱글터치 : \n";
                id[0] = event.getPointerId(0); //터치한 순간부터 부여되는 포인트 고유번호.
                x[0] = (int) (event.getX());
                y[0] = (int) (event.getY());
                result = "싱글터치 : \n";
                result += "(" + x[0] + "," + y[0] + ")";
                break;
            case MotionEvent.ACTION_POINTER_DOWN: //두 개 이상의 포인트에 대한 DOWN을 얻을 때.
                result = "멀티터치 :\n";
                for (int i = 0; i < pointer_count; i++) {
                    id[i] = event.getPointerId(i); //터치한 순간부터 부여되는 포인트 고유번호.
                    x[i] = (int) (event.getX(i));
                    y[i] = (int) (event.getY(i));
                    result += "터치 " + id[i] + ": (" + x[i] + "," + y[i] + ")\n";
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (pointer_count > 1) {
                    result = "멀티터치 MOVE:\n";
                    for (int i = 0; i < pointer_count; i++) {
                        id[i] = event.getPointerId(i);
                        x[i] = (int) (event.getX(i));
                        y[i] = (int) (event.getY(i));
                        result += "터치 " + id[i] + ": (" + x[i] + "," + y[i] + ")\n";
                    }
                } else {
                    result = "싱글터치 MOVE:\n";
                    for (int i = 0; i < pointer_count; i++) {
                        id[i] = event.getPointerId(i);
                        x[i] = (int) (event.getX(i));
                        y[i] = (int) (event.getY(i));
                        result += "(" + x[i] + "," + y[i] + ")\n";
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                result = "";
                break;
        }
        tv.setText(result);
        return super.onTouchEvent(event);
    }

    /*
    float oldXvalue;
    float oldYvalue;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int width = ((ViewGroup) v.getParent()).getWidth() - v.getWidth();
        int height = ((ViewGroup) v.getParent()).getHeight() - v.getHeight();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            oldXvalue = event.getX();
            oldYvalue = event.getY();
            //  Log.i("Tag1", "Action Down X" + event.getX() + "," + event.getY());
            Log.i("Tag1", "Action Down rX " + event.getRawX() + "," + event.getRawY());
            return false;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            v.setX(event.getRawX() - oldXvalue);
            v.setY(event.getRawY() - oldYvalue);
            //  Log.i("Tag2", "Action Down " + me.getRawX() + "," + me.getRawY());
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (v.getX() > width && v.getY() > height) {
                v.setX(width);
                v.setY(height);
            } else if (v.getX() < 0 && v.getY() > height) {
                v.setX(0);
                v.setY(height);
            } else if (v.getX() > width && v.getY() < 0) {
                v.setX(width);
                v.setY(0);
            } else if (v.getX() < 0 && v.getY() < 0) {
                v.setX(0);
                v.setY(0);
            } else if (v.getX() < 0 || v.getX() > width) {
                if (v.getX() < 0) {
                    v.setX(0);
                    v.setY(event.getRawY() - oldYvalue - v.getHeight());
                } else {
                    v.setX(width);
                    v.setY(event.getRawY() - oldYvalue - v.getHeight());
                }
            } else if (v.getY() < 0 || v.getY() > height) {
                if (v.getY() < 0) {
                    v.setX(event.getRawX() - oldXvalue);
                    v.setY(0);
                } else {
                    v.setX(event.getRawX() - oldXvalue);
                    v.setY(height);
                }
            }

            return true;

        }
        return false;
    }
    */
}
