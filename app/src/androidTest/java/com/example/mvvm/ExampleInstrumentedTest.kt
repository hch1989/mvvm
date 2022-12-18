package com.example.mvvm

import android.os.Looper
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mvvm.data.file.ReadFile
import com.example.mvvm.data.model.inboxSorting
import com.example.mvvm.data.model.messages
import com.example.mvvm.data.repository.MainRepository
import com.example.mvvm.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import junit.framework.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var mainRepository: MainRepository

    @Inject
    lateinit var readFile: ReadFile

    //@BindValue
    //@JvmField
    //val fakeApiService: FakeApiService = FakeApiService()

    //@Mock
    //private lateinit var mainViewModel: MainViewModel


    private val EXPECTEDINBOX = inboxSorting(1005, "Hamza Tate", "2022-11-08T10:45:00.000Z")

    private val EXPECTEDMESSAGE = listOf(messages(1005,"Good night! Sweet Dreams","2022-11-08T10:45:00.000Z"))

    private val EXPECTEDUSERID = 1000

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    @Before
    fun setup() {
        hiltRule.inject()
        mainViewModel = MainViewModel(mainRepository)
    }


    @Test
    fun useAppContext() {
        // Context of the app under test.

        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.mvvm", appContext.packageName)

        mainViewModel.getChat(appContext).observe(viewLifecycleOwner, Observer {
            Shadows.shadowOf(Looper.getMainLooper()).idle()
            Assert.assertNotNull(it)
            Assert.assertEquals(it, EXPECTEDINBOX)
        })
    }
}