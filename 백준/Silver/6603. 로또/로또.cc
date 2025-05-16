#include <iostream>
using namespace std;
#include <vector>
#include <algorithm>
int n;
int main()
{
	while (cin >> n) {
		if (n == 0)break;
		int* a = new int[n];
		for (int i = 0; i < n; i++) {
			cin >> a[i];
		}
		vector<int>OK(n);
		for (int i = 0; i < n; i++) {
			if (i < 6)OK[i] = 1;
			else OK[i] = 0;
		}
		int* ary = new int[6];
		do {
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				if (OK[i] == 1) {
					ary[cnt++] = a[i];
				}
			}
			for (int i = 0; i < 6; i++)cout << ary[i] << ' '; cout << '\n';
		} while (prev_permutation(OK.begin(), OK.end()));
        cout << '\n';
	}
}