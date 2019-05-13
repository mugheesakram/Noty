package com.task.noty.common.utils

import android.util.Log

import com.task.noty.BuildConfig
import com.task.noty.common.constants.TAG
import io.reactivex.annotations.NonNull

class AppLog {
    companion object {
        fun loggingEnabled(): Boolean {
            return BuildConfig.DEBUG
        }

        fun logDebug(@NonNull message: String) {
            if (loggingEnabled()) Log.d(TAG, message)
        }

        fun logDebug(@NonNull tag: String, @NonNull message: String) {
            if (loggingEnabled()) Log.d(tag, message)
        }

        fun logInfo(@NonNull message: String) {
            if (loggingEnabled()) Log.i(TAG, message)
        }

        fun logInfo(@NonNull tag: String, @NonNull message: String) {
            if (loggingEnabled()) Log.i(tag, message)
        }

        fun logError(@NonNull message: String) {
            if (loggingEnabled()) Log.e(TAG, message)
        }

        fun logError(@NonNull tag: String, @NonNull message: String) {
            if (loggingEnabled()) Log.e(tag, message)
        }

        fun logError(@NonNull tag: String, @NonNull message: String, throwable: Throwable) {
            if (loggingEnabled()) Log.e(tag, message, throwable)
        }

        fun logWarning(@NonNull message: String) {
            if (loggingEnabled()) Log.w(TAG, message)
        }

        fun logWarning(@NonNull tag: String, @NonNull message: String) {
            if (loggingEnabled()) Log.w(tag, message)
        }
    }

}