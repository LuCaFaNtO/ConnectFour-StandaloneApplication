package ch.supsi.connectfour.backend.dataaccess.savingGame;

import ch.supsi.connectfour.backend.application.exceptions.IllegalFIleException;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;
import ch.supsi.connectfour.backend.business.savingGame.SavingGameDataAccessInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SavingGameDataAccess implements SavingGameDataAccessInterface {
    private static SavingGameDataAccess instance = null;

    private SavingGameDataAccess() {
    }

    public static SavingGameDataAccess getInstance() {
        return instance == null ? instance = new SavingGameDataAccess() : instance;
    }

    @Override
    public void saveGameOnFile(File file, Cell[][] grid, boolean turn) throws IllegalFIleException {
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
                if (player != null)
                    cellJson.put("player", player.getName());
                else
                    cellJson.put("player", "");


                rowJsonArray.put(cellJson);
            }
            gridJsonArray.put(rowJsonArray);
        }

        gameStateJson.put("grid", gridJsonArray);
        gameStateJson.put("turn", turn);

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(gameStateJson.toString(4));
        } catch (IOException e) {
            throw new IllegalFIleException("Error during save file");
        }
    }

    @Override
    public boolean loadTurnFromFile(File file) throws IllegalFIleException {
        try {
            JSONObject gameStateJson = readJsonFromFile(file);
            return gameStateJson.getBoolean("turn");
        } catch (JSONException e) {
            throw new IllegalFIleException("Error: cannot find turn");
        }
    }

    @Override
    public Cell[][] loadGridFromFile(File file, List<Player> players) throws IllegalFIleException {
        try {
            JSONObject gameStateJson = readJsonFromFile(file);
            JSONArray gridJsonArray = gameStateJson.getJSONArray("grid");
            Cell[][] grid = new Cell[gridJsonArray.length()][gridJsonArray.getJSONArray(0).length()];

            for (int i = 0; i < gridJsonArray.length(); i++) {
                JSONArray rowJsonArray = gridJsonArray.getJSONArray(i);
                for (int j = 0; j < rowJsonArray.length(); j++) {
                    JSONObject cellJson = rowJsonArray.getJSONObject(j);
                    int row = cellJson.getInt("row");
                    int col = cellJson.getInt("col");
                    boolean fill = cellJson.getBoolean("fill");

                    Player player = null;
                    String playerName = cellJson.getString("player");
                    if (!playerName.isEmpty())
                        player = playerName.equals("Player1") ? players.get(0) : players.get(1);

                    grid[i][j] = new Cell(row, col);
                    grid[i][j].setFill(fill);
                    grid[i][j].setPlayer(player);
                }
            }
            return grid;
        } catch (JSONException e) {
            throw new IllegalFIleException("Error: cannot find grid");
        }
    }

    private JSONObject readJsonFromFile(File file) throws IllegalFIleException {
        try (FileReader fileReader = new FileReader(file)) {
            StringBuilder stringBuilder = new StringBuilder();
            int character;
            while ((character = fileReader.read()) != -1) {
                stringBuilder.append((char) character);
            }
            return new JSONObject(stringBuilder.toString());
        } catch (IOException e) {
            throw new IllegalFIleException("Error during loading from file");
        }
    }
}
