package ua.com.javatraining.xmlMapping;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {

    private String name;
    private String nickname;
    private int age;

    private User user;

    private List<AuthorityEng> authoritiesEngs; //authorities;

    public Person(String name, String nickName, int age) {
        this.name = name;
        this.nickname = nickName;
        this.age = age;
    }

    public Person(String name, String nickName, int age, List<AuthorityEng> authoritiesEngs) {
        this.name = name;
        this.nickname = nickName;
        this.age = age;
//        this.authorities = authoritiesEngs;
        this.authoritiesEngs = authoritiesEngs;
    }

    /*public Person() {}

    public Person(String name, String nickname, int age) {
        super();
        this.name = name;
        this.nickname = nickname;
        this.age = age;
    }*/

}

