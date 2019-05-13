package com.task.noty.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.NonNull
import com.task.noty.R
import com.task.noty.common.constants.NORMAL_USER
import com.task.noty.common.utils.AppLog
import com.task.noty.common.utils.SessionInfo
import com.task.noty.fragments.acounts.SignUpFragment
import com.task.noty.fragments.notes.NotesListFragment

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)




        if(SessionInfo.role== NORMAL_USER)
        {
            addFragment(this, NotesListFragment.newInstance())

        }
        else
        {
            AppLog.logDebug("User is Admin ->"+SessionInfo.firstName)
        }
    }

    companion object {
        fun changeActivity(@NonNull context: Context): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            return intent
        }
    }
}
