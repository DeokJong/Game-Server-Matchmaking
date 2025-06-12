// benchmark-parallel-total.js
// Usage: node --max-old-space-size=4096 benchmark-parallel-total.js

const axios = require('axios');

const TOTAL_REQUESTS = 1_000_0;
const URL            = 'http://localhost:8080/match/randomMatch';

(async () => {
  // 1) 요청 프라미스 배열 생성 (이 시점엔 아직 실행 직후)
  const promises = Array.from({ length: TOTAL_REQUESTS }, (_, i) =>
    axios.post(URL, { nickname: `user${i}` }).catch(() => {})
  );

  // 2) 전체 병렬 요청 시작 직후 시간 기록
  const start = Date.now();

  // 3) 모두 끝날 때까지 대기
  await Promise.all(promises);

  // 4) 끝난 시점에 다시 시간 기록 → 전체 경과(ms)
  const elapsed = Date.now() - start;

  console.log(`총 요청 수      : ${TOTAL_REQUESTS}`);
  console.log(`평균 응답 시간 : ${(elapsed / TOTAL_REQUESTS).toFixed(2)} ms`);
})();
