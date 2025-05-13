#include <iostream>
#include <cmath>
using namespace std;
int main()
{
	int t, k = 1;
	cin >> t;
	while (t--) {
		long long x, y;
		cin >> x >> y;
		long long ans;
		long long a = (long long)sqrt(y - x);
		if ((y - x) == a * a) { // (y - x) 가 제곱수 일때
			ans = a * 2 - 1;
		}
		else if ((y - x) > a * a && (y - x) <= a * a + a) {
			ans = a * 2;
		}
		else if (a * a + a < (y - x) && (y - x) < (a + 1) * (a + 1)) {
			ans = a * 2 + 1;
		}
		else ans = -1;
		cout << ans << endl;
	}
}