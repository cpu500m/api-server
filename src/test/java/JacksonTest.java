import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.Test;
import server.dto.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JacksonTest {

    // snake_case -> camelCase로 변환하도록 ObjectMapper 생성 및 설정.
    ObjectMapper om = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    @Test
    void JSON을_객체로() throws Exception {
        String string = """
                { "user_id" : 1,
                   "username" : "paul",
                   "post_count" : 15 }
                """;
        User user = om.readValue(string, User.class);
        assertEquals(1, user.getUserId());
        assertEquals("paul", user.getUsername());
        assertEquals(15, user.getPostCount());
    }

    @Test
    void JSON_Array를_List로() throws Exception {
        final int VALID_SIZE = 2;

        String string = """
                [
                  {
                    "user_id": 924,
                    "username": "thompsoneric",
                    "post_count": 20
                  },
                  {
                    "user_id": 920,
                    "username": "drewpineda",
                    "post_count": 99
                  } ]
                """;
        List<User> users = om.readValue(string, new TypeReference<List<User>>() {});
        assertEquals(users.size(), VALID_SIZE);
        User[] checkArr = new User[VALID_SIZE];
        checkArr[0] = new User();
        checkArr[0].setUserId(924L);
        checkArr[0].setUsername("thompsoneric");
        checkArr[0].setPostCount(20L);

        checkArr[1] = new User();
        checkArr[1].setUserId(920L);
        checkArr[1].setUsername("drewpineda");
        checkArr[1].setPostCount(99L);

        for (int i = 0; i < VALID_SIZE; i++) {
            User cur = users.get(i);
            assertEquals(checkArr[i].getUserId(), cur.getUserId());
            assertEquals(checkArr[i].getUsername(), cur.getUsername());
            assertEquals(checkArr[i].getPostCount(), cur.getPostCount());
        }
    }


}
