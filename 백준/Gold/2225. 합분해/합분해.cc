#include <iostream>
using namespace std;
#include <vector>
const int mod = 1000000000;
int main()
{
	int n, k;
	cin >> n >> k;
	vector<vector<int>>d(n + 1, vector<int>(k + 1, 0));
	for (int i = 0; i < n + 1; i++) {
		d[i][1] = 1;
	}
	for (int i = 0; i < n + 1; i++) {
		for (int j = 2; j < k + 1; j++) {
			for (int p = 0; p < i + 1; p++) {
				d[i][j] += d[p][j - 1];
				d[i][j] %= mod;
			}
		}
	}
	cout << d[n][k] % mod;
}