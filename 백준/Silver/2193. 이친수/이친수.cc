#include <iostream>
using namespace std;
long long a[91][2];
int main()
{
	a[1][1] = 1;
	for (int n = 2; n <= 90; n++) {
		a[n][0] = a[n - 1][0] + a[n - 1][1];
		a[n][1] = a[n - 1][0];
	}
	int N;
	cin >> N;
	cout << a[N][0] + a[N][1];
}