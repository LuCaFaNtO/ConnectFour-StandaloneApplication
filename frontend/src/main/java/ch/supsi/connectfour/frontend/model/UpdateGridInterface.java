package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.frontend.view.UpdateViewInterface;

public interface UpdateGridInterface extends UpdateViewInterface {
    void updateGrid(Cell cell);
}
