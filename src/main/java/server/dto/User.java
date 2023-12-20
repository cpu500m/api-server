package server.dto;

/**
 * ObjectMapper는 기본 생성자로 생성후 setter로 데이터를 주입하는 방식을 사용하기 때문에 기본 생성자 코드 넣음.
 * Lombok 쓰면 편하겠지만 외부 라이브러리 최소한으로 땡겨오면서 해보고 싶어서..
 */
public class User {
    private Long userId;
    private String username;
    private Long postCount;

    public User(){

    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Long getPostCount() {
        return postCount;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPostCount(Long postCount) {
        this.postCount = postCount;
    }
}
