package com.task.noty.common.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.task.noty.R
import com.task.noty.controllers.AppController
import com.task.noty.models.database.Users

class Utils {

    companion object {
        fun showErrorSnackBar(view: View, message: String) {
            val snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG)
            val sbView = snackbar.getView()
            sbView.setBackgroundColor(ContextCompat.getColor(AppController.getInstance(), R.color.snack_error_color));
            val textView = sbView.findViewById(android.support.design.R.id.snackbar_text) as TextView
            textView.setTextColor(Color.WHITE)
            textView.setTypeface(ResourcesCompat.getFont(AppController.getInstance(), R.font.medium))
            snackbar.show()
        }


        /**
         * Success message snackbar
         * @param view parent view where want to show
         * @param message message to show
         * */
        fun showSuccessSnackBar(view: View, message: String) {
            val snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG)
            val sbView = snackbar.getView()
            sbView.setBackgroundColor(ContextCompat.getColor(AppController.getInstance(), R.color.success_stroke_color));
            val textView = sbView.findViewById(android.support.design.R.id.snackbar_text) as TextView
            textView.setTextColor(Color.WHITE)
            textView.setTypeface(ResourcesCompat.getFont(AppController.getInstance(), R.font.medium))
            snackbar.show()
        }

        /**
         * Check internet connection
         * */
        fun checkInternetConnection(): Boolean {
            val cm = AppController
                .getInstance().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return (cm.activeNetworkInfo != null
                    && cm.activeNetworkInfo.isAvailable
                    && cm.activeNetworkInfo.isConnected)
        }


        /**
         * Email address validation
         * @param target char to validate
         * */
        fun isValidEmail(target: CharSequence?): Boolean {
            return if (target == null) {
                false
            } else {
                android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
            }
        }

        /**
         * Close the keyboard
         * @param activity Activity context
         * */
        fun hideKeyboard(activity: AppCompatActivity) {
            val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            //Find the currently focused view, so we can grab the correct window token from it.
            var view = activity.currentFocus
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        /**
         * Common function of back button click listener
         * */
        fun onBackPress(activity: AppCompatActivity) {
            hideKeyboard(activity)
            activity.supportFragmentManager.popBackStack()
        }

        fun textChange(editText: EditText, textInputLayout: TextInputLayout) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if (textInputLayout.isErrorEnabled) {
                        textInputLayout.isErrorEnabled = false
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
        }

        fun setUserSession(user: Users) {
            SessionInfo.firstName = user.firstName
            SessionInfo.lastName= user.lastName
            SessionInfo.userName = user.lastName
            SessionInfo.userId= user.userId
            SessionInfo.role=user.role
        }
//
//        fun signOut(context: AppCompatActivity) {
//            SessionInfo.accessToken = null
//            SessionInfo.userName = null
//            SessionInfo.email = null
//            SessionInfo.companyId = null
//
//            DatabaseController.singletonInstance.deleteSession(context)
//
//            val intent = MainActivity.changeActivity(context)
//            context.startActivity(intent)
//            context.finish()
//
//        }
    }
}