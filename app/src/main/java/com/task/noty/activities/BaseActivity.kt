package com.task.noty.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.task.noty.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    fun addFragment(context: AppCompatActivity, fragment: Fragment) {
        val ft = context.supportFragmentManager.beginTransaction()
        ft.add(R.id.content_frame, fragment)
        ft.commit()
    }


    fun addFragmentWithStack(context: AppCompatActivity, fragment: Fragment) {
        val ft = context.supportFragmentManager.beginTransaction()
        ft.add(R.id.content_frame, fragment)
        ft.addToBackStack(fragment.javaClass.simpleName)
        ft.commit()
    }
}