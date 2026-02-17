package com.movies.sys_ltd_movies_android



import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.movies.sys_ltd_movies_android.presentation.MoviesNavHost
import com.movies.sys_ltd_movies_android.presentation.Screen
import com.movies.sys_ltd_movies_android.ui.theme.Sys_ltd_movies_androidTheme
import dagger.hilt.android.AndroidEntryPoint
import io.flutter.embedding.android.FlutterFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var navController: NavHostController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sys_ltd_movies_androidTheme() {
                val nc = rememberNavController()
                navController = nc
                MoviesNavHost(
                    navController = nc,
                    onShowMoviesClick = {
                        launchFlutterModule()
                    }
                )
            }
        }
    }

    private fun launchFlutterModule() {
        val flutterFragment = FlutterBridge.createFlutterFragment()
        supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, flutterFragment, FlutterBridge.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is FlutterFragment) {
            fragment.flutterEngine?.let { engine ->
                FlutterBridge.configureChannel(engine) { movieId ->
                    onMovieSelected(movieId)
                }
            }
        }
    }

    fun onMovieSelected(movieId: Int) {
        supportFragmentManager.popBackStack()
        navController?.navigate(Screen.Trailer.createRoute(movieId))
    }
}