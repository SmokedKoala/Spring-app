package ru.spring.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * todo Document type RockMusic
 */
@Component
public class RockMusic implements Music{
    @Override
    public String getSong() {
        return "Start Me Up";
    }
}
