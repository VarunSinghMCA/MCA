# Campus Connect App

A comprehensive Android application demonstrating multiple navigation patterns in Jetpack Compose for managing university campus information.

## Features

### Screens
1. **Home Screen** - Welcome screen with quick links
2. **Profile Screen** - Student profile information
3. **Notifications Screen** - Campus notifications and updates
4. **Departments Screen** - Grid view of university departments
5. **Event Details Screen** - Detailed information about department events

## Navigation Mechanisms

### 1. Navigation Drawer (Side Menu)
- Accessible from the hamburger icon (☰) on any screen with a top bar
- Contains:
  - **Home** - Navigate to home screen
  - **Departments** - View all departments
  - **Profile** - View student profile
  - **Logout** - Logout option

**How to Access:** Tap the menu icon (☰) in the top-left corner of the screen.

### 2. Bottom Navigation
- Always visible at the bottom of main screens
- Contains:
  - **Home** - Home screen with campus overview
  - **Notifications** - View campus notifications
  - **Profile** - Access student profile

**Note:** Bottom navigation is hidden on the Event Details screen for better content focus.

### 3. Button-based Navigation
- On the **Departments Screen**, tap any department card
- Navigates to the **Event Details Screen** for that department
- Demonstrates parameter passing in navigation

## Back Stack Behavior

The app maintains proper back stack navigation:

**Example Navigation Flow:**
```
Home → Departments → Event Details
```

**Pressing Back Button:**
```
Event Details → Departments → Home → Exit App
```

### How It Works
1. Each navigation action pushes a new screen onto the back stack
2. The back button pops the top screen from the stack
3. Navigation state is preserved when switching between bottom navigation items

## Technical Implementation

### Technologies Used
- **Jetpack Compose** - Modern declarative UI
- **Navigation Compose** - Type-safe navigation
- **Material Design 3** - Modern UI components
- **Kotlin Coroutines** - Asynchronous drawer operations

### Project Structure
```
app/src/main/java/com/example/campusconnectapp/
├── MainActivity.kt                      # Main entry point
├── navigation/
│   └── Screen.kt                       # Navigation routes
├── screens/
│   ├── HomeScreen.kt                   # Home screen UI
│   ├── ProfileScreen.kt                # Profile screen UI
│   ├── NotificationsScreen.kt          # Notifications screen UI
│   ├── DepartmentsScreen.kt            # Departments grid UI
│   └── EventDetailsScreen.kt           # Event details UI
└── components/
    ├── BottomNavigationBar.kt          # Bottom nav component
    └── NavigationDrawer.kt             # Drawer component
```

## Running the App

1. **Sync Gradle** - Make sure all dependencies are downloaded
2. **Build the project** - Build → Make Project
3. **Run** - Select your device/emulator and click Run

## Key Concepts Demonstrated

### 1. NavHost Setup
The `NavHost` in [MainActivity.kt](app/src/main/java/com/example/campusconnectapp/MainActivity.kt) defines all navigation routes and composable destinations.

### 2. Navigation Arguments
The Event Details screen receives department name as a navigation argument:
```kotlin
composable(
    route = "event_details/{departmentName}",
    arguments = listOf(navArgument("departmentName") { type = NavType.StringType })
)
```

### 3. State Management
- **DrawerState** - Controls drawer open/close
- **NavBackStackEntry** - Tracks current route
- **CoroutineScope** - Manages async drawer animations

### 4. Conditional UI
Bottom navigation visibility is controlled based on current route:
```kotlin
val showBottomNav = when {
    currentRoute.startsWith("event_details") -> false
    else -> true
}
```

## Navigation Patterns

### Bottom Navigation Navigation
```kotlin
navController.navigate(route) {
    popUpTo("home") { saveState = true }
    launchSingleTop = true
    restoreState = true
}
```

### Drawer Navigation
```kotlin
scope.launch {
    drawerState.open()  // or drawerState.close()
}
```

### Back Navigation
```kotlin
navController.navigateUp()
```

## Dependencies

```gradle
// Navigation
implementation("androidx.navigation:navigation-compose:2.7.7")

// Material Icons Extended
implementation("androidx.compose.material:material-icons-extended:1.6.1")
```

## Learning Outcomes

By exploring this app, you will understand:
1. ✅ Multiple navigation patterns in Compose
2. ✅ Back stack management
3. ✅ Navigation with parameters
4. ✅ Drawer integration
5. ✅ Bottom navigation implementation
6. ✅ State preservation during navigation
7. ✅ Conditional UI based on navigation state

## Author

Built for Mobile Application Development course demonstrating navigation concepts in Android Compose.
