package com.isabelmartin.kickstartercc.models

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay

class SearchDataModel {

    var gifList: PublishRelay<List<GifDetailModel>> = PublishRelay.create()
    var textToSearch: BehaviorRelay<String> = BehaviorRelay.create()

    fun searchText(query: String) = textToSearch.accept(query)

    fun populateFromRequest(dtoList: List<GiphyModel>) {
        var tempList: MutableList<GifDetailModel> = arrayListOf()
        arrayListOf(dtoList.forEach {
            tempList.add(
                GifDetailModel(
                    actionTitle = it.title,
                    url = it.images.smallSize.gif_url,
                    width = it.images.smallSize.width?.toInt(),
                    height = it.images.smallSize.height?.toInt()
                )
            )
        })

        gifList.accept(tempList.toList())
    }
}