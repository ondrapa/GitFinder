package com.example.gitusers.ui.InfoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gitusers.ui.theme.PalleteLightBrown
import com.example.gitusers.ui.theme.PalleteLighterBrown
import com.example.gitusers.ui.theme.PalleteYellow

@Composable
fun InfoScreen() {
    Box(
        modifier = Modifier
            .background(PalleteLighterBrown)
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.padding(25.dp)
        ) {
            item {
                Text(
                    fontSize = 20.sp,
                    text = "Hi",
                    color = PalleteYellow
                )
                Text(
                    fontSize = 20.sp,
                    text = "My name is Ondra, and I would like to tell you something about my app.",
                    color = PalleteYellow
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    fontSize = 18.sp,
                    text = "Get help",
                    color = PalleteLightBrown
                )
                Text(
                    fontSize = 16.sp,
                    text = "As you propably already know, " +
                            "there is a floating action button " +
                            "in the right top corner. If you dont " +
                            "know how to use the app click it and " +
                            "you will find answers to all your questions.",
                    color = PalleteLightBrown
                )

                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    fontSize = 18.sp,
                    text = "Find friend",
                    color = PalleteLightBrown
                )
                Text(
                    fontSize = 16.sp,
                    text = "Goal of this app is for everyone to be easy to " +
                            "find their friend on github, just by typing their" +
                            "username. Try it by typing name in the textfield " +
                            "that is on the main screen",
                    color = PalleteLightBrown
                )

                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    fontSize = 18.sp,
                    text = "Error",
                    color = PalleteLightBrown
                )
                Text(
                    fontSize = 16.sp,
                    text = "If an error occurred, you can see the reason " +
                            "why on the snackbar. If it says that this user" +
                            "is not on github try to check if you typed it correctly" +
                            "you will receive a warning if you leave the textfield " +
                            "blank and try to find someone",
                    color = PalleteLightBrown
                )

                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    fontSize = 18.sp,
                    text = "Success",
                    color = PalleteLightBrown
                )
                Text(
                    fontSize = 16.sp,
                    text = "If everything went well you will see repositories of the user" +
                            "you was looking for, you can also click on any repository to find " +
                            "more details about it, such as branches or latest commits",
                    color = PalleteLightBrown
                )

                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    fontSize = 18.sp,
                    text = "Details",
                    color = PalleteLightBrown
                )
                Text(
                    fontSize = 16.sp,
                    text = "On the repository screen you can now see all the branches the project" +
                            "have, and also last commits, max 10. I hope this gave you the info" +
                            "you have been looking for. I hope wee will see each other again",
                    color = PalleteLightBrown
                )
            }
        }
    }
}