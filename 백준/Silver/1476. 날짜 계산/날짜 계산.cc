#include <iostream>
using namespace std;
int main()
{
	int E, S, M, e = 0, s = 0, m = 0;
	cin >> E >> S >> M;
	for (int year = 1; year < 100000; year++) {
		e += 1;
		s += 1;
		m += 1;
		if (e > 15) e = 1;
		if (s > 28) s = 1;
		if (m > 19) m = 1;
		if (e == E && s == S && m == M) {
			cout << year;
			break;
		}
	}
}