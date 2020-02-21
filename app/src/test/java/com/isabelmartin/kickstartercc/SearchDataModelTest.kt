package com.isabelmartin.kickstartercc

import com.isabelmartin.kickstartercc.models.*
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SearchDataModelTest {

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun getEmptyRequest() {
        var searchModel = SearchDataModel()
        val observerTest = searchModel.gifList.test()
        searchModel.populateFromRequest(emptyList())
        observerTest.assertResult(emptyList())
        observerTest.assertComplete()
    }

    @Test
    fun getDataRequest() {

        val listInput = listOf(
            GiphyModel("","","","","","", Images(GifPojo(null, null, null)),"", ""),
            GiphyModel(null, null, null, null, null, null, Images(GifPojo("","320","320")), "", ""))

        val listOutput = listOf(
            GifDetailModel(),GifDetailModel()
        )

        var searchDataModel= SearchDataModel()
        val observerTest = searchDataModel.gifList.test()
        searchDataModel.populateFromRequest(listInput)
        observerTest.assertResult(listOutput)
        observerTest.assertComplete()
    }
}
