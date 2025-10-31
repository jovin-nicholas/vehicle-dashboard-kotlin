# Vehicle Telemetry Dashboard

Android Automotive OS application demonstrating real-time vehicle telemetry display with modern Android architecture patterns.

## Overview

Built as a learning project to understand Android Automotive OS platform development and apply mobile architecture patterns to automotive infotainment systems. Implements simulated vehicle telemetry (speed, battery, temperature) using Kotlin, Jetpack Compose, and coroutines.

## Architecture

- **Pattern**: MVVM (Model-View-ViewModel)
- **UI**: Jetpack Compose (declarative UI)
- **State Management**: StateFlow for reactive updates
- **Async Processing**: Kotlin Coroutines + Flow APIs
- **Platform**: Android Automotive OS

### Architecture Diagram

```
┌─────────────────┐
│   Compose UI    │  ← Declarative rendering, Material Design
└────────┬────────┘
         │
┌────────▼────────┐
│   ViewModel     │  ← Business logic, StateFlow emissions
└────────┬────────┘
         │
┌────────▼────────┐
│   Repository    │  ← Data simulation, coroutine timers
└─────────────────┘
```

## Features

- **Real-time Updates**: Telemetry refreshes every 1 second via coroutine-based simulation
- **Three Metrics**: 
  - Speed (0-200 km/h)
  - Battery Level (0-100%)
  - Temperature (-10°C to 50°C)
- **Responsive UI**: Optimized Compose recomposition for smooth rendering
- **Modular Design**: Easy to extend with additional vehicle metrics
- **Clean Architecture**: Clear separation between UI, business logic, and data layers

## Technical Implementation

### State Management with StateFlow

```kotlin
class TelemetryViewModel : ViewModel() {
    private val _speed = MutableStateFlow(0)
    val speed: StateFlow<Int> = _speed.asStateFlow()
    
    init {
        viewModelScope.launch {
            while(isActive) {
                _speed.value = generateSimulatedSpeed()
                delay(1000)
            }
        }
    }
}
```

### Compose UI with State Collection

```kotlin
@Composable
fun TelemetryDashboard(viewModel: TelemetryViewModel) {
    val speed by viewModel.speed.collectAsState()
    val battery by viewModel.battery.collectAsState()
    val temperature by viewModel.temperature.collectAsState()
    
    Column(modifier = Modifier.fillMaxSize()) {
        SpeedGauge(speed)
        BatteryIndicator(battery)
        TemperatureDisplay(temperature)
    }
}
```

## Setup & Installation

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- Android Automotive OS system image (API 31+)
- Android Auto Desktop Head Unit Emulator

### Installation Steps

1. **Install Android Automotive Emulator**
   - Open Android Studio
   - SDK Manager → SDK Tools
   - Install "Android Auto Desktop Head Unit Emulator"

2. **Install System Image**
   - SDK Manager → SDK Platforms
   - Install "Android 12+ (API 31+) Automotive System Image"
   - Select your architecture (ARM/x86)

3. **Create Virtual Device**
   - Device Manager → Create Device
   - Select "Automotive" category
   - Choose installed system image
   - Finish setup

4. **Run Application**
   ```bash
   git clone https://github.com/jovin-nicholas/vehicle-dashboard-kotlin.git
   cd vehicle-dashboard-kotlin
   # Open in Android Studio
   # Select Automotive emulator as target
   # Run app (Shift+F10)
   ```

## Project Structure

```
app/
├── src/main/
│   ├── java/com/example/vehicledashboard/
│   │   ├── ui/
│   │   │   ├── DashboardScreen.kt      # Main Compose UI
│   │   │   ├── components/             # Reusable UI components
│   │   │   └── theme/                  # Material Design theme
│   │   ├── viewmodel/
│   │   │   └── TelemetryViewModel.kt   # Business logic + StateFlow
│   │   ├── data/
│   │   │   └── TelemetryRepository.kt  # Data simulation
│   │   └── MainActivity.kt             # Entry point
│   └── res/                            # Resources
└── build.gradle.kts                    # Dependencies
```

## Key Technologies

- **Kotlin**: 100% Kotlin codebase
- **Jetpack Compose**: Modern declarative UI toolkit
- **Coroutines**: Asynchronous programming
- **StateFlow**: Reactive state management
- **Android Automotive OS**: Vehicle-specific Android platform
- **Material Design 3**: UI design system

## Learning Outcomes

This project demonstrates:
- Android Automotive OS platform development
- MVVM architecture implementation
- Jetpack Compose for automotive displays
- Kotlin coroutines for real-time data simulation
- StateFlow for reactive state management
- Clean architecture principles

## Future Enhancements

- [ ] Connect to real vehicle data via VHAL (Vehicle Hardware Abstraction Layer)
- [ ] Add more metrics (fuel level, RPM, tire pressure)
- [ ] Implement dark mode for night driving
- [ ] Add navigation integration
- [ ] Implement unit tests for ViewModel
- [ ] Add UI tests with Compose Testing

## License

MIT License

## Contact

Jovin Nicholas  
[LinkedIn](https://www.linkedin.com/in/jovin-nicholas/) | [GitHub](https://github.com/jovin-nicholas) | jovin0251@gmail.com

---

**Note**: This is a learning/portfolio project with simulated data. It demonstrates Android platform development patterns applicable to automotive infotainment systems but does not connect to real vehicle hardware.
