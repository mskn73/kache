package com.mskn73.kache;

import com.google.gson.Gson;

/**
 * Json Serializer/Deserializer.
 */
public class Serializer {

  private final Gson gson = new Gson();

  Serializer() {}

  /**
   * Serialize an object to Json.
   *
   * @param object to serialize.
   */
  public String serialize(Object object, Class clazz) {
    return gson.toJson(object, clazz);
  }

  /**
   * Deserialize a json representation of an object.
   *
   * @param string A json string to deserialize.
   */
  public <T> T deserialize(String string, Class<T> clazz) {
    return gson.fromJson(string, clazz);
  }
}
