ROWS = 3
COLS = 3
MAX_TERMS = 10


class Element:
    row = 0
    col = 0
    value = 0

    def __init__(self, row, col, value):
        self.row = row
        self.col = col
        self.value = value


class SparseMatrix:
    rows = 0
    cols = 0
    terms = 0

    def __init__(self, rows, cols, terms):
        self.data = [Element(0, 0, 0) for _ in range(MAX_TERMS)]
        self.rows = rows
        self.cols = cols
        self.terms = terms

    def print_matrix(self):
        arr = [[0] * ROWS for _ in range(COLS)]
        for v in self.data:
            x = v.row
            y = v.col
            arr[x][y] = v.value
        for i in arr:
            for j in i:
                print(j, end=' ')
            print()


def sparse_matrix_add2(a, b):
    ca, cb, cc = 0, 0, 0
    if (a.rows != b.rows) or (a.cols != b.cols):
        print("희소행렬 크기에러")
        exit(1)

    c = SparseMatrix(a.rows, a.cols, 0)

    while (ca < a.terms) and (cb < b.terms):
        inda = a.data[ca].row * a.cols + a.data[ca].col
        indb = b.data[cb].row * b.cols + b.data[cb].col
        if inda < indb:
            c.data[cc] = a.data[ca]
            cc += 1
            ca += 1
        elif inda == indb:
            if (a.data[ca].value + b.data[cb].value) != 0:
                c.data[cc].row = a.data[ca].row
                c.data[cc].col = a.data[ca].col
                c.data[cc].value = a.data[ca].value + b.data[cb].value
                print(str(c.data[cc].row) + "," + str(c.data[cc].col) + "에 " + str(c.data[cc].value) + " 저장")
                cc += 1
                ca += 1
                cb += 1
            else:
                ca += 1
                cb += 1
        else:
            c.data[cc] = b.data[cb]
            cc += 1
            cb += 1

    while ca < a.terms:
        c.data[cc] = a.data[ca]
        cc += 1
        ca += 1
    while cb < b.terms:
        c.data[cc] = b.data[cb]
        cc += 1
        cb += 1
    c.terms = cc
    return c


m1 = SparseMatrix(3, 3, 9)
m1.data = [Element(0, 0, 1), Element(0, 1, 1), Element(0, 2, 1),
           Element(1, 0, 1), Element(1, 1, 1), Element(1, 2, 1),
           Element(2, 0, 1), Element(2, 1, 1), Element(2, 2, 1)]
m2 = SparseMatrix(3, 3, 9)
m2.data = [Element(0, 0, 1), Element(0, 1, 2), Element(0, 2, 3),
           Element(1, 0, 4), Element(1, 1, 5), Element(1, 2, 6),
           Element(2, 0, 7), Element(2, 1, 8), Element(2, 2, 9)]

m3 = sparse_matrix_add2(m1, m2)
m1.print_matrix()
print()
m2.print_matrix()
print()
m3.print_matrix()



# a = SparseMatrix(ROWS, COLS, MAX_TERMS)
# a.data[0].row = 1
# a.data[8].value = 10
# print(a.row)
# for v in a.data:
#     print(v.row, end=' ')
#     print(v.col, end=' ')
#     print(v.value)


