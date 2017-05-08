package com.mskn73.kache.io

import com.mskn73.kache.AndroidTest
import org.amshove.kluent.shouldEqualTo
import org.junit.After
import org.junit.Test
import java.io.File

class FileManagerTest : AndroidTest() {

  private var fileManager = com.mskn73.kache.io.FileManager()

  @After
  fun tearDown() {
    fileManager.clearDirectory(cacheDir())
  }

  @Test
  fun shouldWriteToFile() {
    val fileToWrite = createDummyFile()
    val fileContent = "content"

    fileManager.writeToFile(fileToWrite, fileContent)

    fileToWrite.exists() shouldEqualTo true
  }

  @Test
  fun shouldHaveCorrectFileContent() {
    val fileToWrite = createDummyFile()
    val fileContent = "content\n"

    fileManager.writeToFile(fileToWrite, fileContent)
    val expectedContent = fileManager.readFileContent(fileToWrite)

    expectedContent shouldEqualTo fileContent
  }

  private fun createDummyFile(): File {
    val dummyFilePath = cacheDir().path + java.io.File.separator + "dummyFile"
    return java.io.File(dummyFilePath)
  }
}

