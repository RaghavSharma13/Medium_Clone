package raghav.sharma.mediumclone.ui.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import raghav.sharma.mediumclone.FeedViewModel
import raghav.sharma.mediumclone.R
import raghav.sharma.mediumclone.databinding.FragmentFeedBinding


private const val TAG = "GlobalFeed"
class GlobalFeedFragment: Fragment() {

    private lateinit var _binding: FragmentFeedBinding
    private val _feedViewModel: FeedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //setting viewModel and binding
        _binding = FragmentFeedBinding.inflate(inflater, container, false)

        // fetching global feed to viewModel._feed
        _feedViewModel.getGlobalFeed()

        // initializing adapter
        val feedAdapter = ArticleFeedAdapter { openArticle(it) }

        //setting up recycler view
        _binding.feedRecyler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = feedAdapter
        }

        // observing changes in feed
        _feedViewModel.feed.observe({lifecycle}){
            feedAdapter.submitList(it)
        }

        return _binding.root
    }

    private fun openArticle(slug: String){
        val navController = Navigation.findNavController(requireActivity().findViewById(R.id.nav_host_fragment_content_main))

        navController.navigate(R.id.action_nav_feed_to_nav_article, bundleOf(
            resources.getString(R.string.arg_article_slug) to slug
        ))
    }
}