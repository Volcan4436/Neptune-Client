package neptune.utils;

import neptune.module.api.Category;

public class MiscUtils {

    public static Category getCategoryByPackage(Class<?> clazz) {
        String[] splitPkg = clazz.getPackage().getName().split("\\.");
        String pkg = splitPkg[splitPkg.length - 1];
        return Category.valueOf(pkg.toUpperCase());
    }

    public static <T> boolean contains(T[] array, T object) {
        for (T element : array)
            if (element.equals(object))
                return true;

        return false;
    }
}