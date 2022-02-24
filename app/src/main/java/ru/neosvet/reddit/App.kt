package ru.neosvet.reddit

import android.app.Application
import org.koin.core.context.startKoin
import ru.neosvet.reddit.di.appModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}