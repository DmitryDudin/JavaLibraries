import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateTestCollections {
    private long i = 0;

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

//            System.out.println(name);
        }

        //-----------------------------------------
        long start = System.nanoTime();
        List<User> users = Stream.generate(() -> {
            Long id = i++;
            String firstName = df.getFirstName();
            String lastName = df.getLastName();
            String phone = String.valueOf(df.getNumberBetween(000000, 999999));
            String city = df.getCity();
            UserRole userRole = UserRole.values()[randomGenerator.nextInt(UserRole.values().length)];
            return new User(id, firstName, lastName, phone, city, userRole);
        })
//                .peek(System.out::println)
                .limit(1000000)
                .collect(Collectors.toList());
        long end = System.nanoTime();
        System.out.println("time1=" + (end - start));
        System.out.println(users.size());
//-----------------------------------------
        i = 0;
        start = System.nanoTime();
//        List<User> users1 = new ArrayList<User>(1000000);
//        List<User> users1;
        List<User> users1 = Stream.generate(() -> {
            Long id = i++;
            String firstName = df.getFirstName();
            String lastName = df.getLastName();
            String phone = String.valueOf(df.getNumberBetween(000000, 999999));
            String city = df.getCity();
            UserRole userRole = UserRole.values()[randomGenerator.nextInt(UserRole.values().length)];
            return new User(id, firstName, lastName, phone, city, userRole);
        })
//                .peek(System.out::println)
                .limit(1000000)
                .collect(Collectors.toList());
        end = System.nanoTime();
        System.out.println("time2=" + (end - start));

        System.out.println(users1.size());
    }

    private Long genId() {
        return i++;
    }
}
