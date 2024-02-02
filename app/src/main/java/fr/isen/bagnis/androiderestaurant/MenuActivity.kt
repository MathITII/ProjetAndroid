// MenuActivity.kt

package fr.isen.bagnis.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.bagnis.androiderestaurant.ui.theme.AndroidERestaurantTheme
import org.json.JSONObject

data class Plat(val name: String, val description: String)

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val category = intent.getStringExtra(CATEGORY_KEY)

        setContent {
            AndroidERestaurantTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    // Utilisation de différentes listes pour les plats, les desserts, etc.
                    val entrees = listOf("Salade César", "Bruschetta", "Salade Niçoise", "foie gras")
                    val plats = listOf("Steak au poivre", "Pâtes Carbonara", "Bouillabaise", "fajitas")
                    val desserts = listOf("Tiramisu", "Fondant au chocolat", "panna cotta", "glace")

                    MenuContent(category = category.orEmpty(), entrees, plats, desserts) { selectedDish ->
                        // Naviguer vers DetailActivity avec le plat sélectionné
                        val intent = Intent(this, DetailActivity::class.java).apply {
                            putExtra(DetailActivity.DISH_NAME_KEY, selectedDish)
                        }
                        startActivity(intent)
                    }
                    fetchData()
                }
            }
        }

    }
    companion object {
        const val CATEGORY_KEY = "category"
    }

    private fun fetchData()
    {
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            {
                Log.d("CategoryActivity", "les donnees en brut : $it")
            },
            {  // TODO: Handle error
                Log.e("CategoryActivity", "Erreur : $it")
            })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(jsonObjectRequest)

    }
}

@Composable
fun DishListComponent(dishes: List<String>, onDishClicked: (String) -> Unit) {
    LazyColumn {
        items(dishes) { dish ->
            DishRow(dish, onDishClicked)
        }
    }
}

@Composable
fun DishRow(dish: String, onDishClicked: (String) -> Unit)
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(android.graphics.Color.parseColor("#595E34")))
            .clickable { onDishClicked(dish) }, // Ajout d'un clic sur la carte
        shape = RoundedCornerShape(16.dp) // Forme arrondie de la carte
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = dish,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun MenuContent(category: String, entrees: List<String>, plats: List<String>, desserts: List<String>, onDishClicked: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(android.graphics.Color.parseColor("#595E34")))
            .padding(16.dp)
    ) {
        Text(
            text = category,
            fontSize = 32.sp, // Police plus grande pour le titre
            color = Color.White,
            fontWeight = FontWeight.Bold, // Texte en gras pour attire l'attention
            modifier = Modifier.padding(8.dp)
        )

        when (category) {
            "Entrées" -> DishListComponent(entrees, onDishClicked)
            "Plats" -> DishListComponent(plats, onDishClicked)
            "Desserts" -> DishListComponent(desserts, onDishClicked)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MenuContentPreview() {
    AndroidERestaurantTheme {
        val entrees = listOf("Salade César", "Bruschetta", "Salade Niçoise", "foie gras")
        val plats = listOf("Steak au poivre", "Pâtes Carbonara", "Bouillabaise", "fajitas")
        val desserts = listOf("Tiramisu", "Fondant au chocolat", "panna cotta", "glace")

        MenuContent(category = "Entrées", entrees, plats, desserts) { }
    }
}
