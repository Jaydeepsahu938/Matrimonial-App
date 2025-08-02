package com.example.matrimonialapp

import Model.ProfileMatchesViewModel
import Model.ProfileMatchesViewModelFactory
import Model.ProfilesMatchesRepository
import Network.RetrofitClient
import RoomDB.AppDatabase
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matrimonialapp.databinding.ActivityMainBinding

class ProfileMatchesActivity : AppCompatActivity() {
    private lateinit var viewModel: ProfileMatchesViewModel
    private lateinit var adapter: ProfileMatchesAdapter
    private lateinit var binding: ActivityMainBinding
    private var isProfileLoaded = false
    private val viewModelFactory by lazy {
        ProfileMatchesViewModelFactory(
            ProfilesMatchesRepository(
                RetrofitClient.apiService,
                AppDatabase.getInstance(this).profilesDao()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory)[ProfileMatchesViewModel::class.java]
        setAdapters()
        setPagination()
        setObserver()
    }

    private fun setObserver() {
        viewModel.profiles.observe(this) {
            if(it.isEmpty()){
             binding.connectingText.visibility = View.VISIBLE
            }
            else{
                isProfileLoaded = true
                binding.connectingText.visibility = View.GONE
                adapter.submitList(it)
            }
        }
    }


    private fun setAdapters() {
        adapter = ProfileMatchesAdapter { profile, decision ->
            viewModel.updateDecision(profile, decision)
        }
        binding.matchProfilesRecyclerView.apply {
            adapter = this@ProfileMatchesActivity.adapter
        }
    }

    private fun setPagination() {
        binding.matchProfilesRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItem >= totalItemCount - 5) {
                    // Trigger next page load from ViewModel
                    viewModel.fetchUsers()
                }
            }
        })
    }

}