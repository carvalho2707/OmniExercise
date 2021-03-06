package pt.tiagocarvalho.omni.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import pt.tiagocarvalho.omni.R
import pt.tiagocarvalho.omni.databinding.ActivityMainBinding
import pt.tiagocarvalho.omni.news.SearchFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        navigateToSearch()
    }

    private fun navigateToSearch() {
        val fragment = SearchFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.container, fragment).commit()
    }
}
