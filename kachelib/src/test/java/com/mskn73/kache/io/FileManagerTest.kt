package com.mskn73.kache.io

import org.amshove.kluent.shouldEqualTo

class FileManagerTest : com.mskn73.kache.AndroidTest() {

  private var fileManager = com.mskn73.kache.io.FileManager()

  @org.junit.After
  fun tearDown() {
    fileManager.clearDirectory(cacheDir())
  }

  @org.junit.Test
  fun shouldWriteToFile() {
    val fileToWrite = createDummyFile()
    val fileContent = "content"

    fileManager.writeToFile(fileToWrite, fileContent)

    fileToWrite.exists() shouldEqualTo true
  }

  @org.junit.Test
  fun shouldHaveCorrectFileContent() {
    val fileToWrite = createDummyFile()
    val fileContent = "content\n"

    fileManager.writeToFile(fileToWrite, fileContent)
    val expectedContent = fileManager.readFileContent(fileToWrite)

    expectedContent shouldEqualTo fileContent
  }

  private fun createDummyFile(): java.io.File {
    val dummyFilePath = cacheDir().path + java.io.File.separator + "dummyFile"
    return java.io.File(dummyFilePath)
  }
}

