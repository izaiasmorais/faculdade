rotas = []

while True:
  print("1 - para adicionar um novo ponto GPS à rota.")
  print("2 - para remover um ponto GPS da rota.")
  print("3 - para exibir a rota atual.")
  print("4 - para sair do programa")
  opcao = int(input("Digite um opcao: "))

  if (opcao == 1):
    a = int(input("Origem: "))
    b = int(input("Destino: "))

    ponto = (a, b)
    rotas.append(ponto)
    print(rotas)
    print("")
  elif (opcao == 2):
    a = int(input("Origem a remover: "))
    b = int(input("Destino a remover: "))

    ponto = (a, b)
    rotas.remove(ponto)
    print(rotas)
    print("")
  elif (opcao == 3):
    index = int(input("Digite a posicao da rota: "))
    print(rotas[index - 1])
    print("")
  elif (opcao == 4):
    break
