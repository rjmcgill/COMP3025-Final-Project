package com.example.comp3025_final_project

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.comp3025_final_project.databinding.ActivityRecipeBinding


class RecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.descriptionTextView.movementMethod = ScrollingMovementMethod()
        binding.recipe.text = intent.getStringExtra("recipeName")
        binding.descriptionTextView.text = intent.getStringExtra("description")

        binding.backFAB.setOnClickListener {
            val intent = Intent(this, RecipeListActivity::class.java)
            startActivity(intent)
        }
    }
}