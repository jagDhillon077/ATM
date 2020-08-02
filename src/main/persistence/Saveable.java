package persistence;

import java.io.PrintWriter;
// Source - TellerApp code and logic is integrated to this
// program as TellerApp perfectly fit the needs of this program

// Represents data that can be saved to file
public interface Saveable {

    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    void save(PrintWriter printWriter);
}
