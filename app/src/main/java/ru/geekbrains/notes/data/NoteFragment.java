package ru.geekbrains.notes.data;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Date;

import ru.geekbrains.notes.MainActivity;
import ru.geekbrains.socialnetwork.R;
import ru.geekbrains.notes.observe.Publisher;

public class NoteFragment extends Fragment {
    private static final String ARG_CARD_DATA = "Param_CardData";
    private Notes notes;
    private Publisher publisher;
    private TextInputEditText title;
    private TextInputEditText description;
    private DatePicker datePicker;

    public static NoteFragment newInstance(Notes notes) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CARD_DATA, notes);
        fragment.setArguments(args);
        return fragment;
    }

    public static NoteFragment newInstance() {
        NoteFragment fragment = new NoteFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        initView(view);
        // если cardData пустая, то это добавление
        if (notes != null) {
            populateView();
        }
        return view;
    }

    private void initView(View view) {
        title = view.findViewById(R.id.note_title);
        description = view.findViewById(R.id.note_content);
        datePicker = view.findViewById(R.id.note_date);
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