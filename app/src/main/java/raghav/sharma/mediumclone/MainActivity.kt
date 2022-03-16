package raghav.sharma.mediumclone

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import raghav.sharma.api.models.entities.User
import raghav.sharma.mediumclone.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    companion object {
        const val PREFS_FILE_AUTH = "prefs_auth"
        const val PREFS_KEY_TOKEN = "token"
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val authViewModel: AuthViewModel by viewModels()
    private val feedViewModel: FeedViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(PREFS_FILE_AUTH, MODE_PRIVATE)

        sharedPreferences.getString(PREFS_KEY_TOKEN, null)?.let {
            authViewModel.getCurrentUser(it)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_feed,
                R.id.nav_auth
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.appBarMain.floatingActionButton.hide()
        binding.appBarMain.floatingActionButton.setOnClickListener {
            navController.navigate(R.id.action_nav_feed_to_nav_add_article)
        }

        authViewModel.user.observe(this) {
            navController.navigateUp()
            updateMenu(it)
            it?.token?.let {
                sharedPreferences.edit {
                    putString(PREFS_KEY_TOKEN, it)
                }
            } ?: run {
                sharedPreferences.edit {
                    remove(PREFS_KEY_TOKEN)
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.

        authViewModel.user.observe({ lifecycle }) {
            it?.run {
                menuInflater.inflate(R.menu.main, menu)
            } ?: run {
                menu.clear()
            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                authViewModel.logoutUser()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun updateMenu(user: User?) {
        binding.navView.menu.clear()
        when (user) {
            is User -> {
                binding.navView.inflateMenu(R.menu.activity_main_drawer_logged_in)
            }
            else -> {
                binding.navView.inflateMenu(R.menu.activity_main_drawer)
            }
        }

    }

}