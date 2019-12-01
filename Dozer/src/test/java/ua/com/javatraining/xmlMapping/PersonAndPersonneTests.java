package ua.com.javatraining.xmlMapping;

import org.dozer.DozerBeanMapper;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonAndPersonneTests {

    @Test
    public void mapPersonToPersonne() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozer_mapping.xml"));

        Person person = new Person("customName", "customNickName", 3);
        Personne personne = new Personne();
        mapper.map(person, personne);

        assertThat(personne.getAge()).isEqualTo(person.getAge());
        assertThat(personne.getNom()).isEqualTo(person.getName());
        assertThat(personne.getSurnom()).isEqualTo(person.getNickname());
        System.out.println("personne= " + personne);
    }

    @Test
    public void mapPersonneToPerson() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozer_mapping.xml"));

        Person person = new Person();
        Personne personne = new Personne("customNom", "customSurNom", 1);
        mapper.map(personne, person);

        assertThat(person.getAge()).isEqualTo(personne.getAge());
        assertThat(person.getName()).isEqualTo(personne.getNom());
        assertThat(person.getNickname()).isEqualTo(personne.getSurnom());
        System.out.println("person= " + person);
    }

    @Test
    public void shouldMapWithUser() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozer_mapping.xml"));

        User user = new User(1, "customLogin");
        Person person = new Person("customName", "customNickName", 3, user, null);
        Personne personne = new Personne();
        mapper.map(person, personne);

        assertThat(personne.getAge()).isEqualTo(person.getAge());
        assertThat(personne.getNom()).isEqualTo(person.getName());
        assertThat(personne.getSurnom()).isEqualTo(person.getNickname());

        assertThat(personne.getUser().getId()).isEqualTo(person.getUser().getId());
        assertThat(personne.getUser().getLogin()).isEqualTo(person.getUser().getLogin());

        System.out.println("personne= " + personne);

    }

    @Test
    public void shouldMapWithAuthorityList() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozer_mapping.xml"));

        List<AuthorityEng> authorityEngs = Arrays.asList(new AuthorityEng("customAuthEngName"));
        Person person = new Person("customName", "customNickName", 3, null, authorityEngs);
        Personne personne = new Personne();
        mapper.map(person, personne);

        assertThat(personne.getAge()).isEqualTo(person.getAge());
        assertThat(personne.getNom()).isEqualTo(person.getName());
        assertThat(personne.getSurnom()).isEqualTo(person.getNickname());

        assertThat(personne.getAuthoritiesFranches().get(0).getAuthorityName())
                .isEqualTo(person.getAuthoritiesEngs().get(0).getAuthorityName());

        System.out.println("personne= " + personne);

    }

}
