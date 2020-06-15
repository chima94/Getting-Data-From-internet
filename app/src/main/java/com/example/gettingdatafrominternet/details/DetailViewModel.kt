package com.example.gettingdatafrominternet.details

import android.app.Application
import androidx.lifecycle.*
import com.example.gettingdatafrominternet.R
import com.example.gettingdatafrominternet.network.MarsProperty

class DetailViewModel(marsProperty: MarsProperty, application: Application) : AndroidViewModel(application) {

    private val _selectedProperty = MutableLiveData<MarsProperty>()
    val selectedProperty : LiveData<MarsProperty>
        get() = _selectedProperty

    val displayPropertyPrice = Transformations.map(selectedProperty){
        application.applicationContext.getString(
            when(it.isRental){
                true -> R.string.display_price_monthly_rental
                false -> R.string.display_price
            }, it.price)
    }

    val displayProperType = Transformations.map(selectedProperty){
        application.applicationContext.getString(
            when(it.isRental){
                true -> R.string.rent
                false -> R.string.buy
            }, it.price
        )
    }

    init {
        _selectedProperty.value = marsProperty
    }
}