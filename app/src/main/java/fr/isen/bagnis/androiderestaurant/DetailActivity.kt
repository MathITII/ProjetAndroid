package fr.isen.bagnis.androiderestaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.bagnis.androiderestaurant.ui.theme.AndroidERestaurantTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dishName = intent.getStringExtra(DISH_NAME_KEY)

        setContent {
            AndroidERestaurantTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background)
                {
                    DetailContent(dishName.orEmpty())
                }
            }
        }
    }

    companion object {
        const val DISH_NAME_KEY = "dishName"
    }
}

@Composable
fun DetailContent(dishName: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(android.graphics.Color.parseColor("#34495E")))
            .padding(16.dp)
    ) {
        Text(
            text = dishName,
            fontSize = 28.sp,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )

        // ici le contenu détaillé du plat si nécessaire
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    AndroidERestaurantTheme {
        DetailContent(dishName = "Dish1")
    }
}
