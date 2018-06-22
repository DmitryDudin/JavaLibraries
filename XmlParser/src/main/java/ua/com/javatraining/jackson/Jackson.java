package ua.com.javatraining.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Jackson {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new XmlMapper();
    }
}
