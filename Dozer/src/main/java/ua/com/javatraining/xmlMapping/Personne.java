package ua.com.javatraining.xmlMapping;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Personne {

    private String nom;
    private String surnom;
    private int age;

    private User user;

    private List<AuthorityFranch> authoritiesFranches; //authorities;

    public Personne(String nom, String surNom, int age) {
        this.nom = nom;
        this.surnom = surNom;
        this.age = age;
    }

    public Personne(String nom, String surNom, int age, List<AuthorityFranch> authoritiesFranches) {
        this.nom = nom;
        this.surnom = surNom;
        this.age = age;
        this.authoritiesFranches = authoritiesFranches;
//        this.authorities = authoritiesFranches;
    }

    /*public Personne() {}

    public Personne(String nom, String surnom, int age) {
        super();
        this.nom = nom;
        this.surnom = surnom;
        this.age = age;
    }*/

}
