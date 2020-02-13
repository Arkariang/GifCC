package com.isabelmartin.kickstartercc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.isabelmartin.kickstartercc.viewmodel.SearchGifViewModel

class MainActivity : AppCompatActivity() {

    lateinit var searchViewModel: SearchGifViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchViewModel = SearchGifViewModel
    }

    override fun onDestroy() {
        // TODO call oncleared on viewModels for clean subscriptions
        super.onDestroy()
    }
}
