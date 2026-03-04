#!/usr/bin/env python3
"""Rewrites all XML layout files to ensure no UTF-8 BOM and valid XML."""
import os

base = r"D:\Sx Varun\Sx Code\MCA\3rd Tri\MAD\Aura\app\src\main"
fixed = 0

layouts = {
    "fragment_habit.xml": '''<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/aura_background">
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rvHabits"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="16dp"
        android:clipToPadding="false"
        android:paddingBottom="80dp"/>
    <TextView
        android:id="@+id/tvEmptyHabits"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="No habits yet! Tap + to start."
        android:textAlignment="center"
        android:gravity="center"
        android:textSize="16sp"
        android:textColor="@color/text_secondary"
        android:visibility="gone"
        android:padding="48dp"
        android:layout_gravity="center"/>
    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fabAddHabit"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="16dp"
        android:src="@android:drawable/ic_input_add"
        app:backgroundTint="@color/aura_primary"/>
</androidx.coordinatorlayout.widget.CoordinatorLayout>
''',

    "item_habit.xml": '''<?xml version="1.0" encoding="utf-8"?>
<com.google.android.material.card.MaterialCardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/cardHabit"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginBottom="8dp"
    app:cardCornerRadius="16dp"
    app:cardElevation="4dp"
    app:strokeWidth="2dp">
    <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
        android:orientation="vertical" android:padding="16dp">
        <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
            android:orientation="horizontal" android:gravity="center_vertical">
            <TextView android:id="@+id/tvHabitTitle" android:layout_width="0dp"
                android:layout_height="wrap_content" android:layout_weight="1"
                android:textSize="17sp" android:textStyle="bold" android:textColor="@color/text_primary"/>
            <TextView android:id="@+id/tvStreak" android:layout_width="wrap_content"
                android:layout_height="wrap_content" android:textSize="18sp"
                android:textColor="@color/streak_fire"/>
        </LinearLayout>
        <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
            android:orientation="horizontal" android:layout_marginTop="12dp" android:gravity="center_vertical">
            <TextView android:id="@+id/tvFrequency" android:layout_width="0dp"
                android:layout_height="wrap_content" android:layout_weight="1"
                android:textSize="12sp" android:textColor="@color/text_secondary"/>
            <com.google.android.material.button.MaterialButton android:id="@+id/btnComplete"
                android:layout_width="wrap_content" android:layout_height="36dp"
                android:text="Complete" android:textSize="12sp"
                app:backgroundTint="@color/aura_primary"
                style="@style/Widget.MaterialComponents.Button"/>
            <ImageButton android:id="@+id/btnDelete" android:layout_width="36dp"
                android:layout_height="36dp" android:src="@android:drawable/ic_menu_delete"
                android:background="?attr/selectableItemBackgroundBorderless"
                android:contentDescription="Delete" android:layout_marginStart="8dp"/>
        </LinearLayout>
    </LinearLayout>
</com.google.android.material.card.MaterialCardView>
''',

    "dialog_add_habit.xml": '''<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="24dp">
    <TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:text="New Habit" android:textSize="20sp" android:textStyle="bold"
        android:layout_marginBottom="16dp"/>
    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent" android:layout_height="wrap_content"
        android:layout_marginBottom="12dp"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">
        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/etHabitTitle" android:layout_width="match_parent"
            android:layout_height="wrap_content" android:hint="Habit name *"/>
    </com.google.android.material.textfield.TextInputLayout>
    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent" android:layout_height="wrap_content"
        android:layout_marginBottom="12dp"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">
        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/etHabitDesc" android:layout_width="match_parent"
            android:layout_height="wrap_content" android:hint="Description (optional)"/>
    </com.google.android.material.textfield.TextInputLayout>
    <TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:text="Frequency" android:textSize="14sp" android:layout_marginBottom="8dp"/>
    <RadioGroup android:id="@+id/rgFrequency" android:layout_width="wrap_content"
        android:layout_height="wrap_content" android:orientation="horizontal"
        android:layout_marginBottom="20dp">
        <RadioButton android:id="@+id/rbDaily" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:text="Daily" android:checked="true"/>
        <RadioButton android:id="@+id/rbWeekly" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:text="Weekly"
            android:layout_marginStart="24dp"/>
    </RadioGroup>
    <com.google.android.material.button.MaterialButton android:id="@+id/btnSaveHabit"
        android:layout_width="match_parent" android:layout_height="wrap_content"
        android:text="Save Habit" app:backgroundTint="@color/aura_primary"/>
</LinearLayout>
''',

    "fragment_pomodoro.xml": '''<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/aura_background">
    <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
        android:orientation="vertical" android:padding="24dp" android:gravity="center_horizontal">

        <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
            android:orientation="horizontal" android:layout_marginBottom="24dp">
            <com.google.android.material.button.MaterialButton android:id="@+id/btnFocus"
                android:layout_width="0dp" android:layout_weight="1" android:layout_height="wrap_content"
                android:text="Focus" android:textSize="12sp"
                style="@style/Widget.MaterialComponents.Button.OutlinedButton"/>
            <com.google.android.material.button.MaterialButton android:id="@+id/btnShortBreak"
                android:layout_width="0dp" android:layout_weight="1" android:layout_height="wrap_content"
                android:text="Short Break" android:textSize="12sp" android:layout_marginStart="4dp"
                style="@style/Widget.MaterialComponents.Button.OutlinedButton"/>
            <com.google.android.material.button.MaterialButton android:id="@+id/btnLongBreak"
                android:layout_width="0dp" android:layout_weight="1" android:layout_height="wrap_content"
                android:text="Long Break" android:textSize="12sp" android:layout_marginStart="4dp"
                style="@style/Widget.MaterialComponents.Button.OutlinedButton"/>
        </LinearLayout>

        <TextView android:id="@+id/tvMode" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:text="Focus"
            android:textSize="18sp" android:textColor="@color/aura_primary"
            android:textStyle="bold" android:layout_marginBottom="16dp"/>

        <com.google.android.material.progressindicator.CircularProgressIndicator
            android:id="@+id/progressTimer"
            android:layout_width="220dp"
            android:layout_height="220dp"
            android:layout_marginBottom="16dp"
            app:indicatorSize="220dp"
            app:trackThickness="12dp"
            app:indicatorColor="@color/aura_primary"
            app:trackColor="@color/aura_surface"
            app:progress="0"/>

        <TextView android:id="@+id/tvTimer" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:text="25:00"
            android:textSize="52sp" android:textStyle="bold" android:textColor="@color/text_primary"
            android:layout_marginTop="-160dp" android:layout_marginBottom="160dp"/>

        <LinearLayout android:layout_width="wrap_content" android:layout_height="wrap_content"
            android:orientation="horizontal" android:layout_marginBottom="16dp">
            <com.google.android.material.button.MaterialButton android:id="@+id/btnStartPause"
                android:layout_width="120dp" android:layout_height="48dp" android:text="Start"
                app:backgroundTint="@color/aura_primary"/>
            <com.google.android.material.button.MaterialButton android:id="@+id/btnReset"
                android:layout_width="wrap_content" android:layout_height="48dp"
                android:text="Reset" android:layout_marginStart="12dp"
                style="@style/Widget.MaterialComponents.Button.OutlinedButton"/>
        </LinearLayout>

        <TextView android:id="@+id/tvSessions" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:text="Sessions today: 0"
            android:textSize="14sp" android:textColor="@color/text_secondary"
            android:layout_marginBottom="4dp"/>
        <TextView android:id="@+id/tvFocusMinutes" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:text="Focus time: 0 min"
            android:textSize="14sp" android:textColor="@color/text_secondary"/>
    </LinearLayout>
</ScrollView>
''',

    "fragment_expense.xml": '''<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/aura_background">
    <LinearLayout android:layout_width="match_parent" android:layout_height="match_parent"
        android:orientation="vertical">
        <com.google.android.material.card.MaterialCardView android:layout_width="match_parent"
            android:layout_height="wrap_content" android:layout_margin="16dp"
            app:cardCornerRadius="16dp" app:cardElevation="4dp"
            app:cardBackgroundColor="@color/aura_primary">
            <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
                android:orientation="horizontal" android:padding="16dp">
                <LinearLayout android:layout_width="0dp" android:layout_height="wrap_content"
                    android:layout_weight="1" android:orientation="vertical" android:gravity="center">
                    <TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
                        android:text="Income" android:textColor="@color/white" android:textSize="12sp"/>
                    <TextView android:id="@+id/tvTotalIncome" android:layout_width="wrap_content"
                        android:layout_height="wrap_content" android:text="0.00"
                        android:textColor="@color/white" android:textSize="18sp" android:textStyle="bold"/>
                </LinearLayout>
                <LinearLayout android:layout_width="0dp" android:layout_height="wrap_content"
                    android:layout_weight="1" android:orientation="vertical" android:gravity="center">
                    <TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
                        android:text="Expenses" android:textColor="@color/white" android:textSize="12sp"/>
                    <TextView android:id="@+id/tvTotalExpense" android:layout_width="wrap_content"
                        android:layout_height="wrap_content" android:text="0.00"
                        android:textColor="@color/white" android:textSize="18sp" android:textStyle="bold"/>
                </LinearLayout>
                <LinearLayout android:layout_width="0dp" android:layout_height="wrap_content"
                    android:layout_weight="1" android:orientation="vertical" android:gravity="center">
                    <TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
                        android:text="Balance" android:textColor="@color/white" android:textSize="12sp"/>
                    <TextView android:id="@+id/tvBalance" android:layout_width="wrap_content"
                        android:layout_height="wrap_content" android:text="0.00"
                        android:textColor="@color/white" android:textSize="18sp" android:textStyle="bold"/>
                </LinearLayout>
            </LinearLayout>
        </com.google.android.material.card.MaterialCardView>
        <androidx.recyclerview.widget.RecyclerView android:id="@+id/rvExpenses"
            android:layout_width="match_parent" android:layout_height="0dp"
            android:layout_weight="1" android:padding="8dp"
            android:clipToPadding="false" android:paddingBottom="80dp"/>
    </LinearLayout>
    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fabAddExpense" android:layout_width="wrap_content"
        android:layout_height="wrap_content" android:layout_gravity="bottom|end"
        android:layout_margin="16dp" android:src="@android:drawable/ic_input_add"
        app:backgroundTint="@color/aura_primary"/>
</androidx.coordinatorlayout.widget.CoordinatorLayout>
''',

    "item_expense.xml": '''<?xml version="1.0" encoding="utf-8"?>
<com.google.android.material.card.MaterialCardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="4dp"
    app:cardCornerRadius="12dp"
    app:cardElevation="2dp">
    <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
        android:orientation="horizontal" android:padding="12dp" android:gravity="center_vertical">
        <LinearLayout android:layout_width="0dp" android:layout_height="wrap_content"
            android:layout_weight="1" android:orientation="vertical">
            <TextView android:id="@+id/tvExpenseTitle" android:layout_width="wrap_content"
                android:layout_height="wrap_content" android:textSize="15sp"
                android:textStyle="bold" android:textColor="@color/text_primary"/>
            <LinearLayout android:layout_width="wrap_content" android:layout_height="wrap_content"
                android:orientation="horizontal">
                <TextView android:id="@+id/tvExpenseCategory" android:layout_width="wrap_content"
                    android:layout_height="wrap_content" android:textSize="12sp"
                    android:textColor="@color/aura_primary" android:layout_marginEnd="8dp"/>
                <TextView android:id="@+id/tvExpenseDate" android:layout_width="wrap_content"
                    android:layout_height="wrap_content" android:textSize="12sp"
                    android:textColor="@color/text_secondary"/>
            </LinearLayout>
        </LinearLayout>
        <TextView android:id="@+id/tvExpenseAmount" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:textSize="16sp"
            android:textStyle="bold" android:layout_marginEnd="8dp"/>
        <ImageButton android:id="@+id/btnDeleteExpense" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:src="@android:drawable/ic_menu_delete"
            android:background="?attr/selectableItemBackgroundBorderless"
            android:contentDescription="Delete"/>
    </LinearLayout>
</com.google.android.material.card.MaterialCardView>
''',

    "dialog_add_expense.xml": '''<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="24dp">
    <TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:text="Add Transaction" android:textSize="20sp" android:textStyle="bold"
        android:layout_marginBottom="16dp"/>
    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent" android:layout_height="wrap_content"
        android:layout_marginBottom="12dp"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">
        <com.google.android.material.textfield.TextInputEditText android:id="@+id/etExpenseTitle"
            android:layout_width="match_parent" android:layout_height="wrap_content"
            android:hint="Title *"/>
    </com.google.android.material.textfield.TextInputLayout>
    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent" android:layout_height="wrap_content"
        android:layout_marginBottom="12dp"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">
        <com.google.android.material.textfield.TextInputEditText android:id="@+id/etExpenseAmount"
            android:layout_width="match_parent" android:layout_height="wrap_content"
            android:hint="Amount *" android:inputType="numberDecimal"/>
    </com.google.android.material.textfield.TextInputLayout>
    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent" android:layout_height="wrap_content"
        android:layout_marginBottom="12dp"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">
        <com.google.android.material.textfield.TextInputEditText android:id="@+id/etExpenseCategory"
            android:layout_width="match_parent" android:layout_height="wrap_content"
            android:hint="Category"/>
    </com.google.android.material.textfield.TextInputLayout>
    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent" android:layout_height="wrap_content"
        android:layout_marginBottom="12dp"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">
        <com.google.android.material.textfield.TextInputEditText android:id="@+id/etExpenseNote"
            android:layout_width="match_parent" android:layout_height="wrap_content"
            android:hint="Note (optional)"/>
    </com.google.android.material.textfield.TextInputLayout>
    <RadioGroup android:id="@+id/rgExpenseType" android:layout_width="wrap_content"
        android:layout_height="wrap_content" android:orientation="horizontal"
        android:layout_marginBottom="20dp">
        <RadioButton android:id="@+id/rbExpense" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:text="Expense" android:checked="true"/>
        <RadioButton android:id="@+id/rbIncome" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:text="Income"
            android:layout_marginStart="24dp"/>
    </RadioGroup>
    <com.google.android.material.button.MaterialButton android:id="@+id/btnSaveExpense"
        android:layout_width="match_parent" android:layout_height="wrap_content"
        android:text="Save" app:backgroundTint="@color/aura_primary"/>
</LinearLayout>
''',

    "fragment_budget.xml": '''<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/aura_background">
    <androidx.recyclerview.widget.RecyclerView android:id="@+id/rvBudgets"
        android:layout_width="match_parent" android:layout_height="match_parent"
        android:padding="16dp" android:clipToPadding="false" android:paddingBottom="80dp"/>
    <TextView android:id="@+id/tvEmptyBudget" android:layout_width="match_parent"
        android:layout_height="wrap_content" android:text="No budgets set! Tap + to add."
        android:textAlignment="center" android:gravity="center" android:textSize="16sp"
        android:textColor="@color/text_secondary" android:visibility="gone"
        android:padding="48dp" android:layout_gravity="center"/>
    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fabAddBudget" android:layout_width="wrap_content"
        android:layout_height="wrap_content" android:layout_gravity="bottom|end"
        android:layout_margin="16dp" android:src="@android:drawable/ic_input_add"
        app:backgroundTint="@color/aura_primary"/>
</androidx.coordinatorlayout.widget.CoordinatorLayout>
''',

    "item_budget.xml": '''<?xml version="1.0" encoding="utf-8"?>
<com.google.android.material.card.MaterialCardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginBottom="8dp"
    app:cardCornerRadius="12dp"
    app:cardElevation="2dp">
    <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
        android:orientation="vertical" android:padding="16dp">
        <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
            android:orientation="horizontal" android:gravity="center_vertical">
            <TextView android:id="@+id/tvBudgetCategory" android:layout_width="0dp"
                android:layout_height="wrap_content" android:layout_weight="1"
                android:textSize="16sp" android:textStyle="bold" android:textColor="@color/text_primary"/>
            <ImageButton android:id="@+id/btnDeleteBudget" android:layout_width="wrap_content"
                android:layout_height="wrap_content" android:src="@android:drawable/ic_menu_delete"
                android:background="?attr/selectableItemBackgroundBorderless"
                android:contentDescription="Delete"/>
        </LinearLayout>
        <TextView android:id="@+id/tvBudgetSpent" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:textSize="13sp"
            android:textColor="@color/text_secondary" android:layout_marginTop="4dp"
            android:layout_marginBottom="8dp"/>
        <com.google.android.material.progressindicator.LinearProgressIndicator
            android:id="@+id/progressBudget" android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:trackCornerRadius="4dp" app:trackThickness="8dp"/>
        <TextView android:id="@+id/tvBudgetRemaining" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:textSize="12sp"
            android:textColor="@color/text_secondary" android:layout_marginTop="4dp"/>
    </LinearLayout>
</com.google.android.material.card.MaterialCardView>
''',

    "dialog_add_budget.xml": '''<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="24dp">
    <TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:text="Set Budget" android:textSize="20sp" android:textStyle="bold"
        android:layout_marginBottom="16dp"/>
    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent" android:layout_height="wrap_content"
        android:layout_marginBottom="12dp"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">
        <com.google.android.material.textfield.TextInputEditText android:id="@+id/etBudgetCategory"
            android:layout_width="match_parent" android:layout_height="wrap_content"
            android:hint="Category *"/>
    </com.google.android.material.textfield.TextInputLayout>
    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent" android:layout_height="wrap_content"
        android:layout_marginBottom="20dp"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">
        <com.google.android.material.textfield.TextInputEditText android:id="@+id/etBudgetLimit"
            android:layout_width="match_parent" android:layout_height="wrap_content"
            android:hint="Monthly limit *" android:inputType="numberDecimal"/>
    </com.google.android.material.textfield.TextInputLayout>
    <com.google.android.material.button.MaterialButton android:id="@+id/btnSaveBudget"
        android:layout_width="match_parent" android:layout_height="wrap_content"
        android:text="Save Budget" app:backgroundTint="@color/aura_primary"/>
</LinearLayout>
''',

    "fragment_planner.xml": '''<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/aura_background">
    <LinearLayout android:layout_width="match_parent" android:layout_height="match_parent"
        android:orientation="vertical">
        <HorizontalScrollView android:layout_width="match_parent" android:layout_height="wrap_content"
            android:scrollbars="none" android:background="@color/white" android:elevation="4dp">
            <com.google.android.material.chip.ChipGroup android:id="@+id/chipGroupDates"
                android:layout_width="wrap_content" android:layout_height="wrap_content"
                android:padding="8dp"/>
        </HorizontalScrollView>
        <androidx.recyclerview.widget.RecyclerView android:id="@+id/rvPlannerEntries"
            android:layout_width="match_parent" android:layout_height="0dp"
            android:layout_weight="1" android:padding="16dp"
            android:clipToPadding="false" android:paddingBottom="80dp"/>
        <TextView android:id="@+id/tvEmptyPlanner" android:layout_width="match_parent"
            android:layout_height="wrap_content" android:text="Nothing planned! Tap + to add."
            android:textAlignment="center" android:gravity="center" android:textSize="16sp"
            android:textColor="@color/text_secondary" android:visibility="gone"
            android:padding="32dp"/>
    </LinearLayout>
    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fabAddEntry" android:layout_width="wrap_content"
        android:layout_height="wrap_content" android:layout_gravity="bottom|end"
        android:layout_margin="16dp" android:src="@android:drawable/ic_input_add"
        app:backgroundTint="@color/aura_primary"/>
</androidx.coordinatorlayout.widget.CoordinatorLayout>
''',

    "item_planner_entry.xml": '''<?xml version="1.0" encoding="utf-8"?>
<com.google.android.material.card.MaterialCardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginBottom="8dp"
    app:cardCornerRadius="12dp"
    app:cardElevation="2dp">
    <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
        android:orientation="horizontal" android:padding="12dp" android:gravity="center_vertical">
        <View android:id="@+id/viewColorBar" android:layout_width="6dp"
            android:layout_height="match_parent" android:layout_marginEnd="12dp"
            android:background="@color/aura_primary"/>
        <LinearLayout android:layout_width="0dp" android:layout_height="wrap_content"
            android:layout_weight="1" android:orientation="vertical">
            <TextView android:id="@+id/tvEntryTitle" android:layout_width="wrap_content"
                android:layout_height="wrap_content" android:textSize="15sp"
                android:textStyle="bold" android:textColor="@color/text_primary"/>
            <TextView android:id="@+id/tvEntryTime" android:layout_width="wrap_content"
                android:layout_height="wrap_content" android:textSize="12sp"
                android:textColor="@color/text_secondary" android:layout_marginTop="2dp"/>
        </LinearLayout>
        <CheckBox android:id="@+id/cbEntryComplete" android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>
        <ImageButton android:id="@+id/btnDeleteEntry" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:src="@android:drawable/ic_menu_delete"
            android:background="?attr/selectableItemBackgroundBorderless"
            android:contentDescription="Delete"/>
    </LinearLayout>
</com.google.android.material.card.MaterialCardView>
''',

    "dialog_add_planner_entry.xml": '''<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="24dp">
    <TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:text="Add Planner Entry" android:textSize="20sp" android:textStyle="bold"
        android:layout_marginBottom="16dp"/>
    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent" android:layout_height="wrap_content"
        android:layout_marginBottom="12dp"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">
        <com.google.android.material.textfield.TextInputEditText android:id="@+id/etEntryTitle"
            android:layout_width="match_parent" android:layout_height="wrap_content"
            android:hint="Title *"/>
    </com.google.android.material.textfield.TextInputLayout>
    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent" android:layout_height="wrap_content"
        android:layout_marginBottom="12dp"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">
        <com.google.android.material.textfield.TextInputEditText android:id="@+id/etEntryDesc"
            android:layout_width="match_parent" android:layout_height="wrap_content"
            android:hint="Notes (optional)"/>
    </com.google.android.material.textfield.TextInputLayout>
    <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
        android:orientation="horizontal" android:layout_marginBottom="20dp">
        <com.google.android.material.textfield.TextInputLayout android:layout_width="0dp"
            android:layout_height="wrap_content" android:layout_weight="1"
            android:layout_marginEnd="8dp"
            style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">
            <com.google.android.material.textfield.TextInputEditText android:id="@+id/etStartTime"
                android:layout_width="match_parent" android:layout_height="wrap_content"
                android:hint="Start (mins)" android:inputType="number"/>
        </com.google.android.material.textfield.TextInputLayout>
        <com.google.android.material.textfield.TextInputLayout android:layout_width="0dp"
            android:layout_height="wrap_content" android:layout_weight="1"
            style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">
            <com.google.android.material.textfield.TextInputEditText android:id="@+id/etEndTime"
                android:layout_width="match_parent" android:layout_height="wrap_content"
                android:hint="End (mins)" android:inputType="number"/>
        </com.google.android.material.textfield.TextInputLayout>
    </LinearLayout>
    <com.google.android.material.button.MaterialButton android:id="@+id/btnSaveEntry"
        android:layout_width="match_parent" android:layout_height="wrap_content"
        android:text="Save Entry" app:backgroundTint="@color/aura_primary"/>
</LinearLayout>
'''
}

layout_dir = os.path.join(base, "res", "layout")
for fname, content in layouts.items():
    path = os.path.join(layout_dir, fname)
    with open(path, 'w', encoding='utf-8', newline='\n') as f:
        f.write(content)
    fixed += 1
    print(f"Written: {fname}")

# Also strip BOM from any remaining XML files
for root_dir, dirs, files in os.walk(os.path.join(base, "res")):
    for fname in files:
        if fname.endswith('.xml'):
            p = os.path.join(root_dir, fname)
            d = open(p, 'rb').read()
            if d[:3] == b'\xef\xbb\xbf':
                open(p, 'wb').write(d[3:])
                print(f"BOM stripped: {fname}")

print(f"Done. Written {fixed} layout files.")

