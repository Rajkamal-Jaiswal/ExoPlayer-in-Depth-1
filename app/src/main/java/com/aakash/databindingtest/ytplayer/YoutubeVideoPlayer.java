package com.aakash.databindingtest.ytplayer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aakash.databindingtest.R;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Util;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

public class YoutubeVideoPlayer extends AppCompatActivity {


    PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;
    boolean playWhenReady = true;
    int currentWindow = 0;
    long playBackPosition = 0;


    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initPlayer();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if ((Util.SDK_INT <= 24 || simpleExoPlayer == null)) {
            hideSystemUi();
        }
    }

    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        );
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }

    private void releasePlayer() {

        if (simpleExoPlayer != null) {
            playWhenReady = simpleExoPlayer.getPlayWhenReady();
            playBackPosition = simpleExoPlayer.getCurrentPosition();
            currentWindow = simpleExoPlayer.getCurrentWindowIndex();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_video_player);
        playerView = findViewById(R.id.video_view);
        initPlayer();
    }

    private void initPlayer() {
        simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(simpleExoPlayer);
        playYouTubeVideo("https://www.youtube.com/watch?v=Ya25t4B9xTE");
    }

    private void playYouTubeVideo(String s) {
        new YouTubeExtractor(this) {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected void onExtractionComplete(@Nullable SparseArray<YtFile> ytFiles, @Nullable VideoMeta videoMeta) {
                if (ytFiles!=null){
                    @SuppressLint("StaticFieldLeak") int videoTag=137;  //Video tag for 1080p MP4(You can debug ytFiles to get Tag)
                    @SuppressLint("StaticFieldLeak") int audioTag=140;  //For MP4A
                    @SuppressLint("StaticFieldLeak") MediaSource audiosource=new ProgressiveMediaSource.Factory(new DefaultHttpDataSource.Factory())
                            .createMediaSource(MediaItem.fromUri(ytFiles.get(audioTag).getUrl()));
                    @SuppressLint("StaticFieldLeak") MediaSource vediosource=new ProgressiveMediaSource.Factory(new DefaultHttpDataSource.Factory())
                            .createMediaSource(MediaItem.fromUri(ytFiles.get(videoTag).getUrl()));


                    simpleExoPlayer.setMediaSource(new MergingMediaSource(
                                    true,
                                    vediosource,
                                    audiosource),
                            true);
                    simpleExoPlayer.prepare();
                    simpleExoPlayer.setPlayWhenReady(playWhenReady);
                    simpleExoPlayer.seekTo(currentWindow,playBackPosition);
                }

            }
        }.extract(s, false, true);
    }
}