package com.isabelmartin.kickstartercc.viewmodel

import com.isabelmartin.kickstartercc.models.SearchDataModel
import com.isabelmartin.kickstartercc.repository.RemoteRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SearchGifViewModel {

    private var searchModel: SearchDataModel = SearchDataModel()
    private val composeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        searchModel.textToSearch
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe ( {
                requestWith(it)
            }, {
                // TODO: send to the view with error code and description for UI error handling
                println("something went wrong")
            }).also {
                composeDisposable.add(it)
            }
    }

    private fun requestWith(query: String) {
        RemoteRepository()
            .provideAPI()
            .searchGifs(q = query)
            .observeOn(Schedulers.io())
            .subscribe({
                searchModel.populateFromRequest(it.data)
            }, {
                // TODO: send to the view with error code and description for UI error handling
                println("something went wrong")
            }).also {
                composeDisposable.add(it)
            }
    }

    fun getGiftsList() = searchModel.gifList

    fun search(query: String) = searchModel.searchText(query)

    fun dispose() = composeDisposable.dispose()
}