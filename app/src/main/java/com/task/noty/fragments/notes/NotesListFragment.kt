package com.task.noty.fragments.notes


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.task.noty.R
import com.task.noty.adapter.NotesAdapter
import com.task.noty.common.utils.SessionInfo
import com.task.noty.controllers.DatabaseController
import com.task.noty.fragments.BaseFragment
import com.task.noty.models.database.Notes
import kotlinx.android.synthetic.main.default_view.*
import kotlinx.android.synthetic.main.fragment_notes_list.*
import android.support.v7.widget.StaggeredGridLayoutManager
import com.task.noty.common.utils.SignalEvent
import com.task.noty.common.utils.Utils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * A fragment that contains the list of notes
 *
 *
 */
class NotesListFragment : BaseFragment() {

    lateinit var notesAdapter:NotesAdapter
    lateinit var notes:MutableList<Notes>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews()
    {
        default_view.hideBackButton()
        default_view.setTitle(SessionInfo.firstName+" "+SessionInfo.lastName)
        fb_add_note.setOnClickListener { onAddNoteListener() }

        setData()
    }

    private fun onAddNoteListener()
    {
        addFragment(activity as AppCompatActivity, NotesDetailFragment.newInstance("",0,true))

    }


    private fun setData()
    {

        notes=
            DatabaseController.singletonInstance.getNotesByUserID(activity as AppCompatActivity,SessionInfo.userId!!).toMutableList()

        if(notes.size>0)
        {
            setAdapter()
        }
        else
        {
            default_view.showDialog("No record found",rlMain)
        }

    }

    private fun setAdapter()
    {
        notesAdapter= NotesAdapter(activity as AppCompatActivity,notes,object :NotesAdapter.ItemClickListener{
            override fun onItem(position: Int) {
                addFragment(activity as AppCompatActivity, NotesDetailFragment.newInstance(notes.get(position).notesText!!,notes.get(position).noteId,false))

            }

        })
        rv_notes.setLayoutManager(StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL))
        rv_notes.adapter=notesAdapter

    }

    companion object {
        fun newInstance(): Fragment {
            val fragment = NotesListFragment()
            return fragment;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: SignalEvent) {
        // AppLog.logDebug("ForwardTicketEvent", event.message);

        Utils.showSuccessSnackBar(rlRoot, getString(R.string.notes_updated))

        refresh()

    }

    private fun refresh()
    {
        this.notes?.clear()
        setData()


    }
    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}
