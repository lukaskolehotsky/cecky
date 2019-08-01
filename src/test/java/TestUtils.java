import com.example.model.DBFile;
import com.example.model.DBItem;

import java.time.LocalDateTime;

class TestUtils {

    public DBItem generateDBItem(){
        return new DBItem("brand","type","guid", LocalDateTime.now());
    }

    public DBFile generateFile() {
        return new DBFile("fileName", "fileType", new byte[1], "guid");
    }
}
