#include <stdio.h>

int main() {
  int start, end, total;

  scanf("%d %d", &start, &end);

  if (start > end) {
    total = 24 - start + end;
  } else if (start == end) {
    total = 24;
  } else {
    total = end - start;
  }

  printf("O JOGO DUROU %d HORA(S) \n", total);

  return 0;
}
