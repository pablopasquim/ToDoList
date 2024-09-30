package com.example.aula14

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aula14.ui.theme.Aula14Theme
import retrofit2.await

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutMain()
        }
    }
}

@Composable
fun LayoutMain(){

    var toDo by remember {
        mutableStateOf<List<ToDos>>(emptyList())
    }

    var erro by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit){

        try {

            val respostaUser = Instance.api.getUsers().await()

            toDo = respostaUser

        } catch (e: Exception){

            erro = "Deu errado ai man"

        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(21.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "TO DO LIST", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(14.dp))

        if(erro.isNotBlank()){

            Text(text = erro)

        } else{

            LazyColumn{

                items(toDo){

                    toDo ->

                    Spacer(modifier = Modifier.height(14.dp))
                    Text(text = "User Id: ${toDo.userId}", fontSize = 14.sp)

                    Text(text = "Id: ${toDo.id}", fontSize = 14.sp)

                    Text(text = "Title: ${toDo.title}", fontSize = 14.sp)

                    Text(text = "Completed: ${toDo.completed}", fontSize = 14.sp)

                }
            }
        }

    }
}