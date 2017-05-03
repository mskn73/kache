package com.mskn73.kache;

import com.google.gson.Gson;

public class Serializer {

  private final Gson gson = new Gson();

  Serializer() {
  }

  public String serialize(Object object, Class clazz) {
    return gson.toJson(object, clazz);
  }

  public <T> T deserialize(String string, Class<T> clazz) {
    return gson.fromJson(string, clazz);
  }
}
