package com.example.marvelproject.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel<VS: BaseViewState>: ViewModel() {


    private var baseState: BaseState<VS>?= null
    private val observableState: MutableLiveData<BaseState<VS>> = MutableLiveData()

    fun getObservablestate(): LiveData<BaseState<VS>> = observableState

    /**
     * On start first time
     */
    abstract val defaulState: VS

    fun onStart(){
        if(baseState==null){
            baseState= BaseState.Normal(defaulState)
            onStartFirstTime()
        }
        onResume()
        observableState.postValue(baseState)
    }




    abstract fun onStartFirstTime()
    open fun onResume() {}

    /**
     * State managment
     */

    fun updatetoNormalState(viewState: VS){
        baseState= BaseState.Normal(viewState)
        observableState.postValue(baseState)
    }


    fun updatetoLoadingState(viewState: VS, loadingData: BaseExtraData?= null){
        baseState= BaseState.Loading(viewState,loadingData)
        observableState.postValue(baseState)
    }


    fun updatetoErrorState(viewState: VS, errorData: Throwable = Throwable()){
        baseState= BaseState.Error(viewState,errorData)
        observableState.postValue(baseState)
    }

    fun <T> checkDataState(checkDataStateFunction: (VS) -> T): T{
        return baseState?.let {
            checkDataStateFunction(it.data)
        }?: checkDataStateFunction(defaulState)
    }


    /**
     * Corroutines
     */

    fun executeCoroutines(
            block: suspend CoroutineScope.() -> Unit,
            excpetionBlock: suspend CoroutineScope.(Throwable) -> Unit){
        viewModelScope.launch{
        try {
            block()
        } catch (e: Exception) {
            excpetionBlock(e)
        }
    }
    }



}