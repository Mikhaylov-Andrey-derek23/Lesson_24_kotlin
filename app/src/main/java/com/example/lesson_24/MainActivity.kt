package com.example.lesson_24

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//Темы: Корутиныand RXJava
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, FirstFragment())
            .commit()
    }
}