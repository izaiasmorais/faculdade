#include <stdio.h>
#define tam 1000

int main() {
  int N[tam], T, i;

  scanf("%d", &T);

  N[0] = 0;

  for (i = 1; i < tam; i++) {

    if ((N[i - 1] + 1) == T)
      N[i] = 0;
    else
      N[i] = N[i - 1] + 1;
  }

  for (i = 0; i < tam; i++) {
    printf("N[%d] = %d\n", i, N[i]);
  }

  return 0;
}
