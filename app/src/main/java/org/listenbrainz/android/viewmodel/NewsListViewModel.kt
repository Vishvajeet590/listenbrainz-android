package org.listenbrainz.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import org.listenbrainz.android.repository.BlogRepository
import org.listenbrainz.android.model.Blog
import org.listenbrainz.android.util.Resource.Status.SUCCESS
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(val repository: BlogRepository) : ViewModel() {
    fun fetchBlogs(): LiveData<Blog> {
        return liveData(viewModelScope.coroutineContext + Dispatchers.Default) {
            val result = repository.fetchBlogs()
            if (result.status == SUCCESS) {
                result.data?.let { emit(it) }
            }
        }
    }
}