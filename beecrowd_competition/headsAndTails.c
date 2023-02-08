#include <stdio.h>

int main(void) {
  int M, J, P, V;

  while (scanf("%d", &P)) {
    if (P == 0)
      break;

    M = 0, J = 0;

    for (int i = 0; i < P; i++) {
      scanf("%d", &V);

      if (V == 0)
        M++;
      else if (V == 1)
        J++;
    }

    printf("Mary won %d times and John won %d times\n", M, J);
  }

  return 0;
}
