package com.example.bottom

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL


interface ImageCallback {
    fun success(bitmap: Bitmap)
    fun failed()
}

class DownloadImage(
    private val url: String,
    private val callback: ImageCallback
) : Thread() {

    override fun run() {
        super.run()
        try {
            val connection = URL(url).openConnection()
            connection.doInput = true
            connection.connect()
            connection.getInputStream().use {
                callback.success(BitmapFactory.decodeStream(it))
            }
        } catch (e: Exception) {
            callback.failed()
        }
    }
}