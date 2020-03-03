package com.isabelmartin.kickstartercc.viewmodel

import com.isabelmartin.kickstartercc.models.SearchDataModel
import com.isabelmartin.kickstartercc.repository.RemoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SearchGifViewModel {

    private var searchModel: SearchDataModel = SearchDataModel()
    private val composeDisposable: CompositeDisposable = CompositeDisposable()
    var repository = RemoteRepository().provideAPI()

    init {
        searchModel.textToSearch
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe ( {
                requestWith(it)
            }, {
                // TODO: emmit something to the view with error code and description for UI error handling
                println("something went wrong")
            }).also {
                composeDisposable.add(it)
            }
    }

    private fun requestWith(query: String, page: Int = 0) {
            // todo: set loading mode
            repository
            .searchGifs(q = query, offset = page)
            .subscribeOn(Schedulers.io())
            .subscribe({
                searchModel.populateFromRequest(it.data)
            }, {
                // TODO:  emmit something to the view with error code and description for UI error handling
                println("something went wrong")
            }).also {
                composeDisposable.add(it)
            }
    }

    fun nextPage(loadPage: Int) {
        if (searchModel.textToSearch.hasValue()) {
            requestWith(searchModel.textToSearch?.value.orEmpty(), loadPage)
        }
    }

    fun getGiftsList() = searchModel.gifList

    fun search(query: String) = searchModel.searchText(query)

    fun dispose() = composeDisposable.dispose()
}