package com.company;

import java.util.Objects;

public class Tuple<T, T1> {
    private T person;
    private T1 usaMacchinaPropria;

    public Tuple(T t, T1 t1){
        this.person = t;
        this.usaMacchinaPropria = t1;
    }

    public Tuple(T t){
        this.person = t;
    }
    public T getPerson() {
        return person;
    }

    public T1 getUsaMacchinaPropria() {
        return usaMacchinaPropria;
    }

    public void setUsaMacchinaPropria(T1 usaMacchinaPropria) {
        this.usaMacchinaPropria = usaMacchinaPropria;
    }

    @Override
    public String toString() {
        if(usaMacchinaPropria instanceof Boolean){
            boolean bool = (boolean) usaMacchinaPropria;
            if(bool){
                return person +
                        "*"
                        ;
            }else{
                return person.toString();
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
        return Objects.equals(person, tuple.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person);
    }
}
