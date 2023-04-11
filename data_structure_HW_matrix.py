# 이차원 배열을 받아 출력 하는 함수
def print_arr(arr):
    if not arr:
        return False
    for v in arr:
        for k in v:
            print("%4s" % k, end='')
        print()


# 행렬 m1을 받아 m1의 전치 행렬을 반환 하는 함수
def make_tranpose_matrix(m):
    x = len(m)
    y = len(m[0])
    arr = [[0] * y for _ in range(x)]
    for i in range(x):
        for j in range(y):
            arr[j][i] = m[i][j]
    return arr


# 행렬 m1과 m2를 받아서 두 행렬의 곱셈을 반환하는 함수
def times_matrix(m1, m2):
    row_m1 = len(m1[0])  # m1의 열의 개수
    col_m1 = len(m1)  # m1의 행의 개수
    row_m2 = len(m2[0])  # m2의 열의 개수
    col_m2 = len(m2)  # m2의 행의 개수

    if row_m1 != col_m2:  # m1 의 열의 개수와 m2의 행의 개수가 같지 않으면 곱셈 불가능
        print("행렬의 곱이 불가능합니다.")
        return False

    matrix = [[0] * row_m2 for _ in range(col_m1)]  # 결과값 행렬 미리 선언

    # 행렬의 곱 계산 시작
    for i in range(0, col_m1):
        for j in range(0, row_m2):
            tmp = 0
            for a in range(0, row_m1):
                tmp += m1[i][a] * m2[a][j]
            matrix[i][j] = tmp
    return matrix


# 희소 행렬의 예시
sparse_matrix = [[0, 0, 0, 7, 0, 0],
                 [9, 0, 0, 0, 0, 8],
                 [0, 0, 0, 0, 0, 0],
                 [6, 5, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0, 1],
                 [0, 0, 2, 0, 0, 0]]


# 희소행렬의 전치행렬을 구한다.
tranpose_matrix = make_tranpose_matrix(sparse_matrix)

# 희소행렬과 전치행렬을 곱한다.
result_matrix = times_matrix(sparse_matrix, tranpose_matrix)

# 결과값을 출력한다.
print_arr(result_matrix)

# 이 프로그램의 시간복잡도는 회소행렬의 행의 길이를 N, 열의 길이를 M 이라고 했을 때,
# 행렬의 곱 연산을 수행할 때, m1의 행의 길이 * m2의 열의 길이 * m1의 열의 길이 의 연산이 있을때,
# m2는 m1의 전치행렬 이므로 m1의 행의 길이는 m2의 열의 길이이므로
# m1의 행의 길이는 N, m2의 열의 길이는 N, m1의 열의 길이는 M 이므로
# 이 프로그램의 시간복잡도는 O(N * N * M) = O(MN^2) 이다.
