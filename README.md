gradle-url-cache-plugin
=======================
[![Build Status](https://travis-ci.org/kageiit/gradle-url-cache-plugin.svg)](https://travis-ci.org/kageiit/gradle-url-cache-plugin)

[Gradle](https://www.gradle.org) plugin that facilitates the caching of urls.

Usage
-----
The plugin is available on [Jcenter](https://bintray.com/bintray/jcenter)

To use the plugin with Gradle 2.1 or later, add the following to your 
build.gradle:

```groovy
plugins {
  id 'com.kageiit.url-cache' version '1.0.0'
}
```

To use the plugin with Gradle 2.0 or older, add the following to build.gradle:

```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath "com.kageiit:url-cache-plugin:1.0.0"
    }
}
apply plugin: 'com.kageiit.url-cache'
```

This plugin was created mainly to address the problem of not being able to cache shared build configuration files using `apply from:` . You can now simply do:

```groovy
apply plugin: 'com.kageiit.url-cache'
apply from: urlCache.get('http://example.com/shared-build-config.gradle')
```

It tries to fetch/refetch urls on every run. If the url could not be fetched or the `--offline` switch is used to start the build, the cached version is used instead. The cache is stored in the standard gradle user home `caches` directory.

License
-------

    Copyright 2014 Gautam Korlam

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
