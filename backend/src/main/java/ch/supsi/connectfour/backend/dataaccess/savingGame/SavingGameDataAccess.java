package ch.supsi.connectfour.backend.dataaccess.savingGame;

import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;
import ch.supsi.connectfour.backend.business.savingGame.SavingGameDataAccessInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SavingGameDataAccess implements SavingGameDataAccessInterface {
    private static SavingGameDataAccess instance = null;

    private SavingGameDataAccess() {}

    public static SavingGameDataAccess getInstance() {
        return instance == null ? instance = new SavingGameDataAccess() : instance;
    }

    @Override
    public void saveGameOnFile(File file, Cell[][] grid, boolean turn) throws IOException {
        JSONObject gameStateJson = new JSONObject();

        JSONArray gridJsonArray = new JSONArray();
        for (Cell[] row : grid) {
            JSONArray rowJsonArray = new JSONArray();
            for (Cell cell : row) {
                JSONObject cellJson = new JSONObject();
                cellJson.put("row", cell.getRow());
                cellJson.put("col", cell.getCol());
                cellJson.put("fill", cell.isFill());

                Player player = cell.getPlayer();
                if (player != null) {
                    JSONObject playerJson = new JSONObject();
                    playerJson.put("name", player.getName());

                    Piece piece = player.getPiece();
                    if (piece != null) {
                        JSONObject pieceJson = new JSONObject();
                        pieceJson.put("color", piece.getColor());
                        pieceJson.put("symbol", piece.getSymbol());
                        playerJson.put("piece", pieceJson);
                    }

                    cellJson.put("player", playerJson);
                } else {
                    cellJson.put("player", JSONObject.NULL);
                }

                rowJsonArray.put(cellJson);
            }
            gridJsonArray.put(rowJsonArray);
        }

        gameStateJson.put("grid", gridJsonArray);
        gameStateJson.put("turn", turn);

        // Write the JSON object to the specified file
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(gameStateJson.toString(4));
            System.out.println("Game data saved successfully to " + file.getAbsolutePath());
        }
    }
}
