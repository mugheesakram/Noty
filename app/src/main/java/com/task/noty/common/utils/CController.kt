package com.task.noty.common.utils
/**
 * Created by MUHAMMAD AZHAR ALI on 7/6/2018.
 */

class CController {

    val serverKey: String
        get() = cResults(SERVER)

    val appReferer: String
        get() = cResults(REFERER)


    val stgEndPoint: String
        get() = cResults(STG_ENDPOINT)

    val devEndPoint: String
        get() = cResults(DEV_ENDPOINT)


    val prodEndPoint: String
        get() = cResults(PROD_ENDPOINT)

    companion object {

        private var cController: CController? = null

        private val SERVER = "server"
        private val REFERER = "referer"
        private val STG_ENDPOINT = "stgEndPoint"
        private val DEV_ENDPOINT = "devEndPoint"
        private val PROD_ENDPOINT = "prodEndPoint"

        init {
            System.loadLibrary("hello-jni")
        }

        val singletonInstance: CController get() {
                if (CController.cController == null) {
                    CController.cController = CController()
                }
                return CController.cController as CController
            }
    }

    external fun cResults(name: String): String


}