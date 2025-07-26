#pragma warning(disable : 4996)
#include <stdio.h>

int main()
{
	int a, b;
	scanf("%d", &a);
	scanf("%d", &b);
	int c, d, e, f;
	c = a * (b % 10);
	d = a * ((b / 10) % 10);
	e = a * (b / 100);
	f = a * b;
	printf("%d\n%d\n%d\n%d\n", c, d, e, f);
}