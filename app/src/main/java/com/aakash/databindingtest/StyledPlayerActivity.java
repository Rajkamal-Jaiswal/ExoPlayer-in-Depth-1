package com.aakash.databindingtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class StyledPlayerActivity extends AppCompatActivity {

    StyledPlayerView playerView;

    SimpleExoPlayer simpleExoPlayer;//1st

    String url1 = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_styled_player);
        simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();
        playerView = findViewById(R.id.exoPlayerView);

        playerView.setPlayer(simpleExoPlayer);


        /* Note
         *
         * In ExoPlayer every piece of media is represented by a MediaItem. To play a piece of media you need to build a corresponding MediaItem, add it to the player, prepare the player, and call play to start the playback:
         *
         * */
//        Now we need media item
        // Build the media item.
        MediaItem mediaItem = MediaItem.fromUri(url1);
// Set the media item to be played.
        simpleExoPlayer.addMediaItem(mediaItem);
// Prepare the player.
        simpleExoPlayer.prepare();
// Start the playback.
        simpleExoPlayer.play();

        /*
         *
         * ExoPlayer supports playlists directly, so it’s possible to prepare the player with multiple media items to be played one after the other:
         * */
        /*
        * LIKE
        *
        * // Build the media items.
MediaItem firstItem = MediaItem.fromUri(firstVideoUri);
MediaItem secondItem = MediaItem.fromUri(secondVideoUri);
// Add the media items to be played.
player.addMediaItem(firstItem);
player.addMediaItem(secondItem);
// Prepare the player.
player.prepare();
// Start the playback.
player.play();
*
        * */


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        simpleExoPlayer.release();
    }
}