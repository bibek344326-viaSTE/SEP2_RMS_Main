import client.core.ClientFactory;
import client.model.tables.TableModel;
import client.model.tables.TableModelManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.utils.table.Table;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

public class TableModelTest {
    private TableModel tableModel1;

    // This method is run before each test. It sets up the test environment.
    @BeforeEach
    public void setup() throws RemoteException {
        // Create a new ClientFactory
        ClientFactory clientFactory = new ClientFactory();
        // Use the ClientFactory to create a new TableModel
        tableModel1 = new TableModelManager(clientFactory.getTableClient());
    }

    // Test the addNewTable method
    @Test
    public void testAddNewTable() {
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            Table table = new Table("Table 1", 4);
            tableModel1.createTable(table);
        });
        assertEquals("Illegal State", exception.getMessage());
    }

    // Test the deleteTable method
    @Test
    public void testDeleteTable() {
        assertDoesNotThrow(() -> {
            Table table = new Table("Table 1", 4);
            tableModel1.deleteTable(table.getTableName());
        });
        assertEquals(0, tableModel1.getTables().size());
    }

    // Test the updateTable method
    @Test
    public void testUpdateTable() {
        // Assert that an IllegalStateException is thrown when updateTable is called

        Table table = new Table("Table 1", 4);
        tableModel1.updateTable(table, "Table 2", 5);
        assertEquals("Table 2", table.getTableName());
        assertEquals(5, table.getTableCapacity());
    }

    // Test the getTables method
    @Test
    public void testGetTables() {
        // Assert that the size of the tables list is 0
        assertEquals(11, tableModel1.getTables().size());
    }
}