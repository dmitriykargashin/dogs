package com.cardamon.dogs.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.cardamon.dogs.R
import com.cardamon.dogs.databinding.FragmentDetailBinding
import com.cardamon.dogs.databinding.ItemDogBinding
import com.cardamon.dogs.util.getProgressDrawable
import com.cardamon.dogs.util.loadImage
import com.cardamon.dogs.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_dog.view.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    private var dogUuid = 0
    private lateinit var viewModel: DetailViewModel

    private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate<FragmentDetailBinding>(
                inflater,
                R.layout.fragment_detail,
                container,
                false
            )

        return dataBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid

        }
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        viewModel.fetchByDogUuid(dogUuid)
        observeViewModel()
    }


    private fun observeViewModel() {
        viewModel.dogLiveData.observe(this, Observer { dogFetched ->
            dogFetched?.let {
                dataBinding.dogInfo = dogFetched




            }
        })

    }
}
