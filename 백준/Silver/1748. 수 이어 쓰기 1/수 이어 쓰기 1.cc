#include <cmath>
#include <iostream>
using namespace std;

int main()
{
	int n, num = 0;
	int count = 0;
	cin >> n;
	int m = n;
	for (int i = 1; i <= 10; i++) {
		m /= 10;
		if (m == 0) {
			num = i; // num = n의 자릿수
			break;
		}
	}
	for (int i = 1; i <= num - 1; i++) { // count = n의 이전 자릿수(num-1)까지의 모든 자릿수의 합
		count += i * (int)(pow(10, i) - (int)pow(10, i - 1));
	}
	count += (n - (int)pow(10, num - 1) + 1) * num;
	cout << count;
}