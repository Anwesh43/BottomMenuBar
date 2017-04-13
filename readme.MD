## BottomMenuBar

### Android library to add a bottom menu bar in Android

### Usage

#### Adding the module in build.gradle

````
    dependencies {
        compile project(':bottommenubar')
    }
````

#### Creating  BottomMenuBar in Activity

```
    BottomMenuBar bottomMenuBar = new BottomMenuBar(this);
```

#### Adding a option by passing a string and a listener to bottomMenuBar

```
    bottomMenuBar.addOption("Order",new OnMenuClickListener() {
        public void onMenuClick() {

        }
    });
```

#### Showing the menubar

```
    bottomMenuBar.show();
```

### Screencast of the demo

<img src="https://github.com/Anwesh43/BottomMenuBar/blob/master/screencast/bottommenubar2.gif" width="300px" height="600px" alt="Screencast of demo">
