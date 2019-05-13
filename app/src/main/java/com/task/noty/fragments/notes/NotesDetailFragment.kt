package com.task.noty.fragments.notes


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.task.noty.R
import com.task.noty.common.utils.DefaultView
import com.task.noty.common.utils.SessionInfo
import com.task.noty.common.utils.SignalEvent
import com.task.noty.common.utils.Utils
import com.task.noty.controllers.DatabaseController
import com.task.noty.models.database.Notes
import com.task.noty.models.database.Users
import kotlinx.android.synthetic.main.default_view.*
import kotlinx.android.synthetic.main.fragment_notes_detail.*
import org.greenrobot.eventbus.EventBus

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
val CALL_TYPE="call_type"
val NOTES="notes"
val NOTE_ID="note_id"

class NotesDetailFragment : Fragment() {

    lateinit var note:String
    var callType:Boolean=false
    var noteId:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        callType= arguments?.getBoolean(CALL_TYPE)!!
        arguments?.getBoolean(CALL_TYPE).let { callType= it!! }
        if(!callType) {
            arguments?.getString(NOTES).let { note = it!! }
            arguments?.getInt(NOTE_ID).let { noteId = it!! }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }


    private fun initViews()
    {
        default_view.showBackButton()
        if(callType)
        {
            default_view.setTitle(getString(R.string.add_note))

        }
        else
        {
            default_view.setTitle(getString(R.string.note_detail))
            et_notes.setText(note)
        }

        default_view.setBackEventListener(backEventLisetner)
    }

    val backEventLisetner = object : DefaultView.ToolbarBackEventLisetner {
        override fun onBack() {

            saveNote()
            Utils.onBackPress(activity as AppCompatActivity)
        }

    }


    private fun saveNote()
    {
        if(callType)
        {
            if(et_notes.text.toString().length>0) {
                var noty = Notes()
                noty.notesText = et_notes.text.toString()
                var user = Users()
                user.userId = SessionInfo.userId!!
                user.userName = SessionInfo.userName
                user.firstName = SessionInfo.firstName
                user.lastName = SessionInfo.lastName
                noty.users = user
                DatabaseController.singletonInstance.addNote(activity as AppCompatActivity, noty)
                EventBus.getDefault().post(SignalEvent("SUCCESS"))
            }
        }
        else
        {
            var noty= Notes()
            noty.noteId=noteId
            noty.notesText=et_notes.text.toString()
            noty.users?.userId= SessionInfo.userId!!
            DatabaseController.singletonInstance.editNote(activity as AppCompatActivity,noty)
            EventBus.getDefault().post(SignalEvent("SUCCESS"))

        }

    }





    companion object {
        fun newInstance(note:String, noteId: Int?, callType:Boolean): Fragment {
            val fragment = NotesDetailFragment()
            var bundle=Bundle()

            bundle.putInt(NOTE_ID, noteId!!)
            bundle.putString(NOTES,note)
            bundle.putBoolean(CALL_TYPE,callType)
            fragment.arguments = bundle

            return fragment;
        }
    }


}
