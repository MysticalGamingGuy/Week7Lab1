package dataaccess;

import domainmodel.Note;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoteDB {

    public int insert(Note note) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO note(datecreated,contents) VALUES (SYSDATE(), ?)");
            ps.setString(1, note.getContents());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot insert " + note.toString(), ex);
            throw new NotesDBException("Error inserting Note");
        } finally {
            pool.freeConnection(connection);
        }
    }

    public int update(Note note) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE note SET "
                    + "contents = ?"
                    + "WHERE noteid = ?");
            ps.setString(1, note.getContents());
            ps.setInt(2, note.getNoteid());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot update " + note.toString(), ex);
            throw new NotesDBException("Error updating Note");
        } finally {
            pool.freeConnection(connection);
        }
    }

    public List<Note> getAll() throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM note;");
            ResultSet rs = ps.executeQuery();
            List<Note> notes = new ArrayList<>();
            while (rs.next()) {
                notes.add(new Note(rs.getInt("noteid"), rs.getDate("datecreated"), rs.getString("contents")));
            }
            return notes;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read Notes", ex);
            throw new NotesDBException("Error getting Notes");
        } finally {
            pool.freeConnection(connection);
        }
    }

    public Note getNote(int noteid) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM note WHERE noteid = ?");
            ps.setInt(1, noteid);
            ResultSet rs = ps.executeQuery();
            Note note = null;
            while (rs.next()) {
                note = new Note(rs.getInt("noteid"), rs.getDate("datecreated"), rs.getString("contents"));
            }
            return note;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read note", ex);
            throw new NotesDBException("Error getting Notes");
        } finally {
            pool.freeConnection(connection);
        }
    }

    public int delete(Note note) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM note WHERE noteid = ?");
            ps.setInt(1, note.getNoteid());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot delete " + note.toString(), ex);
            throw new NotesDBException("Error deleting Note");
        } finally {
            pool.freeConnection(connection);
        }
    }
}
