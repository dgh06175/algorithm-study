def transpose_matrix_sparse(matrix):
    # get the number of rows and columns in the matrix
    rows, cols = len(matrix), len(matrix[0])

    # create a dictionary to store the values of the transpose matrix
    trans_dict = {}

    # iterate over the non-zero elements of the matrix and add them to the transpose dictionary
    for i in range(rows):
        for j in range(cols):
            if matrix[i][j] != 0:
                if j in trans_dict:
                    trans_dict[j][i] = matrix[i][j]
                else:
                    trans_dict[j] = {i: matrix[i][j]}

    # convert the transpose dictionary back to a matrix
    transpose = [[trans_dict.get(j, {}).get(i, 0) for j in range(cols)] for i in range(rows)]

    return transpose


def multiply_matrices_sparse(matrix1, matrix2):
    # get the number of rows and columns in the matrices
    rows1, cols1 = len(matrix1), len(matrix1[0])
    rows2, cols2 = len(matrix2), len(matrix2[0])

    # create a dictionary to store the values of the resulting matrix
    result_dict = {}

    # iterate over the non-zero elements of the matrices and perform the multiplication
    for i in range(rows1):
        for j in range(cols2):
            result = 0
            for k in range(cols1):
                if matrix1[i][k] != 0 and matrix2[k][j] != 0:
                    result += matrix1[i][k] * matrix2[k][j]
            if result != 0:
                if i in result_dict:
                    result_dict[i][j] = result
                else:
                    result_dict[i] = {j: result}

    # convert the result dictionary back to a matrix
    result = [[result_dict.get(i, {}).get(j, 0) for j in range(cols2)] for i in range(rows1)]

    return result


# example usage
sparse_matrix = [
    [0, 0, 0, 0, 0],
    [0, 0, 0, 7, 0],
    [0, 3, 0, 0, 0],
    [0, 0, 0, 0, 0],
    [0, 0, 2, 0, 0]
]

# transpose the matrix
transpose_sparse = transpose_matrix_sparse(sparse_matrix)

# multiply the original matrix by its transpose
result_sparse = multiply_matrices_sparse(sparse_matrix, transpose_sparse)

# print the result
for v in result_sparse:
    for k in v:
        print("%3s" % k, end="")
    print()
