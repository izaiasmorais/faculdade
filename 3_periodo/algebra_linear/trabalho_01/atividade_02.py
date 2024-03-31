def determinante(matriz):
    n = len(matriz)
    for linha in matriz:
        if len(linha) != n:
            raise ValueError("A matriz não é quadrada")
    if n == 1:
        return matriz[0][0]
    if n == 2:
        return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0]
    det = 0
    for coluna in range(n):
        cofator = (
            matriz[0][coluna]
            * sinal_cofator(1, coluna + 1)
            * determinante(matriz_menor(matriz, 0, coluna))
        )
        det += cofator
    return det


def sinal_cofator(i, j):
    return (-1) ** (i + j)


def matriz_menor(matriz, linha, coluna):
    return [
        row[:coluna] + row[coluna + 1 :]
        for row in (matriz[:linha] + matriz[linha + 1 :])
    ]


n = int(input("Digite a ordem da matriz quadrada (n): "))
matriz = []

print(f"Digite os elementos da matriz {n}x{n} linha por linha:")
for _ in range(n):
    linha = list(map(float, input().split()))
    matriz.append(linha)

det = determinante(matriz)
print(f"O determinante da matriz é: {det}")
