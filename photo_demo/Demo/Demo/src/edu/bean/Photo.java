package edu.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Photo")
public class Photo {
    private int id;
    private String title;
    private String catalog;
    private String descs;
    private String filePath;
    private MultipartFile file;
    private int creator;

    @JsonFormat(pattern="yyyy-MM-dd HH:MM",timezone = "GMT+8")
    private Timestamp createDt;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String desc) {
        this.descs = desc;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Transient
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public Timestamp getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Timestamp createDt) {
        this.createDt = createDt;
    }
}
