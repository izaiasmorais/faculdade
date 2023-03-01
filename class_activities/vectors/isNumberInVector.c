#include <stdio.h>
#define tam 10

int main() {

  int numbers[tam], n, i, t;

  printf("Digite 10 números: \n");

  for (i = 0; i < tam; i++) {
    scanf("%d", &numbers[i]);
  }

  printf("Digite um inteiro qualquer: \n");
  scanf("%d", &n);

  for (i = 0; i < tam; i++) {
    if (numbers[i] == n) {
      t = 1;
      break;
    } else {
      t = 0;
    }
  }

  if (t == 1) {
    printf("O número foi achado\n");
  } else {
    printf("O número não foi achado\n");
  }

  return 0;
}
