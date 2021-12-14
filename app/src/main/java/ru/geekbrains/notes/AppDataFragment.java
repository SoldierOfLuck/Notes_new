package ru.geekbrains.notes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.geekbrains.socialnetwork.R;


public class AppDataFragment extends Fragment {

    public static AppDataFragment newInstance() {
        AppDataFragment fragment = new AppDataFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_app_data, container, false);
    }
    // Этот метод вызывается, когда макет экрана создан и готов к отображению информации
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Восстановление текущей позиции
        initList(view);
    }
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
            TextView tv = new TextView(getContext());
            tv.setText(R.string.app_data);
            tv.setTextSize(30);
            layoutView.addView(tv);
    }

}