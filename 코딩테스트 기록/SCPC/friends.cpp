#include <iostream>
#include <vector>
#include <cstring>
using namespace std;

int Answer;
vector<int>f[100001];
bool check[100001];

void make_friend(int a, int b)
{
	if (a == b)return;
	for (int i = 0; i < f[a].size(); i++) {
		if (f[a][i] == b) return;
	}
	f[a].push_back(b);
	f[b].push_back(a);
}

void dfs(int node) {
	check[node] = true;
	for (int i = 0; i < f[node].size(); i++) {
		int next = f[node][i];
		if (check[next] == false) {
			dfs(next);
		}
	}
}

int main(int argc, char** argv)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int T, test_case;
	cin >> T;
	for (test_case = 0; test_case < T; test_case++)
	{
		Answer = 0;
		memset(check, 0, sizeof(check));
		memset(f, 0, sizeof(f));

		int n;
		cin >> n;
		int* D = new int[n];
		for (int i = 0; i < n; i++) {
			cin >> D[i];
		}
		for (int i = 0; i < n; i++) {
			if (i + D[i] >= n)continue;
			make_friend(i, i + D[i]);
		}


		for (int i = 0; i < n; i++) {
			int size1 = f[i].size();
			for (int j = 0; j < size1; j++) {
				int size2 = f[f[i][j]].size();
				for (int k = 0; k < size2; k++) {
					make_friend(i, f[f[i][j]][k]);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (check[i] == false) {
				dfs(i);
				Answer += 1;
			}
		}

		cout << "Case #" << test_case + 1 << endl;
		cout << Answer << endl;
	}

	return 0;//Your program should return 0 on normal termination.
}