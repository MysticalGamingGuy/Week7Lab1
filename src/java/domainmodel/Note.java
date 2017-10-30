package domainmodel;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {
    
    private int noteid;
    private Date datecreated;
    private String contents;

    public Note() {
    }

    public Note(int noteid, Date dateCreated, String contents) {
        this.noteid = noteid;
        this.datecreated = dateCreated;
        this.contents = contents;
    }
    
    public Note(String contents) {
        this.datecreated = new Date();
        this.contents = contents;
    }
    
    public Note(Date dateCreated, String contents) {
        this.datecreated = dateCreated;
        this.contents = contents;
    }
    
    public Note(int noteid, String contents) {
        this.noteid = noteid;
        this.datecreated = new Date();
        this.contents = contents;
    }

    public int getNoteid() {
        return noteid;
    }

    public void setNoteid(int noteid) {
        this.noteid = noteid;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date dateCreated) {
        this.datecreated = dateCreated;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
    
    
    
}
