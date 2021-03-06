package com.cardamon.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cardamon.dogs.R
import com.cardamon.dogs.databinding.ItemDogBinding
import com.cardamon.dogs.databinding.ItemDogBindingImpl
import com.cardamon.dogs.model.DogBreed
import com.cardamon.dogs.util.getProgressDrawable
import com.cardamon.dogs.util.loadImage
import kotlinx.android.synthetic.main.item_dog.view.*

class DogsListAdapter(val dogList: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogsListAdapter.DogViewHolder>(), DogClickListener {


    fun updateDogsList(newDogsList: List<DogBreed>) {
        dogList.clear()
        dogList.addAll(newDogsList)
        notifyDataSetChanged()
    }

    class DogViewHolder(val view: ItemDogBinding) : RecyclerView.ViewHolder(view.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        /* val view = inflater.inflate(R.layout.item_dog, parent, false)*/
        val view =
            DataBindingUtil.inflate<ItemDogBinding>(inflater, R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount() = dogList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.dog = dogList[position]
        holder.view.listener = this
        /* holder.view.name.text = dogList[position].dogBreed
         holder.view.lifespan.text = dogList[position].lifeSpan
         holder.view.setOnClickListener {
             val action =
                 ListFragmentDirections.actionDetailFragment()
             action.dogUuid = dogList[position].uuid
             Navigation.findNavController(it).navigate(action)
         }
         holder.view.imageView.loadImage(
             dogList[position].imageUrl,
             getProgressDrawable(holder.view.context)
         )*/

    }

    override fun onDogClicked(v: View) {
        val uuid = v.dogId.text.toString().toInt()
        val action =
            ListFragmentDirections.actionDetailFragment()
        action.dogUuid = uuid
        Navigation.findNavController(v).navigate(action)
    }
}