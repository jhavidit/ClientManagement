package com.jhaFinancial.clientManagement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jhaFinancial.clientManagement.R
import com.jhaFinancial.clientManagement.model.ClientDetails
import kotlinx.android.synthetic.main.client_item_list.view.*

class ClientDetailsAdapter(private val context: Context) :
    RecyclerView.Adapter<ClientDetailsAdapter.ViewHolder>() {

    private var list: List<ClientDetails> = ArrayList()

    fun setCountryWiseTracker(list: List<ClientDetails>) {
        this.list = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.client_item_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {

        return list.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var item = list[position]
        holder.clientName.text = item.name
        holder.clientPan.text = item.panNumber
        val bundle = bundleOf(
            "name" to item.name,
            "dob" to item.dateOfBirth,
            "father_spouse" to item.fathersorSpouseName,
            "mother" to item.motherName,
            "marital_status" to item.maritalStatus,
            "phone" to item.mobileNumber,
            "email" to item.email,
            "address" to item.address,
            "pan" to item.panNumber,
            "adhaar" to item.adhaarNumber,
            "occupation" to item.occupation,
            "income" to item.annualIncome,
            "investment" to item.investment

        )
        holder.clientCard.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_clientDetails_to_clientCompleteDetails,bundle)
        }

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val clientCard: CardView = view.client_details_card
        val clientImage: ImageView = view.client_image
        val clientName: TextView = view.client_name
        val clientPan: TextView = view.client_pan

    }
}