# Campus Connect App - Navigation Flow

## Navigation Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                     Campus Connect App                          │
│                                                                 │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │         Navigation Drawer (Side Menu)                    │  │
│  │  ┌─────────────────────────────────────────────────┐    │  │
│  │  │  ☰ Campus Connect                               │    │  │
│  │  │  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━   │    │  │
│  │  │  🏠 Home                                        │    │  │
│  │  │  🏫 Departments                                 │    │  │
│  │  │  👤 Profile                                     │    │  │
│  │  │  🚪 Logout                                      │    │  │
│  │  └─────────────────────────────────────────────────┘    │  │
│  └──────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘


┌───────────────── SCREEN FLOW ─────────────────────┐

    ┌─────────────────────────────────────────┐
    │         🏠 HOME SCREEN                  │
    │  ┌───────────────────────────────────┐  │
    │  │ ☰ Campus Connect                  │  │  ← Hamburger opens Drawer
    │  ├───────────────────────────────────┤  │
    │  │                                   │  │
    │  │  Welcome to Campus Connect        │  │
    │  │  • View Departments               │  │
    │  │  • Check Notifications            │  │
    │  │  • Update Profile                 │  │
    │  │                                   │  │
    │  └───────────────────────────────────┘  │
    │  ┌───────────────────────────────────┐  │
    │  │ 🏠 Home | 🔔 Notify | 👤 Profile │  │  ← Bottom Nav
    │  └───────────────────────────────────┘  │
    └─────────────────────────────────────────┘
                      ↓
                Bottom Nav or Drawer
                      ↓
    ┌─────────────────────────────────────────┐
    │       🔔 NOTIFICATIONS SCREEN           │
    │  ┌───────────────────────────────────┐  │
    │  │ ☰ Notifications                   │  │
    │  ├───────────────────────────────────┤  │
    │  │ 📅 New Event                      │  │
    │  │    Tech Fest 2026 open!           │  │
    │  │    2 hours ago                    │  │
    │  ├───────────────────────────────────┤  │
    │  │ 📝 Assignment Due                 │  │
    │  │    MAD assignment tomorrow        │  │
    │  │    5 hours ago                    │  │
    │  └───────────────────────────────────┘  │
    │  ┌───────────────────────────────────┐  │
    │  │ 🏠 Home | 🔔 Notify | 👤 Profile │  │
    │  └───────────────────────────────────┘  │
    └─────────────────────────────────────────┘
                      ↓
                Bottom Nav or Drawer
                      ↓
    ┌─────────────────────────────────────────┐
    │         👤 PROFILE SCREEN               │
    │  ┌───────────────────────────────────┐  │
    │  │ ☰ Profile                         │  │
    │  ├───────────────────────────────────┤  │
    │  │         👤                        │  │
    │  │      Student Name                 │  │
    │  │   student@campus.edu              │  │
    │  │                                   │  │
    │  │  👤 Student ID: STU-2024-001      │  │
    │  │  🎓 Dept: Computer Science        │  │
    │  │  📅 Year: 3rd Year                │  │
    │  │  📞 Contact: +1 234-567-8900      │  │
    │  └───────────────────────────────────┘  │
    │  ┌───────────────────────────────────┐  │
    │  │ 🏠 Home | 🔔 Notify | 👤 Profile │  │
    │  └───────────────────────────────────┘  │
    └─────────────────────────────────────────┘
                      ↓
                  Via Drawer
                      ↓
    ┌─────────────────────────────────────────┐
    │       🏫 DEPARTMENTS SCREEN             │
    │  ┌───────────────────────────────────┐  │
    │  │ ☰ Departments                     │  │
    │  ├───────────────────────────────────┤  │
    │  │  ┌─────────┐   ┌─────────┐       │  │
    │  │  │ 💻 Comp │   │ 📡 Elec │       │  │  ← Tap card
    │  │  │ Science │   │ tronics │       │  │    to navigate
    │  │  └─────────┘   └─────────┘       │  │
    │  │  ┌─────────┐   ┌─────────┐       │  │
    │  │  │ ⚙️ Mech │   │ 🏗️ Civil│       │  │
    │  │  │  anical │   │         │       │  │
    │  │  └─────────┘   └─────────┘       │  │
    │  └───────────────────────────────────┘  │
    │  ┌───────────────────────────────────┐  │
    │  │ 🏠 Home | 🔔 Notify | 👤 Profile │  │
    │  └───────────────────────────────────┘  │
    └─────────────────────────────────────────┘
                      ↓
               Button Navigation
          (With department parameter)
                      ↓
    ┌─────────────────────────────────────────┐
    │      📅 EVENT DETAILS SCREEN            │
    │  ┌───────────────────────────────────┐  │
    │  │ ← Computer Science Events         │  │  ← Back button
    │  ├───────────────────────────────────┤  │
    │  │      📅                           │  │
    │  │  Computer Science Tech Fest       │  │
    │  │                                   │  │
    │  │  📅 Date: March 15-17, 2026       │  │
    │  │  🕐 Time: 9:00 AM - 5:00 PM       │  │
    │  │  📍 Venue: CS Block, Auditorium   │  │
    │  │                                   │  │
    │  │  About the Event                  │  │
    │  │  Join us for exciting workshops   │  │
    │  │  and competitions...              │  │
    │  │                                   │  │
    │  │  ┌─────────────────────────────┐  │  │
    │  │  │   📝 Register Now           │  │  │
    │  │  └─────────────────────────────┘  │  │
    │  └───────────────────────────────────┘  │
    │                                         │
    │      (No Bottom Nav on this screen)    │
    └─────────────────────────────────────────┘
```

## Navigation Patterns

### 1️⃣ Bottom Navigation Pattern
```
Home ←→ Notifications ←→ Profile
 ↑          ↑              ↑
 └──────────┴──────────────┘
    Always accessible
```

**Characteristics:**
- Visible on: Home, Notifications, Profile, Departments
- Hidden on: Event Details
- Single tap navigation
- State preservation

### 2️⃣ Drawer Navigation Pattern
```
☰ Menu
  ├─→ Home
  ├─→ Departments
  ├─→ Profile
  └─→ Logout
```

**Characteristics:**
- Accessible from any screen with hamburger icon
- Swipe from left edge
- Contains all main destinations
- Closes after selection

### 3️⃣ Button/Card Navigation Pattern
```
Departments Screen
  ├─ Computer Science Card ──→ Event Details (Computer Science)
  ├─ Electronics Card ──────→ Event Details (Electronics)
  ├─ Mechanical Card ────────→ Event Details (Mechanical)
  └─ Civil Card ─────────────→ Event Details (Civil)
```

**Characteristics:**
- Passes department name as parameter
- Creates specific routes
- Back button returns to Departments

## Back Stack Behavior

### Example 1: Simple Navigation
```
Start: Home
Tap Bottom Nav "Notifications"
Back Stack: [Home, Notifications]
Press Back → Home
Back Stack: [Home]
Press Back → Exit App
```

### Example 2: Complex Navigation
```
Start: Home
Use Drawer → Departments
Back Stack: [Home, Departments]

Tap "Computer Science" card → Event Details
Back Stack: [Home, Departments, Event Details]

Press Back → Departments
Back Stack: [Home, Departments]

Use Bottom Nav → Profile
Back Stack: [Home, Departments, Profile]

Press Back → Departments
Back Stack: [Home, Departments]

Press Back → Home
Back Stack: [Home]
```

### Example 3: State Preservation
```
Home → Notifications (via Bottom Nav)
Notifications → Departments (via Drawer)
Departments → Home (via Bottom Nav)

State of each screen is preserved:
- Notifications: Scroll position maintained
- Departments: Grid state maintained
```

## Navigation Methods in Code

### 1. Bottom Navigation Click
```kotlin
navController.navigate(item.route) {
    popUpTo("home") { saveState = true }  // Clear back stack to home
    launchSingleTop = true                // Don't create duplicates
    restoreState = true                   // Restore previous state
}
```

### 2. Drawer Item Click
```kotlin
navController.navigate(item.route) {
    popUpTo("home") { saveState = true }
    launchSingleTop = true
    restoreState = true
}
scope.launch { drawerState.close() }      // Close drawer
```

### 3. Button/Card Click with Parameters
```kotlin
navController.navigate(
    Screen.EventDetails.createRoute(departmentName)
)
// Creates route: "event_details/Computer Science"
```

### 4. Back Button
```kotlin
navController.navigateUp()
// Pops current screen from back stack
```

## Screen Visibility Matrix

| Screen          | Bottom Nav | Top Bar | Drawer | Back Button |
|----------------|------------|---------|--------|-------------|
| Home           | ✅         | ✅      | ✅     | Exit App    |
| Notifications  | ✅         | ✅      | ✅     | Previous    |
| Profile        | ✅         | ✅      | ✅     | Previous    |
| Departments    | ✅         | ✅      | ✅     | Previous    |
| Event Details  | ❌         | ✅      | ❌     | Departments |

## Key Implementation Details

### Conditional Bottom Navigation
```kotlin
val showBottomNav = when {
    currentRoute == null -> true
    currentRoute.startsWith("event_details") -> false
    else -> true
}
```

### Route Definition
```kotlin
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object EventDetails : Screen("event_details/{departmentName}") {
        fun createRoute(name: String) = "event_details/$name"
    }
}
```

### Parameter Extraction
```kotlin
composable(
    route = "event_details/{departmentName}",
    arguments = listOf(
        navArgument("departmentName") { type = NavType.StringType }
    )
) { backStackEntry ->
    val departmentName = backStackEntry.arguments?.getString("departmentName")
    EventDetailsScreen(departmentName = departmentName ?: "")
}
```

---

**This navigation architecture ensures:**
- ✅ Clear navigation paths
- ✅ Proper back stack management
- ✅ State preservation
- ✅ Intuitive user experience
- ✅ Follows Android best practices
