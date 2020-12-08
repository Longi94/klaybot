package in.dragonbra.klayb0t.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "slap_user")
public class SlapUser {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "exclude", nullable = false)
    private boolean exclude = false;

    public SlapUser() {
    }

    public SlapUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExclude() {
        return exclude;
    }

    public void setExclude(boolean exclude) {
        this.exclude = exclude;
    }
}
