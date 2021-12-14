package ru.geekbrains.notes.observe;

import ru.geekbrains.notes.data.Notes;

public interface Observer {
    void updateCardData(Notes notes);
}
