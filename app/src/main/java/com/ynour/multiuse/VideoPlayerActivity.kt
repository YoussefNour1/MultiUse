package com.ynour.multiuse


import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.ynour.multiuse.databinding.ActivityVideoPlayerBinding

class VideoPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoPlayerBinding
    private lateinit var mediaController: MediaController
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.apply {
            setMediaController(mediaController)
            setVideoURI(Uri.parse("android.resource://$packageName/${R.raw.video}"))
            requestFocus()
            start()
        }


    }
}