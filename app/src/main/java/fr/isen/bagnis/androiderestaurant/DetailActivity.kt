package fr.isen.bagnis.androiderestaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.gson.Gson
import fr.isen.bagnis.androiderestaurant.model.Items
import fr.isen.bagnis.androiderestaurant.ui.theme.AndroidERestaurantTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectedDishJson = intent.getStringExtra(DISH_KEY)
        val selectedDish = Gson().fromJson(selectedDishJson, Items::class.java)

        setContent {
            AndroidERestaurantTheme {
                DetailContent(selectedDish ?: Items())
            }
        }
    }

    companion object {
        const val DISH_KEY = "dishName"
    }
}

@Composable
fun DetailContent(selectedDish: Items) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(android.graphics.Color.parseColor("#34495E")))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = selectedDish.nameFr ?: "",
            fontSize = 35.sp,
            color = Color.White,
            fontFamily = FontFamily.Cursive,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // Carrousel horizontal pour les images
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            items(selectedDish.images) { imageUrl ->
                ImageItem(url = imageUrl)
            }
        }
        // Afficher les ingrédients du plat
        if (selectedDish.ingredients.isNotEmpty()) {
            Text(
                text = "Ingrédients : ${selectedDish.ingredients.joinToString(", ") { it.nameFr ?: "" }}",
                fontSize = 22.sp,
                color = Color.White,
                fontFamily = FontFamily.Cursive,
            )
        }

        // Afficher les prix du plat pour chaque taille disponible
        if (selectedDish.prices.isNotEmpty()) {
            Text(
                text = "Prix :",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,  // Gras
                modifier = Modifier.padding(top = 18.dp)
            )
            selectedDish.prices.forEach { price ->
                Text(
                    text = "- Taille ${price.size ?: ""} : ${price.price} €",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = FontFamily.Cursive,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
fun ImageItem(url: String) {
    val painter = rememberAsyncImagePainter(url)
    androidx.compose.foundation.Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .size(200.dp)
            .padding(8.dp),
        alignment = Alignment.Center
    )
}
