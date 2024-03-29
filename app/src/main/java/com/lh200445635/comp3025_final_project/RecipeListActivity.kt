package com.lh200445635.comp3025_final_project

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lh200445635.comp3025_final_project.databinding.ActivityRecipeListBinding
import androidx.lifecycle.Observer

class RecipeListActivity : AppCompatActivity(), ListViewAdapter.RecipeItemListener {
    private lateinit var binding: ActivityRecipeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val model : RecipeListViewModel by viewModels()
        model.getRecipes().observe(this, Observer<List<Recipe>> { recipeList ->
            var listAdapter = ListViewAdapter(this, recipeList, this)
            binding.verticalRecyclerView.adapter = listAdapter
        })

        binding.addRecipeFAB.setOnClickListener {
            val intent = Intent(this, AddRecipeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun recipeSelected(recipe: Recipe) {
        val intent = Intent(this, RecipeActivity::class.java)
        intent.putExtra("recipeName", recipe.recipeName)
        intent.putExtra("description", recipe.description)
        startActivity(intent)
    }
}