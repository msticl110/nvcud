package org.nvcud.ag;


import java.util.function.Function;
import java.util.function.Supplier;

public interface Trans<T> {

    void tranIn(T data, Supplier<String> cn,Supplier<String> mn);

}
