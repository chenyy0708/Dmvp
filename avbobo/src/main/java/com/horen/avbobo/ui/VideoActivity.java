package com.horen.avbobo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.horen.avbobo.R;
import com.horen.avbobo.glide.GlideUtil;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * @author :ChenYangYi
 * @date :2018/06/15/12:38
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class VideoActivity extends GSYBaseActivityDetail<StandardGSYVideoPlayer> {

    private StandardGSYVideoPlayer detailPlayer;

    public static  void startActivity(Context context, String imageurl, String videoUrl) {
        Intent intent = new Intent();
        intent.setClass(context, VideoActivity.class);
        intent.putExtra("imageurl", imageurl);
        intent.putExtra("videoUrl", videoUrl);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        detailPlayer = (StandardGSYVideoPlayer) findViewById(R.id.detail_player);
        initVideoBuilderMode();

    }

    @Override
    public StandardGSYVideoPlayer getGSYVideoPlayer() {
        return detailPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //内置封面可参考SampleCoverVideo
        ImageView imageView = new ImageView(this);
        GlideUtil.loadUrl(this, getIntent().getStringExtra("imageurl"), imageView);
        return new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setUrl(getIntent().getStringExtra("videoUrl"))
                .setCacheWithPlay(true)
                .setVideoTitle(" ")
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setSeekRatio(1);
    }

    @Override
    public void clickForFullScreen() {

    }

    @Override
    public boolean getDetailOrientationRotateAuto() {
        return true;
    }
}
