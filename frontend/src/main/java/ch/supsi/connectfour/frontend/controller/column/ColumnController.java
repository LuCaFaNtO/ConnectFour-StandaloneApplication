package ch.supsi.connectfour.frontend.controller.column;

import ch.supsi.connectfour.backend.application.ObserverControllerInterface;
import ch.supsi.connectfour.backend.application.observer.ColumnObserver;
import ch.supsi.connectfour.backend.application.observer.ObserverController;
import ch.supsi.connectfour.frontend.view.column.ColumnView;

public class ColumnController implements ColumnControllerInterface, ColumnObserver {
    private static ColumnController instance = null;
    private ColumnViewInterface columnView;

    private ColumnController() {
        this.columnView = ColumnView.getInstance();
        ObserverControllerInterface observerController = ObserverController.getInstance();
        observerController.registerColumnObserver(this);
    }

    public static ColumnController getInstance() {
        return instance == null ? instance = new ColumnController() : instance;
    }

    @Override
    public void addColumnView(ColumnViewInterface columnView) {
        this.columnView = columnView;
    }

    @Override
    public void disableColumn(final int column) {
        columnView.disableColumnButton(column);
    }

    @Override
    public void enableColumn(int column) {
        columnView.enableColumnButton(column);
    }
}
