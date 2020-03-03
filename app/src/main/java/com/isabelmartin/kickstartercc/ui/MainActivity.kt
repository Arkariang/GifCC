package com.isabelmartin.kickstartercc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.isabelmartin.kickstartercc.R
import com.isabelmartin.kickstartercc.models.GifDetailModel
import com.isabelmartin.kickstartercc.viewmodel.SearchGifViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var searchViewModel: SearchGifViewModel
    private val composeDisposable: CompositeDisposable = CompositeDisposable()

    private val actionListener = object : ViewActionListener {
        override fun onAction(action: Int, data: GifDetailModel) {
            // TODO set actions for the list Item if any ie, some item selected, open detail activity with that item
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchViewModel = SearchGifViewModel()
        search_input.setup(searchViewModel)

        recycler_view.adapter = RecyclerAdapter().apply {
            viewActionListener = actionListener
            searchViewModel.getGiftsList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    data = it
                }.also {
                    composeDisposable.add(it)
                }
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addOnScrollListener(object: PaginationListener(recycler_view.layoutManager as LinearLayoutManager) {
            override fun nextPage(loadPage: Int) {
                searchViewModel.nextPage(loadPage)
            }
        })
    }

    override fun onDestroy() {
        searchViewModel.dispose()
        super.onDestroy()
    }
}