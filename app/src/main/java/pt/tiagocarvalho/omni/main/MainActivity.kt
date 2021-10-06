package pt.tiagocarvalho.omni.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import pt.tiagocarvalho.omni.R
import pt.tiagocarvalho.omni.databinding.ActivityMainBinding
import pt.tiagocarvalho.omni.example.ExampleFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        navigateToExample()
    }

    private fun navigateToExample() {
        val fragment = ExampleFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.container, fragment).commit()
    }
}
