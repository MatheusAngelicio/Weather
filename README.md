# Weather

Simple app to learn about openweather api and location


![openweather](https://user-images.githubusercontent.com/54027680/167178134-9e065221-f5d8-467c-a2fa-cc47fd38084a.jpg)



## API Reference: https://openweathermap.org/current#multi

#### Get weather data

```http
  GET /weather?lat&lon&appid

```


#### Example

```http
  https://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid={API key}

```

## To create your api key go to https://home.openweathermap.org/api_keys

| Parameter | Type     | 
| :-------- | :------- | 
| `coord`      | `Coord` | 
| `weather`      | `List<Weather>` | 
| `base`      | `String` | 
| `main`      | `Main` | 
| `visibility`      | `Long` | 
| `wind`      | `Wind` | 
| `clouds`      | `Clouds` | 
| `dt`      | `Long` | 
| `sys`      | `Sys` | 
| `timezone`      | `Long` | 
| `name`      | `String` | 
| `cod`      | `Long` | 


## 🚀 Technologies
* Kotlin
* Architecture MVVM
* Dependency injection - Koin
* LiveData
* Retrofit
* Moshi
* Coroutines
* Google Maps SDK



## Screenshots

![WhatsApp Image 2022-05-06 at 13 44 42](https://user-images.githubusercontent.com/54027680/167176893-d1d8caea-7ee8-4793-b1e2-f7238334e535.jpeg)


