package com.task.noty.models.database

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import com.task.noty.common.constants.*

@DatabaseTable(tableName = USER_TABLE_NAME)
class Users {
    private val serialVersionUID = -222864131214757024L
    @DatabaseField(generatedId = true, columnName = USER_ID)
    var userId: Int = 0

    @DatabaseField(columnName = USERNAME)
    var userName: String? = null

    @DatabaseField(columnName = ROLE)
    var role: String? = null

    @DatabaseField(columnName = FIRST_NAME)
    var firstName: String? = null


    @DatabaseField(columnName = LAST_NAME)
    var lastName: String? = null

    @DatabaseField(columnName = PASSWORD)
    var password: String? = null

    @DatabaseField(columnName = LOGINED)
    var logined: Boolean? = false




}
