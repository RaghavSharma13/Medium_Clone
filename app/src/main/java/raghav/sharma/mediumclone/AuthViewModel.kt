package raghav.sharma.mediumclone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import raghav.sharma.api.models.entities.User
import raghav.sharma.mediumclone.data.UserRepo

class AuthViewModel : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    fun getCurrentUser(token: String) = viewModelScope.launch {
        UserRepo.getCurrentUser(token)?.let {
            _user.postValue(it.user)
        }

    }

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        val res = UserRepo.loginUser(email, password)
        res?.let {
            _user.postValue(it.user)
        }
    }

    fun logoutUser() = viewModelScope.launch {
        _user.postValue(null)
        UserRepo.logoutUser()
    }

    fun signUpUser(email: String, password: String, username: String) = viewModelScope.launch {
        val res = UserRepo.signUpUser(email, password, username)
        res?.let {
            _user.postValue(it.user)
        }
    }

    fun updateUser(email: String?, password: String?, bio: String, username:String?, imageUrl: String) = viewModelScope.launch {
        val res = UserRepo.updateUser(email, password, username, bio, imageUrl)

        res?.let {
            _user.postValue(it.user)
        }
    }
}