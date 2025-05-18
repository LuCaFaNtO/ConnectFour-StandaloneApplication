# 🟡🔴 Connect Four - Standalone Software

## 🧩 Description

This project is a complete implementation of the **Connect Four** game, developed in **JavaFX** as a standalone desktop application.  
The goal was to explore key software engineering concepts such as versioning, modular design, requirements management, and internationalization, while delivering a comprehensive and engaging user experience.

Developed as part of the **Software Engineering and Development I** module at SUPSI (2023–2024).

## 🧱 Software Architecture

The application follows a well-structured architectural approach that separates the frontend and backend responsibilities:

### 🔧 Backend

Implements a **Layered Architecture** composed of:

- **Application Layer**: manages orchestration and delegation
- **Business Layer**: contains the core game logic and validation
- **Data Access Layer**: handles file operations (save/load in JSON format)

This separation promotes modularity, scalability, and maintainability.

### 🎨 Frontend

Developed using **JavaFX** and structured according to the **Model-View-Controller (MVC)** pattern, featuring:

- **Model**: represents the game state and logic
- **View**: the user interface components (grid, controls, info bar)
- **Controller**: manages user interactions and dispatches actions

A dedicated **Dispatcher** component ensures communication between layers and decouples the controller logic.  
The **Observer Pattern** is used to propagate changes between model and view components.

Additional architectural highlights:

- **Error handling** is performed via backend exceptions, propagated and handled by the frontend
- **Preferences and languages** are updated in real time during gameplay
- **Loose coupling** is encouraged for future extensibility

## 🎮 Main Features

- 🟦 Classic 6x7 Connect Four grid with interactive buttons
- 🖱️ Click-based token placement per column
- 💬 Contextual feedback guiding player actions
- 💾 Save/load game state using JSON files
- 🎨 Customizable player symbols and colors via UI
- 🌐 Multilingual interface with real-time language switching
- ℹ️ View app metadata (version, build date, credits)
- 💻 Fully standalone GUI-based desktop app

## 🛠️ Technologies Used

- **Language:** Java
- **GUI Framework:** JavaFX
- **Build Tool:** Maven
- **Architecture:** Layered Backend + MVC Frontend
- **Patterns:** Observer, Dispatcher
- **Persistence:** JSON file I/O
- **Internationalization:** Java `ResourceBundle`

## 🚀 How to Run the Project

> ⚠️ Make sure Java (JDK 17 or higher) is installed.

1. Clone the repository:
   ```bash
   git clone https://github.com/antoniomarroffino/ConnectFour-StandaloneApplication.git
   ```
2. Navigate into the project folder:
   ```bash
   cd ConnectFour-StandaloneApplication
   ```
3. Build and run the application:
   ```bash
   mvn javafx:run
   ```

Alternatively, you can run the `.jar` file provided in the `build/` folder.

## 📦 Requirements

- Java Development Kit (JDK) 17+
- JavaFX SDK (bundled or included as Maven dependency)
- OS Compatibility: Windows, macOS, Linux

## 📚 Educational Goals

This project aimed to apply software engineering principles, including:

- 📁 Configuration and version management (Git + Maven)
- 🧾 Requirements elicitation and validation
- 🧱 Modular software design (MVC, OOP, layered)
- 🌍 Internationalization (i18n)
- 👤 User-centric interaction design

⚠️ A critical reflection: although the **Observer Pattern** was implemented, its structure does not fully respect the **SOLID principles**, particularly the **Open-Closed Principle** (OCP). This limitation impacts long-term maintainability and extensibility.

## ✅ Project Status

✔️ Completed in June 2024  
🔒 No further maintenance planned

## 👤 Author

**Antonio Marroffino**
- GitHub: [github.com/antoniomarroffino](https://github.com/antoniomarroffino)
- LinkedIn: [linkedin.com/in/antonio-marroffino](https://www.linkedin.com/in/antoniomarroffino)

**Luca Fantò**
- GitHub: [github.com/lucafanto](https://github.com/LuCaFaNtO)
- LinkedIn: [linkedin.com/in/lucafanto](https://www.linkedin.com/in/luca-fant%C3%B2-14197232a/)

---

## 📜 License

This project was developed for educational purposes as part of the Bachelor's degree in Computer Engineering at SUPSI.
