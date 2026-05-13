package com.example.r504tl1973004_jsontypicodeusers

import com.example.r504tl1973004_jsontypicodeusers.domain.JsonTypiCodeAPI
import com.example.r504tl1973004_jsontypicodeusers.presentation.User
import com.example.r504tl1973004_jsontypicodeusers.presentation.UsersScreenViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.Dispatcher
import org.junit.After
import org.junit.Before
import org.junit.Test

class MockAPI(private val result: List<User> = emptyList(), val err: String? = null) : JsonTypiCodeAPI {
    override suspend fun getAllUsers(): List<User> {
        if(err != null) {
            throw Exception(err)
        }

        return result
    }
}

class UsersScreenViewModelTest {
    private lateinit var vm: UsersScreenViewModel
    private lateinit var testDoubleApi : JsonTypiCodeAPI

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGetAllUsersError() {
        // AAA
        testDoubleApi = MockAPI(err = "Joku tahallaan lavastettu virhe")
        vm = UsersScreenViewModel(testDoubleApi)

        // ACT
        vm.getUsers()

        // Assert asdasd
        assertEquals("Joku tahallaan lavastettu virhe", vm.state.value.error)
    }
}