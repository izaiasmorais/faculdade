#include <stdio.h>

int main(void) {
  int R, A, B;
  int p1, p2;

  while (scanf("%d", &R)) {
    if (!R)
      break;

    p1 = 0, p2 = 0;

    for (int i = 0; i < R; i++) {

      scanf("%d %d", &A, &B);

      if (A > B)
        p1 = p1 + 1;
      else if (A < B)
        p2 = p2 + 1;
    }

    printf("%d %d\n", p1, p2);
  }

  return 0;
}
