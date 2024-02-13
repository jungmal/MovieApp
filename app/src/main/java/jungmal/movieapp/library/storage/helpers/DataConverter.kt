package jungmal.movieapp.library.storage.helpers

import android.util.Log
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import javax.inject.Inject

class DataConverter @Inject constructor() {

    fun <T> serialize(obj: T): ByteArray? {
        return try {
            val bytesOS = ByteArrayOutputStream()
            ObjectOutputStream(bytesOS).writeObject(obj)
            bytesOS.toByteArray()
        } catch (e: IOException) {
            Log.e("DataConverter", e.toString())
            null
        }
    }

    fun <T> deserialize(data: ByteArray?): T? {
        return try {
            val bytesIS = ByteArrayInputStream(data)
            @Suppress("UNCHECKED_CAST")
            ObjectInputStream(bytesIS).readObject() as? T?
        } catch (e: ClassNotFoundException) {
            Log.e("DataConverter", e.toString())
            null
        } catch (e: IOException) {
            Log.e("DataConverter", e.toString())
            null
        }
    }
}
