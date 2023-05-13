package com.example.postsapplication.core.base.screens

import android.annotation.SuppressLint
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity : AppCompatActivity() {

    /**
     * Inline Function used to define databinding layout and
     * Set the content view to the activty
     * @param resId [Int] layout resource id
     * @return [kotlin.Lazy]<[ViewDataBinding]> View Data Binding used to access view layout and bind data to view
     */
    protected inline fun <reified T : ViewDataBinding> binding(
        @LayoutRes resId: Int,
    ): T = DataBindingUtil.setContentView<T>(this, resId)

    /**
     * Inline Function used to define databinding layout and
     * Set the content view to the activty
     * @param resId [Int] layout resource id
     * @return [kotlin.Lazy]<[ViewDataBinding]> View Data Binding used to access view layout and bind data to view
     * this function will not create binding instance until the binding instance is requested.
     */
    protected inline fun <reified T : ViewDataBinding> lazyBinding(
        @LayoutRes resId: Int,
    ): Lazy<T> = lazy { DataBindingUtil.setContentView<T>(this, resId) }


    @SuppressLint("ResourceType")
    fun configureToolbar(@LayoutRes toolbarId: Int) {
        setSupportActionBar(findViewById(toolbarId))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}