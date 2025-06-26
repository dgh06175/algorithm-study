#include <queue>
#include <cstdio>
using namespace std;
int a[100][100]; // 미로 기록
bool check[100][100];
int dist[100][100]; // 원점부터의 거리 기록
queue<pair<int, int>>q;
int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };

int main()
{
	//입력받고, bfs를 이용해서 dist배열을 채운후, dist[n-1][m-1]의 숫자를출력한다.
	int n, m;
	scanf("%d %d", &n, &m);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			scanf("%1d", &a[i][j]);
		}
	}
	int x = 0, y = 0;
	dist[x][y] = 1;
	check[x][y] = true; q.push(make_pair(x, y));
	while (!q.empty()) {
		x = q.front().first;
		y = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
				if (a[nx][ny] == 1 && check[nx][ny] == false) {
					check[nx][ny] = true;
					dist[nx][ny] = dist[x][y] + 1;
					q.push(make_pair(nx, ny));
				}
			}
		}
	}
	//for (int i = 0; i < n; i++) {
	//	for (int j = 0; j < m; j++) {
	//		printf("%02d ", dist[i][j]);
	//	}
	//	printf("\n");
	//}
	printf("%d", dist[n - 1][m - 1]);
}