package com.example.navigasiapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_product.view.*

class ProductAdapter (val context: Context) : RecyclerView.Adapter<ProductAdapter.ViewHolder>(), Filterable {
    var arrayList = ArrayList<ProductModel>()
    var ProductListFilter = ArrayList<ProductModel>()

    fun setData(arrayList: ArrayList<ProductModel>) {
        this.arrayList = arrayList
        this.ProductListFilter = arrayList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(model: ProductModel) {
            itemView.productName.text   = "${model.idProduct}. ${model.nmProduct}"
            itemView.productDesc.text   = model.dsProduct
            itemView.priceProduct.text  = model.priceofProduct.toString()
            itemView.imgProduct.setImageResource(model.picProduct)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_product, parent, false)
        return ProductAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(arrayList[position])
        holder.itemView.setOnClickListener() {
            val model = arrayList.get(position)

            var gId: Int = model.idProduct
            var gProduct: String = model.nmProduct
            var gDesc: String    = model.dsProduct
            var gHarga: Int      = model.priceofProduct.toString().toInt()
            var gImg: Int        = model.picProduct

            val intent = Intent(context, Order::class.java)
            intent.putExtra("pId", gId)
            intent.putExtra("pProduct", gProduct)
            intent.putExtra("pDesc", gDesc)
            intent.putExtra("pHarga", gHarga)
            intent.putExtra("pImg", gImg)
            context.startActivity(intent)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint == null || constraint.length < 0) {
                    filterResults.count = ProductListFilter.size
                    filterResults.values = ProductListFilter
                } else {
                var searchChr = constraint.toString()
                val productSearch = ArrayList<ProductModel>()
                for (item in ProductListFilter) {
                    if (item.nmProduct.toLowerCase()
                            .contains(searchChr) || item.dsProduct.toLowerCase().contains(searchChr)) {
                        productSearch.add(item)
                    }
                }
                filterResults.count = productSearch.size
                filterResults.values = productSearch
            }
            return filterResults
            }

            override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
                arrayList = filterResults!!.values as ArrayList<ProductModel>
                notifyDataSetChanged()
            }
        }
    }
}