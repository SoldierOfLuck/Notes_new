package ru.geekbrains.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.geekbrains.socialnetwork.R;


public class SettingsFragment extends Fragment {


    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
    // Этот метод вызывается, когда макет экрана создан и готов к отображению информации
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Восстановление текущей позиции
        Button buttonBack = view.findViewById(R.id.button_back_settings);
        buttonBack.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }


}