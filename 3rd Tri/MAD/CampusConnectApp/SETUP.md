# Campus Connect App - Setup Guide

## Quick Start

### Step 1: Sync Gradle
The project has been set up with all necessary files and dependencies. You need to sync Gradle to download the dependencies:

1. Open the project in Android Studio
2. Click **File** → **Sync Project with Gradle Files** (or click the sync icon in the toolbar)
3. Wait for Gradle sync to complete

### Step 2: Build the Project
After successful Gradle sync:
1. Click **Build** → **Make Project** (or Ctrl+F9 / Cmd+F9)
2. Wait for the build to complete

### Step 3: Run the App
1. Select your target device (physical device or emulator)
2. Click the **Run** button (green play icon) or press Shift+F10 / Ctrl+R
3. The app will install and launch on your device

## Project Structure

```
CampusConnectApp/
├── app/
│   ├── build.gradle.kts                 # Dependencies configured
│   └── src/main/java/com/example/campusconnectapp/
│       ├── MainActivity.kt              # ✅ Main app with navigation setup
│       ├── navigation/
│       │   └── Screen.kt               # ✅ Navigation routes
│       ├── screens/
│       │   ├── HomeScreen.kt           # ✅ Home screen
│       │   ├── ProfileScreen.kt        # ✅ Profile screen
│       │   ├── NotificationsScreen.kt  # ✅ Notifications screen
│       │   ├── DepartmentsScreen.kt    # ✅ Departments grid
│       │   └── EventDetailsScreen.kt   # ✅ Event details
│       └── components/
│           ├── BottomNavigationBar.kt  # ✅ Bottom nav
│           └── NavigationDrawer.kt     # ✅ Side drawer
└── README.md                            # ✅ Documentation
```

## Features Implemented

### ✅ All 5 Screens Created
- Home Screen
- Profile Screen
- Notifications Screen
- Departments Screen
- Event Details Screen

### ✅ Three Navigation Mechanisms

#### 1. Navigation Drawer (Side Menu)
- Hamburger icon (☰) in top bar
- Items: Home, Departments, Profile, Logout
- Swipe from left edge or tap menu icon

#### 2. Bottom Navigation
- Fixed at bottom of screen
- Items: Home, Notifications, Profile
- Hidden on Event Details screen

#### 3. Button Navigation
- Tap department cards on Departments screen
- Navigates to Event Details with department name
- Demonstrates parameter passing

### ✅ Proper Back Stack Management
- Each navigation adds to back stack
- Back button navigates correctly
- State preservation on navigation

## Testing the Navigation

### Test 1: Bottom Navigation
1. Launch app (starts on Home)
2. Tap **Notifications** in bottom nav
3. Tap **Profile** in bottom nav
4. Press back button → should go to Notifications
5. Press back again → should go to Home

### Test 2: Navigation Drawer
1. On any screen, tap the ☰ menu icon
2. Select **Departments** from drawer
3. Tap menu icon again
4. Select **Profile**
5. Press back → should return to Departments

### Test 3: Button Navigation & Back Stack
1. Navigate to **Departments** (via drawer)
2. Tap any department card (e.g., "Computer Science")
3. View Event Details screen
4. Note: Bottom navigation is hidden here
5. Press back button → returns to Departments
6. Press back again → returns to Home

### Test 4: Combined Navigation
1. Start at Home
2. Go to Departments (via drawer)
3. Tap a department → Event Details
4. Press back → Departments
5. Use bottom nav → go to Notifications
6. Use drawer → go to Profile
7. Press back multiple times → should retrace steps

## Dependencies Added

The following dependencies were added to [app/build.gradle.kts](app/build.gradle.kts):

```kotlin
// Navigation
implementation("androidx.navigation:navigation-compose:2.7.7")

// Material Icons Extended
implementation("androidx.compose.material:material-icons-extended:1.6.1")
```

## Common Issues & Solutions

### Issue 1: "Unresolved reference" errors
**Solution:** Sync Gradle files
- File → Sync Project with Gradle Files

### Issue 2: Build fails
**Solution:** 
1. Clean project: Build → Clean Project
2. Rebuild: Build → Rebuild Project

### Issue 3: App crashes on launch
**Solution:**
1. Check logcat for error messages
2. Ensure minimum SDK is 24 or higher
3. Reinstall the app

## Code Highlights

### MainActivity.kt - Navigation Setup
```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampusConnectApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { NavigationDrawerContent(...) }
    ) {
        Scaffold(
            bottomBar = { BottomNavigationBar(...) }
        ) {
            NavHost(navController, startDestination = "home") {
                // All screen destinations
            }
        }
    }
}
```

### Screen.kt - Navigation Routes
```kotlin
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Notifications : Screen("notifications")
    object Departments : Screen("departments")
    object EventDetails : Screen("event_details/{departmentName}") {
        fun createRoute(departmentName: String) = "event_details/$departmentName"
    }
}
```

## Next Steps

After successfully running the app:

1. **Explore the code** - Understand how navigation is implemented
2. **Modify screens** - Customize the UI to your needs
3. **Add features** - Implement logout functionality, add more departments
4. **Enhance UI** - Add animations, improve styling
5. **Add data layer** - Connect to real data sources or APIs

## Requirements Met ✅

- ✅ 5 screens implemented
- ✅ Navigation Drawer with 4 items (Home, Departments, Profile, Logout)
- ✅ Bottom Navigation with 3 items (Home, Notifications, Profile)
- ✅ Button-based navigation (Departments → Event Details)
- ✅ Proper back stack behavior
- ✅ Parameter passing in navigation
- ✅ Conditional UI (bottom nav hidden on Event Details)
- ✅ Modern Material Design 3
- ✅ Jetpack Compose with Navigation Compose

## Support

If you encounter any issues:
1. Check this guide for solutions
2. Review the [README.md](README.md) for detailed documentation
3. Examine the code comments in each file
4. Ensure all dependencies are properly synced

---

**Ready to Run!** 🚀

Just sync Gradle, build, and run the app to see all navigation mechanisms in action.
