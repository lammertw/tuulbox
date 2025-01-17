[![codecov](https://codecov.io/gh/JuulLabs/tuulbox/branch/main/graph/badge.svg?token=24ilSLPwN2)](https://codecov.io/gh/JuulLabs/tuulbox)
[![Slack](https://img.shields.io/badge/Slack-%23juul--libraries-ECB22E.svg?logo=data:image/svg+xml;base64,PHN2ZyB2aWV3Qm94PSIwIDAgNTQgNTQiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+PGcgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj48cGF0aCBkPSJNMTkuNzEyLjEzM2E1LjM4MSA1LjM4MSAwIDAgMC01LjM3NiA1LjM4NyA1LjM4MSA1LjM4MSAwIDAgMCA1LjM3NiA1LjM4Nmg1LjM3NlY1LjUyQTUuMzgxIDUuMzgxIDAgMCAwIDE5LjcxMi4xMzNtMCAxNC4zNjVINS4zNzZBNS4zODEgNS4zODEgMCAwIDAgMCAxOS44ODRhNS4zODEgNS4zODEgMCAwIDAgNS4zNzYgNS4zODdoMTQuMzM2YTUuMzgxIDUuMzgxIDAgMCAwIDUuMzc2LTUuMzg3IDUuMzgxIDUuMzgxIDAgMCAwLTUuMzc2LTUuMzg2IiBmaWxsPSIjMzZDNUYwIi8+PHBhdGggZD0iTTUzLjc2IDE5Ljg4NGE1LjM4MSA1LjM4MSAwIDAgMC01LjM3Ni01LjM4NiA1LjM4MSA1LjM4MSAwIDAgMC01LjM3NiA1LjM4NnY1LjM4N2g1LjM3NmE1LjM4MSA1LjM4MSAwIDAgMCA1LjM3Ni01LjM4N20tMTQuMzM2IDBWNS41MkE1LjM4MSA1LjM4MSAwIDAgMCAzNC4wNDguMTMzYTUuMzgxIDUuMzgxIDAgMCAwLTUuMzc2IDUuMzg3djE0LjM2NGE1LjM4MSA1LjM4MSAwIDAgMCA1LjM3NiA1LjM4NyA1LjM4MSA1LjM4MSAwIDAgMCA1LjM3Ni01LjM4NyIgZmlsbD0iIzJFQjY3RCIvPjxwYXRoIGQ9Ik0zNC4wNDggNTRhNS4zODEgNS4zODEgMCAwIDAgNS4zNzYtNS4zODcgNS4zODEgNS4zODEgMCAwIDAtNS4zNzYtNS4zODZoLTUuMzc2djUuMzg2QTUuMzgxIDUuMzgxIDAgMCAwIDM0LjA0OCA1NG0wLTE0LjM2NWgxNC4zMzZhNS4zODEgNS4zODEgMCAwIDAgNS4zNzYtNS4zODYgNS4zODEgNS4zODEgMCAwIDAtNS4zNzYtNS4zODdIMzQuMDQ4YTUuMzgxIDUuMzgxIDAgMCAwLTUuMzc2IDUuMzg3IDUuMzgxIDUuMzgxIDAgMCAwIDUuMzc2IDUuMzg2IiBmaWxsPSIjRUNCMjJFIi8+PHBhdGggZD0iTTAgMzQuMjQ5YTUuMzgxIDUuMzgxIDAgMCAwIDUuMzc2IDUuMzg2IDUuMzgxIDUuMzgxIDAgMCAwIDUuMzc2LTUuMzg2di01LjM4N0g1LjM3NkE1LjM4MSA1LjM4MSAwIDAgMCAwIDM0LjI1bTE0LjMzNi0uMDAxdjE0LjM2NEE1LjM4MSA1LjM4MSAwIDAgMCAxOS43MTIgNTRhNS4zODEgNS4zODEgMCAwIDAgNS4zNzYtNS4zODdWMzQuMjVhNS4zODEgNS4zODEgMCAwIDAtNS4zNzYtNS4zODcgNS4zODEgNS4zODEgMCAwIDAtNS4zNzYgNS4zODciIGZpbGw9IiNFMDFFNUEiLz48L2c+PC9zdmc+&labelColor=611f69)](https://kotlinlang.slack.com/messages/juul-libraries/)

# Tuulbox

Toolbox of utilities/helpers for Kotlin development.

## [Collections](https://juullabs.github.io/tuulbox/collections/index.html)

![badge-ios]
![badge-js]
![badge-jvm]
![badge-mac]

### [`SynchronizedMap`]

Provides a map where read and write operations are protected using a reentrant lock, allowing mutation from multiple
threads without fear of data corruption.

Use `SynchronizedMap.synchronize` when multiple read and write operations need to happen atomically, such as when
performing a `getOrPut`. 

```kotlin
// Creating a synchronized map
val map = SynchronizedMap(mutableMapOf("key" to "value"))
// Synchronize across multiple operations
val value = map.synchronized { getOrPut("key") { "defaultValue" } }
```

### [`Map.toJsObject`]

Convert a map to a Plain Old JavaScript Object by transforming the keys to strings and the values to any of the JavaScript types.

`Map` is not directly accessible from Javascript code. For example:
```kotlin
val simple = mapOf(1 to "a")
val json = js("JSON.stringify(simple)") as String
println(json)
>>>
{
  "_keys_up5z3z$_0": null,
  "_values_6nw1f1$_0": null,
  <and lots of other name-mangled properties>
}

val value = js("simple['1']") as String
println(value)
>>> ClassCastException: Illegal cast
```

Using this convertor to emit `Object` with the expected properties:
```kotlin
val simple = mapOf(1 to "a").toJsObject { it.key.toString() to it.value }
val json = js("JSON.stringify(simple)") as String
println(json)
>>> {"1":"a"}

val value = js("simple['1']") as String
println(value)
>>> a
```

## [Coroutines](https://juullabs.github.io/tuulbox/coroutines/index.html)

![badge-android]
![badge-ios]
![badge-js]
![badge-jvm]
![badge-mac]

Utilities for [Coroutines].

### [`combine`]

[`combine`][coroutines-combine] for up to 10 [`Flow`]s:

```kotlin
val combined = combine(
    flow1,
    flow2,
    // ...
    flow9,
    flow10,
) { value1, value2, /* ... */, value9, value10 ->
    // ...
}
```

## [Logging](https://juullabs.github.io/tuulbox/logging/index.html)

![badge-ios]
![badge-js]
![badge-jvm]
![badge-mac]

Simple multiplatform logging API.

### Initialization

Logging can be initialized via [`install`]:

```kotlin
Log.dispatcher.install(ConsoleLogger)
```

If no [`Logger`] is installed, then log blocks are not called at runtime.

Custom loggers can be created by implementing the [`Logger`] interface.

#### Android (Logcat)

Because Kotlin Multiplatform does not yet nicely support Android and JVM as a hierarchical dependency, Logcat logging
is provided as a separate artifact.

```kotlin
implementation("com.juul.tuulbox:logging-android:$version")
```

After adding the Gradle dependency, it can be installed as follows.

```kotlin
Log.dispatcher.install(AndroidLogger)
```

#### Apple (NSLog)

Log to the Apple System Log by installing the `AppleSystemLogger`.

```kotlin
Log.dispatcher.install(AppleSystemLogger)
```

### Log

Log message can be logged via:

```kotlin
Log.verbose { "Example" }
```

The following [log level] functions are available:
- [`verbose`]
- [`debug`]
- [`info`]
- [`warn`]
- [`error`]
- [`assert`]

Optional `tag` and `throwable` may be provided. `tag` defaults to an autogenerated value, but behavior can be customized
via [`Log.tagGenerator`] property.

### Metadata

Tuulbox logging supports associating arbitrary metadata to each log. A [`WriteMetadata`] instance is passed to each of
the logging functions above.

```kotlin
Log.verbose { metadata ->
    metadata[Sensitivity] = Sensitivity.Sensitive
    "My social security number is 123 45 6789"
}
```

This can be read inside of a [`Logger`] via the [`ReadMetadata`] passed into it.

```kotlin
class SampleLogger : Logger {
    override fun verbose(tag: String, message: String, metadata: ReadMetadata, throwable: Throwable?) {
        if (metadata[Sensitivity] != Sensitivity.Sensitive) {
            // report to a destination that cannot include sensitive data
        }
    }

    // ...
}
```

To create your own metadata fields, use an `object` that implements the [`Key`] interface. The value of the generic
controls the type of the value in the metadata map.

```kotlin
object YourMetadata : Key<String>
```

A helpful pattern for many types is using the `companion object` as the metadata key.

```kotlin
enum class Sample {
    A, B, C;
    companion object : Key<Sample>
}
```

### Filtering

Tuulbox implements log filtering by decorating [`Logger`]s. 

#### Log Level Filters

Log level filters are installed with [`Logger.withMinimumLogLevel`]. Because the filtering is based on which log call is
made, instead of the content of the log call, these can be used as an optimization: if all [`Logger`]s installed in the
root [`DispatchLogger`] have a minimum log level higher than the log call being made, then the log block is never called.

```kotlin
Log.dispatcher.install(
    ConsoleLogger
        .withMinimumLogLevel(LogLevel.Warn)
)

Log.debug { "This is not called." }
Log.warn { "This still gets called." }
```

#### Log Content Filters

Log content filters are installed with [`Logger.withFilter`], and have full access to the content of a log.


```kotlin
Log.dispatcher.install(
    ConsoleLogger
        .withFilter { tag, message, metadata, throwable ->
            metadata[Sensitivity] == Sensitivity.NotSensitive
        }
)

Log.debug { "This block is evaluated, but does not get printed to the console." }
Log.warn { metadata ->
    metadata[Sensitivity] = Sensitivity.NotSensitive
    "This is also evaluated, and does print to the console."
}
```

## [Functional](https://juullabs.github.io/tuulbox/functional/index.html)

![badge-ios]
![badge-js]
![badge-jvm]
![badge-mac]

Utilities for manipulating functions.
For a full functional ecosystem, complete with `Monad` and the like, prefer [Arrow](https://arrow-kt.io/).

## [Temporal](https://juullabs.github.io/tuulbox/temporal/index.html)

![badge-android]
![badge-js]
![badge-jvm]

Toolbox of utilities for dates and times, building on [KotlinX DateTime].

Various interval [`Flow`]s are provided, such as: [`instantFlow`], [`localDateTimeFlow`], and [`localDateFlow`]. For example:

```kotlin
localDateTimeFlow().collect {
    println("The time is now: $it")
}
```

_Note: Because this is built on top of [KotlinX DateTime], [core library desugaring] must be enabled for Android targets._

## [Test](https://juullabs.github.io/tuulbox/test/index.html)

![badge-ios]
![badge-js]
![badge-jvm]
![badge-mac]

Utilities for test suites.

### [`runTest`]

Multiplatform test analogy to [`runBlocking`]:

```kotlin
@Test
fun exampleUnitTest() = runTest {
    // ...
}
```

### [`assertContains`]

```kotlin
assertContains(
    array = arrayOf(1, 2, 3),
    value = 2,
)
```

```kotlin
assertContains(
    range = 1..10,
    value = 5,
)
```

### [`assertSimilar`]

```kotlin
assertSimilar(
    target = 5,
    epsilon = 1,
    value = 6,
)
```

## [Encoding](https://juullabs.github.io/tuulbox/encoding/index.html)

![badge-ios]
![badge-js]
![badge-jvm]
![badge-mac]

Utilities for working with binary data.

### [`IntBitSet`]

```kotlin
val bitSet = 0.bits
bitSet[30] = true
bitSet.asPrimitive() // 1073741824
```

```kotlin
/* | Index | ... | 3 | 2 | 1 | 0 |
 * |-------|-----|---|---|---|---|
 * | Bit   | ... | 1 | 0 | 1 | 0 |
 */
val bitSet = 10.bits

bitSet[0] // false
bitSet[1] // true
bitSet[2] // false
bitSet[3] // true
```

### [`LongBitSet`]

```kotlin
/* | Index | ... | 3 | 2 | 1 | 0 |
 * |-------|-----|---|---|---|---|
 * | Bit   | ... | 1 | 1 | 0 | 0 |
 */
val bitSet = 12L.bits

bitSet[0] // false
bitSet[1] // false
bitSet[2] // true
bitSet[3] // true
```

```kotlin
val bitSet = 0L.bits
bitSet[40] = true
bitSet.asPrimitive() // 1099511627776L
```

# Setup

### Gradle

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.juul.tuulbox/coroutines/badge.svg)](https://search.maven.org/search?q=g:com.juul.tuulbox)

Tuulbox can be configured via Gradle Kotlin DSL as follows:

#### Multiplatform

```kotlin
plugins {
    id("com.android.application") // or id("com.android.library")
    kotlin("multiplatform")
}

repositories {
    mavenCentral()
}

kotlin {
    jvm() // and/or android()
    js().browser() // and/or js().node()
    macosX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.juul.tuulbox:collections:$version")
                implementation("com.juul.tuulbox:coroutines:$version")
                implementation("com.juul.tuulbox:logging:$version")
                implementation("com.juul.tuulbox:functional:$version")
                implementation("com.juul.tuulbox:temporal:$version")
                implementation("com.juul.tuulbox:encoding:$version")
            }
        }
        
        val commonTest by getting {
            dependencies {
                implementation("com.juul.tuulbox:test:$version")
            }
        }
    }
}
```

#### Platform-specific

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.juul.tuulbox:collections:$version")
    implementation("com.juul.tuulbox:coroutines:$version")
    implementation("com.juul.tuulbox:logging:$version")
    implementation("com.juul.tuulbox:functional:$version")
    implementation("com.juul.tuulbox:temporal:$version")
    implementation("com.juul.tuulbox:encoding:$version")
    testImplementation("com.juul.tuulbox:test:$version")
}
```

# License

```
Copyright 2021 JUUL Labs, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


[Collections]: https://kotlinlang.org/docs/reference/collections-overview.html
[`SynchronizedMap`]: https://juullabs.github.io/tuulbox/collections/collections/com.juul.tuulbox.collections/-synchronized-map/index.html 
[`Map.toJsObject`]: https://juullabs.github.io/tuulbox/collections/collections/com.juul.tuulbox.collections/to-js-object.html
[Coroutines]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[KotlinX DateTime]: https://github.com/Kotlin/kotlinx-datetime
[core library desugaring]: https://developer.android.com/studio/write/java8-support#library-desugaring
[coroutines-combine]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/combine.html
[`Flow`]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/index.html
[`runBlocking`]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/run-blocking.html
[`combine`]: https://juullabs.github.io/tuulbox/coroutines/coroutines/com.juul.tuulbox.coroutines.flow/combine.html
[`Logger`]: https://juullabs.github.io/tuulbox/logging/logging/com.juul.tuulbox.logging/-logger/index.html
[`install`]: https://juullabs.github.io/tuulbox/logging/logging/com.juul.tuulbox.logging/-dispatch-logger/install.html
[log level]: https://juullabs.github.io/tuulbox/logging/logging/com.juul.tuulbox.logging/-log/index.html
[`Log.tagGenerator`]: https://juullabs.github.io/tuulbox/logging/logging/com.juul.tuulbox.logging/-log/tag-generator.html
[`verbose`]: https://juullabs.github.io/tuulbox/logging/logging/com.juul.tuulbox.logging/-log/verbose.html
[`debug`]: https://juullabs.github.io/tuulbox/logging/logging/com.juul.tuulbox.logging/-log/debug.html
[`info`]: https://juullabs.github.io/tuulbox/logging/logging/com.juul.tuulbox.logging/-log/info.html
[`warn`]: https://juullabs.github.io/tuulbox/logging/logging/com.juul.tuulbox.logging/-log/warn.html
[`error`]: https://juullabs.github.io/tuulbox/logging/logging/com.juul.tuulbox.logging/-log/error.html
[`assert`]: https://juullabs.github.io/tuulbox/logging/logging/com.juul.tuulbox.logging/-log/assert.html
[`WriteMetadata`]: https://juullabs.github.io/tuulbox/logging/logging/com.juul.tuulbox.logging/-write-metadata/index.html
[`ReadMetadata`]: https://juullabs.github.io/tuulbox/logging/logging/com.juul.tuulbox.logging/-read-metadata/index.html
[`Key`]: https://juullabs.github.io/tuulbox/logging/logging/com.juul.tuulbox.logging/-key/index.html
[`Logger.withMinimumLogLevel`]: https://juullabs.github.io/tuulbox/logging/logging/com.juul.tuulbox.logging/with-minimum-log-level.html
[`DispatchLogger`]: https://juullabs.github.io/tuulbox/logging/logging/com.juul.tuulbox.logging/-dispatch-logger/index.html
[`Logger.withFilter`]: https://juullabs.github.io/tuulbox/logging/logging/com.juul.tuulbox.logging/with-filter.html
[`runTest`]: https://juullabs.github.io/tuulbox/test/test/com.juul.tuulbox.test/run-test.html
[`assertContains`]: https://juullabs.github.io/tuulbox/test/test/com.juul.tuulbox.test/assert-contains.html
[`assertSimilar`]: https://juullabs.github.io/tuulbox/test/test/com.juul.tuulbox.test/assert-similar.html
[`IntBitSet`]: https://juullabs.github.io/tuulbox/encoding/encoding/com.juul.tuulbox.encoding/-int-bit-set/index.html
[`LongBitSet`]: https://juullabs.github.io/tuulbox/encoding/encoding/com.juul.tuulbox.encoding/-long-bit-set/index.html
[`instantFlow`]: https://juullabs.github.io/tuulbox/temporal/temporal/com.juul.tuulbox.temporal/instant-flow.html
[`localDateFlow`]: https://juullabs.github.io/tuulbox/temporal/temporal/com.juul.tuulbox.temporal/local-date-flow.html
[`localDateTimeFlow`]: https://juullabs.github.io/tuulbox/temporal/temporal/com.juul.tuulbox.temporal/local-date-time-flow.html

[badge-android]: http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat
[badge-ios]: http://img.shields.io/badge/platform-ios-CDCDCD.svg?style=flat
[badge-js]: http://img.shields.io/badge/platform-js-F8DB5D.svg?style=flat
[badge-jvm]: http://img.shields.io/badge/platform-jvm-DB413D.svg?style=flat
[badge-linux]: http://img.shields.io/badge/platform-linux-2D3F6C.svg?style=flat
[badge-windows]: http://img.shields.io/badge/platform-windows-4D76CD.svg?style=flat
[badge-mac]: http://img.shields.io/badge/platform-macos-111111.svg?style=flat
[badge-watchos]: http://img.shields.io/badge/platform-watchos-C0C0C0.svg?style=flat
[badge-tvos]: http://img.shields.io/badge/platform-tvos-808080.svg?style=flat
[badge-wasm]: https://img.shields.io/badge/platform-wasm-624FE8.svg?style=flat
