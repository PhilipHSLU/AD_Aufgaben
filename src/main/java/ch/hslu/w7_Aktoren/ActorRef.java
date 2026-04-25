package ch.hslu.w7_Aktoren;

@FunctionalInterface
public interface ActorRef<T> {
    void tell(T message);
}
