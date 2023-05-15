# Innowacja_Accenture_WeatherApp
Podstawowa applikacja webowa korzystająca z OpenWeatherApi. Pozwala użytkownikowi przeglądać aktualne i prognozowane informacje o pogodzie dla określonej lokalizacji oraz może zasugerować aktywności oparte na pogodzie w danej lokalizacji. 
Aplikacja wykorzystuje baze danych PostgreSQL, Spring Boot jako framework backendowy oraz vue.js jako framework frontendowy.

Używane bilioteki:
- axios (requesty HTTP) 
- ~~Vue-router(Nawigacja po komponentach bez odświeżania strony)~~
- Java Persistence API (Zarządzanie obiektami/klasami i relacyjną bazą danych. Mapowanie klas .java do tabel bazy danych)
- Lombok (automatyczna generacja boilerplate kodu np. getterów, setterów i construktorów)
- org.json (parsowanie i generowanie danych JSON)

## API 
GET http://localhost:8080/api/v1/weather/current?city={city}   
Pobiera aktualne informacje o pogodzie dla określonego miasta.  
<br>
GET http://localhost:8080/api/v1/weather/current/current/cords?latitude={lat}&longitude={long}     
Pobiera aktualne informacje o pogodzie dla określonej wysokości i szerokości geograficznej.   
<br>
GET http://localhost:8080/api/v1/weather/forecast?city={city}    
Pobiera 5-dniową prognozę pogody dla określonego miasta.     
<br>
GET http://localhost:8080/api/v1/activity/suggestion?city={city}    
Pobiera listę sugerowanych aktywności dla określonego miasta w oparciu o aktualne warunki pogodowe.
<br>
<br>
Aplikacja używa Data Transfer Objects (DTO) do komunikacji pomiędzy frontendem a backendem. Np:
```java
package com.example.weatherapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WeatherDto {
    private int temperature;
    private int windSpeed;
    private int humidity;
    private String description;
    private LocalDate dateTime;
    private int feelsLikeTemperature;
    private String city;
}
```
Dane pobrane z API pogodowego są modyfikowane i parsowane do określonego formatu przed wysłaniem do użytkownika.
Tutaj nie zbyt ładne setowanie do klasy Weather.
```java 
 public Weather setWeatherFromJsonObject(JSONObject jsonObject) {
        this.setHumidity((int) ((JSONObject) jsonObject.get("main")).get("humidity"));
        this.setTemperature((int) Math.ceil( Double.parseDouble(((JSONObject) jsonObject.get("main")).get("temp").toString())));
        this.setDateTime(jsonObject.get("dt").toString());
        this.setDescription(((JSONObject) ((JSONArray) jsonObject.get("weather")).get(0)).get("main").toString().toUpperCase());
        this.setWindSpeed((int) Math.ceil( Double.parseDouble((((JSONObject) jsonObject.get("wind")).get("speed").toString()))));
        this.setFeelsLikeTemperature((int) Math.ceil( Double.parseDouble(((JSONObject) jsonObject.get("main")).get("feels_like").toString())));
        return this;
    }
```

## Odpalenie aplikacji
### Backend

1. Sklonuj repozytorium

`git clone https://github.com/username/weather-app.git`

2. Utwórz nową bazę danych w PostgreSQL i zaktualizuj właściwości aplikacji w `src/main/resources/application.properties` o odpowiedni adres URL bazy danych, nazwę użytkownika i hasło.
3. Uruchom polecenie `mvn spring-boot:run`, aby uruchomić aplikację Spring Boot.

### Frontend

1. Przejdź do katalogu `frontend`
2. Zainstaluj dependencje uruchamiając `npm install`.
3. Uruchom serwer uruchamiając `npm run serve` lub `vite dev`.



## Część wizualna
![image](https://user-images.githubusercontent.com/86847471/213948275-b9faf5cd-d1a4-4ee7-828e-8b5433b8663c.png)
![image](https://user-images.githubusercontent.com/86847471/213948537-c2147a9e-96be-4b42-9c30-8254f3e1bf45.png)



## Model bazy danych (Użytkownik będzie dodany w późniejszej eskalacji aplikacji)
![image](https://user-images.githubusercontent.com/86847471/213948067-9bbcfd14-79b5-4bc6-963b-0888f10cbfbf.png)

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Vue.js](https://vuejs.org/) 
* [PostgreSQL](https://www.postgresql.org/)
