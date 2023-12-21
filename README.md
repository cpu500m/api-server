# 외부라이브러리 Jackson만 사용하여 API서버 개발해보기.

### Host -> 0.0.0.0 지정 . 모든 Host 접근 가능.
### Port -> 기본포트. 8080으로 설정.

## 결과

#### 루트 경로 기본요청 (Get)
<img width="452" alt="image" src="https://github.com/cpu500m/api-server/assets/80875680/46f73607-409d-40c5-b80a-0b2f2ae7b455"> <br>

#### 서버 내 데이터 읽어서 결과 JSON으로 반환(GET)
<img width="450" alt="image" src="https://github.com/cpu500m/api-server/assets/80875680/c4eecdbb-2ac7-46fd-ab97-4a3f81cdd5c8"><br>

#### GET 요청 + Query 처리 
<img width="443" alt="image" src="https://github.com/cpu500m/api-server/assets/80875680/24502d9a-5c32-4f93-b743-32ef4e83dc4a"><br>

#### POST 요청 + RequestBody 처리 
<img width="444" alt="image" src="https://github.com/cpu500m/api-server/assets/80875680/3769d43a-b394-4191-924e-da13f7536549"> <br>

#### 지원하지 않는 메서드 (405 상태코드 반환)
<img width="448" alt="image" src="https://github.com/cpu500m/api-server/assets/80875680/00a2effc-9b78-4498-91d9-f5bd0739d4d5"> <br>


### 후기
#### 스프링 최고. 톰캣 만세.
