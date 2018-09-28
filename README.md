# AndroidX Modern MVP
* Write <b>clean</b> and [SOLID](https://en.wikipedia.org/wiki/SOLID) code
* Eliminate <b>boiler plate</b> (write less, say more)
* Implement <b>MVP</b> (Model View Presenter) the easy way
* Also:
	* provides common simplified **lifecycle** for `Activity` and `Fragment`
	* supports <b>AndroidX</b>
    * written in <b>Kotlin</b>

ModernMVP is a <b>lightweight</b> (~6 classes) library that makes Android development <b>fast</b> and <b>easy</b>.<br>
No more memory leaks! No more common errors! No more 1000+ lines GOD activities without structure!<br>
Write <b>clean</b> and <b>concise</b> code using the MVP architecture and Kotlin.<br>
>NOTE: Currently Presenter don't survive screen rotations or orientation changes!
## Download
Add it in your **root** build.gradle at the end of repositories
```groovy
 allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Add the dependency in **app** build.gradle
```groovy
dependencies {
	implementation 'com.github.ILIYANGERMANOV:androidx-modern-mvp:1.0.1'
}
```
## Usage
* If you are using `com.android.support` check [appcompat version](https://github.com/ILIYANGERMANOV/android-modern-mvp)!

Here's a simple example for screen which displays the weather for a city.

```kotlin
data class Weather(val tempKelvin: Double, val condition: String)
```

### 0. Define the contract

```kotlin
interface WeatherContract {
    interface Presenter : BasePresenter {
        fun initScreen()

        fun displayWeather()
    }

    interface View : BaseView {
        fun useCelsius(): Boolean

        fun getCityInput(): String

        fun setCity(city: String)

        fun displayWeather(temp: Double, condition: String)

        fun showError()
    }

    interface Model {
        fun fetchWeather(city: String, callback: DataCallback<Weather>)
    }
}
```

### 1. Implement the Model

```kotlin
class WeatherModel(val appContext: Context) : WeatherContract.Model {
    override fun fetchWeather(city: String, callback: DataCallback<Weather>) {
        //Mocked weather service
        Handler().postDelayed({
            callback.onSuccess(Weather(303.15, "Sunny"))
        }, 500)
    }
}
```

### 2. Implement the Presenter

```kotlin
class WeatherPresenter(view: WeatherContract.View, val model: WeatherContract.Model)
    : Presenter<WeatherContract.View>(view), WeatherContract.Presenter {
    override fun initScreen() {
        view?.setCity("Sofia")
        displayWeather()
    }

    override fun displayWeather() {
        val city = view?.getCityInput() ?: return
        model.fetchWeather(city, object : DataCallback<Weather> {
            override fun onSuccess(data: Weather) {
                val useCelsius = view?.useCelsius() ?: return
                var temp = data.tempKelvin
                if (useCelsius) {
                    //transform temperature from Kelvin to Celsius
                    temp -= 273.15
                }
                view?.displayWeather(temp, data.condition)
            }

            override fun onError() {
                view?.showError()
            }
        })
    }
}
```

### 3. Implement the View

```kotlin
class WeatherActivity : MVPActivity<WeatherContract.Presenter>(), WeatherContract.View {
    override fun getContentLayout() = R.layout.activity_weather

    override fun initPresenter(applicationContext: Context, intent: Intent): WeatherContract.Presenter {
        return WeatherPresenter(this, WeatherModel(applicationContext))
    }

    override fun onSetupListeners() {
        btnRefresh.setOnClickListener { presenter.displayWeather() }
    }

    override fun onReady() {
        presenter.initScreen()
    }

    override fun useCelsius(): Boolean {
        return swCelsius.isChecked
    }

    override fun getCityInput() = etCity.text.toString()

    override fun setCity(city: String) {
        etCity.setText(city)
    }

    override fun displayWeather(temp: Double, condition: String) {
        tvWeather.text = "Temperature is $temp and condition is $condition."
    }

    override fun showError() {
        tvWeather.text = getString(R.string.err_fetch_data)
    }
}
```

### That's it!
Congrats you have implemented a fully working feature with solid architecture in less than 100 lines of code:ok_hand:<br>
For fragments just extend `MVPFragment`. Lifecycle is the same as `MVPActivity`.

## Lifecycle
The goal of ModernMVP is to cutoff the bullshit and let you focus on the real work.

### Mandatory methods to override

1. `@LayoutRes abstract fun getContentLayout(): Int` select content layout (e.g. *R.layout.activity_main*)
2. `initPresenter(applicationContext: Context, intent: Intent)` return an instance for your presenter

### Optional methods

* `onSetupUI()` here you can setup your UI programmatically. For example setup `RecyclerView`, underline `TextView` and so on...
* `onSetupListeners()` here you should attach your input and action listeners (Buttons, Input Fields, Gestures and etc.)
* `onReady()` here you should execute your screen's initial business logic

### Lifecycle itself

1. ` getContentLayout()`
2. `initPresenter(...)`
3. `onSetupUI()`
4. `onSetupListeners()`
5. `onStart()` this is from  `Activity` or `Fragment`
6. `onReady()`

### Customization

You can always extend the standard lifecycle callbacks `onResume()`, `onPause()` and etc. if needed.<br>
ModernMVP just provides you with a zero to none boiler plate for 90% of the common cases.
> NOTE: The most cool part is that **View** is lifecycle aware and you just make `view?.someMethod()` calls without thinking whether it is in right state in the moment!

## Try it out!
Your feedback will be highly appreciated! I accept pull requests:) If you find any bugs don't hesitate to raise a issue. If you need any features just send me a feature request. And have a nice, concise and SOLID Android Development!
