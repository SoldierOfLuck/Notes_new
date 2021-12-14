package ru.geekbrains.notes.data;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ru.geekbrains.socialnetwork.R;

public class NotesDataImpl implements NotesSource {

    private List<Notes> dataSource;
    private Resources resource;

    public NotesDataImpl(Resources resource){
        dataSource = new ArrayList<>(7);
        this.resource = resource;
    }

    public NotesSource init(){
        String[] titles = resource.getStringArray(R.array.titles);
        String[] descriptions = resource.getStringArray(R.array.descriptions);
        int[] pictures = getImageArray();
        int length = descriptions.length;
        for (int i=0; i < length; i++){
            dataSource.add(new Notes(titles[i], descriptions[i], false, Calendar.getInstance().getTime()));
        }
        return this;
    }

    private int[] getImageArray() {
        TypedArray pictures = resource.obtainTypedArray(R.array.pictures);
        int length = pictures.length();
        int[] answer = new int[length];
        for (int i=0; i < length; i++){
            answer[i] = pictures.getResourceId(i, 0);
        }
        pictures.recycle();
        return answer;
    }

    @Override
    public Notes getCardData(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateCardData(int position, Notes notes) {
        dataSource.set(position, notes);
    }

    @Override
    public void addCardData(Notes notes) {
        dataSource.add(notes);
    }

    @Override
    public void clearCardData() {
        dataSource.clear();
    }
}
