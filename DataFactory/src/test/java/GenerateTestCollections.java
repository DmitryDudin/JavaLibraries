import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateTestCollections {
    enum UserRole {
        ADMIN, USER, GUEST
    }

    private static class User {
        private Long id;
        private String firstName;
        private String lastName;
        private String phone;
        private String city;
        private UserRole userRole;

        public User() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            return id.equals(user.id);
        }

        @Override
        public int hashCode() {
            int result = id.hashCode();
            result = 31 * result + firstName.hashCode();
            result = 31 * result + lastName.hashCode();
            result = 31 * result + phone.hashCode();
            result = 31 * result + city.hashCode();
            result = 31 * result + userRole.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "[" +
                    "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", phone='" + phone + '\'' +
                    ", city='" + city + '\'' +
                    ", userRole=" + userRole +
                    ']';
        }

        public User(Long id, String firstName, String lastName, String phone, String city, UserRole userRole) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.city = city;
            this.userRole = userRole;
        }

        public Long getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getPhone() {
            return phone;
        }

        public String getCity() {
            return city;
        }

        public UserRole getUserRole() {
            return userRole;
        }
    }

    @Test
    public void generate() {
        DataFactory df = new DataFactory();
        int seed = 1234567890;
        df.randomize(seed);
        Random randomGenerator = new Random();
        for (long i = 0; i < 1000; i++) {
            UserRole userRole = UserRole.values()[randomGenerator.nextInt(UserRole.values().length)];
            String name = "id=" + i +
                    " firsName=" + df.getFirstName() +
                    "    lastName=" + df.getLastName() +
                    "    userRole=" + userRole;

            System.out.println(name);
            new User(i, df.getFirstName(), df.getLastName(), String.valueOf(df.getNumberBetween(000000, 999999)), df.getCity(), userRole);
            Stream.generate(()->new User(Long., df.getFirstName(), df.getLastName(), String.valueOf(df.getNumberBetween(000000, 999999)), df.getCity(), userRole))
                    .limit(1000000)
                    .collect(Collectors.toList());
        }
    }
}
