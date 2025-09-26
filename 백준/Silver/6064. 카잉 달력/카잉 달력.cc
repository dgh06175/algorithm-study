#include <iostream>
using namespace std;

int main()
{
	int attempt;
	cin >> attempt;
	for (int j = 0; j < attempt; j++) {
		int m, n, x, y, OK = 0;
		cin >> m >> n >> x >> y;
		for (int i = x - 1; i <= m * n; i += m) {
			if (i % m == x - 1 && i % n == y - 1) {
				OK = i + 1;
				break;
			}
		}
		if (OK) cout << OK;
		else cout << -1;
		cout << '\n';
	}
}