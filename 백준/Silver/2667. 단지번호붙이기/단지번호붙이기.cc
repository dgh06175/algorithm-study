#include <vector>
#include <algorithm>
#include <queue>
#include <cstdio>
using namespace std;
int A[30][30];
int group[30][30]; // check
int ans[25 * 25];
int dx[4] = { 0,0,-1,1 };
int dy[4] = { 1,-1,0,0 };
int k;
void bfs(int a, int b, int m)
{
	int countt = 0;
	queue<pair<int, int>>q;
	group[a][b] = m; q.push(make_pair(a, b));
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && ny >= 0 && nx < k && ny < k) {
				if (A[nx][ny] == 1 && group[nx][ny] == 0) {
					group[nx][ny] = m;
					q.push(make_pair(nx, ny));
				}
			}
		}
	}
}

int main()
{
	scanf("%d", &k);
	for (int i = 0; i < k; i++) {
		for (int j = 0; j < k; j++) {
			scanf("%1d", &A[i][j]);
		}
	}
	int cnt = 0;
	for (int i = 0; i < k; i++) {
		for (int j = 0; j < k; j++) {
			if (A[i][j] == 1 && group[i][j] == 0) {
				bfs(i, j, ++cnt);
			}
			ans[group[i][j]] += 1;
		}
	}
	printf("%d\n", cnt);
	sort(ans+1, ans + 1 + cnt);
	for (int i = 1; i <= cnt; i++) {
		printf("%d\n", ans[i]);
	}
}