package raghav.sharma.mediumclone.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import raghav.sharma.api.models.entities.Article
import raghav.sharma.mediumclone.data.ArticlesRepo

class ArticleViewModel: ViewModel() {

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> = _article

    fun getArticle(slug: String) = viewModelScope.launch {
        ArticlesRepo.getArticle(slug).body()?.let {
            _article.postValue(it.article)
        }
    }

}