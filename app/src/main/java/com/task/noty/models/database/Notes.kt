package com.task.noty.models.database

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import com.task.noty.common.constants.*

@DatabaseTable(tableName = NOTES_TABLE_NAME)
class Notes {
    private val serialVersionUID = -222864131214757024L



    @DatabaseField(generatedId = true, columnName = NOTES_ID)
    var noteId: Int = 0

    @DatabaseField(columnName = NOTES_TEXT)
    var notesText: String? = null

    @DatabaseField(columnName = NOTES_TYPE)
    var notesType: String? = null

    @DatabaseField(columnName = IMAGE_PATH)
    var imagePath: String? = null

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true,columnName = USER_ID)
    var users: Users? = null

    @DatabaseField(columnName = CREATED_ON)
    var createdOn: String? = null
}