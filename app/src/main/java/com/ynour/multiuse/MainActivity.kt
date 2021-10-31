package com.ynour.multiuse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.ynour.multiuse.databinding.ActivityMainBinding

const val EXTRA_STRING ="com.ynour.multiuse"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.setOnClickListener{
            editText = binding.searchBar
            val url =editText.text.toString()
            val intent = Intent(this@MainActivity, WebViewActivity::class.java).apply {
                putExtra(EXTRA_STRING, url)
            }
            startActivity(intent)
        }
        binding.musicPlayer.setOnClickListener{
            val intent = Intent(this@MainActivity, MusicPlayerActivity::class.java)
            startActivity(intent)
        }

        binding.videoPlayer.setOnClickListener{
            val intent = Intent(this@MainActivity, VideoPlayerActivity::class.java)
            startActivity(intent)
        }

    }
}