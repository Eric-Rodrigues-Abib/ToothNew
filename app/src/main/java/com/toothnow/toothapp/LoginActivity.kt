package com.toothnow.toothapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class LoginActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            FormLogin()
        }
    }

    
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Preview
    @Composable
    private fun FormLogin(){
        val nameState = remember {
            mutableStateOf("")
        }
        val passwordState = remember {
            mutableStateOf("")
        }

        Scaffold() {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Text(text = "TOOTH NOW", fontWeight = FontWeight.Bold, fontSize = 22.sp)

                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                ){
                    OutlinedTextField(
                        value = nameState.value,
                        onValueChange = { TextoRecebido -> nameState.value = TextoRecebido },
                        label = { Text(text = "User Email") })
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = passwordState.value,
                        onValueChange = { TextoRecebido -> passwordState.value = TextoRecebido },
                        label = { Text(text = "Password") })
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Login")
                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Forgot my Account")
                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Doesn't have an Account?")//aonde ta o TODO deve trocar para por o intent para trocar de tela
                    }


                }

            }

        }
    }
}