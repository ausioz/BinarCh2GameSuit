package com.ausioz.binarch2gamesuit

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.ausioz.binarch2gamesuit.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val textTitle = (R.string.app_title_text)

    private val pilihanSuit = arrayOf("Batu", "Gunting", "Kertas")
    private var status: String? = null
    private var pemain1: String? = null
    private var pemain2: String? = null
    private var _binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        _binding?.textTitle?.text = (getText(textTitle))
        _binding?.textTerminal?.text = "VS"

        // 0: Batu      1: Gunting      2:Kertas
        _binding?.playerBtnBatu?.setOnClickListener {
            refreshPick()
            activePickBatu()
            pemain1 = pilihanSuit[0]
            doRule()
        }
        _binding?.playerBtnGunting?.setOnClickListener {
            refreshPick()
            activePickGunting()
            pemain1 = pilihanSuit[1]
            doRule()
        }
        _binding?.playerBtnKertas?.setOnClickListener {
            refreshPick()
            activePickKertas()
            pemain1 = pilihanSuit[2]
            doRule()
        }
        _binding?.btnLockPick?.setOnClickListener {
            playTerminal()
            disablePick()
        }
        _binding?.btnRefresh?.setOnClickListener {
            enablePick()
            refreshPick()
            _binding?.textTerminal?.text = "VS"
        }
    }

    fun doRule() {
        pemain2 = pilihanSuit.random()
        // 0: Batu      1: Gunting      2:Kertas
        if (
            pemain1 == pilihanSuit[0] && pemain2 == pilihanSuit[1] ||
            pemain1 == pilihanSuit[1] && pemain2 == pilihanSuit[2] ||
            pemain1 == pilihanSuit[2] && pemain2 == pilihanSuit[0]
        ) {
            status = "Pemain 1 MENANG!"
        } else if (
            pemain1 == pilihanSuit[0] && pemain2 == pilihanSuit[2] ||
            pemain1 == pilihanSuit[1] && pemain2 == pilihanSuit[0] ||
            pemain1 == pilihanSuit[2] && pemain2 == pilihanSuit[1]
        ) {
            status = "COM MENANG!"
        } else {
            status = "DRAW!"
        }
    }

    fun activePickBatu() {
        _binding?.playerBtnBatu?.setBackgroundColor(resources.getColor(R.color.active))
        _binding?.playerBtnGunting?.setColorFilter(Color.LTGRAY)
        _binding?.playerBtnKertas?.setColorFilter(Color.LTGRAY)
        _binding?.btnLockPick?.isVisible = true
        _binding?.btnLockPick?.text = "Kuci Pilihan\nBatu"
    }

    fun activePickGunting() {
        _binding?.playerBtnBatu?.setColorFilter(Color.LTGRAY)
        _binding?.playerBtnGunting?.setBackgroundColor(resources.getColor(R.color.active))
        _binding?.playerBtnKertas?.setColorFilter(Color.LTGRAY)
        _binding?.btnLockPick?.isVisible = true
        _binding?.btnLockPick?.text = "Kuci Pilihan\nGunting"
    }

    fun activePickKertas() {
        _binding?.playerBtnBatu?.setColorFilter(Color.LTGRAY)
        _binding?.playerBtnGunting?.setColorFilter(Color.LTGRAY)
        _binding?.playerBtnKertas?.setBackgroundColor(resources.getColor(R.color.active))
        _binding?.btnLockPick?.isVisible = true
        _binding?.btnLockPick?.text = "Kuci Pilihan\nKertas"
    }

    fun inactivePickCom(){
        _binding?.comBtnBatu?.setColorFilter(Color.LTGRAY)
        _binding?.comBtnGunting?.setColorFilter(Color.LTGRAY)
        _binding?.comBtnKertas?.setColorFilter(Color.LTGRAY)
    }


    fun refreshPick() {
        _binding?.btnLockPick?.isVisible = false
        _binding?.playerBtnBatu?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.playerBtnBatu?.colorFilter = null
        _binding?.comBtnBatu?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.comBtnBatu?.colorFilter = null
        _binding?.playerBtnGunting?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.playerBtnGunting?.colorFilter = null
        _binding?.comBtnGunting?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.comBtnGunting?.colorFilter = null
        _binding?.playerBtnKertas?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.playerBtnKertas?.colorFilter = null
        _binding?.comBtnKertas?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.comBtnKertas?.colorFilter = null
    }

    fun disablePick() {
        _binding?.playerBtnBatu?.isClickable = false
        _binding?.playerBtnGunting?.isClickable = false
        _binding?.playerBtnKertas?.isClickable = false
        _binding?.btnLockPick?.isVisible = false
    }
    fun enablePick() {
        _binding?.playerBtnBatu?.isClickable = true
        _binding?.playerBtnGunting?.isClickable = true
        _binding?.playerBtnKertas?.isClickable = true
    }
    fun comPickReveal(){
        // 0: Batu      1: Gunting      2:Kertas
        if (pemain2 == pilihanSuit[0]){
            _binding?.comBtnBatu?.setBackgroundColor(resources.getColor(R.color.active))
            _binding?.comBtnBatu?.colorFilter = null
        }
        else if(pemain2 == pilihanSuit[1]){
            _binding?.comBtnGunting?.setBackgroundColor(resources.getColor(R.color.active))
            _binding?.comBtnGunting?.colorFilter = null
        }
        else{
            _binding?.comBtnKertas?.setBackgroundColor(resources.getColor(R.color.active))
            _binding?.comBtnKertas?.colorFilter = null
        }
    }
    fun playTerminal() {
        inactivePickCom()
        val count: CountDownTimer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _binding?.textTerminal?.text = (millisUntilFinished/1000).toString()
                Log.d("Countdown", "playTerminal: ${(millisUntilFinished/1000)}")
            }
            override fun onFinish() {
                _binding?.textTerminal?.text = status
                comPickReveal()
            }
        }
        count.start()

    }
}