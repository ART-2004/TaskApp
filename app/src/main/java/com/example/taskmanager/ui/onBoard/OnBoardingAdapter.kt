package com.example.taskmanager.ui.onBoard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.Utils.loadImage
import com.example.taskmanager.databinding.ItemOnBoardingBinding
import kotlin.reflect.KFunction0

class OnBoardingAdapter(private val onClick: KFunction0<Unit>, private val onSkip: () -> Unit, private val onNext: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private val data = arrayListOf(
        OnBoarding("To do list", "Here you can write down .....", "https://i.postimg.cc/rwzVj76h/img1.png"),
        OnBoarding("Share your crazy idea", "You can easyly share woth your ....", "https://i.postimg.cc/mD4Wv5qW/Img2.png"),
        OnBoarding("Flexibility", "Your note with you at home .....", "https://i.postimg.cc/bJr9wz4x/Img3.png")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnBoardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class OnBoardingViewHolder(private val binding: ItemOnBoardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoarding) {
            binding.tvTitle.text = onBoarding.title
            binding.tvDesk.text = onBoarding.desc
            binding.image.loadImage(onBoarding.image.toString())

            binding.btnStart.isVisible = adapterPosition == data.lastIndex

            if (adapterPosition == data.lastIndex){
                binding.btnSkip.isVisible = false
                binding.btnNext.isVisible = false
            }else{
                binding.btnSkip.isVisible = true
                binding.btnNext.isVisible = true
            }

            itemView.setOnClickListener {
                onClick()
                onSkip()
                onNext()
            }


        }

    }
}