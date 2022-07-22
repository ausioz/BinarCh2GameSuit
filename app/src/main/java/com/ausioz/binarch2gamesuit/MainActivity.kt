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
            gameFlow(pilihanSuit[0])
        }
        _binding?.playerBtnGunting?.setOnClickListener {
            refreshPick()
            activePickGunting()
            gameFlow(pilihanSuit[1])
        }
        _binding?.playerBtnKertas?.setOnClickListener {
            refreshPick()
            activePickKertas()
            gameFlow(pilihanSuit[2])
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

    fun rule(pemain1: String?, pemain2: String?) {
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

    fun gameFlow(pemain1: String?){
        val pemain2 = pilihanSuit.random()
        rule(pemain1,pemain2)
    }

    fun playTerminal() {
        val Count: CountDownTimer = object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _binding?.textTerminal?.text = (millisUntilFinished/1000).toString()
                Log.d("Countdown", "playTerminal: ${(millisUntilFinished/1000)}")
            }
            override fun onFinish() {
                _binding?.textTerminal?.text = status
            }
        }
        Count.start()
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

    fun refreshPick() {
        _binding?.btnLockPick?.isVisible = false
        _binding?.playerBtnBatu?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.playerBtnBatu?.colorFilter = null
        _binding?.playerBtnGunting?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.playerBtnGunting?.colorFilter = null
        _binding?.playerBtnKertas?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.playerBtnKertas?.colorFilter = null
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

}