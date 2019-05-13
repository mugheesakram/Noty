package com.task.noty.common.constants

import com.task.noty.controllers.AppController

val DATABASE_PATH = AppController.getInstance().getFilesDir().toString() + "/" + "noty.db"
const val TAG = "ChatKraft"
