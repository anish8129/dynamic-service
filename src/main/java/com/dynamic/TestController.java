/*
 * Copyright 2022 Play Games24x7 Pvt. Ltd. All Rights Reserved
 */

package com.dynamic;

import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicStringProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private static final DynamicStringProperty DYNAMIC_STRING_PROPERTY;
    private static final DynamicIntProperty DYNAMIC_INT_PROPERTY;

    static {
        DYNAMIC_STRING_PROPERTY = new DynamicStringProperty("name", "Test");
        DYNAMIC_INT_PROPERTY = new DynamicIntProperty("id", 3);
    }

    public TestController() {
        // Adding the cal back facility
        DYNAMIC_STRING_PROPERTY.addCallback(()-> {
            double rand = Math.random();
            System.out.println("Random Number for name " + rand);
        });

        DYNAMIC_INT_PROPERTY.addCallback(()-> {
            double rand = Math.random();
            System.out.println("Random Number for int " + rand);
        });
    }

    @GetMapping("/getString")
    public String getString() {
        return DYNAMIC_STRING_PROPERTY.get();
    }

    @GetMapping("/getInt")
    public int getInt() {
        return DYNAMIC_INT_PROPERTY.get();
    }
}
