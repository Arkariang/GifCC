package com.isabelmartin.kickstartercc

import com.isabelmartin.kickstartercc.models.*
import org.junit.Test



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SearchDataModelTest {

    @Test
    fun `Test empty list from request`() {
        var searchModel = SearchDataModel()
        val observerTest = searchModel.gifList.test()
        searchModel.populateFromRequest(emptyList())
        observerTest.assertValue(emptyList())
        observerTest.assertNotComplete()
    }

    @Test
    fun `Test some result from request but wrong data`() {

        val listInput = listOf(
            GiphyModel("","","","","","", Images(GifPojo(null, null, null)),"", "")
            )

        val listOutput = listOf(
            GifDetailModel("",null,null, null)
        )

        var searchDataModel= SearchDataModel()
        val observerTest = searchDataModel.gifList.test()
        searchDataModel.populateFromRequest(listInput)
        observerTest.assertValue(listOutput)
        observerTest.assertNotComplete()
    }
}
