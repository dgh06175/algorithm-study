#include <iostream>
using namespace std;
#include <vector>
const int mod = 9901;
int main()
{
	int n;
	cin >> n;
	vector<vector<int>>d(n + 1, vector<int>(3, 0));
	d[1][0] = 1;
	d[1][1] = 1;
	d[1][2] = 1;
	for (int i = 2; i <= n; i++) {
		d[i][0] = d[i - 1][1] + d[i - 1][2] % mod;
		d[i][1] = d[i - 1][0] + d[i - 1][2] % mod;
		d[i][2] = d[i - 1][0] + d[i - 1][1] + d[i - 1][2] % mod;
	}
	cout << (d[n][0] + d[n][1] + d[n][2]) % mod;
}