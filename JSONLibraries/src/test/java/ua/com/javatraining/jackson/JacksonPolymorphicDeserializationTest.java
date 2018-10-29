package ua.com.javatraining.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import ua.com.javatraining.jackson.jacksonPolymorphicDeserialization.Animal;
import ua.com.javatraining.jackson.jacksonPolymorphicDeserialization.Cat;
import ua.com.javatraining.jackson.jacksonPolymorphicDeserialization.Dog;

import java.io.IOException;

public class JacksonPolymorphicDeserializationTest {

    @Test
    public void globalDefaultTyping() throws IOException {
        //need Animal without @JsonTypeInfo and @JsonSubTypes
        ObjectMapper objectMapper = new ObjectMapper();

        //To enable use of (requirement for) type information for all objects:
//        objectMapper.enableDefaultTyping();// default to using DefaultTyping.OBJECT_AND_NON_CONCRETE

//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);//not work
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

//        JAVA_LANG_OBJECT: only affects properties of type Object.class
//        OBJECT_AND_NON_CONCRETE: affects Object.class and all non-concrete types (abstract classes, interfaces)
//        NON_CONCRETE_AND_ARRAYS: same as above, and all array types of the same (direct elements
//                                 are non-concrete types or Object.class)
//        NON_FINAL: affects all types that are not declared 'final', and array types of non-final element types.


        Animal myDog = new Dog("ruffus", 12.0);
        Animal myCat = new Cat("goya", true, 99);

        String dogJson = objectMapper.writeValueAsString(myDog);
        String catJson = objectMapper.writeValueAsString(myCat);

        System.out.println("dogJson= " + dogJson);
        System.out.println("catJson= " + catJson);

        Animal animal = objectMapper.readValue(dogJson, Animal.class);
        System.out.println("\nanimal instanceof Dog= " + (animal instanceof Dog));
        System.out.println("animal.getClass().getSimpleName()= " + animal.getClass().getSimpleName());

        animal = objectMapper.readValue(catJson, Animal.class);
        System.out.println("\nanimal instanceof Cat= " + (animal instanceof Cat));
        System.out.println("animal.getClass().getSimpleName()= " + animal.getClass().getSimpleName());

    }

    @Test
    public void perClassAnnotations() throws IOException {
        //need Animal with @JsonTypeInfo and @JsonSubTypes
        ObjectMapper objectMapper = new ObjectMapper();

        Animal myDog = new Dog("ruffus", 12.0);
        Animal myCat = new Cat("goya", true, 99);

        String dogJson = objectMapper.writeValueAsString(myDog);
        String catJson = objectMapper.writeValueAsString(myCat);

        System.out.println("dogJson= " + dogJson);
        System.out.println("catJson= " + catJson);

        Animal animal = objectMapper.readValue(dogJson, Animal.class);
        System.out.println("\nanimal instanceof Dog= " + (animal instanceof Dog));
        System.out.println("animal.getClass().getSimpleName()= " + animal.getClass().getSimpleName());

        animal = objectMapper.readValue(catJson, Animal.class);
        System.out.println("\nanimal instanceof Cat= " + (animal instanceof Cat));
        System.out.println("animal.getClass().getSimpleName()= " + animal.getClass().getSimpleName());

    }

}
