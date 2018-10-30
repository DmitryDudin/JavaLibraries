package ua.com.javatraining.jackson.jacksonPolymorphicDeserialization;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dog extends Animal {

    public Double barkVolume; // in decibels

    public Dog(String name, Double barkVolume) {
        super(name);
        this.barkVolume = barkVolume;
    }

}