package khan.uz.mytasbeeh

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import khan.uz.mytasbeeh.databinding.ActivityMainBinding

@Suppress("LABEL_NAME_CLASH")
class MainActivity : AppCompatActivity() {


    private var count: Int = 0
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    private var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView2.setImageResource(R.drawable.music_note_24)
        binding.res.setImageResource(R.drawable.ic_baseline_refresh_24)
        val vibe: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        mediaPlayer = MediaPlayer.create(this, R.raw.mixkit)
        binding.button.setOnClickListener {

            count++
            vibe.vibrate(100)



            binding.progressBar.progress = count
            binding.textView.text = count.toString()

            mediaPlayer.start()
        }





        binding.imageView2.setOnClickListener {

            i++

            if (i == 1) {
                mediaPlayer.setVolume(0.0F, 0.0F)
                mediaPlayer.pause()
                val amanager = getSystemService(AUDIO_SERVICE) as AudioManager
                amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true)
                binding.imageView2.setImageResource(R.drawable.music_off_24)
            } else if (i == 2) {
                mediaPlayer.setVolume(1F, 1F);
                val amanager = getSystemService(AUDIO_SERVICE) as AudioManager
                amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false)
                binding.imageView2.setImageResource(R.drawable.music_note_24)
                i = 0
            }


        }


        binding.res.setOnClickListener {

            count = 0
            binding.progressBar.progress = count
            binding.textView.text = count.toString()

        }


    }

    fun music() {
        mediaPlayer = MediaPlayer.create(this, R.raw.mixkit)

        if (mediaPlayer.isPlaying) {

        }

    }


}