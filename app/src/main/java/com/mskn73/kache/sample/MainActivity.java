package com.mskn73.kache.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.mskn73.kache.Kache;
import com.mskn73.kache.Kacheable;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Kache k = new Kache(getApplicationContext(), getCacheDir().toString());
    k.put(new MyObject());
    Kacheable myKachedObject = (Kacheable) k.get(MyObject.class, "asd");

    Log.v("Obj", myKachedObject.getKey());
  }

  class MyObject implements Kacheable {
    @NotNull @Override public String getKey() {
      return "asd";
    }
  }
}
