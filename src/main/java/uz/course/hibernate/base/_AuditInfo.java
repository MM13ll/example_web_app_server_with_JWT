package uz.course.hibernate.base;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import uz.course.hibernate.domain._User;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.Instant;

@Embeddable
public class _AuditInfo implements Serializable {

    @Column(name = "creation_date")
    private Instant creationDate = Instant.now();
    @Column(name = "updated_date")
    private Instant updatedDate;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name = "none")
    private _User createdByUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @ForeignKey(name = "none")
    private _User updatedByUser;

    public _AuditInfo(Instant creationDate, Instant updatedDate, _User createdByUser, _User updatedByUser) {
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
        this.createdByUser = createdByUser;
        this.updatedByUser = updatedByUser;
    }

    public _AuditInfo() {

    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }

    public _User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(_User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public _User getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(_User updatedByUser) {
        this.updatedByUser = updatedByUser;
    }
}
