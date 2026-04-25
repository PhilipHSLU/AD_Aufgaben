package ch.hslu.w7_Aktoren;

import java.util.concurrent.*;
import java.util.function.*;

/**
 * Example Actor implementation for teaching purposes. Please see the examples
 * and lecture slides for details on how to use this implementation.
 * <p>
 * Usage: Each actor inherits from Actor parametrized with its message protocol.
 * <p>
 * In the main method, create at least one actor instance and send a first message.
 * Then call Actor.awaitShutDown(); Now your actor-based program waits until an actor
 * sends the Shutdown message to the ShutdownActor.
 * <p>
 * Current limitations:
 * - Actors are never garbage collected as the runLoop never exits.
 * - No clean shutdown possible.
 *
 * @param <M> The message protocol (interface) used by this Actor.
 */
public abstract class Actor<M> implements ActorRef<M> {
    private final LinkedBlockingQueue<M> mailbox = new LinkedBlockingQueue<>();
    private final Thread worker = Thread.startVirtualThread(this::runLoop);

    /**
     * Sends a message asynchronously to this actor (no wait).
     *
     * @param message The message to be sent.
     */
    public final void tell(M message) {
        mailbox.add(message);
    }

    /**
     *  Sends a message synchronously to this actor (to wait for reply).
     * <p>
     *  Requires a lamba as parameter that specifies the full message
     *  expect to be sent to the reply address,
     *  e.g., replyTo -> new Msg(a, b, replyTo).
     *
     * @param mkMessage Lamba to create the message.
     * @return Type handle to wait for the result (see class Mailbox).
     * @param <R> Response type.
     */
    public <R> Mailbox<R> ask(Function<ActorRef<R>, M> mkMessage) {
        Mailbox<R> replyTo = new Mailbox<>();
        tell(mkMessage.apply(replyTo));
        return replyTo;
    }

    protected abstract void onMessage(M message) throws Exception;
    protected void onError(M message, Throwable t) {
        throw new RuntimeException(t);
    };

    private void runLoop() {
        while (true) {
            final M message;
            try {
                message = mailbox.take();
            } catch (InterruptedException e) {
                break;
            }
            try {
                onMessage(message);
            } catch (Throwable t) {
                onError(message, t);
            }
        }
    }

    /**
     * A single shot continuation actor. Use for converting message type
     * and contents.
     *
     * @param <M>
     */
    public final static class ContinuationActor<M> extends Actor<M> {
        private final java.util.function.Consumer<M> continuation;
        private boolean used = false;

        public ContinuationActor(java.util.function.Consumer<M> continuation) {
            this.continuation = continuation;
        }

        @Override
        protected void onMessage(M message) {
            if (used) {
                throw new IllegalStateException("Continuation already used");
            }
            used = true;
            continuation.accept(message);
        }
    }

    /**
     * Replaces an actor for synchronous actions: Store a single temporary result.
     * Always to waits until result is ready. Only for usage in combination with ask.
     * Do not use otherwise.
     *
     * @param <T>
     */
    public static class Mailbox<T> implements ActorRef<T> {
        private final BlockingQueue<T> content = new ArrayBlockingQueue<>(1);

        /**
         * Waits (interruptable) for reply  and returns it without timeout.
         *
         * @return The reply in the mailbox.
         * @throws InterruptedException
         */
        public T get() throws InterruptedException {
            return content.take();
        }

        /**
         * Waits (interruptable) for reply and returns it with a specifiable timeout.
         *
         * @return The reply in the mailbox.
         * @throws InterruptedException
         */
        public T get(long timeout, TimeUnit unit) throws InterruptedException {
            return content.poll(timeout, unit);
        }

        /**
         * Sends a message to this actor.
         * @param message message to be sent.
         */
        @Override
        public void tell(T message) {
            content.add(message);
        }
    }

    public sealed interface ShutDownMessage permits Shutdown {}
    public record Shutdown(int exitCode) implements ShutDownMessage {}
    private static final class ShutdownActor extends Actor<ShutDownMessage> {
        @Override
        protected void onMessage(ShutDownMessage message) throws Exception {
            switch (message) {
                case Shutdown s -> {
                    exitCode = s.exitCode;
                    synchronized (shutdownSignal) {
                        shutdownSignal.notify();
                    }
                }
            }
        }
    }
    private static final Actor<ShutDownMessage> shutdownActor = new ShutdownActor();
    private static final Object shutdownSignal = new Object();
    private static Integer exitCode;

    /**
     * Obtains reference to ShutdownActor for sending the ShutdownMessage:
     * getShutdownActor().tell(new Shutdown(exitCode));
     *
     * @return instance of ShutdownActor.
     */
    public static Actor<ShutDownMessage> getShutdownActor() {
        return shutdownActor;
    }

    /**
     * Awaits ShutDownMessage. Place at end of main method:
     *
     * void main() {
     *     ...
     *     ...
     *     Actor.awaitShutDown();
     * }
     *
     */
    public static void awaitShutDown() {
        synchronized (shutdownSignal) {
           try {
               shutdownSignal.wait();
               System.exit(exitCode);
           } catch (InterruptedException e) {
               throw new RuntimeException(e); // cannot not happen if used correctly
           }
        }
    }
}