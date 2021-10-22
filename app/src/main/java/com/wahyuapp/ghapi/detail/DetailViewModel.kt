package com.wahyuapp.ghapi.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wahyuapp.ghapi.network.GithubApi
import com.wahyuapp.ghapi.network.GithubData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailViewModel(username: String) : ViewModel() {

    private val _item = MutableLiveData<GithubData>()

    val item: LiveData<GithubData>
        get() = _item

    private var vmJob = Job()
    private val crScope = CoroutineScope(vmJob + Dispatchers.Main)

    init {
        crScope.launch {
            try {
                val result = GithubApi.retrofitService.showUser(username)
                _item.value = result
            } catch (t: Throwable) {
                //...show error
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }

}