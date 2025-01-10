package com.example.readle.ui

import android.content.Intent
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
import androidx.compose.material3.rememberModalBottomSheetState
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
fun ForgotPasswordScreen(
    values: PaddingValues,
    viewModel: ReadLeViewModel = viewModel(),
    navController: NavController,
) {
    //get screen size
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val width = displayMetrics.widthPixels
    val height = displayMetrics.heightPixels

    val senderEmail by remember { mutableStateOf("lj15233@student.uni-lj.si") }      //vedno isti
    var recieverEmail by remember { mutableStateOf("") }
    val emailSubject by remember { mutableStateOf("Forgot ReadLe password") }
    val emailBody by remember { mutableStateOf("Hello, \n\nthis email has been sent from the ReadLe app. \nIf you did not prompt the sending of this email, please contact us at lj15233@student.uni-lj.si.\n\nThank you, \nReadLe team.") }

    var showPopup by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .padding(values)
    ) {
        //textSize 80 je probably too much
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Forgot Password",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 60.sp,
            color = Green01,
            lineHeight = 60.sp
        )

        Spacer(modifier = Modifier.height(54.dp))

        Text(
            text = "We will send you an email to this address.",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            color = Green01
        )

        Spacer(modifier = Modifier.height(54.dp))

        TextField(
            value = recieverEmail,
            onValueChange = { recieverEmail = it },
            placeholder = { Text(text = "Email") },
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


        PopupBox(
            popupWidth = width/4,
            popupHeight = height/8,
            showPopup = showPopup,
            onClickOutside = {showPopup = false},
            content = {Text("Please fill out your email.", color = Beige01)})

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            content = { Text(text = "Send email") },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Green01,
                contentColor = Beige01
            ),
            onClick = {
                //TODO send an email to address

                if (recieverEmail.isBlank() || recieverEmail.isEmpty()) {
                    showPopup = true
                } else {
                    //create intent za posiljanje emaila
                    //nalasc narjen tko, da je treba email odpret pa manually poslat!!!!!!
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "message/rfc822" // Ensures only email apps handle this
                        putExtra(Intent.EXTRA_EMAIL, arrayOf(recieverEmail))
                        putExtra(Intent.EXTRA_SUBJECT, emailSubject)
                        putExtra(Intent.EXTRA_TEXT, emailBody)
                    }

                    // Start the email app
                    if (intent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(Intent.createChooser(intent, "Choose an email app"))
                    } else {
                        // Show a message if no email app is found
                        showPopup = true
                    }
                }
                navController.navigate(ReadLeScreen.Login.name)
            }
        )


        Button(
            modifier = Modifier
                .fillMaxWidth(),
            content = { Text(text = "Back") },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Green01,
                contentColor = Beige01
            ),
            onClick = {
                navController.navigate(ReadLeScreen.Login.name)
            }
        )


    }
}
