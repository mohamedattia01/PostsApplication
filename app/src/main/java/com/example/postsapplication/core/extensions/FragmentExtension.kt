import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.postsapplication.R

/**
 * use default navController method to navigate in the app and and attaching default nav options
 */
fun Fragment.navigateWithId(@IdRes navId: Int, args: Bundle? = null) {
    findNavController().navigate(navId, args, getDefaultNavOptions())
}

/**
 * Method provides default navOptions that will be applied on all of the navigation
 */
private fun getDefaultNavOptions() = navOptions {
    anim {
        enter = R.anim.slide_in_right
        exit = R.anim.slide_out_left
        popEnter = R.anim.slide_in_left
        popExit = R.anim.slide_out_right
    }
}