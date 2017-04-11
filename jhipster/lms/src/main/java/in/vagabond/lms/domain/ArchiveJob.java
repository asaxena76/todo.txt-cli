package in.vagabond.lms.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import in.vagabond.lms.domain.enumeration.ArchiveJobStatus;

/**
 * A ArchiveJob.
 */
@Entity
@Table(name = "archive_job")
public class ArchiveJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "start_time")
    private ZonedDateTime startTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ArchiveJobStatus status;

    @Column(name = "end_time")
    private ZonedDateTime endTime;

    @Column(name = "dummy")
    private String dummy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public ArchiveJob startTime(ZonedDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ArchiveJobStatus getStatus() {
        return status;
    }

    public ArchiveJob status(ArchiveJobStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(ArchiveJobStatus status) {
        this.status = status;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public ArchiveJob endTime(ZonedDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public String getDummy() {
        return dummy;
    }

    public ArchiveJob dummy(String dummy) {
        this.dummy = dummy;
        return this;
    }

    public void setDummy(String dummy) {
        this.dummy = dummy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArchiveJob archiveJob = (ArchiveJob) o;
        if (archiveJob.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, archiveJob.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ArchiveJob{" +
            "id=" + id +
            ", startTime='" + startTime + "'" +
            ", status='" + status + "'" +
            ", endTime='" + endTime + "'" +
            ", dummy='" + dummy + "'" +
            '}';
    }
}
