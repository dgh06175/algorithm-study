#include <queue>
#include <iostream>
using namespace std;
#define MAX 100000

int a[MAX + 1];
bool check[MAX + 1];
int dist[MAX + 1];
queue<int>q;
int main()
{
	int n, k;
	cin >> n >> k;
	int x = n;
	q.push(x); check[x] = true;
	while (!q.empty())
	{
		x = q.front(); q.pop();
		int nx;
		if (x < MAX) {
			nx = x + 1;
			if (check[nx] == false) {
				q.push(nx); check[nx] = true;
				dist[nx] = dist[x] + 1;
			}
		}
		if (x > 0) {
			nx = x - 1;
			if (check[nx] == false) {
				q.push(nx); check[nx] = true;
				dist[nx] = dist[x] + 1;
			}
		}
		if (x * 2 <= MAX) {
			nx = x * 2;
			if (check[nx] == false) {
				q.push(nx); check[nx] = true;
				dist[nx] = dist[x] + 1;
			}
		}
	}
	cout << dist[k];
}