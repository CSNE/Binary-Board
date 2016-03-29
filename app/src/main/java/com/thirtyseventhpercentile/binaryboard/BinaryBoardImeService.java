package com.thirtyseventhpercentile.binaryboard;

import android.inputmethodservice.InputMethodService;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.TextView;


public class BinaryBoardImeService extends InputMethodService implements View.OnClickListener, View.OnTouchListener {

    private static final String LOG_TAG = "CS_BinaryBoard";



    Button  delBtn, enterBtn, confirmBtn;
    TextView codePointPreview;

    Button btn_1,btn_2,btn_4,btn_8,btn_16,btn_32,btn_64,btn_128,btn_256,btn_512,btn_1024,btn_2048,btn_4096,btn_8192,btn_16384,btn_32768;
    TextView tv_1,tv_2,tv_4,tv_8,tv_16,tv_32,tv_64,tv_128,tv_256,tv_512,tv_1024,tv_2048,tv_4096,tv_8192,tv_16384,tv_32768;
    TextView binTV_1,binTV_2,binTV_4,binTV_8,binTV_16,binTV_32,binTV_64,binTV_128,binTV_256,binTV_512,binTV_1024,binTV_2048,binTV_4096,binTV_8192,binTV_16384,binTV_32768;
    boolean active_1=false,active_2=false,active_4=false,active_8=false,active_16=false,active_32=false,active_64=false,active_128=false,active_256=false,active_512=false,active_1024=false,active_2048=false,active_4096=false,active_8192=false,active_16384=false,active_32768=false;

    int value=0;


    int activeBtn= 0xFFB0B0B0;
    int inActiveBtn= 0xFF404040;
    int activeBtnText= 0xFF404040;
    int inActiveBtnText= 0xFFB0B0B0;
    int activeTV= 0xFF000000;
    int inActiveTV= 0x40000000;

    @Override
    public View onCreateInputView() {
        View v = getLayoutInflater().inflate(R.layout.layout, null);

        delBtn = (Button) v.findViewById(R.id.key_del);
        delBtn.setOnTouchListener(this);

        enterBtn = (Button) v.findViewById(R.id.key_end);
        enterBtn.setOnClickListener(this);

        confirmBtn=(Button)v.findViewById(R.id.preview_char);
        confirmBtn.setOnClickListener(this);

        codePointPreview =(TextView)v.findViewById(R.id.preview_codepoint);

/*
        confirmBtn.setBackgroundColor(inActiveBtn);
        confirmBtn.setTextColor(inActiveBtnText);
        enterBtn.setBackgroundColor(inActiveBtn);
        enterBtn.setTextColor(inActiveBtnText);
        delBtn.setBackgroundColor(inActiveBtn);
        delBtn.setTextColor(inActiveBtnText);

*/

        btn_1=(Button)v.findViewById(R.id.btn_1);
        btn_2=(Button)v.findViewById(R.id.btn_2);
        btn_4=(Button)v.findViewById(R.id.btn_4);
        btn_8=(Button)v.findViewById(R.id.btn_8);
        btn_16=(Button)v.findViewById(R.id.btn_16);
        btn_32=(Button)v.findViewById(R.id.btn_32);
        btn_64=(Button)v.findViewById(R.id.btn_64);
        btn_128=(Button)v.findViewById(R.id.btn_128);
        btn_256=(Button)v.findViewById(R.id.btn_256);
        btn_512=(Button)v.findViewById(R.id.btn_512);
        btn_1024=(Button)v.findViewById(R.id.btn_1024);
        btn_2048=(Button)v.findViewById(R.id.btn_2048);
        btn_4096=(Button)v.findViewById(R.id.btn_4096);
        btn_8192=(Button)v.findViewById(R.id.btn_8192);
        btn_16384=(Button)v.findViewById(R.id.btn_16384);
        btn_32768=(Button)v.findViewById(R.id.btn_32768);

        tv_1=(TextView)v.findViewById(R.id.value_1);
        tv_2=(TextView)v.findViewById(R.id.value_2);
        tv_4=(TextView)v.findViewById(R.id.value_4);
        tv_8=(TextView)v.findViewById(R.id.value_8);
        tv_16=(TextView)v.findViewById(R.id.value_16);
        tv_32=(TextView)v.findViewById(R.id.value_32);
        tv_64=(TextView)v.findViewById(R.id.value_64);
        tv_128=(TextView)v.findViewById(R.id.value_128);
        tv_256=(TextView)v.findViewById(R.id.value_256);
        tv_512=(TextView)v.findViewById(R.id.value_512);
        tv_1024=(TextView)v.findViewById(R.id.value_1024);
        tv_2048=(TextView)v.findViewById(R.id.value_2048);
        tv_4096=(TextView)v.findViewById(R.id.value_4096);
        tv_8192=(TextView)v.findViewById(R.id.value_8192);
        tv_16384=(TextView)v.findViewById(R.id.value_16384);
        tv_32768=(TextView)v.findViewById(R.id.value_32768);

        binTV_1=(TextView)v.findViewById(R.id.binary_1);
        binTV_2=(TextView)v.findViewById(R.id.binary_2);
        binTV_4=(TextView)v.findViewById(R.id.binary_4);
        binTV_8=(TextView)v.findViewById(R.id.binary_8);
        binTV_16=(TextView)v.findViewById(R.id.binary_16);
        binTV_32=(TextView)v.findViewById(R.id.binary_32);
        binTV_64=(TextView)v.findViewById(R.id.binary_64);
        binTV_128=(TextView)v.findViewById(R.id.binary_128);
        binTV_256=(TextView)v.findViewById(R.id.binary_256);
        binTV_512=(TextView)v.findViewById(R.id.binary_512);
        binTV_1024=(TextView)v.findViewById(R.id.binary_1024);
        binTV_2048=(TextView)v.findViewById(R.id.binary_2048);
        binTV_4096=(TextView)v.findViewById(R.id.binary_4096);
        binTV_8192=(TextView)v.findViewById(R.id.binary_8192);
        binTV_16384=(TextView)v.findViewById(R.id.binary_16384);
        binTV_32768=(TextView)v.findViewById(R.id.binary_32768);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_16.setOnClickListener(this);
        btn_32.setOnClickListener(this);
        btn_64.setOnClickListener(this);
        btn_128.setOnClickListener(this);
        btn_256.setOnClickListener(this);
        btn_512.setOnClickListener(this);
        btn_1024.setOnClickListener(this);
        btn_2048.setOnClickListener(this);
        btn_4096.setOnClickListener(this);
        btn_8192.setOnClickListener(this);
        btn_16384.setOnClickListener(this);
        btn_32768.setOnClickListener(this);

        btn_1.setBackgroundColor(inActiveBtn);
        btn_2.setBackgroundColor(inActiveBtn);
        btn_4.setBackgroundColor(inActiveBtn);
        btn_8.setBackgroundColor(inActiveBtn);
        btn_16.setBackgroundColor(inActiveBtn);
        btn_32.setBackgroundColor(inActiveBtn);
        btn_64.setBackgroundColor(inActiveBtn);
        btn_128.setBackgroundColor(inActiveBtn);
        btn_256.setBackgroundColor(inActiveBtn);
        btn_512.setBackgroundColor(inActiveBtn);
        btn_1024.setBackgroundColor(inActiveBtn);
        btn_2048.setBackgroundColor(inActiveBtn);
        btn_4096.setBackgroundColor(inActiveBtn);
        btn_8192.setBackgroundColor(inActiveBtn);
        btn_16384.setBackgroundColor(inActiveBtn);
        btn_32768.setBackgroundColor(inActiveBtn);

        btn_1.setTextColor(inActiveBtnText);
        btn_2.setTextColor(inActiveBtnText);
        btn_4.setTextColor(inActiveBtnText);
        btn_8.setTextColor(inActiveBtnText);
        btn_16.setTextColor(inActiveBtnText);
        btn_32.setTextColor(inActiveBtnText);
        btn_64.setTextColor(inActiveBtnText);
        btn_128.setTextColor(inActiveBtnText);
        btn_256.setTextColor(inActiveBtnText);
        btn_512.setTextColor(inActiveBtnText);
        btn_1024.setTextColor(inActiveBtnText);
        btn_2048.setTextColor(inActiveBtnText);
        btn_4096.setTextColor(inActiveBtnText);
        btn_8192.setTextColor(inActiveBtnText);
        btn_16384.setTextColor(inActiveBtnText);
        btn_32768.setTextColor(inActiveBtnText);

        tv_1.setTextColor(inActiveTV);
        tv_2.setTextColor(inActiveTV);
        tv_4.setTextColor(inActiveTV);
        tv_8.setTextColor(inActiveTV);
        tv_16.setTextColor(inActiveTV);
        tv_32.setTextColor(inActiveTV);
        tv_64.setTextColor(inActiveTV);
        tv_128.setTextColor(inActiveTV);
        tv_256.setTextColor(inActiveTV);
        tv_512.setTextColor(inActiveTV);
        tv_1024.setTextColor(inActiveTV);
        tv_2048.setTextColor(inActiveTV);
        tv_4096.setTextColor(inActiveTV);
        tv_8192.setTextColor(inActiveTV);
        tv_16384.setTextColor(inActiveTV);
        tv_32768.setTextColor(inActiveTV);

        binTV_1.setTextColor(inActiveTV);
        binTV_2.setTextColor(inActiveTV);
        binTV_4.setTextColor(inActiveTV);
        binTV_8.setTextColor(inActiveTV);
        binTV_16.setTextColor(inActiveTV);
        binTV_32.setTextColor(inActiveTV);
        binTV_64.setTextColor(inActiveTV);
        binTV_128.setTextColor(inActiveTV);
        binTV_256.setTextColor(inActiveTV);
        binTV_512.setTextColor(inActiveTV);
        binTV_1024.setTextColor(inActiveTV);
        binTV_2048.setTextColor(inActiveTV);
        binTV_4096.setTextColor(inActiveTV);
        binTV_8192.setTextColor(inActiveTV);
        binTV_16384.setTextColor(inActiveTV);
        binTV_32768.setTextColor(inActiveTV);

        binTV_1.setText("0");
        binTV_2.setText("0");
        binTV_4.setText("0");
        binTV_8.setText("0");
        binTV_16.setText("0");
        binTV_32.setText("0");
        binTV_64.setText("0");
        binTV_128.setText("0");
        binTV_256.setText("0");
        binTV_512.setText("0");
        binTV_1024.setText("0");
        binTV_2048.setText("0");
        binTV_4096.setText("0");
        binTV_8192.setText("0");
        binTV_16384.setText("0");
        binTV_32768.setText("0");

        updateChar();

        return v;
    }


    public void input(String s) {
        InputConnection ic = getCurrentInputConnection();
        ic.commitText(s, 1);

    }


    public void delete() {
        getCurrentInputConnection().deleteSurroundingText(1, 0);
    }


    @Override
    public void onClick(View v) {
        InputConnection ic = getCurrentInputConnection();
        int id = v.getId();

        if (id == R.id.key_end) {
            ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
        }else if (id==R.id.btn_1){
            if (active_1){
                value-=1;
                btn_1.setBackgroundColor(inActiveBtn);
                btn_1.setTextColor(inActiveBtnText);
                tv_1.setTextColor(inActiveTV);
                active_1=false;
                binTV_1.setText("0");
                binTV_1.setTextColor(inActiveTV);
            }else{
                value+=1;
                btn_1.setBackgroundColor(activeBtn);
                btn_1.setTextColor(activeBtnText);
                tv_1.setTextColor(activeTV);
                active_1=true;
                binTV_1.setText("1");
                binTV_1.setTextColor(activeTV);
            }

        }
        else if (id==R.id.btn_2){
            if (active_2){
                value-=2;
                btn_2.setBackgroundColor(inActiveBtn);
                btn_2.setTextColor(inActiveBtnText);
                tv_2.setTextColor(inActiveTV);
                active_2=false;
                binTV_2.setText("0");
                binTV_2.setTextColor(inActiveTV);
            }else{
                value+=2;
                btn_2.setBackgroundColor(activeBtn);
                btn_2.setTextColor(activeBtnText);
                tv_2.setTextColor(activeTV);
                active_2=true;
                binTV_2.setText("1");
                binTV_2.setTextColor(activeTV);
            }

        }
        else if (id==R.id.btn_4){
            if (active_4){
                value-=4;
                btn_4.setBackgroundColor(inActiveBtn);
                btn_4.setTextColor(inActiveBtnText);
                tv_4.setTextColor(inActiveTV);
                active_4=false;
                binTV_4.setText("0");
                binTV_4.setTextColor(inActiveTV);
            }else{
                value+=4;
                btn_4.setBackgroundColor(activeBtn);
                btn_4.setTextColor(activeBtnText);
                tv_4.setTextColor(activeTV);
                active_4=true;
                binTV_4.setText("1");
                binTV_4.setTextColor(activeTV);
            }

        }
        else if (id==R.id.btn_8){
            if (active_8){
                value-=8;
                btn_8.setBackgroundColor(inActiveBtn);
                btn_8.setTextColor(inActiveBtnText);
                tv_8.setTextColor(inActiveTV);
                active_8=false;
                binTV_8.setText("0");
                binTV_8.setTextColor(inActiveTV);
            }else{
                value+=8;
                btn_8.setBackgroundColor(activeBtn);
                btn_8.setTextColor(activeBtnText);
                tv_8.setTextColor(activeTV);
                active_8=true;
                binTV_8.setText("1");
                binTV_8.setTextColor(activeTV);
            }

        }
        else if (id==R.id.btn_16){
            if (active_16){
                value-=16;
                btn_16.setBackgroundColor(inActiveBtn);
                btn_16.setTextColor(inActiveBtnText);
                tv_16.setTextColor(inActiveTV);
                active_16=false;
                binTV_16.setText("0");
                binTV_16.setTextColor(inActiveTV);
            }else{
                value+=16;
                btn_16.setBackgroundColor(activeBtn);
                btn_16.setTextColor(activeBtnText);
                tv_16.setTextColor(activeTV);
                active_16=true;
                binTV_16.setText("1");
                binTV_16.setTextColor(activeTV);
            }

        }
        else if (id==R.id.btn_32){
            if (active_32){
                value-=32;
                btn_32.setBackgroundColor(inActiveBtn);
                btn_32.setTextColor(inActiveBtnText);
                tv_32.setTextColor(inActiveTV);
                active_32=false;
                binTV_32.setText("0");
                binTV_32.setTextColor(inActiveTV);
            }else{
                value+=32;
                btn_32.setBackgroundColor(activeBtn);
                btn_32.setTextColor(activeBtnText);
                tv_32.setTextColor(activeTV);
                active_32=true;
                binTV_32.setText("1");
                binTV_32.setTextColor(activeTV);
            }

        }
        else if (id==R.id.btn_64){
            if (active_64){
                value-=64;
                btn_64.setBackgroundColor(inActiveBtn);
                btn_64.setTextColor(inActiveBtnText);
                tv_64.setTextColor(inActiveTV);
                active_64=false;
                binTV_64.setText("0");
                binTV_64.setTextColor(inActiveTV);
            }else{
                value+=64;
                btn_64.setBackgroundColor(activeBtn);
                btn_64.setTextColor(activeBtnText);
                tv_64.setTextColor(activeTV);
                active_64=true;
                binTV_64.setText("1");
                binTV_64.setTextColor(activeTV);
            }

        }
        else if (id==R.id.btn_128){
            if (active_128){
                value-=128;
                btn_128.setBackgroundColor(inActiveBtn);
                btn_128.setTextColor(inActiveBtnText);
                tv_128.setTextColor(inActiveTV);
                active_128=false;
                binTV_128.setText("0");
                binTV_128.setTextColor(inActiveTV);
            }else{
                value+=128;
                btn_128.setBackgroundColor(activeBtn);
                btn_128.setTextColor(activeBtnText);
                tv_128.setTextColor(activeTV);
                active_128=true;
                binTV_128.setText("1");
                binTV_128.setTextColor(activeTV);
            }

        }
        else if (id==R.id.btn_256){
            if (active_256){
                value-=256;
                btn_256.setBackgroundColor(inActiveBtn);
                btn_256.setTextColor(inActiveBtnText);
                tv_256.setTextColor(inActiveTV);
                active_256=false;
                binTV_256.setText("0");
                binTV_256.setTextColor(inActiveTV);
            }else{
                value+=256;
                btn_256.setBackgroundColor(activeBtn);
                btn_256.setTextColor(activeBtnText);
                tv_256.setTextColor(activeTV);
                active_256=true;
                binTV_256.setText("1");
                binTV_256.setTextColor(activeTV);
            }

        }
        else if (id==R.id.btn_512){
            if (active_512){
                value-=512;
                btn_512.setBackgroundColor(inActiveBtn);
                btn_512.setTextColor(inActiveBtnText);
                tv_512.setTextColor(inActiveTV);
                active_512=false;
                binTV_512.setText("0");
                binTV_512.setTextColor(inActiveTV);
            }else{
                value+=512;
                btn_512.setBackgroundColor(activeBtn);
                btn_512.setTextColor(activeBtnText);
                tv_512.setTextColor(activeTV);
                active_512=true;
                binTV_512.setText("1");
                binTV_512.setTextColor(activeTV);
            }

        }
        else if (id==R.id.btn_1024){
            if (active_1024){
                value-=1024;
                btn_1024.setBackgroundColor(inActiveBtn);
                btn_1024.setTextColor(inActiveBtnText);
                tv_1024.setTextColor(inActiveTV);
                active_1024=false;
                binTV_1024.setText("0");
                binTV_1024.setTextColor(inActiveTV);
            }else{
                value+=1024;
                btn_1024.setBackgroundColor(activeBtn);
                btn_1024.setTextColor(activeBtnText);
                tv_1024.setTextColor(activeTV);
                active_1024=true;
                binTV_1024.setText("1");
                binTV_1024.setTextColor(activeTV);
            }

        }
        else if (id==R.id.btn_2048){
            if (active_2048){
                value-=2048;
                btn_2048.setBackgroundColor(inActiveBtn);
                btn_2048.setTextColor(inActiveBtnText);
                tv_2048.setTextColor(inActiveTV);
                active_2048=false;
                binTV_2048.setText("0");
                binTV_2048.setTextColor(inActiveTV);
            }else{
                value+=2048;
                btn_2048.setBackgroundColor(activeBtn);
                btn_2048.setTextColor(activeBtnText);
                tv_2048.setTextColor(activeTV);
                active_2048=true;
                binTV_2048.setText("1");
                binTV_2048.setTextColor(activeTV);
            }

        }
        else if (id==R.id.btn_4096){
            if (active_4096){
                value-=4096;
                btn_4096.setBackgroundColor(inActiveBtn);
                btn_4096.setTextColor(inActiveBtnText);
                tv_4096.setTextColor(inActiveTV);
                active_4096=false;
                binTV_4096.setText("0");
                binTV_4096.setTextColor(inActiveTV);
            }else{
                value+=4096;
                btn_4096.setBackgroundColor(activeBtn);
                btn_4096.setTextColor(activeBtnText);
                tv_4096.setTextColor(activeTV);
                active_4096=true;
                binTV_4096.setText("1");
                binTV_4096.setTextColor(activeTV);
            }

        }
        else if (id==R.id.btn_8192){
            if (active_8192){
                value-=8192;
                btn_8192.setBackgroundColor(inActiveBtn);
                btn_8192.setTextColor(inActiveBtnText);
                tv_8192.setTextColor(inActiveTV);
                active_8192=false;
                binTV_8192.setText("0");
                binTV_8192.setTextColor(inActiveTV);
            }else{
                value+=8192;
                btn_8192.setBackgroundColor(activeBtn);
                btn_8192.setTextColor(activeBtnText);
                tv_8192.setTextColor(activeTV);
                active_8192=true;
                binTV_8192.setText("1");
                binTV_8192.setTextColor(activeTV);
            }

        }
        else if (id==R.id.btn_16384){
            if (active_16384){
                value-=16384;
                btn_16384.setBackgroundColor(inActiveBtn);
                btn_16384.setTextColor(inActiveBtnText);
                tv_16384.setTextColor(inActiveTV);
                active_16384=false;
                binTV_16384.setText("0");
                binTV_16384.setTextColor(inActiveTV);
            }else{
                value+=16384;
                btn_16384.setBackgroundColor(activeBtn);
                btn_16384.setTextColor(activeBtnText);
                tv_16384.setTextColor(activeTV);
                active_16384=true;
                binTV_16384.setText("1");
                binTV_16384.setTextColor(activeTV);
            }

        }
        else if (id==R.id.btn_32768){
            if (active_32768){
                value-=32768;
                btn_32768.setBackgroundColor(inActiveBtn);
                btn_32768.setTextColor(inActiveBtnText);
                tv_32768.setTextColor(inActiveTV);
                active_32768=false;
                binTV_32768.setText("0");
                binTV_32768.setTextColor(inActiveTV);
            }else{
                value+=32768;
                btn_32768.setBackgroundColor(activeBtn);
                btn_32768.setTextColor(activeBtnText);
                tv_32768.setTextColor(activeTV);
                active_32768=true;
                binTV_32768.setText("1");
                binTV_32768.setTextColor(activeTV);
            }

        }else if (id==R.id.preview_char){
            input(Character.toString((char)value));
        }
        updateChar();
    }

    private void updateChar(){
        confirmBtn.setText(Character.toString((char)value));
        codePointPreview.setText("U+" + String.format("%04X", value));


        if (active_1) btn_1.setText(Character.toString((char)(value-1)));
        else btn_1.setText(Character.toString((char)(value+1)));
        if (active_2) btn_2.setText(Character.toString((char)(value-2)));
        else btn_2.setText(Character.toString((char)(value+2)));
        if (active_4) btn_4.setText(Character.toString((char)(value-4)));
        else btn_4.setText(Character.toString((char)(value+4)));
        if (active_8) btn_8.setText(Character.toString((char)(value-8)));
        else btn_8.setText(Character.toString((char)(value+8)));
        if (active_16) btn_16.setText(Character.toString((char)(value-16)));
        else btn_16.setText(Character.toString((char)(value+16)));
        if (active_32) btn_32.setText(Character.toString((char)(value-32)));
        else btn_32.setText(Character.toString((char)(value+32)));
        if (active_64) btn_64.setText(Character.toString((char)(value-64)));
        else btn_64.setText(Character.toString((char)(value+64)));
        if (active_128) btn_128.setText(Character.toString((char)(value-128)));
        else btn_128.setText(Character.toString((char)(value+128)));
        if (active_256) btn_256.setText(Character.toString((char)(value-256)));
        else btn_256.setText(Character.toString((char)(value+256)));
        if (active_512) btn_512.setText(Character.toString((char)(value-512)));
        else btn_512.setText(Character.toString((char)(value+512)));
        if (active_1024) btn_1024.setText(Character.toString((char)(value-1024)));
        else btn_1024.setText(Character.toString((char)(value+1024)));
        if (active_2048) btn_2048.setText(Character.toString((char)(value-2048)));
        else btn_2048.setText(Character.toString((char)(value+2048)));
        if (active_4096) btn_4096.setText(Character.toString((char)(value-4096)));
        else btn_4096.setText(Character.toString((char)(value+4096)));
        if (active_8192) btn_8192.setText(Character.toString((char)(value-8192)));
        else btn_8192.setText(Character.toString((char)(value+8192)));
        if (active_16384) btn_16384.setText(Character.toString((char)(value-16384)));
        else btn_16384.setText(Character.toString((char)(value+16384)));
        if (active_32768) btn_32768.setText(Character.toString((char)(value-32768)));
        else btn_32768.setText(Character.toString((char)(value+32768)));
    }

    Handler mHandler = null;
    Runnable del_action = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this, 50);
            delete();
        }
    };
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId()==R.id.key_del) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(del_action, 50);
                    break;
                case MotionEvent.ACTION_UP:
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(del_action);
                    mHandler = null;
                    break;
            }
        }
        return false;
    }

}
