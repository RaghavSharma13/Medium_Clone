package raghav.sharma.mediumclone.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayout
import raghav.sharma.api.models.entities.User
import raghav.sharma.mediumclone.AuthViewModel
import raghav.sharma.mediumclone.FeedViewModel
import raghav.sharma.mediumclone.R
import raghav.sharma.mediumclone.databinding.FragmentFeedControllerBinding

class FeedControllerFragment: Fragment() {
    private lateinit var  _binding: FragmentFeedControllerBinding
    private val feedViewModel: FeedViewModel by activityViewModels()
    private val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedControllerBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(_binding.root.findViewById(R.id.feedNavHostFragment))
        _binding.feedTabLayout.getTabAt(1)?.view?.isVisible = false

        authViewModel.user.observe({lifecycle}){
            when(it){
                is User-> _binding.feedTabLayout.getTabAt(1)?.view?.isVisible = true
                else-> _binding.feedTabLayout.getTabAt(1)?.view?.isVisible = false
            }
        }

        _binding.feedTabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{
//                        feedViewModel.getGlobalFeed()
                        navController.navigate(R.id.gotoGlobalFeed)
                    }
                    1->{
//                        feedViewModel.getMyFeed()
                        navController.navigate(R.id.gotoMyFeed)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}