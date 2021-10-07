package pt.tiagocarvalho.omni.news.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import pt.tiagocarvalho.omni.GlideApp
import pt.tiagocarvalho.omni.databinding.ItemNewsBinding
import pt.tiagocarvalho.omni.news.News
import kotlin.math.abs

internal class SearchAdapter(
    private val onItemClicked: (News) -> Unit
) :
    ListAdapter<News, SearchAdapter.SearchViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClicked)
    }

    class SearchViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News, onItemClicked: (News) -> Unit) {
            val viewType = calculateViewType(news)
            print(viewType)
            when (viewType) {
                VIEW_LARGE -> bindLayoutLarge(binding, news)
                VIEW_MEDIUM -> bindLayoutMedium(binding, news)
                VIEW_SMALL -> bindLayoutSmall(binding, news)
            }

            binding.root.setOnClickListener {
                val nextViewType = calculateNextViewType(binding)
                print("Next ViewType: $nextViewType")
                when (nextViewType) {
                    VIEW_LARGE -> bindLayoutLarge(binding, news)
                    VIEW_MEDIUM -> bindLayoutMedium(binding, news)
                    VIEW_SMALL -> bindLayoutSmall(binding, news)
                }
            }
        }

        private fun bindLayoutSmall(binding: ItemNewsBinding, news: News) {
            with(binding) {
                layoutLarge.root.isVisible = false
                layoutMedium.root.isVisible = false
                layoutSmall.root.isVisible = true

                layoutSmall.titleSmall.text = news.title
            }
        }

        private fun bindLayoutMedium(binding: ItemNewsBinding, news: News) {
            with(binding) {
                layoutLarge.root.isVisible = false
                layoutMedium.root.isVisible = true
                layoutSmall.root.isVisible = false

                layoutMedium.title.text = news.title
                loadImage(this, news.imageUrl, layoutMedium.image)
            }
        }

        private fun bindLayoutLarge(binding: ItemNewsBinding, news: News) {
            with(binding) {
                layoutLarge.root.isVisible = true
                layoutMedium.root.isVisible = false
                layoutSmall.root.isVisible = false

                layoutLarge.title.text = news.title
                loadImage(this, news.imageUrl, layoutLarge.image)
            }
        }

        private fun calculateViewType(news: News): Int {
            return abs(news.id.hashCode() % 3)
        }

        private fun calculateNextViewType(binding: ItemNewsBinding): Int {
            return when {
                binding.layoutLarge.root.isVisible -> VIEW_MEDIUM
                binding.layoutMedium.root.isVisible -> VIEW_SMALL
                else -> VIEW_LARGE
            }
        }

        private fun loadImage(binding: ItemNewsBinding, url: String?, view: ImageView) {
            with(binding) {
                val listener = object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        view.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        view.isVisible = true
                        return false
                    }
                }

                GlideApp.with(this.root.context)
                    .load(url)
                    .centerCrop()
                    .listener(listener)
                    .into(view)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News) =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: News, newItem: News) =
                oldItem == newItem
        }

        private const val VIEW_LARGE = 0
        private const val VIEW_MEDIUM = 1
        private const val VIEW_SMALL = 2
    }
}
