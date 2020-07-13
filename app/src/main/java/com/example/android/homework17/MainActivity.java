package com.example.android.homework17;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Locale locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        final Spinner spinner = findViewById(R.id.spinner_language);
        final Spinner spinnerColors = findViewById(R.id.spinner_colors);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spinner.getSelectedItem().equals("Русский")) {
                    locale = new Locale("ru");
                } else locale = new Locale("eng");
                Configuration config = new Configuration();
                config.setLocale(locale);
                getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

                switch (spinnerColors.getSelectedItem().toString()) {
                    case "Зеленый":
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_GREEN);
                        break;
                    case "Синий":
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_BLUE);
                        break;
                    case "Красный":
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_RED);
                        break;
                }
            }
        });
    }
}

class Utils {
    private static int sTheme;

    public final static int THEME_GREEN = 0;
    public final static int THEME_RED = 1;
    public final static int THEME_BLUE = 2;

    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    /**
     * Set the theme of the activity, according to the configuration.
     */
    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            default:
            case THEME_GREEN:
                activity.setTheme(R.style.AppThemeGreen);
                break;
            case THEME_BLUE:
                activity.setTheme(R.style.AppThemeBlue);
                break;
            case THEME_RED:
                activity.setTheme(R.style.AppThemeRed);
                break;
        }
    }
}