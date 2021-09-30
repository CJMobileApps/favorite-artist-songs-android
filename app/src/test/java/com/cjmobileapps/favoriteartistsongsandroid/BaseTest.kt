package com.cjmobileapps.favoriteartistsongsandroid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.*
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

@Suppress("UNCHECKED_CAST")
abstract class BaseTest {

    //Use this any() instead of the directly using Mockito.any()
    fun <T> any(): T {
        Mockito.any<T>()
        return null as T
    }

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var closeable: AutoCloseable? = null

    @Before
    open fun openMocks() {
        closeable = MockitoAnnotations.openMocks(this)
    }

    @After
    @Throws(Exception::class)
    open fun releaseMocks() {
        closeable?.close()
    }

    companion object {

        private val immediate = object : Scheduler() {
            override fun scheduleDirect(
                run: Runnable,
                delay: Long, unit: TimeUnit
            ): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(
                    Executor { it.run() },
                )
            }
        }

        @JvmStatic
        @BeforeClass
        fun before() {
            RxJavaPlugins.setInitIoSchedulerHandler { immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
        }

        @JvmStatic
        @AfterClass
        fun after() {
            RxAndroidPlugins.reset()
            RxJavaPlugins.reset()
        }
    }
}
