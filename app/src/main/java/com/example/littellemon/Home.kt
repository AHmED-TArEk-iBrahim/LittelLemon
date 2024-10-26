package com.example.littellemon

import android.content.Context
import android.widget.Toast
import com.example.littellemon.Data.MenuItemRoom
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littellemon.Data.MyViewModel
import com.example.littellemon.ui.theme.HighlightBlack
import com.example.littellemon.ui.theme.HighlightGray
import com.example.littellemon.ui.theme.PrimaryGreen
import com.example.littellemon.ui.theme.PrimaryYellow
import com.example.littellemon.ui.theme.Secondary2

@Composable
fun Home(navController: NavHostController) {
    val viewModel: MyViewModel = viewModel()
    val databaseMenuItems = viewModel.getAllDatabaseMenuItems().observeAsState(emptyList()).value
    val searchPhrase = remember {
        mutableStateOf("")
    }
    LaunchedEffect(Unit) {
        viewModel.fetchMenuDataIfNeeded()
    }


    val keyboardController = LocalSoftwareKeyboardController.current


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
                ,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            // Your content here
            header(navController)
            UpperPanel(){
                searchPhrase.value=it
            }
            LowerPanel(databaseMenuItems,searchPhrase)

            // TextField at the bottom

        }
    }


}
@Composable
fun header(navController: NavHostController){
    Row {
        Spacer(modifier = Modifier.weight(.50f))
        Image(
            painter = painterResource(R.drawable.logo),
            "logo",
            modifier = Modifier.size(width = 200.dp, height = 90.dp)
        )
        Spacer(modifier = Modifier.weight(.30f))
        Image(painter = painterResource(R.drawable.profile),"Profile", modifier = Modifier
            .size(70.dp) // adjust the size as needed
            .clip(CircleShape).clickable {
                navController.navigate(ProfileDestination.route)
            })
        Spacer(modifier = Modifier.weight(.20f))
    }
}
@Composable
fun UpperPanel(search : (parameter: String)-> Unit) {
    val searchPhrase = remember {
        mutableStateOf("")
    }

//    Log.d("AAAAA", "UpperPanel: ${searchPhrase.value}")
    Column(modifier = Modifier
        .background(PrimaryGreen)
        .padding(horizontal = 20.dp, vertical = 10.dp)) {
        Text(text = "Little Lemon", style = MaterialTheme.typography.headlineLarge, color = PrimaryYellow)
        Text(text = "Chicago", style = MaterialTheme.typography.headlineSmall, color = Color.White)
        Row(Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with  a modern twist. Turkish, Italian, Indian and chinese recipes are our speciality.",
                modifier = Modifier.fillMaxWidth(0.7f),
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge)
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero Image",
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
            )
        }

        Spacer(modifier = Modifier.size(10.dp))
        TextField(value = searchPhrase.value,
            onValueChange = {
                searchPhrase.value = it
                search(searchPhrase.value)
            },
            placeholder = {
                Text(text = "Enter Search Phrase")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon")
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background
            ),
            modifier = Modifier.fillMaxWidth())

    }

}
@Composable
fun LowerPanel(databaseMenuItems: List<MenuItemRoom>, search: MutableState<String>) {
    val categories = databaseMenuItems.map {
        it.category.replaceFirstChar {character ->
            character.uppercase()
        }
    }.toSet()


    val selectedCategory = remember {
        mutableStateOf("")
    }


    val items = if(search.value == ""){
        databaseMenuItems

    }
    else{
        databaseMenuItems.filter {
            it.title.contains(search.value, ignoreCase = true)

        }


    }



    val filteredItems = if(selectedCategory.value == "" || selectedCategory.value == "all"){
        items
    }
    else{
        items.filter {
            it.category.contains(selectedCategory.value, ignoreCase = true)
        }
    }


    Column() {
        MenuCategories(categories) {selectedCategory.value = it
        }
        Spacer(modifier = Modifier.size(2.dp))
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            for (item in filteredItems){
                MenuItem(item = item)
            }
        }

    }
}


@Composable
fun MenuCategories(categories: Set<String>, categoryLambda : (sel: String) -> Unit) {
    val cat = remember {
        mutableStateOf("")
    }

    Card(elevation = CardDefaults.cardElevation(
        defaultElevation = 10.dp
    ), modifier = Modifier.fillMaxWidth()) {

        Column(Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
            Text(text = "ORDER FOR DELIVERY", fontWeight = FontWeight.Bold)

            Row(modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                CategoryButton(category = "All"){
                    cat.value = it.lowercase()
                    categoryLambda(it.lowercase())
                }

                for (category in categories){
                    CategoryButton(category = category){
                        cat.value = it
                        categoryLambda(it)
                    }

                }

            }
        }
    }
}

@Composable
fun CategoryButton(category:String, selectedCategory: (sel: String) -> Unit) {
    val isClicked = remember{
        mutableStateOf(false)
    }
    Button(onClick = {
        isClicked.value = !isClicked.value
        selectedCategory(category)

    },
        colors = ButtonDefaults.buttonColors(
            contentColor = HighlightBlack,
            containerColor = HighlightGray
        )) {
        Text(text = category)
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(item: MenuItemRoom) {

    val itemDescription = if(item.description.length>100) {
        item.description.substring(0,100) + ". . ."
    }
    else{
        item.description
    }

    Card(elevation = CardDefaults.cardElevation(
        defaultElevation = 4.dp
    ),
        modifier = Modifier
            .clickable {

            }) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.fillMaxWidth(0.7f),
                verticalArrangement = Arrangement.SpaceBetween) {
                Text(text = item.title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 10.dp))

                Text(text ="$itemDescription", modifier = Modifier.padding(bottom = 10.dp))
                Text(text = "$ ${item.price}", fontWeight = FontWeight.Bold)

            }

            GlideImage(model = item.imageUrl,
                contentDescription = "",
                Modifier.size(100.dp, 100.dp),
                contentScale = ContentScale.Crop)
        }
    }

}