package com.mskn73.kache.io

import android.content.Context
import android.content.SharedPreferences
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

class FileManager internal constructor() {

  internal fun writeToFile(file: File, fileContent: String) {
    if (!file.exists()) {
      file.parentFile.mkdirs()
      val writer = FileWriter(file)
      writer.write(fileContent)
      writer.close()
    }
  }

  internal fun readFileContent(file: File): String {
    val fileContentBuilder = StringBuilder()
    if (file.exists()) {
      var stringLine: String
      val fileReader = FileReader(file)
      val bufferedReader = BufferedReader(fileReader)
      var finished = false
      while (!finished) {
        stringLine = bufferedReader.readLine()
        finished = stringLine != null
        fileContentBuilder.append(stringLine).append("\n")
      }
      bufferedReader.close()
      fileReader.close()
    }
    return fileContentBuilder.toString()
  }

  internal fun exists(file: File): Boolean {
    return file.exists()
  }

  internal fun clearDirectory(directory: File): Boolean {
    var result = false
    if (directory.exists()) {
      for (file in directory.listFiles()) {
        file.delete()
      }
      result = directory.listFiles().size == 0
    }
    return result
  }

  internal fun delete(file: File): Boolean {
    if (file.isDirectory()) {
      val children = file.list()
      for (i in children.indices) {
        File(file, children[i]).delete()
      }
    }
    file.delete()
    val result = exists(file)
    return result
  }

  internal fun writeToPreferences(context: Context, preferenceFileName: String, key: String,
      value: Long) {

    val sharedPreferences = context.getSharedPreferences(preferenceFileName,
        Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putLong(key, value)
    editor.commit()
  }

  internal fun getFromPreferences(context: Context, preferenceFileName: String, key: String): Long {
    val sharedPreferences = context.getSharedPreferences(preferenceFileName,
        Context.MODE_PRIVATE)
    return sharedPreferences.getLong(key, 0)
  }
}
