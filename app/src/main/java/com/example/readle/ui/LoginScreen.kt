package com.example.readle.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.readle.R
import com.example.readle.ReadLeScreen
import com.example.readle.ui.theme.Beige01
import com.example.readle.ui.theme.Beige02
import com.example.readle.ui.theme.Green01

@Composable
fun LoginScreen(
    values: PaddingValues,
    viewModel: ReadLeViewModel = viewModel(),
    navController: NavController,
) {
    //get screen size
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val width = displayMetrics.widthPixels
    val height = displayMetrics.heightPixels


    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var showPopup by remember { mutableStateOf(false) }


    /*TODO*/
    //do something with something to make something
    //AKA login screen :)
    //make subnavigation for register screen and forgot password

    //BEWARE this code is not yet tested and probably has bugs ;)
    //

    Column(
        modifier = Modifier
            .padding(values)
    ) {
        //textSize 80 je probably too much
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Login",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 60.sp,
            color = Green01
        )

        Spacer(modifier = Modifier.height(54.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            placeholder = { Text(text = "Username") },
            singleLine = true,
            modifier = Modifier
                .height(52.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Beige02,
                focusedContainerColor = Beige02
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text(text = "Password") },
            singleLine = true,
            modifier = Modifier
                .height(52.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painter = painterResource(id =if (isPasswordVisible) R.drawable.visibility else R.drawable.visibility_off),
                        contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password"
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Beige02,
                focusedContainerColor = Beige02
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )

        PopupBox(
            popupWidth = width/4,
            popupHeight = height/8,
            showPopup = showPopup,
            onClickOutside = {showPopup = false},
            content = {Text("Invalid login", color = Beige01)})

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            content = { Text(text = "Login") },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Green01,
                contentColor = Beige01
            ),
            onClick = {
                if (username.isBlank() || username.isEmpty()) {
                    showPopup = true
                } else if (password.isBlank() || password.isEmpty()) {
                    showPopup = true
                } else {
                    viewModel.authenticateUser(username, password) { isValid ->     //password bi lahko encryptau z BCrypt alpa kej podobnga
                        if (isValid) {
                            navController.navigate(ReadLeScreen.Home.name)
                        } else {
                            showPopup = true
                        }
                    }
                }
            }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            content = { Text(text = "Register") },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Green01,
                contentColor = Beige01
            ),
            onClick = {
                navController.navigate(ReadLeScreen.Register.name)
            }
        )

        TextButton (
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            content = { Text(text = "Forgot password?") },
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color.Transparent,
                contentColor = Green01
            ),
            onClick = {
                navController.navigate(ReadLeScreen.ForgotPassword.name)
            }
        )
    }
}



@Composable
fun PopupBox(
    popupWidth:Int,
    popupHeight:Int,
    showPopup: Boolean,
    onClickOutside: () -> Unit,
    content: @Composable() () -> Unit,
) {

    if (showPopup) {
        // full screen background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .zIndex(10F),
            contentAlignment = Alignment.Center,
        ) {
            // popup
            Popup(
                alignment = Alignment.Center,
                properties = PopupProperties(
                    excludeFromSystemGesture = true,
                ),
                // to dismiss on click outside
                onDismissRequest = { onClickOutside()},
            ) {
                Box(
                    Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .width(popupWidth.dp)
                        .height(popupHeight.dp)
                        .background(Green01),
                    contentAlignment = Alignment.Center,

                ) {
                    content()
                }
            }
        }
    }
}