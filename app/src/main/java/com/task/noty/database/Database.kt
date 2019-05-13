package com.task.noty.database

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import com.task.noty.common.constants.DATABASE_PATH
import com.task.noty.models.database.Notes
import com.task.noty.models.database.Users
import java.sql.SQLException
import java.util.concurrent.atomic.AtomicInteger

class Database private constructor(context: Context) : OrmLiteSqliteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    private var userTable: Dao<Users, Int>? = null
    private var notesTable: Dao<Notes, Int>? = null



    override fun onCreate(sqlDatabase: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        try {
            TableUtils.createTable(connectionSource, Users::class.java)
            TableUtils.createTable(connectionSource, Notes::class.java)
        }catch (e:SQLException) {
            Log.e("DataBase Error",e.printStackTrace().toString());
        }
    }

    override fun onUpgrade(sqlDatabase: SQLiteDatabase?, connectionSource: ConnectionSource?, p2: Int, p3: Int) {
        try {

        TableUtils.dropTable<Users,Int>(connectionSource,Users::class.java,true)
        TableUtils.dropTable<Notes,Int>(connectionSource, Notes::class.java, true)
        onCreate(sqlDatabase, connectionSource)


        }catch (e:SQLException) {
            Log.e("DataBase Error",e.printStackTrace().toString());
        }
    }

    @Throws(SQLException::class)
    fun getUsersDao(): Dao<Users, Int>? {
        if (userTable == null) {
            userTable = getDao(Users::class.java)
        }
        return this!!.userTable
    }

    @Throws(SQLException::class)
    fun getNotesDao(): Dao<Notes, Int>? {
        if (notesTable == null) {
            notesTable = getDao(Notes::class.java)
        }
        return this!!.notesTable
    }



    companion object {
        @SuppressLint("SdCardPath")
        private val DATABASE_NAME = DATABASE_PATH
        private val DATABASE_VERSION = 1

        private val usageCounter = AtomicInteger(0)
        private var database: Database? = null

        @Synchronized
        fun getSingletonInstance(context: Context): Database {
            if (database == null) {
                database = Database(context)
            }
            usageCounter.incrementAndGet()
            return database as Database
        }
    }
}