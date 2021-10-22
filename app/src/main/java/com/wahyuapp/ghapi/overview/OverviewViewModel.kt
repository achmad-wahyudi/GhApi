package com.wahyuapp.ghapi.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wahyuapp.ghapi.network.GithubApi
import com.wahyuapp.ghapi.network.GithubData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    private val _items = MutableLiveData<List<GithubData>>()

    val items: LiveData<List<GithubData>>
        get() = _items

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response


    private var vmJob = Job()
    private val crScope = CoroutineScope(vmJob + Dispatchers.Main)

    init {
        initData()
    }

    private fun initData() {
        _response.value = "Loading ....."

        crScope.launch {
            try {
                val result = GithubApi.retrofitService.showList()

                if (result.isNotEmpty()) {
                    _items.value = result
                    _response.value = "OK"
                }
            } catch (t: Throwable) {
                _response.value = "Failed, Internet OFF"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }

}