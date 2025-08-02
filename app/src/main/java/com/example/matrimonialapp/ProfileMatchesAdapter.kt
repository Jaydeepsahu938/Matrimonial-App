package com.example.matrimonialapp

import RoomDB.ProfileEntity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matrimonialapp.databinding.MatchProfileItemViewBinding

class ProfileMatchesAdapter(
    private val onDecision: (ProfileEntity, Int) -> Unit
) : ListAdapter<ProfileEntity, ProfileMatchesAdapter.ProfileViewHolder>(DIFF) {

    inner class ProfileViewHolder(private val binding: MatchProfileItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: ProfileEntity) {
            binding.profileNameText.text = profile.name
            if (profile.status == 0) {
                binding.actionIconsConstraintLayout.visibility = View.VISIBLE
                binding.profileStatusConstraintLayout.visibility = View.GONE

            } else {
                binding.actionIconsConstraintLayout.visibility = View.GONE
                binding.profileStatusConstraintLayout.visibility = View.VISIBLE
                if (profile.status == 1) {
                    binding.profileStatusText.text = "Accepted"
                } else {
                    binding.profileStatusText.text = "Declined"
                }
            }
            var profileDetails: String = profile.age.toString()
            if (profileDetails.isNotEmpty()) {
                profileDetails += ", " + profile.location
            }
            binding.profileDetailsText.text = profileDetails
            Glide.with(binding.root).load(profile.imageUrl).into(binding.profileImage)

            binding.checkIcon.setOnClickListener {
                onDecision(profile, 1)
            }
            binding.crossIcon.setOnClickListener {
                onDecision(profile, 2)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileViewHolder {
        val binding =
            MatchProfileItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<ProfileEntity>() {
            override fun areItemsTheSame(old: ProfileEntity, new: ProfileEntity) = old.id == new.id
            override fun areContentsTheSame(old: ProfileEntity, new: ProfileEntity) = old == new
        }
    }
}