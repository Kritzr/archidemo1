# 🏗️ Android Architecture Patterns Demo

### MVC vs MVP vs MVVM vs MVI (Jetpack Compose)

This project demonstrates the implementation of **four major Android architecture patterns** using a single feature:

> 🧮 **Counter App** — Increment, Decrement, Reset

Instead of explaining theory in isolation, this repo shows how the **same logic behaves under different architectures**.

---

## 🚀 What This Project Covers

* MVC (Model–View–Controller)
* MVP (Model–View–Presenter)
* MVVM (Model–View–ViewModel)
* MVI (Model–View–Intent)

Each pattern is implemented in isolation with:

* Identical UI
* Same business logic
* Different architectural approach

---

## 📱 App Structure

```
app/
 ├── mvc/
 │    └── MvcCounterScreen.kt
 │
 ├── mvp/
 │    ├── MvpCounterScreen.kt
 │    ├── CounterPresenter.kt
 │    └── ICounterView.kt
 │
 ├── mvvm/
 │    ├── MvvmCounterScreen.kt
 │    ├── CounterViewModel.kt
 │    └── CounterUiState.kt
 │
 ├── mvi/
 │    ├── MviCounterScreen.kt
 │    ├── CounterViewModel.kt
 │    ├── CounterState.kt
 │    └── CounterIntent.kt
 │
 ├── shared/
 │    └── CounterModel.kt
 │
 └── MainActivity.kt
```

---

## 🧠 Core Idea

All four patterns use the same base model:

```kotlin
data class CounterModel(val count: Int = 0) {
    fun increment() = copy(count = count + 1)
    fun decrement() = copy(count = count - 1)
    fun reset()     = copy(count = 0)
}
```

The only difference is **how state and logic flow through the app**.

---

## 🔁 Navigation

The app uses a **bottom navigation bar** to switch between patterns:

* MVC
* MVP
* MVVM
* MVI

This allows direct comparison of:

* Code structure
* State handling
* Separation of concerns

---

## 👑 Architecture Breakdown

---

### 🟡 MVC — Everything in One Place

📁 `mvc/MvcCounterScreen.kt`

* Composable acts as **Controller**
* Directly mutates `CounterModel`
* UI updates immediately

```kotlin
var model by remember { mutableStateOf(CounterModel()) }
Button(onClick = { model = model.increment() })
```

**Key Characteristics**

* No separation of concerns
* Fast to build
* Hard to scale

---

### 🔵 MVP — Introduces Presenter

📁 `mvp/`

* `CounterPresenter.kt` → handles logic
* `ICounterView.kt` → contract
* View communicates via interface

```kotlin
presenter.onIncrement()
```

**Key Characteristics**

* Clear separation
* Testable Presenter
* Boilerplate-heavy

---

### 🟢 MVVM — State-Driven UI

📁 `mvvm/`

* `ViewModel` exposes `StateFlow`
* UI observes state changes
* No direct UI manipulation

```kotlin
val state by vm.uiState.collectAsStateWithLifecycle()
```

**Key Characteristics**

* Lifecycle-aware
* Reactive UI
* Recommended for modern Android

---

### 🔴 MVI — Unidirectional Data Flow

📁 `mvi/`

* `Intent` → user actions
* `State` → single source of truth
* `Reducer` → pure function

```kotlin
vm.process(CounterIntent.Increment)
```

**Key Characteristics**

* Predictable state
* Strict architecture
* Best for complex apps

---

## ⚖️ Comparison Summary

| Pattern | Logic Location      | State Flow           | Complexity | Best For        |
| ------- | ------------------- | -------------------- | ---------- | --------------- |
| MVC     | UI (Composable)     | Direct               | Low        | Small apps      |
| MVP     | Presenter           | Callback-based       | Medium     | Testable logic  |
| MVVM    | ViewModel           | Reactive (StateFlow) | Medium     | Production apps |
| MVI     | ViewModel + Reducer | Unidirectional       | High       | Complex state   |

---

## 💡 Key Learnings

* MVC is simple but doesn’t scale
* MVP improves structure but adds boilerplate
* MVVM provides the best balance for most apps
* MVI enforces discipline and predictability

> The biggest takeaway:
> **Architecture is about managing complexity, not just organizing code.**

---

## 🛠️ Tech Stack

* Kotlin
* Jetpack Compose
* StateFlow / Coroutines
* Material 3

---

## ▶️ How to Run

1. Clone the repo
2. Open in Android Studio
3. Run on emulator/device
4. Use bottom navigation to switch patterns

---

## 📌 Why This Project?

This project was built to:

* Move beyond theory
* Understand architecture through implementation
* Compare patterns side-by-side

---

## ✍️ Related Article

If you want the full explanation + learning journey:

👉 *(Add your Medium article link here)*

---

## 🤝 Contributions

Feel free to:

* Suggest improvements
* Add more patterns (Clean Architecture, Redux, etc.)
* Extend with real APIs

---

## ⭐ Final Thought

> Don’t just read architecture patterns.
> Build the same feature in all of them.

That’s where real understanding happens.
