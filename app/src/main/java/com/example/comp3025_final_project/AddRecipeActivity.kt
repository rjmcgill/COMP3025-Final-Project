package com.example.comp3025_final_project

import com.firebase.ui.auth.AuthUI
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.comp3025_final_project.databinding.ActivityAddRecipeBinding
import com.google.firebase.firestore.FirebaseFirestore

class AddRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            val intent = Intent(this, RecipeListActivity::class.java)
            startActivity(intent)
        }

        binding.save.setOnClickListener {
            if(binding.editTextTitle.text.toString().isNotEmpty() && binding.editTextMultiLineDescription.text.toString().isNotEmpty()) {
                val recipe = Recipe()
                recipe.description = binding.editTextMultiLineDescription.text.toString()
                recipe.recipeName = binding.editTextTitle.text.toString()

                val db = FirebaseFirestore.getInstance().collection("recipes")
                recipe.id = db.document().id

                db.document(recipe.id!!).set(recipe)

                Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, RecipeListActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Title and description cannot be empty.", Toast.LENGTH_LONG).show()
            }
        }
    }
}