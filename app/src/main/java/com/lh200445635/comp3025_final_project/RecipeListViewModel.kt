package com.lh200445635.comp3025_final_project

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class RecipeListViewModel : ViewModel() {
    private val recipes = MutableLiveData<List<Recipe>>()

    init {
        loadRecipes()
    }

    fun getRecipes() : LiveData<List<Recipe>> {
        return recipes
    }


    private fun loadRecipes() {
        val db = FirebaseFirestore.getInstance().collection("recipes").orderBy("recipeName", Query.Direction.ASCENDING)

        db.addSnapshotListener { documents, exception ->
            Log.i("DB_RESPONSE", "# of elements returned ${documents?.size()}")

            if(exception != null) {
                Log.w("DB_RESPONSE", "Listen failed", exception)
                return@addSnapshotListener
            }

            val recipeList = ArrayList<Recipe>()

            documents?.let {
                for(document in documents) {
                    val recipe = document.toObject(Recipe::class.java)
                    recipeList.add(recipe)
                }
            }
            recipes.value = recipeList
        }
    }
}