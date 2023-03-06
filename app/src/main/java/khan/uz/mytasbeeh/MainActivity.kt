package khan.uz.mytasbeeh

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import khan.uz.mytasbeeh.databinding.ActivityMainBinding

@Suppress("LABEL_NAME_CLASH")
class MainActivity : AppCompatActivity() {


    private var count: Int = 0
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView2.setImageResource(R.drawable.music_note_24)
        binding.res.setImageResource(R.drawable.ic_baseline_refresh_24)
        val vibe: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        binding.button.setOnClickListener {
            count++
            vibe.vibrate(200)
            mediaPlayer = MediaPlayer.create(this, R.raw.mixkit)
            binding.progressBar.progress = count
            binding.textView.text = count.toString()


            mediaPlayer.start()

        }

        binding.res.setOnClickListener {
            count = 0
            binding.textView.text = count.toString()
        }

        binding.imageView2.setOnClickListener {


            binding.imageView2.isSoundEffectsEnabled = false
            binding.imageView2.setImageResource(R.drawable.music_off_24)

        }


    }


}