opcao = -1
alunos = []


def listarAlunos():
    if len(alunos) > 0:
        for aluno in alunos:
            if aluno == alunos[-1]:
                print(aluno)
            else:
                print(aluno, end=" > ")
    else:
        print("Nao existem alunos para listar")
    print("")


def buscarAluno(nome):
    index = 0
    for aluno in alunos:
        if aluno != nome:
            index += 1
        else:
            return index
    return -1


while opcao != 5:
    print("Digitar '1' para inserir um novo aluno na lista.")
    print("Digitar '2' para buscar um aluno na lista.")
    print("Digitar '3' para exibir a lista de alunos em ordem alfabética.")
    print("Digitar '4' para remover um aluno da lista.")
    print("Digitar '5' para sair do programa.")

    opcao = int(input(""))

    if opcao == 1:
        novoNome = input("Digite um novo nome: ")
        alunos.append(novoNome)
        listarAlunos()
    elif opcao == 2:
        alunosProcurado = input("Digite o nome que quer buscar: ")
        resultado = buscarAluno(alunosProcurado)
        if resultado == -1:
            print("Aluno nao encontrado")
        else:
            print("O aluno foi encontrado na posicao %d" % resultado + 1)
    elif opcao == 3:
        alunos.sort()
        listarAlunos()
    elif opcao == 4:
        aluno = input("Digite um novo nome: ")
        alunos.remove(aluno)
        listarAlunos()
    elif opcao == 5:
        break

print("O programa foi finalizado")
