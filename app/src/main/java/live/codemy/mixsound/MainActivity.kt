package live.codemy.mixsound

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import live.codemy.mixsoundlib.MixSound
import live.codemy.mixsoundlib.SoundType

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgmic = findViewById<ImageButton>(R.id.imgviewregister)
        val imgfast = findViewById<ImageButton>(R.id.imgbtnfast)
        val imgslow = findViewById<ImageButton>(R.id.imgbtnslow)
        val imgkalın = findViewById<ImageButton>(R.id.imgbtnpowerful)
        val imgince = findViewById<ImageButton>(R.id.imgbtnthin)



        imgfast.setOnClickListener {
            MixSound.getInstance(this).changeSound(SoundType.Fast)

            "imgfast clicked" extshowToast this

        }
        imgslow.setOnClickListener {
            MixSound.getInstance(this).changeSound(SoundType.Slow)
            "imgslow clicked" extshowToast this

        }

        imgkalın.setOnClickListener {
            MixSound.getInstance(this).changeSound(SoundType.DarthVader)

            "imgkalın clicked" extshowToast this

        }
        imgince.setOnClickListener {
            MixSound.getInstance(this).changeSound(SoundType.Chipmunk)

            "imgince clicked" extshowToast this

        }
        imgmic.setOnClickListener {
            MixSound.getInstance(this).recordSound()
            "mic clicked" extshowToast this
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            MixSound.CODE_SPEECH_RECOGNIZER -> {
                data?.let {
                    val result = it.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    MixSound.recordSound = result.first()
                }
            }

        }
    }


    override fun onPause() {
        MixSound.getInstance(this).textToSpeech.stop()
        MixSound.getInstance(this).textToSpeech.shutdown()
        super.onPause()
    }
}

infix fun String.extshowToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}