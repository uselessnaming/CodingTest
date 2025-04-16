## 완전탐색 공부 (2025.02.24 ~ 2025.03.19)
+ 사소한 조건 꼼꼼히 확인
+ 데이터의 양이 많지 않다면 일단 구현해보기
+ 데이터의 양이 많다면 이진 탐색, HashMap 등을 활용해 시간 소요를 줄여보기
+ 단순 탐색의 경우 간단한 bfs, dfs를 통한 문제들이 꽤나 있음
+ 수가 적은 경우 단순 구현으로 흘러가는 경우가 많음
### 결론 : 완전탐색의 경우 간단하게는 구현으로 흘러가고, 수가 많아지는 경우에는 다른 알고리즘과 혼합되어 사용되는 경우가 많음

## 그리디 알고리즘 (2025.03.19 ~ 2025.03.27)
+ 단순 암기를 통해서는 대체가 어려움
+ 가장 큰 순서대로, 가장 작은 순서대로와 같은 말들이 제시될 가능성이 높음
+ 대체로 정렬 알고리즘과 짝을 이룸
+ 정렬 알고리즘을 통해 우선되는 것들을 구분해야 함
+ 어떤 것을 기준으로 정렬하는 것이 최적인지 생각해야 함
+ 이 과정에서 Priority Queue 또한 상당히 자주 사용됨
+ Custom Comparator를 잘 설정해야 함
### 결론 : 정렬의 기준이 굉장히 중요함

## DFS와 BFS (2025.03.27 ~ 2025.04.15)
+ 깊이 우선 탐색 / 넓이 우선 탐색 방법
+ 완전 탐색의 경우 사용
+ dfs - 모든 단계를 탐색해야 하는 경우보다 하나의 경우의 수를 끝까지 확인하며 진행해야 할 때 더 적합함
+ bfs - 모든 단계를 최소한으로 탐색하는 경우 사용
+ 기본 코드의 구성이나 발상은 동일하지만 데이터의 양에 따라 bfs와 dfs를 선택해야 할 때가 있음
+ 여러 가지 조건과 접목하여 문제가 나는 경우가 많음
### 결론 : 조건에 따라 dfs와 bfs를 잘 구분지어야 함 (유리한 방향으로) / 여러 조건들을 적절히 조합할 수 있어야 함

## 이분탐색 (2025.04.15 ~ )
+ 이분 탐색 방법
+ 데이터의 양이 많은 경우 사용해야 함
+ 다른 유형과 연계되는 문제가 많음
+ 다양한 유형의 이분 탐색 방법이 있기에 창의성이 조금 필요할 수도..
+ 비교문의 순서에 따라 성공 여부가 갈릴 수 있음