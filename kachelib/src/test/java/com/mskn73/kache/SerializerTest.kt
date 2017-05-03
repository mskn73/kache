package com.mskn73.kache

import org.amshove.kluent.shouldEqual
import org.junit.Test


class SerializerTest {

  private val JSON_RESPONSE = "{\r\n    \"red\":\"#f00\",\r\n    \"green\":\"#0f0\",\r\n    \"blue\":\"#00f\",\r\n    \"cyan\":\"#0ff\",\r\n    \"magenta\":\"#f0f\",\r\n    \"yellow\":\"#ff0\",\r\n    \"black\":\"#000\"\r\n}"

  private var serializer = Serializer()

  @Test
  fun shouldSerialize() {
    val userEntityOne = serializer.deserialize(JSON_RESPONSE, Example::class.java)
    val jsonString = serializer.serialize(userEntityOne, Example::class.java)
    val userEntityTwo = serializer.deserialize(jsonString, Example::class.java)

    userEntityOne.red shouldEqual userEntityTwo.red
    userEntityOne.green shouldEqual userEntityTwo.green
    userEntityOne.blue shouldEqual userEntityTwo.blue
    userEntityOne.cyan shouldEqual userEntityTwo.cyan
    userEntityOne.magenta shouldEqual userEntityTwo.magenta
    userEntityOne.yellow shouldEqual userEntityTwo.yellow
    userEntityOne.black shouldEqual userEntityTwo.black

  }

  @Test
  fun shouldDesearialize() {
    val userEntity = serializer.deserialize(JSON_RESPONSE, Example::class.java)

    userEntity.red shouldEqual "#f00"
    userEntity.green shouldEqual "#0f0"
    userEntity.blue shouldEqual "#00f"
    userEntity.cyan shouldEqual "#0ff"
    userEntity.magenta shouldEqual "#f0f"
    userEntity.yellow shouldEqual "#ff0"
    userEntity.black shouldEqual "#000"
  }

  inner class Example(var red: String, var green: String, var blue: String, var cyan: String,
      var magenta: String, var yellow: String, var black: String)
}

