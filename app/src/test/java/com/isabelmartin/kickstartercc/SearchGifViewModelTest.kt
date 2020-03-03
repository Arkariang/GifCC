package com.isabelmartin.kickstartercc

import com.isabelmartin.kickstartercc.viewmodel.SearchGifViewModel
import org.junit.Test

class SearchGifViewModelTest {

    // Bad functional test, is actually performing the request, with dagger you will be able to inject all the mocks
    // for the repository, API and http client
    @Test
    fun `Perform search`() {
        val viewModelTest = SearchGifViewModel()
        viewModelTest.search("pi")
        val list = viewModelTest.getGiftsList().blockingFirst()
        assert(list.filter { it.url.isNullOrBlank() }.isNotEmpty())
        assert(list.isNotEmpty())
        assert(list.size == 10)
    }
}
