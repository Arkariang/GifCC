package com.isabelmartin.kickstartercc.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.isabelmartin.kickstartercc.R
import com.isabelmartin.kickstartercc.models.GifDetailModel
import kotlinx.android.synthetic.main.list_item.view.*


class RecyclerAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var _data: MutableList<GifDetailModel> = mutableListOf()
    var data: List<GifDetailModel>
        get() = _data.toList()
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }
    var viewActionListener: ViewActionListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.list_item), parent.context, viewActionListener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(_data[position])

    override fun getItemCount(): Int = data.size
}

class ViewHolder(
    item: View,
    context: Context,
    viewActionListener: ViewActionListener?
) : RecyclerView.ViewHolder(item) {
    private val context = context
    private val viewActionListener = viewActionListener

    fun bind(gifModel: GifDetailModel) {
        itemView.setOnClickListener {
            viewActionListener?.onAction(
                action = ACTION_CLICK,
                data = gifModel
            )
        }

        Glide
            .with(context)
            .load(gifModel.url)
            .error(R.drawable.ic_error_24px) // show error drawable if the image is not a gif
            .into(itemView.imageView)
    }

    companion object {
        const val ACTION_CLICK = 1
    }
}

abstract class PageingListener(private val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    protected abstract fun loadMoreItems(pageNumber: Int)

    private var pageNumber: Int = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
            && firstVisibleItemPosition >= 0
        ) {
            loadMoreItems(pageNumber++)
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }
}

/**
 * A common interface for view actions
 */
interface ViewActionListener {

    /**
     * Handle a view action
     *
     * @param action the action type
     * @param data any associated data
     */
    fun onAction(action: Int, data: GifDetailModel)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
