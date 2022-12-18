package com.example.mvvm.viewmodel

import android.os.Looper
import androidx.lifecycle.*
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mvvm.api.FakeApiService
import com.example.mvvm.data.file.ReadFile
import com.example.mvvm.data.model.inboxSorting
import com.example.mvvm.data.model.messages
import com.example.mvvm.data.repository.MainRepository
import com.example.mvvm.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject


@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [26], application = HiltTestApplication::class)
@ExperimentalCoroutinesApi
@LooperMode(LooperMode.Mode.PAUSED)
class MainViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var mainRepository: MainRepository

    @Inject
    lateinit var readFile: ReadFile

    @BindValue
    @JvmField
    val fakeApiService: FakeApiService = FakeApiService()

    @Mock
    private lateinit var mainViewModel: MainViewModel


    private val EXPECTEDINBOX = listOf(inboxSorting(1005, "Hamza Tate", "2022-11-08T10:45:00.000Z"))

    private val EXPECTEDMESSAGE = listOf(messages(1005,"Good night! Sweet Dreams","2022-11-08T10:45:00.000Z"))

    private val EXPECTEDUSERID = 1000

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    @Before
    fun setup() {
        hiltRule.inject()
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun test_inbox_data_success() = runBlockingTest {
        val value = mainRepository.getChat(appContext)
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        assertEquals(value, EXPECTEDINBOX)

        value.forEach{
            assertEquals("Hamza Tate",  it.senderName )
        }
    }

    @Test
    fun test_message_data_success() = runBlockingTest {
        val value = mainRepository.getMessage(appContext,1005)
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        assertNotNull(value)
        assertEquals(value, EXPECTEDMESSAGE)
        if (value != null) {
            value.forEach {
                assertEquals("Good night! Sweet Dreams",  it.message )
            }
        }
    }

    @Test
    fun test_inbox_data_wrong() = runBlockingTest {
        val value = mainRepository.getChat(appContext)
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        assertEquals(value, EXPECTEDINBOX)

        value.forEach{
            assertEquals("Hamza",  it.senderName )
        }
    }

    @Test
    fun test_message_data_wrong() = runBlockingTest {
        val value = mainRepository.getMessage(appContext,1005)
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        assertNotNull(value)
        assertEquals(value, EXPECTEDMESSAGE)
        if (value != null) {
            value.forEach {
                assertEquals("Good night!",  it.message )
            }
        }
    }

    @Test
    fun test_message_data_null() = runBlockingTest {
        val value = mainRepository.getMessage(appContext,1088)
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        assertNull(value)
    }
}