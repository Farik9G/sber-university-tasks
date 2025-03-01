package streams;

import java.util.*;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Streams<T> {
    private final List<T> elements;

    private Streams(List<T> elements) {
        this.elements = new ArrayList<>(elements);
    }

    public static <T> Streams<T> of(List<T> list) {
        return new Streams<>(list);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return new Streams<>(elements.stream()
                .filter(predicate)
                .collect(Collectors.toList()));
    }

    public <R> Streams<R> transform(Function<? super T, ? extends R> transformer) {
        Objects.requireNonNull(transformer);
        return new Streams<>(elements.stream()
                .map(transformer)
                .collect(Collectors.toList()));
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyMapper,
                                  Function<? super T, ? extends V> valueMapper) {
        Objects.requireNonNull(keyMapper);
        Objects.requireNonNull(valueMapper);
        return elements.stream()
                .collect(Collectors.toMap(keyMapper, valueMapper, (e1, e2) -> e1));
    }

}

