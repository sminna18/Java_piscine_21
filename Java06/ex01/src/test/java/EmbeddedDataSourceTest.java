import org.junit.jupiter.api.BeforeEach;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class EmbeddedDataSourceTest {
    private DataSource dataSource;

    @BeforeEach
    public void init() {
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        dataSource = databaseBuilder.addScript("schema.sql").addScript("data.sql").build();
    }

    @Test
    void connectionTest() throws SQLException {
        assertNotNull(dataSource.getConnection());
    }
}
