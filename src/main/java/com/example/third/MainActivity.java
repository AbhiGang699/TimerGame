package com.example.third;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {
    int sn,sd,ra,rb,ai;
    Button a,b,c,d,go,go2;
    TextView score,time,ques,s,t,sc;
    CountDownTimer cdt;
    ArrayList<Integer> ar;

    public void init(){
        score.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);
        ques.setVisibility(View.INVISIBLE);
        a.setVisibility(View.INVISIBLE);
        b.setVisibility(View.INVISIBLE);
        c.setVisibility(View.INVISIBLE);
        d.setVisibility(View.INVISIBLE);
        s.setVisibility(View.INVISIBLE);
        t.setVisibility(View.INVISIBLE);
        go.setVisibility(View.VISIBLE);
    }
    public void setQ(){
        Random rand=new Random();
        ra=rand.nextInt(100)+1;
        rb=rand.nextInt(200)+1;
        ques.setText(Integer.toString(ra)+" + "+Integer.toString(rb)+" = ?");
        ar.add(ra+rb);ar.add(rand.nextInt(300)+1);ar.add(rand.nextInt(300)+1);ar.add(rand.nextInt(300)+1);
        for(int i=0;i<4;i++){
            int k=rand.nextInt(ar.size());
            if(i==0)    a.setText(Integer.toString(ar.get(k)));
            if(i==1)    b.setText(Integer.toString(ar.get(k)));
            if(i==2)    c.setText(Integer.toString(ar.get(k)));
            if(i==3)    d.setText(Integer.toString(ar.get(k)));
            if(ar.get(k)==ra+rb){
                if(i==0)    ai=R.id.button19;
                if(i==1)    ai=R.id.button20;
                if(i==2)    ai=R.id.button21;
                if(i==3)    ai=R.id.button22;
            }
            ar.remove(k);
        }
    }
    public void ans(View v){
        boolean flag=false;
        int id=v.getId(),ans=ra+rb;
        if(id==ai)  flag=true;
        if(flag)
            sn++;
        sd++;
        score.setText(Integer.toString(sn)+" /"+Integer.toString(sd));
        setQ();
    }
    public void timerstart(){
        //setContentView(R.layout.activity_main);
        sn=0;
        sd=0;
        score.setText("__/__");
        time.setText("01:00");
        setQ();
        score.setVisibility(View.VISIBLE);
        time.setVisibility(View.VISIBLE);
        ques.setVisibility(View.VISIBLE);
        a.setVisibility(View.VISIBLE);
        b.setVisibility(View.VISIBLE);
        c.setVisibility(View.VISIBLE);
        d.setVisibility(View.VISIBLE);
        s.setVisibility(View.VISIBLE);
        t.setVisibility(View.VISIBLE);
        go.setVisibility(View.INVISIBLE);
        cdt=new CountDownTimer(10000+100,1000){
            public void onTick(long lft){
                int m=(int)lft/60000;
                int s=(int)(lft/1000)%60;
                String min=Integer.toString(m);
                String sec=Integer.toString(s);
                if(m<=9)    min="0"+min;
                if(s<=9)    sec="0"+sec;
                time.setText(min+"min "+sec+"sec");
            }
            public void onFinish(){
                init();
                go.setVisibility(View.INVISIBLE);
                go2.setAlpha(0);
                go2.setVisibility(View.VISIBLE);
                sc.setTranslationX(-1000f);sc.setTranslationY(-1000f);
                sc.setVisibility(View.VISIBLE);
                sc.setText("Wohoooo !!\nScore : "+Integer.toString(sn)+" / "+Integer.toString(sd));
                sc.animate().rotation(360*2).translationXBy(1000f).translationYBy(1000f).setDuration(500);
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                go2.animate().alpha(1f).setDuration(1000);
            }
        }.start();

    }
    public void go(View v){
        if(v.getId()==R.id.button){
            go2.setVisibility(View.INVISIBLE);
            sc.setVisibility(View.INVISIBLE);
        }
        timerstart();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a=(Button)findViewById(R.id.button19);
        b=(Button)findViewById(R.id.button20);
        c=(Button)findViewById(R.id.button21);
        d=(Button)findViewById(R.id.button22);
        go2=(Button)findViewById(R.id.button);
        go=(Button)findViewById(R.id.go);
        sc=(TextView)findViewById(R.id.textView3);
        score=(TextView)findViewById(R.id.score);
        ques=(TextView)findViewById(R.id.ques);
        time=(TextView)findViewById(R.id.timer);
        s=(TextView)findViewById(R.id.textView2);
        t=(TextView)findViewById(R.id.textView);
        ar=new ArrayList<Integer>();
    }
}
