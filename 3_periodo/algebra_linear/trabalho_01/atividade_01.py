def encontrar_transmissao_direta(matriz, origem, destino):
    if matriz[origem][destino] == 1:
        return [(origem, destino)]
    else:
        return []


def encontrar_retransmissao_uma(matriz, origem, destino):
    retransmissoes = []
    for i in range(len(matriz)):
        if matriz[origem][i] == 1 and matriz[i][destino] == 1:
            retransmissoes.append((origem, i, destino))
    return retransmissoes


def encontrar_retransmissao_duas(matriz, origem, destino):
    retransmissoes_duas = []
    for i in range(len(matriz)):
        if matriz[origem][i] == 1:
            retransmissoes = encontrar_retransmissao_uma(matriz, i, destino)
            for retransmissao in retransmissoes:
                retransmissoes_duas.append(
                    (origem, retransmissao[0], retransmissao[1], destino)
                )
    return retransmissoes_duas


def imprimir_caminhos(caminhos):
    for caminho in caminhos:
        print(" -> ".join(map(str, caminho)))


matriz = [
    [0, 1, 1, 1, 1],
    [1, 0, 1, 1, 0],
    [0, 1, 0, 1, 0],
    [0, 0, 1, 0, 1],
    [0, 0, 0, 1, 0],
]

origem = int(input("Digite o número da estação de origem (1 a 5): ")) - 1
destino = int(input("Digite o número da estação de destino (1 a 5): ")) - 1

transmissao_direta = encontrar_transmissao_direta(matriz, origem, destino)
retransmissao_uma = encontrar_retransmissao_uma(matriz, origem, destino)
retransmissao_duas = encontrar_retransmissao_duas(matriz, origem, destino)

print("\n(i) Com transmissão direta:")
imprimir_caminhos(transmissao_direta)

print("\n(ii) Com no máximo uma retransmissão:")
imprimir_caminhos(retransmissao_uma)

print("\n(iii) Com no máximo duas retransmissões:")
imprimir_caminhos(retransmissao_duas)
