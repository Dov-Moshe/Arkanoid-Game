package interfaces;

/**
 * A interface of notifier that adds and removes listeners.
 */
public interface HitNotifier {
    /**
     * This method adds 'hit listener' as a listener to hit events.
     * @param hl the listener.
     */
    void addHitListener(HitListener hl);
    /**
     * This method removes 'hit listener' from the list of listeners to hit events.
     * @param hl the listener.
     */
    void removeHitListener(HitListener hl);
}