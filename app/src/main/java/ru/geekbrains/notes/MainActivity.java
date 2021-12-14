package ru.geekbrains.notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ru.geekbrains.socialnetwork.R;
import ru.geekbrains.notes.observe.Publisher;
import ru.geekbrains.notes.ui.NotesAppFragment;

public class MainActivity extends AppCompatActivity {

    private Navigation navigation;
    private Publisher publisher = new Publisher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = new Navigation(getSupportFragmentManager());
        initToolBar();
        getNavigation().addFragment(new NotesAppFragment(), false);
        addFragment(new NotesAppFragment());
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    public void onBackPressed() {
        showAlertDialogWithCustomView();
    }

    private void showAlertDialogWithCustomView() {
        final View customView = getLayoutInflater().inflate(R.layout.custom_alert_dialog, null);
        new AlertDialog.Builder(this)
                .setView(customView)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Приложение закрыто",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Отмена",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}