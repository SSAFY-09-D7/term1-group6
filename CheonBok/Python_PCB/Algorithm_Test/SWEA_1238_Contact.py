from collections import defaultdict, deque
nodes = defaultdict(set) # 각 정점과 연결된 노드 정보
T = 10 # Test Case

def bfs(start: int) -> int:
    used = [False] * 101    # 방문처리
    queue = deque([start])
    cnt = 0  # 라운드 수
    rnd = 1  # 같은 cnt 번호를 붙일 개수
    while queue:
        cnt += 1 

        # 한 번의 큐 회전은 같은 방문 차수를 의미
        # (다자 간 통화로 동시 전달 가능)
        for _ in range(rnd):
            target = queue.popleft()

            if not used[target]:
                stamp[target] = cnt
                used[target] = True
                queue.extend(nodes[target])
                tmp = cnt
        
        rnd = len(queue) # 다음 회차에 동시 전화될 수
    
    #print(stamp, cnt)
    return tmp


for t in range(1, T+1):
    nodes.clear()
    size, start = map(int, input().split())
    inputs = list(map(int, input().split()))
    stamp = [0] * 101 # 방문 차례 저장

    for idx in range(0, size, 2):
        _from = inputs[idx]       # 출발 (발신)
        _to = inputs[idx+1]       # 도착 (수신)
        nodes[_from].add(_to)     # 방향성


    ret = bfs(start) # 가능한 최대 연락 도달 차수

    # 역순으로 탐색해 가장 큰 번호 반환
    for idx in range(100, -1, -1):
        if stamp[idx] == ret:
            print(f"#{t} {idx}")
            break