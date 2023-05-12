#include <stdio.h>
#include <string.h>
#define tam 100

typedef struct {
  char nome[80];
  char email[80];
} Pessoa;

typedef struct {
  int quant;
  Pessoa pessoas[tam];
} Conjunto;

int search(char email[], Conjunto *users) {
  for (int i = 0; i < users->quant; i++) {
    if (strcmp(email, users->pessoas[i].email) == 0)
      return i;
  }
  return -1;
}

int signout(Pessoa p, Conjunto *users) {
  if (users->quant == tam)
    return 0;

  if (search(p.email, users) != -1)
    return -1;

  users->pessoas[users->quant] = p;
  users->quant++;
  return 1;
}

int main(void) {
  Pessoa user;
  Conjunto users;
  users.quant = 0;

  char email[tam];
  int i, v;

  int option = -1;

  while (option != 0) {
    printf("Escolha uma opção: \n");
    printf("1 - Cadastrar\n2 - Localizar\n0 - Sair\n");
    scanf("%d", &option);

    if (option == 1) {
      printf("Digite seu nome e email: \n");
      scanf("%s%s", user.nome, user.email);
      printf("\n");

      v = signout(user, &users);

      if (v == 0)
        printf("Impossível cadastrar já que o conjunto está cheio!\n");
      else if (v == -1)
        printf("Já existe uma pessoa com este email cadastrado!\n");
      else
        printf("Pessoa cadastrada com sucesso!\n");

    } else if (option == 2) {
      printf("Digite o email do usuário que quer buscar: \n");
      scanf("%s", email);
      printf("\n");

      v = search(email, &users);

      if (v == -1) {
        printf("Usuário não existente!\n");
      } else {
        printf("O usuário está na posição %d\n", v);
      }
    }
  }

  for (i = 0; i < users.quant; i++) {
    printf("email: %s\n", users.pessoas[i].email);
    printf("nome: %s\n", users.pessoas[i].nome);
    printf("-----------------------------\n");
  }

  return 0;
}
