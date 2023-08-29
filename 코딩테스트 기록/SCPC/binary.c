#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <string.h>
#include <math.h>

int Answer;
int a[50001];
int b[50001];
int check[50001];

int main(void)
{
	int T, test_case;
	
	setbuf(stdout, NULL);

	scanf("%d", &T);
	for (test_case = 0; test_case < T; test_case++)
	{
		//////////////////////////////
		memset(a, 0, sizeof(a));
		memset(b, 0, sizeof(b));
		memset(check, 0, sizeof(check));
		Answer = 0;
		int n, t;
		scanf("%d %d", &n, &t);
		for (int i = 1; i <= n; i++) {
			scanf("%1d", &b[i]);
		}

		for (int i = 1; i <= t; i++) {
			if (b[i] == 1) {
				a[i + t] = 1;
				check[i + t + t] = 1;
			}
		}
		for (int i = t + 1; i <= n - t; i++) {
			if (b[i] == 1 && check[i] == 0) {
				a[i + t] = 1;
				check[i + t + t] = 1;
			}
		}
		for (int i = n - t + 1; i <= n; i++) {
			if (b[i] == 1 && check[i] == 0) {
				a[i - t] = 1;
			}
		}
		int cnt = 0;
		int cnt2 = 0;
		/*printf("\n");
		for (int i = 1; i <= n; i++) {
			printf("%d", a[i]);
		}
		printf("\n");*/
		for (int i = 1; i <= n; i++) {
			if (a[i] == 0)cnt2 += 1;
			else break;
		}
		for (int i = n; i > 0; i--) {
			Answer += a[i] * pow(10,cnt++);
		}
		//////////////////////////////
		printf("Case #%d\n", test_case + 1);
		while (cnt2--)printf("0");
		printf("%d\n", Answer);

	}

	return 0;//Your program should return 0 on normal termination.
}