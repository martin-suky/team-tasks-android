package cz.suky.teamtodo.android.model;

import java.io.Serializable;

/**
 * Created by suky on 2.6.15.
 */
public class AbstractModel implements Serializable {
    private static final long serialVersionUID = 1;

    public static final String COLUMN_ID      = "id";
    public static final String COLUMN_VERSION = "version";

    private Long id;
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
