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

/* 최대공약수와 최대공배수 */
//int GCD(int a, int b)
//{
//	while (b!=0)
//	{
//		int r = a % b;
//		a = b;
//		b = r;
//	}
//	return a;
//}
//
//int GCD1(int a, int b)
//{
//	if (b == 0)return a;
//	else return GCD1(b, a % b);
//}
//
//int main()
//{
//	int a, b, g;
//	cin >> a >> b;
//	g = GCD1(a, b);
//	cout << g << '\n' << g * (a / g) * (b / g);
//}

/* 백준 1978 */
//bool is_prime(int n)
//{
//	if (n < 2) return false;
//	for (int i = 2; i * i <= n; i++)
//		if (n % i == 0) return false;
//	return true;
//}
//
//int main()
//{
//	int n;
//	int count = 0;
//	cin >> n;
//	int* x = new int[n];
//	for (int i = 0; i < n; i++)
//		cin >> x[i];
//	for (int i = 0; i < n; i++)
//		if (is_prime(x[i])) count++;
//	cout << count;
//}

/* 1929 */
//bool is_prime(int n)
//{
//	if (n < 2) return false;
//	for (int i = 2; i * i <= n; i++)
//		if (n % i == 0) return false;
//	return true;
//}
//
//int main()
//{
//	int n, m;
//	int count = 0;
//	cin >> n >> m;
//	for (int i = n; i <= m; i++)
//		if (is_prime(i)) cout << i << '\n';
//}

///* 1929 에라토머시기 체 */
//const int MAX = 100000;
//int main()
//{
//	bool check[MAX+1] = { 0, };
//	int a = MAX;
//	check[0] = check[1] = true;
//	for (int i = 2; i <= a; i++) {
//		if (check[i] == false) {
//			for (int j = i * i; j <= a; j += i) {
//				check[j] = true;
//			}
//		}
//	}
//	int n, m;
//	cin >> n >> m;
//	for (int i = n; i <= m; i++) {
//		if (check[i] == false) cout << i << '\n';
//	}
//}

/* 6588 골드바흐의 추측 */
//int main()
//{
//	const int MAX = 10000;
//	int prime[MAX] = { 0, };
//	int pn = 0;
//	bool check[MAX + 1] = { false, };
//	ios_base::sync_with_stdio(false);
//	cin.tie(nullptr);
//	for (int i = 2; i <= MAX; i++) {
//		if (check[i] == false) {
//			prime[pn++] = i;
//			for (int j = i + i; j <= MAX; j += i) {
//				check[j] = true;
//			}
//		}
//	}
//	while (true) {
//		int n;
//		cin >> n;
//		if (n == 0) break;
//		for (int i = 1; i < pn; i++) {
//			if (check[n - prime[i]] == false) {
//				cout << n << " = " << prime[i] << " + " << n - prime[i] << "\n";
//				break;
//			}
//		}
//	}
//}
//int main()
//{
//
//	const int MAX = 100;
//	int prime[MAX] = { 0, };
//	int pn = 0;
//	bool check[MAX + 1] = { false, };
//	ios_base::sync_with_stdio(false);
//	cin.tie(nullptr);
//	for (int i = 2; i <= MAX; i++) {
//		if (check[i] == false) {
//			prime[pn++] = i;
//			for (int j = i + i; j <= MAX; j += i) {
//				check[j] = true;
//			}
//		}
//	}
//	for (int i = 0; i < MAX; i++) {
//		cout << i << "번째 prime[i] = " << prime[i] << "\ncheck[i] = " << check[i] << "\n\n";
//	}
//}

///* 2309 일곱 난쟁이 */
//int main()
//{
//	int height[9];
//	int real_height[7];
//	int sum;
//	for (int i = 0; i < 9; i++) {
//		cin >> height[i];
//	}
//	for (int i = 0; i < 8; i++) {
//		for (int j = i + 1; j < 9; j++) {
//			sum = 0;
//			for (int k = 0; k < 9; k++) {
//				if (k != i && k != j) {
//					sum += height[k];
//				}
//			}
//			if (sum == 100) {
//				int l = 0;
//				for (int k = 0; k < 9; k++) {
//					if (k != i && k != j) real_height[l++] = height[k];
//				}
//				int tmp;
//				for (int n = 0; n < 6; n++) {
//					for (int p = 0; p < 6; p++) {
//						if (real_height[p] > real_height[p + 1]) {
//							tmp = real_height[p];
//							real_height[p] = real_height[p + 1];
//							real_height[p + 1] = tmp;
//						}
//					}
//				}
//				for (int q = 0; q < 7; q++) {
//					cout << real_height[q] << '\n';
//				}
//				return 0;
//			}
//		}
//	}
//}

///* 3085번 사탕 게임 */
//
//void swap(char &a, char &b)
//{
//	char temp = a;
//	a = b;
//	b = temp;
//}
//
///* 테이블의 정보를 받고 먹을 수 있는 사탕의 최대 개수를 반환 */
//int Max_Eatable(string* table, int n)
//{
//	int max = 1;
//	int count;
//	int i = 0;
//	for (int i = 0; i < n; i++) {
//		count = 1;
//		for (int j = 0; j < n - 1; j++) {
//			if (table[i][j] == table[i][j + 1]) {
//				count += 1;
//			}
//			else {
//				count = 1;
//			}
//			if (count > max)max = count;
//		}
//		count = 1;
//		for (int j = 0; j < n - 1; j++) {
//			if (table[j][i] == table[j + 1][i]) {
//				count += 1;
//			}
//			else {
//				count = 1;
//			}
//			if (count > max)max = count;
//		}
//	}
//	return max;
//}
//
//int main()
//{
//	int n;
//	int max = 1;
//	int tmp;
//	cin >> n;
//	/* 테이블 배열 생성 */
//	string* table = new string [n];
//	/* 사탕 배치 */
//	for (int i = 0; i < n; i++) {
//		cin >> table[i];
//	}
//	/* 인접 사탕 교환하면서 먹을 수 있는 최대 사탕 개수 검사 */
//	tmp = Max_Eatable(table, n);
//	for (int i = 0; i < n; i++) {
//		for (int j = 0; j < n; j++) {
//			if (i + 1 < n) {
//				swap(table[i][j], table[i + 1][j]);
//				tmp = Max_Eatable(table, n);
//				if (max < tmp) {
//					max = tmp;
//				}
//				swap(table[i][j], table[i + 1][j]);
//			}
//			if (j + 1 < n) {
//				swap(table[i][j], table[i][j + 1]);
//				tmp = Max_Eatable(table, n);
//				if (max < tmp) {
//					max = tmp;
//				}
//				swap(table[i][j], table[i][j + 1]);
//			}
//		}
//	}
//	cout << max;
//}

