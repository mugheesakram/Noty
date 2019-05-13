package com.task.noty.controllers

import android.content.Context
import com.j256.ormlite.dao.Dao
import com.task.noty.common.constants.*
import com.task.noty.database.Database
import com.task.noty.models.database.Notes
import com.task.noty.models.database.Users
import java.sql.SQLException

class DatabaseController {

    fun signUp(user: Users, context: Context): Boolean? {
        var status : Dao.CreateOrUpdateStatus? = null
        try {
            status = Database.getSingletonInstance(
                    context)
                    .getUsersDao()?.createOrUpdate(user)

            status?.isCreated
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return status?.isCreated
    }

    @Throws(SQLException::class)
    fun getSessionInfo(context: Context): List<Users> {
        return Database.getSingletonInstance(context)
                .getUsersDao()?.queryBuilder()?.query()!!
    }

    @Throws(SQLException::class)
    fun login(context: Context,username:String,password:String): Users? {

        val users= Database.getSingletonInstance(context)
            .getUsersDao()?.queryBuilder()?.where()?.eq(USERNAME,username)?.and()?.eq(PASSWORD,password)?.query()!!

        val updateBuilder=Database.getSingletonInstance(context).getUsersDao()
            ?.updateBuilder()
        updateBuilder?.where()?.eq(USERNAME,username)?.and()?.eq(PASSWORD,password)
        updateBuilder?.updateColumnValue(LOGINED /* column */, true /* value */);
        updateBuilder?.update();

        if(users.size>0)
        {
            return users[0]
        }

        return null
    }

    @Throws(SQLException::class)

    fun checkIfLoginedAlready(context: Context): Users?
    {


        val users=Database.getSingletonInstance(context)
            .getUsersDao()?.queryBuilder()?.where()?.eq(LOGINED,true)?.query()!!

        if(users.size>0)
        {
            return users[0]
        }
        return null
    }


    @Throws(SQLException::class)
    fun logout(context: Context,username: String?){

        val updateBuilder=Database.getSingletonInstance(context).getUsersDao()
            ?.updateBuilder()
        updateBuilder?.where()?.eq(USERNAME,username)
        updateBuilder?.updateColumnValue(LOGINED /* column */, false /* value */);
        updateBuilder?.update();

    }


    @Throws(SQLException::class)
    fun getNotesByUserID(context:Context,userId:Int):List<Notes> {
        return Database.getSingletonInstance(context)
            .getNotesDao()?.queryBuilder()?.where()?.eq(USER_ID,userId)?.query()!!
    }
    @Throws(SQLException::class)

    fun adminCheck(context:Context):Boolean
    {
        val users = Database.getSingletonInstance(context)
            .getUsersDao()?.queryBuilder()?.where()?.eq(ROLE, ADMIN_USER)?.query()!!

        if(users.size==0)
        {
            return false
        }

        return true
    }
    @Throws(SQLException::class)

    fun addNote(context: Context,notes: Notes): Boolean?
    {
        var status : Dao.CreateOrUpdateStatus? = null
        try {
            status = Database.getSingletonInstance(
                context)
                .getNotesDao()?.createOrUpdate(notes)

            status?.isCreated
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return status?.isCreated
    }

    @Throws(SQLException::class)

    fun editNote(context: Context,notes:Notes)
    {
        val updateBuilder=Database.getSingletonInstance(context).getNotesDao()
            ?.updateBuilder()
        updateBuilder?.where()?.eq(NOTES_ID,notes.noteId)
        updateBuilder?.updateColumnValue(NOTES_TEXT/* column */, notes.notesText /* value */);
        updateBuilder?.update();

    }







    companion object {
        private var databaseController: DatabaseController? = null

        val singletonInstance: DatabaseController
            get() {
                if (databaseController == null) {
                    databaseController = DatabaseController()
                }
                return databaseController as DatabaseController
            }
    }
}