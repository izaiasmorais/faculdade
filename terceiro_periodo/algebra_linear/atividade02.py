def determinant(matrix):
    n = len(matrix)
    for row in matrix:
        if len(row) != n:
            raise ValueError("A matriz não é quadrada")
    if n == 1:
        return matrix[0][0]
    if n == 2:
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
    det = 0
    for col in range(n):
        cofactor = matrix[0][col] * cofactor_sign(0, col) * determinant(minor_matrix(matrix, 0, col))
        det += cofactor
    return det

def cofactor_sign(i, j):
    return (-1) ** (i + j)

def minor_matrix(matrix, row, col):
    return [row[:col] + row[col + 1:] for row in (matrix[:row] + matrix[row + 1:])]

n = int(input("Digite a ordem da matriz quadrada (n): "))
matrix = []

print(f"Digite os elementos da matriz {n}x{n} linha por linha:")
for _ in range(n):
    row = list(map(float, input().split()))
    matrix.append(row)

det = determinant(matrix)
print(f"O determinante da matriz é: {det}")
