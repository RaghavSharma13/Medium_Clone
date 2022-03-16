package raghav.sharma.mediumclone.ui.article

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import raghav.sharma.api.models.entities.Article
import raghav.sharma.mediumclone.data.ArticlesRepo

private const val TAG = "ArticleVM"
class ArticleViewModel: ViewModel() {

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> = _article

    private val _created_new_article = MutableLiveData<Boolean>()
    val created_new_article: LiveData<Boolean> = _created_new_article

    fun getArticle(slug: String) = viewModelScope.launch {
        ArticlesRepo.getArticle(slug).body()?.let {
            _article.postValue(it.article)
        }
    }

    fun publishArticle(title: String, description: String, content: String, tags: String?){
        viewModelScope.launch {
            val tagList = tags?.split(" ")
            ArticlesRepo.publishArticle(title=title, body = content, description = description, tagList = tagList).body()?.let{
                Log.d(TAG, it.article.toString())
                _created_new_article.postValue(true)
            }?: run {
                _created_new_article.postValue(false)
            }
        }
    }

}