package com.chomptech.easysleepsounds

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.media.MediaPlayer
import android.os.PowerManager
import android.os.CountDownTimer
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdView




class MainActivity : AppCompatActivity() {
    var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this, "ca-app-pub-9999083812241050~7895146987");

        val mAdView = findViewById<View>(R.id.adView) as AdView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val gridview = findViewById<View>(R.id.gridview) as GridView
        gridview.adapter = ImageAdapter(this)

        gridview.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, v: View,
                                     position: Int, id: Long) {
                startAudio(position)
            }
        }
    }
    fun startAudio(num : Int) {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }

        when (num) {
            0 -> { mediaPlayer = MediaPlayer.create(applicationContext, R.raw.rain)
                play()}
            1 -> {mediaPlayer = MediaPlayer.create(applicationContext, R.raw.thunder)
                play()}
            2 -> {mediaPlayer = MediaPlayer.create(applicationContext, R.raw.highway)
                play()}
            3 -> if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer = MediaPlayer()
            }
            else -> { // Note the block

            }
        }
    }

    fun play() {
        //val mediaPlayer = MediaPlayer.create(applicationContext, R.raw.rain)
        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mediaPlayer.setLooping(true)
        mediaPlayer.start() // no need to call prepare(); create() does that for you

        Toast.makeText(applicationContext, "Sound will play for one hour or until stopped manually with the in-app STOP button",
                Toast.LENGTH_LONG).show();


        object : CountDownTimer(3600000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer.release()
                    mediaPlayer = MediaPlayer()  //CHECK THE BEHAVIOR LATER WHAT HAPPENS ATTEMPTING
                    // PLAYBACK AFTER TIMER RUNS OUT, WIHTOUT THIS LINE OF CODE. THIS IS KIND OF DIRTY BECAUSE NOT RELEASED ANYMORE
                }
            }
        }.start()
    }

    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Back button pressed, app exiting.",
                Toast.LENGTH_SHORT).show();
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.release()
        super.onBackPressed()
    }

    }

