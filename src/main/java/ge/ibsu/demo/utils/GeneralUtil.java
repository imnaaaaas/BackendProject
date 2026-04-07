package ge.ibsu.demo.utils;

import org.apache.coyote.BadRequestException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public final class GeneralUtil {

    public static String getGetterName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static String getSetterName(String fieldName) {
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static void checkRequiredProperties(Object objectToCheck, List<String> requiredProperties) throws Exception {
        List<String> errorKeywords = new ArrayList<>();
        Iterator<String> it = requiredProperties.iterator();
        while (it.hasNext()) {
            String property = (String) it.next();
            boolean requiredPropertyIsPresent = true;
            Object value = null;
            try {
                value = objectToCheck
                        .getClass()
                        .getMethod(getGetterName(property))
                        .invoke(objectToCheck);
            } catch (Exception ex) {
                requiredPropertyIsPresent = false;
            }
            if (value == null) {
                requiredPropertyIsPresent = false;
            }
            if (!requiredPropertyIsPresent) {
                errorKeywords.add(property + "_REQUIRED");
            }
        }
        if (!errorKeywords.isEmpty()) {
            throw new BadRequestException(errorKeywords.stream().collect(Collectors.joining(";")));
        }
    }
}