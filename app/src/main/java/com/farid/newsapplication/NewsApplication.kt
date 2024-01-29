package com.farid.newsapplication

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/*
* This is the main application of the app.
*  It is annotated with @HiltAndroidApp to let Hilt know that it should inject Android classes like
* Application, Activity, Fragment, Service, BroadcastReceiver, and ContentProvider.
*
 */
@HiltAndroidApp
class NewsApplication : Application(), Configuration.Provider {
    // The onCreate() method is where you should initialize your application.

    @Inject
    lateinit var workerFactory: HiltWorkerFactory
    // Hilt provides an instance of HiltWorkerFactory that knows how to provide dependencies to workers.
    @Inject
    lateinit var workManager: WorkManager
    // Hilt provides an instance of WorkManager that knows how to provide dependencies to workers.
    override fun onCreate() {
        super.onCreate()
        // The onCreate() method is where you should initialize your application.

     //   initWorkManager()
    }
    //

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
/*
    private fun initWorkManager() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequest.Builder(
            NewsWorker::class.java,
            24,
            TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .setInitialDelay(TimeUtil.getInitialDelay(), TimeUnit.MILLISECONDS)
            .addTag(Const.UNIQUE_WORK_NAME)
            .build()

        workManager.enqueueUniquePeriodicWork(
            Const.UNIQUE_WORK_NAME,
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE, workRequest
        )
    }
*/
}