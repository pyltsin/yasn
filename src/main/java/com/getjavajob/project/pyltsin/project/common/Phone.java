package com.getjavajob.project.pyltsin.project.common;

import javax.persistence.*;

/**
 * Created by Pyltsin on 24.12.2016. Algo8
 */
@Entity
@Table(name = "TELEPHONES")
public class Phone {

    @Column(name = "TELEPHONE")
    private String telephone;
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private Type type;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TELEPHONE", unique = true, nullable = false)
    private int id;

    public Phone(String tel, String type) {
        if (tel != null) {
            telephone = tel;
        } else {
            telephone = "";
        }

        if (type != null && Type.contain(type)) {
            this.type = Type.valueOf(type);
        } else {
            this.type = Type.UNDEFINED;
        }
    }

    public Phone() {
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String phone) {
        this.telephone = phone;
    }

    public Type getType() {
        return type;
    }

    public void setType(String type) {
        if (type != null && Type.contain(type)) {
            this.type = Type.valueOf(type);
        } else {
            this.type = Type.UNDEFINED;
        }
    }

    public String getTypeRus() {
        return type.getName();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public enum Type {
        UNDEFINED("UNDEFINED"), WORK("WORK"), HOME("HOME"), MOBILE("MOBILE");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public static boolean contain(String type) {
            for (int i = 0; i < Type.values().length; i++) {
                Type type1 = Type.values()[i];
                if (type1.name().equals(type)) {
                    return true;
                }
            }
            return false;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
