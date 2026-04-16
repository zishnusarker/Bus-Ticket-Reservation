<h1 align="center">🚌 Bus Ticket Reservation</h1>

<p align="center">
  <strong>An Android mobile application for bus ticket booking with Firebase backend, featuring OTP-based authentication, ticket purchasing, booking history, and cancellation management.</strong>
</p>

<p align="center">
  <a href="#"><img src="https://img.shields.io/badge/Android-21+-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android"></a>
  <a href="#"><img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"></a>
  <a href="#"><img src="https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black" alt="Firebase"></a>
  <a href="https://github.com/zishnusarker/Bus-Ticket-Reservation/blob/main/LICENSE"><img src="https://img.shields.io/badge/License-MIT-green?style=for-the-badge" alt="License"></a>
</p>

<p align="center">
  <a href="#-overview">Overview</a> •
  <a href="#-features">Features</a> •
  <a href="#-tech-stack">Tech Stack</a> •
  <a href="#-architecture">Architecture</a> •
  <a href="#-installation">Installation</a> •
  <a href="#-app-flow">App Flow</a> •
  <a href="#-project-structure">Structure</a>
</p>

---

## 📖 Overview

A full-featured **Android mobile application** for bus ticket reservation, built with Java and powered by Firebase backend services. The app provides a complete booking workflow   - from user registration with phone OTP verification, to ticket purchasing, viewing booking history, and canceling reservations.

This project demonstrates practical mobile development skills including Firebase integration, multi-step authentication flows, real-time database operations, Material Design UI, and navigation architecture.

---

## ✨ Features

### 🔐 Authentication
- Multi-step user registration (3-page signup flow)
- Phone number verification with OTP (PIN view input)
- International country code picker (CCP library)
- Secure login with Firebase Authentication
- Forgot password flow with multiple recovery options
- Set new password functionality

### 🎫 Ticket Management
- Browse available bus routes and schedules
- Purchase tickets with seat selection
- View detailed ticket information
- Booking history tracking
- Ticket cancellation with confirmation

### 📱 User Experience
- Splash screen with app branding
- Onboarding / Getting started screens with image slider
- Material Design UI with CardView layouts
- Smooth navigation between screens
- Intuitive main home dashboard

---

## 🛠 Tech Stack

| Category | Technology | Purpose |
|----------|-----------|---------|
| **Language** | Java | Core application logic |
| **Platform** | Android (API 21+) | Mobile platform |
| **Build System** | Gradle | Dependency management and build |
| **Backend** | Firebase | Cloud backend services |
| **Auth** | Firebase Authentication | Phone/OTP-based user authentication |
| **Database** | Firebase Realtime Database | User data and booking storage |
| **Storage** | Firebase Storage | File/image storage |
| **Analytics** | Firebase Analytics | Usage tracking and insights |
| **UI - Material** | Material Design Components | Modern Android UI components |
| **UI - CardView** | AndroidX CardView | Card-based layouts |
| **UI - Navigation** | AndroidX Navigation | Fragment-based navigation |
| **UI - CCP** | Country Code Picker (hbb20) | International phone number input |
| **UI - PIN** | PinView (chaos) | OTP input field |
| **UI - Slider** | Custom SliderAdapter | Onboarding image carousel |
| **Lifecycle** | AndroidX Lifecycle | Activity/fragment lifecycle management |
| **Testing** | JUnit + Espresso | Unit and UI testing |

---

## 🏗 Architecture

```
┌──────────────────────────────────────────────────────────────┐
│                      ANDROID APP                              │
├──────────────────────────────────────────────────────────────┤
│                                                               │
│  ┌─────────────┐  ┌──────────────┐  ┌─────────────────────┐  │
│  │   Splash     │→│  Getting      │→│  Login / SignUp      │  │
│  │   Screen     │  │  Started     │  │  Module              │  │
│  └─────────────┘  └──────────────┘  └──────────┬──────────┘  │
│                                                  │             │
│         ┌────────────────────────────────────────▼──────┐     │
│         │              Main Home Dashboard              │     │
│         ├──────────┬───────────┬───────────┬───────────┤     │
│         │ Purchase │  Ticket   │  History  │  Cancel   │     │
│         │ Activity │  Activity │  Activity │  Activity │     │
│         └──────────┴───────────┴───────────┴───────────┘     │
│                                                               │
├──────────────────────────────────────────────────────────────┤
│                     FIREBASE BACKEND                          │
│  ┌──────────┐  ┌───────────────┐  ┌─────────┐  ┌─────────┐  │
│  │   Auth   │  │   Realtime    │  │ Storage │  │Analytics│  │
│  │  (OTP)   │  │   Database    │  │         │  │         │  │
│  └──────────┘  └───────────────┘  └─────────┘  └─────────┘  │
└──────────────────────────────────────────────────────────────┘
```

---

## 🚀 Installation

### Prerequisites
- Android Studio (Arctic Fox or later recommended)
- JDK 8 or higher
- Android SDK (API 21+)
- Firebase project (for backend services)

### Setup

```bash
# Clone the repository
git clone https://github.com/zishnusarker/Bus-Ticket-Reservation.git
cd Bus-Ticket-Reservation/Bus\ ticket\ reservation\ master
```

1. **Open in Android Studio** - File → Open → select the `Bus ticket reservation master` folder
2. **Sync Gradle** - Android Studio will automatically sync dependencies
3. **Configure Firebase** - The `google-services.json` is included, but for your own deployment you should:
   - Create a new Firebase project at [console.firebase.google.com](https://console.firebase.google.com)
   - Enable Authentication (Phone provider)
   - Enable Realtime Database
   - Download and replace `app/google-services.json`
4. **Run the app** - Connect an Android device or emulator (API 21+) and click Run

### Pre-built APK

A pre-built debug APK is available for quick testing:
```
Bus ticket reservation master/app-debug.apk
```

---

## 📋 App Flow

### 1. Onboarding
```
Splash Screen → Getting Started (Image Slider) → Login/SignUp Selection
```

### 2. Registration (3-Step Signup)
```
SignUp Page 1 (Basic Info) → SignUp Page 2 (Details) → SignUp Page 3 (Phone)
→ OTP Verification → Registration Complete
```

### 3. Login
```
Login → Enter Credentials → Firebase Auth → Main Home
```

### 4. Forgot Password
```
Forgot Password → Select Recovery Method → Verify Identity → Set New Password
```

### 5. Booking Flow
```
Main Home → Purchase Activity → Select Route/Details
→ Purchase Confirmation → Ticket Generated
```

### 6. Manage Bookings
```
Main Home → History (View past bookings)
Main Home → Cancel Activity (Cancel a booking)
Main Home → Information (View ticket details)
```

---

## 📁 Project Structure

```
Bus-Ticket-Reservation/
├── README.md
├── LICENSE
├── .gitattributes
│
└── Bus ticket reservation master/        # Android project root
    ├── build.gradle                      # Project-level Gradle config
    ├── settings.gradle                   # Gradle settings
    ├── gradle.properties                 # Gradle properties
    ├── gradlew / gradlew.bat            # Gradle wrapper scripts
    ├── app-debug.apk                    # Pre-built debug APK
    │
    ├── gradle/wrapper/                  # Gradle wrapper
    │
    └── app/                             # Application module
        ├── build.gradle                 # App-level dependencies
        ├── google-services.json         # Firebase configuration
        │
        └── src/main/
            ├── AndroidManifest.xml      # App manifest
            ├── app_logo.png             # App logo asset
            │
            ├── java/.../project1/       # Java source code
            │   ├── Common/LoginSignUp/  # Authentication module
            │   │   ├── LoginSignUp.java       # Auth landing page
            │   │   ├── Login.java             # Login screen
            │   │   ├── Registration.java      # Registration handler
            │   │   ├── SignUp2ndPage.java      # Signup step 2
            │   │   ├── SignUp3rdPage.java      # Signup step 3
            │   │   ├── Verify_OTP.java        # OTP verification
            │   │   ├── ForgetPassword.java    # Forgot password
            │   │   ├── MakeSelectionForgetPass.java  # Recovery options
            │   │   └── SetNewPassword.java    # New password
            │   │
            │   ├── Database/            # Data layer
            │   │   └── userDatabaseHelperClass.java  # Firebase DB helper
            │   │
            │   ├── HelperClasses/       # UI helpers
            │   │   └── SliderAdapter.java     # Onboarding slider
            │   │
            │   └── commonForApp/        # Core app screens
            │       ├── Splash.java            # Splash screen
            │       ├── Getting_started.java   # Onboarding
            │       ├── Main_home.java         # Home dashboard
            │       ├── PurchaseActivity.java   # Buy tickets
            │       ├── PurchaseActivity_2nd.java # Confirm purchase
            │       ├── TicketActivity.java     # View ticket
            │       ├── HistoryActivity.java   # Booking history
            │       ├── CancelActivity.java    # Cancel booking
            │       └── InformationActivity.java # Ticket info
            │
            └── res/                     # Resources (layouts, drawables, etc.)
```

---

## 🔑 Key Technical Details

| Detail | Value |
|--------|-------|
| **Min SDK** | 21 (Android 5.0 Lollipop) |
| **Target SDK** | 28 (Android 9.0 Pie) |
| **Compile SDK** | 30 (Android 11) |
| **Application ID** | `aidooo.spydo.com.project1` |
| **Build Tools** | 29.0.3 |

---

## 🔮 Future Improvements

- Migrate to Kotlin from Java
- Update to latest AndroidX and Firebase SDK versions
- Implement MVVM architecture pattern
- Add real-time bus tracking with Google Maps API
- Implement online payment gateway (Razorpay/Stripe)
- Add push notifications for booking confirmations
- Implement seat selection UI with visual bus layout
- Add user profile management with photo upload
- Write comprehensive unit and UI tests
- Add dark mode support

---

## ⚠️ Note

> The `google-services.json` file included in this repository is for development purposes. For production deployment, you should create your own Firebase project and replace the configuration file.

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

<p align="center">
  Made with ❤️ for Android development education
</p>
