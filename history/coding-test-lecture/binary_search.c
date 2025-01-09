#include <stdio.h>
int target;

int binary_search(int array[], int start, int end)
{
	if (start > end) return -1;
	int mid = (start + end) / 2;
	if (array[mid] == target) return mid;
	else if (array[mid] > target) return binary_search(array, start, mid - 1);
	else return binary_search(array, mid + 1, end);
}


int main()
{
	int ary[] = {1, 6, 8, 10, 32, 512, 4623, 123412};
	printf("타겟 입력 : ");
	scanf("%d", &target);

	int result = binary_search(ary, 0, sizeof(ary)/sizeof(int) - 1);
	printf("%d", result);
}