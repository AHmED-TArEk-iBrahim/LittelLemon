package com.example.littellemon


import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littellemon.ui.theme.HighlightBlack
import com.example.littellemon.ui.theme.PrimaryGreen
import com.example.littellemon.ui.theme.PrimaryYellow

@Composable
fun Profile(context: Context, navController: NavHostController) {
    val  sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    val firstName = remember {
        mutableStateOf(sharedPreferences.getString("firstName", ""))
    }

    val lastName = remember {
        mutableStateOf(sharedPreferences.getString("lastName", ""))
    }

    val email = remember {
        mutableStateOf(sharedPreferences.getString("email", ""))
    }
    val scrollState = rememberScrollState()


    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Row(Modifier.fillMaxWidth(0.6f)) {
            Image(painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo")
        }

        Text(text = "Personal Information",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineSmall)
        TextField(
            enabled = false,
            readOnly = true,
            value = firstName.value!!,
            onValueChange ={},
            label = { Text(text = "First Name")},
            singleLine = true,
            placeholder = { Text(text = "John")},
//            colors = TextFieldDefaults.colors(
//                disabledBorderColor = PrimaryGreen,
//                disabledLabelColor = PrimaryGreen
//            ),
            modifier = Modifier.fillMaxWidth())

     TextField(
            enabled = false,
            readOnly = true,
            value = lastName.value!!,
            onValueChange ={},
            label = { Text(text = "Last Name")},
            singleLine = true,
            placeholder = { Text(text = "Doe")},
//            colors = TextFieldDefaults.colors(
//                disabledBorderColor = PrimaryGreen,
//                disabledLabelColor = PrimaryGreen
//            ),
            modifier = Modifier.fillMaxWidth())

      TextField(
            enabled = false,
            readOnly = true,
            value = email.value!!,
            onValueChange ={},
            label = { Text(text = "Email")},
            singleLine = true,
            placeholder = { Text(text = "johndoe@gmail.com")},
//            colors = TextFieldDefaults.colors(
//                disabledBorderColor = PrimaryGreen,
//                disabledLabelColor = PrimaryGreen
//            ),
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            sharedPreferences.edit()
                .clear()
                .apply()

            navController.navigate(OnboardingDestination.route){
                popUpTo(HomeDestination.route){inclusive = true}
                launchSingleTop = true
            }

        },
            modifier = Modifier
                .fillMaxWidth().padding(horizontal = 25.dp), shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow, contentColor = HighlightBlack)
        ) {
            Text(text = "Log Out")
        }
    }

}












