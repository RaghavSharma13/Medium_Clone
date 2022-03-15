package raghav.sharma.mediumclone.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayout
import raghav.sharma.mediumclone.R
import raghav.sharma.mediumclone.databinding.FragmentAuthBinding

private const val TAG = "AuthFragment"

class AuthFragment : Fragment() {
    private lateinit var _binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(_binding.root.findViewById(R.id.authFragmentNavHost))

        _binding.authTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        navController.navigate(R.id.gotologinFragment)
                    }
                    1 -> {
                        navController.navigate(R.id.gotosignupFragment)
                    }
                    else -> {
                        throw IllegalStateException("Unknown tab item clicked")
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