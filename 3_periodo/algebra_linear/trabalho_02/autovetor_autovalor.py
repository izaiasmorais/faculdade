def produto_matriz_vetor(matriz, vetor):
    result = []
    for i in range(len(matriz)):
        linha = sum(matriz[i][j] * vetor[j] for j in range(len(vetor)))
        result.append(linha)
    return result


def normalizar_vetor(vetor):
    norma = sum(x**2 for x in vetor) ** 0.5
    return [x / norma for x in vetor]


def metodo_potencia(matriz, num_iteracoes=1000, tolerancia=1e-6):
    n = len(matriz)
    v = [1.0] * n

    for _ in range(num_iteracoes):
        Av = produto_matriz_vetor(matriz, v)

        autovalor = sum(Av[i] / v[i] for i in range(n)) / n

        v = normalizar_vetor(Av)

        if abs(autovalor - v[0]) < tolerancia:
            break

    return autovalor, v


if __name__ == "__main__":
    # matriz_exemplo = [[4, -2], [1, 1]]
    # matriz_exemplo = [[0.3, 0.7, 0.5],
    # [0.4, 0.2, 0.5],
    # [0.3, 0.1, 0.0]]
    # matriz_exemplo = [[0, 0, 1 / 2, 1 / 2], [1, 0, 0, 1 / 2], [0, 1 / 2, 0, 0],
    #                   [0, 1 / 2, 1 / 2, 0]]
    matriz_exemplo = [[-5, 2], [2, -2]]

    autovalor, autovetor = metodo_potencia(matriz_exemplo)

    print("Autovalor aproximado:", autovalor)
    print("Autovetor aproximado:", autovetor)
