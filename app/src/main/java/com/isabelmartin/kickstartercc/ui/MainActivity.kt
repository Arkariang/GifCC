package com.isabelmartin.kickstartercc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.isabelmartin.kickstartercc.R
import com.isabelmartin.kickstartercc.models.GifDetailModel
import com.isabelmartin.kickstartercc.viewmodel.SearchGifViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
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
                }
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    override fun onDestroy() {
        searchViewModel.dispose()
        super.onDestroy()
    }
}