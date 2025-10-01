package com.example.proyecto.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto.models.HServicesScheduleData
import com.example.proyecto.models.Hostel
import com.example.proyecto.models.HostelServices
import com.example.proyecto.models.Location
val scheduleData = HServicesScheduleData(
    created_at = "2025-09-30T16:00:00Z",
    created_by_name = "Admin User",
    day_name = "Monday",
    day_of_week = 1,
    duration_hours = 12,
    end_time = "20:00",
    id = "schedule_001",
    is_available = true,
    start_time = "08:00",
    updated_at = "2025-09-30T16:30:00Z"
)

val hostelService = HostelServices(
    created_at = "2025-09-30T16:30:00Z",
    created_by_name = "Admin User",
    hostel = "hostel_001",
    hostel_location = "loc_001",
    hostel_name = "Montaña Hostel",
    id = "service_001",
    is_active = true,
    schedule = "08:00 - 20:00",
    schedule_data = scheduleData,
    service = "cleaning",
    service_description = "Daily room cleaning and bed making",
    service_max_time = 60,  // in minutes
    service_name = "Room Cleaning",
    service_needs_approval = false,
    service_price = "200 MXN",
    total_reservations = 15,
    updated_at = "2025-09-30T16:45:00Z"
)


val myHostel = Hostel(
    created_at = "2025-09-30T16:00:00Z",
    id = "hostel_001",
    is_active = true,
    location = Location(
        address = "Av. Universidad 123",
        city = "Monterrey",
        country = "Mexico",
        id = "loc_001",
        landmarks = "Near Macroplaza",
        latitude = 25,          // integers as your class expects
        longitude = -100,
        state = "Nuevo León",
        zip_code = "64000"
    ),
    men_capacity = 50,
    name = "Montaña Hostel",
    phone = "+52 81 1234 5678",
    women_capacity = 40
)

@Preview
@Composable
fun HostelCard() {

}

@Preview
@Composable
fun ServiceCard() {

}