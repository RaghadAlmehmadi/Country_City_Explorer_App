# Country City Explorer App

**Country City Explorer App** is an Android application that allows users to explore countries, states, and cities using data fetched from a REST API. The app follows **MVI architecture** with **Clean Architecture principles** and leverages **Jetpack Compose** for a modern, declarative UI.

---

##  Project Overview

This application provides a structured and seamless way to navigate through different geographical locations:

- Displays a list of **countries** retrieved from an API.
- Allows users to **select a country** to view its states.
- Clicking on a **state** fetches and displays its cities.
- Implements **StateFlow** for managing UI states efficiently.
- Supports **dark mode** via the MaterialTheme API.

---

## Setup Instructions

### Prerequisites

- **Android Studio** 
- **Minimum SDK:** 
- **Target SDK:** 
- **Internet Connection**

### Features Implemented
- **Country List Screen** – Displays a list of countries from an API.
- **State List Screen** – Fetches and displays states for a selected country.
- **City List Screen** – Fetches and displays cities for a selected state.
- **Navigation Component** – Handles smooth screen transitions.
- **MVI State Management** – Uses StateFlow for UI state handling.
- **Loading/Error Handling** – Displays loading indicators and error messages.
- **MaterialTheme API** – Supports both light and dark modes.

  -------

  ### Technologies Used
- **Kotlin** – Primary programming language.  
- **Jetpack Compose** – UI framework for modern Android apps.  
- **StateFlow** – Reactive state management.  
- **Retrofit** – REST API calls.  
- **Material Theme API** – Custom theming and dark mode support.  
- **Navigation Component** – Manages in-app navigation.  


## Screen Country List
<img width="1440" alt="country" src="https://github.com/user-attachments/assets/90db4722-7ccd-42f5-848d-b9acd144994a" />

## Screen State List
<img width="1440" alt="city" src="https://github.com/user-attachments/assets/cf58f0c0-5c66-43d6-95eb-79364728a20b" />

## Screen City List
<img width="1440" alt="city" src="https://github.com/user-attachments/assets/fcc1e5e1-bb78-44e8-acf9-fca5e8b851f9" />


