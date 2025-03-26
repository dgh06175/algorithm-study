def longest_increasing_subsequence(arr):
    n = len(arr)
    dp = [1] * n  # 각 위치에서의 LIS 길이를 저장

    for i in range(1, n):
        for j in range(i):
            if arr[i] > arr[j]:
                dp[i] = max(dp[i], dp[j] + 1)
    
    # LIS 길이 출력
    return max(dp)

# 입력 처리
n = int(input())  # 수열 A의 크기
arr = list(map(int, input().split()))  # 수열 A의 요소들

# 결과 출력
print(longest_increasing_subsequence(arr))