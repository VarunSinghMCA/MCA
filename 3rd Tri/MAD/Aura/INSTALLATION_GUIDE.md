# 📱 Aura App - Installation & Usage Guide

## ✅ APK Successfully Built

Your Aura productivity app APK has been successfully created and is ready for installation!

### 📍 APK Location
```
D:\Sx Varun\Sx Code\MCA\3rd Tri\MAD\Aura\app\build\outputs\apk\debug\app-debug.apk
```

**File Name**: `app-debug.apk`  
**Application ID**: `com.example.aura`  
**Version**: 1.0 (Build 1)

---

## 🚀 Installation Methods

### Method 1: Direct APK Installation (Phone)
1. **Transfer APK to Phone**
   - Connect your Android phone to your computer via USB
   - Enable "File Transfer" mode on your phone
   - Copy `app-debug.apk` to your phone's Downloads folder

2. **Install on Phone**
   - Open Files/File Manager on your phone
   - Navigate to Downloads
   - Tap on `app-debug.apk`
   - Tap "Install"
   - Grant permissions when prompted
   - Open the app!

### Method 2: ADB Installation (Recommended for Developers)
```bash
# Connect your device via USB
# Navigate to the APK folder in Command Prompt/PowerShell

cd "D:\Sx Varun\Sx Code\MCA\3rd Tri\MAD\Aura\app\build\outputs\apk\debug"

# Install using ADB
adb install app-debug.apk

# Check installation status
adb shell pm list packages | grep aura
```

### Method 3: Android Studio Installation
1. Open Android Studio
2. Go to **File → Open**
3. Select the Aura project folder
4. Click the **Run** button (or press Shift+F10)
5. Select your target device
6. Wait for app to build and install

---

## 📋 System Requirements

### Minimum Requirements
- **Android Version**: 7.0 (API 24)
- **RAM**: 2GB minimum (4GB+ recommended)
- **Storage**: ~50MB free space
- **Internet**: Not required for basic functionality

### Device Compatibility
✓ Android Phones  
✓ Android Tablets  
✓ Android Emulator  
✓ Any device running Android 7.0+

---

## 🎯 Features Included in APK

### 📊 Dashboard
- Overview of all activities
- Quick access to all features
- Summary statistics

### 💰 Budget Management
- Set monthly budgets per category
- Track spending vs budget
- Visual progress indicators
- Alerts when near limit
- Over-budget warnings

### 💸 Expense Tracking
- Record expenses with categories
- Add dates and amounts
- View expense history
- Filter by category/date
- Edit and delete entries

### ✅ Todo/Task Management
- Create and manage tasks
- Set due dates
- Mark tasks complete
- Delete tasks
- Task reminders

### 🎯 Habit Tracking
- Create daily habits
- Log habit completion
- View habit streaks
- Daily reminders
- Habit statistics

### ⏲️ Pomodoro Timer
- 25-minute focus sessions
- Customizable work/break intervals
- Notifications when timer ends
- Session history

### 📅 Personal Planner
- Plan your day/week
- Add planner entries
- View calendar
- Organization tools

### ⚙️ Settings
- Notification preferences
- App preferences
- Data management
- Theme settings (if available)

---

## 🔐 Permissions Required

The app will request the following permissions on first launch:
- **Storage** - To save app data locally
- **Notifications** - For reminders and alerts
- **Calendar** (if planner feature is used)

**Note**: All data is stored locally on your device. No data is sent to external servers.

---

## 🛠️ Troubleshooting

### Installation Issues

**"App not installed" Error**
- Solution: Uninstall any previous version: `adb uninstall com.example.aura`
- Then reinstall the APK

**"Unknown sources" Error**
- Go to **Settings → Security → Unknown Sources**
- Enable "Allow installation of apps from unknown sources"
- Then try installing again

**ADB not recognized**
- Ensure Android SDK Platform Tools are installed
- Add ADB to your system PATH
- Restart Command Prompt/PowerShell

### Runtime Issues

**App crashes on startup**
- Ensure device has minimum 2GB RAM available
- Clear phone cache: Settings → Apps → Aura → Clear Cache
- Reinstall the app

**Notifications not working**
- Check Settings → Notifications
- Ensure app notifications are enabled
- Restart the device

---

## 📊 Build Information

```
App Name:           Aura - Productivity App
Package Name:       com.example.aura
Build Type:         Debug
Build Date:         March 10, 2026
Gradle Version:     8.13
Android Gradle:     Latest
Kotlin Version:     Latest (1.9.x)

Architecture:       Multi-architecture
Data Storage:       Local SQLite (Room Database)
DI Framework:       Hilt
State Management:   ViewModel + StateFlow/Flow
Navigation:         Jetpack Navigation
UI Framework:       Material Design
Database:           Room ORM
```

---

## 🔄 Build Commands Reference

```bash
# Navigate to project directory
cd "D:\Sx Varun\Sx Code\MCA\3rd Tri\MAD\Aura"

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run tests
./gradlew test

# Clean build
./gradlew clean

# Build and run on connected device
./gradlew installDebug
```

---

## 📝 Notes

- This is a **Debug APK** - use for testing and development
- Debug APK includes debugging symbols (larger file size)
- For production release, generate a signed release APK
- All user data is stored locally in SQLite database
- No internet connection required for app functionality
- App complies with Material Design guidelines

---

## 🎉 You're All Set!

Your Aura app is ready to use. Install it on your Android device and start being productive!

For questions or issues, refer to the codebase documentation or rebuild using Android Studio.

**Happy Productivity! 🚀**

