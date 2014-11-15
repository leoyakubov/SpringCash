package io.leonid.springcash.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by leonid on 06.11.14.
 */
@Entity
@Table(name = "log_records")
public class LogRecord extends BaseEntity {
    public static final String SUCCESS = "Success";
    public static final String ERROR = "Error";

    public static final String ADMIN_AREA = "Admin";
    public static final String USER_AREA = "User";
    public static final String DEMO_AREA = "Demo";

    public static final String CREATE_USER_ACTION = "Create user";
    public static final String UPDATE_USER_ACTION = "Update user";
    public static final String DELETE_USER_ACTION = "Delete user";
    public static final String UPDATE_USER_BY_ADMIN_ACTION = "Update user by admin";

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tstamp", updatable = false)
    private Date timestamp;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user", nullable = true)
    private User user;

    @Column(name = "action", nullable = false)
    @NotEmpty
    private String action;

    @Column(name = "status", nullable = false)
    @NotEmpty
    private String status;

    @Column(name = "description", nullable = false)
    @NotEmpty
    private String description;

    @Column(name = "area", nullable = false)
    @NotEmpty
    private String area;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogRecord)) return false;

        LogRecord logRecord = (LogRecord) o;

        if (timestamp != logRecord.timestamp) return false;
        if (action != null ? !action.equals(logRecord.action) : logRecord.action != null) return false;
        if (area != null ? !area.equals(logRecord.area) : logRecord.area != null) return false;
        if (description != null ? !description.equals(logRecord.description) : logRecord.description != null)
            return false;
        if (status != null ? !status.equals(logRecord.status) : logRecord.status != null) return false;
        if (user != null ? !user.equals(logRecord.user) : logRecord.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timestamp != null ? timestamp.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LogRecord{" +
                "timestamp=" + timestamp +
                ", user=" + user +
                ", action='" + action + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
