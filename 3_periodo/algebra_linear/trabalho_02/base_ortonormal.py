def sistema(matriz):
    n = len(matriz)
    for i in range(n):
        max_index = i
        for k in range(i + 1, n):
            if abs(matriz[k][i]) > abs(matriz[max_index][i]):
                max_index = k
        matriz[i], matriz[max_index] = matriz[max_index], matriz[i]
        pivot = matriz[i][i]
        for j in range(i, n + 1):
            matriz[i][j] /= pivot
        for k in range(i + 1, n):
            factor = matriz[k][i]
            for j in range(i, n + 1):
                matriz[k][j] -= factor * matriz[i][j]
    solucoes = [0] * n
    for i in range(n - 1, -1, -1):
        solucao = matriz[i][n]
        for j in range(i + 1, n):
            solucao -= matriz[i][j] * solucoes[j]
        solucoes[i] = solucao
    return solucoes


def escalar(v1, v2):
    return sum(x * y for x, y in zip(v1, v2))


def algoritmo(base):
    u = []
    for i in range(len(base)):
        u.append(base[i])
    for i in range(1, len(base)):
        for j in range(i):
            projection = escalar(u[i], u[j]) / escalar(u[j], u[j])
            u[i] = [u[i][k] - projection * u[j][k] for k in range(len(u[i]))]
    for i in range(len(u)):
        magnitude = sum(x**2 for x in u[i]) ** 0.5
        u[i] = [x / magnitude for x in u[i]]
    return u


def main():
    vetor = [1, 2, 3]
    base = [[1, 1, 0], [2, 0, 1], [2, 2, 1]]
    matriz = []
    for i in range(len(vetor)):
        linha = []
        for j in range(len(vetor)):
            linha.append(base[j][i])
        linha.append(vetor[i])
        matriz.append(linha)
    print("Novas coordenadas:", sistema(matriz))
    print("\nBase ortonormal:", algoritmo(base))


main()
