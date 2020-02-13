# GifSearch

Run it!!
Small app that makes requesto to GIPHY serach endpoint with the query introduced by the user. Press enter or the action key in your keyboard, or just wait for it to be done! 
Here you can check a [demo](demo.gif)


Dependencies:
## Networking
```
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.squareup.retrofit2:retrofit:2.7.1'
    implementation 'com.squareup.retrofit2:converter-gson:2.7.1'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.2.1'
```
## RX
```
    implementation 'io.reactivex.rxjava2:rxjava:2.2.2'
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.4.0'
    implementation 'com.jakewharton.rxrelay2:rxrelay:2.1.1'
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
```
- [RXRelay](https://github.com/JakeWharton/RxRelay) Library used to taid togueter non RX API with RX. 

## Glide
```
    implementation 'com.github.bumptech.glide:glide:4.11.0'
```

Things to improve
- Dagger, No dependency injection in this Coding Challenge, I would´ve added it for testing
- First thing layout, it´s using a simple linear layout so the look & feel is quite awful. In case of having more time I´ll implement a layout like Telegram.
- Pagination for the query, it wasn´t a requirement but it would have been nice doing that part.
