# KACHE a Cache lib made with Kotlin    
[![Build Status](https://travis-ci.org/mskn73/kache.svg?branch=master)](https://travis-ci.org/mskn73/kache)  
[![codecov](https://codecov.io/gh/mskn73/kache/branch/master/graph/badge.svg)](https://codecov.io/gh/mskn73/kache)
[![](https://jitpack.io/v/mskn73/kache.svg)](https://jitpack.io/#mskn73/kache)

*Still in development*

## Add the dependency

1. Add jitpack in your root build.gradle at the end of repositories
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
2. Add the dependency [![](https://jitpack.io/v/mskn73/kache.svg)](https://jitpack.io/#mskn73/kache)

```
dependencies {
  compile "com.github.mskn73:kache:${kacheVersion}"
}
```

## How to use
### Kotlin example
1. Implement the Kacheable interface for your class
```kotlin
internal inner class MyObject(override val key: String) : Kacheable
```

If you want to specifies the expiration time, annotates the class with `KacheLife:
```kotlin
@KacheLife(expiresTime = (60 * 1000).toLong())
internal inner class MyObject(override val key: String) : Kacheable
```

2. Save and retrieve the object:
```kotlin
//Instantiate Kache
val k = Kache(applicationContext, cacheDir.toString())
//Save data
k.put(MyObject("fake@mail.com"))
//Get the data
val myKachedObject = k.get(MyObject::class.java, "fake@mail.com") as Kacheable
```
### Java example
1. Implement the Kacheable interface for your class
```java
class MyObject implements Kacheable {
  private final String mail;

  public MyObject(String mail) {
    this.mail = mail;
  }

  @NotNull @Override public String getKey() {
    return mail;
  }
}
```

If you want to specifies the expiration time, annotates the class with `KacheLife:
```java

@KacheLife(expiresTime = 60 * 1000)
class MyObject implements Kacheable {
  private final String mail;

  public MyObject(String mail) {
    this.mail = mail;
  }

  @NotNull @Override public String getKey() {
    return mail;
  }
}
```

2. Save and retrieve the object:
```java
//Instantiate Kache
Kache k = new Kache(getApplicationContext(), getCacheDir().toString());
//Save data
k.put(new MyObject("fake@mail.com"));
//Get the data
Kacheable myKachedObject = (Kacheable) k.get(MyObject.class, "fake@mail.com");
```