package raghav.sharma.mediumclone

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import raghav.sharma.api.ConduitClient
import raghav.sharma.api.models.entities.Article
import raghav.sharma.mediumclone.data.ArticlesRepo

private const val TAG = "FeedViewModel"
class FeedViewModel: ViewModel() {

    private val _feed = MutableLiveData<List<Article>>()

    val feed :LiveData<List<Article>> = _feed

    fun getGlobalFeed() = viewModelScope.launch {
        ArticlesRepo.getGlobalFeed().body()?.let {
            _feed.postValue(it.articles)
            Log.d(TAG, "Article Count from Global: ${it.articlesCount}")
        }
    }

    fun getMyFeed() = viewModelScope.launch {
        Log.d(TAG, "auth token: ${ConduitClient.authToken}")
        ArticlesRepo.getMyFeed().body()?.let{
            _feed.postValue(it.articles)
            Log.d(TAG, "Article count from my feed: ${it.articlesCount}")
        }
    }
}