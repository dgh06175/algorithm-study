#include <iostream>
using namespace std;

int main()
{
	int height[9];
	int real_height[7];
	int sum;
	for (int i = 0; i < 9; i++) {
		cin >> height[i];
	}
	for (int i = 0; i < 8; i++) {
		for (int j = i + 1; j < 9; j++) {
			sum = 0;
			for (int k = 0; k < 9; k++) {
				if (k != i && k != j) {
					sum += height[k];
				}
			}
			if (sum == 100) {
				int l = 0;
				for (int k = 0; k < 9; k++) {
					if (k != i && k != j) real_height[l++] = height[k];
				}
				int tmp;
				for (int n = 0; n < 6; n++) {
					for (int p = 0; p < 6; p++) {
						if (real_height[p] > real_height[p + 1]) {
							tmp = real_height[p];
							real_height[p] = real_height[p + 1];
							real_height[p + 1] = tmp;
						}
					}
				}
				for (int q = 0; q < 7; q++) {
					cout << real_height[q] << '\n';
				}
				return 0;
			}
		}
	}
}