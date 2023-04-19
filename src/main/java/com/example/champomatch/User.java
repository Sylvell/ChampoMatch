package com.example.champomatch;

public class User {
    private Integer id;

    private String fullName;

    private String email_id;

    private Boolean admin;

    public User(String fullName, String email_id, int admin) {
        this.fullName = fullName;
        this.email_id = email_id;
        switch (admin) {
            case 1:
                this.admin = true;
                break;
            case 0:
                this.admin = false;
                break;
        }
    }

    public User(int id,String fullName, String email_id, int admin) {
        this.id = id;
        this.fullName = fullName;
        this.email_id = email_id;
        switch (admin) {
            case 1:
                this.admin = true;
                break;
            case 0:
                this.admin = false;
                break;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public int getAdmin() {
        if (this.admin) {
            return 1;
        } else if (!(this.admin)) {
            return 0;
        }
        return 0;
    }

    public void setAdmin(int admin) {
        switch (admin) {
            case 1:
                this.admin = true;
                break;
            case 0:
                this.admin = false;
                break;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email_id='" + email_id + '\'' +
                ", admin=" + admin +
                '}';
    }
}
