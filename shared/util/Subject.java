package GameApp.shared.util;

import java.beans.PropertyChangeListener;

/**
 * An interface to provide add or remove listeners to observers.
 *
 * @author Adrian Bugiel
 * @version 1.0
 */
public interface Subject
{
  /**
   * Adds an observer.
   *
   * @param eventName name of the event
   * @param listener provided listener
   */
  void addListener(String eventName, PropertyChangeListener listener);

  /**
   * Removes an observer.
   *
   * @param eventName name of the event
   * @param listener provided listener
   */
  void removeListener(String eventName, PropertyChangeListener listener);
}
