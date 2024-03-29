#include <stdio.h>
#include <stdlib.h>

#define ROWS 3
#define COLS 3
#define MAX_TERMS 10

typedef struct {
	int row;
	int col;
	int value;
} element;

typedef struct SparseMatrix {
	element data[MAX_TERMS];
	int rows;
	int cols;
	int terms;
} SparseMatrix;

SparseMatrix sparse_matrix_add2(SparseMatrix a, SparseMatrix b)
{
	SparseMatrix c;
	int ca=0, cb=0, cc=0;
	if(a.rows != b.rows || a.cols != b.cols) {
		fprintf(stderr, "희소행렬 크기에러\n");
		exit(1);
	}
	c.rows = a.rows;
	c.cols = a.cols;
	c.terms = 0;

	while (ca < a.terms && cb < b.terms) {
		int inda = a.data[ca].row * a.cols + a.data[ca].col;
		int indb = b.data[cb].row * b.cols + b.data[cb].col;
		if (inda < indb) {
			c.data[cc++] = a.data[ca++];
		}
		else if (inda == indb) {
			if ((a.data[ca].value + b.data[cb].value) != 0) {
				c.data[cc].row = a.data[ca].row;
				c.data[cc].col = a.data[ca].col;
				c.data[cc++].value = a.data[ca++].value + b.data[cb++].value;
			} else {
				ca++; cb++;
			}
		} else {
			c.data[cc++] = b.data[cb++];
		}
	}
	for (;ca < a.terms;) c.data[cc++] = a.data[ca++];
	for (;cb < b.terms;) c.data[cc++] = b.data[cb++];
	c.terms = cc;
	return c;
}

void printMatrix(SparseMatrix m){
int arr[ROWS][COLS] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
	for (int i=0; i< m.terms; i++){
		int r = m.data[i].row;
		int c = m.data[i].col;
		int v = m.data[i].value;
		arr[r][c] = v;
	}

	for (int i=0; i<ROWS; i++){
		for (int j=0; j<COLS; j++){
			printf("%-3d", arr[i][j]);
		}
		printf("\n");
	}
}

int main()
{
	SparseMatrix m1 = { {{1, 1, 5}, {2, 2, 9}}, 3, 3, 2 };
	SparseMatrix m2 = { {{0, 0, 5}, {2, 2, 9}}, 3, 3, 2 };
	SparseMatrix m3;
	m3 = sparse_matrix_add2(m1, m2);
	printMatrix(m1);
	printf("\n");
	printMatrix(m2);
	printf("\n");
	printMatrix(m3);
}