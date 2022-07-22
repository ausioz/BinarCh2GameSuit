package com.ausioz.binarch2gamesuit

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
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
            clearPickBG()
            activePickBatu()
            rule(pilihanSuit[0], pilihanSuit.random())
        }
        _binding?.playerBtnGunting?.setOnClickListener {
            clearPickBG()
            activePickGunting()
            rule(pilihanSuit[1], pilihanSuit.random())
        }
        _binding?.playerBtnKertas?.setOnClickListener {
            clearPickBG()
            activePickKertas()
            rule(pilihanSuit[2], pilihanSuit.random())
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


    fun clearPickBG() {
        _binding?.playerBtnBatu?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.playerBtnBatu?.colorFilter = null
        _binding?.playerBtnGunting?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.playerBtnGunting?.colorFilter = null
        _binding?.playerBtnKertas?.setBackgroundColor(Color.TRANSPARENT)
        _binding?.playerBtnKertas?.colorFilter = null
    }

    fun activePickBatu() {
        _binding?.playerBtnBatu?.setBackgroundColor(resources.getColor(R.color.active))
        _binding?.playerBtnGunting?.setColorFilter(Color.LTGRAY)
        _binding?.playerBtnKertas?.setColorFilter(Color.LTGRAY)
        _binding?.btnLockPick?.isVisible = true
        _binding?.btnLockPick?.text = "Kuci Pilihan : \nBatu"
    }

    fun activePickGunting() {
        _binding?.playerBtnBatu?.setColorFilter(Color.LTGRAY)
        _binding?.playerBtnGunting?.setBackgroundColor(resources.getColor(R.color.active))
        _binding?.playerBtnKertas?.setColorFilter(Color.LTGRAY)
        _binding?.btnLockPick?.isVisible = true
        _binding?.btnLockPick?.text = "Kuci Pilihan : \nGunting"
    }

    fun activePickKertas() {
        _binding?.playerBtnBatu?.setColorFilter(Color.LTGRAY)
        _binding?.playerBtnGunting?.setColorFilter(Color.LTGRAY)
        _binding?.playerBtnKertas?.setBackgroundColor(resources.getColor(R.color.active))
        _binding?.btnLockPick?.isVisible = true
        _binding?.btnLockPick?.text = "Kuci Pilihan : \nKertas"
    }


}