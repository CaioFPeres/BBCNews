import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bbcnews.ui.authentication.BiometricsState
import com.example.bbcnews.ui.authentication.BiometricsViewModel

@Composable
fun BiometricScreen(navController: NavHostController, biometricsViewModel: BiometricsViewModel) {
    val authState by biometricsViewModel.authState.collectAsState()
    val context = LocalContext.current
    val authUseCase = AuthenticateUseCase(context)

    if(authState != BiometricsState.Success) {
        authUseCase.showBiometricPrompt(
            onSuccess = { biometricsViewModel.onAuthenticationResult(true) },
            onError = { error -> biometricsViewModel.onAuthenticationResult(false, error) }
        )
    }

    when (authState) {
        is BiometricsState.Success, BiometricsState.NotAvailable -> navController.navigate("MainScreen")

        is BiometricsState.Error -> ErrorCard((authState as BiometricsState.Error).message
                                    + " You need to authenticate to use the application!")
        is BiometricsState.Failed -> ErrorCard("Authentication failed! "
                + "You need to authenticate to use the application!")

        else -> { IdleScreen() }
    }
}

@Composable
fun IdleScreen(){
    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorCard(msg: String){
    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = msg
            )
        }
    }
}