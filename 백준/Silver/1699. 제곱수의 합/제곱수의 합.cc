#include <vector>
#include <iostream>
using namespace std;
const int MAX_NUM = 316;
int main()
{
	int n;
	cin >> n;
	vector<int>nums(MAX_NUM + 1);
	vector<int>d(100001);
	d[0] = 0;
	for (int i = 0; i <= MAX_NUM; i++) {
		nums[i] = i * i;
	}
	for (int i = 1; i <= 100000; i++) {
		int MIN = d[i - 1];
		for (int j = 1; j <= MAX_NUM; j++) {
			if (i >= nums[j]) {
				MIN = min(MIN, d[i - nums[j]]);
			}
		}
		d[i] = MIN + 1;
	}
	cout << d[n];
}