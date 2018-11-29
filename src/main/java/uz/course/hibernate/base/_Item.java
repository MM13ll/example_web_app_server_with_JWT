package uz.course.hibernate.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import uz.course.localization.GlobalizationExtentions;
import uz.course.to.SelectItem;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by Virus on 31-Aug-16.
 */
@MappedSuperclass
public class _Item extends _Entity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String name_ru;
    @Column(nullable = false)
    private String name_uzl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_ru() {
        return name_ru;
    }

    public void setName_ru(String name_ru) {
        this.name_ru = name_ru;
    }

    public String getName_uzl() {
        return name_uzl;
    }

    public void setName_uzl(String name_uzl) {
        this.name_uzl = name_uzl;
    }

    @Override
    public String toString() {
        return GlobalizationExtentions.getName(this);
    }

    @JsonIgnore
    public SelectItem getSelectItem() {
        return new SelectItem(getId(), name, "" + getId());
    }
}