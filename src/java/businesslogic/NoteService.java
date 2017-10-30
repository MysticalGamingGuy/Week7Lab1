package businesslogic;

import dataaccess.NoteDB;
import dataaccess.NotesDBException;
import domainmodel.Note;
import java.util.Date;
import java.util.List;

public class NoteService {

    private final NoteDB noteDB;

    public NoteService() {
        noteDB = new NoteDB();
    }

    public Note get(int noteid) throws NotesDBException {
        return noteDB.getNote(noteid);
    }

    public List<Note> getAll() throws NotesDBException {
        return noteDB.getAll();
    }
    public int delete(int noteid) throws NotesDBException {
        return noteDB.delete(noteDB.getNote(noteid));
    }
    
    public int update(String contents) throws NotesDBException {
        return noteDB.update(new Note(contents));
    }

    public int insert(String contents) throws NotesDBException {
        return noteDB.insert(new Note(contents));
    }
}
