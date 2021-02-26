package com.example.marvelproject.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvelproject.databinding.FragmentCharacterListBinding
import com.example.marvelproject.presentation.fragments.characterdetail.CharacterDetailState


abstract class BaseFragment<VS: BaseViewState,VM: BaseViewModel<VS>,DB: ViewDataBinding> : Fragment() {

    /**
     *  Inicializamos el viewModel de la manera antigua porque no podemos
     *  usar byViewModels() en clases abstractas
     */

    private lateinit var vm: VM

    abstract val viewModelClass: Class<VM>

    /**
     * Data Binding
     */
    protected lateinit var binding: DB

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> DB

    /**
     * Llamamos a onResume() porque hay que inicializar el viewModel
     */

    override fun onResume() {
        super.onResume()

        //Get or create the viewModel
        vm = ViewModelProvider(this).get(viewModelClass)

        //Setup observers
        vm.getObservablestate().observe(viewLifecycleOwner,{ state ->
            onNormal(state.data)
            when(state) {
                is BaseState.Error -> {
                    onError(state.error)
                }
                is BaseState.Loading -> {
                    onLoading(state.dataLoading)
                }
                else -> {}
            }
        })
        //Configurar vista con el viewModel Creado
        setupView(vm)

        // Fragment Start
        vm.onStart()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding= bindingInflater.invoke(inflater,container,false)

        return binding.root
    }

    /**
     * A la vista le obligo a implementar este metodo
     */
    abstract fun setupView(viewModel: VM)

    /**
     * Manage state function
     */
    abstract fun onLoading(dataLoading: BaseExtraData?)

    abstract fun onError(error: Throwable)

    abstract fun onNormal(baseViewState: VS): Any
}