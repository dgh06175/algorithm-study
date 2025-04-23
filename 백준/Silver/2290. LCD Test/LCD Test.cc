#include <vector>
#include <algorithm>
#include <iostream>
#include <cstdio>
using namespace std;
int shape_of_digit[10][7] = {
	{1,1,1,0,1,1,1}, //0
	{0,0,1,0,0,1,0}, //1
	{1,0,1,1,1,0,1}, //2
	{1,0,1,1,0,1,1}, //3
	{0,1,1,1,0,1,0}, //4
	{1,1,0,1,0,1,1}, //5
	{1,1,0,1,1,1,1}, //6
	{1,0,1,0,0,1,0}, //7
	{1,1,1,1,1,1,1}, //8
	{1,1,1,1,0,1,1}, //9
};

void print_line(int num, int type, int size)
{
	if (type == 0 || type == 3 || type == 6) { // ----
		printf(" ");
		if (shape_of_digit[num][type] == 0) {
			for (int i = 0; i < size; i++) {
				printf(" ");
			}
		}
		else {
			for (int i = 0; i < size; i++) {
				printf("-");
			}
		}
		printf("  ");
	}
	else if (type == 1 || type == 4) { // |
		if (shape_of_digit[num][type] == 0) {
			printf(" ");
			for (int i = 0; i < size; i++) {
				printf(" ");
			}
			if (shape_of_digit[num][type + 1] == 0) {
				printf(" ");
			}
			else {
				printf("|");
			}
		}
		else {
			printf("|");
			for (int i = 0; i < size; i++) {
				printf(" ");
			}
			if (shape_of_digit[num][type + 1] == 0) {
				printf(" ");
			}
			else {
				printf("|");
			}
		}
		printf(" ");
	}
	else {
		return;
	}
}

int main()
{
	int size;
	long long n;
	scanf("%d %lld", &size, &n);
	vector<int>a;
	for (int i = 0; n != 0; i++) {
		a.push_back(n % 10);
		n /= 10;
	}
	reverse(a.begin(), a.end());

	for (int type = 0; type < 7; type++) {
		if (type == 1 || type == 4) {
			for (int i = 0; i < size; i++) {
				for (int num = 0; num < a.size(); num++) {
					print_line(a[num], type, size);
				}
				if (i != size - 1)printf("\n");
			}
		}
		else {
			for (int num = 0; num < a.size(); num++) {
				print_line(a[num], type, size);
			}
			printf("\n");
		}
	}
}
