package com.company;

import java.util.Objects;

public class Tuple<T, T1> {
    private T t1;
    private T1 t2;

    public Tuple(T t, T1 t1){
        this.t1 = t;
        this.t2 = t1;
    }

    public Tuple(T t){
        this.t1 = t;
    }
    public T getT1() {
        return t1;
    }

    public T1 getT2() {
        return t2;
    }

    public void setT1(T t1) {
        this.t1 = t1;
    }
    public void setT2(T1 t2) {
        this.t2 = t2;
    }

    @Override
    public String toString() {
        if(t2 instanceof Boolean){
            boolean bool = (boolean) t2;
            if(bool){
                return t1 +
                        "*"
                        ;
            }else{
                return t1.toString();
            }
        }else{
            throw new RuntimeException();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(t1, tuple.t1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t1);
    }
}
