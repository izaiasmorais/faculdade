#include <stdio.h>

int main() {
  double item, qtd, total, value;

  scanf("%lf", &item);
  scanf("%lf", &qtd);

  if (item == 1) {
    value = 4;
  } else if (item == 2) {
    value = 4.5;
  } else if (item == 3) {
    value = 5;
  } else if (item == 4) {
    value = 2;
  } else if (item == 5) {
    value = 1.5;
  }

  total = value * qtd;

  printf("Total: R$ %.2lf\n", total);

  return 0;
}
