package com.task.noty

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.task.noty.activities.BaseActivity
import com.task.noty.activities.HomeActivity
import com.task.noty.common.constants.ADMIN_USER
import com.task.noty.common.utils.AppLog
import com.task.noty.common.utils.Utils
import com.task.noty.controllers.DatabaseController
import com.task.noty.fragments.acounts.LoginFragment
import com.task.noty.models.database.Users
import io.reactivex.annotations.NonNull
import java.sql.SQLException

class MainActivity : BaseActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerAdmin()

        val session = DatabaseController.singletonInstance.checkIfLoginedAlready(this)


        if(session!=null)
        {
            Utils.setUserSession(session)
            val intent = HomeActivity.changeActivity(this)
            startActivity(intent)
            finish()
        } else {
            addFragment(this, LoginFragment.newInstance())
        }



    }

    // Registers Admin with the given credentials in project requirement
    private fun registerAdmin()
    {
        val status=DatabaseController.singletonInstance.adminCheck(this)

        if(!status)
        {
            val admin = Users()

            admin.firstName="Bruce"
            admin.lastName="Wayne"
            admin.userName="adminuser"
            admin.password="superuser"
            admin.role= ADMIN_USER
            admin.logined=false
            try {
                val status=DatabaseController.singletonInstance.signUp(admin, this)

                if(status!!)
                {
                    AppLog.logDebug("Admin Signed Up")

                }



            } catch (e: SQLException) {
                AppLog.logDebug(e.printStackTrace().toString())
            }
        }else
        {
            AppLog.logDebug("Admin Already Exists")
        }

    }




    companion object {
        fun changeActivity(@NonNull context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }
}
