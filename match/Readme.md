# Matchmaking Server

## Architecture

![image](https://github.com/user-attachments/assets/6c6dd49e-553b-4fdf-a6f2-3718318040d5)

## Explain

1. 단일 토픽에 파티션을 여러개 구성
2. Spring boot에 컨슈머 그룹 설정
3. Spring boot와 kafka 연결시 해당 토픽에 대해 파티션 id 부여 될 것.
4. 매칭 시작의 경우 상관 없이 ALB를 통해서 어떤 인스턴스로 접근.
5. 매칭 시작 메세지를 Produce 하고, 리턴값으로 파티션 id 부여
6. 시작 메세지에 자동으로 파티션 id가 round robin 으로 할당이 되며, Redis에서 매칭 큐에 대해 관리하기 시작
7. Redis에서 lua 스크립트로 일련의 과정에 원자성 부여
8. SSE 방식으로 Client는 이벤트를 받게 구성함
9. 클라이언트는 초기 연결한 SSE 이벤트를 통해 수시로 현재 매칭중인 인원 파악

- 매칭 성공 경로
  10. 매칭 큐에 모든 인원이 채워지면 해당 인원에 대한 정보를 Kafka에게 넘기고, 이를 게임서버와 채팅 서버가 받아서 웹 소켓 통신 시작
  11. 클라이언트는 SSE 이벤트를 통해 연결을 끊고, 주워진 URL로 WebSocket 통신 시작

- 매칭 취소 경로 
  10. 클라이언트는 해당 파티션 id 기준으로 매칭 취소 이벤트를 발생
  11. 해당 파티션 id를 갖고있는 Spring boot 인스턴스의 컨슈머가 작동
