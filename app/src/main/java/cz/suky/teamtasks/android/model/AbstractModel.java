package cz.suky.teamtasks.android.model;

import java.io.Serializable;

/**
 * Created by suky on 2.6.15.
 */
public class AbstractModel implements Serializable {
    private static final long serialVersionUID = 1;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_VERSION = "version";

    private Integer id;
    private Long version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractModel that = (AbstractModel) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
