package com.example.pixel_project2.common.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class PostDiversityOrdering {

    private PostDiversityOrdering() {
    }

    public static <T, K> List<T> diversify(List<T> items, Function<T, K> keyExtractor) {
        if (items == null || items.size() < 3) {
            return items == null ? List.of() : new ArrayList<>(items);
        }

        Map<K, Deque<T>> grouped = new LinkedHashMap<>();
        for (T item : items) {
            grouped.computeIfAbsent(keyExtractor.apply(item), ignored -> new ArrayDeque<>()).addLast(item);
        }

        List<T> diversified = new ArrayList<>(items.size());
        boolean hasRemaining = true;
        while (hasRemaining) {
            hasRemaining = false;
            for (Deque<T> queue : grouped.values()) {
                T next = queue.pollFirst();
                if (next == null) {
                    continue;
                }
                diversified.add(next);
                hasRemaining = true;
            }
        }

        return diversified;
    }
}
