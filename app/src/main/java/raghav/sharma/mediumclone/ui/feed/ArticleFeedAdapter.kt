package raghav.sharma.mediumclone.ui.feed

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import raghav.sharma.api.models.entities.Article
import raghav.sharma.mediumclone.R
import raghav.sharma.mediumclone.databinding.ListItemArticleBinding
import raghav.sharma.mediumclone.extensions.formatDate
import raghav.sharma.mediumclone.extensions.loadImage

class ArticleFeedAdapter(val onArticleClicked: (slug: String)->Unit): ListAdapter<Article, ArticleFeedAdapter.ArticleViewHolder>(
    object: DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
){

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_article, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        ListItemArticleBinding.bind(holder.itemView).apply {
            val article = getItem(position)

            articleDisplayImageView.loadImage(article.author.image)
            articleAuthorView.text = article.author.username
            articleDateView.formatDate(article.createdAt)
            articleTitleView.text = article.title
            articleSubHeaderView.text = article.description

            root.setOnClickListener{
                onArticleClicked(article.slug)
            }
        }

    }
}