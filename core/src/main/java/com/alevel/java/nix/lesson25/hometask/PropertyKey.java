package com.alevel.java.nix.lesson25.hometask;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PropertyKey {
    String value();
}
