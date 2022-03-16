package raghav.sharma.mediumclone.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import raghav.sharma.mediumclone.R
import raghav.sharma.mediumclone.databinding.FragmentAddArticleBinding

class AddArticleFragment : Fragment() {
    private lateinit var _binding: FragmentAddArticleBinding
    private lateinit var articleViewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)

        // hiding fab
        requireActivity().findViewById<FloatingActionButton>(R.id.floatingActionButton).hide()

        val navController =
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_host_fragment_content_main))
        articleViewModel.created_new_article.observe({ lifecycle }) {
            if (it) {
                navController.navigateUp()
            }else{
                Toast.makeText(context, "Article Creation Failed. Check the fields or try again later.", Toast.LENGTH_LONG).show()
            }
        }

        _binding = FragmentAddArticleBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.articlePublish.setOnClickListener {
            articleViewModel.publishArticle(
                title = _binding.articleTitle.text.toString(),
                description = _binding.articleDescription.text.toString(),
                content = _binding.articleBody.text.toString(),
                tags = _binding.articleTags.text.toString().takeIf { it.isNotBlank() },
            )
        }


    }

}