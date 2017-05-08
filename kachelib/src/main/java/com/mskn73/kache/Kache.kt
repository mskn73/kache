package com.mskn73.kache

import android.content.Context
import com.mskn73.kache.io.FileManager
import com.mskn73.kache.io.Serializer
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import java.io.File
import kotlin.system.measureTimeMillis

/**
 * Created by francisco.hernandez on 8/5/17.
 */

class Kache(val context: Context, val cacheDir: String) {
  private val SETTINGS_FILE_NAME = "com.mskn73.kache.SETTINGS"

  val fileManager: FileManager = FileManager()
  val serializer: Serializer = Serializer()


  fun put(myKacheable: Kacheable) {
    val file = buildFileForKey(myKacheable.key)
    val serializedObject = serializer.serialize(myKacheable, myKacheable.javaClass)
    fileManager.writeToFile(file, serializedObject)
    setLastCacheUpdate(myKacheable.key)
  }

  fun get(clazz: Class<*>, key: String): Any? {
    val file = buildFileForKey(key)
    val fileContent = fileManager.readFileContent(file)
    val obj = serializer.deserialize(fileContent, clazz)
    return obj
  }

  private fun setLastCacheUpdate(key: String) {
    fileManager.writeToPreferences(context, SETTINGS_FILE_NAME, key, System.currentTimeMillis())
  }

  private fun buildFileForKey(key: String): File {
    return File(cacheDir + File.separator + key)
  }
}