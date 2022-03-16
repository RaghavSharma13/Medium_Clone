package raghav.sharma.mediumclone.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import raghav.sharma.mediumclone.AuthViewModel
import raghav.sharma.mediumclone.R
import raghav.sharma.mediumclone.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment() {

    private lateinit var _binding: FragmentSettingsBinding
    private val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // hiding fab
        requireActivity().findViewById<FloatingActionButton>(R.id.floatingActionButton).hide()
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.user.observe({lifecycle}){
            _binding.apply {
                settingsImageUrl.setText(it?.image?:"")
                settingsUsername.setText(it?.username?:"")
                settingsBio.setText(it?.bio?:"")
                settingsEmail.setText(it?.email?:"")
            }
        }

        _binding.settingsUpdateButton.setOnClickListener {
            authViewModel.updateUser(
                email = _binding.settingsEmail.text.toString().takeIf { it.isNotBlank() },
                password = _binding.settingsPassword.text.toString().takeIf { it.isNotBlank() },
                username = _binding.settingsUsername.text.toString().takeIf { it.isNotBlank() },
                bio = _binding.settingsBio.text.toString(),
                imageUrl = _binding.settingsImageUrl.text.toString()
            )
        }
    }
}