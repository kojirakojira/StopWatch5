package jp.co.sakuma.stopwatch5;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{

    // 秒計算
    private Timer timer;
    private Timer mTimer;

    private TimeCalculator timeCal = new TimeCalculator();

    private Handler handler = new Handler();
    private Handler mHandler = new Handler();

    // 表示用のTextView
    private TextView timerText;
    private TextView mTimerText;

    // カウント再開時の秒数が進むまでのラグ
    private int delay = 1000;

    // 各ボタンの定義
    Button startButton;
    Button stopButton;
    Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.start_button);
        stopButton = findViewById(R.id.stop_button);
        resetButton = findViewById(R.id.reset_button);

        timerText = findViewById(R.id.hyoujiran);
        mTimerText = findViewById(R.id.mhyoujiran);

        start();
        stop();
        reset();
    }

    // 開始ボタン押下時の処理
    private void start() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                resetButton.setEnabled(true);
                timer = new Timer();
                mTimer = new Timer();
                timeCul();
            }
        });
    }


    // 停止ボタン押下時の処理
    private void stop() {

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                mTimer.cancel();

                // 再開時の遅延時間を計算
                delay = (1000 - timeCal.getMsec() * 10);

                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                resetButton.setEnabled(true);
            }
        });
    }

    // リセットボタン押下時の処理
    private void reset() {

        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                timer.cancel();
                mTimer.cancel();
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                resetButton.setEnabled(false);
                // 初期化
                timeCal = new TimeCalculator();
                delay = 1000;

                timerText.setText("00:00:00.");
                mTimerText.setText("00");

            }
        });
    }

    // スケジュールに従って計算
    private void timeCul() {

        // 秒以上の計算(1000ミリ秒に一度実行)
        timer.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        timerText.setText(timeCal.time());
                    }
                });
            }
        } ,delay ,1000);

        // 秒より下位の計算(10ミリ秒に一度実行)
        mTimer.schedule(new TimerTask() {
            public void run() {
                mHandler.post(new Runnable() {
                    public void run() {
                        mTimerText.setText(timeCal.msecTime());
                    }
                });
            }
        }, 10, 10);
    }
}
