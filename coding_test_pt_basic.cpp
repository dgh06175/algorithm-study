#include <iostream>
using namespace std;

//int Digit(long long m)
//{
//	int i = 0;
//	for (; m > (long long)0; i++) {
//		m /= (long long)10;
//	}
//	return i;
//}
//int IsOnlyOne(long long m)
//{
//	while (1)
//	{
//		if (m % (long long)10 == (long long)1) {
//			m /= (long long)10;
//			if (m < (long long)1) return 1;
//		}
//		else return 0;
//	}
//}
//
//int main(void)
//{
//	int n;
//	long long m;
//	while (cin >> n) {
//		if (n >= 1 || n <= 10000) {
//			for (int i = 1; i < 12000000; i++) {
//				m = (long long)n * (long long)i;
//				if (IsOnlyOne(m)) {
//					cout << Digit(m) << '\n';
//					break;
//				}
//			}
//		}
//	}
//} 

/* 백준 4375 */
//int main(void)
//{
//	int n;
//	while (cin >> n) {
//		int num = 0;
//		for (int i = 1;; i++){
//			num = (num * 10 + 1) % n;
//			if (num == 0){
//				cout << i << '\n';
//				break;
//			}
//		}
//	}
//}

/* 백준 1037 */
//int main()
//{
//	int count, min, max;
//	cin >> count;
//	int* real = new int[count];
//	for (int i = 0; i < count; i++) {
//		cin >> real[i];
//	}
//	min = max = real[0];
//	for (int i = 1; i < count; i++) {
//		if (min > real[i]) {
//			min = real[i];
//		}
//		if (max < real[i]) {
//			max = real[i];
//		}
//	}
//	cout << min * max;
//	delete real;
//}

/* 백준 17427 */
//#include <cmath>
//int f(int a) // a의 약수들의 합 반환
//{
//	int sum = 0;
//	int root = (int)pow((double)a, 0.5);
//	if (root * root == a){ //제곱수일 경우
//		for (int i = 1; i < root; i++)
//		{
//			if (a % i == 0) {
//				sum += i;
//				sum += a / i;
//			}
//		}
//		sum += root;
//	}
//	else {
//		for (int i = 1; i <= root; i++)
//		{
//			if (a % i == 0) {
//				sum += i;
//				sum += a / i;
//			}
//		}
//	}
//	return sum;
//}
//
//int main()
//{
//	int n;
//	long long sum = 0;
//	cin >> n;
//	for (int i = 1; i <= n; i++)
//	{
//		sum += (long long)(n / i) * i;
//	}
//	cout << sum;
//}
