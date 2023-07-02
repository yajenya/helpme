package com.helpme.exoplayersample

import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.helpme.exoplayersample.databinding.ActivityRadioPlayerBinding

class RadioPlayerActivity : AppCompatActivity() {

    private lateinit var simpleExoPlayer: ExoPlayer

    private lateinit var binding: ActivityRadioPlayerBinding

    override fun onResume() {
        super.onResume()
        binding = ActivityRadioPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediaDataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(applicationContext)

        val mediaSource = ProgressiveMediaSource.Factory(mediaDataSourceFactory)
            .createMediaSource(MediaItem.fromUri(RADIO_URL))

        val mediaSourceFactory = DefaultMediaSourceFactory(mediaDataSourceFactory)

        simpleExoPlayer = ExoPlayer.Builder(this)
            .setMediaSourceFactory(mediaSourceFactory)
            .build()

        simpleExoPlayer.addMediaSource(mediaSource)
        simpleExoPlayer.prepare()
        initListeners()
    }


    fun initListeners() {
        binding.btnStart.setOnClickListener {
            simpleExoPlayer.playWhenReady = true
        }

        binding.btnStop.setOnClickListener {
            simpleExoPlayer.playWhenReady = false
        }
    }

    object {
        const val RADIO_URL = "http://kastos.cdnstream.com/1345_32"
    }
}
