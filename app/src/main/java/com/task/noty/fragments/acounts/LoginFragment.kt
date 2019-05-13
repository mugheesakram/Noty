package com.task.noty.fragments.acounts


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.task.noty.R
import com.task.noty.activities.HomeActivity
import com.task.noty.common.utils.AppLog
import com.task.noty.common.utils.Utils
import com.task.noty.controllers.DatabaseController
import com.task.noty.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import java.sql.SQLException


/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        btn_sign_up.setOnClickListener { onSignUp() }
        btn_sign_in.setOnClickListener { onSignIn() }
    }


    private fun onSignUp() {
        Utils.hideKeyboard(activity as AppCompatActivity)
        addFragment(activity as AppCompatActivity, SignUpFragment.newInstance())
    }

    private fun onSignIn() {
        if (validate()) {
            try {
                val user=DatabaseController.singletonInstance.login(
                    activity as AppCompatActivity,
                    et_username.text.toString(),
                    et_password.text.toString()
                )

                if(user==null)
                {
                    Utils.showErrorSnackBar(rlRoot,getString(R.string.message_username_password_incorrect))
                }
                else
                {
                    Utils.showSuccessSnackBar(rlRoot,getString(R.string.message_user_signed_in))
                    Utils.setUserSession(user)

                    val intent = HomeActivity.changeActivity(activity as AppCompatActivity)
                    startActivity(intent)
                    (activity as AppCompatActivity).finish()
                }

            } catch (e: SQLException) {
                AppLog.logDebug(e.printStackTrace().toString())
            }
        }
    }

    private fun validate(): Boolean {

        var isValid: Boolean = true

        if (TextUtils.isEmpty(et_username.text.toString())) {
            input_email.error = getString(R.string.please_enter_username)
            isValid = false
        }
        if (TextUtils.isEmpty(et_password.text.toString())) {
            input_password.error = getString(R.string.please_enter_password)
            isValid = false
        }

        return isValid
    }


    companion object {
        fun newInstance(): Fragment {
            val fragment = LoginFragment()
            return fragment;
        }
    }


}
