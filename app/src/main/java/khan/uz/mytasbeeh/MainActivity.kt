package khan.uz.mytasbeeh

import android.content.Context
import android.media.AudioAttributes

import android.media.MediaPlayer
import android.media.SoundPool
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
    private var soundPool: SoundPool? = null
    private var soundPlayer: Int = 0
    private var isTap = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSound.setImageResource(R.drawable.music_note_24)
        binding.vibrationBtn.setImageResource(R.drawable.noun_vibrate_on)
        binding.res.setImageResource(R.drawable.ic_baseline_refresh_24)

        val vibe: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        val audioAttributes = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()

        soundPool = SoundPool.Builder().setMaxStreams(2).setAudioAttributes(audioAttributes).build()


        soundPlayer = soundPool!!.load(this, R.raw.tap, 1)

        binding.button.setOnClickListener {

            if (isTap) {
                soundPool?.play(soundPlayer, 1f, 1f, 1, 0, 1f)
            }
               if (isTap) {
                   vibe.vibrate(100)
            }

            count++

            binding.progressBar.progress = count
            binding.textView.text = count.toString()


        }


        binding.btnSound.setOnClickListener {

            i++

            if (i == 1) {
                isTap =false
                soundPool?.stop(soundPlayer)


                binding.btnSound.setImageResource(R.drawable.music_off_24)
            } else if (i == 2) {


                isTap = true
                binding.btnSound.setImageResource(R.drawable.music_note_24)
                i = 0
            }


        }
        binding.vibrationBtn.setOnClickListener {

            i++

            if (i == 1) {
                isTap =false
               vibe.vibrate(0)
                binding.vibrationBtn.setImageResource(R.drawable.noun_vibration_off)
            } else if (i == 2) {
                isTap = true
                binding.vibrationBtn.setImageResource(R.drawable.noun_vibrate_on)
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