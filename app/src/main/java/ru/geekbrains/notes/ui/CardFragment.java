package ru.geekbrains.notes.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Date;

import ru.geekbrains.notes.MainActivity;
import ru.geekbrains.socialnetwork.R;
import ru.geekbrains.notes.data.Notes;
import ru.geekbrains.notes.observe.Publisher;

public class CardFragment extends Fragment {
    private static final String ARG_CARD_DATA = "Param_CardData";

    private Notes notes;
    private Publisher publisher;

    private TextInputEditText title;
    private TextInputEditText description;
    private DatePicker datePicker;

    public static CardFragment newInstance(Notes notes) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CARD_DATA, notes);
        fragment.setArguments(args);
        return fragment;
    }

    public static CardFragment newInstance() {
        CardFragment fragment = new CardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            notes = getArguments().getParcelable(ARG_CARD_DATA);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity)context;
        publisher = activity.getPublisher();
    }

    @Override
    public void onDetach() {
        publisher = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        initView(view);
        // если cardData пустая, то это добавление
        if (notes != null) {
            populateView();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Восстановление текущей позиции
        Button button_complete = view.findViewById(R.id.complete_note);
        button_complete.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }

    private void initView(View view) {
        title = view.findViewById(R.id.inputTitle);
        description = view.findViewById(R.id.inputDescription);
        datePicker = view.findViewById(R.id.inputDate);
    }

    private void populateView(){
        title.setText(notes.getTitle());
        description.setText(notes.getDescription());
        initDatePicker(notes.getDate());
    }

    private void initDatePicker(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                null);
    }

    @Override
    public void onStop() {
        super.onStop();
        notes = collectCardData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        publisher.notifySingle(notes);
    }

    private Notes collectCardData(){
        String title = this.title.getText().toString();
        String description = this.description.getText().toString();
        Date date = getDateFromDatePicker();
        boolean like;
        if (notes != null){
            like = notes.isLike();
        } else {
            like = false;
        }
        return new Notes(title, description, like, date);
    }

    private Date getDateFromDatePicker() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, this.datePicker.getYear());
        cal.set(Calendar.MONTH, this.datePicker.getMonth());
        cal.set(Calendar.DAY_OF_MONTH, this.datePicker.getDayOfMonth());
        return cal.getTime();
    }

}
