package com.example.administrator.myapplication01.activity;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.util.Util;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class VideoActivity extends Activity implements SurfaceHolder.Callback {
    private List<String> suffix_list = new ArrayList<String>();
    private List<String> file_list = new ArrayList<String>();
    private SurfaceView surface;
    private Button video_bt1;
    private Button video_bt2;
    private Button video_bt3;

    MediaPlayer player;
    SurfaceHolder surfaceHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_layout);
        initView();
        init();
    }


    private void initView() {
        surface = (SurfaceView) findViewById(R.id.surface);
        video_bt1 = (Button) findViewById(R.id.video_bt1);
        video_bt2 = (Button) findViewById(R.id.video_bt2);
        video_bt3 = (Button) findViewById(R.id.video_bt3);
        video_bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();
            }
        });
        video_bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
            }
        });
        video_bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
            }
        });

    }

    private void init() {
        surfaceHolder = surface.getHolder();   //SurfaceHolder是SurfaceView的控制接口
        surfaceHolder.addCallback(this);//因为这个类实现了SurfaceHolder.Callback接口，所以回调参数直接this
        surfaceHolder.setFixedSize(320, 220);//显示的分辨率,不设置为视频默认
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); //Surface类型
        suffix_list.add("mp4");
        suffix_list.add("mkv");
        suffix_list.add("avi");
        suffix_list.add("3gp");
        suffix_list.add("rmvb");
        suffix_list.add("wmv");
        suffix_list.add("flv");
        suffix_list.add("f4v");
        suffix_list.add("asf");
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
//必须在surface创建后才能初始化MediaPlayer,否则不会显示图像
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setDisplay(surfaceHolder);

        //设置显示视频显示在SurfaceView上
        try {

            //  player.setDataSource("//mmt//shell//emulated//0//Download//other//asd.mkv");
            player.setDataSource("//storage//emulated//0//Download//other//asd.mkv");
            player.prepare();

        } catch (Exception e) {
            e.printStackTrace();
            Util.toast(VideoActivity.this, "资源未找到");
            file_list.clear();
            final File rootFile = Environment.getExternalStorageDirectory();
            new Thread(new Runnable() {
                @Override
                public void run() {

                    getVideoFile(rootFile);
                    for(String str:file_list){
                        System.out.println(str);
                    }
                }
            }).start();
            for(File file:rootFile.listFiles()){
                System.out.println("############"+file.toString());
            }



            File file_kv = new File("/storage/emulated/0/Download/other/asd.mkv");
            if (file_kv.isFile()) {
                System.out.println("文件存在");
            }
            File file_k = new File("//storage//emulated//0//Download//other//asd.mkv");
            if (file_k.isFile()) {
                System.out.println("文件存在");
            }
            File file_ = new File("/mmt/shell/emulated/0/Download/other/asd.mkv");
            if (file_.isFile()) {
                System.out.println("文件存在");
            }
            File file_2 = new File("//mmt//shell//emulated//0//Download//other//asd.mkv");
            if (file_2.isFile()) {
                System.out.println("文件存在");
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player.isPlaying()) {
            player.stop();
        }
        player.release();
        //Activity销毁时停止播放，释放资源。不做这个操作，即使退出还是能听到视频播放的声音
    }


    public void getVideoFile(File rootFile) {
        File[] files = rootFile.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    System.out.println("**************"+pathname.toString());
                    return true;
                }
                String file = pathname.getName();
//                if (file.length() > 4) {
                    System.out.println("**************"+pathname.toString());
                    String video_suffix = file.substring(file.length() - 3);
                    if (suffix_list.contains(video_suffix)) {
                        file_list.add(pathname.toString());
                        return false;
                    }
//                }
                return false;
            }
        });
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getVideoFile(file);
                }
            }
        }


    }
}





