class UserSolution {
    public final static int N = 4;
    Solution solution = new Solution();

    public void doUserImplementation(int guess[]) {
        int[][] candidates = new int[10000][];
        int candidateCount = makeAllCandidate(candidates);

    	while(true) {
  			int[] can = candidates[0];
    		Solution.Result result = solution.query(can);
    		if (result.strike == N) {
    			for(int i = 0; i < N; i++) {
   					guess[i] = can[i];
    			}
    			return;
    		}
            candidateCount = removeInValid(candidates, candidateCount, can, result.strike, result.ball);
    	}
    }
    
    private int makeAllCandidate(int[][] candidates) {
        int index = 0;
        for (int i = 0; i < 10000; i++) {
            int n1000 = i / 1000;
            int n100 = (i % 1000) / 100;
            int n10 = (i % 100) / 10;
            int n1 = i % 10;
            if (n1000 == n100 || n1000 == n10 || n1000 == n1 ||
                n100 == n10 || n100 == n1 || n10 == n1) {
                continue;
            }
            candidates[index++] = new int[] { n1000, n100, n10, n1 };
        }
        return index;
    }
    
    private int removeInValid(int[][] candidates, int size, int[] can, int strike, int ball) {
    	int newSize = 0;
    	for (int i = 0; i < size; i++) {
            int[] candidate = candidates[i];
            Solution.Result res = myQuery(candidate, can);
            if (res.strike == strike && res.ball == ball) {
                candidates[newSize++] = candidate;
            }
        }
    	return newSize;
    }
    
    private Solution.Result myQuery(int[] answer, int guess[]) {
        Solution.Result result = new Solution.Result();
		result.strike = 0;
		result.ball = 0;

		for (int i = 0; i < N; ++i) {
			if (guess[i] == answer[i])
				result.strike++;
			else {
				boolean found = false;
                for (int j = 0; j < N; j++) {
                    if (guess[i] == answer[j]) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    result.ball++;
                }
			}
		}
		return result;
	}
}
