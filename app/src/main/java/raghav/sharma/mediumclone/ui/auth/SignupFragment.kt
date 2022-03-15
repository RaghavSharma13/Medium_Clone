package raghav.sharma.mediumclone.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import raghav.sharma.mediumclone.AuthViewModel
import raghav.sharma.mediumclone.R
import raghav.sharma.mediumclone.databinding.FragmentLoginSignupBinding

class SignupFragment : Fragment() {
    private lateinit var _binding: FragmentLoginSignupBinding
    private val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginSignupBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.authInputUsernameView.isVisible = true
        _binding.authInputButton.text = resources.getString(R.string.sign_up)

        _binding.authInputButton.setOnClickListener {
            authViewModel.signUpUser(
                email = _binding.authInputEmailView.text.toString(),
                password = _binding.authInputPasswordView.text.toString(),
                username = _binding.authInputUsernameView.text.toString()
            )
        }
    }
}