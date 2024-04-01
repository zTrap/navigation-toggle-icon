# Navigation toggle icon

[Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) icon which have 3 states (burger, arrow and cross) and animated transitions between them

[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
[![Download](https://img.shields.io/maven-central/v/ru.ztrap/navigation-toggle-icon?style=flat)](https://central.sonatype.com/artifact/ru.ztrap/navigation-toggle-icon)

Supported platforms:
![badge](https://img.shields.io/badge/-android-6EDB8D.svg?style=flat)
![badge](https://img.shields.io/badge/-ios-CDCDCD.svg?style=flat)
![badge](https://img.shields.io/badge/-wasm-624FE8.svg?style=flat)
![badge](https://img.shields.io/badge/-jvm-DB413D.svg?style=flat)

## Install

```gradle
implementation 'ru.ztrap:navigation-toggle-icon:${latestVersion}'
```

## Sample

![sample](.github/animations-sample.gif)

## Usage

1. Simple toggle icon

    ```kotlin
    var toggled by remember { mutableStateOf(false) }
    
    NavigationToggleIcon(
        modifier = Modifier.size(24.dp).clickable { toggled = !toggled },
        startIconType = Burger,
        endIconType = Arrow,
        toggled = toggled,
        contentDescription = null,
    )
    ```

2. Toggle icon with controlled progress

    ```kotlin
    val progress = // any source which produce progress in 0f..1f bounds
    
    NavigationToggleIcon(
        modifier = Modifier.size(24.dp),
        startIconType = Burger,
        endIconType = Arrow,
        progress = progress,
        contentDescription = null,
    )
    ```

## Developed By

- Peter Gulko
- ztrap.developer@gmail.com

## License

```
Copyright 2024 Peter Gulko (zTrap)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
