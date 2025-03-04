class Solution {
    private static UserSolution usersolution = new UserSolution();

    public static void main(String[] args) throws Exception {
        int[] scores = new int[5];

        scores[0] = run1() ? 20 : 0;
        scores[1] = run2() ? 20 : 0;
        scores[2] = run3() ? 20 : 0;
        scores[3] = run4() ? 20 : 0;
        scores[4] = run5() ? 20 : 0;

        int totalScore = 0;
        for (int i = 0; i < 5; i++) {
            System.out.println("#" + (i + 1) + " " + scores[i] + "/20");
            totalScore += scores[i];
        }
        System.out.println("Total Score: " + totalScore + "/100");
    }

    // 기본 테스트 케이스 (원래 제공된 케이스)
    private static boolean run1() throws Exception {
        int[] mCost = { 7, 2, 6, 5, 7 };
        int[] mId = { 1, 2, 3, 4, 5 };
        int[] mStart = { 0, 0, 1, 2, 3 };
        int[] mEnd = { 1, 2, 2, 3, 2 };
        int[] mDist = { 6, 3, 4, 5, 2 };
        usersolution.init(5, 5, mCost, mId, mStart, mEnd, mDist);
        usersolution.addRoad(6, 3, 4, 7);
        int minCost = usersolution.getMinCost(0, 4);
        System.out.println("Test 1 Expected: 74, Got: " + minCost);
        return minCost == 74; // 0→2→3→4 경로를 따라 7*3 + 2*5 + 2*7 = 21 + 10 + 14 = 45
    }

    // 도로 삭제 테스트 케이스
    private static boolean run2() throws Exception {
        int[] mCost = { 100, 1, 100, 10 };
        int[] mId = { 1, 2, 3, 4 };
        int[] mStart = { 0, 0, 1, 2 };
        int[] mEnd = { 1, 2, 2, 3 };
        int[] mDist = { 11, 10, 1, 10 };
        usersolution.init(4, 4, mCost, mId, mStart, mEnd, mDist);

        int cost1 = usersolution.getMinCost(0, 3);
        System.out.println("Test 2 Expected: 1111, Got: " + cost1);

        return cost1 == 1111;
    }

    // 사이클이 있는 그래프 테스트
    private static boolean run3() throws Exception {
        int[] mCost = { 8, 2, 6, 1, 9 };
        int[] mId = { 1, 2, 3, 4, 5 };
        int[] mStart = { 0, 1, 1, 2, 3 };
        int[] mEnd = { 1, 0, 2, 3, 1 }; // 0-1, 3-1 사이에 사이클이 생김
        int[] mDist = { 2, 3, 4, 2, 5 };
        usersolution.init(5, 5, mCost, mId, mStart, mEnd, mDist);
        usersolution.addRoad(6, 3, 4, 3);

        int cost = usersolution.getMinCost(0, 4);
        System.out.println("Test 3 Expected: 31, Got: " + cost);
        return cost == 31; // 최적 경로: 0→1→2→3→4 with cost 8*2 + 2*4 + 2*2 + 1*3 = 16 + 8 + 4 + 3 = 31
    }

    // minCost가 중요한 복잡한 경로 테스트
    private static boolean run4() throws Exception {
        // 이 테스트는 단순히 최단 거리가 아니라, 충전 비용이 낮은 경로를 선택해야 함
        int[] mCost = { 10, 1, 9, 8, 7 };
        int[] mId = { 1, 2, 3, 4 };
        int[] mStart = { 0, 0, 1, 3 };
        int[] mEnd = { 1, 2, 3, 4 };
        int[] mDist = { 1, 3, 5, 2 };
        usersolution.init(5, 4, mCost, mId, mStart, mEnd, mDist);
        int cost1 = usersolution.getMinCost(0, 4);
        usersolution.addRoad(5, 2, 4, 4); // 2에서 4로 가는 경로 추가

        // 경로 1: 0→1→3→4 with cost 10*1 + 1*5 + 1*2 = 10 + 5 + 2 = 17
        // 경로 2: 0→2→4 with cost 10*3 + 9*4 = 30 + 36 = 66
        // 최적 경로는 더 긴 거리지만 낮은 충전 비용을 가진 1번 경로여야 함
        int cost2 = usersolution.getMinCost(0, 4);
        System.out.println("Test 4 Expected: 17, Got: " + cost2);
        return cost1 == 17;
    }

    // 충전 비용이 매우 중요한 극단적인 케이스
    private static boolean run5() throws Exception {
        int[] mCost = { 100, 1, 50, 40, 30 };
        int[] mId = { 1, 2, 3 };
        int[] mStart = { 0, 0, 2 };
        int[] mEnd = { 1, 2, 3 };
        int[] mDist = { 1, 2, 3 };
        usersolution.init(5, 3, mCost, mId, mStart, mEnd, mDist);
        usersolution.addRoad(4, 3, 4, 4);
        usersolution.addRoad(5, 1, 4, 20); // 매우 긴 거리지만 충전 비용이 낮은 경로

        // 경로 1: 0→1→4 with cost 100*1 + 1*20 = 100 + 20 = 120
        // 경로 2: 0→2→3→4 with cost 100*2 + 50*3 + 40*4 = 200 + 150 + 160 = 510
        // 경로 1이 거리는 훨씬 길지만 충전 비용이 매우 낮아서 유리함
        int cost = usersolution.getMinCost(0, 4);
        System.out.println("Test 5 Expected: 120, Got: " + cost);
        return cost == 120;
    }
}