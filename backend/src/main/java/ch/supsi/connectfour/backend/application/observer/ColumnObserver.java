package ch.supsi.connectfour.backend.application.observer;

public interface ColumnObserver {
    void disableColumn(final int column);

    void enableColumn(final int column);
}
