package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NoteRepository {
    public final static String fileName = "notes.json";

    public List<Note> notes = new ArrayList<>();

    public NoteRepository() {
        notes = readFromFile();
    }

    public List<Note> getNotes() {
        return notes;
    }

    public Note addNote(Note note) {
        int highestId = 0;
        for (Note a : notes) {
            if (a.id > highestId) {
                highestId = a.id;
            }
        }
        note.id = highestId + 1;
        notes.add(note);
        saveListToFile(notes);
        return note;
    }

    private List<Note> readFromFile() {
        Type REVIEW_TYPE = new TypeToken<List<Note>>() {
        }.getType();
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            saveListToFile(notes);
            return notes;
        }
        return gson.fromJson(reader, REVIEW_TYPE);
    }

    private void saveListToFile(List<Note> list) {
        try (Writer writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(list, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
