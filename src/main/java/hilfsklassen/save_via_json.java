package hilfsklassen;

/*
*
* Hilfsklasse zum Speichern von Daten in Json File
*
* */

import com.google.gson.JsonObject;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static org.SurvivalPlus.survivalPlus.SurvivalPlus.gson; //gson aus onEnable

public class save_via_json extends JavaPlugin {

    private File dataFile;
    private JsonObject dataJson;


    public void createDataFile() {
        dataFile = new File(getDataFolder(), "data.json");
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            try {
                dataFile.createNewFile();
                dataJson = new JsonObject();
                saveDataFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            loadDataFile();
        }
    }

    private void saveDataFile() {
        try (FileWriter writer = new FileWriter(dataFile)) {
            gson.toJson(dataJson, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDataFile() {
        try (FileReader reader = new FileReader(dataFile)) {
            dataJson = gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveData(String key, Object value) {
        dataJson.add(key, gson.toJsonTree(value));
        saveDataFile();
    }

    public <T> T loadData(String key, Class<T> clazz) {
        if (dataJson.has(key)) {
            return gson.fromJson(dataJson.get(key), clazz);
        }
        return null;
    }

    public void removeData(String key) {
        dataJson.remove(key);
        saveDataFile();
    }

    /*
    *
    * Spezifische Speichermethoden
    *
     */
    public void saveLocation(String key, Location location) {
        JsonObject locJson = new JsonObject();
        locJson.addProperty("world", location.getWorld().getName());
        locJson.addProperty("x", location.getX());
        locJson.addProperty("y", location.getY());
        locJson.addProperty("z", location.getZ());
        saveData(key, locJson);
    }

    public Location loadLocation(String key) {
        JsonObject locJson = loadData(key, JsonObject.class);
        if (locJson != null) {
            return new Location(getServer().getWorld(locJson.get("world").getAsString()),
                    locJson.get("x").getAsDouble(),
                    locJson.get("y").getAsDouble(),
                    locJson.get("z").getAsDouble());
        }
        return null;
    }

}

/*

String chestKey = "chest_location_1";
saveLocation(chestKey, new Location(world, x, y, z));

Location loadedLocation = loadLocation(chestKey);
String worldName = loadedLocation.getWorld().getName();
    double x = loadedLocation.getX();
    double y = loadedLocation.getY();
    double z = loadedLocation.getZ();


*/

