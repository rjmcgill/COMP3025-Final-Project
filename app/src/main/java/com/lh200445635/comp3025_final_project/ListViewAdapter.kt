package com.lh200445635.comp3025_final_project

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListViewAdapter(  val context: Context,
                        val recipes : List<Recipe>,
                        val itemListener : RecipeItemListener) : RecyclerView.Adapter<ListViewAdapter.RecipeViewHolder>() {
    inner class RecipeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        with(holder) {
            nameTextView.text = recipe.recipeName
            nameTextView.setTextColor(Color.parseColor("#FF000000"))
        }

        holder.itemView.setOnClickListener {
            itemListener.recipeSelected(recipe)
        }
    }

    interface RecipeItemListener {
        fun recipeSelected(recipe: Recipe)
    }
}