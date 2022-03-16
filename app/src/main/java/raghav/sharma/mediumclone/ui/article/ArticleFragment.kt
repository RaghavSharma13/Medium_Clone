package raghav.sharma.mediumclone.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import raghav.sharma.mediumclone.R
import raghav.sharma.mediumclone.databinding.FragmentArticleBinding
import raghav.sharma.mediumclone.extensions.formatDate
import raghav.sharma.mediumclone.extensions.loadImage

class ArticleFragment: Fragment() {
    private lateinit var _binding: FragmentArticleBinding
    private lateinit var articleViewModel: ArticleViewModel
    private var slug: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // hiding fab
        requireActivity().findViewById<FloatingActionButton>(R.id.floatingActionButton).hide()
        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        slug = arguments?.getString(resources.getString(R.string.arg_article_slug),"")
        slug?.let {
            articleViewModel.getArticle(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleViewModel.article.observe({lifecycle}){
            _binding.apply {
                articleTitleView.text = it.title
                articleDateView.formatDate(it.createdAt)
                articleContentView.text = it.body
                articleAuthorView.text = it.author.username
                articleDisplayImageView.loadImage(it.author.image)
            }
        }
    }
}