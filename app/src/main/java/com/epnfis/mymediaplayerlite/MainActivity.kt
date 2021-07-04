package com.epnfis.mymediaplayerlite

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var buttonPlay: Button
    private lateinit var buttonPause: Button
    private lateinit var buttonStop: Button

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPlay = findViewById(R.id.buttonPlay)
        buttonPause = findViewById(R.id.buttonPause)
        buttonStop = findViewById(R.id.buttonStop)

        //Botones deshabilitados hasta que cargue archivo de música
        //buttonPlay.isEnabled = false
        buttonPause.isEnabled = false
        buttonStop.isEnabled = false
        //mediaPlayer = MediaPlayer.create(this, R.raw.audio1)
        inicializarMediaPlayer()
        setOnClickListeners(this)
    }

    private fun inicializarMediaPlayer(){

        //var mp3URL = "https://file-examples.com/wp-content/uploads/2017/11/file_example_MP3_700KB.mp3" //MediaPlayer No reproduce en 700KB de bibrate
        mediaPlayer = MediaPlayer.create(this, Uri.parse("https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3"))
        mediaPlayer?.setOnPreparedListener {
                //mp -> mp.start()
            buttonPlay.isEnabled = true
            buttonPause.isEnabled = true
            buttonStop.isEnabled = true
        }
        //mediaPlayer.start();
    }

    private fun setOnClickListeners(context: Context) {
        buttonPlay.setOnClickListener {
            if (mediaPlayer == null) {
                inicializarMediaPlayer()
            }
            else if( mediaPlayer!!.isPlaying ) {
                return@setOnClickListener
            }
            else
                mediaPlayer?.start()
            Toast.makeText(context, "Reproduciendo...", Toast.LENGTH_SHORT).show()
        }

        buttonPause.setOnClickListener {
            mediaPlayer?.pause()
            Toast.makeText(context, "En pausa...", Toast.LENGTH_SHORT).show()
        }

        buttonStop.setOnClickListener {
            mediaPlayer?.stop()
            //mediaPlayer = MediaPlayer.create(context, R.raw.audio1)
            Toast.makeText(context, "Parando...", Toast.LENGTH_SHORT).show()
        }
    }
}