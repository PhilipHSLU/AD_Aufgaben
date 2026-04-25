package ch.hslu.w7_Aktoren;


public class TimeOutActor<T> extends Actor<TimeOutActor.Start<T>> {

    public record Start<T>(long delayMillis, ActorRef<T> target, T message) {}

    @Override
    protected void onMessage(Start<T> message) throws Exception {
        Thread.sleep(message.delayMillis());
        message.target().tell(message.message());
    }
}
