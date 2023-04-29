package utils;

public class Box<T> {
    private T value;
    private boolean hasSome;

    public Box(T value) {
        this.value = value;
        this.hasSome = true;
    }

    public Box() {
        this.value = null;
        this.hasSome = false;
    }

    public boolean hasSome() {
        return this.hasSome;
    }

    public boolean isNone() {
        return !this.hasSome;
    }

    public T get() {
        if (!this.hasSome) {
            throw new RuntimeException("Box is empty");
        }
        return this.value;
    }
}
