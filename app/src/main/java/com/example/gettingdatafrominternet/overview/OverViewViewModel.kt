package com.example.gettingdatafrominternet.overview

import android.net.DnsResolver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gettingdatafrominternet.network.MarsApi
import com.example.gettingdatafrominternet.network.MarsApiFilter
import com.example.gettingdatafrominternet.network.MarsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

enum class MarsApiStatus{LOADING, ERROR, DONE}

class OverViewViewModel : ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _property = MutableLiveData<List<MarsProperty>>()
    val property : LiveData<List<MarsProperty>>
            get() = _property

    private val _navigation = MutableLiveData<MarsProperty>()
    val navigation : LiveData<MarsProperty>
        get() = _navigation


    private  var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
    }


    private fun getMarsRealEstateProperties(filter : MarsApiFilter) {
       coroutineScope.launch {
           var getPropertiesDefered = MarsApi.retrofitService.getProperties(filter.value)
           try{
                _status.value = MarsApiStatus.LOADING
                var listResult = getPropertiesDefered.await()
                _status.value = MarsApiStatus.DONE
                if(listResult.isNotEmpty()){
                    _property.value = listResult
                }

           }catch (e : Exception){
                _status.value = MarsApiStatus.ERROR
               _property.value = ArrayList()
           }
       }
    }

    fun updateFilter(filter : MarsApiFilter){
        getMarsRealEstateProperties(filter)

    }

    fun displayPropertyDetails(marsProperty: MarsProperty){
        _navigation.value = marsProperty
    }

    fun displayPropertyDetailCompleted(){
        _navigation.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}