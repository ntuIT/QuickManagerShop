package com.qman.web.utility;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StreamUtils {

    public static <T> List<T> toList(Iterable<T> iter) {
        if (iter == null) return null;
        return StreamSupport.stream(iter.spliterator(), false).collect(Collectors.toList());
    }
}
