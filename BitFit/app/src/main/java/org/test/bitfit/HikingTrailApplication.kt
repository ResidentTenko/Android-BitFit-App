package org.test.bitfit

import android.app.Application

/**
 * Step 4: Creating instance of our database
 * To interact with our database we will need to get an instance of it!
 * We will only want to create this once per app, so we will use an Android Application class.
 * The Application class in Android is the base class within an Android app
 * that contains all other components such as activities and services.
 * A lazy initialization here just means we don't create this variable until it needs to be used.
 */
class HikingTrailApplication : Application() {
    val db by lazy{AppDatabase.getInstance(this)}

    /**
     * Whatever code we write inside this class will be executed everytime we launch the app
     * In this case we create a new instance of our database when the app is launched.
     * If the database already exists then the existing version is used and a new one isn't
     * created.
     */
}