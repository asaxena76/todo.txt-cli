package in.vagabond.lms.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import in.vagabond.lms.domain.enumeration.FileType;

/**
 * A LocalFile.
 */
@Entity
@Table(name = "local_file")
public class LocalFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "local_path")
    private String localPath;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type")
    private FileType type;

    @Column(name = "jhi_size")
    private Integer size;

    @Column(name = "date_added")
    private LocalDate dateAdded;

    @Column(name = "rejected")
    private Boolean rejected;

    @Column(name = "uploaded")
    private Boolean uploaded;

    @Column(name = "duplicate")
    private Boolean duplicate;

    @Column(name = "jhi_hash")
    private String hash;

    @Column(name = "resource_url")
    private String resourceUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalPath() {
        return localPath;
    }

    public LocalFile localPath(String localPath) {
        this.localPath = localPath;
        return this;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public FileType getType() {
        return type;
    }

    public LocalFile type(FileType type) {
        this.type = type;
        return this;
    }

    public void setType(FileType type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public LocalFile size(Integer size) {
        this.size = size;
        return this;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public LocalFile dateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Boolean isRejected() {
        return rejected;
    }

    public LocalFile rejected(Boolean rejected) {
        this.rejected = rejected;
        return this;
    }

    public void setRejected(Boolean rejected) {
        this.rejected = rejected;
    }

    public Boolean isUploaded() {
        return uploaded;
    }

    public LocalFile uploaded(Boolean uploaded) {
        this.uploaded = uploaded;
        return this;
    }

    public void setUploaded(Boolean uploaded) {
        this.uploaded = uploaded;
    }

    public Boolean isDuplicate() {
        return duplicate;
    }

    public LocalFile duplicate(Boolean duplicate) {
        this.duplicate = duplicate;
        return this;
    }

    public void setDuplicate(Boolean duplicate) {
        this.duplicate = duplicate;
    }

    public String getHash() {
        return hash;
    }

    public LocalFile hash(String hash) {
        this.hash = hash;
        return this;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public LocalFile resourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
        return this;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocalFile localFile = (LocalFile) o;
        if (localFile.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, localFile.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LocalFile{" +
            "id=" + id +
            ", localPath='" + localPath + "'" +
            ", type='" + type + "'" +
            ", size='" + size + "'" +
            ", dateAdded='" + dateAdded + "'" +
            ", rejected='" + rejected + "'" +
            ", uploaded='" + uploaded + "'" +
            ", duplicate='" + duplicate + "'" +
            ", hash='" + hash + "'" +
            ", resourceUrl='" + resourceUrl + "'" +
            '}';
    }
}
