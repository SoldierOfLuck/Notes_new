package ru.geekbrains.notes.data;

public interface NotesSource {
    Notes getCardData(int position);
    int size();
    void deleteCardData(int position);
    void updateCardData(int position, Notes notes);
    void addCardData(Notes notes);
    void clearCardData();
}
