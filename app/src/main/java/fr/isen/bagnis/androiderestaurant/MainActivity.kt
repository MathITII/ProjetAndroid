// Importation des packages nécessaires pour l'application Android
package fr.isen.bagnis.androiderestaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    // Appel à la fonction Greeting avec le texte "Android" en paramètre
                    Greeting("Android")
                }
            }
        }
    }
}
// Définition d'une fonction composable qui affiche un texte de salutation
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier)
{
    // Utilisation du composant Text de Jetpack Compose pour afficher un texte
    Text(
        text = "ta grand mere l'$name!",  // Le texte affiché inclut le paramètre 'name'
        modifier = modifier
    )
}

// Fonction composable de prévisualisation pour afficher l'apparence du composant Greeting dans l'éditeur de conception
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    // Utilisation du thème de l'application pour la prévisualisation
    AndroidERestaurantTheme {
        // Appel à la fonction Greeting avec le texte "Android" en paramètre pour la prévisualisation
        Greeting("Android")
    }
}
