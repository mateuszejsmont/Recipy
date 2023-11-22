package com.example.recipy

import android.app.Application
import com.example.recipy.data.AppContainer
import com.example.recipy.data.DefaultAppContainer

class RecipyApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}