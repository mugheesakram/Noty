package com.task.noty.fragments

import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.task.noty.R

open class BaseFragment : Fragment() {
    fun addFragment(@NonNull context: AppCompatActivity?, fragment: Fragment) {
        val ft = context?.supportFragmentManager?.beginTransaction()
        ft?.add(R.id.content_frame, fragment)
        ft?.addToBackStack(fragment.javaClass.simpleName)
        ft?.commit()
    }
}