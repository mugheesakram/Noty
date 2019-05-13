package com.task.noty.controllers

import android.app.Application

class AppController : Application() {

    companion object {
        lateinit var newInstance: AppController


        fun getInstance(): AppController {
            return newInstance
        }

    }

    override fun onCreate() {
        super.onCreate()
        newInstance = this


    }


}