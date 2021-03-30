package com.example.composeprueba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Recipe(
    @DrawableRes val imageResource: Int,
    val title: String,
    val ingredients: List<String>
)

val recipeList = listOf(
    Recipe(R.drawable.ic_launcher_background, "Arroz al carabinero", listOf("Arroz", "Crema")),
    Recipe(R.drawable.ic_launcher_foreground, "Pollo al aceite", listOf("Pollo, aceite"))
)

@Composable
private fun RecipeCard(recipe: Recipe, onRecipeClick:(Recipe) -> Unit) {
    val image = painterResource(id = R.drawable.ic_launcher_foreground)
    Card(shape = RoundedCornerShape(8.dp), elevation = 8.dp, modifier = Modifier.padding(8.dp).clickable { onRecipeClick(recipe) }) {
        Column(modifier = Modifier.padding(16.dp)) {
            val imageModifier = Modifier.requiredHeight(150.dp).fillMaxWidth().clip(shape = RoundedCornerShape(8.dp))
            Image(
                painter = image,
                modifier = imageModifier,
                contentScale = ContentScale.Crop,
                contentDescription = "Foto receta"
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(text = recipe.title, style = MaterialTheme.typography.h6)
            for(ingredient in recipe.ingredients){
                Text(text = "* $ingredient", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Composable
private fun RecipeColumnList(recipeList:List<Recipe>){
    LazyRow {
        items(recipeList){ recipe ->
            RecipeCard(recipe = recipe, onRecipeClick = {
                Log.d("Recipe", "RecipeColumnList: ${it.title}")
            })
        }
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeColumnList(recipeList = recipeList)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewsStoty() {
    RecipeCard(recipeList[0], onRecipeClick = {})
}
