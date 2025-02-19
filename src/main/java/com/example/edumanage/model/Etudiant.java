package com.example.edumanage.model;

public class Etudiant {

    private int id;
    private String family_name, first_name, email, birth_date;

    public Etudiant(int id, String family_name, String first_name, String email, String birth_date) {
        this.id = id;
        this.family_name = family_name;
        this.first_name = first_name;
        this.email = email;
        this.birth_date = birth_date;
    }

    public Etudiant(String family_name, String first_name, String email, String birth_date) {
        this.family_name = family_name;
        this.first_name = first_name;
        this.email = email;
        this.birth_date = birth_date;
    }

    public Etudiant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }
}
