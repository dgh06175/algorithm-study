#include <iostream>
using namespace std;
#include <cmath>
int n;
int c[10];
long long MAX, MIN;
void go(int* ine, int* arr, int index)
{
	if (index == n + 1) {
		// 부등호 맞는지 검사
		/*int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (ine[i] == 1) {
				if (arr[i] < arr[i + 1]) cnt += 1;
			}
			else if (ine[i] == 2) {
				if (arr[i] > arr[i + 1]) cnt += 1;
			}
		}
		if (cnt == n) {*/
			long long sum = 0;

			/*cout << "arr = ";
			for (int i = 0; i < n + 1; i++) {
				cout << arr[i];
			}
			cout << '\n';*/

			for (int i = 0; i < n + 1; i++) {
				sum += (long long)arr[i] * (long long)pow(10, n - i);
			}
			if (MAX == 0 && MIN == 0) { // 처음에만
				MAX = MIN = sum;
			}
			//cout << "sum = " << sum << '\n'; // 임시
			if (MAX < sum)MAX = sum;
			if (MIN > sum)MIN = sum;
		//}
		return;
	}
	for (int i = 0; i <= 9; i++) {
		if (c[i])continue;
		if (index > 0) {
			if (ine[index - 1] == 1) { // <
				if (arr[index - 1] > i) continue;
			}
			else if (ine[index - 1] == 2) { // >
				if (arr[index - 1] < i) continue;
			}
		}
		arr[index] = i;
		c[i] = 1;
		go(ine, arr, index + 1);
		c[i] = 0;
	}
}

int main()
{
	cin >> n;
	int* ine = new int[n];
	int* arr = new int[n + 1];
	for (int i = 0; i < n; i++) {
		char c;
		cin >> c;
		if (c == '<') ine[i] = 1; // ine[i]가 1이면 부등호 <
		else if (c == '>') ine[i] = 2; // ine[i]가 2면 부등호 >
		else ine[i] = 0;
	}
	go(ine, arr, 0);
	char* CMIN = new char[n + 2];
	char* CMAX = new char[n + 2];
	for (int i = n; i >= 0; i--) {
		CMIN[i] = (MIN % 10) + '0';
		MIN /= 10;
		CMAX[i] = (MAX % 10) + '0';
		MAX /= 10;
	}
	for (int i = 0; i < n + 1; i++) {
		cout << CMAX[i];
	}
	cout << '\n';
	for (int i = 0; i < n + 1; i++) {
		cout << CMIN[i];
	}
}