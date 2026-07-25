import java.util.*;

class Solution {
    public int solution(int[] sales, int[][] links) {
        int n = sales.length;

        List<Integer>[] children = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int[] l : links) {
            children[l[0]].add(l[1]);
        }

        int[] order = new int[n];
        int idx = 0;
        order[idx++] = 1;
        for (int i = 0; i < idx; i++) {
            for (int c : children[order[i]]) {
                order[idx++] = c;
            }
        }

        long[] dp0 = new long[n + 1];   // 불참
        long[] dp1 = new long[n + 1];   // 참석

        for (int i = n - 1; i >= 0; i--) {
            int v = order[i];
            int sale = sales[v - 1];
            List<Integer> child = children[v];

            if (child.isEmpty()) {
                dp0[v] = 0;
                dp1[v] = sale;
                continue;
            }

            long S = 0;
            long minDelta = Long.MAX_VALUE;

            for (int c : child) {
                S += Math.min(dp0[c], dp1[c]);
                minDelta = Math.min(minDelta, dp1[c] - dp0[c]);
            }

            dp1[v] = sale + S; 
            dp0[v] = S + Math.max(0, minDelta);
        }

        return (int) Math.min(dp0[1], dp1[1]);
    }
}

// 모든 팀은 최소 1명 이상의 직원이 워크숍 참석
// 워크숍 참석 직원의 매출액 합이 최소

// N <= 300,000
// 모든 경우의 수 -> 2^N -> 시간초과
// CEO 부터 시작해서, 팀원중에 한명을 고른다.
// 다음 팀장들 중에서 팀원중에 한명을 고른다.
// 모든 팀중에, 한명씩 고르는 모든 경우의 수?
// -> 극단적인 경우, 트리가 한줄 -> O(N)
// -> 균등 트리 -> 트리의 깊이가 LogN
// 팀 배분이 완벽하게 되어서, 팀원 평균 루트N, 팀 개수 루트N -> 팀마다 한명씩 모두 뽑아보기 -> O(루트N^루트N) -> 안됨
// 그리디 안됨. 팀이 겹치기 때문
// 완탐 안됨. 시간초과

// dp? 점화식 어떻게 세우지
// 모든 팀은 최소 1명 이상의 직원이 워크숍 참석 -> 조건이 팀에 묶인다. 쪼갤 수 있다.
// 팀 안에서 판단할 때, 팀장이 참석중인지 아닌지에 따라 조건이 나뉜다.
// 팀장 참석 -> 팀원 참석 필수 아님
// dp[i][0] -> 팀원 i 가 참석하지 않을 때 매출액 최소값
// dp[i][1] -> 팀원 i 가 참석할 때 매출액 최소값

// p = 부모, c = 자식 노드
// dp[p][0] = 
// dp[p][1] = sale[v] + min(dp[c][0], dp[c][1])