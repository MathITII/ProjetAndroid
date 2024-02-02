// Importation des packages nécessaires pour l'application Android
package fr.isen.bagnis.androiderestaurant
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import fr.isen.bagnis.androiderestaurant.ui.theme.AndroidERestaurantTheme

// Définition de la classe principale de l'activité Android
class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configuration du contenu de l'activité avec Jetpack Compose
        setContent {
            AndroidERestaurantTheme {
                // Création d'une surface (conteneur graphique) occupant toute la taille de l'écran
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Appel à la fonction Greeting avec le texte "Android" en paramètre
                    Greeting("Android", ::onMenuClick)
                }
            }
       }
    }

    private fun onMenuClick(menu: String)
    {
        Toast.makeText(this,menu,Toast.LENGTH_LONG).show()
        val intent = Intent(this, MenuActivity::class.java).apply {
            putExtra(MenuActivity.CATEGORY_KEY, menu)
        }
        startActivity(intent)
    }
}


// Définition d'une fonction composable
@Composable
fun Greeting(name: String, onMenuClick: (String) -> Unit) {
    // Utilisation du composant Column pour aligner les boutons verticalement
    //val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(android.graphics.Color.parseColor("#34495E")))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Appel à la fonction Greeting avec le texte "Android" en paramètre
        Text(
            text = "Bienvenue chez ta mère",  // Le texte affiché inclut le paramètre 'name'
            fontSize = 32.sp,
            color = Color.White,  // Couleur du texte
            fontWeight = FontWeight.Bold,  // Gras
            fontFamily = FontFamily.Cursive,
            modifier = Modifier
        )

        // Image centrée tout en haut
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(140.dp)
                .wrapContentSize(align = Alignment.Center)
        )

        // Ajout de trois boutons dans la Column
        //StyledButton(onClick = { showToast("Entrées", context) }, buttonText = "Entrées")
        StyledButton(onClick = { onMenuClick("Entrées") }, buttonText = "Entrées")
        StyledButton(onClick = { onMenuClick("Plats") }, buttonText = "Plats")
        StyledButton(onClick = { onMenuClick("Desserts") }, buttonText = "Desserts")

    }
}

//public fun showToast(category: String, context: Context)
//{
//   Toast.makeText(context, "Vous avez cliqué sur la catégorie $category", Toast.LENGTH_SHORT).show()
//}
@Composable
fun StyledButton(onClick: () -> Unit, buttonText: String) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#595E34"))),
        shape = MaterialTheme.shapes.medium,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 25.dp,
            pressedElevation = 30.dp
        )
    ) {
        Text(
            text = buttonText,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
    }
}

// Fonction composable de prévisualisation pour afficher l'apparence du composant Greeting dans l'éditeur de conception
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    // Utilisation du thème de l'application pour la prévisualisation
    AndroidERestaurantTheme {
        // Appel à la fonction Greeting avec le texte "Android" en paramètre pour la prévisualisation
        Greeting("Android", {})
    }
}

