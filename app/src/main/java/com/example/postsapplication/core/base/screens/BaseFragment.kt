package com.example.postsapplication.core.base.screens

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.postsapplication.core.base.viewmodel.BaseViewModel
import com.example.postsapplication.core.base.viewmodel.ViewState
import com.example.postsapplication.core.custom_views.app_progress_dialog.AppProgressDialog
import kotlinx.coroutines.launch

/**
 * Abstract class used as a main base Fragment
 */

abstract class BaseFragment<DataBinding : ViewDataBinding, STATE : ViewState?>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {
    abstract val viewModel: BaseViewModel<STATE>?
    private lateinit var loadingDialog: AppProgressDialog

    private var onBackPressedCallback: OnBackPressedCallback? = null

    private var internalDataBinding: DataBinding? = null
    protected val dataBinding: DataBinding
        get() {
            return internalDataBinding ?: throw IllegalStateException(
                "data binding should not be requested before onViewCreated is called"
            )
        }

    fun addOnBackPressed(
        isEnabled: Boolean = false, onBackPressed: () -> Unit = { requireActivity().finish() }
    ) {
        onBackPressedCallback?.isEnabled = isEnabled

        if (onBackPressedCallback == null) {
            onBackPressedCallback = object : OnBackPressedCallback(isEnabled) {
                override fun handleOnBackPressed() {
                    onBackPressed.invoke()
                }
            }
            requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback!!)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //ViewModel is set as Binding Variable
        internalDataBinding = DataBindingUtil.bind<DataBinding>(view)?.apply {
            lifecycleOwner = this@BaseFragment
        }

        loadingDialog = AppProgressDialog(requireContext())

        lifecycleScope.launch {
            viewModel!!.loadingFlow.collect { isLoading ->
                if (isLoading) {
                    loadingDialog.showProgress()
                } else {
                    loadingDialog.dismiss()
                }
            }
        }
    }

    override fun onDestroyView() {
        // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
        internalDataBinding = null
        super.onDestroyView()
    }
}