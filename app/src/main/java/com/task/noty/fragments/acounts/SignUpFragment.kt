package com.task.noty.fragments.acounts


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.task.noty.R
import com.task.noty.common.constants.NORMAL_USER
import com.task.noty.common.utils.AppLog
import com.task.noty.common.utils.Utils
import com.task.noty.controllers.DatabaseController
import com.task.noty.models.database.Users
import kotlinx.android.synthetic.main.fragment_sign_up.*
import java.sql.SQLException


/**
 * A Sign Up fragment where Simple Users gets Sign up if he/she doesnt have account]
 *
 */
class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    /** This function initializes the UI elements as well as class members**/
    fun initViews() {

        btn_sign_up.setOnClickListener { onSignup() }

        Utils.textChange(et_first_name, input_first_name)
        Utils.textChange(et_username, input_email)
        Utils.textChange(et_last_name, input_last_name)
        Utils.textChange(et_confirm_password, input_confirm_password)
        Utils.textChange(et_password, input_password)
    }

    fun onSignup() {
        if (validate()) {

            val user = Users()

            user.firstName = et_first_name.text.toString()
            user.lastName = et_last_name.text.toString()
            user.userName = et_username.text.toString()
            user.password = et_password.text.toString()
            user.role= NORMAL_USER

            try {
                val status=DatabaseController.singletonInstance.signUp(user, activity as AppCompatActivity)

                if(status!!)
                {
                    AppLog.logDebug("User Signed Up")

                    Utils.showSuccessSnackBar(rlRoot,getString(R.string.message_user_signed_up))
                    Utils.onBackPress(activity as AppCompatActivity)
                }
                else
                {
                    AppLog.logDebug("User not Signed Up")
                }


            } catch (e: SQLException) {
                AppLog.logDebug(e.printStackTrace().toString())
            }
        }
    }

    fun validate(): Boolean {
        var isValid: Boolean = true

        if (TextUtils.isEmpty(et_first_name.text.toString())) {
            input_first_name.error = getString(R.string.please_enter_first_name)
            isValid = false
        }
        if (TextUtils.isEmpty(et_last_name.text.toString())) {
            input_last_name.error = getString(R.string.please_enter_last_name)
            isValid = false
        }
        if (TextUtils.isEmpty(et_username.text.toString())) {
            input_email.error = getString(R.string.please_enter_username)
            isValid = false
        }
        if (TextUtils.isEmpty(et_password.text.toString())) {
            input_password.error = getString(R.string.please_enter_password)
            isValid = false
        }
        if (TextUtils.isEmpty(et_confirm_password.text.toString())) {
            input_confirm_password.error = getString(R.string.please_enter_confirm_password)
            isValid = false
        }

        if (!et_password.text.toString().equals(et_confirm_password.text.toString())) {
            Utils.showErrorSnackBar(rlRoot, getString(R.string.please_enter_same_password))
            isValid = false
        }

        return isValid;
    }


    companion object {
        fun newInstance(): Fragment {
            val fragment = SignUpFragment()
            return fragment;
        }
    }

}
