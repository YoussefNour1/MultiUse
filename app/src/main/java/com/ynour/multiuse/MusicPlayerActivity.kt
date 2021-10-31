package com.ynour.multiuse

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.ynour.multiuse.databinding.ActivityMusicPlayerBinding


class MusicPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMusicPlayerBinding
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityMusicPlayerBinding.inflate(layoutInflater)
        binding.imageView.clipToOutline = true
        setContentView(binding.root)
        mediaPlayer = MediaPlayer.create(this, R.raw.song)
        binding.songlen.text = convertTime(mediaPlayer.duration)
        "00:00".also { binding.progress.text = it }
        binding.playButton.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                binding.playButton.setImageResource(R.drawable.ic_pause)
                initialListenSeek()
            } else {
                binding.playButton.setImageResource(R.drawable.ic_play)
                mediaPlayer.pause()
            }
        }
        binding.replay.setOnClickListener {
            mediaPlayer.seekTo(0)
        }

        binding.skipButton.setOnClickListener{
            mediaPlayer.seekTo(mediaPlayer.currentPosition+10000)
        }
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mediaPlayer.seekTo(progress)

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

    }

    private fun initialListenSeek() {
        binding.seekBar.max = mediaPlayer.duration
        val handler = Looper.myLooper()?.let { Handler(it) }
        handler?.postDelayed(object : Runnable {
            override fun run() {
                try {
                    binding.seekBar.progress = mediaPlayer.currentPosition
                    binding.progress.text = convertTime(mediaPlayer.currentPosition)
                    handler.postDelayed(this, 1000)
                } catch (e: Exception) {
                    binding.seekBar.progress = 0
                }
            }
        }, 0)
    }

    override fun onRestart() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
        super.onRestart()
    }

    override fun onPause() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            binding.seekBar.progress = mediaPlayer.currentPosition
        }
        super.onPause()
    }



    private fun convertTime(value: Int): String {
        val audioTime: String
        val hrs = value / 3600000
        val mns = value / 60000 % 60000
        val scs = value % 60000 / 1000
        audioTime = if (hrs > 0) {
            String.format("%02d:%02d:%02d", hrs, mns, scs)
        } else {
            String.format("%02d:%02d", mns, scs)
        }
        return audioTime
    }
}