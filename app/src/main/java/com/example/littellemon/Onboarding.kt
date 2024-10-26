package com.example.littellemon
//
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littellemon.ui.theme.HighlightBlack
import com.example.littellemon.ui.theme.PrimaryGreen
import com.example.littellemon.ui.theme.PrimaryYellow

//
//@Composable
//fun Onboarding(context: Context, navController: NavHostController) {
//    val firstName = remember {
//        mutableStateOf("")
//    }
//    val lastName = remember {
//        mutableStateOf("")
//    }
//    val email = remember {
//        mutableStateOf("")
//    }
//    Column(modifier = Modifier. verticalScroll(rememberScrollState()).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
//        Image(painter = painterResource(R.drawable.logo),"logo", modifier = Modifier.size(width = 200.dp, height = 100.dp))
//TextField(
//    value = firstName.value,
//    onValueChange = {
//        firstName.value = it
//    }
//)
//        TextField(
//            value = firstName.value,
//            onValueChange = {
//                firstName.value = it
//            }
//        )
//        TextField(
//            value = firstName.value,
//            onValueChange = {
//                firstName.value = it
//            }
//        )
//        TextField(
//            value = firstName.value,
//            onValueChange = {
//                firstName.value = it
//            }
//        )
//        TextField(
//            value = firstName.value,
//            onValueChange = {
//                firstName.value = it
//            }
//        )
//        TextField(
//            value = firstName.value,
//            onValueChange = {
//                firstName.value = it
//            }
//        )
//        TextField(
//            value = firstName.value,
//            onValueChange = {
//                firstName.value = it
//            }
//        )
//        TextField(
//            value = firstName.value,
//            onValueChange = {
//                firstName.value = it
//            }
//        )
//        TextField(
//            value = firstName.value,
//            onValueChange = {
//                firstName.value = it
//            }
//        )
//    }
//
//
//}
@Composable
fun Onboarding(context: Context, navController: NavHostController) {
    val  sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)

    val keyboardController = LocalSoftwareKeyboardController.current
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    // This modifier handles the keyboard insets
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            // This is crucial for proper keyboard handling
            .imePadding()
            // Optional: if you want the content to be scrollable when keyboard shows
            .windowInsetsPadding(WindowInsets.ime)
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                // Make the content scrollable
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Your content here
           Image(painter = painterResource(R.drawable.logo),"logo", modifier = Modifier.size(width = 200.dp, height = 90.dp))
            Box (modifier = Modifier.fillMaxWidth().height(190.dp).background(color = PrimaryGreen), contentAlignment = Alignment.Center){
                Text("Let's get to know you", style = MaterialTheme.typography.headlineLarge, textAlign = TextAlign.Center, color = Color.White, )

            }
            Text("Personal Information", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.fillMaxWidth().padding(vertical = 25.dp, horizontal = 15.dp))
            Text("First name",modifier = Modifier.fillMaxWidth().padding(start = 25.dp))
            // TextField at the bottom
            TextField(
                shape = RoundedCornerShape(20),
                value = firstName,
                onValueChange = { firstName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),

                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                )
            )
Spacer(modifier = Modifier.height(25.dp))
            Text("Last name",modifier = Modifier.fillMaxWidth().padding(start = 25.dp))
            // TextField at the bottom
            TextField(
                shape = RoundedCornerShape(20),
                value = lastName,
                onValueChange = { lastName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),

                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                )
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text("Email name",modifier = Modifier.fillMaxWidth().padding(start = 25.dp))
            // TextField at the bottom
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                shape = RoundedCornerShape(20),

                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                if (validateRegData(
                        firstName,
                        lastName,
                        email)){
                    sharedPreferences.edit()
                        .putString("firstName", firstName)
                        .putString("lastName", lastName)
                        .putString("email", email)
                        .putBoolean("userRegistered", true)
                        .apply()

                    Toast.makeText(context,
                        "Registration Successful",
                        Toast.LENGTH_SHORT)
                        .show()


                    navController.navigate(HomeDestination.route){
                        popUpTo(OnboardingDestination.route){inclusive = true}
                        launchSingleTop = true
                    }

                }
                else{
                    Toast.makeText(context,
                        "Invalid Details, Please try again",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            },modifier = Modifier
                .fillMaxWidth().padding(horizontal = 25.dp), shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow, contentColor = HighlightBlack)
                ) {  Text(text = "Register")}
        }
    }
}

