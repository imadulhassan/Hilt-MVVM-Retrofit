package com.hiltMvvmRetrofit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.hiltMvvmRetrofit.BR
import com.hiltMvvmRetrofit.R
import com.hiltMvvmRetrofit.databinding.RvItemgridBinding
import com.hiltMvvmRetrofit.models.ResultsItem
import com.hiltMvvmRetrofit.ui.fragments.home.HomeViewModel
import com.hiltMvvmRetrofit.utils.checkFav


class ChracterAdapter(
    var viewModel: HomeViewModel
) : Adapter<ChracterAdapter.ItemHolder>() {
      private  var itemList= mutableListOf<ResultsItem>()


     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
         val itemView: ViewDataBinding
         itemView =
             DataBindingUtil.inflate(
                 LayoutInflater.from(parent.context), R.layout.rv_itemgrid, parent, false)

         return ItemHolder(itemView)
     }

     override fun onBindViewHolder(holder: ItemHolder, position: Int) {
          (holder as ChracterAdapter.ItemHolder).bindData(itemList.get(position))
         (holder as ChracterAdapter.ItemHolder).checkFav(position)


     }

     override fun getItemCount(): Int {
         return  itemList.size
     }

     fun  updateList(items:ArrayList<ResultsItem>){
         itemList.addAll(items)
         notifyDataSetChanged()
     }


     inner  class ItemHolder(val binding: ViewDataBinding ): RecyclerView.ViewHolder(binding.root) {
          var mbinding:RvItemgridBinding
         init {
             binding.apply {
             }
             mbinding= binding as RvItemgridBinding
         }
         fun bindData(itemdata: ResultsItem){
                binding.setVariable(BR.position,adapterPosition)
                binding.setVariable(BR.viewModel,viewModel)
                binding.setVariable(BR.view,mbinding.ivFav)
                binding.setVariable(BR.itemData,itemdata)

         }

         fun  checkFav(position: Int){
              mbinding.ivFav.checkFav(viewModel.checkIsFav(itemList.get(position)))
         }
     }
 }
