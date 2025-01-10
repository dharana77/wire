
## 와이어 바알리

### 실행 방법 

- 루트 경로에서 `docker build -t wire .` 명령어를 통해 도커 이미지를 생성합니다.

- 루트 경로에서 `docker-compose up` 명령어를 통해 Mysql 데이터베이스와 스프링을 실행합니다.


1. 계좌 거래 내용 가져오기
   - http://localhost:8080/history 
   - ```json
     {
     "accountNumber": "1234",
     "accountType": "1"
     }
     ```

2. 계좌 출금
    - http://localhost:8080/account/withdraw
    - ```json
      {"accountId": 1,
      "amount" : 1000}

3. 계좌 입금
    - http://localhost:8080/account/deposit
    - ```json
      {"accountId": 1,
      "amount" : 1000}

4. 계좌 이체
    - http://localhost:8080/account/transfer
    - ```json
      { "accountId": 1,
      "receiptAccountId": 2,
      "amount" : 1000
      }

5. 계좌 삭제
    - http://localhost:8080/account/delete
    - ```json
      { "id": 1}

6. 계좌 등록
   - http://localhost:8080/account/enroll
    - ```json
      {"accountNumber": "1234",
      "accountType": "1"
      }
     
