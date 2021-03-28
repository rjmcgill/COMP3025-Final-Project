package com.example.comp3025_final_project

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.comp3025_final_project.databinding.ActivityRecipeListBinding
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var binding: ActivityRecipeListBinding

class RecipeListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_recipe_list)

        val db = FirebaseFirestore.getInstance().collection("recipes")
        val id = db.document().id
        val query = db.document(id)

        query.get().addOnSuccessListener { document ->
            if(document != null) {
                Log.i("DB_RESPONSE", "DocumentSnapshot data: ${document.data}")
            } else {
                Log.i("DB_RESPONCE", "No such document");
            }
        }.addOnFailureListener { exception ->
            Log.i("DB_RESPONCE", "get failed with ", exception)
        }
    }
}