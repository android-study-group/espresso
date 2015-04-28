# Espresso

Simple gradle project with Espresso and Spoon setup

To run the instrumentation tests with [Espresso](https://code.google.com/p/android-test-kit/wiki/Espresso)
you can use:

```./gradlew connectedAndroidTest```

In order to use [spoon](http://square.github.io/spoon/) to generate greater reports, the addition of the 
[spoon plugin](https://github.com/stanfy/spoon-gradle-plugin) gives one extra
gradle task that runs the instrumentation tests using the spoon runner, and generates an html report in
`app/build/spoon` output directory. To run with spoon:

```./gradlew spoon```
