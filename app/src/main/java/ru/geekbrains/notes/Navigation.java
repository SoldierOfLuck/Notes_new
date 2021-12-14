package ru.geekbrains.notes;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ru.geekbrains.socialnetwork.R;

public class Navigation {

    private final FragmentManager fragmentManager;

    public Navigation(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    public void addFragment(Fragment fragment, boolean useBackstack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        if (useBackstack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
