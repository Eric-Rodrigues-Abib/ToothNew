package com.toothnow.toothapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.functions.FirebaseFunctions
import com.toothnow.toothapp.ui.theme.ToothNowTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = FirebaseApp.initializeApp(this)
        val functions = app?.let { FirebaseFunctions.getInstance(it, "southamerica-east1") }

        setContent {
            ToothNowTheme {
                var name by remember { mutableStateOf("") }
                var phone by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var bio by remember { mutableStateOf("") }

                val addresses = remember { mutableStateListOf("") }

                Column(modifier = Modifier.padding(16.dp)) {
                    Text("ToothNow - Dentista", style = MaterialTheme.typography.h5)
                    Spacer(modifier = Modifier.height(20.dp))

                    Text("Informações pessoais:", style = MaterialTheme.typography.h6)
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Nome") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    TextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Telefone") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("E-mail") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Endereços de atendimento: ", style = MaterialTheme.typography.h6)

                    addresses.forEachIndexed { index, endereco ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "Endereço ${index + 1}:",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                            if (index > 0) {
                                IconButton(
                                    onClick = { addresses.removeAt(index) },
                                    modifier = Modifier.width(24.dp).height(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = ""
                                    )
                                }
                            }
                            TextField(
                                value = endereco,
                                onValueChange = { addresses[index] = it },
                                label = { Text("Endereço") },
                                modifier = Modifier.weight(2f).padding(start = 8.dp)
                            )
                        }
                    }

                    if (addresses.size < 3) {
                        Button(
                            onClick = { addresses.add("") },
                            modifier = Modifier.align(Alignment.End).padding(end = 16.dp)
                        ) {
                            Text("+")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Currículo: ", style = MaterialTheme.typography.h6)

                    TextField(
                        value = bio,
                        onValueChange = { bio = it },
                        label = { Text("Digite até 5000 caracteres.") },
                        maxLines = 10,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Button(
                        onClick = {
                            val data = hashMapOf(
                                "nome" to name,
                                "telefone" to phone,
                                "email" to email,
                                "curriculo" to bio
                            )

                            functions!!.getHttpsCallable("salvarDadosDentista")
                                .call(data)
                                .continueWith { task ->
                                    val result = task.result?.data as String
                                    result
                                }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Enviar")
                    }
                }
            }
        }
    }
}