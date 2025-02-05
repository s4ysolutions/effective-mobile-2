## Architecture

The same as in the [previous demo](https://github.com/s4ysolutions/effective-mobile)


## Used Technologies

#### Retrofit
   - [interceptor](https://github.com/s4ysolutions/effective-mobile-2) to get JSON response from the assets instead of WEB

####  RxJava
  - [use RxJava3CallAdapterFactory]([RxJava3CallAdapterFactory](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/data-retrofit/src/main/java/com/example/effectivem2/data/retrofit/JobsRetrofitClient.kt#L67C25-L67C83)) to expose API as [RxJava Singles]([RxJava3CallAdapterFactory](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/data-retrofit/src/main/java/com/example/effectivem2/data/retrofit/JobsRetrofitClient.kt#L15C1-L20C54))
    and [emulate network delays](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/data-retrofit/src/main/java/com/example/effectivem2/data/retrofit/RetrofitJobsProvider.kt#L19C10-L19C67).

#### Gson
  - [convert](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/data-retrofit/src/main/java/com/example/effectivem2/data/retrofit/JobsRetrofitClient.kt#L66C25-L66C76) JSON responses to [DTO](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/data-retrofit/src/main/java/com/example/effectivem2/data/retrofit/beans/JsonVacancy.kt#L25C1-L42C10)

#### Deep links 
  - [Inter-features](https://github.com/s4ysolutions/effective-mobile-2/tree/main/features) navigations with [intents](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/shared/src/main/java/solutions/s4y/effectivem/views/BaseActivity.kt#L19C1-L25C14) to the features activity [destinations](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/features/vacancies/src/main/AndroidManifest.xml#L17C1-L20C57). 

#### Navigation

  - Passing [arguments](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/features/vacancies/src/main/res/navigation/navigation_vacancies.xml#L45C1-L47C75) between destinations in a [bundle](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/features/vacancies/src/main/java/com/example/effectivem2/vacancies/screens/search/SearchFragment.kt#L59C1-L61C59)

#### XML Layout
  - Using androidx.constraintlayout.widget.Barrier to line up the items
  - android:fitsSystemWindows="true" to obey safe area

#### Theming 
  - the UI is completely styled with the custom [styles](https://github.com/s4ysolutions/effective-mobile-2/blob/main/shared/src/main/res/values/styles.xml) that are assigned to 
    [Material UI theme](https://github.com/s4ysolutions/effective-mobile-2/blob/main/shared/src/main/res/values/themes.xml) and [custom attributes](https://github.com/s4ysolutions/effective-mobile-2/blob/main/shared/src/main/res/values/attrs.xml)
  - color selectors. Color selectors are used to change the presentation of enabled/disable state leveraging [theme attributes](https://github.com/s4ysolutions/effective-mobile-2/blob/main/shared/src/main/res/color/button_primary_state.xml).

#### Platform independent kotlin library
  - Domain level implemented as [pure kotlin module](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/domain/build.gradle.kts#L1C1-L5C2) and does not depend on Android platform. This way it can be shared with iOS/Web targets.

#### Inline/values classes
  - Since the give JSON data prvides different id formats the entiry ID is abstracted to its own type implemented with Kotlins value classes https://github.com/s4ysolutions/effective-mobile-2/blob/main/data/src/main/java/com/example/effectivem2/data/dto/Id.kt

#### Dagger/Hilt
  - DI implemented with Hilt for android modules and Dagger2 for pure kotlin ones

#### Programmatically creating elements
  - For adding list of buttons with the questions dynamically the  MaterialButton instances are created [by code](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/features/vacancies/src/main/java/com/example/effectivem2/vacancies/screens/vacancy/VacancyFragment.kt#L143C17-L147C18) and its attributes
    are evaluated from the [theme attributes](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/features/vacancies/src/main/java/com/example/effectivem2/vacancies/screens/vacancy/VacancyFragment.kt#L129C1-L141C14).
  
#### Room
  - [Using the room](https://github.com/s4ysolutions/effective-mobile-2/tree/main/data-room/src/main/java/com/example/effectivem2/data/room) for saving favorites vacancies is over-endgeeniring but still applied for demo purposes. Alternatvie approach with Data was used in the [previous demo](https://github.com/s4ysolutions/effective-mobile/blob/main/feature/flight-tickets/src/main/java/solutions/s4y/effectivem/flight_tickets/views/PersistedState.kt)

#### Futures/Suspended/Flow APIs
  - For demo purposes different APIs are implemented with different technologies and the converted to each other
    - [Future from callback](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/data-retrofit/src/main/java/com/example/effectivem2/data/retrofit/RetrofitCoordinatesProvider.kt#L42C1-L42C67)
    - [Future to suspended](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/domain/src/main/java/com/example/effectivem2/domain/GeoService.kt#L12C1-L21C6)
    - RxJava Single [to Flow](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/domain/src/main/java/com/example/effectivem2/domain/JobsService.kt#L51C6-L52C1)
      and [StateFlow](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/domain/src/main/java/com/example/effectivem2/domain/JobsService.kt#L81C1-L81C46)

#### Nominatim
  - to [translate an address to geo locations](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/data-retrofit/src/main/java/com/example/effectivem2/data/retrofit/RetrofitCoordinatesProvider.kt#L20C1-L20C88) as an alternative to Yandex search API (i do not have API key)

#### Webview
  - Use to [show Leaflet map](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/features/vacancies/src/main/java/com/example/effectivem2/vacancies/screens/vacancy/VacancyFragment.kt#L116C1-L118C93)
     as an alternative to Yandex search API (i do not have API key)
  - [passing click form webview to android host](https://github.com/s4ysolutions/effective-mobile-2/blob/6ad90cdd51ded69fd18da9ad00b478e18b47fa0b/features/vacancies/src/main/java/com/example/effectivem2/vacancies/screens/vacancy/VacancyFragment.kt#L107C1-L107C77)

#### calling external app
  - call for an [applicaiton able to show maps](https://github.com/s4ysolutions/effective-mobile-2/blob/791e32069408d9e6e3b5698c498440dee02c85a6/features/vacancies/src/main/java/com/example/effectivem2/vacancies/screens/vacancy/VacancyFragment.kt#L174C1-L177C1)

#### Plurals resources
  - Localized [plural forms for string](https://github.com/s4ysolutions/effective-mobile-2/blob/13aab168cf1b7ed2e9505cf56a9edb8aabddfe3c/features/vacancies/src/main/res/values-ru/strings.xml#L22C1-L52C15)
