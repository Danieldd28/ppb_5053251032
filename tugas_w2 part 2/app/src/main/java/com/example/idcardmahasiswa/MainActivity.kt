package com.example.idcardmahasiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(R.color.screen_background)
                ) {
                    IdCardMahasiswa()
                }
            }
        }
    }
}

@Composable
fun IdCardMahasiswa() {
    Box(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.card_background)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column {
                CardHeader()
                CardBody()
            }
        }
    }
}

@Composable
fun CardHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.card_primary))
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.logo_its),
            contentDescription = stringResource(R.string.campus_name),
            modifier = Modifier.size(40.dp)
        )
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = stringResource(R.string.campus_name),
                color = colorResource(R.color.text_light),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = stringResource(R.string.app_name),
                color = colorResource(R.color.text_light),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun CardBody() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.foto_mahasiswa),
            contentDescription = stringResource(R.string.label_nama),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
        )
        Column(modifier = Modifier.padding(start = 20.dp)) {
            ProfileField(
                label = stringResource(R.string.label_nama),
                value = stringResource(R.string.value_nama),
                bold = true,
                valueSize = 16.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            ProfileField(
                label = stringResource(R.string.label_nrp),
                value = stringResource(R.string.value_nrp),
                valueSize = 14.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            ProfileField(
                label = stringResource(R.string.label_prodi),
                value = stringResource(R.string.value_prodi),
                valueSize = 14.sp
            )
        }
    }
}

@Composable
fun ProfileField(label: String, value: String, valueSize: TextUnit, bold: Boolean = false) {
    Column {
        Text(text = label, color = colorResource(R.color.text_muted), fontSize = 11.sp)
        Text(
            text = value,
            color = colorResource(R.color.text_dark),
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
            fontSize = valueSize
        )
    }
}

@Preview(showBackground = true, name = "ID Card Mahasiswa")
@Composable
fun IdCardMahasiswaPreview() {
    MaterialTheme {
        IdCardMahasiswa()
    }
}
