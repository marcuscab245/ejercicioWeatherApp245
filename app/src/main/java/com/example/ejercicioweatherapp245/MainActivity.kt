package com.example.ejercicioweatherapp245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejercicioweatherapp245.ui.theme.EjercicioWeatherApp245Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjercicioWeatherApp245Theme {
                WeatherAppScreen()
            }
        }
    }
}

@Composable
fun WeatherAppScreen() {
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF72EAFF),
            Color(0xFF72C2FF),
            Color(0xFF003888)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundBrush)
            .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("El Salvador", color = Color.White, fontSize = 24.sp)
        Text("25°C", color = Color.White, fontSize = 64.sp, fontWeight = FontWeight.Bold)
        Text("Soleado", color = Color.White, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(24.dp))

        HourlyForecastSection()

        Spacer(modifier = Modifier.height(16.dp))

        WeatherDetailCard()

        Spacer(modifier = Modifier.height(16.dp))

        WeeklyForecastSection()

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text("ACTUALIZAR", color = Color(0xFF003888), fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun HourlyForecastSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("PRONOSTICO POR HORAS", color = Color.LightGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item { HourlyItem("Ahora", "☀️", "25°") }
            item { HourlyItem("14:00", "☀️", "26°") }
            item { HourlyItem("16:00", "⛅", "24°") }
            item { HourlyItem("18:00", "⛅", "22°") }
            item { HourlyItem("20:00", "🌙", "20°") }
        }
    }
}

@Composable
fun HourlyItem(time: String, icon: String, temp: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(time, color = Color.White, fontSize = 14.sp)
        Text(icon, fontSize = 24.sp, modifier = Modifier.padding(vertical = 4.dp))
        Text(temp, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun WeatherDetailCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.2f)),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("DETALLES", color = Color.LightGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                WeatherInfoItem("Humedad", "65%")
                WeatherInfoItem("Viento", "12 km/h")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                WeatherInfoItem("Presión", "1012 hPa")
                WeatherInfoItem("UV", "5")
            }
        }
    }
}

@Composable
fun WeatherInfoItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(label, color = Color.LightGray, fontSize = 14.sp)
        Text(value, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun WeeklyForecastSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("PRONOSTICO SEMANAL", color = Color.LightGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.2f)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                WeeklyItem("Lun", "☀️", "28° / 22°")
                Spacer(modifier = Modifier.height(12.dp))
                WeeklyItem("Mar", "⛅", "27° / 21°")
                Spacer(modifier = Modifier.height(12.dp))
                WeeklyItem("Mié", "🌧️", "26° / 20°")
                Spacer(modifier = Modifier.height(12.dp))
                WeeklyItem("Jue", "⛅", "25° / 19°")
                Spacer(modifier = Modifier.height(12.dp))
                WeeklyItem("Vie", "🌧️", "24° / 18°")
            }
        }
    }
}

@Composable
fun WeeklyItem(day: String, icon: String, temps: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(day, color = Color.White, fontSize = 16.sp, modifier = Modifier.weight(1f))
        Text(icon, fontSize = 20.sp, modifier = Modifier.weight(1f))
        Text(temps, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}