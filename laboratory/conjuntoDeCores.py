conjunto1 = set()
conjunto2 = set()
conjunto3 = set()

conjunto1.update(["vermelho", "azul", "verde", "amarelo"])
conjunto2.update(["azul", "amarelo", "laranja", "roxo"])
conjunto3.update(["verde", "roxo", "amarelo", "preto"])

while True:
    print("Selecione uma opção:")
    print("1. União entre dois conjuntos de cores")
    print("2. Interseção entre dois conjuntos de cores")
    print("3. Diferença entre dois conjuntos de cores")
    print("4. Sair do programa")

    opcao = input("Opção: ")

    if opcao == '1':
        conjunto1_id = int(
            input("Selecione o primeiro conjunto (1, 2 ou 3): "))
        conjunto2_id = int(input("Selecione o segundo conjunto (1, 2 ou 3): "))

        if conjunto1_id == 1 and conjunto2_id == 2:
            resultado = conjunto1.union(conjunto2)
        elif conjunto1_id == 1 and conjunto2_id == 3:
            resultado = conjunto1.union(conjunto3)
        elif conjunto1_id == 2 and conjunto2_id == 3:
            resultado = conjunto2.union(conjunto3)
        else:
            print("Opção inválida!")
            continue

        print("Resultado da união:", resultado)

    elif opcao == '2':
        conjunto1_id = int(
            input("Selecione o primeiro conjunto (1, 2 ou 3): "))
        conjunto2_id = int(input("Selecione o segundo conjunto (1, 2 ou 3): "))

        if conjunto1_id == 1 and conjunto2_id == 2:
            resultado = conjunto1.intersection(conjunto2)
        elif conjunto1_id == 1 and conjunto2_id == 3:
            resultado = conjunto1.intersection(conjunto3)
        elif conjunto1_id == 2 and conjunto2_id == 3:
            resultado = conjunto2.intersection(conjunto3)
        else:
            print("Opção inválida!")
            continue

        print("Resultado da interseção:", resultado)

    elif opcao == '3':
        conjunto1_id = int(
            input("Selecione o primeiro conjunto (1, 2 ou 3): "))
        conjunto2_id = int(input("Selecione o segundo conjunto (1, 2 ou 3): "))

        if conjunto1_id == 1 and conjunto2_id == 2:
            resultado = conjunto1.difference(conjunto2)
        elif conjunto1_id == 1 and conjunto2_id == 3:
            resultado = conjunto1.difference(conjunto3)
        elif conjunto1_id == 2 and conjunto2_id == 3:
            resultado = conjunto2.difference(conjunto3)
        else:
            print("Opção inválida!")
            continue

        print("Resultado da diferença:", resultado)

    elif opcao == '4':
        print("Programa encerrado.")
        break

    else:
        print("Opção inválida!")
