package khan.uz.mytasbeeh

import android.content.Context
import android.media.AudioAttributes

import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.os.Vibrator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import khan.uz.mytasbeeh.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private var count: Int = 0
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    private var s = 0
    private var v = 0
    private var soundPool: SoundPool? = null
    private var soundPlayer: Int = 0
    private var isTapSound = true
    private var isTapVibrator = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // binding.progressBar.max = 99
        val animBtn = AnimationUtils.loadAnimation(this, R.anim.anim_btn)
        val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_image)


        binding.sound.setImageResource(R.drawable.noun_sound_on_3992693)
        binding.vib.setImageResource(R.drawable.noun_vibration_2310451)
        binding.ref.setImageResource(R.drawable.ic_baseline_refresh_24)

        val vibe: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        val audioAttributes = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()

        soundPool = SoundPool.Builder().setMaxStreams(2).setAudioAttributes(audioAttributes).build()


        soundPlayer = soundPool!!.load(this, R.raw.click_button, 1)


        binding.button.setOnClickListener {
            count++
           binding.imageView.startAnimation(rotateAnimation)
            if (isTapSound) {
                soundPool?.play(soundPlayer, 1f, 1f, 1, 0, 1f)
            }
            if (isTapVibrator) {
                vibe.vibrate(100)
            }
            funCount()






        }

        binding.vib.setOnClickListener {

            v++

            if (v == 1) {
                isTapVibrator = false
                vibe.cancel()
                binding.vib.startAnimation(animBtn)
                binding.vib.setImageResource(R.drawable.vib_off_2864194)
            } else if (v == 2) {
                isTapVibrator = true
                vibe.vibrate(200)
                binding.vib.startAnimation(animBtn)
                binding.vib.setImageResource(R.drawable.noun_vibration_2310451)
                v = 0
            }


        }

        binding.sound.setOnClickListener {

            s++

            if (s == 1) {
                isTapSound = false
                soundPool?.stop(soundPlayer)

                binding.sound.startAnimation(animBtn)
                binding.sound.setImageResource(R.drawable.noun_sound_off_1098137)
            } else if (s == 2) {
                isTapSound = true
                binding.sound.startAnimation(animBtn)
                binding.sound.setImageResource(R.drawable.noun_sound_on_3992693)
                s = 0
            }


        }




        binding.ref.setOnClickListener {
            binding.ref.startAnimation(animBtn)
            count = 0
        //    binding.progressBar.progress = count
            binding.textView.startAnimation(animBtn)
            binding.textView.text = count.toString()
        }


    }

    private fun funCount() {
        if (count < 99999) {
          //  binding.progressBar.progress = count
            binding.textView.text = count.toString()
        } else {
            count = 0
        }
    }


}