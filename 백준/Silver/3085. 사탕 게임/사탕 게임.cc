#include <iostream>
using namespace std;

void swap(char &a, char &b)
{
	char temp = a;
	a = b;
	b = temp;
}

/* 테이블의 정보를 받고 먹을 수 있는 사탕의 최대 개수를 반환 */
int Max_Eatable(string* table, int n)
{
	int max = 1;
	int count;
	int i = 0;
	for (int i = 0; i < n; i++) {
		count = 1;
		for (int j = 0; j < n - 1; j++) {
			if (table[i][j] == table[i][j + 1]) {
				count += 1;
			}
			else {
				count = 1;
			}
			if (count > max)max = count;
		}
		count = 1;
		for (int j = 0; j < n - 1; j++) {
			if (table[j][i] == table[j + 1][i]) {
				count += 1;
			}
			else {
				count = 1;
			}
			if (count > max)max = count;
		}
	}
	return max;
}

int main()
{
	int n;
	int max = 1;
	int tmp;
	cin >> n;
	/* 테이블 배열 생성 */
	string* table = new string [n];
	/* 사탕 배치 */
	for (int i = 0; i < n; i++) {
		cin >> table[i];
	}
	/* 인접 사탕 교환하면서 먹을 수 있는 최대 사탕 개수 검사 */
	tmp = Max_Eatable(table, n);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (i + 1 < n) {
				swap(table[i][j], table[i + 1][j]);
				tmp = Max_Eatable(table, n);
				if (max < tmp) {
					max = tmp;
				}
				swap(table[i][j], table[i + 1][j]);
			}
			if (j + 1 < n) {
				swap(table[i][j], table[i][j + 1]);
				tmp = Max_Eatable(table, n);
				if (max < tmp) {
					max = tmp;
				}
				swap(table[i][j], table[i][j + 1]);
			}
		}
	}
	cout << max;
}