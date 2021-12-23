package cn.jay.tomatoalarm;

import android.content.pm.ActivityInfo;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.jay.tomatoalarm.service.AlarmService;
import cn.jay.tomatoalarm.utils.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button bt_start;
    private TextView tv_time;
    private boolean isRunning = false;
    private Vibrator vibrator;
    private AlarmService alarmService;
    public static final long TOMATO_TIME_LENGTH_MILLIS = 25*60*1000;


    @Override
    public void initLocalData() {

    }

    @Override
    public void initView() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 设置状态栏颜色
        getWindow().setStatusBarColor(getColor(R.color.bg));

        bt_start = (Button) findViewById(R.id.bt_start);
        bt_start.setOnClickListener(this);
        tv_time = (TextView) findViewById(R.id.tv_time);

        alarmService = new AlarmService("AlarmService");
        alarmService.setBt_start(bt_start);
        alarmService.setTv_time(tv_time);
        vibrator = (Vibrator) MainActivity.this.getSystemService(MainActivity.VIBRATOR_SERVICE);
        alarmService.setVibrator(vibrator);
        alarmService.setContext(this);

    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        if (bt_start.equals(view)) {
            if(alarmService.isRunning())
                alarmService.stop();
            else
                alarmService.start();
        }
    }


}