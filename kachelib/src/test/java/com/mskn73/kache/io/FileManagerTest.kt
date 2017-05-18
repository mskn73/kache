package com.mskn73.kache.io

import com.mskn73.kache.AndroidTest
import org.amshove.kluent.shouldEqualTo
import org.junit.After
import org.junit.Test
import java.io.File

class FileManagerTest : AndroidTest() {

  private var fileManager = FileManager()

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

  @Test
  fun shouldDeleteFileSameFile() {
    val fileToWrite = createDummyFile()
    val fileContent = "content\n"

    fileManager.writeToFile(fileToWrite, fileContent)
    fileManager.exists(fileToWrite) shouldEqualTo true
    fileManager.delete(fileToWrite)
    val expectedContent = fileManager.exists(fileToWrite)

    expectedContent shouldEqualTo false
  }

  @Test
  fun shouldNotDeleteDiferentSameFile() {
    val fileToWrite1 = createDummyFile()
    val fileToWrite2 = createDummyFile2()
    val fileContent = "content\n"

    fileManager.writeToFile(fileToWrite1, fileContent)
    fileManager.exists(fileToWrite1) shouldEqualTo true
    fileManager.writeToFile(fileToWrite2, fileContent)
    fileManager.exists(fileToWrite2) shouldEqualTo true
    fileManager.delete(fileToWrite1)
    val expectedContent1 = fileManager.exists(fileToWrite1)
    val expectedContent2 = fileManager.exists(fileToWrite2)

    expectedContent1 shouldEqualTo false
    expectedContent2 shouldEqualTo true
  }

  private fun createDummyFile(): File {
    val dummyFilePath = cacheDir().path + java.io.File.separator + "dummyFile"
    return File(dummyFilePath)
  }

  private fun createDummyFile2(): File {
    val dummyFilePath = cacheDir().path + java.io.File.separator + "dummyFile2"
    return File(dummyFilePath)
  }
}

