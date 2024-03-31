A = [[0, 0, 1 / 2, 1 / 2], [1, 0, 0, 1 / 2], [0, 1 / 2, 0, 0], [0, 1 / 2, 1 / 2, 0]]

v = [1] * len(A)


def normalizar_vetor(vec):
    norma = sum(vec)
    return [x / norma for x in vec]


v = normalizar_vetor(v)

tolerancia = 1e-6
max_iteracoes = 1000


def multiplicar_matriz_vetor(matriz, vetor):
    resultado = []
    for i in range(len(matriz)):
        linha = matriz[i]
        resultado.append(sum(linha[j] * vetor[j] for j in range(len(linha))))
    return resultado


for _ in range(max_iteracoes):
    v_proximo = multiplicar_matriz_vetor(A, v)
    v_proximo = normalizar_vetor(v_proximo)

    if sum(abs(v_proximo[i] - v[i]) for i in range(len(v))) < tolerancia:
        break

    v = v_proximo

print("Autovetor associado ao autovalor 1:")
print(v)
