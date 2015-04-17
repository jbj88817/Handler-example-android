package com.bojie.handler_example;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.widget.ProgressBar;


public class MainActivity extends ActionBarActivity {

    Thread mThread;
    Handler mHandler;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mThread = new Thread(new MyThread());
        mThread.start();
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                mProgressBar.setProgress(msg.arg1);
            }
        };
    }

    class MyThread implements Runnable {

        @Override
        public void run() {
            Message message = Message.obtain();

            for(int i = 0; i < 100; i++) {
                message.arg1 = i;
                mHandler.dispatchMessage(message);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
