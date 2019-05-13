package com.task.noty.common.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.task.noty.R
import io.reactivex.annotations.NonNull
import kotlinx.android.synthetic.main.default_layout_template.view.*
import kotlinx.android.synthetic.main.toolbar_default.view.*

class DefaultView : RelativeLayout {
    internal var mInflater: LayoutInflater

    internal var toolbarBackEventLisetner: ToolbarBackEventLisetner? = null
    internal var retryEventListener: RetryEventListener? = null

    constructor(context: Context) : super(context) {
        mInflater = LayoutInflater.from(context)
        init()

    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        mInflater = LayoutInflater.from(context)
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mInflater = LayoutInflater.from(context)
        init()
    }

    fun init() {
        val view = mInflater.inflate(R.layout.default_layout_template, this, true)
        rlMessage.visibility = View.GONE
        rlBack.setOnClickListener { onBack() }
        btn_retry.setOnClickListener { onRetry() }

    }





    fun setText(message: String, view: View) {
        view.visibility = View.GONE
        rlMessage.visibility = View.VISIBLE
        tv_message.text = message
    }

    fun showDialog(message: String, view: View) {
        view.visibility = View.GONE
        rlMessage.visibility = View.VISIBLE
        tv_message.text = message
    }

    fun showContent(view: View) {
        view.visibility = View.VISIBLE
        rlMessage.visibility = View.GONE
    }

    fun hideDialog(view: View) {
        view.visibility = View.VISIBLE
        rlMessage.visibility = View.GONE
    }

    fun hideAll(view: View) {
        view.visibility = View.GONE
        rlMessage.visibility = View.GONE
    }

    fun hideRetry() {
        btn_retry.visibility = View.GONE
    }

    fun showRetry()
    {
        btn_retry.visibility= View.VISIBLE
    }





    fun setBackEventListener(toolbarBackEventLisetner: ToolbarBackEventLisetner) {
        this.toolbarBackEventLisetner = toolbarBackEventLisetner;
    }

    fun onBack() {
        toolbarBackEventLisetner?.onBack()
    }

    fun setRetryListener(retryEventListener: RetryEventListener) {
        this.retryEventListener = retryEventListener
    }

    fun onRetry() {
        retryEventListener?.onRetry()
    }



    interface ToolbarBackEventLisetner {
        fun onBack()
    }

    interface RetryEventListener {
        fun onRetry()
    }



    fun setTitle(@NonNull title: String) {
        tv_title.setText(title)
    }

    fun setTitle(@NonNull title: Int) {
        tv_title.setText(title)
    }

    fun setBackIcon(@NonNull icon: String) {
        tv_back.setText(icon)
    }

    fun setBackIcon(@NonNull icon: Int) {
        tv_back.setText(icon)
    }

    fun hideBackButton() {
        rlBack.visibility = View.INVISIBLE
    }

    fun showBackButton() {
        rlBack.visibility = View.VISIBLE
    }







}