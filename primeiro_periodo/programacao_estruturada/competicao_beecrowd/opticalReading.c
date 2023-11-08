
#include <stdio.h>

// A função deve ler 5 números
// No máximo 1 número deve ser menor ou igual a 127
// O menor número dentre os 5 números fará ser printado A, B, C, D ou E
// A sequência de "cores" deve ser digitada tantas vezes quanto o usuário quiser
// O loop termina quando é digitado 0 para o valor de repetições

int main() {
  int A, B, C, D, E, R;

  while (scanf("%d", &R)) {
    if (R == 0)
      break;

    for (int i = 0; i < R; i++) {
      scanf("%d %d %d %d %d", &A, &B, &C, &D, &E);

      if (A <= 127 && B > 127 && C > 127 && D > 127 && E > 127)
        printf("A\n");
      else if (A > 127 && B <= 127 && C > 127 && D > 127 && E > 127)
        printf("B\n");
      else if (A > 127 && B > 127 && C <= 127 && D > 127 && E > 127)
        printf("C\n");
      else if (A > 127 && B > 127 && C > 127 && D <= 127 && E > 127)
        printf("D\n");
      else if (A > 127 && B > 127 && C > 127 && D > 127 && E <= 127)
        printf("E\n");
      else
        printf("*\n");
    }
  }

  return 0;
}
